package kr.or.ddit.iteams.pms.goal.service;


import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.PMSTotalVO;
import kr.or.ddit.iteams.common.vo.PagingVO;


public interface GoalService {
	
	public List<MasterVO> retrieveGoalList(MasterVO masterVO);
	public MasterVO retrieveGoal(MasterVO masterVO);
	public ServiceResult createGoal(MasterVO masterVO);
	public ServiceResult modifyGoal(MasterVO masterVO);
	public ServiceResult removeGoal(MasterVO masterVO);
	
}
