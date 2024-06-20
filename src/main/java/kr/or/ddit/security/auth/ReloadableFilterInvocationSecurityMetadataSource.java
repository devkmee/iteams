package kr.or.ddit.security.auth;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import kr.or.ddit.iteams.common.error.ForbiddenException;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.vo.MasterVOWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 동적으로 변경될 수 있는 역할별 접근 제어 설정을 리로드 할 수 있는 metadataSource 
 * 
 */
@Slf4j
public class ReloadableFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
	@Inject
	private WebApplicationContext container;
	
	private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;
	
	public ReloadableFilterInvocationSecurityMetadataSource(
			LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
		this.requestMap = requestMap;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
				.entrySet()) {
			allAttributes.addAll(entry.getValue());
		}

		return allAttributes;
	}
	
	
	//pathVariable => requestURL에 포함
//	인증객체 => SecurityContextHolder.getContext().getAuthentication();
	//작성자, 담당자, 수정권한 => 파라미터로 전달
	//여기서 리턴은 논리적인 뷰 네임같음
	public Collection<ConfigAttribute> getAttributes(Object object) {
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
		final HttpServletResponse response = ((FilterInvocation) object).getHttpResponse();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		FlashMap flashMap = new SessionFlashMapManager().retrieveAndUpdate(request, null);
		
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
				.entrySet()) {
			if (entry.getKey().matches(request)) {
				if(authentication != null) {
					Object principal = authentication.getPrincipal();
					if(principal instanceof MasterVOWrapper) {
						HttpSession session = request.getSession();
						MasterVOWrapper wrapper = (MasterVOWrapper) principal;
						MasterVO authMember = wrapper.getAuthMember();
						String authMemId = authMember.getMemId();
						//PMS 권한 => PL, PM , AA ... 등
						String auth = authMember.getAuthority();
						//회원 권한 => 개발자, 클라이언트, 관리자
						String memRole = authMember.getMemRole();
						//요청 URL
						String requestURL = request.getRequestURL().toString();
						
						//작성자 ID파라미터
						String originWriter = request.getParameter("writer");
						//일감 담당자 파라미터
						String workCharger = request.getParameter("charger");
						//웹하드, 기술문서함 수정 권한 파라미터
						String editAuth = request.getParameter("auth");
						
						//프로젝트 생성은 PathVariable을 받지 않음
						String createProjectURL = "/pms/project/projectInsert.do";
						boolean token = requestURL.contains(createProjectURL);
						//PMS 접근 제한
						if(!token && StringUtils.contains(requestURL, "/pms")) {
							char[] proNoChar = new char[] {requestURL.charAt(requestURL.lastIndexOf("/") - 1)};
							String[] splitRequestURL = requestURL.split("/");
							String proNo2 = splitRequestURL[splitRequestURL.length-2];
							String proNo = new String(proNoChar);
							log.info("PathVariable : {}", proNo);
							String ingProjectNum = authMember.getProNo();
							//진행중인 proNo 목록
							List<String> ingProNoList = authMember.getProNoList();
							//소속 프로젝트가 아닌경우 접근 제한
							if(ingProNoList == null) {
								throw new AccessDeniedException("접근 권한이 없습니다.");
							}						
							if(!ingProNoList.contains(proNo2)) {
								throw new AccessDeniedException("접근 권한이 없습니다.");
							} //소속 프로젝트가 아닌경우 접근 제한 끝
							
							//PMS Update, Delete 접근 제한
							if(StringUtils.contains(requestURL, "Update.do") ||StringUtils.contains(requestURL, "Delete.do")) {
								//일감 Update, Delete 접근 제한 시작
								if(StringUtils.contains(requestURL, "/work")) {
									if(!StringUtils.equalsAnyIgnoreCase(auth, "PM", "PL") && 
											!StringUtils.equals(workCharger, authMemId) &&
											!StringUtils.equals(originWriter, authMemId)) {
										throw new AccessDeniedException("접근 권한이 없습니다.");
									}
								} // 일감 Update, Delete 접근 제한 시작 끝
								//웹하드, 문서함 Update, Delete 접근 제한 시작
								else if(StringUtils.contains(requestURL, "/webhard") || StringUtils.contains(requestURL, "/documents")) {
									if(!StringUtils.equalsAnyIgnoreCase(auth, "PM", "PL")) {
										if(!StringUtils.equals(editAuth, auth) && !StringUtils.equals(editAuth, "ALL")) {	
											if(!StringUtils.equals(originWriter, authMemId)) {
												if(!StringUtils.contains(requestURL, "/documents") && !StringUtils.contains(requestURL, "/Update.do")) {
													throw new AccessDeniedException("\"message\" : \"403\"");
												}
											}
										}
									}
								} // 웹하드, 문서함 Update, Delete 접근 제한 시작 끝

								//그 외의 PMS Update, Delete 접근 제한
								else { 
									if(!StringUtils.equalsAnyIgnoreCase(auth, "PM", "PL") &&
										!StringUtils.equals(originWriter, authMemId)) {
										throw new AccessDeniedException("접근 권한이 없습니다.");
									}
								} //그 외의 PMS Update, Delete 접근 제한 끝
							} //PMS Update, Delete 접근 제한 끝
						} // PMS 접근제한 끝
						
						
						//관리자 메뉴 접근 제한
						if(StringUtils.contains(requestURL, "/admin")) {
							if(!StringUtils.equalsIgnoreCase(memRole, "ADMIN")) {
								throw new AccessDeniedException("접근 권한이 없습니다.");
							}
						}					
						//아웃소싱 접근제한
						if(StringUtils.contains(requestURL, "/outs") && 
								(StringUtils.contains(requestURL, "Update.do") ||
								StringUtils.contains(requestURL, "Delete.do"))) {
							if(!StringUtils.equalsIgnoreCase(memRole, "ADMIN")) {
								if(!StringUtils.equals(originWriter, authMemId)) {
									throw new AccessDeniedException("접근 권한이 없습니다.");
								}
							}
						} //아웃소싱 Update, Delete 접근제한 끝
						
					}		
				}
				return entry.getValue();
			}
		}
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
	
	public void reload() {
		LinkedHashMap<RequestMatcher, List<ConfigAttribute>> reloadedRequestMap 
									= container.getBean("requestMap", LinkedHashMap.class);
		synchronized (requestMap) {
			requestMap.clear();
			requestMap.putAll(reloadedRequestMap);
		}
	}
}
