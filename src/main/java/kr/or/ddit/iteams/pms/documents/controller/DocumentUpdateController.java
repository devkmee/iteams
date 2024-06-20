package kr.or.ddit.iteams.pms.documents.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.documents.ExcelChangeManager;
import kr.or.ddit.iteams.pms.documents.service.DocumentService;

@Controller
@RequestMapping("/pms/documents/{proNo}/documentsUpdate.do")
public class DocumentUpdateController {
	
	@Inject
	private DocumentService service;
	
	@Inject
	private ExcelChangeManager manager;
	
	@RequestMapping(method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object doProcess(@Authenticated MasterVO authMember
			, @RequestBody List<List<String>> docData) throws Exception {
		
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
			} else if(StringUtils.isBlank(metaList.get(1))) {
				valid = false;																
			}
		}
		
		if(!valid) {
			resultMap.put("message", "notValid");
			return resultMap;
		}
		
		metaList = docData.get(docData.size()-1);
		String docNum = metaList.get(0);
		String docTitle = metaList.get(1);
		String editRange = metaList.get(2);
		String originEditRange = metaList.get(3);
		
		if(!authMember.getAuthority().equals("PL") && !authMember.getAuthority().equals("PM")) {
			if(!authMember.getAuthority().equals(originEditRange)) {
				if(!originEditRange.equals("ALL")) {
					throw new AccessDeniedException("\"message\" : \"403\"");					
				}
			}
		}
		
		MasterVO masterVO = new MasterVO();
		masterVO.setDocNum(docNum);
		masterVO.setDocTitle(docTitle);
		editRange = StringUtils.isBlank(editRange) ? "ALL" : editRange;
		masterVO.setEditRange(editRange);
		
		FileItem fileItem = manager.ArrayToExcelFileItem(docData);
		
		String saveName = fileItem.getName();
		
        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
        MultipartFile[] multipartFileArray = new MultipartFile[] {multipartFile};
        masterVO.setProNo(authMember.getProNo());
        masterVO.setAttachFiles(multipartFileArray);
        
        masterVO.getAttachList().get(0).setAttachName(saveName);
        masterVO.getAttachList().get(0).setAttachOrigin(docTitle + ".xls");
        
        service.updateDocuments(masterVO);
        ServiceResult result = masterVO.getResult();
        String message = null;
        String url = null;
        
        if(result == ServiceResult.OK) {
        	message = "success";
        	url = "/pms/documents/" + authMember.getProNo() + "/documentsView.do?what=" + docNum;
        } else {
        	message = "fail";
        	url = "/pms/documents/" + authMember.getProNo() + "/documentsView.do?what=" + docNum;
        }
		
        resultMap.put("message", message);
        resultMap.put("url", url);
		
		return resultMap;
	}
}
