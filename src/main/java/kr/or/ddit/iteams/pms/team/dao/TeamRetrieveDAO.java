package kr.or.ddit.iteams.pms.team.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface TeamRetrieveDAO {
	
	//프로젝트중인 모든멤버리스트
	public List<MasterVO> memberList(MasterVO master);
	//프로젝트멤버 상세보기
	public MasterVO memberDetail(String number);
	
	//프로젝트메인화면->멤버리스트
	public List<MasterVO> projectMemberList(MasterVO master);
	//프로젝트메인화면->정보(work_state별 count)
	public MasterVO selectProjectInfo(MasterVO master);
	
	public Map<String, Object> projectInfo(MasterVO master);
	//-> Map<key값받을것타임, value값받을것타입> projectname(어디vo에서받을지  변수명아무)
	//public List<MasterVO> projectCount(String proNo);
	
	public Map<String, String> selectDevWorkList(MasterVO masterVO);
	
}
