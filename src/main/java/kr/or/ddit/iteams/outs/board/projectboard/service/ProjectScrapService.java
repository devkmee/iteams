package kr.or.ddit.iteams.outs.board.projectboard.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.BaseVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.OutsTotalVO;

public interface ProjectScrapService {

	public ServiceResult scrap(MasterVO vo);
	
	public List<OutsTotalVO> retrieveProjectList(MasterVO base);
	
	public MasterVO retrieveProject(String number);
	
	public ServiceResult unscrap(MasterVO vo);
}
