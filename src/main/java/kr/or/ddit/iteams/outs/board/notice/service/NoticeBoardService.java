package kr.or.ddit.iteams.outs.board.notice.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import kr.or.ddit.iteams.common.vo.MasterVO;

public interface NoticeBoardService {
	
	/**
	 * 공지사항 게시글 전체 목록 조회
	 * @param board
	 */
	public void selectBoardList(MasterVO board);
	
	/**
	 * 공지사항 게시글 상세 조회
	 * @param board
	 */
	public void selectBoard(MasterVO board) throws IllegalAccessException, InvocationTargetException;
	
	/**
	 * 공지사항 게시글 작성
	 * OK:성공, FALIE:실패
	 * @param board
	 */
	public void insertBoard(MasterVO board)  throws Exception;
	
	/**
	 * 공지사항 게시글 수정
	 * OK:성공, FALIE:실패, PKNotFound:수정할 게시글 없음
	 * @param board
	 */
	public void updateBoard(MasterVO board)  throws Exception ;
	
	/**
	 * 공지사항 게시글 삭제
	 * OK:성공, FALIE:실패, PKNotFound:삭제할 게시글 없음
	 * @param board
	 */
	public void deleteBoard(MasterVO board) throws Exception ;

}
