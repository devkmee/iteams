package kr.or.ddit.iteams.common.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.validate.groups.hire.HireDeleteGroup;
import kr.or.ddit.validate.groups.hire.HireInsertGroup;
import kr.or.ddit.validate.groups.hire.HirePublicInsertGroup;
import kr.or.ddit.validate.groups.hire.HirePublicUpdateGroup;
import kr.or.ddit.validate.groups.hire.HireUpdateGroup;
import kr.or.ddit.validate.groups.schedule.ScheduleInsertGroup;
import kr.or.ddit.validate.groups.schedule.ScheduleUpdateGroup;
import kr.or.ddit.validate.groups.work.WorkDeleteGroup;
import kr.or.ddit.validate.groups.work.WorkInsertGroup;
import kr.or.ddit.validate.groups.work.WorkUpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of= {"proNo","boNum"})
public class PMSTotalVO extends OutsTotalVO implements Serializable{
	
	//프로젝트 번호, 수정일, 삭제일, 삭제자, 여부 등 여러곳에서 중복되는 값은 위로 빼둠
	@NotBlank(message="프로젝트번호 누락", groups= {HirePublicUpdateGroup.class,WorkInsertGroup.class, HirePublicInsertGroup.class})
	private String proNo;
	private String deletedNy;
	private String modifyDate;
	private String deleteDate;
	
	//TBL_PRO	
	//OutsTotalVO에 이미 있음 주석 풀지 말것
//	private String projectName;
	private String createDate;
	private String completeDate;
	private String projectState;

	
	//PROJECT_PLAN
	private String planCreate;
	private String executionMember;
	private String deleteMember;
	
	//GOAL
	private String goalId;
	private String goalWriter;
	private String goalName;
	private String writeDate;
	private String completedNy;
	private String goalParent;
	
	//PROJECT_MEM
	private String promemNum;
	@NotBlank(message="권한 누락", groups=HirePublicInsertGroup.class)
	private String authority;
	private String joinDate;
	private String outDate;
	private String outedNy;
	private List<CustomProjectMemVO> devList;
	private int startNum;

	public String getOutedNyValue() {
		if(StringUtils.isBlank(this.outedNy) || "N".equals(this.outedNy)) {
			return "참여중";
		} else {
			return "탈퇴";
		}				
	}
	
	//WEBHARD
	private String hardNum;
	
	//WHARD_DETAIL
	private String attNo;
	private String folderNy;
	private String webhardParent;
	private String editRange;
	private String publicRange;
	
	//PMS_BOARD
	//private Integer rnum;	// 게시물 번호 => BaseVO에 있음
	private String boWriter; //작성자
	
	//PMS_REPLY
	//outs에 포함
	
	//PBOARD_ATT
	//위에서 전부 중복됨
	
	//DOCUMENT
	private String docNum;
	private String docWriter;
	
	//DOC_DETAIL
	private String attachNo;
	private String docTitle;
//	private List<List<String>> docData;
	
	
	//TBL_WORK
	@NotBlank(groups={WorkUpdateGroup.class})
	private String workNum;
	private String workWriter;
	private String writerName;
	@NotBlank(groups= {WorkInsertGroup.class}, message="담당자를 선택하세요.")
	private String workCharger;
	private String chargerName;
	@NotBlank(groups={WorkInsertGroup.class, WorkUpdateGroup.class}, message="제목은 공백일 수 없습니다.")
	private String workTitle;
	private String workContent;
	@NotBlank(groups= {ScheduleUpdateGroup.class,ScheduleInsertGroup.class,WorkInsertGroup.class, WorkUpdateGroup.class}, message="시작일을 선택하세요.")
	private String startDate;
	@NotBlank(groups= {ScheduleUpdateGroup.class,ScheduleInsertGroup.class,WorkInsertGroup.class, WorkUpdateGroup.class}, message="마감기한을 선택하세요.")
	private String endDate;
	@NotBlank(groups= {WorkInsertGroup.class, WorkUpdateGroup.class}, message="우선순위를 선택하세요.")
	private String workPriority;
	@NotBlank(groups= {WorkInsertGroup.class, WorkUpdateGroup.class}, message="유형을 선택하세요.")
	private String workType;
	private String workProgress;
	private String workParent;
	@NotBlank(groups= {WorkInsertGroup.class, WorkUpdateGroup.class}, message="상태를 선택하세요.")
	private String workState;
	private String workPriorityValue;
	private String workTypeValue;
	private String workStateValue;
	private String workProgressValue;
	private String parentTitle;
	
	public void setWorkType(String workType) {
		if(workType == null) return;
		this.workType = workType;
		if(workType.equals("1")) {
			this.workTypeValue = "새기능";
		} else if(workType.equals("2")) {
			this.workTypeValue = "결함";
		}else if(workType.equals("3")) {
			this.workTypeValue = "지원";
		}else if(workType.equals("4")) {
			this.workTypeValue = "공지";
		}
	}
	
	public void setWorkPriority(String workPriority) {
		if(workPriority == null) return;
		this.workPriority = workPriority;
		if(workPriority.equals("1")) {
			this.workPriorityValue = "낮음";
		} else if(workPriority.equals("2")) {
			this.workPriorityValue = "보통";
		}else if(workPriority.equals("3")) {
			this.workPriorityValue = "높음";
		}else if(workPriority.equals("4")) {
			this.workPriorityValue = "긴급";
		}else if(workPriority.equals("5")) {
			this.workPriorityValue = "즉시";
		}
	}
	
	
	public void setWorkState(String workState) {
		if(workState == null) return;
		this.workState = workState;
		if(workState.equals("1")) {
			this.workStateValue = "신규";
		} else if(workState.equals("2")) {
			this.workStateValue = "진행";
		}else if(workState.equals("3")) {
			this.workStateValue = "해결";
		}else if(workState.equals("4")) {
			this.workStateValue = "의견";
		}else if(workState.equals("5")) {
			this.workStateValue = "완료";
		}
	}
	
	public void setWorkProgress(String workProgress) {
		if(workProgress == null) return;
		this.workProgress = workProgress;
		this.workProgressValue = workProgress + "%";
	}

	
	//WORKCHECK
	private String solarDate;
	private String inDate;
	private String checkNum;
	
	
	//DAYOFF
	private String dayoffNum;
	
	
	//CALENDAR
	private String datevalue;
	private String lunarDate;
	private Integer year;
	private Integer month;
	private Integer day;
	private String dayofweek;
	private String yun;
	private String ganji;
	private String holiday;
	private String note;
	
	//TIMELINE
	private String timeNum;
	private String timelineDate;
	private String timelineContent;
	private String timelineUrl;
	private String timelineName;
	
	//첨부파일
	//OutsTotalVO 에서 상속받음
	//프로퍼티명 : attachList => 엘리먼트 AttachTotalVO 사용할것
	
	
}
