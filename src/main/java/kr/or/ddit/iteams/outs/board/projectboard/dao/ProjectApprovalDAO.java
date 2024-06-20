package kr.or.ddit.iteams.outs.board.projectboard.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.BaseVO;
import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface ProjectApprovalDAO {
	
	public int selectTotalRecord(BaseVO base);
	
	public List<MasterVO> selectProjectList(MasterVO base);
	   
	public MasterVO selectProject(String number);
	
	public int approveProject(MasterVO vo); 
	
	public int approveProjctUpdateGenboard(MasterVO vo);
	
	public int refuseProject(MasterVO vo); 
	
}
