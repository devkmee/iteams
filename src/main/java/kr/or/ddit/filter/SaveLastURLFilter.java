package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

public class SaveLastURLFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURL().toString();
		
		if(StringUtils.contains(url, "/logout.do")) {
			chain.doFilter(request, response);
		} else {
			HttpSession session = req.getSession();
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
			chain.doFilter(request, response);			
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
