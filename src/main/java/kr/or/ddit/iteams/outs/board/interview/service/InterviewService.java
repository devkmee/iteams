package kr.or.ddit.iteams.outs.board.interview.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

public interface InterviewService {
	public ServiceResult createBoard(BoardVO board);
	
	public void retrieveBoardList(MasterVO masterVO);
	
	public BoardVO retrieveBoard(int boNum);
	
	public ServiceResult modifyBoard(BoardVO board);
	
	public ServiceResult removeBoard(BoardVO board);
	
	public AttatchVO download(int attNo);
	
}
