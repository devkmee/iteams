package kr.or.ddit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface Authenticated {
	
}
