package kr.or.ddit.iteams.outs.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SaveLastURLInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String url = request.getRequestURL().toString();
		String whatParam = request.getParameter("what");
		String whoParam = request.getParameter("who");
		if(whatParam != null) {
			url = url + "?what=" +whatParam;
		} else if(whoParam != null) {
			url = url + "?who=" +whoParam;
		}
		
		if(!StringUtils.contains(url, "/outs/login/login.do")) {
			session.setAttribute("tempURL", url);			
		}
		return true;
	}
}
