package kr.or.ddit.iteams.pms.work.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.work.dao.WorkDAO;
import kr.or.ddit.iteams.pms.work.service.WorkService;
import kr.or.ddit.validate.groups.work.WorkInsertGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WorkInsertController {
	
	@Inject
	private WorkService service;
	
	@Inject
	private WorkDAO dao;
	
	@Value("#{appInfo['cPath']}")
	private String cPath;
	
	@ModelAttribute("masterVO")
	public Object makeModel() {
		MasterVO masterVO = new MasterVO();
		masterVO.setCrudToken("INSERT");
		return masterVO;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/pms/work/{proNo}/workInsert.do")
	public String getForm() {
		return "pms/work/workForm";
	}
	
//	@Validated(WorkInsertGroup.class)
//	, Errors errors
	@RequestMapping(method=RequestMethod.POST, value="/pms/work/{proNo}/workInsert.do")
	public String doProcess(@Validated(WorkInsertGroup.class) @ModelAttribute MasterVO masterVO
			, Errors errors) throws Exception {
		String viewName = null;

		if(!errors.hasErrors()) {
			service.insertWork(masterVO);			
			ServiceResult result = masterVO.getResult();
			if(result == ServiceResult.OK) {
				viewName = "redirect:/pms/work/"+masterVO.getProNo()+"/workView.do?what="+masterVO.getWorkNum();
			} else {
				viewName = "pms/work/workForm";
			}
		} else {
			viewName = "pms/work/workForm";
		}
		return viewName;
	}
	
	@RequestMapping(value="/pms/work/{proNo}/workTitleList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object getWorkTitleList(@PathVariable("proNo")String proNo) {
		MasterVO masterVO = new MasterVO();
		masterVO.setProNo(proNo);
		return dao.selectWorkTitleList(masterVO);
	}
}
