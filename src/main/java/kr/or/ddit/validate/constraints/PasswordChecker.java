package kr.or.ddit.validate.constraints;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;

@Target({FIELD, METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy= {PasswordValidator.class})
public @interface PasswordChecker {
	String regex() default "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$";
	
	String message() default "{kr.or.ddit.validate.constraints.PasswordChecker.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
