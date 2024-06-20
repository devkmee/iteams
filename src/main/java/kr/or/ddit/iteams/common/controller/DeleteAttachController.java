package kr.or.ddit.iteams.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.service.AttachService;
import kr.or.ddit.iteams.common.vo.MasterVO;

@Controller
public class DeleteAttachController {
	
	@Inject
	private AttachService service;
	
	@RequestMapping(value="/pms/{proNo}/attachDel.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@Transactional
	public Object doProcessPMSWork(@ModelAttribute MasterVO masterVO) throws Exception {
		
		service.deleteAttachForPMS(masterVO);
		ServiceResult result = masterVO.getResult();
		
		String message = result == ServiceResult.OK ? "success" : "fail";
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("message", message);
		return resultMap;
		
	}
	
	@RequestMapping(value="/outs/attachDel.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@Transactional
	public Object doProcessOuts(@ModelAttribute MasterVO masterVO) throws Exception {
		
		service.deleteAttachForOuts(masterVO);
		ServiceResult result = masterVO.getResult();
		
		String message = result == ServiceResult.OK ? "success" : "fail";
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("message", message);
		return resultMap;
		
	}
	
	@RequestMapping(value="/profile/attachDel.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@Transactional
	public Object doProcessProfile(@ModelAttribute MasterVO masterVO) throws Exception {
		
		service.deleteAttachForProfile(masterVO);
		ServiceResult result = masterVO.getResult();
		
		String message = result == ServiceResult.OK ? "success" : "fail";
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("message", message);
		return resultMap;
		
	}
}
