package kr.or.ddit.iteams.outs.login;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import kr.or.ddit.iteams.common.vo.MasterVO;


public class PMSAuthInterceptor extends HandlerInterceptorAdapter{
	
//	@Inject
//	private SecurityContext context;
//	@Inject
//	private SecurityContextHolder holder;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		Authentication authentication = holder.getContextHolderStrategy().getContext().getAuthentication();
//		Authentication authentication = context.getAuthentication();
		HttpSession session = request.getSession();
		Map<String, String> pathVarialbles = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String pathValue = pathVarialbles.get("proNo"); 
		String url = request.getRequestURL().toString();
		//요청 발생전 원래의 URL
		String preRequestOriginURL = (String) session.getAttribute("originURL");
		MasterVO authentication = (MasterVO) session.getAttribute("authMember");
		RedirectAttributes redirectAttr = new RedirectAttributesModelMap();
		String message = null;
		
		//파라미터가 what인지 who인지에 따라 저장해 놓을 url을 다르게 구성
		if(request.getParameter("what") != null) {		
			url = url + "?what=" + request.getParameter("what");
		} else if(request.getParameter("who") != null) {		
			url = url + "?who=" + request.getParameter("who");
		}
		
		
		//관리자 기능 접근 제한
		if(StringUtils.contains("/admin", url)) {
			if(StringUtils.equalsIgnoreCase("ADMIN", authentication.getMemRole())) {
				session.setAttribute("originURL", url);
				return true;
			} else {
				response.sendError(response.SC_FORBIDDEN, "접근 권한이 없습니다.");
				return false;
			}
		}
		
		
		
	
		
		//프로젝트 생성은 PathVariable을 받지 않음
		String createProjectURL = "/pms/project/projectInsert.do";
		boolean token = url.contains(createProjectURL);
		//pms 자신이 소속하지 않은 프로젝트로 요청시 접근 제한
		if(authentication != null && !token && StringUtils.contains(url, "/pms")) {
			String ingProjectNum = authentication.getProNo();
			
			if(!StringUtils.equals(pathValue, ingProjectNum)) {
				response.sendError(response.SC_FORBIDDEN, "접근 권한이 없습니다.");
				return false;
			}
		}
		
		//아웃소싱 커뮤니티쪽 단순 조회는 비로그인도 가능
		if(!StringUtils.contains(url, "/pms") && !StringUtils.contains(url, "/mypage")
				&& (StringUtils.contains(url, "List.do") ||
						StringUtils.contains(url, "View.do") ||
						StringUtils.contains(url, "Main.do") ||
						StringUtils.contains(url, "index.do") ||
						StringUtils.equals(url, "/"))) {
			if(!StringUtils.contains(url, "/outs/login/login.do")) {
				session.setAttribute("originURL", url);			
			}
			return true;
		}
		
//		/pms/project/{proNo}/projectView.do
		
		//그 외의 요청은 비로그인으로 접근시 접근 제한
		if(authentication == null) {
			if(!StringUtils.contains(url, "/outs/login/login.do")) {
				session.setAttribute("originURL", url);			
			}
			message = "로그인이 필요한 서비스입니다.";
			redirectAttr.addFlashAttribute("message", message);
			response.sendRedirect(request.getContextPath()+"/outs/login/login.do");
			return false;
		}
		
		String authMemId = authentication.getMemId();
		//PMS 권한 => PL, PM , AA ... 등
		String auth = authentication.getAuthority();
		//회원 권한 => 개발자, 클라이언트, 관리자
		String memRole = authentication.getMemRole();
		/**
		 * update, delete 작업 접근 제한 시작
		 */
		//아웃소싱쪽 update, delete 작업 접근 제어 => 작성자 or 관리자만 접근 허용
		if(!StringUtils.contains(url, "/pms") && 
				(StringUtils.contains(url, "Update.do") ||
						StringUtils.contains(url, "Delete.do"))) {
			//관리자는 무조건 접근 허용
			if(StringUtils.equalsIgnoreCase(memRole, "ADMIN")) {
				if(!StringUtils.contains(url, "/outs/login/login.do")) {
					session.setAttribute("originURL", url);			
				}
				return true;
			}
			//작성자 ID 파라미터로 넣어줘야함 => name=writer
			String originWriter = request.getParameter("writer");
			//작성자 ID 와 현재 로그인한 유저의 ID가 같으면 통과 틀리면 접근 제한
			if(!StringUtils.equals(originWriter, authMemId)) {
				//접근 거부시 돌려보낼 곳
				String whatParam = request.getParameter("what");
				message = "작성자만 수정이나 삭제 할 수 있습니다.";
				redirectAttr.addFlashAttribute("message", message);
				response.sendRedirect(preRequestOriginURL);
				return false;
			} else {
				if(!StringUtils.contains(url, "/outs/login/login.do")) {
					session.setAttribute("originURL", url);			
				}
				return true;
			}
			//PMS쪽 update, delete 작업 접근 제어 => 작성자 or 관리자만 접근 허용
			//일감은 담당자도 작업 가능
		} else if(StringUtils.contains(url, "/pms") && 
				(StringUtils.contains(url, "Update.do") ||
						StringUtils.contains(url, "Delete.do"))) {
			//작성자
			String originWriter = request.getParameter("writer");
			//일감은 담당자도 접근 허용하게 한다
			if(StringUtils.contains(url, "/work")) {
				//일감은 PL 또는 PM 또는 작성자 또는 담당자면 무조건 접근 허용
				//일감에서 update, delete를 할땐 workCharger, writer 파라미터로 보내줄 것
				String workCharger = request.getParameter("charger");
				if(StringUtils.equalsAnyIgnoreCase(auth, "PM", "PL") || 
						StringUtils.equals(workCharger, authMemId) ||
						StringUtils.equals(originWriter, authMemId)) {
					if(!StringUtils.contains(url, "/outs/login/login.do")) {
						session.setAttribute("originURL", url);			
					}
					return true;
				} else {
					String whatParam = request.getParameter("what");
					message = "작성자나 담당자만 수정이나 삭제 할 수 있습니다.";
					redirectAttr.addFlashAttribute("message", message);
					response.sendRedirect(preRequestOriginURL);
					return false;
				}	
				
				//웹하드, 문서함의 경우
			} else if(StringUtils.contains(url, "/webhard") || StringUtils.contains(url, "/document")) {
				//현재 글의 수정 권한
				String editAuth = request.getParameter("auth");
				if(StringUtils.equalsAnyIgnoreCase(auth, "PM", "PL") || 
						StringUtils.equals(editAuth, auth) ||
						StringUtils.equals(originWriter, authMemId)) {
					if(!StringUtils.contains(url, "/outs/login/login.do")) {
						session.setAttribute("originURL", url);			
					}
					return true;
				} else {
					//접근 거부시 돌려보낼 곳
					String whatParam = request.getParameter("what");
					message = "작성자나 책임자만 수정이나 삭제 할 수 있습니다.";
					redirectAttr.addFlashAttribute("message", message);
					response.sendRedirect(preRequestOriginURL);
					return false;
				}
				
			}  else {
				if(StringUtils.equalsAnyIgnoreCase(auth, "PM", "PL") ||
						StringUtils.equals(originWriter, authMemId)) {
					if(!StringUtils.contains(url, "/outs/login/login.do")) {
						session.setAttribute("originURL", url);			
					}
					return true;
				} else {
					//접근 거부시 돌려보낼 곳
					String whatParam = request.getParameter("what");
					message = "작성자만 수정이나 삭제 할 수 있습니다.";
					redirectAttr.addFlashAttribute("message", message);
					response.sendRedirect(preRequestOriginURL);
					return false;
				}
			}
		}
		
		if(!StringUtils.contains(url, "/outs/login/login.do")) {
			session.setAttribute("originURL", url);			
		}
		return true;
	}

}
