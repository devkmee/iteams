package kr.or.ddit.iteams.outs.login;

import javax.inject.Inject;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationArgumentResolver implements HandlerMethodArgumentResolver{
	
//	@Inject
//	private SecurityContext context;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Authenticated annotation = parameter.getParameterAnnotation(Authenticated.class);
		Class<? extends MethodParameter> class1 = parameter.getClass();
		return parameter.hasParameterAnnotation(Authenticated.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		ServletWebRequest req = (ServletWebRequest) webRequest;
		MasterVO masterVO = (MasterVO) req.getRequest().getSession().getAttribute("authMember");
//		MasterVO masterVO = (MasterVO) context.getAuthentication().getDetails();
		return masterVO;
	}

}
