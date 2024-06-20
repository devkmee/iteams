package kr.or.ddit.iteams.pms.documents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExcelChangeManager {
	
	@Value("#{appInfo.documentsPath}")
	private String savePath;
	
	public FileItem ArrayToExcelFileItem(List<List<String>> docData) throws Exception{
		// 워크북 생성
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 워크시트 생성
        HSSFSheet sheet = workbook.createSheet();
        // 행 생성
        HSSFRow row;
        // 쎌 생성
        HSSFCell cell;
               
        // 리스트의 size 만큼 row를 생성
        docData.remove(docData.size()-1);
        
        for(int rowIdx=0; rowIdx < docData.size(); rowIdx++) {         
            // 행 생성
            row = sheet.createRow(rowIdx);
            List<String> rowList = docData.get(rowIdx);
            for(int i = 0; i < rowList.size(); i++) {
            	cell = row.createCell(i);
            	cell.setCellValue(rowList.get(i));
            }        
        }
        
        // 입력된 내용 파일로 쓰기
        String saveName = UUID.randomUUID().toString();
        File file = new File(savePath, saveName);
        FileOutputStream fos = null;
        
        try {
        	fos = new FileOutputStream(file);
        	workbook.write(fos);
        } finally {
        	if(workbook!=null) workbook.close();
        	if(fos!=null) fos.close();    
        }
        
        
	
        FileItem fileItem = new DiskFileItem("mainFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
        
        try(InputStream input = new FileInputStream(file);
        	OutputStream os = fileItem.getOutputStream();) {
        	IOUtils.copy(input, os);        	
        }
             
        
        return fileItem;
	}
}
