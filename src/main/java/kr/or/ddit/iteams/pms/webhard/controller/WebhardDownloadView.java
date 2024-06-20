package kr.or.ddit.iteams.pms.webhard.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.dao.AttachDAO;
import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.MasterVO;

public class WebhardDownloadView extends AbstractView{

	@Inject
	private FTPServerFileManager manager;
	
	@Inject
	private AttachDAO dao;
	
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] attNos = (String[]) model.get("attNosList");
		MasterVO authMember = (MasterVO) model.get("authMember");
		String fileName = authMember.getProjectName() + ".zip";
		fileName = URLEncoder.encode(fileName , "UTF-8").replaceAll("\\+", " ");
		
		response.setContentType("application/zip");
	    response.setStatus(HttpServletResponse.SC_OK);
	    response.addHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
		
	    List<String> fileNameList = new ArrayList<>();
	    
	    
		try(OutputStream byteOs = response.getOutputStream();
			BufferedOutputStream bufferOs = new BufferedOutputStream(byteOs);
			ZipOutputStream zipOs = new ZipOutputStream(bufferOs);) {
			
			for(String attNo :attNos) {
				
				MasterVO masterVO = new MasterVO();
				masterVO.setAttNo(attNo);
				AttachTotalVO savedAttach = dao.selectAttachForPMS(masterVO);
				
				if(fileNameList.contains(savedAttach.getAttachOrigin())) {
					continue;						
				}
				
				masterVO.setAttachVO(savedAttach);
				manager.downLoadFileForPMS(masterVO);
				
				FileInputStream fis = null;
				File tempFile = null;
				
				try(InputStream is = masterVO.getFileDownLoadInputStream();) {
					
					tempFile = File.createTempFile(String.valueOf(is.hashCode()), ".tmp");
					tempFile.deleteOnExit();
					FileUtils.copyInputStreamToFile(is, tempFile);
					fis = new FileInputStream(tempFile);

					zipOs.putNextEntry(new ZipEntry(savedAttach.getAttachOrigin()));
					IOUtils.copy(fis, zipOs);					
					zipOs.closeEntry();

				} finally {
					if(fis != null) {
						IOUtils.closeQuietly(fis);
					}
					if(tempFile != null) {
						tempFile.delete();
					}
				}				
				fileNameList.add(savedAttach.getAttachOrigin());
			}
			
			if(zipOs != null) {
				zipOs.finish();
				zipOs.flush();
				IOUtils.closeQuietly(zipOs);
			}
			
			IOUtils.closeQuietly(bufferOs);
			IOUtils.closeQuietly(byteOs);
			
		}
		
	}

}
