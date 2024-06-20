package kr.or.ddit.iteams.outs.blacklist.service;

import kr.or.ddit.iteams.common.vo.MasterVO;

public interface BlacklistService {
	
	public void selectBlackList(MasterVO master);

	/**
	 * 블랙리스트 등록
	 * @param member
	 */
	public void insertMemToBlack (MasterVO member);
	
	/**
	 * 블랙리스트 해제
	 * @param master
	 */
	public void updateBlackToMem (MasterVO master);
	
	/**
	 * 신고글 목록
	 * @param board
	 */
	public void selectRepBoardList(MasterVO board);
	
	/**
	 * 신고 해제
	 * @param board
	 */
	public void updateClearBoard (MasterVO board);

}
