package kr.or.ddit.iteams.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MeetingController {
	
	@GetMapping("/outs/meeting/meeting.do")
	public String getForm() {
		return "outs/meeting/meeting";
	}
	
	@GetMapping("/pms/meeting/{proNo}/meeting.do")
	public String getPmsForm() {
		return "pms/meeting/meeting";
	}

}
