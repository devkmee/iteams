package kr.or.ddit.iteams.outs.schedule.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.annotations.TimeLine;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.schedule.dao.OutsScheduleDAO;

@Service
public class OutsScheduleServiceImpl implements OutsScheduleService{
	
	@Inject
	private OutsScheduleDAO dao;
	
	@Override
	public void selectScheduleList(MasterVO vo) {
		List<MasterVO> saved = dao.selectScheduleList(vo);
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
		
		vo.setDataList(schduleList);
	}

	@Override
	@TimeLine
	public void updateSchedule(MasterVO masterVO) {
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
