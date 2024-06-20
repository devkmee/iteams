package kr.or.ddit.iteams.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CookieUtils {



	public static void cookieCheckerTest(HttpServletRequest req, HttpServletResponse resp, String cookieName,
			String newValue) {
		ServiceResult result = null;

		Cookie[] AllCookies = req.getCookies();
		String[] savedArr = new String[3];
		boolean flag = true;

		/**
		 * [0,"C"]	  [0,"D"] (NEW)
		 * [1,"B"] => [1,"C"]
		 * [2,"A"]	  [2,"B"]
		 */
		
		/*
		 * 모든 쿠키 꺼내서 쿠키 이름 ==파람쿠키이름+0이면, 기존 값은 배열[1]에 저장 + 쿠키값에 파람값 세팅 
		 * 					==파람쿠키이름+1이면, 기존 값은 배열[2]에 저장 + 쿠키값에 배열[1]값 세팅
		 * 					==파람쿠키이름+2이면, 쿠키값에 배열[2]값 세팅
		 * 
		 * 세션에 자바 객체 형태로. 컬렉션화. stack 구조. 마지막에 들어갔을때 제일 처음 꺼내게 + 마샬링
		*/
		for (Cookie cookie : AllCookies) { 
			
				if(cookie.getValue().equals(newValue)) {
					flag = false;
				}
					
				if (StringUtils.equals(cookie.getName(), cookieName + 0)
						&& flag == true) {
					String savedValue = cookie.getValue();
					savedArr[1] = savedValue;
					resp.addCookie(new Cookie(cookieName + 0, newValue));
					resp.addCookie(new Cookie(cookieName + 1, savedValue));
					result = ServiceResult.OVERRIDE;
				}
				if (StringUtils.equals(cookie.getName(), cookieName + 1)
						&& flag == true) {
					String savedValue = cookie.getValue();
					savedArr[2] = savedValue;
					resp.addCookie(new Cookie(cookieName + 1, savedArr[1]));
					resp.addCookie(new Cookie(cookieName + 2, savedValue));
					result = ServiceResult.OVERRIDE;
				}
				if (StringUtils.equals(cookie.getName(), cookieName + 2)
						&& flag == true) {
					String savedValue = cookie.getValue();
					resp.addCookie(new Cookie(cookieName + 2, savedArr[2]));
					result = ServiceResult.OVERRIDE;
				}
		} // end for
		
		if (result != ServiceResult.OVERRIDE) {
			Cookie newCookie = new Cookie(cookieName +0, newValue);
			resp.addCookie(newCookie);
			result = ServiceResult.NEW;
		}	
	}
	
	public static List<String> getCookieTest(HttpServletRequest req, HttpServletResponse resp, String cookieName) {

		List<String> valueList = new ArrayList<>();
		Cookie[] cookieArr = req.getCookies();
		int i = 0;
		
		for (Cookie cookie : cookieArr) {
			if (StringUtils.equals(cookieName + i, cookie.getName())
					&& StringUtils.isNoneBlank(cookie.getValue())) {
				valueList.add(cookie.getValue());
				i++;
			}
		}
		return valueList;
	}
	
	


	public static ServiceResult cookieChecker(HttpServletRequest req, HttpServletResponse resp, String cookieName, String boNum) {
		ServiceResult result = null;

		Cookie[] AllCookies = req.getCookies();
	 
		for (Cookie cookie : AllCookies) {
			 if(StringUtils.equals(cookieName, cookie.getName())) {
				resp.addCookie(new Cookie(cookieName, boNum));
				result = ServiceResult.OVERRIDE;
			}		
		} // end for

		if (result != ServiceResult.OVERRIDE) {
			Cookie newCookie = new Cookie(cookieName, boNum);
			resp.addCookie(newCookie);
			result = ServiceResult.NEW;
		}
		return result;
	}
	
	public static String getCookie(HttpServletRequest req, HttpServletResponse resp, String cookieName) {

		String boNum = null;
		Cookie[] cookieArr = req.getCookies();
		for (Cookie cookie : cookieArr) {
			if (StringUtils.equals(cookieName, cookie.getName())) {
				boNum = cookie.getValue();
			}
		} // end for

		return boNum;
	}

}
