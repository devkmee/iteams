<%@page import="org.apache.commons.net.ftp.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.net.*" %>

<%

	FTPClient ftp = null;

	try
	{
		 String FilePath="";
	
	    ftp = new FTPClient();
	    ftp.setControlEncoding("UTF-8");
	    ftp.connect("127.0.0.1");			// 접속할 ip
	    ftp.login("user01", "java");	// 접속할 아이디, 비밀번호

	    ftp.setFileType(FTPClient.BINARY_FILE_TYPE);	// 파일 깨짐 방지	   

	    // ftp 저장할 장소 (루트의 imgs 폴더)
	    ftp.changeWorkingDirectory("/imgs/");
	    
	   	// 저장할 파일 선택
	    File uploadFile = new File("/Users/tj/Documents/text.txt");
	    FileInputStream fis = null;
	   
	    try
	    {
	        fis = new FileInputStream(uploadFile);
	        
	        // ftp 서버에 파일을 저장한다. (저장한 이름, 파일)
	        boolean isSuccess = ftp.storeFile(uploadFile.getName(), fis);
	        if (isSuccess)
	        {
	            System.out.println("Upload Success");
	        }
	    } catch (IOException ex) {
	        System.out.println(ex.getMessage());
	    } finally {
	        if (fis != null)
	            try
	            {
	                fis.close();
	            } catch (IOException ex) {}
	    }
	    ftp.logout();
	    
	} catch (SocketException e) {
	    System.out.println("Socket:" + e.getMessage());
	} catch (IOException e)	{
	    System.out.println("IO:" + e.getMessage());
	} finally {
	    if (ftp != null && ftp.isConnected())
	    {
	        try
	        {
	            ftp.disconnect();
	        } catch (IOException e)
	        {
	        }
	    }
	}

%>