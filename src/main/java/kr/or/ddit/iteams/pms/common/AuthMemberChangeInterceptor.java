package kr.or.ddit.iteams.pms.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.iteams.common.vo.MasterVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthMemberChangeInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		MasterVO authMember = (MasterVO) session.getAttribute("authMember");
		Map<String, String> pathVarialbles = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String pathValue = pathVarialbles.get("proNo");
		List<String> proNoList = authMember.getProNoList();
		
		if(authMember != null) {
			String curProNo = authMember.getProNo();
			if(!StringUtils.equals(curProNo, pathValue)) {
				 Integer findIdx = proNoList.indexOf(pathValue);
				 if(findIdx < 0) throw new AccessDeniedException("접근 권한이 없습니다.");
				 String requestProNo = proNoList.get(findIdx);
				 authMember.setProNo(requestProNo);
				 log.info("교체 전 proNo : {} / 교체 후 proNo : {}", curProNo, requestProNo);
				 return true;
			}
		}
		
		return true;
	}
	
}
