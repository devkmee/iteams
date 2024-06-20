package kr.or.ddit.iteams.outs.login.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;

public interface EmailCheckService {

	public int check(String email);
	
	public int checkEmail(MasterVO masterVO);
	
	public String send(String email);
	
	public String getId(String email);
	
}
