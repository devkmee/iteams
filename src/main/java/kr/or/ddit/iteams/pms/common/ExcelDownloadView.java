package kr.or.ddit.iteams.pms.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.AbstractView;

import lombok.extern.slf4j.Slf4j;
import net.sf.jxls.transformer.XLSTransformer;

@Slf4j
public class ExcelDownloadView extends AbstractView {

	@Value("#{appInfo.workExcelTemplatePath}")
	private String workTemplate;
	
	@Value("#{appInfo.scheduleTemplatePath}")
	private String scheduleTemplate;
	
	@Value("#{appInfo.chattingTemplatePath}")
	private String chattingTemplate;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			model.put("downloadDate", sdf.format(new Date()));
			String name = (String) model.get("fileName");
			
			String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", " ");
					
			response.setHeader("Content-Type", "application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

			
			String requestURL = request.getRequestURL().toString();
			File template = null;
			
			if(StringUtils.contains(requestURL, "/work")) {
				template = new File(workTemplate);
			} else if(StringUtils.contains(requestURL, "/schedule")) {
				template = new File(scheduleTemplate);
			} else if(StringUtils.contains(requestURL, "/chatting")) {
				template = new File(chattingTemplate);
			}
				
			try (InputStream is = new FileInputStream(template);
					Workbook wb = new XSSFWorkbook(is);
					OutputStream os = response.getOutputStream();
				){
				XLSTransformer transformer = new XLSTransformer();
				transformer.transformWorkbook(wb, model);
				wb.write(os);

		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

}
