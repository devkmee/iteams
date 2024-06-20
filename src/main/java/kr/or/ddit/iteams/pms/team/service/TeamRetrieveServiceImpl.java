package kr.or.ddit.iteams.pms.team.service;

import java.util.List;

import java.util.Map;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.team.dao.TeamRetrieveDAO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TeamRetrieveServiceImpl implements TeamRetrieveService  {

	@Inject
	private TeamRetrieveDAO dao;

	//프로젝트중인 모든멤버리스트
	@Override
	public List<MasterVO> memberList(MasterVO master) {
		return dao.memberList(master);
	}

	//프로젝트멤버 상세보기
	@Override
	public MasterVO memberDetail(String number) {
		return dao.memberDetail(number);
	}

	//프로젝트메인화면->멤버리스트
	@Override
	public List<MasterVO> projectMemberList(MasterVO master) {
		return dao.projectMemberList(master);
	}

	//프로젝트메인화면->정보(work_state별 count)
	@Override
	public Map<String, Object> projectInfo(MasterVO master) {
		return null;
	}
	
}
