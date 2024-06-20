package kr.or.ddit.iteams.outs.hire.service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.hire.dao.PublicHireDAO;
import kr.or.ddit.iteams.outs.message.dao.MessageDAO;
import kr.or.ddit.iteams.pms.project.dao.ProjectDAO;

@Service
public class PublicHireServiceImpl implements PublicHireService {
	
	@Inject
	private PublicHireDAO dao;
	
	@Inject
	private MessageDAO msgDao;
	
	@Inject
	private ProjectDAO proDao;

	@Override
	public void selectProfileList(MasterVO master) {
		int totalRecord = dao.selectProfileTotalRecord(master);
		master.setTotalRecord(totalRecord);
		
		List<MasterVO> profileList = dao.selectProfileList(master);
		master.setDataList(profileList);
	}

	@Override
	public void selectProfile(MasterVO master) throws IllegalAccessException, InvocationTargetException {
		MasterVO saved = dao.selectProfile(master);
		if(saved !=null ) {
			BeanUtils.copyProperties(master, saved);
		}else throw new PKNotFoundException(master.getDevId() + "님의 프로필을 찾을 수 없습니다.");
	}

	@Override
	public void selectProjectListForInvite(MasterVO master) {
		List<MasterVO> projList = dao.selectProjectListForInvite(master);
		master.setDataList(projList);
	}

	@Override
	@Transactional
	public void insertInviteDev(MasterVO master) {
		
		ServiceResult result = null;
		if(null == dao.selectCheckDev(master.getDevId())){
			if(null != dao.selectCheckPro(master.getProNo())) {
				int cnt = dao.insertInviteDev(master);
				if(cnt > 0) {
					MasterVO dummy = new MasterVO();
					dummy.setProNo(master.getProNo());
					MasterVO savedProject = dao.selectProject(dummy);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 E요일 a hh시 mm분");
					String title = master.getClientName() + "에서 회원님을 " + savedProject.getProjectName() + "으로 초대 했습니다.";
					String message = "초대 시각 : " + sdf.format(new Date()) + 
							"<br> 제안 연봉 : " + master.getInvitePriceValue() + "<br>상세한 내용은 마이페이지에서 확인 할 수 있습니다.";
					dummy.setMsgTitle(title);
					dummy.setMsgContent(message);
					dummy.setMemId("iteams");
					dummy.setMsgReceive(master.getDevId());
					msgDao.sendMessage(dummy);
					result = ServiceResult.OK;
				}else result = ServiceResult.FAILED;
			}else result = ServiceResult.NONEPRO;
		}else result = ServiceResult.NONEDEV;
		
		master.setResult(result);
	}

	@Override
	public void selectInviteListForDev(MasterVO master) {
		int totalRecord = dao.selectInviteTotalRecord(master);
		master.setTotalRecord(totalRecord);
		if(totalRecord > 0) {
			List<MasterVO> inviteList = dao.selectInviteListForDev(master);
			master.setDataList(inviteList);
		}
	}
	
	@Override
	public void selectInviteListForClient(MasterVO master) {
		List<MasterVO> inviteList = dao.selectInviteListForClient(master);
		master.setDataList(inviteList);
	}


	@Transactional
	@Override
	public void updateAcceptInvitation(MasterVO invite) {
		MasterVO saved = dao.selectInvite(invite);
		if(saved!=null) {
			if(null == dao.selectCheckDev(invite.getDevId())) {
				int updateCnt = dao.updateAcceptInvitation(invite);
				int insertCnt = dao.insertToProMember(invite);
				if(updateCnt > 0 && insertCnt > 0) {
					invite.setResult(ServiceResult.OK);
				}else {
					invite.setResult(ServiceResult.FAILED);
				}
			}else {
				invite.setResult(ServiceResult.DUPLICATED);
			}
		}else
			throw new PKNotFoundException(invite.getInviteNo()+"초대장을 찾을 수 없습니다");
	}

	@Override
	public void updateRefuseInvitation(MasterVO invite) {
		MasterVO saved = dao.selectInvite(invite);
		if(saved!=null) {
			int cnt = dao.updateRefuseInvitation(invite);
			if(cnt > 0 ) {
				invite.setResult(ServiceResult.OK);
			}else {
				invite.setResult(ServiceResult.FAILED);
			}
		}else
			throw new PKNotFoundException(invite.getInviteNo()+"초대장을 찾을 수 없습니다");	
	}

	@Override
	public void ProfileScrab(MasterVO scrab) {
		
		String function = null;
		ServiceResult result = null;
		MasterVO saved = dao.selectCheckProfile(scrab);
		
		if(null == saved) {
			int insertCnt = dao.insertProfileScrab(scrab);
			if(insertCnt > 0) {
				result = ServiceResult.OK;
				function = "추가";
			}else {
				result = ServiceResult.FAILED;
			}
		}else {
			int deleteCnt = dao.deleteProfileScrab(scrab);
			if(deleteCnt > 0) {
				result = ServiceResult.OK;
				function =  "삭제";
			}else {
				result = ServiceResult.FAILED;
			}
		}
		scrab.setFunction(function);
		scrab.setResult(result);
	}

	@Override
	public void selectProfileScrabList(MasterVO scrab) {
		int totalRecord = dao.profileScrabListTotalRecord(scrab);
		scrab.setTotalRecord(totalRecord);
		
		List<MasterVO> scrabList = dao.selectProfileScrabList(scrab);
		scrab.setDataList(scrabList);
	}

	@Override
	public void onlyDelectScrab(MasterVO scrab) {
		ServiceResult result = null;
		MasterVO saved = dao.selectCheckProfile(scrab);
		if (null != saved) {
			int deleteCnt = dao.deleteProfileScrab(scrab);
			if (deleteCnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
			scrab.setResult(result);
		}else throw new PKNotFoundException(scrab.getDevId() + "스크랩 기록을 찾을 수 없습니다");
	}

	@Override
	public void updateReapply(MasterVO masterVO) {
		int cnt = dao.updateReapply(masterVO);
		ServiceResult result = cnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
		masterVO.setResult(result);
	}


	


}
