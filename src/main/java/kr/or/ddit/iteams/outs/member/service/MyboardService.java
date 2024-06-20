package kr.or.ddit.iteams.outs.member.service;

import kr.or.ddit.iteams.common.vo.MasterVO;

public interface MyboardService {
	
	/**
	 * 나의 게시글 목록
	 * @param board
	 */
	public void selectMyBoardList(MasterVO board);

}
