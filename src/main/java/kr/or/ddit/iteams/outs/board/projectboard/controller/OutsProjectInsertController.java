package kr.or.ddit.iteams.outs.board.projectboard.controller;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.projectboard.service.OutsProjectService;
import kr.or.ddit.validate.groups.outsproject.OutsProjectInsertGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class OutsProjectInsertController {
	
	@Inject
	private OutsProjectService service;
	
	@RequestMapping(value="/outs/board/projectboard/projectInsert.do", method=RequestMethod.GET)
	public String getFrom(
			@ModelAttribute("project") MasterVO project, Model model ) {
		
		model.addAttribute("project", project);
		
		return "outs/board/projectboard/projectBoardForm";
	}
	
	@RequestMapping(value="/outs/board/projectboard/projectInsert.do", method=RequestMethod.POST)
	public String doProcess(
		@Authenticated MasterVO master, 	
		@Validated(OutsProjectInsertGroup.class) @ModelAttribute("project") MasterVO project
		, Errors errors		// Errors 안에 검증의 결과가 있음(하나라도 객체검증(Validated)에 실패하면 에러가 생김. 
		, Model model 
	) throws Exception {
		String memId = master.getMemId();
		
		project.setMemId(memId);
		
		String viewName = null; 
		String message = null;
		String meetingRoom = UUID.randomUUID().toString();
		
		if(!errors.hasErrors()) {	// 에러가 없을 경우
			project.setMeetingRoom(meetingRoom);
			ServiceResult result = service.createProject(project);
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
}
