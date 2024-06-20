package kr.or.ddit.iteams.outs.board.projectboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.projectboard.dao.OutsProjectDAO;
import kr.or.ddit.iteams.outs.board.projectboard.service.OutsProjectService;
import kr.or.ddit.validate.groups.outsproject.OutsProjectUpdateGroup;

@Controller
public class OutsProjectUpdateController {
	
	@Inject
	private OutsProjectService service;
	
	@Inject
	private OutsProjectDAO dao;
	
	@RequestMapping(value="/outs/board/projectboard/projectUpdate.do", method=RequestMethod.GET)
	public String getFrom(@ModelAttribute("project") MasterVO project, Model model) {
		
		MasterVO saved = service.retrieveProject(project.getWhat());
		saved.setCrudToken("UPDATE");
		model.addAttribute("project", saved);
		
		return "outs/board/projectboard/projectBoardForm";
	}
	
	@RequestMapping(value="/outs/board/projectboard/projectUpdate.do", method=RequestMethod.POST)
	public String doProcess(
		@Authenticated MasterVO master, 	
		@Validated(OutsProjectUpdateGroup.class) @ModelAttribute("project") MasterVO project
		, Errors errors		// Errors 안에 검증의 결과가 있음(하나라도 객체검증(Validated)에 실패하면 에러가 생김. 
		, Model model
	) throws Exception {
		String memId = master.getMemId();
		
		project.setMemId(memId);
		
		String viewName = null; 
		String message = null; 
		
		if(!errors.hasErrors()) {	// 에러가 없을 경우 
			ServiceResult result = service.modifyProject(project);
			switch(result) {
				case OK:
					viewName = "redirect:/outs/board/projectboard/projectView.do?what="+project.getBoNum();
					message = "정상 처리되었습니다.";
					break;
				default:
					viewName = "outs/board/projectboard/projectBoardForm";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
			
		}else {
			viewName = "outs/board/projectboard/projectBoardForm";
		}
		
		model.addAttribute("message", message);
		
		return viewName;
	}
	
	@PostMapping(value="/outs/board/projectboard/meetingJoin.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object joinMeeting(@ModelAttribute MasterVO masterVO) {
		Map<String, String> resultMap = new HashMap<>();
		int cnt = dao.updateMeetingState(masterVO);
		String message = cnt > 0 ? "success" : "fail";
		resultMap.put("message", message);
		return resultMap;
	}
	
	
	@RequestMapping(value="/outs/board/projectboard/deadline.do")
	public String deadline( 
		@ModelAttribute("project") MasterVO project, RedirectAttributes redirectAttributes 
	) { 
		ServiceResult result = null; 
		String message = null; 
		
		String what = project.getWhat(); 
		
		result = service.deadline(what); 
		
		switch(result) {
			case OK : message = "공고를 마감했습니다.";
					  break;
			default : message = "오류 발생!";
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return "redirect:/outs/member/mypageClientHire.do";
	}
}
