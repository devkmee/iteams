package kr.or.ddit.iteams.outs.board.projectboard.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;

public interface OutsProjectService {

	public ServiceResult createProject(MasterVO vo) throws Exception;
	public void retrieveProjectList(MasterVO base);
	public MasterVO selectProjectApp(MasterVO masterVO);
	public MasterVO retrieveProject(String number);
	public ServiceResult modifyProject(MasterVO vo) throws Exception;
	public ServiceResult removeProject(MasterVO vo);
	public MasterVO selectProject(MasterVO master);
	public ServiceResult deadline(String boNum);
	
}
