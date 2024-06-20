package kr.or.ddit.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import kr.or.ddit.iteams.outs.login.AuthenticationArgumentResolver;

//@Configuration
//@EnableWebMvc
public class WebConfig extends WebMvcConfigurationSupport{
	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		super.addArgumentResolvers(argumentResolvers);
		argumentResolvers.add(new AuthenticationArgumentResolver());
	}
}
