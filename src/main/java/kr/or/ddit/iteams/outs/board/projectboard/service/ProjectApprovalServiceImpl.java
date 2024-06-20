package kr.or.ddit.iteams.outs.board.projectboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.OutsTotalVO;
import kr.or.ddit.iteams.outs.board.projectboard.dao.ProjectApprovalDAO;

@Service
public class ProjectApprovalServiceImpl implements ProjectApprovalService {

	@Inject
	private ProjectApprovalDAO dao;
	
	@Override
	public List<MasterVO> retrieveProjectList(MasterVO base) {
		base.setTotalRecord(dao.selectTotalRecord(base));
		List<MasterVO> projectList = dao.selectProjectList(base);
		base.setDataList(projectList);
		return projectList;
	}

	@Override
	public MasterVO retrieveProject(String number) {
		
		MasterVO vo = dao.selectProject(number);
		
		if(vo==null)
			throw new PKNotFoundException(number +"번 글이 없음.");
		
		return vo;
	}

	@Override
	@Transactional
	public ServiceResult approveProject(MasterVO vo) {
		
		ServiceResult result = null;
		
		int rowcnt = dao.approveProject(vo); 
		dao.approveProjctUpdateGenboard(vo);
		
		if(rowcnt > 0) { 
			result = ServiceResult.OK; 
		}else { 
			result = ServiceResult.FAILED; 
		} 
		return result;
	}
	
	@Override
	@Transactional
	public ServiceResult refuseProject(MasterVO vo) {

		ServiceResult result = null;
		
		int rowcnt = dao.refuseProject(vo); 
		
		if(rowcnt > 0) { 
			result = ServiceResult.OK; 
		}else { 
			result = ServiceResult.FAILED; 
		} 
		return result;
	}
	
}
