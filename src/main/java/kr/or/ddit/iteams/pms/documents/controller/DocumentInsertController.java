package kr.or.ddit.iteams.pms.documents.controller;

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

import javax.inject.Inject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.documents.ExcelChangeManager;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/pms/documents/{proNo}/documentsInsert.do")
public class DocumentInsertController {
	
	@Inject
	private FTPServerFileManager manager;
	
	@Inject
	private ExcelChangeManager excelManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getForm() {
		return "pms/documents/documentsForm";
	}
	
	@RequestMapping(method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object doProcess(@Authenticated MasterVO authMember
			, @RequestBody List<List<String>> docData) throws Exception {
		//제목, 수정권한 리스트 => 0번째 : 제목, 1번째 수정권한
		List<String> metaList = null;
		boolean valid = true;
		Map<String, String> resultMap = new HashMap<>();
		
		if(docData == null) {
			valid = false;
		} else {
			if(docData.isEmpty()) {
				valid = false;				
			}
			metaList = docData.get(docData.size()-1);

			if(metaList == null) {
				valid = false;								
			} else if(metaList.isEmpty()) {
				valid = false;												
			} else if(StringUtils.isBlank(metaList.get(0))) {
				valid = false;																
			}
		}
		
		if(!valid) {
			resultMap.put("message", "notValid");
			return resultMap;
		}
		
		metaList = docData.get(docData.size()-1);				      
		FileItem fileItem = excelManager.ArrayToExcelFileItem(docData);
		String saveName = fileItem.getName();
		
        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
        MasterVO masterVO = new MasterVO();
        MultipartFile[] multipartFileArray = new MultipartFile[] {multipartFile};
        masterVO.setProNo(authMember.getProNo());
        masterVO.setAttachFiles(multipartFileArray);
        String editRange = null;
        if(StringUtils.isBlank(metaList.get(1))) {
        	editRange = "ALL";
        } else {
        	editRange = metaList.get(1);
        }
        
        masterVO.setDocWriter(authMember.getRealName());
        masterVO.setEditRange(editRange);
        masterVO.setDocTitle(metaList.get(0));
        masterVO.setDevId(authMember.getMemId());
        masterVO.getAttachList().get(0).setAttachName(saveName);
        masterVO.getAttachList().get(0).setAttachOrigin(metaList.get(0) + ".xls");
        
        manager.uploadFilesForPMSDocuments(masterVO);
        
        String docNum = masterVO.getDocNum();
        ServiceResult result = masterVO.getResult();
        
        if(result == ServiceResult.OK) {
        	resultMap.put("url", "/pms/documents/" + authMember.getProNo() + "/documentsView.do?what=" + docNum);
        	resultMap.put("message", "success");
        }
       
		return resultMap;
	}
}
