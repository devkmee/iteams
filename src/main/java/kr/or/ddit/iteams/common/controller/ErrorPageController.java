package kr.or.ddit.iteams.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.iteams.common.PreviousPage;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ErrorPageController {
	
	@RequestMapping("/error404.do")
	public String error404(HttpServletResponse resp
			, HttpServletRequest req) {
		resp.setStatus(resp.SC_OK);
		String referer = PreviousPage.getPreviousPageByRequest(req);
		log.info("이전 : {} ///", referer);
		return "errors/error404";
	}
	
	@RequestMapping("/error403.do")
	public String error403(HttpServletResponse resp
			, HttpServletRequest req) {
		resp.setStatus(resp.SC_OK);
		String referer = PreviousPage.getPreviousPageByRequest(req);
		log.info("이전 : {} ///", referer);
		return "errors/error403";
	}
	
	@RequestMapping("/error500.do")
	public String error500(HttpServletResponse resp
			, HttpServletRequest req) {
		resp.setStatus(resp.SC_OK);
		String referer = PreviousPage.getPreviousPageByRequest(req);
		log.info("이전 : {} ///", referer);
		return "errors/error500";
	}
}
