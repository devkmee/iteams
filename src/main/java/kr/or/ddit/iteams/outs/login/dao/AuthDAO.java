package kr.or.ddit.iteams.outs.login.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface AuthDAO {
	
	
	public MasterVO selectMemberForAuth(String userName);
	
	public MasterVO selectForLogin(MasterVO masterVO);
	
	public MasterVO selectMemberForPMSAuth(MasterVO masterVO);
	
	/**
	 * 클라이언트가 현재 인증 객체의 proNo 와 다른 proNo의 요청을 발생 시킨경우 인증 객체를 교체하기 위한 DAO
	 * @param masterVO
	 * @return
	 */
	public MasterVO selectAuthMemberForChange(MasterVO masterVO);
	
	
}
