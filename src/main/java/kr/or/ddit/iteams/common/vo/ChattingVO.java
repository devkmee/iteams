package kr.or.ddit.iteams.common.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class ChattingVO implements Serializable{
	
	private String timeStamp;
	private String memId;
	private String message;

}
