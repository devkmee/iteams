package kr.or.ddit.iteams.outs.board.projectboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.outs.board.projectboard.dao.OutsProjectDAO;

@RestController		// json 데이터 리퀘스트 바디 (Controller + Request Body 역할) // 신고 할 때 비동기 처리 
public class RepController {
	
	@Inject
	private OutsProjectDAO dao;
	
	@RequestMapping(value="/outs/board/projectboard/report.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object report(@RequestParam("what") String number
			, HttpServletRequest req 
			, HttpServletResponse resp){ 
		
		boolean valid = true; 
		ServiceResult result = null; 
		
		Cookie[] cookies = req.getCookies(); 
		for(Cookie tmp : cookies) {
			if(tmp.getName().equals("rep") && tmp.getValue().equals(String.valueOf(number))) {
				valid = false;
				result = ServiceResult.FAILED;
			}
		}
		
		if(valid) {
			Map<String, Object> pMap = new HashMap<>();
			pMap.put("number", number);
			pMap.put("incType", "BO_REP");	// 신고수 
			int cnt = dao.incrementCount(pMap);
			if(cnt > 0) {
				result = ServiceResult.OK;
				Cookie cookie = new Cookie("rep", String.valueOf(number));
				resp.addCookie(cookie);
			}else {
				result = ServiceResult.FAILED;
			}
		}
		
		Map<String, String> resultMap = new HashMap<>();
		String message = result == ServiceResult.OK ? "게시글을 신고했습니다." : "신고는 한 번만 할 수 있습니다.";
		resultMap.put("message", message);
		
		return resultMap;
	}
}