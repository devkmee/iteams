package kr.or.ddit.iteams.outs.member.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.BaseVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.MemberTotalVO;
import kr.or.ddit.iteams.common.vo.OutsTotalVO;
import kr.or.ddit.iteams.outs.login.vo.JoinDevRequestVO;
import kr.or.ddit.iteams.outs.member.dao.MemberDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	private MemberDAO dao;
	
	@Inject
	private FTPServerFileManager manager;
	
	@Resource(name="passwordEncoder")
	private PasswordEncoder encoder;
	
	@Inject
	private WebApplicationContext context;
	
	@Value("#{appInfo.profileAttachPath}")
	private String profileFolder;
	
	@Value("#{appInfo.profileAttachInnerServerPath}")
	private String profileServerUrl;
	
	
	@PostConstruct
	public void init() {
		log.info("주입된 dao 객체 : {}", dao.getClass());
	}

	
	@Override
	public void retrieveMemberList(MasterVO base) {
		int totalRecord = dao.selectTotalRecord(base);
		base.setTotalRecord(totalRecord);
		
		List<MasterVO> memList = dao.selectMemberList(base);
		base.setDataList(memList);
	}

	@Override
	public MasterVO selectDevInfo(String memId) {
		MasterVO vo = dao.selectDevInfo(memId);
		
		if(vo==null)
			throw new PKNotFoundException();
		
		return vo;
	}
	
	@Override
	public void selectDevPro(MasterVO vo) {
		
		int totalRecord = dao.selectAPPTotalRecord(vo);
		vo.setTotalRecord(totalRecord);
		
		List<MasterVO> applyList = dao.selectDevPro(vo);
		vo.setDataList(applyList);
	}
	
	@Override
	public MasterVO selectClientInfo(String memId) {
		MasterVO vo = dao.selectClientInfo(memId);
		
		if(vo==null)
			throw new PKNotFoundException();
		
		return vo;
	}

	@Override
	public MasterVO selectClientPro(String memId) {
		MasterVO vo = dao.selectClientPro(memId);
		
		if(vo==null)
			throw new PKNotFoundException();
		
		return vo;
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
	public void selectOngoingProject(MasterVO vo) {
		int totalRecord = dao.selectOngoingTotalRecord(vo);
		vo.setTotalRecord(totalRecord);
		if(totalRecord > 0) {
			List<MasterVO> inviteList = dao.selectOngoingProject(vo);
			vo.setDataList(inviteList);
		}
	}

	@Override
	public void selectEndProject(MasterVO vo) {
		int totalRecord = dao.selectEndTotalRecord(vo);
		vo.setTotalRecord(totalRecord);
		if(totalRecord > 0) {
			List<MasterVO> inviteList = dao.selectEndProject(vo);
			vo.setDataList(inviteList);
		}
		
	}

	@Override
	public ServiceResult updatePass(String memPass, String dbPass) {

		ServiceResult result = null; 
		
		if(encoder.matches(memPass, dbPass)){
			// 파라미터 2개 각각 입력받은 비번, 인증객체 VO에서 꺼낸 비번 
			// 서로 일치하면 true 
			result = ServiceResult.OK;
		}else{	// 일치하지 않을 경우 INVALIDPASSWORD 리턴
			result = ServiceResult.INVALIDPASSWORD;
		}
		
		return result;
	}

	@Override
	public MasterVO selectDevAtt(String memId) {
		MasterVO vo = dao.selectDevAtt(memId);
		
		return vo;
	}

	@Override
	@Transactional
	public ServiceResult updateDev(JoinDevRequestVO vo) throws Exception {
		
		ServiceResult result = null;
		
		dao.deleteAtt(vo); 

		manager.uploadFilesForProfile(vo);
		
		String attNo = null;
		MultipartFile profileImage = vo.getProfileImage();
		
		AttachTotalVO profile = vo.getProfileImageVO();
		String realPath = context.getServletContext().getRealPath(profileServerUrl);
		File saveFolder = new File(profileFolder);
		File serverFolder = new File(realPath, profile.getAttachName());
		Path serverPath = Paths.get(realPath + "/" + profile.getAttachName());
		
		if(StringUtils.isNotBlank(profile.getAttachOrigin())) {
			if(!saveFolder.exists()) {
				saveFolder.mkdirs();
			}
		
			if(!saveFolder.exists()) {
				serverFolder.mkdirs();
			}
			profile.saveTo(saveFolder);
			Path workSpace = Paths.get(profileFolder + "/" + profile.getAttachName());
//			OutputStream os = new FileOutputStream(serverFolder);
			File imageFile = new File(saveFolder, profile.getAttachName());
			try(InputStream fis = new FileInputStream(imageFile)) {
//				Files.copy(workSpace, os);
				Files.copy(fis, serverPath, StandardCopyOption.REPLACE_EXISTING);
			}					
			attNo = vo.getAttNo();
			int attCnt = vo.getAttachList().size();
			attCnt = Integer.parseInt(attNo) + (attCnt - 1);
			attNo = String.valueOf(attCnt);
		} else {
			attNo = "";
		}		
		vo.setDevImg(attNo);
		
		int rowcnt = dao.updateDev1(vo); 
		
		rowcnt += dao.updateDev2(vo); 
		
		if(rowcnt > 1) { 
			result = ServiceResult.OK; 
		}else { 
			result = ServiceResult.FAILED; 
		} 
		return result;
	}

	@Override
	public void selectBoardForClient(MasterVO vo) { 
		int totalRecord = dao.selectBOARDTotalRecord(vo); 
		vo.setTotalRecord(totalRecord); 
		
		List<MasterVO> proList = dao.selectApplyListForClient(vo);
		vo.setDataList(proList);
	}

	@Override
	public ServiceResult updateClient(JoinDevRequestVO vo) throws Exception {
		
		ServiceResult result = null;
		
//		dao.deleteAtt(vo); 

//		manager.uploadFilesForProfile(vo);
		
		int rowcnt = dao.updateClient(vo); 
		
		rowcnt += dao.updateClientMember(vo); 
		
		if(rowcnt > 1) { 
			result = ServiceResult.OK; 
		}else { 
			result = ServiceResult.FAILED; 
		} 
		return result;
	}

	@Override
	public ServiceResult deletePass(String memPass, String dbPass) {
		
		ServiceResult result = null; 
		
		if(encoder.matches(memPass, dbPass)){
			// 파라미터 2개 각각 입력받은 비번, 인증객체 VO에서 꺼낸 비번 
			// 서로 일치하면 true 
			result = ServiceResult.OK;
		}else{	// 일치하지 않을 경우 INVALIDPASSWORD 리턴
			result = ServiceResult.INVALIDPASSWORD;
		}
		
		return result;
	}

	@Override
	public ServiceResult deleteMember(String memId) {
		
		ServiceResult result = null; 
		
		int rowCnt = dao.deleteMember(memId); 
		
		if(rowCnt > 0) { 
			result = ServiceResult.OK; 
		}else { 
			result = ServiceResult.FAILED; 
		} 
		
		return result;
	}

}
