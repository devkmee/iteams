package kr.or.ddit.iteams.pms.documents.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.SearchWordCollector;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.common.dao.PMSOthersDAO;
import kr.or.ddit.iteams.pms.documents.service.DocumentService;

@Controller
public class DocumentsListController {
	
	@Inject
	private DocumentService service;
	
	@Inject
	private PMSOthersDAO dao;
	
	@RequestMapping("/pms/documents/{proNo}/documentsList.do")
	public String getForm(@PathVariable String proNo
			, Model model) {
		MasterVO masterVO = new MasterVO();
		masterVO.setProNo(proNo);
		List<MasterVO> dataList = dao.selectProjectMember(masterVO);
		model.addAttribute("proMemList", dataList);
		return "pms/documents/documentsList";
	}
	
	@RequestMapping(value="/pms/documents/{proNo}/documentsList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object getList(@ModelAttribute MasterVO masterVO
			, @Authenticated MasterVO authMember) throws IllegalAccessException, InvocationTargetException {
		masterVO.setCurrentPage(masterVO.getPage());
		service.selectDocumentsList(masterVO);
		SearchWordCollector.makeSearchVO();
		return masterVO;
	}
}
