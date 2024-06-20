package kr.or.ddit.iteams.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.dao.AttachDAO;
import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.MasterVO;

@Service
public class AttachServiceImpl implements AttachService{

	@Inject
	private AttachDAO dao;
	
	@Inject
	private FTPServerFileManager manager;
	
	@Override
	public void insertAttachForOuts(MasterVO masterVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertAttachForPMS(MasterVO masterVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertAttachInfoForWork(MasterVO masterVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectAttachForPMS(MasterVO masterVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void deleteAttachForPMS(MasterVO masterVO) throws Exception {
		masterVO.setAttNo(masterVO.getWhat());
		AttachTotalVO savedAttach = dao.selectAttachForPMS(masterVO);
		List<AttachTotalVO> list = new ArrayList<>();
		list.add(savedAttach);
		masterVO.setAttachList(list);
		int cnt = manager.deleteFilesForPMS(masterVO);
		
		ServiceResult result = cnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
		masterVO.setResult(result);
		
	}

	@Override
	@Transactional
	public void deleteAttachForOuts(MasterVO masterVO) throws Exception {
		masterVO.setAttNo(masterVO.getWhat());
		AttachTotalVO savedAttach = dao.selectAttachForOuts(masterVO);
		List<AttachTotalVO> list = new ArrayList<>();
		list.add(savedAttach);
		masterVO.setAttachList(list);
		int cnt = manager.deleteFilesForOuts(masterVO);
		
		ServiceResult result = cnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
		masterVO.setResult(result);
		
	}

	@Override
	@Transactional
	public void deleteAttachForProfile(MasterVO masterVO) throws Exception {
		masterVO.setAttNo(masterVO.getWhat());
		AttachTotalVO savedAttach = dao.selectAttachForProfile(masterVO);
		List<AttachTotalVO> list = new ArrayList<>();
		list.add(savedAttach);
		masterVO.setAttachList(list);

		int cnt = manager.deleteFilesForProfile(masterVO);
		
		ServiceResult result = cnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
		masterVO.setResult(result);
		
	}

}
