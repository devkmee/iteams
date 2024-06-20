package kr.or.ddit.iteams.outs.member.batch;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.member.dao.MemberDAO;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MemberRealDeleteJob {
	
	@Inject
	private MemberDAO dao;
	
	@Transactional
	public void realDelete() {
		List<MasterVO> quitList = dao.selectQuitMemberList();
		log.info("탈퇴 목록 : {}", quitList.toString());
		int cnt = dao.deleteRealMember();
		log.info("탈퇴처리 결과 {} 명", cnt);
		
	}
}
