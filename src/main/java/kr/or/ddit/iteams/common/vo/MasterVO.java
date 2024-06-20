package kr.or.ddit.iteams.common.vo;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.board.infoshare.InfoShareUpdateGroup;
import kr.or.ddit.validate.groups.documents.DocumentsDeleteGroup;
import kr.or.ddit.validate.groups.work.WorkDeleteGroup;
import kr.or.ddit.validate.groups.work.WorkSelectGroup;
import kr.or.ddit.validate.groups.work.WorkUpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of= {"boNum","appNo","repNo","recNo","proNo","memId","cliId","adminId"})
@ToString
public class MasterVO extends PMSTotalVO implements Serializable{
	
	private ServiceResult result;
	
	//어떤 작업을 해야하는지 구분하기 위해
	private String crudToken;
	
	private List<PMSTotalVO> projectList;
	
	//단순 파라미터 받기용
	@NotBlank(groups= {WorkSelectGroup.class,WorkDeleteGroup.class,DocumentsDeleteGroup.class})
	private String what;
	
	public void setWhat(String what) {
		if(what == null) return;
		this.what = what;
		//일감 번호 자동 세팅
		setWorkNum(what);
	}
	
	//단순 파라미터 받기용
	private String who;
	
	//부모일감 제목 받기용
	private String workParentValue;
	
	//update와 delete의 접근 권한 확인용
	@NotBlank(groups= {WorkUpdateGroup.class, WorkDeleteGroup.class})
	private String writer;
	//일감
	private String charger;
	//웹하드,문서함
	private String auth;
	
	// 개발자 이름, 클라이언트 담당자 이름이 나눠져있어서 귀찮아서 이걸로 현재 로그인 유저 이름 받으려고 만듦
	private String realName;
	public String getRealName() {
		return this.getDevName() == null ? this.getManagerName() : this.getDevName();
	}
	
	//컨트롤러에서 첨부파일 받는용
	private MultipartFile[] attachFiles;
	
	//첨부파일 MultiPartFile이 셋팅 됨과 동시에 attachList도 세팅
	public void setAttachFiles(MultipartFile[] attachFiles) {
		if(attachFiles == null) return;
		this.attachFiles = attachFiles;
		List<AttachTotalVO> attList = new ArrayList<>(attachFiles.length);
		
		for(int i = 0; i < attachFiles.length; i++) {
			if(attachFiles[i].isEmpty()) continue;
			attList.add(new AttachTotalVO(attachFiles[i]));
		}
		
		this.setAttachList(attList);
	}
	

	private List<String> AppIds;
	
	//파일 다운로드 용
	private AttachTotalVO attachVO;
	private File ftpSavedFile;
	private OutputStream fileDownLoadOutputStream;
	private InputStream fileDownLoadInputStream;
	
	//시큐리티 권한 목록 용
	private List<String> authorityList;
	private String authorityString;
	
	public void setAuthorityList(List<String> authorityList) {
		if(authorityList == null || authorityList.isEmpty()) {
			return;
		}
		
		this.authorityList = authorityList;
		
		if(authorityList.size() > 1) {
			String auth1 = authorityList.get(0);
			String auth2 = authorityList.get(1);
			authorityString = auth1 + "," + auth2;
		} else {
			String auth1 = authorityList.get(0);
			authorityString = auth1;
		}
		
	}
	
	//클라이언트가 여러 프로젝트에 참여중일 때 프로젝트 번호 목록 저장용
	private List<String> proNoList;
	
	private List<String> recList;
	
	//지원자 리스트
	private List<OutsTotalVO> appList;
	
	//결근 리스트 -> 날짜별로 그룹핑
	private List<PMSTotalVO> dayOffList;
	
	private Integer dayoffCount;
	private Integer lateCount;
	private List<MasterVO> monthDayoffList;
	private List<MasterVO> monthLateList;
	private Map<String, String> devWorkMap;
	

	
}
