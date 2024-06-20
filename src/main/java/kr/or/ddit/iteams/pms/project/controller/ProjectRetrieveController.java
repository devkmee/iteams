package kr.or.ddit.iteams.pms.project.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.CustomProjectMemVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.PMSTotalVO;
import kr.or.ddit.iteams.common.vo.PagingVO;
import kr.or.ddit.iteams.pms.project.service.ProjectService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ProjectRetrieveController {
	
	@Inject
	private ProjectService service;
	
	@RequestMapping("/pms/project/{proNo}/projectList.do")
	public String getForm(@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("searchVO") SearchVO searchVO
			,@Authenticated MasterVO authMember
			, MasterVO masterVO
			, Model model
		) {
			String memId = authMember.getMemId();
			String managerName = authMember.getManagerName();
			String projectName = authMember.getProjectName();
			
			masterVO.setMemId(memId);
			masterVO.setProjectName(projectName);
			masterVO.setManagerName(managerName);
				
			service.retrieveProjectList(masterVO);
			service.retrieveProBoardList(masterVO);

			model.addAttribute("masterVO", masterVO);
			
		return "pms/project/pro/pr/p/clientMain";
	}
	
	@RequestMapping("/pms/project/{proNo}/projectView.do")
	public String doProcess(
			@Authenticated MasterVO authMember
			,@PathVariable String proNo
			,MasterVO masterVO
			,@RequestParam("what")String what
			,Model model
			
			) {
		
			masterVO.setProNo(what);
			String memId = masterVO.getMemId();

			masterVO.setMemId(memId);
			
			List<MasterVO> proMemList = service.retrieveProject(masterVO);
			for(MasterVO mem:proMemList) {
				String createDate = mem.getCreateDate();
				masterVO.setCreateDate(createDate);
				String projectName = mem.getProjectName();
				masterVO.setProjectName(projectName);
			}
			
			model.addAttribute("proMemList",proMemList);
			model.addAttribute("masterVO",masterVO);
			
		return "pms/project/projectView";
	}
	
	
	
}
