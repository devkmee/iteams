package kr.or.ddit.iteams.pms.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScheduleViewController {
	
	@RequestMapping("/pms/schedule/scheduleView.do")
	public String doProcess() {
		return "pms/schedule/scheduleView";
	}
}
