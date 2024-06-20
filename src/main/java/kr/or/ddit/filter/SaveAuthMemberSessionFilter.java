package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.vo.MasterVOWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SaveAuthMemberSessionFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			Object principal = authentication.getPrincipal();
			if(principal != null) {
				if(principal instanceof MasterVOWrapper) {
					MasterVO saved = (MasterVO) session.getAttribute("authMember");
					if(saved == null) {
						MasterVO authMember = ((MasterVOWrapper) principal).getAuthMember();
						log.info("인증 객체 : {}",  authMember);
						session.setAttribute("authMember", authMember);
						filterChain.doFilter(request, response);
					} else {
						filterChain.doFilter(request, response);				
					}
				} else {
					filterChain.doFilter(request, response);				
				}
			} 
		} 
	}

}
