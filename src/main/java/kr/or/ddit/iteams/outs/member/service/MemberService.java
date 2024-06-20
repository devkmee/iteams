package kr.or.ddit.iteams.outs.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.BaseVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.MemberTotalVO;
import kr.or.ddit.iteams.common.vo.OutsTotalVO;
import kr.or.ddit.iteams.outs.login.vo.JoinDevRequestVO;

public interface MemberService {
	
	/**
	 * 관리자용 전체 회원 조회
	 * @param base
	 */
	public void retrieveMemberList(MasterVO base);

	public MasterVO selectDevInfo(String memId);
	
	public void selectDevPro(MasterVO vo);
	
	public MasterVO selectClientInfo(String memId);
	
	public MasterVO selectClientPro(String memId);
	
	public void selectInviteListForDev(MasterVO master);
	
	public void selectOngoingProject(MasterVO vo);
	
	public void selectEndProject(MasterVO vo);
	
	public ServiceResult updatePass(String memPass, String dbPass);

	public ServiceResult deletePass(String memPass, String dbPass);
	
	public MasterVO selectDevAtt(String memId);
	
	public ServiceResult updateDev(JoinDevRequestVO vo) throws Exception;
	
	public void selectBoardForClient(MasterVO vo); 
	
	public ServiceResult updateClient(JoinDevRequestVO vo) throws Exception;
	
	public ServiceResult deleteMember(String memId);
	
}
