package kr.or.ddit.iteams.pms.webhard.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.dao.AttachDAO;
import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.webhard.service.WebHardService;

@Controller
@RequestMapping("/pms/webhard/{proNo}/webhardDownload.do")
public class WebhardDownloadController {
	
	@Inject
	private AttachDAO dao;
	
	@Inject
	private WebHardService service;

	@RequestMapping(method=RequestMethod.GET)
	public String getForm() {
		return "pms/webhard/webhardForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doProcess(@ModelAttribute MasterVO master
			, @Authenticated MasterVO authMember
			,MasterVO masterVO
			, RedirectAttributes reAttr
			, HttpServletRequest request
			,Model model) throws Exception {
		
		String attNos = request.getParameter("attNo");
		String[] attNosList = attNos.split(",");
		

		model.addAttribute("attNosList", attNosList);
		model.addAttribute("authMember", authMember);
		
		return "webhardDownloadView";
	}
}
