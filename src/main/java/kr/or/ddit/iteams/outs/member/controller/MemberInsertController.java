package kr.or.ddit.iteams.outs.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/outs/member/memberInsert.do")
public class MemberInsertController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String getFrom() {
		return "outs/member/memberForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doProcess() {
		return "outs/member/memberForm";
	}
	
}
