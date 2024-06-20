package kr.or.ddit.validate.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class PasswordValidator implements ConstraintValidator<PasswordChecker, String>{

	private PasswordChecker constraintAnnotation;
	
	@Override
	public void initialize(PasswordChecker constraintAnnotation) {
		this.constraintAnnotation = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(StringUtils.isBlank(value)) return true;
		
		return value.matches(constraintAnnotation.regex());
	}

}
