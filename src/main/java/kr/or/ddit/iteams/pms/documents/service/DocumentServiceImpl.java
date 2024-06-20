package kr.or.ddit.iteams.pms.documents.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.annotations.TimeLine;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.dao.AttachDAO;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.documents.dao.DocumentsDAO;

@Service
public class DocumentServiceImpl  implements DocumentService{

	@Inject
	private AttachDAO attachDao;
	
	@Inject
	private DocumentsDAO dao;
	
	@Inject
	private FTPServerFileManager manager;

	
	@Override
	public void selectDocuments(MasterVO masterVO) throws IllegalAccessException, InvocationTargetException {
		MasterVO documents = dao.selectDocuments(masterVO);
		
		if(documents != null) {
			BeanUtils.copyProperties(masterVO, documents);
			masterVO.setResult(ServiceResult.OK);
		} else {
			throw new PKNotFoundException("해당 문서가 없음");
		}
	}

	@Override
	@Transactional
	@TimeLine
	public void updateDocuments(MasterVO masterVO) throws Exception {
		MasterVO savedDocument = dao.selectDocuments(masterVO);
		AttachTotalVO savedExcel = attachDao.selectAttachForPMS(savedDocument);
		
//		masterVO.setDocTitle(savedDocument.getDocTitle());
		manager.uploadFilesForPMS(masterVO);
		int cnt = dao.updateDocuments(masterVO);
		
		List<AttachTotalVO> attachList = new ArrayList<>();
		attachList.add(savedExcel);
		savedDocument.setAttachList(attachList);
		cnt += attachDao.deleteAttachForPMS(savedDocument);
		manager.deleteFilesForPMS(savedDocument);
		
		ServiceResult result = cnt > 1 ? ServiceResult.OK : ServiceResult.FAILED;
		masterVO.setResult(result);
		
	}

	@Override
	public void selectDocumentsList(MasterVO masterVO) {
		int totalRecord = dao.selectDocumentsTotalRecords(masterVO);
		masterVO.setTotalRecord(totalRecord);
		List<MasterVO> documentsList = dao.selectDocumentsList(masterVO);
		masterVO.setDataList(documentsList);
	}

	@Override
	@Transactional
	@TimeLine
	public void deleteDocuments(MasterVO masterVO) throws Exception {
		masterVO.setDocNum(masterVO.getWhat());
		int cnt = dao.deleteDocuments(masterVO);
		AttachTotalVO savedAttach = attachDao.selectAttachForPMS(masterVO);
		masterVO.setDocTitle(savedAttach.getAttachOrigin().substring(0, savedAttach.getAttachOrigin().lastIndexOf(".")));
		List<AttachTotalVO> attachList = new ArrayList<>();
		attachList.add(savedAttach);
		masterVO.setAttachList(attachList);
		manager.deleteFilesForPMS(masterVO);
		
		if(cnt > 0) {
			masterVO.setResult(ServiceResult.OK);
		}		 		
	}
	
}
