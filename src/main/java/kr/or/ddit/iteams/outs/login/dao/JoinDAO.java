package kr.or.ddit.iteams.outs.login.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MemberTotalVO;
import kr.or.ddit.iteams.outs.login.vo.JoinDevRequestVO;
import kr.or.ddit.iteams.outs.login.vo.TechCodeVO;

@Repository
public interface JoinDAO {
	public MemberTotalVO selectMemberForAuth(String memId);
	/**
	 * 회원 추가
	 * @param member
	 * @return > 0 성공
	 */
	public int insertMember(JoinDevRequestVO member);
	
	public int insertProfileImage(JoinDevRequestVO member);
	
	/**
	 * Dev 회원 추가
	 * @param member
	 * @return
	 */
	public int insertDevMember(JoinDevRequestVO member);
	
	/**
	 * Client 회원 추가
	 * @param member
	 * @return
	 */
	public int insertClientMember(JoinDevRequestVO member);
	
	/**
	 * 기술스택 추가
	 * @param params
	 * @return
	 */
	public int insertDevTech(List<Map<String, String>> params);
	
	/**
	 * 중복아이디 체크
	 * @param member
	 * @return
	 */

	public int checkId (MemberTotalVO member);
	
	/**
	 * 아이디 체크
	 * @param memId
	 * @return
	 */
	public int idCheck(String memId);
	
	/**
	 * 기술스택 자동완성
	 * @param keyword
	 * @return
	 */
	public List<TechCodeVO> findTechCodeByKeyword(String keyword);
	
	/**
	 * 비밀번호 변경
	 * @param params
	 * @return
	 */
	public int changePassword(JoinDevRequestVO params);
	
}
