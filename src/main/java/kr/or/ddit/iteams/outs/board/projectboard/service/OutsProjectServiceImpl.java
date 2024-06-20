package kr.or.ddit.iteams.outs.board.projectboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.projectboard.dao.OutsProjectDAO;

@Service
public class OutsProjectServiceImpl implements OutsProjectService {

	@Inject
	private OutsProjectDAO projectDAO;
	
	@Inject
	private FTPServerFileManager manager;
	
	
	@Override
	@Transactional
	public ServiceResult createProject(MasterVO vo) throws Exception {
		
		ServiceResult result = null;
		
//		encryptPassword(board);
		
		int rowcnt = projectDAO.insertProject(vo);
		
		if(rowcnt > 0) {
			manager.uploadFilesForOuts(vo);
//			rowcnt += processAttatches(vo);
			result = ServiceResult.OK;		
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public void retrieveProjectList(MasterVO base) {
		base.setTotalRecord(projectDAO.selectTotalRecord(base));
		List<MasterVO> projectList = projectDAO.selectProjectList(base);
		base.setDataList(projectList);
	}

	@Override
	@Transactional
	public MasterVO retrieveProject(String number) {
		MasterVO vo = projectDAO.selectProject(number);
		
		if(vo==null)
			throw new PKNotFoundException(number + "번 글이 없음.");
		
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("number", number);
		pMap.put("incType", "BO_HIT"); 
		projectDAO.incrementCount(pMap); 
		return vo;
	}

	@Override
	@Transactional
	public ServiceResult modifyProject(MasterVO vo) throws Exception {
		
		ServiceResult result = null;
		
//		encryptPassword(board); 
		
		int rowcnt = projectDAO.updateGen(vo); 
		rowcnt += projectDAO.updatePro(vo); 
		
		if(rowcnt > 1) { 
			manager.uploadFilesForOuts(vo);
//			rowcnt += processAttatches(vo); 
			result = ServiceResult.OK; 
		}else { 
			result = ServiceResult.FAILED; 
		} 
		return result; 
	}

	@Override
	@Transactional
	public ServiceResult removeProject(MasterVO vo) {
		
		ServiceResult result = null; 
		
		int rowcnt = projectDAO.deleteProject(vo); 
		
		if(rowcnt > 0) { 
			result = ServiceResult.OK; 
		}else { 
			result = ServiceResult.FAILED; 
		} 
		return result;
	}

	@Override
	public MasterVO selectProjectApp(MasterVO masterVO) {
		return projectDAO.selectProjectApp(masterVO);
	}

	@Override
	public MasterVO selectProject(MasterVO master) { 
		
		MasterVO vo = projectDAO.selectTraceProject(master);
		
		return vo;
	}

	
	@Override 
	public ServiceResult deadline(String boNum) {
		
		ServiceResult result = null; 
		
		int rowcnt = projectDAO.deadline(boNum); 
		
		if(rowcnt > 0) { 
			result = ServiceResult.OK; 
		}else { 
			result = ServiceResult.FAILED; 
		} 
		return result;
	}

}
