package kr.or.ddit.iteams.pms.workcheck.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface WorkCheckDAO {
	
	public int checkWorkIn(MasterVO masterVO);
	
	public int insertForWorkIn(MasterVO masterVO);
	
	public int updateForWorkOut(MasterVO masterVO);
	
	public MasterVO selectForWorkIn(MasterVO masterVO);
	
	public MasterVO selectForWorkOut(MasterVO masterVO);
	
	public List<MasterVO> selectWorkcheckList(MasterVO masterVO);
	
	public List<MasterVO> selectTodayDayoffList();
	
	public int insertDayoffList(MasterVO masterVO);
	
	public List<MasterVO> selectTotalDayoffList(MasterVO masterVO);
	
	public int selectDayoffTotalRecords(MasterVO masterVO);
	
	public List<MasterVO> selectMonthDayoffList(MasterVO masterVO);
	
	public List<MasterVO> selectMonthLateList(MasterVO masterVO);
	
	public Map<String, Integer> selectWorkPie(MasterVO masterVO);
	

}
