package kr.or.ddit.iteams.common.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.groups.black.BlackInsertGroup;
import kr.or.ddit.validate.groups.black.BlackRepUpdateGroup;
import kr.or.ddit.validate.groups.black.BlackUpdateGroup;
import kr.or.ddit.validate.groups.board.BoardDeleteGroup;
import kr.or.ddit.validate.groups.board.BoardUpdateGroup;
import kr.or.ddit.validate.groups.board.QNAUpdateGroup;
import kr.or.ddit.validate.groups.board.infoshare.InfoShareUpdateGroup;
import kr.or.ddit.validate.groups.company.ReviewUpdateGroup;
import kr.or.ddit.validate.groups.company.ReviewnsertGroup;
import kr.or.ddit.validate.groups.hire.HireDeleteGroup;
import kr.or.ddit.validate.groups.hire.HireInsertGroup;
import kr.or.ddit.validate.groups.hire.HirePublicInsertGroup;
import kr.or.ddit.validate.groups.hire.HireScrabGroup;
import kr.or.ddit.validate.groups.hire.HireUpdateGroup;
import kr.or.ddit.validate.groups.login.LoginGroup;
import kr.or.ddit.validate.groups.message.MessageInsertGroup;
import kr.or.ddit.validate.groups.schedule.ScheduleInsertGroup;
import kr.or.ddit.validate.groups.schedule.ScheduleUpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of= {"memId","cliId","adminId"})
public class MemberTotalVO extends BaseVO implements Serializable{
	
	//~여부 에 대한 상태 값, 삭제일 => 여러 테이블에서 중복되서 아에 위쪽으로 빼둠
	private String deletedNy;
	private String deleteDate;
	
	//MEMBER
	@NotBlank(groups= {LoginGroup.class})
	private String memId;
	private String memRole;
	private String memRoleValue;
	@NotBlank(groups= {LoginGroup.class})
	private String memPass;
	private String memTel;
	private String memMail;
	private List<String> memMailList;
	private String joinDate;
	private String quitNy;
	private String quitDate;

	
	public void setMemRole(String memRole) {
		if(memRole == null) return;
		this.memRole = memRole;
		
		switch(memRole) {
			case "CLIENT" :
				this.memRoleValue = "클라이언트";
				break;
			case "DEV" :
				this.memRoleValue = "개발자";
				break;
			default :
				this.memRoleValue = "관리자";
				break;
		}
	}
	
//	public void setMemMailList(List<String>mail) {
//		if (mail == null && mail.isEmpty() ) {
//			return;
//		}
//		this.memMail = mail.toString();
//	}
	

	//DEV_TECH + TECH_CODE
	private String devStack;
	private String techImg;

	//DEV
	@NotBlank(message="개발자아이디 누락",groups= {HirePublicInsertGroup.class, BlackInsertGroup.class
				,HireScrabGroup.class})
	private String devId;
	private String devName;
	private String devBir;
	private String devJob;
	private String devTech;		
	private String[] devTechValue;
	private String devEdu;
	private String devMajor;
	private String devCareer;
	private String devPort;
	private String devImg;
	private String publicNy;
	
	
	//개발자 회원 프로필 이미지용
	private MultipartFile profileImage;
	private AttachTotalVO profileImageVO;
	
	public String getDefaultImage() {
		return "/resources/images/profile/defaultProfileImage.png";
	}
	
	public void setDevTech(String devTech) {
		if(devTech == null) {
			return;
		}else {
			this.devTech = devTech;
			this.devTechValue = StringUtils.split(devTech, ",");
		}
	}
	
	public void setProfileImage(MultipartFile image) {
		if(image == null) return;
		this.profileImage = image;
		this.profileImageVO = new AttachTotalVO(image);	
		this.profileImageVO.setProimageNy("Y");
	}
	
	
	//CLIENT
	@NotBlank(groups= {ReviewnsertGroup.class, ReviewUpdateGroup.class})
	private String cliId;
	private String businessNum;
	private String clientName;
	private String clientAdd;
	private String managerName;
	private String managerRank;
	private String clientCeo;
	private String clientHomepage;
	private Integer clientScale;
	private String clientScaleValue;
	private String clientAnni;
//	private Long clientSales;
	private BigInteger clientSales;
	
	public void setClientScale(Integer clientScale) {
		if(clientScale == null) return;
		this.clientScale = clientScale;
		
		if(clientScale >= 1100) {
			this.clientScaleValue = "대기업";
		}else if(clientScale >= 1000) {
			this.clientScaleValue = "중견기업";
		}else {
			this.clientScaleValue = "중소기업";
		}
	}
	
	public String getClientSalesValue() {
		if(this.clientSales == null) return null;
		
		DecimalFormat df = new DecimalFormat("###,###");
		BigInteger divideSales = this.clientSales.divide(new BigInteger("1000000"));
		String divideStr = divideSales.toString();
		Integer divideInt = Integer.parseInt(divideStr);
		if(divideInt > 0) {
			return df.format(divideInt) + " 백만원";
		} else {
			return df.format(this.clientSales.longValue()) + " 원";
		}
	}
	
	//ADMIN
	private String adminId;
	
	//MILEAGE
	private Integer mileage;
	
	//USE_MILE
	private String useNo;
	private String useDate;
	private Integer useAmount;
	@NotBlank(message="글번호 누락", groups= {BoardUpdateGroup.class, BoardDeleteGroup.class, HireInsertGroup.class
			, HireDeleteGroup.class, HireUpdateGroup.class, QNAUpdateGroup.class, BlackRepUpdateGroup.class
			, InfoShareUpdateGroup.class})
	private String boNum;
	
	//CHAR_MILE
	private String chaNo;
	private String chargeDate;
	private Integer chargeAmount;
	private String chargeMethod;
	private String chargeBank;
	private String bankNum;
	private String chargeCard;
	private String cardNum;
	
	//BLACKLIST
	@NotBlank(groups= {BlackUpdateGroup.class})
	private String blackNo;
	private String regDate;
	private String clearedNy;
	private String clearDate;
	private String clearAdmin;
	@NotBlank(groups= {BlackInsertGroup.class})
	private String blackContent;
	
	//REVIEW
	@NotBlank(groups= {ReviewUpdateGroup.class})
	private String revNo;
	private String memWrite;
	private String writeDate;
	@NotBlank(groups= {ReviewnsertGroup.class, ReviewUpdateGroup.class})
	private String reviewContent;
	@NotNull(groups= {ReviewnsertGroup.class, ReviewUpdateGroup.class})
	private Double reviewScore;
	private String reviewScoreValue;
	private String reviewAuth;
	
	public void setReviewScore(Double reviewScore) {
		if(reviewScore == null) return;
		this.reviewScore = reviewScore;
		
		if(reviewScore >= 5) {
			this.reviewScoreValue = "★★★★★";
		}else if(reviewScore >= 4) {
			this.reviewScoreValue = "★★★★☆";
		}else if(reviewScore >= 3) {
			this.reviewScoreValue = "★★★☆☆";
		}else if(reviewScore >= 2) {
			this.reviewScoreValue = "★★☆☆☆";
		}else if(reviewScore >= 1) {
			this.reviewScoreValue = "★☆☆☆☆";
		}
	}
	
	//PLAN_LIST
	private String planNum;
	private String planWriter;
	
	//PRIVATE_PLAN
	private String createDate;
	@NotBlank(groups= {ScheduleUpdateGroup.class,ScheduleInsertGroup.class})
	private String planContent;
	private String modifyDate;
	
	//MESSAGE
	private String msgNum;
	@NotBlank(groups= {MessageInsertGroup.class}, message="제목은 공백일 수 없습니다.")
	private String msgTitle;
	@NotBlank(groups= {MessageInsertGroup.class}, message="내용은 공백일 수 없습니다.")
	private String msgContent;
	private String sendDate;
	
	//MSG_DET
	private String detNo;
	@NotBlank(groups= {MessageInsertGroup.class}, message="받는사람은 공백일 수 없습니다.")
	private String msgReceive;
	private String receiveNy;
	private String receiveDate;
	private String deleteMem;
	
	//SCRAP_DEV
	private String clientId;
	private String scrapDate;
	
	//SCRAP_PRO
    //위쪽에 다 중복되어있음
	
	//INVITE
	private String inviteNo;
	private String proNo;
	private String inviteDate;
	
	//첨부파일
	private List<AttachTotalVO> attachList;
	
	
	
	
	
	
	
	
}
