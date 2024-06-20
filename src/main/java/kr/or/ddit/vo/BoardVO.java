package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="boNum")
@ToString(exclude= {"replyList", "attatchList"})
public class BoardVO implements Serializable{
	int rnum;

	@NotNull(groups=UpdateGroup.class)
	private Integer boNum;
	private String boCode;
	@NotBlank
	private String memId;
	@NotBlank
	private String boTitle;
	@NotBlank
	private String boContent;
	private String writeDate;
	private String modifyMember;
	private String modifyDate;
	private String isDeleted;
	private String deleteMember;
	private String deleteDate;
	private Integer boHit;
	private Integer boRec;
	private Integer boRep;
	private String isPublic;
	private List<ReplyVO> replyList;
	
	private MultipartFile[] boFiles;
	public void setBoFiles(MultipartFile[] boFiles) {
		if(boFiles==null) return;
		this.boFiles = boFiles;
		this.attatchList = new ArrayList<>();
		for(MultipartFile tmp : boFiles) {
			if(tmp.isEmpty()) continue;
			attatchList.add(new AttatchVO(tmp));
		}
	}
	
	private List<AttatchVO> attatchList;
	
	private int startAttNo;

	private int[] delAttNos;
	
	private int repCnt;
	
	private int atchCnt;
	
	private String devName;
	
}
