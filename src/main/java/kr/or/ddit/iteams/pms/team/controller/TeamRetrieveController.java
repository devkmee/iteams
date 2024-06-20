package kr.or.ddit.iteams.pms.team.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.team.service.TeamRetrieveService;

@Controller
public class TeamRetrieveController {
	
	@Inject
	private TeamRetrieveService service; 
	
	@RequestMapping("/pms/team/{proNo}/teamList.do")
	public String getForm(HttpServletRequest req, HttpServletResponse resp, Model model) {

		HttpSession session = req.getSession();
		MasterVO authMember = (MasterVO) session.getAttribute("authMember");
		
		String proNo = authMember.getProNo();
		
		List<MasterVO> vo = service.memberList(authMember);

		model.addAttribute("vo", vo);
		
		return "pms/team/teamForm";
	}
	
	@RequestMapping("/pms/team/{proNo}/teamView.do")
	public String doProcess(@RequestParam("what") String promemNum, Model model) {
		
		int number = Integer.parseInt(promemNum);
		
		MasterVO detail = service.memberDetail(promemNum); 
		
		model.addAttribute("detail", detail);	
		
		return "pms/team/teamView";
	}
	
	@GetMapping("/pms/team/{proNo}/messageForm.do")
	public String messageForm(@ModelAttribute MasterVO masterVO
			, Model model) {
		MasterVO messageVO = new MasterVO();
		messageVO.setMsgReceive(masterVO.getMemId());
		model.addAttribute("messageVO", messageVO);
		return "pms/include/outer/inner/in/second/messageForm";
	}
}
