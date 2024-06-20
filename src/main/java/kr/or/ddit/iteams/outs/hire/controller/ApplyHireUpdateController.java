package kr.or.ddit.iteams.outs.hire.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.hire.service.ApplyProjectService;
import kr.or.ddit.validate.groups.hire.AppReturnGroup;
import kr.or.ddit.validate.groups.hire.HireUpdateGroup;

@Controller
public class ApplyHireUpdateController {
	
	@Inject
	private ApplyProjectService service;
	
	/**
	 * 지원자 채용
	 * @param appNo
	 * @param re
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value="/outs/hire/mypage/hireEdit.do",method=RequestMethod.POST)
	public String hireApply(
			@RequestParam("appNo") String appNo,
			@RequestParam("devId") String devId,
			RedirectAttributes re,
			Model model
			) throws IllegalAccessException, InvocationTargetException {
		MasterVO applyPro = new MasterVO();
		applyPro.setAppNo(appNo);
		applyPro.setDevId(devId);
		service.updateApplyHire(applyPro);
		
		String message = null;
		switch(applyPro.getResult()) {
				case OK:
					message = "지원자에게 채용 연락을 보냈습니다.";
					break;
				case DUPLICATED:
					message = "이미 채용 결과를 통보한 지원자입니다.";
					break;
				case NONEDEV:
					message = "다른 프로젝트에 참여 중인 개발자입니다.";
					break;
				default:
					message = "채용에 실패하였습니다. 고객센터로 문의해주세요.";
					break;
			}
		re.addFlashAttribute("message",message);
		
		return "redirect:/outs/hire/mypage/applyProfile.do?appNo="+applyPro.getAppNo();
	}
	
	
	@RequestMapping(value="/outs/hire/mypage/returnEdit.do",method=RequestMethod.POST)
	public String returnApply(
			@RequestParam("appNo") String appNo,
			@RequestParam("devId") String devId,
			@Validated(AppReturnGroup.class) @ModelAttribute("dev") MasterVO applyPro,
			Errors errors,
			RedirectAttributes re,
			Model model
			) throws IllegalAccessException, InvocationTargetException {
		
		String message = null;
		if(!errors.hasErrors()) {
			applyPro.setAppNo(appNo);
			applyPro.setDevId(devId);
			service.updateApplyReturn(applyPro);

			switch(applyPro.getResult()) {
			case OK:
				message = "지원자에게 채용 반려 연락을 보냈습니다.";
				break;
			case DUPLICATED:
				message = "이미 채용 결과를 통보한 지원자입니다.";
				break;
			case NONEDEV:
				message = "다른 프로젝트에 참여 중인 개발자입니다.";
				break;
			default:
				message = "반려에 실패하였습니다. 고객센터로 문의해주세요.";
				break;
			}
		}
		re.addFlashAttribute("message",message);
		
		return "redirect:/outs/hire/mypage/applyProfile.do?appNo="+applyPro.getAppNo();
	}
	
	@PostMapping(value="/outs/hire/meetingOffer.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object meeting(@ModelAttribute MasterVO masterVO) {
		Map<String, String> resultMap = new HashMap<>();
		String temp = masterVO.getMeetingDate();
		temp = temp.replace("T", " ");
		masterVO.setMeetingDate(temp);
		service.updateAppMeeting(masterVO);
		ServiceResult result = masterVO.getResult();
		String message = result == ServiceResult.OK ? "면접을 제안했습니다."  : "면접 제안에 실패했습니다.";
		resultMap.put("message", message);
		return resultMap;
	}
	
	
}
