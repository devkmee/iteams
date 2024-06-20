package kr.or.ddit.iteams.pms.common.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.common.dao.PMSOthersDAO;

@RestController
public class SelectProjectMemberListController {
	
	@Inject
	private PMSOthersDAO dao;
	
	@RequestMapping(value="/pms/{proNo}/projectMemberList.do", method=RequestMethod.POST , produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object getMemberList(@PathVariable("proNo")String proNo  ) {
		MasterVO masterVO = new MasterVO();
		masterVO.setProNo(proNo);
		return dao.selectProjectMember(masterVO);
	}
}
