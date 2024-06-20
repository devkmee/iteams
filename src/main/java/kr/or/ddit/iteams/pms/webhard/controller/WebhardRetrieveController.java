package kr.or.ddit.iteams.pms.webhard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.webhard.service.WebHardService;

@Controller
public class WebhardRetrieveController {
	
	@Inject
	private WebHardService service;
	
	@RequestMapping("/pms/poject/{proNo}/webhardList.do")
	public String getForm(
			@Authenticated MasterVO authMember
			,Model model
			) {
		String ftpsServer = "192.168.0.123";
	    int ftpsPort = 21;
	    String ftpsUser = "user01";
	    String ftpsPass = "java";
	    String proNo = authMember.getProNo();//프로젝트 번호
	    
	   
	    
	    List<Map<String, Object>> fileList = new ArrayList<>();
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.setControlEncoding("euc-kr");  // 한글파일명 때문에 디폴트 인코딩을 euc-kr로...
			ftpClient.connect(ftpsServer, ftpsPort);
			
			int reply = ftpClient.getReplyCode(); // 응답코드가 비정상이면 종료합니다
			 if (!FTPReply.isPositiveCompletion(reply)) {
			        ftpClient.disconnect();
			          
			    } else {
			        ftpClient.setSoTimeout(10000);  // 현재 커넥션 timeout을 millisecond 값으로 입력합니다
			        ftpClient.login("user01", "java"); // 로그인 유저명과 비밀번호를 입력 합니다

			        // 목록보기 구현
			        FTPFile[] ftpfiles = ftpClient.listFiles(String.format("/pms/%s", proNo));  // public 폴더의 모든 파일을 list 합니다
			        
			        if (ftpfiles != null) {
			        	for (FTPFile ftpFile : ftpfiles) {
			        		ftpFile.getSize();
			        		ftpFile.getUser();
			        		ftpFile.getTimestamp();
			        		
			        		Map<String, Object> fileInfo = new HashMap<>();

			        		fileInfo.put("type", ftpFile.getType()); // 파일 0 : 폴더 : 1
			        		
			        		AttachTotalVO retrieveWebHardList = service.retrieveWebHardList(ftpFile.getName());
			        		if(retrieveWebHardList==null) {
			        			continue;
			        		}
			        		fileInfo.put("attNo", retrieveWebHardList.getAttNo());
			        		fileInfo.put("fileName", retrieveWebHardList.getAttachOrigin()); // file.getName(), file.getSize() 등등..
			        		fileInfo.put("fileSize", ftpFile.getSize());
			        		fileInfo.put("fileUser", ftpFile.getUser());
			        		fileInfo.put("fileTimestamp", ftpFile.getTimestamp());
			        		fileList.add(fileInfo);
						}
			        }
			        ftpClient.logout();
			    }
			} catch (Exception e) {
			    
			    e.printStackTrace(); 
			} finally {
			   if (ftpClient != null && ftpClient.isConnected()) {
			       try {
			           ftpClient.disconnect();
			       } catch (IOException ioe) {
			           ioe.printStackTrace();
			       }
			   }
			}
		model.addAttribute("fileList",fileList);
		
		
		return "pms/webhard/NewFile";
	}
	

}
