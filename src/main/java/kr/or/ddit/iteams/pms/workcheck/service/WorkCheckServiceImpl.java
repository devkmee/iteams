package kr.or.ddit.iteams.pms.workcheck.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.workcheck.dao.WorkCheckDAO;

@Service
public class WorkCheckServiceImpl implements WorkCheckService {

	@Inject
	private WorkCheckDAO dao;
	
	@Override
	public void insertForWorkIn(MasterVO masterVO) {
		
		MasterVO check = dao.selectForWorkIn(masterVO);
		
		if(check != null) {
			masterVO.setResult(ServiceResult.ALREADYEXIST);
			return;
		}
		
		int res = dao.insertForWorkIn(masterVO);
		if(res > 0) {
			masterVO.setResult(ServiceResult.OK);
		} else {
			masterVO.setResult(ServiceResult.FAILED);
		}

	}

	@Override
	public void updateForWorkOut(MasterVO masterVO) {
		
		MasterVO check = dao.selectForWorkOut(masterVO);
		
		if(check == null) {
			masterVO.setResult(ServiceResult.NOTEXIST);
			return;
		}
		
		int res = dao.updateForWorkOut(masterVO);
		if(res > 0) {
			masterVO.setResult(ServiceResult.OK);
		} else {
			masterVO.setResult(ServiceResult.FAILED);
		}
	}

	@Override
	public void selectWorkcheckList(MasterVO masterVO) {
		List<MasterVO> saved = dao.selectWorkcheckList(masterVO);
		List<Map<String, String>> workcheckList = new ArrayList<>();
		for(MasterVO temp : saved) {
			Map<String, String> tempMap = new HashMap<>();
			tempMap.put("title", temp.getStartDate().substring(10) + " ~ " + temp.getEndDate().substring(10));
			tempMap.put("start", temp.getStartDate());
			tempMap.put("end", temp.getEndDate());
			tempMap.put("startDate", temp.getStartDate());
			tempMap.put("endDate", temp.getEndDate());
			int color = (int) Math.floor(Math.random() * 16777215);
			tempMap.put("color", "#" + Integer.toHexString(color));
			workcheckList.add(tempMap);
		}
		
		masterVO.setDataList(workcheckList);
		
	}

	@Override
	public void selectTotalDayoffList(MasterVO masterVO) {
		int totalRecord = dao.selectDayoffTotalRecords(masterVO);
		masterVO.setTotalRecord(totalRecord);
		List<MasterVO> dataList = dao.selectTotalDayoffList(masterVO);
		masterVO.setDataList(dataList);
		
	}

	@Override
	public List<MasterVO> selectMonthDayoffList(MasterVO masterVO) {
		List<MasterVO> dayoffList = dao.selectMonthDayoffList(masterVO);
		for(int i = 0; i < dayoffList.size(); i++) {
			MasterVO temp = dayoffList.get(i);
			if(temp.getSolarDate() == null) {
				dayoffList.remove(i);
				break;
			}
		}
		return dayoffList;
	}

	@Override
	public List<MasterVO> selectMonthLateList(MasterVO masterVO) {
		List<MasterVO> lateList = dao.selectMonthLateList(masterVO);
		for(int i = 0; i < lateList.size(); i++) {
			MasterVO temp = lateList.get(i);
			if(temp.getSolarDate() == null) {
				lateList.remove(i);
				break;
			}
		}
		return lateList;
	}

}
