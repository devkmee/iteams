package kr.or.ddit.iteams.outs.login.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO {
	
	private Object data;
	private String redirectUrl;
	private Boolean isSuccess;
	
}
