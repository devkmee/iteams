package kr.or.ddit.iteams.pms.work.service;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.annotations.TimeLine;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.dao.AttachDAO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.work.dao.WorkDAO;

@Service
public class WorkServiceImpl implements WorkService{
	
	@Inject
	private WorkDAO dao;
	
	@Inject
	private AttachDAO attachDao;
	
	@Inject
	private FTPServerFileManager uploader;
	
//	@Value("#{appInfo.pmsAttachRealPath}")
//	private String saveFolderPath;

	@Override
	public void selectWorkList(MasterVO masterVO) {
		int totalRecord = dao.selectWorkTotalRecordCount(masterVO);
		masterVO.setTotalRecord(totalRecord);
		masterVO.setDataList(dao.selectWorkList(masterVO));
	}

	@Override
	public void selectWork(MasterVO masterVO) throws IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(masterVO, dao.selectWork(masterVO));
		masterVO.setAttachList(attachDao.selectAttachListForWork(masterVO));
	}

	@Override
	@TimeLine
	@Transactional
	public void updateWork(MasterVO masterVO) throws Exception {
		int cnt = dao.updateWork(masterVO);
		if(cnt > 0) {
			uploader.uploadFilesForPMSWork(masterVO);
			masterVO.setResult(ServiceResult.OK);
		} else {
			masterVO.setResult(ServiceResult.FAILED);
		}
	}

	@Override
	@TimeLine
	@Transactional
	public void deleteWork(MasterVO masterVO) throws Exception {
		MasterVO saved = dao.selectWork(masterVO);
		if(saved == null) throw new NotFoundException("해당글이 없음");
		
		uploader.deleteFilesForPMS(saved);			
		int cnt = dao.deleteWork(masterVO);
		if(cnt > 0) {
			masterVO.setResult(ServiceResult.OK);
		} else {
			masterVO.setResult(ServiceResult.FAILED);
		}	
	}

	@Override
	@Transactional
	@TimeLine
	public void insertWork(MasterVO masterVO) throws Exception {
		int cnt = dao.insertWork(masterVO);
		if(cnt > 0) {
				uploader.uploadFilesForPMSWork(masterVO);				
			masterVO.setResult(ServiceResult.OK);
		} else {
			masterVO.setResult(ServiceResult.FAILED);
		}
	}
	
	
}
