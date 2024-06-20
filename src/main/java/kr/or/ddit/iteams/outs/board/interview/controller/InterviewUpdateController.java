package kr.or.ddit.iteams.outs.board.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/outs/board/interview/interviewUpdate.do")
public class InterviewUpdateController {

	@RequestMapping(method=RequestMethod.GET)
	public String getFrom() {
		return "outs/board/interview/interviewForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doProcess() {
		return "outs/board/interview/interviewView";
	}
	
}
