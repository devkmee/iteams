package kr.or.ddit.iteams.pms.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.team.dao.TeamRetrieveDAO;
import kr.or.ddit.iteams.pms.team.service.TeamRetrieveService;
import kr.or.ddit.iteams.pms.workcheck.dao.WorkCheckDAO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {

	@Inject
	private TeamRetrieveService service; 
	
	@Inject
	private TeamRetrieveDAO dao;
	
	@Inject
	private WorkCheckDAO workDao;
	
	
	@RequestMapping("pms/{proNo}/main.do")
	public String index(HttpServletRequest req, HttpServletResponse resp, Model model){
		
		HttpSession session = req.getSession();
		MasterVO authMember = (MasterVO) session.getAttribute("authMember");
		
		String proNo = authMember.getProNo();
		
		// 프로젝트 ->구성원쪽
		List<MasterVO> vo = service.projectMemberList(authMember);
		
		List<MasterVO> totalDevWorkList = new ArrayList<>();
		
		for(MasterVO dev:vo) {
			dev.setMemId(dev.getDevId());
			Map<String, String> workListMap = dao.selectDevWorkList(dev);
			dev.setDevWorkMap(workListMap);
			totalDevWorkList.add(dev);
		}
		
		model.addAttribute("totalWorkList", totalDevWorkList);
		
		for(int i = 0; i < vo.size(); i++) {
			MasterVO temp = vo.get(i);
			if(temp.getAuthority().equals("PM")) {
				MasterVO saved = vo.get(0);
				vo.remove(0);
				vo.add(0, temp);
				vo.remove(i);
				vo.add(i, saved);
			} else if(temp.getAuthority().equals("PL")) {
				MasterVO saved = vo.get(1);
				vo.remove(1);
				vo.add(1, temp);
				vo.remove(i);
				vo.add(i, saved);
			}
		}
				
		model.addAttribute("vo", vo);
		
		Map<String, Integer> workPie = workDao.selectWorkPie(authMember);
		model.addAttribute("pieMap", workPie);

		session.setAttribute("projectMemberList", vo);
		// 프로젝트 ->개요쪽
		Map<String, Object> workType = service.projectInfo(authMember); //HashMap의 인스턴스화
		Map<String, Object> workCnt = dao.projectInfo(authMember);
		int newProgress = (int) workCnt.get("NEW_SUM");
		int defectProgress = (int) workCnt.get("DEFECT_SUM");
		int supportProgress = (int) workCnt.get("SUPPORT_SUM");
		int noticeProgress = (int) workCnt.get("NOTICE_SUM");
		
		int progressTotal = newProgress+defectProgress+supportProgress+noticeProgress;
		
		int completeTotal = (int) workCnt.get("NEW_COMPLETED") +
							(int) workCnt.get("DEFECT_COMPLETED") +
							(int) workCnt.get("SUPPORT_COMPLETED") +
							(int) workCnt.get("NOTICE_COMPLETED");
		
		int progress = (int) Math.round(((double) completeTotal / (double) progressTotal) * 100);
		log.info("진행: {}, 완료 :{}, 진행도:{}", progressTotal,completeTotal,progress);
		
		model.addAttribute("progress", progress);
		
		MasterVO projectInfo = dao.selectProjectInfo(authMember);
		model.addAttribute("workCnt", workCnt);
		model.addAttribute("workType", workType);
		session.setAttribute("projectInfo", projectInfo);
		
		if(authMember.getMemRole().equals("DEV")) {
			Map<String, String> devWorkList = dao.selectDevWorkList(authMember);
			model.addAttribute("devWorkList", devWorkList);
		}
		

		return "pms/main";
	}
	
	@GetMapping(value="/pms/{proNo}/workInCheck.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object workInCheck(Model model
			, @Authenticated MasterVO authMember
			, HttpSession session) {
		int cnt = workDao.selectForWorkOut(authMember) == null ? 0 : 1;
		int workOut = workDao.selectForWorkIn(authMember) == null ? 0 : 1;
		session.setAttribute("workIn", cnt);
		session.setAttribute("workOut", workOut);
		return cnt;
	}
	
	@GetMapping(value="/pms/{proNo}/mainWorkList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object projectMainWorkList(@ModelAttribute MasterVO masterVO
			, @PathVariable("proNo") String proNo) {
		masterVO.setProNo(proNo);
		Map<String, String> devWorkList = dao.selectDevWorkList(masterVO);
		return devWorkList;
	}

}
