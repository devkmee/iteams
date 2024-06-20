package kr.or.ddit.iteams.pms.goal.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.logging.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.PMSTotalVO;
import kr.or.ddit.iteams.common.vo.PagingVO;
import kr.or.ddit.iteams.pms.goal.dao.GoalDAO;
import kr.or.ddit.iteams.pms.project.dao.OthersDAO;
import kr.or.ddit.iteams.pms.project.dao.ProjectDAO;
import kr.or.ddit.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GoalServiceImpl implements GoalService {
	
	@Inject
	private GoalDAO dao;	

	
	@Transactional
	@Override
	public ServiceResult createGoal(MasterVO masterVO) {
		//목표
		int rowcnt = dao.insertGoal(masterVO);
		
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
	}
	
	@Transactional
	@Override
	public ServiceResult modifyGoal(MasterVO masterVO) {
		//목표수정
		int rowcnt = dao.updateGoal(masterVO);
		
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
	}
	@Transactional
	@Override
	public ServiceResult removeGoal(MasterVO masterVO) {
		int rowcnt = dao.deleteGoal(masterVO);
		
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
	}
	@Transactional
	@Override
	public List<MasterVO> retrieveGoalList(MasterVO masterVO) {
		List<MasterVO> projectList = dao.selectGoalList(masterVO);
		
		masterVO.setDataList(projectList);
		
		return projectList;
	}
	

	
	@Transactional
	@Override
	public MasterVO retrieveGoal(MasterVO masterVO) {
		PMSTotalVO pms = dao.selectGoal(masterVO);
		if(pms==null) 
			throw new PKNotFoundException(masterVO +"번 글이 없음.");
		
		return masterVO;
	}

	


}
