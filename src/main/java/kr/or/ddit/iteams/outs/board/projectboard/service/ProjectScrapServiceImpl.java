package kr.or.ddit.iteams.outs.board.projectboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.BaseVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.OutsTotalVO;
import kr.or.ddit.iteams.outs.board.projectboard.dao.ProjectScrapDAO;

@Service
public class ProjectScrapServiceImpl implements ProjectScrapService {

	@Inject
	private ProjectScrapDAO dao;
	
	@Override
	@Transactional
	public ServiceResult scrap(MasterVO vo) {
		
		ServiceResult result = null;

		if(null == dao.selectScrap(vo)) {
			int insertCnt = dao.scarp(vo); 
			if(insertCnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}	
		}else {
//			int deleteCnt = dao.unScrap(vo);
//			if(deleteCnt > 0) {
//				result = ServiceResult.OK;
//			}else {
//				result = ServiceResult.FAILED;
//			}
			result = ServiceResult.PKDUPLICATED;
		}
		
		return result;
	}

	@Override
	public List<OutsTotalVO> retrieveProjectList(MasterVO base) {
		List<OutsTotalVO> projectList = dao.selectScrapList(base);
		base.setTotalRecord(dao.selectTotalRecord(base));
		base.setDataList(projectList);
		return projectList;
	}
	
	@Override
	public MasterVO retrieveProject(String number) {
		MasterVO vo = dao.selectScrapView(number);
		
		if(vo==null)
			throw new PKNotFoundException(number + "번 글이 없음.");
		
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("number", number);
		pMap.put("incType", "BO_HIT"); 
		dao.incrementCount(pMap); 
		return vo;
	}

	@Override
	@Transactional
	public ServiceResult unscrap(MasterVO vo) {
		
		ServiceResult result = null;
				
		int deleteCnt = dao.unScrap(vo);
		if(deleteCnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}
}
