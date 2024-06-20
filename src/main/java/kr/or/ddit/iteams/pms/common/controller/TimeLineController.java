package kr.or.ddit.iteams.pms.common.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.common.service.PMSOthersService;

@Controller
public class TimeLineController {
	
	@Inject
	private PMSOthersService service;
	
	@RequestMapping(value="/pms/timeline/{proNo}/timelineList.do", method=RequestMethod.GET)
	public String getForm() {
		return "pms/timeline/timelineList";
	}
	
	@RequestMapping(value="/pms/timeline/{proNo}/timelineList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object getList(@ModelAttribute MasterVO masterVO
			,@Authenticated MasterVO authMember) {
		masterVO.setCurrentPage(masterVO.getPage());
		masterVO.setProNo(authMember.getProNo());
		service.selectTimeLineList(masterVO);
		return masterVO;
	}

}
