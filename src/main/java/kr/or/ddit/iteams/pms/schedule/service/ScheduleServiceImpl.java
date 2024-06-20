package kr.or.ddit.iteams.pms.schedule.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.annotations.TimeLine;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.schedule.dao.ScheduleDAO;

@Service
public class ScheduleServiceImpl implements ScheduleService{
	
	@Inject
	private ScheduleDAO dao;
	
	@Override
	public void selectScheduleList(MasterVO masterVO) {
		List<MasterVO> saved = dao.selectScheduleList(masterVO);
		List<Map<String, String>> schduleList = new ArrayList<>();
		for(MasterVO temp : saved) {
			Map<String, String> tempMap = new HashMap<>();
			tempMap.put("title", temp.getPlanContent());
			tempMap.put("start", temp.getStartDate());
			tempMap.put("end", temp.getEndDate());
			tempMap.put("planNum", temp.getPlanNum());
			tempMap.put("startDate", temp.getStartDate());
			tempMap.put("endDate", temp.getEndDate());
			tempMap.put("writerName", temp.getWriterName());
			tempMap.put("writer", temp.getPlanWriter());
			int color = (int) Math.floor(Math.random() * 16777215);
			tempMap.put("color", "#" + Integer.toHexString(color));
			tempMap.put("planContent", temp.getPlanContent());
			schduleList.add(tempMap);
		}
		
		masterVO.setDataList(schduleList);
		
	}

	@Override
	@TimeLine
	public void updateSchedule(MasterVO masterVO) {
		masterVO.setWorkNum("");
		int cnt = dao.updateSchedule(masterVO);
		ServiceResult result = cnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
		masterVO.setResult(result);
	}

	@Override
	@Transactional
	@TimeLine
	public void deleteSchedule(MasterVO masterVO) {
		int cnt = dao.deleteSchedule(masterVO);
		dao.deletePlan(masterVO);
		ServiceResult result = cnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
		masterVO.setResult(result);
	}

	@Override
	@TimeLine
	public void insertSchedule(MasterVO masterVO) {
		int cnt = dao.insertSchedule(masterVO);
		ServiceResult result = cnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
		masterVO.setResult(result);
	}


}
