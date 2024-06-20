//package kr.or.ddit.iteams.pms.documents.view;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.URLEncoder;
//import java.nio.file.Files;
//import java.util.Map;
//
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItem;
//import org.apache.commons.io.IOUtils;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.artofsolving.jodconverter.OfficeDocumentConverter;
//import org.artofsolving.jodconverter.document.DefaultDocumentFormatRegistry;
//
//import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
//import org.artofsolving.jodconverter.office.OfficeManager;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.servlet.view.AbstractView;
//
//import com.lowagie.text.Document;
//
//import kr.or.ddit.iteams.common.FTPServerFileManager;
//import kr.or.ddit.iteams.common.vo.AttachTotalVO;
//import kr.or.ddit.iteams.common.vo.MasterVO;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class DocumentsPDFDownloadView extends AbstractView {
//
//	@Inject
//	private FTPServerFileManager manager;
//	private InputStream inputDocument = null;
//	private HSSFWorkbook myXlsWorkBook = null;
//	private Document iTextXlsToPdf = null;
//	private OutputStream os = null;
//	private InputStream pdfInput = null;
//	private OutputStream pdfOutput = null;
//	private InputStream fileItemInput = null;
//	private OfficeManager officeManager = null;
//
//	@Value("#{appInfo.pdfPath}")
//	private String pdfPath;
//
//	@Value("#{appInfo.documentsPath}")
//	private String excelPath;
//
//	@Override
//	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
//			HttpServletResponse resp) throws Exception {
//		// TODO Auto-generated method stub
//		AttachTotalVO attach = (AttachTotalVO) model.get("attach");
//		MasterVO masterVO = new MasterVO();
//		masterVO.setAttachVO(attach);
//		manager.downLoadFileForPMS(masterVO);
//		String fileName = attach.getAttachOrigin();
//		fileName = fileName.substring(0, fileName.lastIndexOf(".")) + ".pdf";
//
//		try {
//			File inputFile = new File(excelPath,attach.getAttachName()); // 실제 엑셀파일 위치
//			log.info("엑셀 위치 : {}",inputFile.getName());
//			File outputFile = new File(pdfPath + fileName); // 저장할 PDF파일 위치
//			DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
//
//			configuration.setPortNumber(8100); // 포트설정
//			configuration.setOfficeHome(new File("C:/Program Files (x86)/OpenOffice 4")); // OpenOffice 설치된 경로
//
//			officeManager = configuration.buildOfficeManager();
//			officeManager.start();
//			DefaultDocumentFormatRegistry formatRegistry = new DefaultDocumentFormatRegistry();
//			OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager, formatRegistry);
//			converter.convert(inputFile, outputFile);
//			
//			String downName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", " ");
//			resp.setContentType("application/octet-stream");
//			resp.setHeader("Content-Disposition", "attachment; filename=\"" + downName + "\"");
//			
//			FileItem fileItem = new DiskFileItem("mainFile", Files.probeContentType(outputFile.toPath()), false, outputFile.getName(), (int) outputFile.length(), outputFile.getParentFile());
//			fileItemInput = new FileInputStream(outputFile);
//			pdfOutput = fileItem.getOutputStream();
//	        IOUtils.copy(fileItemInput, pdfOutput);
//	        
//	        Long fileSize = fileItem.getSize();
//	        resp.setHeader("Content-Length", fileSize.toString());
//			
//			os = resp.getOutputStream();
//			pdfInput = new FileInputStream(outputFile);
//			
//			int c = 0;
//			while ((c = pdfInput.read()) != -1) {
//				os.write(c);
//			}
//			os.flush();
//
//		} finally {
//			if(officeManager != null) {
//				officeManager.stop();				
//			}
//			if(os != null) {
//				os.close();
//			}
//			if(fileItemInput != null) {
//				fileItemInput.close();
//			}
//			if(pdfOutput != null) {
//				pdfOutput.close();
//			}
//			if(pdfInput != null) {
//				pdfInput.close();
//			}
//		}
//	}
//
//}
