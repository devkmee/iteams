package kr.or.ddit.iteams.outs.board.news.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.free.dao.FreeBoardDAO;

@RestController
public class NewsRepAndRecController {
	@Inject
	private FreeBoardDAO dao;
	
	@RequestMapping(value="/outs/board/news/recommend.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object recommend(
			@ModelAttribute MasterVO masterVO
			, HttpServletRequest req
			, HttpServletResponse resp){
		
		String boNo = masterVO.getWhat();
		boolean valid = true;
		ServiceResult result = null;
		
		Cookie[] cookies = req.getCookies();
		for(Cookie tmp :cookies) {
			if(tmp.getName().equals("rec") && tmp.getValue().equals(String.valueOf(boNo))) {
				valid = false;
				result = ServiceResult.FAILED;
			}
		}
		
		if(valid) {
			Map<String, Object> pMap = new HashMap<>();
			pMap.put("boNum", boNo);
			pMap.put("incType", "BO_REC");
			int res = dao.incrementCount(pMap);
			if(res > 0) {
				result = ServiceResult.OK;
				Cookie cookie = new Cookie("rec", String.valueOf(boNo));
				resp.addCookie(cookie);
			} else {
				result = ServiceResult.FAILED;
			}			
		}
		
		Map<String, String> resultMap = new HashMap<>();
		String message = result == ServiceResult.OK ? "게시글을 추천했습니다." : "게시글 당 추천은 한번만 할 수 있습니다.";
		resultMap.put("message", message);
		
		return resultMap;
	}
	
	@RequestMapping(value="/outs/board/news/report.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object report(@RequestParam("what") int boNo
			, HttpServletRequest req
			, HttpServletResponse resp){
		
		boolean valid = true;
		ServiceResult result = null;
		
		Cookie[] cookies = req.getCookies();
		for(Cookie tmp : cookies) {
			if(tmp.getName().equals("rep") && tmp.getValue().equals(String.valueOf(boNo))) {
				valid = false;
				result = ServiceResult.FAILED;
			}
		}
		
		if(valid) {
			Map<String, Object> pMap = new HashMap<>();
			pMap.put("boNum", boNo);
			pMap.put("incType", "BO_REP");
			int res = dao.incrementCount(pMap);
			if(res > 0) {
				result = ServiceResult.OK;
				Cookie cookie = new Cookie("rep", String.valueOf(boNo));
				resp.addCookie(cookie);
			} else {
				result = ServiceResult.FAILED;
			}
		}
		
		Map<String, String> resultMap = new HashMap<>();
		String message = result == ServiceResult.OK ? "게시글을 신고했습니다." : "게시글 당 신고는 한번만 할 수 있습니다.";
		resultMap.put("message", message);
		
		return resultMap;
	}
}




