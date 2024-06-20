package kr.or.ddit.iteams.pms.schedule.controller;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
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
import kr.or.ddit.validate.groups.schedule.ScheduleUpdateGroup;

@Controller
@RequestMapping("/pms/schedule/{proNo}/scheduleUpdate.do")
public class ScheduleUpdateController {
	
	@Inject
	private ScheduleService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public String doProcess(@Validated(ScheduleUpdateGroup.class) @ModelAttribute MasterVO masterVO
			,Errors errors
			,@Authenticated MasterVO authMember
			,@PathVariable String proNo
			,RedirectAttributes reAttr) {
		String viewName = null;
		String message = null;
		
		
		
		if(!errors.hasErrors()) {
			masterVO.setPlanNum(masterVO.getWhat());
			masterVO.setModifyMember(masterVO.getWriter());
			service.updateSchedule(masterVO);
			ServiceResult result = masterVO.getResult();
			if(result == ServiceResult.OK) {
				message = "일정을 성공적으로 수정했습니다.";
			} else {
				message = "서버 장애";
			}
		} else {
			message = "내용, 시작일, 종료일은 공백일 수 없습니다.";
		}
		reAttr.addFlashAttribute("message", message);
		viewName = "redirect:/pms/schedule/"+ proNo + "/scheduleList.do";
		return viewName;
	}
}
