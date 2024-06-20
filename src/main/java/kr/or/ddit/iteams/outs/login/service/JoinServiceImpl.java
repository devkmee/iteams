package kr.or.ddit.iteams.outs.login.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.ComboVO;
import kr.or.ddit.iteams.outs.login.dao.JoinDAO;
import kr.or.ddit.iteams.outs.login.vo.JoinDevRequestVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JoinServiceImpl implements JoinService {
	@Inject
	private JoinDAO dao;
	@Inject
	private FTPServerFileManager ftpManager;
	@Inject
	private PasswordEncoder encoder;
	
	@Inject
	private WebApplicationContext context;
	
	@Value("#{appInfo.profileAttachPath}")
	private String profileFolder;
	
	@Value("#{appInfo.profileAttachInnerServerPath}")
	private String profileServerUrl;
	
	private String encryptPassword(String password) {
		return encoder.encode(password);
	}
	
	@Override
	@Transactional
	public ServiceResult createMember(JoinDevRequestVO member) throws Exception {
		int checkFlag = 0;
		ServiceResult result = null;
		if(dao.idCheck(member.getMemId()) == 0) {
			// 암호화
			member.setMemPass(encryptPassword(member.getMemPass()));
			
			// Member Insert
			int rowcnt = dao.insertMember(member);
			String devRole = member.getMemRole();
			// 개인회원 가입
			if(StringUtils.equals("DEV", devRole)) {
				// 검증해야할 값 -> 개인회원: 2개의 Insert + DevTech 개수
				checkFlag += 2;
				// Dev Insert
				
				rowcnt += dao.insertDevMember(member);					
				ftpManager.uploadFilesForProfile(member);
				String attNo = null;
				MultipartFile profileImage = member.getProfileImage();
				
				AttachTotalVO profile = member.getProfileImageVO();
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
//					OutputStream os = new FileOutputStream(serverFolder);
					File imageFile = new File(saveFolder, profile.getAttachName());
					try(InputStream fis = new FileInputStream(imageFile)) {
//						Files.copy(workSpace, os);
						Files.copy(fis, serverPath, StandardCopyOption.REPLACE_EXISTING);
					}					
					attNo = member.getAttNo();
					int attCnt = member.getAttachList().size();
					attCnt = Integer.parseInt(attNo) + (attCnt - 1);
					attNo = String.valueOf(attCnt);
				} else {
					attNo = "";
				}		
				member.setDevImg(attNo);
				if(StringUtils.isNotBlank(attNo)) {
					dao.insertProfileImage(member);
				}

				// 기술스택 데이터가 있으면 기술스택 테이블에 넣어준다.
				String devTech = member.getDevTech();
				if(!StringUtils.isEmpty(devTech)) {
					String[] devTechArr = devTech.split(",");
					checkFlag += devTechArr.length;
					List<Map<String, String>> devTechList = new ArrayList<>();
					for (String string : devTechArr) {
						Map<String, String> m = new HashMap<>();
						m.put("memId", member.getMemId());
						m.put("devTech", string.trim());
						devTechList.add(m);
					}
					rowcnt += dao.insertDevTech(devTechList);
				}

			// 클라이언트 가입
			}else if(StringUtils.equals("CLIENT", devRole)) {
				// 검증해야할 값 -> 클라이언트회원: 2개의 Insert
				checkFlag += 2;
				rowcnt += dao.insertClientMember(member);
			}
			// 회원가입 후 Dev Insert
			if(rowcnt == checkFlag) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}
	
	@Override
	public int idCheck(String memId) {
		return dao.idCheck(memId);
	}

	@Override
	public List<ComboVO> findTechCodeByKeyword(String keyword) {
		
		List<ComboVO> result = dao.findTechCodeByKeyword(keyword)
								  .stream()
								  .map(m -> new ComboVO(m.getDevStack(), m.getDevStack(), m.getTechImg()))
								  .collect(Collectors.toList());
										
										
								
		
		return result;
	}

	@Override
	public ServiceResult changePassword(JoinDevRequestVO params) {
		
		String memPass = params.getMemPass();
		params.setMemPass(encryptPassword(memPass));
		
		int result = dao.changePassword(params);
		if(result == 1) {
			return ServiceResult.OK;
		}else {
			return ServiceResult.FAILED;
		}
		
	}
	// 클라이언트 회원가입
	/*@Override
	public ServiceResult createClientMember(JoinDevRequestVO member) {
		
		return dao.insertClientMember(member);
	}*/

	@Override
	@Transactional
	public ServiceResult createMemberCli(JoinDevRequestVO member) throws Exception {
			int checkFlag = 0;
			ServiceResult result = null;
			if(dao.idCheck(member.getMemId()) == 0) {
				// 암호화
				member.setMemPass(encryptPassword(member.getMemPass()));
				
				// Member Insert
				int rowcnt = dao.insertMember(member);
				String devRole = member.getMemRole();

					// 검증해야할 값 -> 클라이언트회원: 2개의 Insert
					checkFlag += 2;
					rowcnt += dao.insertClientMember(member);
				
				// 회원가입 후 Dev Insert
				if(rowcnt == checkFlag) {
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.FAILED;
				}
			}else {
				result = ServiceResult.PKDUPLICATED;
			}
			return result;
		}

}
