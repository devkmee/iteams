package kr.or.ddit.iteams.outs.sales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/outs/sales/salesInsert.do")
public class SalesInsertController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String getFrom() {
		return "outs/sales/salesForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doProcess() {
		return "outs/sales/salesForm";
	}
	
}
