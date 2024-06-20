package kr.or.ddit.iteams.outs.schedule.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.schedule.service.OutsScheduleService;

@Controller
@RequestMapping("/outs/schedule/scheduleInsert.do")
public class OutsScheduleInsertController {
	
	@Inject
	private OutsScheduleService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public String doProcess(@ModelAttribute("masterVO")MasterVO masterVO
			, Errors errors
			, @Authenticated MasterVO authMember
			, RedirectAttributes redirectAttr) {
		
		String memId = authMember.getMemId();
		
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			masterVO.setWriterName(authMember.getRealName());
			masterVO.setPlanWriter(authMember.getMemId());
			masterVO.setMemId(memId);
			service.insertSchedule(masterVO);
			ServiceResult result = masterVO.getResult();
			if(result == ServiceResult.OK) {
				message = "일정이 성공적으로 등록 되었습니다.";
				viewName = "redirect:/outs/schedule/scheduleList.do";
			} else {
				message = "서버 에러 잠시 후 다시 시도해주세요.";
			}
		} else {
			for(ObjectError error : errors.getAllErrors()) {
				message += error.getDefaultMessage();
			}
		}
		
		redirectAttr.addFlashAttribute(message);
		return viewName;
	}
}
