package kr.or.ddit.iteams.common.vo;

import java.io.Serializable;

import kr.or.ddit.enumpkg.ServiceResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of= {"repNo"})
public class ReplyTotalVO implements Serializable{
	
	private String repNo;
	private String boNum;
	private String repWriter;
	private String repWriteDate;
	private String repModifyDate;
	private String repContent;
	private String repDeletedNy;
	private String repDeleteDate;
	private ServiceResult result;
}
