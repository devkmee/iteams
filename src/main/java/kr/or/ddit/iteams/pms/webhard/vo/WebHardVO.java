package kr.or.ddit.iteams.pms.webhard.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class WebHardVO implements Serializable{
	
	private String attachMime;
	private String ftpSavedpath;
	private String attNo;
	private String attachName;
	private String attachOrigin;
	private Integer attachSize;
	private String uploadDate;
	private String attModifyMember;
	private String attModifyDate;
	private String attDeletedNy;
	private String attDeleteMember;
	private String attDeleteDate;

}
