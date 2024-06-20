package kr.or.ddit.iteams.pms.work.controller;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.work.service.WorkService;
import kr.or.ddit.validate.groups.work.WorkSelectGroup;

@Controller
public class WorkViewController {
	@Inject
	private WorkService service;
	
	@RequestMapping("/pms/work/{proNo}/workView.do")
	public String doProcess(@Validated(WorkSelectGroup.class) @ModelAttribute("masterVO")MasterVO masterVO
			, Errors errors) throws IllegalAccessException, InvocationTargetException {
		String viewName = null;
		if(!errors.hasErrors()) {
			masterVO.setWorkNum(masterVO.getWhat());
			service.selectWork(masterVO);
			viewName = "pms/work/workView";
		} else {
			viewName = "redirect:/pms/work/"+masterVO.getProNo()+"/workList.do";
		}
		
		return viewName;
	}
}
