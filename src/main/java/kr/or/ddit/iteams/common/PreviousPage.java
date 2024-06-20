package kr.or.ddit.iteams.common;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class PreviousPage {
	
	public static String getPreviousPageByRequest(HttpServletRequest request)
	{
		String pre = request.getHeader("Referer") == null ? "/" : request.getHeader("Referer");
		String viewName = "redirect:" + pre;
	   return viewName;
	}
}
