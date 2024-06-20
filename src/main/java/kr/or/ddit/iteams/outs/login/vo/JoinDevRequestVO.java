package kr.or.ddit.iteams.outs.login.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.validate.constraints.CustomNotBlack;
import kr.or.ddit.validate.constraints.IDChecker;
import kr.or.ddit.validate.constraints.PasswordChecker;
import kr.or.ddit.validate.constraints.TelNumber;
import kr.or.ddit.validate.groups.join.CliJoinInsertGroup;
import kr.or.ddit.validate.groups.join.JoinInsertGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class JoinDevRequestVO {
	
	@IDChecker(groups= {JoinInsertGroup.class,CliJoinInsertGroup.class})
	private String memId;
	@PasswordChecker(groups=JoinInsertGroup.class)
	private String memPass;
	private String memRole;
	private String attNo;
	
	// Dev
	@NotBlank(groups=JoinInsertGroup.class, message="이름은 공백일 수 없습니다.")
	private String devName;
	private MultipartFile profileImage;
	private AttachTotalVO profileImageVO;
	@NotBlank(groups=JoinInsertGroup.class, message="출생년도는 공백일 수 없습니다.")
	private String devBir;
	@NotBlank(groups= {JoinInsertGroup.class,CliJoinInsertGroup.class}, message="이메일 앞부분은 공백일 수 없습니다.")
	private String memMailPrefix;
	@NotBlank(groups= {JoinInsertGroup.class,CliJoinInsertGroup.class}, message="이메일 뒷부분은 공백일 수 없습니다.")
	private String memMailSuffix;
	@TelNumber(groups= {JoinInsertGroup.class,CliJoinInsertGroup.class}, message="전화번호는 9자에서 11자 사이로 숫자만 입력해주세요.")
	private String memTel;
	@NotBlank(groups=JoinInsertGroup.class, message="기술스택은 공백일 수 없습니다")
	private String devTech;
	@NotBlank(groups=JoinInsertGroup.class, message="직무는 공백일 수 없습니다")
	private String devJob;
	private String devMajor;
	@NotBlank(groups=JoinInsertGroup.class, message="경력은 공백일 수 없습니다")
	private String devCareer;
	private String devEdu;
	private String devPort;
	private String devImg;
	private MultipartFile[] attachFiles;
	private List<AttachTotalVO> attachList;
	
	public void setProfileImage(MultipartFile image) {
		if(image == null) return;
		this.profileImage = image;
		this.profileImageVO = new AttachTotalVO(image);	
		this.profileImageVO.setProimageNy("Y");
	}
	
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
	
	// 클라이언트
	@NotBlank(groups=CliJoinInsertGroup.class, message="사업자 등록 번호는 공백일 수 없습니다")
	private String businessNum;
	@NotBlank(groups=CliJoinInsertGroup.class, message="기업명은 공백일 수 없습니다")
    private String clientName;
	@NotBlank(groups=CliJoinInsertGroup.class, message="주소는 공백일 수 없습니다")
	private String clientAdd;
	@NotBlank(groups=CliJoinInsertGroup.class, message="담당자 이름은 공백일 수 없습니다")
    private String managerName;
	@NotBlank(groups=CliJoinInsertGroup.class, message="담당자 직급은 공백일 수 없습니다")
    private String managerRank;
	@NotBlank(groups=CliJoinInsertGroup.class, message="대표명은 공백일 수 없습니다")
    private String clientCeo;
    private String clientHomepage;
    @NotNull(groups=CliJoinInsertGroup.class, message="회사 규모는 공백일 수 없습니다")
    private Integer clientScale;
    @NotBlank(groups=CliJoinInsertGroup.class, message="설립일은 공백일 수 없습니다")
    private String clientAnni; 
    @NotNull(groups=CliJoinInsertGroup.class, message="매출액은 공백일 수 없습니다")
    private Long clientSales;
    @NotBlank(groups=CliJoinInsertGroup.class, message="상세주소는 공백일 수 없습니다")
    private String clientAddret;
    @NotBlank(groups=CliJoinInsertGroup.class, message="우편번호는 공백일 수 없습니다")
    private String clientZip;
    private String clientAddx;
    private String clientAddy;
	
	public void setDevTech(String devTech) {
		if(!StringUtils.isEmpty(devTech)) {
			String lastWord = devTech.substring(devTech.length()-1);
			if(StringUtils.equals(",", lastWord)) {
				// 맨뒤에 콤마가 붙여서 들어오기 때문에 삭제해준다.
				devTech = devTech.substring(0, devTech.length()-1);
			}
		}
		this.devTech = devTech;
	}
	
}
