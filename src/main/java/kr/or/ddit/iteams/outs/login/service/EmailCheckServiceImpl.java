package kr.or.ddit.iteams.outs.login.service;

import java.util.Date;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.login.dao.EmailCheckDAO;
import kr.or.ddit.iteams.outs.login.util.GetTempPassword;

@Service
public class EmailCheckServiceImpl implements EmailCheckService {

	@Inject
	private EmailCheckDAO dao;
	
	@Override
	public int check(String email) {
		
		int result = dao.check(email);
		
		return result;
	}

	
	@Override
	public String send(String email) {
		String keyNumber = GetTempPassword.getTempPassword(6);
		
		// 메일 발송
		sendMail(email, keyNumber);
		
		return keyNumber;
	}
	
	
	//메일발송메서드
		public void sendMail(String email, String keyNumber) {
			
			// 메일 인코딩
			final String bodyEncoding = "UTF-8"; //콘텐츠 인코딩
				 
		    String subject = "iteams 인증번호입니다 : " + keyNumber;
		    String fromEmail = "iteams@gmail.com";
		    String fromUsername = "iteams manager";
		    String toEmail = email; 
		    
		    final String username = "iteams.ddit@gmail.com";        
		    final String password = "iteams301";	
		    
		    // 메일에 출력할 텍스트
		    StringBuffer sb = new StringBuffer();
		    sb.append("<h3>아이팀즈</h3>\n");
		    sb.append("<h4>이메일 인증번호입니다.</h4>\n");    
		    sb.append("이메일 인증번호 : " + keyNumber);    
		    String html = sb.toString();
		    
		    // 메일 옵션 설정
		    Properties props = new Properties();    
		    props.put("mail.transport.protocol", "smtp");
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.port", "465");
		    props.put("mail.smtp.auth", "true");
		    
		    props.put("mail.smtp.quitwait", "false");
		    props.put("mail.smtp.socketFactory.port", "465");
		    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		    props.put("mail.smtp.socketFactory.fallback", "false");
				 
		    try {
		          // 메일 서버  인증 계정 설정
		          Authenticator auth = new Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		              return new PasswordAuthentication(username, password);
		            }
		          };
		          
		          // 메일 세션 생성
		          Session session = Session.getInstance(props, auth);
		          
		          // 메일 송/수신 옵션 설정
		          Message message = new MimeMessage(session);
		          message.setFrom(new InternetAddress(fromEmail, fromUsername));
		          message.setRecipients(RecipientType.TO, InternetAddress.parse(toEmail, false));
		          message.setSubject(subject);
		          message.setSentDate(new Date());
		          
		          // 메일 콘텐츠 설정
		          Multipart mParts = new MimeMultipart();
		          MimeBodyPart mTextPart = new MimeBodyPart();
		          MimeBodyPart mFilePart = null;
		        
		          // 메일 콘텐츠 - 내용
		          mTextPart.setText(html, bodyEncoding, "html");
		          mParts.addBodyPart(mTextPart);
		                
		          // 메일 콘텐츠 설정
		          message.setContent(mParts);
		          
		          // 메일 발송
		          Transport.send( message );
		          
		    } catch ( Exception e ) {
		    	e.printStackTrace();
		    }
		 	   
		}


		@Override
		public String getId(String email) {

			String result = dao.getId(email);
			
			return result;
		}


		@Override
		public int checkEmail(MasterVO masterVO) {
			return dao.emailCheck(masterVO);
		}
	
}
