package kr.or.ddit.iteams.pms.goal.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.PMSTotalVO;
import kr.or.ddit.iteams.common.vo.PagingVO;

@Repository
public interface GoalDAO {
	
	public List<MasterVO> selectGoalList(MasterVO masterVO);
	public MasterVO selectGoal(MasterVO masterVO);
	public int selectTotalRecord(MasterVO masterVO);
	public int insertGoal(MasterVO masterVO);
	public int updateGoal(MasterVO masterVO);
	public int deleteGoal(MasterVO masterVO);
	
}
