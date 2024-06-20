package kr.or.ddit.iteams.outs.board.qna.service;

import java.lang.reflect.InvocationTargetException;

import kr.or.ddit.iteams.common.vo.MasterVO;

public interface FAQService {
	
	
	public void selectFAQList(MasterVO board);
	
	public void selectFAQ (MasterVO board) throws IllegalAccessException, InvocationTargetException;
	
	public void insertFAQ (MasterVO board) throws Exception;
	
	public void updateFAQ (MasterVO board) throws Exception;
	
	public void deleteFAQ (MasterVO board);

}
