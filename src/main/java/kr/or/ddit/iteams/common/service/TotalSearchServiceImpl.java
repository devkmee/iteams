package kr.or.ddit.iteams.common.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.iteams.common.dao.TotalSearchDAO;
import kr.or.ddit.iteams.common.vo.MasterVO;

@Service
public class TotalSearchServiceImpl implements TotalSearchService{

	@Inject
	private TotalSearchDAO dao;
	
	@Override
	public void selectTotalSearchList(MasterVO masterVO) {
		int records = dao.selectTotalRecordsSearchResult(masterVO);
		masterVO.setTotalRecord(records);
		masterVO.setDataList(dao.selectTotalSearchList(masterVO));
	}

}
