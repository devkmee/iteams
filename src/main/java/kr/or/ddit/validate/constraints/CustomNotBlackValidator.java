package kr.or.ddit.validate.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class CustomNotBlackValidator implements ConstraintValidator<CustomNotBlack, String>{

	//현재 vo에 걸려있는 어노테이션
	private CustomNotBlack constraintAnnotation;
	
	@Override
	public void initialize(CustomNotBlack constraintAnnotation) {
		this.constraintAnnotation = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		//value == 실제로 검증으로 들어오는 값
		if(StringUtils.isBlank(value)) return true;
		
		return true;
	}

}
