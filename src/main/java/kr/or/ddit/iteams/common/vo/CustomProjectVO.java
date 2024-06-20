package kr.or.ddit.iteams.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CustomProjectVO implements Serializable {
	private String proNo;
	private String projectName;
	private String cliId;
	private String createDate;
	private String completeDate;
	private String projectState;
	private String deletedNy;
	private String modifyDate;
	private String deleteDate;
	
	private List<CustomProjectMemVO> devList;
	private int startNum;
}
