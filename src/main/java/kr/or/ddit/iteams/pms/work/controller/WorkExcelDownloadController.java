package kr.or.ddit.iteams.pms.work.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.work.dao.WorkDAO;
import kr.or.ddit.iteams.pms.work.service.WorkService;

@Controller
public class WorkExcelDownloadController {
	
	@Inject
	private WorkDAO dao;
	
	@RequestMapping(value="/pms/work/{proNo}/workExcelDownload.do", method=RequestMethod.GET)
	public String makeList(@ModelAttribute MasterVO masterVO
			,@PathVariable("proNo") String proNo
			,Model model) {
		
		masterVO.setProNo(proNo);
		List<MasterVO> dataList = dao.selectWorkListTotal(masterVO);
		model.addAttribute("list", dataList);
		model.addAttribute("count", dataList.size());		
		model.addAttribute("fileName", "일감목록");
		
		return "excelDownloadView";
	}
}
