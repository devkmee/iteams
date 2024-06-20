package kr.or.ddit.iteams.common.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="attNo")
public class AttachTotalVO implements Serializable{
	
	private MultipartFile attachFile;
	
	
	
	public AttachTotalVO(MultipartFile attachFile) {
		super();
		this.attachFile = attachFile;
		this.attachName = UUID.randomUUID().toString();
		this.attachOrigin = attachFile.getOriginalFilename();
		this.attachMime = attachFile.getContentType();
		this.attachSize = attachFile.getSize();
	}
	
	public void saveTo(File saveFolder) throws IllegalStateException, IOException {
		if(attachFile != null && !attachFile.isEmpty()) {
			attachFile.transferTo(new File(saveFolder, attachName));
			this.attachSavedpath = saveFolder.toString();
		}
	}
	
	//프로필
	private String attNo;
	private String devId;
	private String attachName;
	private String attachOrigin;
	private Long attachSize;
	private String attachExt;
	private String uploadDate;
	private String attModifyDate;
	private String attachSavedpath;
	private String ftpSavedpath;
	//프로필 이미지인지 판단 여부
	private String proimageNy;
	
	//아웃소싱
	private String boNum;
	private String attModifyMember;
	private String attDeletedNy;
	private String attDeleteMember;
	private String attDeleteDate;
	private String attachMime;
	
	//PMS
	//위에서 전체 중복


}
