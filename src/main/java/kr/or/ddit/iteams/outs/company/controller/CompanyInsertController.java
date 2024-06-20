package kr.or.ddit.iteams.outs.company.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.company.service.ComponyService;
import kr.or.ddit.validate.groups.board.BoardInsertGroup;
import kr.or.ddit.validate.groups.company.ReviewnsertGroup;

@Controller
public class CompanyInsertController {
	
	@Inject
	private ComponyService service;
	
	@RequestMapping(value="/outs/company/reviewInsert.do", method=RequestMethod.POST)
	public String insertReview(
			@Authenticated MasterVO member,
			@Validated(ReviewnsertGroup.class) @ModelAttribute MasterVO review,
			Errors errors,
			Model model
			) {
		String viewName = null;
		String message = null;
		review.setMemId(member.getMemId());
		
		if(!errors.hasErrors()) {
			service.insertReview(review);
			switch (review.getResult()) {
			case OK:
				viewName = "redirect:/outs/company/companyView.do?cliId="+review.getCliId();
				break;
			default:
				viewName = "outs/company/companyView.do?cliId="+review.getCliId();
				message = "서버 오류 잠시 후 다시 시도해주세요";
				break;
			}
		}else {
			viewName = "outs/company/companyView.do?cliId="+review.getCliId();
			message = "내용을 기입해주세요";
		}
		model.addAttribute("message", message);
		return viewName;
	}

}
