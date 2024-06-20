package kr.or.ddit.iteams.pms.workcheck.batch;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.workcheck.dao.WorkCheckDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DayoffInsertJob {
	
	@Inject
	private WorkCheckDAO dao;
	
	@Transactional
	public void insertList() {
		
		List<MasterVO> dataList = dao.selectTodayDayoffList();
		log.info("결근 리스트 : {}", dataList.toString());
		
		MasterVO masterVO = new MasterVO();
		masterVO.setDataList(dataList);
		
		int cnt = dao.insertDayoffList(masterVO);
		
		log.info("결근 인서트 결과 : {}", cnt);
		
	}
	
}
