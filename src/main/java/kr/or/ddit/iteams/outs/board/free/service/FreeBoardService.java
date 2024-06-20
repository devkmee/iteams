package kr.or.ddit.iteams.outs.board.free.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.vo.AttatchVO;

public interface FreeBoardService {
	public void createBoard(MasterVO board) throws Exception;
	
	public void retrieveBoardList(MasterVO masterVO);
	
	public MasterVO retrieveBoard(MasterVO masterVO);
	
	public void modifyBoard(MasterVO board) throws Exception;
	
	public ServiceResult removeBoard(MasterVO board) throws Exception;
	
	public AttatchVO download(int attNo);
}
