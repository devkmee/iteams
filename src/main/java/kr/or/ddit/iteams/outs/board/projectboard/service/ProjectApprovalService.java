package kr.or.ddit.iteams.outs.board.projectboard.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;

public interface ProjectApprovalService {

	public List<MasterVO> retrieveProjectList(MasterVO base);
	
	public MasterVO retrieveProject(String number);
	
	public ServiceResult approveProject(MasterVO vo);
	
	public ServiceResult refuseProject(MasterVO vo);
	
}
