package kr.or.ddit.iteams.outs.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.login.LoginFailHandler;
import kr.or.ddit.iteams.outs.login.service.EmailCheckService;
import kr.or.ddit.iteams.outs.login.service.JoinService;
import kr.or.ddit.iteams.outs.login.vo.JoinDevRequestVO;
import kr.or.ddit.iteams.outs.login.vo.ResponseVO;

@Controller
public class LoginController {
	
	@Inject
	private EmailCheckService service;
	
	@Autowired
	private JoinService joinService;
	
//	
//	@Inject
//	private AuthService service;
//	
//	@RequestMapping(value="/outs/login/login.do", method=RequestMethod.GET)
//	public String getForm() {
//		return "outs/login/login";
//	}
//	
//	@RequestMapping(value="/outs/login/login.do", method=RequestMethod.POST)
//	public String doProcess(@Validated(LoginGroup.class) @ModelAttribute("masterVO") MasterVO masterVO
//			, Errors errors
//			, RedirectAttributes redirectAttr
//			, HttpSession session) throws IllegalAccessException, InvocationTargetException {
//		
//		String message = null;
//		String viewName = null;
//				
//		if(!errors.hasErrors()) {
//			service.selectForLogin(masterVO);
//			ServiceResult result = masterVO.getResult();
//			if(result == ServiceResult.OK) {
//				session.setAttribute("authMember", masterVO);
//				String originURL = (String) session.getAttribute("originURL");
//
//				viewName = "redirect:" + originURL;
//				message = masterVO.getMemId() + " 님 환영합니다.";
//			} else if(result == ServiceResult.NOTEXIST){
//				viewName = "redirect:/outs/login/login.do";
//				message = "잘못된 아이디";
//			} else {
//				viewName = "redirect:/outs/login/login.do";
//				message = "잘못된 비밀번호";
//			}
//		} else {
//			viewName = "redirect:/outs/login/login.do";
//			message = "아이디 혹은 비밀번호 누락";
//		}
//		
//		redirectAttr.addFlashAttribute(message);
//		return viewName;
//	}
	
	@RequestMapping(value="/outs/login/login.do", method=RequestMethod.GET)
	public String getForm(HttpServletRequest request
			, @ModelAttribute(LoginFailHandler.AUTHENTICATION_MESSAGE) String authenticationMessage
			, Model model
			, @Authenticated MasterVO authMember
			) {
		String referrer = request.getHeader("Referer");
	    request.getSession().setAttribute("prevPage", referrer);
	    String message = null;
	    String failId = null;
	    String viewName = "outs/login/login";
	    if(StringUtils.isNotBlank(authenticationMessage)) {
	    	String[] split = authenticationMessage.split(",");
	    	message = split[0];
	    	if(split.length > 1) {
	    		failId = split[1];	    		    		
	    	}
	    }
	    model.addAttribute("message", message);
	    model.addAttribute("failId", failId);
	    
	    if(authMember != null) {
	    	message = "이미 로그인 중입니다.";
	    	viewName = "redirect:/";	    	
	    }

		return viewName;
	}
	
	@RequestMapping(value="/outs/login/findId.do", method=RequestMethod.GET)
	public String findId() {
		return "outs/login/findId";
	}
	
	@RequestMapping(value="/outs/login/emailId.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Object emailCheck(@RequestParam("what") String email, Model model
			, @RequestParam(value="memId", required=false) String memId) {
		String keyNumber = null;
		String id = null;
		boolean valid = true;
		Map<String, String> result = new HashMap();
		
		if(!StringUtils.isBlank(memId)) {
			MasterVO masterVO = new MasterVO();
			masterVO.setMemId(memId);
			masterVO.setMemMail(email);
			int res = service.checkEmail(masterVO);
			if(res > 0) {
				valid = true;
			} else {
				keyNumber = "2";
				valid = false;
			}
		}
			if(valid) {
				int cnt = service.check(email);
				if(cnt > 0) {
					keyNumber = service.send(email);
					id = service.getId(email);
				}else {
					keyNumber = "1";
				}							
			}
		

		result.put("keyNumber", keyNumber);
		result.put("id", id);
		
		return result;
	}
	
	@RequestMapping(value="/outs/login/findPass.do")
	public String findPass() {
		return "outs/login/findPass";
	}
	
	@PostMapping(value="/outs/login/newPass.do")
	public ModelAndView newPass(@RequestParam("memId") String memId, ModelAndView model) {
		
		System.out.println(memId);
		
		model.addObject("memId", memId);
		model.setViewName("outs/login/newPass");
		
		return model;
	}
	
	@PostMapping(value="/outs/login/changePass.do")
	@ResponseBody
	public ResponseEntity<ResponseVO> changePass(@RequestBody JoinDevRequestVO params) {
		
		ServiceResult flag = joinService.changePassword(params);
		if(flag == ServiceResult.OK) {
			return new ResponseEntity<ResponseVO>(new ResponseVO(null, "/outs/login/login.do", true), HttpStatus.OK);
		}else {
			return new ResponseEntity<ResponseVO>(new ResponseVO(null, "/outs/login/login.do", true), HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
