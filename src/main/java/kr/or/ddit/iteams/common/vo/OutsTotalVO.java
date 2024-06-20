package kr.or.ddit.iteams.common.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Clob;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import kr.or.ddit.validate.groups.board.AnswerUpdateGroup;
import kr.or.ddit.validate.groups.board.BoardDeleteGroup;
import kr.or.ddit.validate.groups.board.BoardInsertGroup;
import kr.or.ddit.validate.groups.board.BoardUpdateGroup;
import kr.or.ddit.validate.groups.board.QNAInsertGroup;
import kr.or.ddit.validate.groups.board.QNAUpdateGroup;
import kr.or.ddit.validate.groups.board.free.FreeBoardInsertGroup;
import kr.or.ddit.validate.groups.board.free.FreeBoardUpdateGroup;
import kr.or.ddit.validate.groups.board.infoshare.InfoShareInsertGroup;
import kr.or.ddit.validate.groups.board.infoshare.InfoShareUpdateGroup;
import kr.or.ddit.validate.groups.hire.AppReturnGroup;
import kr.or.ddit.validate.groups.hire.HireDeleteGroup;
import kr.or.ddit.validate.groups.hire.HireInsertGroup;
import kr.or.ddit.validate.groups.hire.HirePublicRefuseGroup;
import kr.or.ddit.validate.groups.hire.HirePublicUpdateGroup;
import kr.or.ddit.validate.groups.hire.HireUpdateGroup;
import kr.or.ddit.validate.groups.outsproject.OutsProjectInsertGroup;
import kr.or.ddit.validate.groups.outsproject.OutsProjectUpdateGroup;
import kr.or.ddit.validate.groups.outsproject.RefuseGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import oracle.sql.CLOB;

@Data
@EqualsAndHashCode(of= {"boNum","appNo","repNo","recNo"})
public class OutsTotalVO extends MemberTotalVO implements Serializable{
	
	//작성일, 수정일, 삭제일, 상태 여부 등 많은 곳에서 중복되는 부분은 위로 빼둠
	private String writeDate;
	private String modifyDate;
	private String deletedNy;
	private String deleteDate;

	// 회원 랭킹
	private Integer completeCount; 
	private Integer activeCount; 
	private double reviewCount;
	
	//GEN_BOARD 
	private String boCode;
	@NotBlank(message="제목은 공백일 수 없습니다.", groups= {OutsProjectInsertGroup.class,InfoShareUpdateGroup.class,InfoShareInsertGroup.class,FreeBoardUpdateGroup.class,BoardInsertGroup.class, BoardUpdateGroup.class,FreeBoardInsertGroup.class})
	private String boTitle;
	@NotBlank(message="내용은 공백일 수 없습니다.", groups= {OutsProjectInsertGroup.class,InfoShareUpdateGroup.class,InfoShareInsertGroup.class,FreeBoardUpdateGroup.class,BoardInsertGroup.class, BoardUpdateGroup.class, QNAInsertGroup.class
				, QNAUpdateGroup.class, AnswerUpdateGroup.class, FreeBoardInsertGroup.class})
	private String boContent;
//	private CLOB boContent;
	private String modifyMember;
	private String deleteMember;
	private Integer boHit;
	private Integer boRec;
	private Integer boRep;
	private String publicNy;
	private String boCodeValue;
	
	public void setBoCode(String boCode) {
		if(boCode == null) return;
		this.boCode = boCode;
		switch(boCode) {
			case "GF" :
				this.boCodeValue = "일상이야기";
				break;
			case "GI" :
				this.boCodeValue = "개발자인터뷰";
				break;
			case "GC" :
				this.boCodeValue = "코드북";
				break;
			case "GN" :
				this.boCodeValue = "뉴스피드";
				break;
			case "GP" :
				this.boCodeValue = "프로젝트공고";
				break;
			case "GQ" :
				this.boCodeValue = "문의사항";
				break;
			default :
				this.boCodeValue = "기타";
				break;				
		}
	}
	
	//REPLY
	private String repNo;
	private String repWriter;
	private String repContent;
	private String repWriteDate;
	private String repModifyDate;
	private String repDeletedNy;
	private String repDeleteDate;
	
	//댓글 has Many 관계
	private List<ReplyTotalVO> replyList;
	
	//PRO_BOARD
	@NotBlank(message = "프로젝트명은 공백일 수 없습니다.",groups= {OutsProjectUpdateGroup.class,OutsProjectInsertGroup.class})
	private String projectName;
	@NotBlank(message = "프로젝트 규모는 공백일 수 없습니다.",groups= {OutsProjectUpdateGroup.class,OutsProjectInsertGroup.class})
	private String projectScale;
	@Pattern(regexp="^[0-9]+$", message = "예상단가는 숫자만 가능합니다.", groups= {OutsProjectUpdateGroup.class,OutsProjectInsertGroup.class})
	private String projectPrice;

	
	public String getProjectPriceValue() {
		if(StringUtils.isBlank(this.projectPrice)) return null;
		BigInteger intValue = new BigInteger(this.projectPrice);
		DecimalFormat df = new DecimalFormat("###,###");
		BigInteger divideSales = intValue.divide(new BigInteger("1000000"));
		
		String divideStr = divideSales.toString();
		Integer divideInt = Integer.parseInt(divideStr);
		if(divideInt > 0) {
			return df.format(divideInt) + " 백만원";
		} else {
			return df.format(intValue) + " 원";
		}
	}
	private String projectReq;
	@NotBlank(message = "요구 기술스택은 공백일 수 없습니다.",groups= {OutsProjectUpdateGroup.class,OutsProjectInsertGroup.class})
	private String projectTech;
	private String[] projectTechValue;
	@NotBlank(message = "요구 직무는 공백일 수 없습니다.",groups= {OutsProjectUpdateGroup.class,OutsProjectInsertGroup.class})
	private String projectJob;
	private String permissionDate;
	private String permissionNy;
	private String permissionAdmin;
	@NotBlank(message = "공고 기한은 공백일 수 없습니다.",groups= {OutsProjectUpdateGroup.class,OutsProjectInsertGroup.class})
	private String limitDate;
	private Integer limitCount;
	private String limitModifyDate;
	private String limitModifyAdmin;
	private String completedNy;
	@NotBlank(message = "모집인원은 공백일 수 없습니다.",groups= {OutsProjectUpdateGroup.class,OutsProjectInsertGroup.class})
	private String projectRecruit; //모집인원
	@NotBlank(message = "상주여부는 공백일 수 없습니다.",groups= {OutsProjectUpdateGroup.class,OutsProjectInsertGroup.class})
	private String officeNy;	   //상주여부
	//면접실 id 11/30일 추가
	private String meetingRoom;
	@NotBlank(groups= {RefuseGroup.class} , message="반려 사유는 공백일 수 없습니다.")
	private String projectRefuse;
	
	public void setProjectTech(String projectTech) {
		if(projectTech == null) {
			return;
		}else {
			this.projectTech = projectTech;
			this.projectTechValue = StringUtils.split(projectTech, ",");
		}
	}
	
	
	//PRO_APP
	private String appNo;
	@NotBlank(message="지원자아이디 누락", groups= {HireUpdateGroup.class})
	private String appId; //지원자 아이디(dev)
	private String appDate;
	private String hiredNy;
	private String hiredValue;
	private String hireDate;
	private String function;
	private String meetingDate;
	private String meetingNy;
	@NotBlank(groups= {AppReturnGroup.class}, message="반려 사유는 공백일 수 없습니다.")
	private String appReturn;
	private String appNy;
//	private String meetingBtn;
	
	public String getMeetingBtn() {
		
		if(this.meetingDate == null) return null;
		String btn = null;
		Date curDate = new Date();
		String curDateStr = null;
		Date meetingDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		curDateStr = sdf.format(curDate);
		try {
			curDate = sdf.parse(curDateStr);
			meetingDate = sdf.parse(this.meetingDate);
		} catch (ParseException e) {
			return null;
		}
		int compare = curDate.compareTo(meetingDate);
		if(compare > 0) {
			btn = "<button data-room='"+this.meetingRoom+"' class='meetingJoinBtn btn btn-gray'>면접 참여</button>";
		} else {
			btn = "<button class='btn btn-gray' disabled title='참여시간이 아닙니다 / 참여가능시각 : "+this.meetingDate+"'>면접 참여</button>";
		}
		
		return btn == null ? "" : btn;
	}
	
	public void setHiredNy(String hiredNy) {
		if(hiredNy == null) return;
		this.hiredNy = hiredNy;
		
		if(hiredNy.equals("0")) {
			this.hiredValue = "대기중";
		}else if(hiredNy.equals("1")) {
			this.hiredValue = "채용";
		}else if(hiredNy.equals("2")) {
			this.hiredValue = "반려";
		}
	}
	
	//INVITE
	@NotBlank(message="초대번호 누락", groups= {HirePublicUpdateGroup.class,HirePublicRefuseGroup.class})
	private String inviteNo;
	private String inviteDate;
	private String inviteState;
	private String inviteStateValue;
	@NotBlank(message="초대권한 누락", groups=HirePublicUpdateGroup.class)
	private String inviteAuth;
	private BigInteger invitePrice;
	
	public void setInviteState(String inviteState) {
		if(inviteState == null) return;
		this.inviteState = inviteState;
		if(inviteState.equals("0")) {
			this.inviteStateValue = "미답변";
		}else if(inviteState.equals("1")) {
			this.inviteStateValue = "수락";
		}else if(inviteState.equals("2")) {
			this.inviteStateValue = "거절";
		}
	}
	
	public String getInvitePriceValue() {
		if(this.invitePrice == null) return null;
		
		DecimalFormat df = new DecimalFormat("###,###");
		return df.format(this.invitePrice.longValue()) + " 원";
	}
	
	//RECOMM
	private String recNo;
	private String recDate;
	
	//BOARD_REP
	private String repDate;
	
	//QNA_BOARD
	@NotBlank(message="질문 글번호 누락", groups= {QNAInsertGroup.class})
	private String boParent;
	private String qnaKeyword;
	private String qnaKeywordValue;
	private String qnaDate;
	private String qnaAdmin;
	private String qnaMem;
	
	public void setQnaKeyword(String qnaKeyword) {
		if(qnaKeyword == null) return;
		this.qnaKeyword = qnaKeyword;
		if(qnaKeyword.equals("1")) {
			this.qnaKeywordValue = "계정 정보";
		}else if(qnaKeyword.equals("2")) {
			this.qnaKeywordValue = "프로젝트 관리";
		}else if(qnaKeyword.equals("3")) {
			this.qnaKeywordValue = "프로젝트 검색/지원";
		}else if(qnaKeyword.equals("4")) {
			this.qnaKeywordValue = "개발자 검색/초대";
		}else if(qnaKeyword.equals("5")) {
			this.qnaKeywordValue = "PMS";
		}
	}
	
	//첨부파일
	//MemberTotalVO에서 상속받음
	
	private List<String> projectJobList;
	private List<String> projectScaleList;
	private List<String> officeNyList;
	private List<String> projectTechList;
	private List<MasterVO> profileProjectList;
	private Integer appCount;
}
