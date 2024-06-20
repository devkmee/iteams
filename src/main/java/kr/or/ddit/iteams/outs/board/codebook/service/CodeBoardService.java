package kr.or.ddit.iteams.outs.board.codebook.service;

import java.util.List;

import kr.or.ddit.iteams.common.vo.MasterVO;

public interface CodeBoardService {

	//게시글 전체목록 조회
	public List<MasterVO> CodeBoardList(MasterVO master);
	//게시글 상세보기 
	public MasterVO retrieveCode(int number);
	
	
}
