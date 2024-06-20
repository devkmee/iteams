package kr.or.ddit.iteams.outs.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.BaseVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.MemberTotalVO;
import kr.or.ddit.iteams.common.vo.OutsTotalVO;
import kr.or.ddit.iteams.outs.login.vo.JoinDevRequestVO;
import kr.or.ddit.vo.MemberVO;

/**
 * 회원 관리를 위한 Persistence Layer
 *
 */
@Repository
public interface MemberDAO {
	
	public MemberVO selectMemberForAuth(String memId);
	
	
	
	/**
	 * 전체 레코드수 조회
	 * @param BaseVO 단순검색용 searchVO + 페이징
	 * @return
	 */
	public int selectTotalRecord(MasterVO base);
	
	
	/**
	 * 회원 목록 조회
	 * @param MemberTotalVO
	 * @return 조회 데이터가 없다면, size()==0
	 */
	public List<MasterVO> selectMemberList(MasterVO base);
	
	

	/**
	 * 배치를 이용한 실제 회원 탈퇴 처리 => 자식 테이블 cascade로 삭제
	 * 업데이트나 커밋시 충돌나면 반드시 살려둘것
	 * @param 
	 */
	public int deleteRealMember();
	
	/**
	 * 탈퇴 처리 사전에 탈퇴하는 회원 정보 조회
	 * @return
	 */
	public List<MasterVO> selectQuitMemberList();
	
	
	public MasterVO selectDevInfo(String memId);
	
	public List<MasterVO> selectDevPro(MasterVO vo);
	
	public MasterVO selectClientInfo(String memId);
	
	public MasterVO selectClientPro(String memId);
	
	public int selectAPPTotalRecord(MasterVO applyPro);
	
	public int selectInviteTotalRecord (MasterVO master);
	
	public List<MasterVO> selectInviteListForDev(MasterVO master);
	
	public int selectOngoingTotalRecord (MasterVO ongoing);
	
	public List<MasterVO> selectOngoingProject(MasterVO vo);
	
	public int selectEndTotalRecord (MasterVO end);
	
	public List<MasterVO> selectEndProject(MasterVO vo);
	
	public MasterVO selectDevAtt(String memId); 
	
	public int updateDev1(JoinDevRequestVO vo); 

	public int updateDev2(JoinDevRequestVO vo); 
	
	public int updateClient(JoinDevRequestVO vo);
	
	public int updateClientMember(JoinDevRequestVO vo);
	
	public int deleteAtt(JoinDevRequestVO vo); 
	
	
	public int deleteMember(String memId);
	
	public List<String> selectMemIdList(String memId);
	
	
	// 클라이언트
	public int selectBOARDTotalRecord(MasterVO applyPro);
	
	public List<MasterVO> selectApplyListForClient(MasterVO board);
	
}



