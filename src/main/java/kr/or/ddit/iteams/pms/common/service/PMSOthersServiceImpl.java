package kr.or.ddit.iteams.pms.common.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.common.dao.PMSOthersDAO;

@Service
public class PMSOthersServiceImpl implements PMSOthersService {

	@Inject
	private PMSOthersDAO dao;
	
	@Override
	public void selectTimeLineList(MasterVO masterVO) {
		int totalRecords = dao.selectTimeLineTotalRecordCount(masterVO);
		masterVO.setTotalRecord(totalRecords);
		masterVO.setDataList(dao.selectTimeLineList(masterVO));
	}

}
