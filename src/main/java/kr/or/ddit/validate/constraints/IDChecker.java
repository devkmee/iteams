package kr.or.ddit.validate.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.RetentionPolicy.*;

@Target({FIELD, METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy= {IDCheckerValidator.class})
public @interface IDChecker {
	String regex() default "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";
	
	String message() default "{kr.or.ddit.validate.constraints.IDChecker.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
