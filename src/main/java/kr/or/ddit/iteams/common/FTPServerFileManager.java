package kr.or.ddit.iteams.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kr.or.ddit.annotations.TimeLine;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.dao.AttachDAO;
import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.login.vo.JoinDevRequestVO;

@Component
@Transactional
public class FTPServerFileManager {

	private FTPClient ftp = null;
	private InputStream is = null;
	private OutputStream os = null;
	
	@Value("#{appInfo.outsAttachServerPath}")
	private String outsAttachServerPath;
	
	@Value("#{appInfo.pmsAttachServerPath}")
	private String pmsAttachServerPath;
	
	@Value("#{appInfo.profileAttachServerPath}")
	private String profileAttachServerPath;
	
	@Value("#{appInfo.profileAttachPath}")
	private String profileAttachPath;
	
	@Inject
	private WebApplicationContext context;
	
	@Inject
	private AttachDAO dao;
	
	@Value("#{appInfo.FTPServerHost}")
	private String host;
	@Value("#{appInfo.FTPServerUser}")
	private String user;
	@Value("#{appInfo.FTPServerPassword}")
	private String password;
	
	// param( host server ip, username, password )
	public void getConnect() throws Exception {
		ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		int reply;
		ftp.connect(host);// 호스트 연결
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			throw new Exception("Exception in connecting to FTP Server");
		}
		ftp.login(user, password);// 로그인
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.enterLocalPassiveMode();
	}
	
	@PreDestroy
	public void destroy() {
		this.disconnect();
	}
	
	
	//프로필 업로드
	public void uploadFilesForProfile(JoinDevRequestVO memVO) throws Exception {

		List<AttachTotalVO> attList = memVO.getAttachList();
		AttachTotalVO proImage = memVO.getProfileImageVO();
		
		try {

			this.getConnect();

			String ftpPath = profileAttachServerPath;

			for(AttachTotalVO attach : attList) {
				attach.setFtpSavedpath(ftpPath);
			}
			
			if(attList == null) {
				if(proImage == null) {
					return;
				} else {
					attList = new ArrayList<>();
					proImage.setFtpSavedpath(profileAttachPath);
					attList.add(proImage);				
				}
			} else {
				if(proImage != null) {
					proImage.setFtpSavedpath(profileAttachPath);
					attList.add(proImage);				
				}
			}

			int cnt = dao.insertAttachForProfile(memVO);

			if (cnt > 0) {
				for (AttachTotalVO attach : attList) {				
					try (InputStream input = attach.getAttachFile().getInputStream();) {
						this.ftp.storeFile(ftpPath + attach.getAttachName(), input);
					}
				}

			}
		} finally {
			if(this.ftp != null && this.ftp.isConnected()) {
				this.disconnect();				
			}
		}
	}
	
	//프로필 다운로드
	public void downLoadFileForProfile(MasterVO masterVO) throws Exception {

		AttachTotalVO attachVO = masterVO.getAttachVO();

		if(attachVO == null || attachVO.getAttNo() == null) return;

		try {
			this.getConnect();
			String savedPath = profileAttachServerPath;
			String saveName = attachVO.getAttachName();
			ftp.changeWorkingDirectory(savedPath);
			InputStream inputStream = ftp.retrieveFileStream(saveName);
			masterVO.setFileDownLoadInputStream(inputStream);
		} finally {
			if(this.ftp != null && this.ftp.isConnected()) {
				this.disconnect();
			}
		}
	}
	
	//디폴트 프로필 이미지 다운로드
	public void downLoadFileForDefaultProfileImage(MasterVO masterVO) throws Exception {

		try {
			this.getConnect();
			String savedPath = profileAttachServerPath;
			ftp.changeWorkingDirectory(savedPath);
			InputStream inputStream = ftp.retrieveFileStream("defaultProfileImage.png");
			masterVO.setFileDownLoadInputStream(inputStream);
		} finally {
			if(this.ftp != null && this.ftp.isConnected()) {
				this.disconnect();
			}
		}
	}
	
	
	// param( 보낼파일경로+파일명, 호스트에서 받을 파일 이름, 호스트 디렉토리 )
	// PMS 일감 업로드
	public void uploadFilesForPMSWork(MasterVO masterVO, String... saveFolderPath) throws Exception {
		List<AttachTotalVO> attList = masterVO.getAttachList();
		if(attList == null || attList.isEmpty()) {
			return;
		}
		
		
		try {		
			this.getConnect();

			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			HttpSession session = req.getSession();
			MasterVO authMember = (MasterVO) session.getAttribute("authMember");
			
			String savePath = saveFolderPath.length == 0 ? "" : saveFolderPath[0];
			
			String ftpPath = pmsAttachServerPath + authMember.getProNo() + "/" + savePath;

			for(AttachTotalVO attach : attList) {
				attach.setFtpSavedpath(ftpPath);
			}

			int cnt = dao.insertAttachForPMS(masterVO);		
			cnt +=  dao.insertAttachInfoForWork(masterVO);

			if (cnt > 0) {
				for (AttachTotalVO attach : attList) {
					try (InputStream input = attach.getAttachFile().getInputStream();) {
						this.ftp.storeFile(ftpPath + attach.getAttachName(), input);
					}
				}

			}

		} finally {
			this.disconnect();
		}	
	}
	
	// param( 보낼파일경로+파일명, 호스트에서 받을 파일 이름, 호스트 디렉토리 )
	/**
	 * PMS 게시판 첨부파일 업로드
	 * @param masterVO
	 * @param saveFolderPath
	 * @throws Exception
	 */
	public void uploadFilesForPMSBoard(MasterVO masterVO, String... saveFolderPath) throws Exception {
		List<AttachTotalVO> attList = masterVO.getAttachList();
		if(attList == null || attList.isEmpty()) {
			return;
		}
		
		
		try {		
			this.getConnect();
			
			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			HttpSession session = req.getSession();
			MasterVO authMember = (MasterVO) session.getAttribute("authMember");
			
			String savePath = saveFolderPath.length == 0 ? "" : saveFolderPath[0];
			
			String ftpPath = pmsAttachServerPath + authMember.getProNo() + "/" + savePath;
			
			for(AttachTotalVO attach : attList) {
				attach.setFtpSavedpath(ftpPath);
			}
			
			int cnt = dao.insertAttachForPMS(masterVO);		
			cnt +=  dao.insertAttachForPMSBoard(masterVO);
			
			if (cnt > 0) {
				for (AttachTotalVO attach : attList) {
					try (InputStream input = attach.getAttachFile().getInputStream();) {
						this.ftp.storeFile(ftpPath + attach.getAttachName(), input);
					}
				}
				
			}
			
		} finally {
			this.disconnect();
		}	
	}
	
	// PMS 문서함 업로드
		@TimeLine
		public void uploadFilesForPMSDocuments(MasterVO masterVO, String... saveFolderPath) throws Exception {
			List<AttachTotalVO> attList = masterVO.getAttachList();
			if(attList == null || attList.isEmpty()) {
				return;
			}
			
			
			try {		
				this.getConnect();

				HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
				HttpSession session = req.getSession();
				MasterVO authMember = (MasterVO) session.getAttribute("authMember");
				
				String savePath = saveFolderPath.length == 0 ? "" : saveFolderPath[0];	
				String ftpPath = pmsAttachServerPath + authMember.getProNo() + "/" + savePath;

				for(AttachTotalVO attach : attList) {
					attach.setFtpSavedpath(ftpPath);
				}

				int cnt = dao.insertAttachForPMS(masterVO);		
				cnt +=  dao.insertDocumentAttach(masterVO);

				if (cnt > 0) {
					for (AttachTotalVO attach : attList) {
						try (InputStream input = attach.getAttachFile().getInputStream();) {
							this.ftp.storeFile(ftpPath + attach.getAttachName(), input);
						}
					}
					
					masterVO.setResult(ServiceResult.OK);
				}

			} finally {
				this.disconnect();
			}	
		}
		
		/**
		 * PMS 업로드 PMS_ATT 테이블만 인서트 => update 작업시에만 사용할것
		 * @param masterVO
		 * @param saveFolderPath
		 * @throws Exception
		 */
		public void uploadFilesForPMS(MasterVO masterVO, String... saveFolderPath) throws Exception {
			List<AttachTotalVO> attList = masterVO.getAttachList();
			if(attList == null || attList.isEmpty()) {
				return;
			}
			
			
			try {		
				this.getConnect();
				
				HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
				HttpSession session = req.getSession();
				MasterVO authMember = (MasterVO) session.getAttribute("authMember");
				
				String savePath = saveFolderPath.length == 0 ? "" : saveFolderPath[0];	
				String ftpPath = pmsAttachServerPath + authMember.getProNo() + "/" + savePath;
				
				for(AttachTotalVO attach : attList) {
					attach.setFtpSavedpath(ftpPath);
				}
				
				int cnt = dao.insertAttachForPMS(masterVO);		
				
				if (cnt > 0) {
					for (AttachTotalVO attach : attList) {
						try (InputStream input = attach.getAttachFile().getInputStream();) {
							this.ftp.storeFile(ftpPath + attach.getAttachName(), input);
						}
					}
					
					masterVO.setResult(ServiceResult.OK);
				}
				
			} finally {
				this.disconnect();
			}	
		}
	
	// PMS 다운로드
	public void downLoadFileForPMS(MasterVO masterVO) throws Exception {
		
		AttachTotalVO attachVO = masterVO.getAttachVO();
		
		if(attachVO == null || attachVO.getAttNo() == null) return;
		
		try {
			this.getConnect();
			String savedPath = attachVO.getFtpSavedpath();
			ftp.changeWorkingDirectory(savedPath);
			String savedFileName = attachVO.getAttachName();
			InputStream inputStream = ftp.retrieveFileStream(savedFileName);
			masterVO.setAttNo(attachVO.getAttNo());
			masterVO.setFileDownLoadInputStream(inputStream);
			
		} finally {
			if(this.ftp != null && this.ftp.isConnected()) {
				this.disconnect();
			}
		}
	}
	
	// 아웃소싱 다운로드
	public void downLoadFileForOuts(MasterVO masterVO) throws Exception {
		
		AttachTotalVO attachVO = masterVO.getAttachVO();
		
		if(attachVO == null || attachVO.getAttNo() == null) return;
		
		try {
			this.getConnect();
		} finally {
			if(this.ftp != null && this.ftp.isConnected()) {
				this.disconnect();
				String savedPath = outsAttachServerPath;
				ftp.changeWorkingDirectory(savedPath);
				String savedFileName = attachVO.getAttachName();
				InputStream inputStream = ftp.retrieveFileStream(savedFileName);
				masterVO.setFileDownLoadInputStream(inputStream);
			}
		}
	}
	
	// 아웃소싱 업로드
	public void uploadFilesForOuts(MasterVO masterVO) throws Exception {
		
		List<AttachTotalVO> attList = masterVO.getAttachList();
		
		if(attList == null || attList.isEmpty()) {
			return;
		}
		try {
			
			this.getConnect();
			
			String ftpPath = outsAttachServerPath;

			for(AttachTotalVO attach : attList) {
				attach.setFtpSavedpath(ftpPath);
			}

			int cnt = dao.insertAttachForOuts(masterVO);	

			if (cnt > 0) {
				for (AttachTotalVO attach : attList) {
					try (InputStream input = attach.getAttachFile().getInputStream();) {
						this.ftp.storeFile(ftpPath + attach.getAttachName(), input);
					}
				}

			}
		} finally {
			if(this.ftp != null && this.ftp.isConnected()) {
				this.disconnect();				
			}
		}
	}
	
	public int deleteFilesForPMS(MasterVO masterVO) throws Exception {
		List<AttachTotalVO> attList = masterVO.getAttachList();
		if(attList == null || attList.isEmpty()) {
			return 0;
		}

		try {		
			this.getConnect();

			int cnt = dao.deleteAttachForPMS(masterVO);
			for (AttachTotalVO attach : attList) {
				this.ftp.deleteFile(attach.getFtpSavedpath() + attach.getAttachName());

			}
			this.disconnect();
			return cnt;
			
		} finally {
			this.disconnect();
		}	
	}
	
	public int deleteFilesForOuts(MasterVO masterVO) throws Exception {
		List<AttachTotalVO> attList = masterVO.getAttachList();
		if(attList == null || attList.isEmpty()) {
			return 0;
		}
		
		int cnt = dao.deleteAttachForOuts(masterVO);
		
		try {		
			this.getConnect();	
				for (AttachTotalVO attach : attList) {
					this.ftp.deleteFile(attach.getFtpSavedpath() + attach.getAttachName());
				}
		this.disconnect();
		return cnt;
			
		} finally {
			this.disconnect();
		}	
	}
	
	public int deleteFilesForProfile(MasterVO masterVO) throws Exception {
		List<AttachTotalVO> attList = masterVO.getAttachList();
		if(attList == null || attList.isEmpty()) {
			return 0;
		}
		
		int cnt = dao.deleteAttachForProfile(masterVO);
		try {		
			this.getConnect();
			
				for (AttachTotalVO attach : attList) {
					this.ftp.deleteFile(attach.getFtpSavedpath() + attach.getAttachName());
				
			}
		this.disconnect();
		return cnt;
			
		} finally {
			this.disconnect();
		}	
	}
	
		
	public void createPMSRootFolder(String proNo) throws Exception {
		if(proNo == null) return;
		try {
			this.getConnect();
			this.ftp.makeDirectory(pmsAttachServerPath + proNo);
		} finally {
			if(this.ftp != null && this.ftp.isConnected()) {
				this.disconnect();				
			}
		}
	}
	
	public void createPMSFolder(String folderPath) throws Exception {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = req.getSession();
		MasterVO authMember = (MasterVO) session.getAttribute("authMember");
		if(authMember == null) return;
		try {
			this.getConnect();
			this.ftp.makeDirectory(pmsAttachServerPath + authMember.getProNo() + folderPath);
		} finally {
			if(this.ftp != null && this.ftp.isConnected()) {
				this.disconnect();				
			}
		}
	}

	public void disconnect() {
		if (this.ftp.isConnected() && this.ftp != null) {
			try {
				this.ftp.logout();
				this.ftp.disconnect();
			} catch (IOException f) {
				f.printStackTrace();
			}
		}
	}

//	public static void main(String[] args) throws Exception {
//		FTPServerUploader uploader = new FTPServerUploader("192.168.0.123", "user01", "java");
//		uploader.uploadFile("D:/FTP/hello.txt", "hello.txt", "2");
//		uploader.disconnect();
//	}

}
