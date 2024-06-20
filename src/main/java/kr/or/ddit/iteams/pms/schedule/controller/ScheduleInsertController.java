package kr.or.ddit.iteams.pms.schedule.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
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
import kr.or.ddit.validate.groups.schedule.ScheduleInsertGroup;

@Controller
@RequestMapping("/pms/schedule/{proNo}/scheduleInsert.do")
public class ScheduleInsertController {
	
	@Inject
	private ScheduleService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public String doProcess(@Validated(ScheduleInsertGroup.class) @ModelAttribute("masterVO")MasterVO masterVO
			, Errors errors
			, @PathVariable String proNo
			, @Authenticated MasterVO authMember
			, RedirectAttributes redirectAttr) {
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			masterVO.setWriterName(authMember.getRealName());
			masterVO.setPlanWriter(authMember.getMemId());
			masterVO.setProNo(proNo);
			service.insertSchedule(masterVO);
			ServiceResult result = masterVO.getResult();
			if(result == ServiceResult.OK) {
				message = "일정이 성공적으로 등록 되었습니다.";
				viewName = "redirect:/pms/schedule/"+ proNo + "/scheduleList.do";
			} else {
				message = "서버 에러 잠시 후 다시 시도해주세요.";
			}
		} else {
			viewName = "redirect:/pms/schedule/"+ proNo + "/scheduleList.do";
			message = "내용, 시작일, 종료일은 공백일 수 없습니다.";
		}
		
		redirectAttr.addFlashAttribute("message", message);
		return viewName;
	}
}
