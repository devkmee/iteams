package kr.or.ddit.iteams.outs.login.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface EmailCheckDAO {

	public int check(String email);
	
	public String getId(String email);
	
	public String getPass(String email);
	
	public int emailCheck(MasterVO masterVO);
	
}
