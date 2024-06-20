package kr.or.ddit.iteams.pms.project.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.project.service.ProjectService;

@Controller
@RequestMapping("/pms/project/{proNo}/projectDelete.do")
public class ProjectDeleteController {
	
	@Inject
	private ProjectService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getForm(
			@Authenticated MasterVO authMember
			,MasterVO masterVO
			,Model model
			) {
		String proNo = masterVO.getWhat();
		
		masterVO.setProNo(proNo);
		
		
		service.removeProject(masterVO);
		
		model.addAttribute("masterVO",masterVO);
		
		
		return "redirect:projectList.do";
	}
}