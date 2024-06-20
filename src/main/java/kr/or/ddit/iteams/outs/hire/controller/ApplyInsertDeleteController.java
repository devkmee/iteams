package kr.or.ddit.iteams.outs.hire.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.OutsTotalVO;
import kr.or.ddit.iteams.outs.hire.service.ApplyProjectService;
import kr.or.ddit.iteams.outs.hire.service.ApplyProjectServiceImpl;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.hire.HireInsertGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ApplyInsertDeleteController {
	
	@Inject
	private ApplyProjectService service;
	
	//공고글 상세조회에서 버튼 눌렀을 때 실행되는 컨트롤러
	@RequestMapping(value="/outs/hire/Apply.do", method=RequestMethod.POST)
	public String applyProject(
			@Authenticated MasterVO master,
			RedirectAttributes re,
			@Validated(HireInsertGroup.class) @ModelAttribute("applyPro") MasterVO applyPro,
			Errors errors,
			Model model	
			) {		
		String message = null;
		String appId = master.getMemId();
		applyPro.setAppId(appId);
		
		if(!errors.hasErrors()) {
			service.insertDeleteApplyPro(applyPro);
			
			switch(applyPro.getResult()) {
				case OK:
					message = applyPro.getFunction();
					break;
				default:
					message = applyPro.getFunction();
					break;
			}
		}else {
			message = "검증 실패. 고객센터로 문의해주세요.";
		}
		re.addFlashAttribute("message", message);
		
		return "redirect:/outs/board/projectboard/projectView.do?what="+applyPro.getBoNum();
	}


}
