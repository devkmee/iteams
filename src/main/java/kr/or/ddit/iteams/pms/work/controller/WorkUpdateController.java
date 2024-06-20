package kr.or.ddit.iteams.pms.work.controller;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.work.service.WorkService;
import kr.or.ddit.validate.groups.work.WorkSelectGroup;
import kr.or.ddit.validate.groups.work.WorkUpdateGroup;

@Controller
@RequestMapping("/pms/work/{proNo}/workUpdate.do")
public class WorkUpdateController {
	
	@Inject
	private WorkService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getForm(@Validated(WorkSelectGroup.class) @ModelAttribute MasterVO masterVO) throws IllegalAccessException, InvocationTargetException {
		
		masterVO.setWorkNum(masterVO.getWhat());
		service.selectWork(masterVO);
		masterVO.setCrudToken("UPDATE");
		return "pms/work/workForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doProcess(@Validated(WorkUpdateGroup.class) @ModelAttribute("masterVO") MasterVO masterVO
			, Errors errors
			,@Authenticated MasterVO authmember) throws Exception {
		
		String viewName = null;
		
		if(!errors.hasErrors()) {
			masterVO.setProNo(authmember.getProNo());
			String modifyMember = authmember.getDevName() == null ? authmember.getManagerName() : authmember.getDevName();
			masterVO.setModifyMember(modifyMember);
			service.updateWork(masterVO);
			ServiceResult result = masterVO.getResult();
			if(result == ServiceResult.OK) {
				viewName = "redirect:/pms/work/"+masterVO.getProNo()+"/workView.do?what="+masterVO.getWorkNum();
			} else {
				viewName = "pms/work/workForm";
			}
		} else {
			viewName = "pms/work/workForm";
		}
		
		return viewName;
	}
	
	
	
}
