package kr.or.ddit.iteams.pms.webhard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.dao.AttachDAO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.webhard.service.WebHardService;

@Controller
@RequestMapping("/pms/webhard/{proNo}/webhardDelete.do")
public class WebhardDeleteController {
	
	@Inject
	private AttachDAO dao;
	
	@Inject
	private WebHardService service;
	
	@Inject
	private FTPServerFileManager manager;

	
	@RequestMapping(method=RequestMethod.POST)
	public void doProcess(@ModelAttribute MasterVO master
			, @Authenticated MasterVO authMember
			,MasterVO masterVO
			, HttpServletRequest request
			,Model model) throws Exception {
		String attNos = request.getParameter("attNo");
		String[] attNosList = attNos.split(",");
		int cnt = 0;
		ServiceResult result = null;
		
		
		for(int i =0;i<attNosList.length;i++) {
			masterVO.setWhat(attNosList[i]);
			cnt = manager.deleteFilesForPMS(masterVO);

		}
		result = cnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
//		String message = null;
//		if(master.getResult()==ServiceResult.OK) {
//			message = "정상 처리되었습니다.";
//		} else {
//			message = "실패";			
//		}
//		reAttr.addFlashAttribute("message", message);
//		String viewName = "redirect:/pms/poject/"+authMember.getProNo()+"/webhardList.do";
		masterVO.setResult(result);
	}
}
