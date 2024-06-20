package kr.or.ddit.iteams.pms.work.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.work.dao.WorkDAO;

@Controller
public class WorkGanttChartController {
	
	@Inject
	private WorkDAO dao;
	
	@RequestMapping("/pms/work/{proNo}/gantt.do")
	public String getView(Model model, @PathVariable("proNo")String proNo) {
		return "pms/work/workGanttChart";
	}
	
	@RequestMapping(value="/pms/work/{proNo}/gantt.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object getList(@PathVariable("proNo")String proNo) {
		MasterVO masterVO = new MasterVO();
		masterVO.setProNo(proNo);
		return dao.selectWorkListForGantt(masterVO);
	}
	
	@RequestMapping(value="/pms/work/{proNo}/myGantt.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object getMyList(@PathVariable("proNo")String proNo
			, @Authenticated MasterVO authMember) {
		return dao.selectWorkListFormyGantt(authMember);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/pms/work/{proNo}/ganttUpdate.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object getUpdate(MasterVO masterVO
			,@Authenticated MasterVO authMember) {
		String modifyMember = authMember.getRealName();
		masterVO.setModifyMember(modifyMember);
		int cnt = dao.updateWorkByGantt(masterVO);
		if(cnt > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
}
