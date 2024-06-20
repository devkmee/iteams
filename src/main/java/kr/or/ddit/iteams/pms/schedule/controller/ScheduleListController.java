package kr.or.ddit.iteams.pms.schedule.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.schedule.service.ScheduleService;

@Controller
public class ScheduleListController {
	
	@Inject
	private ScheduleService service;
	
	@RequestMapping("/pms/schedule/{proNo}/scheduleList.do")
	public String getForm() {
		return "pms/schedule/scheduleList";
	}
	
	@RequestMapping(value="/pms/schedule/{proNo}/scheduleList.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object getList(MasterVO masterVO
			,@PathVariable String proNo) {
		
		masterVO.setProNo(proNo);
		service.selectScheduleList(masterVO);
		List<?> dataList = masterVO.getDataList();
		
		return dataList;
	}
}
