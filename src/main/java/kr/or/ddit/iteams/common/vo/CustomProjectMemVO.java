package kr.or.ddit.iteams.common.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomProjectMemVO implements Serializable{
	private String devId;
	private String promemNum;
	private String proNo;
	private String authority;
	private String joinDate;
	private String outDate;
	private String outedNy;
	private int starNum;
	
}
