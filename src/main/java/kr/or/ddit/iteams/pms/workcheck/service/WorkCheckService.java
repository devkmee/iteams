package kr.or.ddit.iteams.pms.workcheck.service;

import java.util.List;

import kr.or.ddit.iteams.common.vo.MasterVO;

public interface WorkCheckService {

	public void insertForWorkIn(MasterVO masterVO);
	
	public void updateForWorkOut(MasterVO masterVO);	
	
	public void selectWorkcheckList(MasterVO masterVO);
	
	public void selectTotalDayoffList(MasterVO masterVO);
	
	public List<MasterVO> selectMonthDayoffList(MasterVO masterVO);
	
	public List<MasterVO> selectMonthLateList(MasterVO masterVO); 

}
