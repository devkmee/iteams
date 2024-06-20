package kr.or.ddit.iteams.pms.schedule.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.schedule.dao.ScheduleDAO;

@Controller
public class ScheduleExcelDownloadView {
	
	@Inject
	private ScheduleDAO dao;
	
	@RequestMapping(value="/pms/schedule/{proNo}/scheduleExcel.do", method=RequestMethod.GET)
	public String getList(@PathVariable String proNo
			, Model model) {
		MasterVO masterVO = new MasterVO();
		masterVO.setProNo(proNo);
		List<MasterVO> dataList = dao.selectScheduleListTotal(masterVO);
		model.addAttribute("list", dataList);
		model.addAttribute("count", dataList.size());
		model.addAttribute("fileName", "일정목록");
		
		return "excelDownloadView";
	}
}
