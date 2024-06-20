package kr.or.ddit.iteams.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.iteams.common.vo.MasterVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SearchVORemoveInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String preRequestURL = PreviousPage.getPreviousPageByRequest(request);
		String requestURl = request.getRequestURL().toString();
		String method = request.getMethod();
		String[] splitURL = requestURl.split("/");
		String[] splitPreRequestURL = preRequestURL.split("/");
		List<String> splitList = Arrays.asList(splitURL);
		List<String> preSplitList = Arrays.asList(splitPreRequestURL);
		log.info(splitList.toString());
		log.info("/pms 위치 {}", splitList.indexOf("pms"));
		String tempDepth = splitList.get(splitList.indexOf("pms") + 1);
		String preTempDepth = preSplitList.get(preSplitList.indexOf("pms") + 1);
		log.info("현재 작업공간 뎁스 : {} / 이전 작업공간 뎁스 : {}", tempDepth, preTempDepth);
		HttpSession session = request.getSession();
		
		if(StringUtils.equalsIgnoreCase(method, "GET")) {
			if(!StringUtils.equalsIgnoreCase(tempDepth, preTempDepth)) {
				session.removeAttribute("searchVO");				
			}
		}
		
		return true;
	}

}
