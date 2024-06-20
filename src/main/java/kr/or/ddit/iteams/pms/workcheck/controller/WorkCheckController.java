package kr.or.ddit.iteams.pms.workcheck.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.PreviousPage;
import kr.or.ddit.iteams.common.SearchWordCollector;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.common.dao.PMSOthersDAO;
import kr.or.ddit.iteams.pms.workcheck.dao.WorkCheckDAO;
import kr.or.ddit.iteams.pms.workcheck.service.WorkCheckService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WorkCheckController {
	
	@Inject
	private WorkCheckService service;
	
	@Inject
	private PMSOthersDAO dao;
	
	@RequestMapping(value="/pms/workcheck/{proNo}/workIn.do", method=RequestMethod.POST)
	public String workIn(@Authenticated MasterVO authMember
			, HttpServletRequest req
			, RedirectAttributes reAttr ) {
		String viewName = null;
		String message = null;
		
		service.insertForWorkIn(authMember);
		ServiceResult result = authMember.getResult();
		
		if(result == ServiceResult.OK) {
			message = "정상적으로 출근 처리 되었습니다.";
			
		} else if(result == ServiceResult.FAILED){
			message = "출근 처리에 실패했습니다.";
		} else if(result == ServiceResult.ALREADYEXIST) {
			message = "이미 퇴근 처리 되었습니다.";
		}
		
		viewName = PreviousPage.getPreviousPageByRequest(req);
		reAttr.addFlashAttribute("message", message);
		log.info("이전 페이지 : {}", viewName);
		return viewName;
	}
	
	@RequestMapping(value="/pms/workcheck/{proNo}/workOut.do", method=RequestMethod.POST)
	public String workOut(@Authenticated MasterVO authMember
			, HttpServletRequest req
			, RedirectAttributes reAttr) {
		String viewName = null;
		String message = null;
		
		service.updateForWorkOut(authMember);
		ServiceResult result = authMember.getResult();
		
		if(result == ServiceResult.OK) {
			message = "정상적으로 퇴근 처리 되었습니다.";
			authMember.setInDate("");
		} else if(result == ServiceResult.FAILED) {
			message = "퇴근 처리에 실패했습니다.";
		} else if(result == ServiceResult.NOTEXIST) {
			message = "출근 기록이 없습니다.";
		}
		
		viewName = PreviousPage.getPreviousPageByRequest(req);
		reAttr.addFlashAttribute("message", message);
		log.info("이전 페이지 : {}", viewName);
		return viewName;
	}
	
	@RequestMapping(value="/pms/workcheck/{proNo}/workcheckList.do", method=RequestMethod.GET)
	public String getForm() {
		return "pms/checkwork/checkworkList";
	}
	
	@RequestMapping(value="/pms/workcheck/{proNo}/workcheckList.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object getList(@ModelAttribute("masterVO") MasterVO masterVO
			, @Authenticated MasterVO authMember) {
		
		masterVO.setMemId(authMember.getMemId());
		masterVO.setProNo(authMember.getProNo());
		service.selectWorkcheckList(masterVO);
		
		List<?> dataList = masterVO.getDataList();
		
		return dataList;
	}
	
	@RequestMapping(value="/pms/workcheck/{proNo}/dayoffList.do")
	public String getDayoffForm(@PathVariable String proNo
			, Model model) {
		MasterVO masterVO = new MasterVO();
		masterVO.setProNo(proNo);
//		List<MasterVO> dataList = dao.selectProjectMember(masterVO);
//		model.addAttribute("proMemList", dataList);
		List<MasterVO> dayoffList = service.selectMonthDayoffList(masterVO);
		List<MasterVO> lateList = service.selectMonthLateList(masterVO);
		

		
		model.addAttribute("monthDayoffList", dayoffList);
		log.info("리스트 크기 : {}", dayoffList.get(0).getMonthDayoffList().size());
		model.addAttribute("monthLateList", lateList);
		
		return "pms/checkwork/dayoffList";
	}
	
	@RequestMapping(value="/pms/workcheck/{proNo}/dayoffList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object getDayoffList(@ModelAttribute MasterVO masterVO
			, @Authenticated MasterVO authMember) throws IllegalAccessException, InvocationTargetException {
		masterVO.setCurrentPage(masterVO.getPage());
		masterVO.setProNo(authMember.getProNo());
		service.selectTotalDayoffList(masterVO);
		SearchWordCollector.makeSearchVO();
		
		return masterVO;
		
	}
	
}
