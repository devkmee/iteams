package kr.or.ddit.iteams.pms.team.service;

import java.util.List;
import java.util.Map;


import kr.or.ddit.iteams.common.vo.MasterVO;

public interface TeamRetrieveService {
	//프로젝트중인 모든멤버리스트
	public List<MasterVO> memberList(MasterVO master);
	//프로젝트멤버 상세보기
	public MasterVO memberDetail(String number);
		
	//프로젝트메인화면->멤버리스트
	public List<MasterVO> projectMemberList(MasterVO master);
	//프로젝트메인화면->정보(work_state별 count)
	public Map<String,Object> projectInfo(MasterVO master);
	
	
}
