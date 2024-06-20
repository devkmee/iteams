package kr.or.ddit.iteams.outs.board.codebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/outs/board/codebook/codebookUpdate.do")
public class CodebookUpdateController {

	@RequestMapping(method=RequestMethod.GET)
	public String getFrom() {
		return "outs/board/codebook/codeForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doProcess() {
		return "outs/board/codebook/codeView";
	}
	
}
