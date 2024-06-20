package kr.or.ddit.iteams.outs.login;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import kr.or.ddit.iteams.outs.login.dao.AuthDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginFailHandler implements AuthenticationFailureHandler{
	
//	@Inject
//	private AuthDAO dao;
//	
//	@Inject
//	private PasswordEncoder encoder;
	
	private String defaultLoginUrl;
	
	public static final String AUTHENTICATION_MESSAGE = "FLASH_AUTHENTICATION_MESSAGE";
	
	public LoginFailHandler(String defaultLoginUrl) {
		this.defaultLoginUrl = defaultLoginUrl;
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String message = null;
		String memId = request.getParameter("memId");
		HttpSession session = request.getSession();
		
		if(exception instanceof UsernameNotFoundException) {
			message = "존재하지 않는 아이디입니다.";
			memId = "";
		} else if(exception instanceof BadCredentialsException) {
			message = "비밀번호가 일치하지 않습니다.";
		}
		
//		message = URLEncoder.encode(message, "UTF-8");
//		session.setAttribute("message", message);
//		session.setAttribute("failId", memId);
        final FlashMap flashMap = new FlashMap();
        // Don't send the AuthenticationException object itself because it has no default constructor and cannot be re-instantiated.
        String result = message + "," + memId;
        flashMap.put(AUTHENTICATION_MESSAGE, result);
        final FlashMapManager flashMapManager = new SessionFlashMapManager();
        flashMapManager.saveOutputFlashMap(flashMap, request, response);
		response.sendRedirect(request.getContextPath() + "/outs/login/login.do");
		return;
		
	}

}
