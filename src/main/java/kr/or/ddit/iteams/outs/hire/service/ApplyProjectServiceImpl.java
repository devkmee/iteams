package kr.or.ddit.iteams.outs.hire.service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.OutsTotalVO;
import kr.or.ddit.iteams.outs.board.projectboard.dao.OutsProjectDAO;
import kr.or.ddit.iteams.outs.hire.dao.ApplyProjectDAO;
import kr.or.ddit.iteams.outs.hire.dao.PublicHireDAO;
import kr.or.ddit.iteams.outs.message.dao.MessageDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApplyProjectServiceImpl implements ApplyProjectService {
	
	@Inject
	private ApplyProjectDAO dao;
	
	@Inject
	private MessageDAO msgDao;
	
	@Inject
	private PublicHireDAO daoPublic;

	@Override
	@Transactional
	public void insertDeleteApplyPro(MasterVO applyPro) {
	
		String function = null;
		ServiceResult result = null;

		if(null == dao.selectApply(applyPro)) {
			int insertCnt = dao.insertApplyProject(applyPro);
			function = "지원에 성공하였습니다";
			if(insertCnt > 0) {
//				MasterVO applyProject = proDao.selectProject(applyPro.getBoNum());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 E요일 a hh시 mm분");
				
				String title = applyPro.getProjectName() + "에 " + applyPro.getAppId() + " 개발자님께서 지원하셨습니다.";
				String message = 
						applyPro.getProjectName() + " 에 " + 
								applyPro.getAppId() + " 개발자님께서 지원하셨습니다. <br> 지원시각 : " + sdf.format(new Date());
				applyPro.setMsgReceive(applyPro.getMemId());
				applyPro.setMsgContent(message);
				applyPro.setMsgTitle(title);
				applyPro.setMemId("iteams");
				msgDao.sendMessage(applyPro);
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}	
		}else {
			int deleteCnt = dao.DeleteApplyProject(applyPro);
			function = "지원을 취소하였습니다";
			if(deleteCnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}
		applyPro.setFunction(function);
		applyPro.setResult(result);
	}


	@Override
	public void selectProBoardListForClient(OutsTotalVO applyPro) {
		int totalRecord = dao.selectBOARDTotalRecord(applyPro);
		applyPro.setTotalRecord(totalRecord);
		
		List<OutsTotalVO> proList = dao.selectApplyListForClient(applyPro);
		applyPro.setDataList(proList);
		
	}

	
	@Override
	public void selectApplyListOneProject(OutsTotalVO applyPro) {
		int totalRecord = dao.selectAPPTotalRecord(applyPro);
		applyPro.setTotalRecord(totalRecord);
		
		List<OutsTotalVO> applyList = dao.selectApplyListOneProject(applyPro);
		applyPro.setProjectName(applyList.get(0).getProjectName());
		applyPro.setDataList(applyList);
	}

	
	@Override
	public void selectApplyListForDev(MasterVO applyPro) {
		int totalRecord = dao.selectAPPTotalRecord(applyPro);
		applyPro.setTotalRecord(totalRecord);
		
		List<OutsTotalVO> applyList = dao.selectApplyListForDev(applyPro);
		applyPro.setDataList(applyList);
	}


	@Override
	public void updateApplyHire(MasterVO applyPro) throws IllegalAccessException, InvocationTargetException {
		
		MasterVO saved = dao.selectApply(applyPro);
		
		if(saved != null) {
			if(StringUtils.equals(saved.getHiredNy(), "0")) {
				if(null == daoPublic.selectCheckDev(applyPro.getDevId())){
					int cnt = dao.updateHireApply(applyPro);
					if(cnt > 0) {
						applyPro.setResult(ServiceResult.OK);
					}else {
						applyPro.setResult(ServiceResult.FAILED);
					}
				}else {
					applyPro.setResult(ServiceResult.NONEDEV);
				}
			}else {
				applyPro.setResult(ServiceResult.DUPLICATED);
			}
		}else {
			throw new PKNotFoundException(applyPro.getAppNo() + "번 지원자를 찾을 수 없습니다.");
		}
	}


	@Override
	public void selectDevProfile(MasterVO applyPro) throws IllegalAccessException, InvocationTargetException {
		
		BeanUtils.copyProperties(applyPro, dao.selectDevProfile(applyPro));
	
	}


	@Override
	public void updateApplyReturn(MasterVO applyPro) throws IllegalAccessException, InvocationTargetException {
		
		MasterVO saved = dao.selectApply(applyPro);
		
		if(saved != null) {
			if(StringUtils.equals(saved.getHiredNy(), "0")) {
				if(null == daoPublic.selectCheckDev(applyPro.getDevId())){
					int cnt = dao.updateReturnApply(applyPro);
					if(cnt > 0) {
						applyPro.setResult(ServiceResult.OK);
					}else {
						applyPro.setResult(ServiceResult.FAILED);
					}
				}else {
					applyPro.setResult(ServiceResult.NONEDEV);
				}
			}else {
				applyPro.setResult(ServiceResult.DUPLICATED);
			}
		}else {
			throw new PKNotFoundException(applyPro.getAppNo() + "번 지원자를 찾을 수 없습니다.");
		}
	}


	@Override
	public void updateAppMeeting(MasterVO masterVO) {
		int cnt = dao.updateAppMeeting(masterVO);
		ServiceResult result = cnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
		masterVO.setResult(result);
	}







	
	

}
