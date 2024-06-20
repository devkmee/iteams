package kr.or.ddit.iteams.outs.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogoutInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		String tempURL = (String) session.getAttribute("originURL");
		session.invalidate();
		if(StringUtils.containsIgnoreCase(tempURL, "/pms")) {
			response.sendRedirect(request.getContextPath()+"/index.do");
			return false;
		}
		
		response.sendRedirect(tempURL);
		
		return false;
	}

}
