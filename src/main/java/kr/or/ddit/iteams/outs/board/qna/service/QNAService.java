package kr.or.ddit.iteams.outs.board.qna.service;

import java.lang.reflect.InvocationTargetException;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;

public interface QNAService {
	
	public void selectQnAList(MasterVO board);
	
	/**
	 * 글 조회 시 권한 체크 용도
	 * @param member
	 * @return
	 */
	public ServiceResult selectCheckAuth (MasterVO member);
	
	public void selectQnA (MasterVO board) throws IllegalAccessException, InvocationTargetException;
	
	public void insertQuestion (MasterVO board) throws Exception;
	
	public void insertAnswer (MasterVO board) throws Exception;
	
	/**
	 * 질문 수정 or 삭제 시 답변 있는지 체크하는 용도
	 * @param board : boParent
	 * @return HAVECHILD:불가능, OK:가능
	 */
	public void selectCheckAnswer (MasterVO board);
	
	/**
	 * 질문 수정
	 * result OK:성공, FAILED:실패
	 * @param board
	 * @throws Exception
	 */
	public void updateQuestion (MasterVO board) throws Exception;
	
	public void updateAnswer (MasterVO board) throws Exception;

}
