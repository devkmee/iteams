package kr.or.ddit.iteams.outs.schedule.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.schedule.service.OutsScheduleService;

@Controller
public class OutsScheduleListController {
	
	@Inject
	private OutsScheduleService service;
	
	@RequestMapping("/outs/schedule/scheduleList.do")
	public String getForm() {
		return "outs/schedule/scheduleList";
	}
	
	@RequestMapping(value="/outs/schedule/scheduleList.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object getList(MasterVO vo, @Authenticated MasterVO master) {
		
		String memId = master.getMemId();
		
		vo.setMemId(memId);
		service.selectScheduleList(vo); 
		List<?> dataList = vo.getDataList();
		
		return dataList;
	}
}
