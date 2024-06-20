package kr.or.ddit.iteams.common.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.iteams.common.dao.AttachDAO;
import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.MasterVO;

@Controller
public class ImageRenderingController {
	@Inject
	private AttachDAO dao;
	
	@RequestMapping("/imageRender.do")
	public String download(@ModelAttribute MasterVO masterVO
			, Model model) throws FileNotFoundException, IOException{
		masterVO.setAttNo(masterVO.getWhat());
		AttachTotalVO attach = dao.selectAttachForProfile(masterVO);
		masterVO.setAttachVO(attach);
		model.addAttribute("masterVO", masterVO);
		return "imageRenderingView";
	}
}
