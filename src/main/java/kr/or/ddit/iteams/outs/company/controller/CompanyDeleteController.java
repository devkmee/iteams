package kr.or.ddit.iteams.outs.company.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.company.service.ComponyService;
import kr.or.ddit.validate.groups.company.ReviewDeleteGroup;

@Controller
public class CompanyDeleteController {

	@Inject
	private ComponyService service;
	
	@RequestMapping(value="/outs/company/reviewDelete.do", method=RequestMethod.POST)
	public String doProcess(
			@Authenticated MasterVO member,
			@Validated(ReviewDeleteGroup.class) @ModelAttribute MasterVO review,
			Errors errors,
			RedirectAttributes red
			) throws Exception {
		String message = null;

		if (!errors.hasErrors()) {
			if (member != null) {
				review.setMemId(member.getMemId());
				service.deleteReview(review);
				switch (review.getResult()) {
				case OK:
					break;
				default:
					message = "서버 오류 잠시 후 다시 시도해주세요";
				}
			}
		} else {
			message = "댓글 번호를 가져올 수 없습니다  \\r\\n고객센터로 문의해주세요.";
		}
		red.addFlashAttribute("message", message);
		return "redirect:/outs/company/companyView.do?cliId="+review.getCliId();
	}
}
