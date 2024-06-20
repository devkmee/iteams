package kr.or.ddit.iteams.outs.login.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.ComboVO;
import kr.or.ddit.iteams.outs.login.service.JoinService;
import kr.or.ddit.iteams.outs.login.vo.JoinDevRequestVO;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.join.CliJoinInsertGroup;
import kr.or.ddit.validate.groups.join.JoinInsertGroup;

@Controller
public class JoinController {
	
	@Inject
	private JoinService service;
	
	@ModelAttribute("command")
	public String commandId() {
		return "INSERT";
	}
	
	@RequestMapping(value="/outs/login/join.do", method=RequestMethod.GET)
	public String communityMain() {
		
		return "outs/login/join";
	}
	
	@RequestMapping(value="/outs/login/join.do", method=RequestMethod.POST)
	public String process(@Validated(JoinInsertGroup.class) @ModelAttribute("member") JoinDevRequestVO member
			, BindingResult errors 
			, Model model) throws Exception{
		
		String viewName = null;
		String message = null;
		System.out.println(member.toString());
		if(!errors.hasErrors()) {
//		3-1. 통과
//			로직 사용
			ServiceResult result = service.createMember(member);
			switch(result) {
				case PKDUPLICATED:
	//			PK중복 : memberForm 으로 이동(기존 데이터 + 메시지 전달).
					viewName = "outs/login/join";
					message = "아이디 중복";
					break;
				case OK:
	//			OK : 웰컴 페이지로 이동
					viewName = "redirect:/";
					break;
				default:
	//			FAIL : memberForm 으로 이동(기존 데이터 + 메시지 전달).
					viewName = "outs/login/join";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
				}
			
		}else {
//		3-2. 불통
//			: memberForm으로 이동 (기존데이터 + 검증 결과 메시지 전달)
			viewName = "outs/login/join";
			
		}
		
		model.addAttribute("message", message);
		
		return viewName;
		
 	}
	
	@RequestMapping(value="/outs/login/cliJoin.do", method=RequestMethod.POST)
	public String client(@Validated(CliJoinInsertGroup.class) @ModelAttribute("member") JoinDevRequestVO member
			, BindingResult errors 
			, Model model) throws Exception{
		
		String viewName = null;
		String message = null;
		System.out.println(member.toString());
		if(!errors.hasErrors()) {
//		3-1. 통과
//			로직 사용
			ServiceResult result = service.createMemberCli(member);
			switch(result) {
			case PKDUPLICATED:
				//			PK중복 : memberForm 으로 이동(기존 데이터 + 메시지 전달).
				viewName = "outs/login/join";
				message = "아이디 중복";
				break;
			case OK:
				//			OK : 웰컴 페이지로 이동
				viewName = "redirect:/";
				break;
			default:
				//			FAIL : memberForm 으로 이동(기존 데이터 + 메시지 전달).
				viewName = "outs/login/join";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
			
		}else {
//		3-2. 불통
//			: memberForm으로 이동 (기존데이터 + 검증 결과 메시지 전달)
			viewName = "outs/login/join";
			
		}
		
		model.addAttribute("message", message);
		
		return viewName;
		
	}
	
	@GetMapping("/outs/login/idCheck.do")
	@ResponseBody
	public ResponseEntity<Integer> idCheck(String memId) {
		
		int result = service.idCheck(memId);
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/outs/login/findTechCodeByKeyword.do")
	@ResponseBody
	public ResponseEntity<List<ComboVO>> findTechCodeByKeyword(String keyword) {
		
		List<ComboVO> data = service.findTechCodeByKeyword(keyword);
		
		return new ResponseEntity<List<ComboVO>>(data, HttpStatus.OK);
	}
}
