package kr.or.ddit.iteams.pms.schedule.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.schedule.service.ScheduleService;
import kr.or.ddit.validate.groups.schedule.ScheduleDeleteGroup;
import kr.or.ddit.validate.groups.schedule.ScheduleUpdateGroup;

@Controller
@RequestMapping("/pms/schedule/{proNo}/scheduleDelete.do")
public class ScheduleDeleteController {
	
	@Inject
	private ScheduleService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public String doProcess(@Validated(ScheduleDeleteGroup.class) @ModelAttribute MasterVO masterVO
			,Errors errors
			,@Authenticated MasterVO authMember
			,@PathVariable String proNo
			,RedirectAttributes reAttr) {
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			masterVO.setPlanNum(masterVO.getWhat());
			masterVO.setWorkNum("");
			service.deleteSchedule(masterVO);
			ServiceResult result = masterVO.getResult();
			if(result == ServiceResult.OK) {
				message = "일정을 성공적으로 삭제했습니다.";
			} else {
				message = "서버 장애";
			}
		} else {
			message = "필수 입력사항 누락";
		}
		reAttr.addFlashAttribute("message", message);
		viewName = "redirect:/pms/schedule/"+ proNo + "/scheduleList.do";
		return viewName;
	}
}
