package kr.or.ddit.iteams.outs.sales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SalesRetrieveController {
	
	@RequestMapping("/outs/sales/salesList.do")
	public String getFrom() {
		return "outs/sales/salesList";
	}
	
	@RequestMapping("/outs/sales/salesView.do")
	public String doProcess() {
		return "outs/sales/salesView";
	}
	
}
