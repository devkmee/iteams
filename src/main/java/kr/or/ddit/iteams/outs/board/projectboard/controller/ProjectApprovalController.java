package kr.or.ddit.iteams.outs.board.projectboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.projectboard.service.ProjectApprovalService;
import kr.or.ddit.validate.groups.outsproject.RefuseGroup;

@Controller
public class ProjectApprovalController {

	@Inject
	private ProjectApprovalService service;
	
	@RequestMapping(value="/outs/board/projectboard/projectApprove.do", method=RequestMethod.POST)
	public String approve(
		@ModelAttribute("project") MasterVO project, Model model, RedirectAttributes redirectAttributes
	) {
		String viewName = null; 
		String message = null; 
		
		ServiceResult result = service.approveProject(project);
		
		switch(result) {
			case OK:
				viewName = "redirect:/outs/admin/board/projectboard/approvalList.do"; 
				message = "해당 프로젝트가 승인 되었습니다.";
				redirectAttributes.addFlashAttribute("message", message);
				break;
			default:
				viewName = "outs/board/projectboard/adminApprovalList";
				message = "서버 오류, 잠시후 다시 시도하세요.";
				model.addAttribute("message", message);
		}
		
		return viewName;
	}
	
	
	@RequestMapping(value="/outs/board/projectboard/projectRefuse.do", method=RequestMethod.POST)
	public String refuse(
		@Validated(RefuseGroup.class) @ModelAttribute("project") MasterVO project, Errors errors, Model model, RedirectAttributes redirectAttributes
	) { 
		String viewName = null; 
		String message = null; 
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.refuseProject(project);

			switch(result) {
			case OK:
				viewName = "redirect:/outs/admin/board/projectboard/approvalList.do"; 
				message = "해당 프로젝트가 반려 처리 되었습니다.";
				redirectAttributes.addFlashAttribute("message", message);
				break;
			default:
				viewName = "outs/board/projectboard/adminApprovalList";
				message = "서버 오류, 잠시후 다시 시도하세요.";
				model.addAttribute("message", message);
			}

		} else {
			viewName = "outs/board/projectboard/adminApprovalList"; 
		}
		
		return viewName;
	}
	
}
