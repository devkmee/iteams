package kr.or.ddit.iteams.pms.chatting.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.iteams.common.vo.ChattingVO;

@Controller
public class ChattingExcelDownloadController {

	@Inject
	private WebApplicationContext context;
	
	@GetMapping(value="/pms/chatting/{proNo}/chattingExcel.do")
	public String doProcess(@PathVariable("proNo") String proNo
			, Model model) {
		
		ServletContext application = context.getServletContext();
		String key = "회의기록" + proNo;
		List<ChattingVO> messageList =  (List<ChattingVO>) application.getAttribute(key);
		model.addAttribute("list", messageList);
		model.addAttribute("count", messageList.size());
		model.addAttribute("fileName", "회의기록");		
		
		return "excelDownloadView";
	}
}
