package kr.or.ddit.iteams.pms.webhard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.webhard.service.WebHardService;

@Controller
@RequestMapping("/pms/webhard/{proNo}/webhardInsert.do")
public class WebhardUploadController {
	
	@Inject
	private WebHardService service;

	@RequestMapping(method=RequestMethod.GET)
	public String getForm() {
		return "pms/webhard/webhardForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView doProcess(@ModelAttribute MasterVO master
			, @Authenticated MasterVO authMember
			, RedirectAttributes reAttr) throws Exception {
		
		service.insertAttachfile(master);
		String message = null;
		if(master.getResult()==ServiceResult.OK) {
			message = "정상 처리되었습니다.";
		} else {
			message = "실패";			
		}
		reAttr.addFlashAttribute("message", message);
		String viewName = "redirect:/pms/poject/"+authMember.getProNo()+"/webhardList.do";
		return new ModelAndView(viewName, "message", message);
				
	}
}
