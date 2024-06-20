package kr.or.ddit.iteams.pms.documents.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.documents.service.DocumentService;
import kr.or.ddit.validate.groups.documents.DocumentsDeleteGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/pms/documents/{proNo}/documentsDelete.do")
@Slf4j
public class DocumentDeleteContoller {
	
	@Inject
	private DocumentService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public String getForm(@ModelAttribute @Validated(DocumentsDeleteGroup.class) MasterVO masterVO
			, Errors errors
			, @Authenticated MasterVO authMember
			, RedirectAttributes redirectAttr) throws Exception {
		
		String message = null;
		String viewName = null;
		
		if(!errors.hasErrors()) {
			masterVO.setWorkNum("");
			masterVO.setDocNum(masterVO.getWhat());
			service.deleteDocuments(masterVO);
			ServiceResult result = masterVO.getResult();
			if(result == ServiceResult.OK) {
				message = "문서를 성공적으로 삭제했습니다.";
				viewName = "redirect:/pms/documents/" + authMember.getProNo() + "/documentsList.do";
			} else {
				message = "문서 삭제에 실패했습니다.";
				viewName = "redirect:/pms/documents/" + authMember.getProNo() + "/documentsView.do?what=" + masterVO.getWhat();
			}
		}
		
		redirectAttr.addFlashAttribute("message", message);
		return viewName;
	}
}
