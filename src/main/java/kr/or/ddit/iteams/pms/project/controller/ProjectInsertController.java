package kr.or.ddit.iteams.pms.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.CustomProjectMemVO;
import kr.or.ddit.iteams.common.vo.CustomProjectVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.project.service.ProjectService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/pms/project/{proNo}/projectInsert.do")
public class ProjectInsertController {
	
	@Inject
	private ProjectService service;
	
	//insertform에 들어갈때
	@RequestMapping(method=RequestMethod.GET)
	public String getForm(
			Model model
			,@Authenticated MasterVO authMember
			,@PathVariable String proNo
			,MasterVO masterVO
			,String devName
			,@RequestParam("boNum") String boNum
			) {	
		
		String memId = authMember.getMemId();
		String managerName = authMember.getManagerName();
		
		masterVO.setBoNum(boNum);
		masterVO.setManagerName(managerName);
		masterVO.setMemId(memId);
		
		service.retrieveProMemList(masterVO);
		
		model.addAttribute("masterVO",masterVO);
		
		return "pms/project/projectForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doProcess( @Authenticated MasterVO authMember
			,CustomProjectVO cPVO
			,MasterVO masterVO
			,Model model
			,String cliId
			
			) throws Exception {
		String viewName = null;
		String message = null;	
		
		List appIds = new ArrayList();
		String projectName = cPVO.getProjectName();
		masterVO.setCliId(cliId);
		
		
		masterVO.setProjectName(projectName);
		masterVO.setProjectName(projectName);

		model.addAttribute("masterVO",masterVO);
		ServiceResult result = null;
		List<CustomProjectMemVO> memList =masterVO.getDevList();
		List<CustomProjectMemVO> devList = new ArrayList<>();
		int index = 0;
		for(CustomProjectMemVO mem:memList ) {
			if(mem.getDevId()!=null) {
				devList.add(mem);
				
			}else {
				message="입력오류";
			}
		}
		
		masterVO.setDevList(devList);
		
		result=service.createProject(masterVO);
		
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
