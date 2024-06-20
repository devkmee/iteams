package kr.or.ddit.iteams.pms.project.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.CustomProjectMemVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.project.service.ProjectService;

@Controller
@RequestMapping("/pms/project/{proNo}/projectUpdate.do")
public class ProjectUpdateController {
	
	@Inject
	private ProjectService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getForm(
			@Authenticated MasterVO authMember
			,MasterVO masterVO
			,Model model
			,@RequestParam("what") String what
			) {
		
		masterVO.setProNo(what);
		
		String memId = authMember.getMemId();
		masterVO.setMemId(memId);
		
		String managerName = authMember.getManagerName();
		masterVO.setManagerName(managerName);
	
		service.retrieveProingMemList(masterVO);
	
		model.addAttribute("masterVO",masterVO);
		
		
		return "pms/project/projectEditForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doProcess(
			@Authenticated MasterVO authMember
			,MasterVO masterVO
			,Model model
			) {
		String viewName = null;
		String message = null;
		ServiceResult result = null;
		
		
		
		List<CustomProjectMemVO> memList=masterVO.getDevList();
		for(CustomProjectMemVO mem : memList) {
			String proNo = mem.getProNo();
			masterVO.setProNo(proNo);
		}
		
		
		result = service.modifyProject(masterVO);
		
		switch(result) {
		case OK:
			
			viewName = "redirect:/pms/project/{proNo}/projectView.do?what="+masterVO.getProNo()+"&who="+authMember.getMemId();
			break;
		default:
			viewName = "pms/project/projectList";
			message = "서버 오류, 잠시뒤 다시 해보셈.";
		}
		
		
		return viewName;
	}
}
