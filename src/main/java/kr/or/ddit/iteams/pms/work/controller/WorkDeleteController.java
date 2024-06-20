package kr.or.ddit.iteams.pms.work.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.work.service.WorkService;
import kr.or.ddit.validate.groups.work.WorkDeleteGroup;

@Controller
public class WorkDeleteController {
	
	@Inject
	private WorkService service;
	
	@RequestMapping("/pms/work/{proNo}/workDelete.do")
	public String doProcess(@Validated(WorkDeleteGroup.class) @ModelAttribute("masterVO")MasterVO masterVO
			, Errors errors
			,@Authenticated MasterVO authMember) throws Exception {
		String viewName = null;
		masterVO.setWorkNum(masterVO.getWhat());
		if(!errors.hasErrors()) {
			service.deleteWork(masterVO);
			ServiceResult result = masterVO.getResult();
			if(result == ServiceResult.OK) {
				viewName = "redirect:/pms/work/" + authMember.getProNo() + "/workList.do";
			} else {
				viewName = "redirect:/pms/work/" + authMember.getProNo() + "/workView.do?what=" + masterVO.getWorkNum();
			}
		}
		return viewName;
	}
}
