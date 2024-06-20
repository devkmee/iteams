package kr.or.ddit.iteams.pms.documents.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.iteams.common.dao.AttachDAO;
import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.MasterVO;

//@Controller
public class DocumentsPDFDownloadController {
	
	@Inject
	private AttachDAO dao;
	
	@RequestMapping(value="/pms/{proNo}/downloadPDF.do")
	public String doProcess(@ModelAttribute MasterVO masterVO
			, Model model) {
		
		masterVO.setAttNo(masterVO.getWhat());
		AttachTotalVO saved = dao.selectAttachForPMS(masterVO);
		model.addAttribute("attach", saved);
		
		return "documentsPDFDownloadView";
	}
}
