package kr.or.ddit.iteams.pms.project.service;


import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.CustomProjectVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.PMSTotalVO;
import kr.or.ddit.iteams.common.vo.PagingVO;


public interface ProjectService {
	
	public List<MasterVO> retrieveProjectList(MasterVO masterVO);
	public List<MasterVO> retrieveProject(MasterVO masterVO);
	public ServiceResult createProject(MasterVO masterVO) throws Exception;
	public ServiceResult modifyProject(MasterVO masterVO);
	public ServiceResult removeProject(MasterVO masterVO);
	public List<MasterVO> retrieveProMemList(MasterVO masterVO);
	public List<MasterVO> retrieveProingMemList(MasterVO masterVO);
	public List<MasterVO> retrieveProBoardList(MasterVO masterVO);
	
}
