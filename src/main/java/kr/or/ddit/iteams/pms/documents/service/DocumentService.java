package kr.or.ddit.iteams.pms.documents.service;

import java.lang.reflect.InvocationTargetException;

import kr.or.ddit.iteams.common.vo.MasterVO;

public interface DocumentService {
	
	public void selectDocuments(MasterVO masterVO) throws IllegalAccessException, InvocationTargetException;
	
	public void updateDocuments(MasterVO masterVO) throws Exception;
	
	public void selectDocumentsList(MasterVO masterVO);
	
	public void deleteDocuments(MasterVO masterVO) throws Exception;
}
