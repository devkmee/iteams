package kr.or.ddit.iteams.outs.hire.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.OutsTotalVO;

@Repository
public interface PublicHireDAO {
	
	/**
	 * 공개된 프로필 전체 레코드 수
	 * @param master
	 * @return
	 */
	public int selectProfileTotalRecord (MasterVO master);
	
	/**
	 * 공개된 프로필 목록 조회
	 * @param master : 페이징 + 검색
	 * @return
	 */
	public List<MasterVO> selectProfileList (MasterVO master);
	
	/**
	 * 1명의 개발자 프로필 상세조회
	 * @param devId
	 * @return
	 */
	public MasterVO selectProfile (MasterVO devId);
	
	public int selectScrapDevNy(MasterVO masterVO);
	
	/**
	 * 개발자 초대를 위한 특정 클라이언트의 진행 프로젝트 목록 조회
	 * @param masterVO
	 * @return
	 */
	public List<MasterVO> selectProjectListForInvite(MasterVO masterVO);
	
	/**
	 * 초대 전 참여 가능한 개발자인지 검증 : 존재여부, 상태
	 * @param devId
	 * @return null = 참여가능
	 */
	public MasterVO selectCheckDev(String devId);
	
	/**
	 * 초대 전 참여 가능한 프로젝트인지 검증 : 존재여부, 상태
	 * @param proNo
	 * @return null = 참여불가
	 */
	public MasterVO selectCheckPro(String proNo);
	
	/**
	 * 클라이언트가 진행 프로젝트에 개발자 초대
	 * @param invite : proNo + devId
	 * @return
	 */
	public int insertInviteDev (MasterVO invite);
	
	/**
	 * 특정 개발자의 초대목록 전체 레코드 수
	 * @param master
	 * @return
	 */
	public int selectInviteTotalRecord (MasterVO master);
	
	/**
	 * 특정 개발자의 프로젝트 초대 목록
	 * @param memId
	 * @return
	 */
	public List<MasterVO> selectInviteListForDev(MasterVO master);
	
	/**
	 * 특정 클라이언트의 프로젝트 초대 목록
	 * @param master
	 * @return
	 */
	public List<MasterVO> selectInviteListForClient(MasterVO master);
	
	/**
	 * 1건의 초대  조회
	 * @param master inviteNo
	 * @return
	 */
	public MasterVO selectInvite(MasterVO master);
	
	/**
	 * 개발자가 초대 수락
	 * @param invite : inviteNo
	 * @return
	 */
	public int updateAcceptInvitation (MasterVO invite);
	
	/**
	 * 초대 수락 시 개발자 프로젝트에 멤버로 추가
	 * @param proMem
	 * @return
	 */
	public int insertToProMember(MasterVO proMem);
	
	/**
	 * 개발자가 초대 거절
	 * @param invite : inviteNo
	 * @return
	 */
	public int updateRefuseInvitation (MasterVO invite);
	
	/**
	 * 스크랩 추가/삭제를 위한 1건의 스크랩 기록 조회
	 * @param scrab : clientId, devId
	 * @return
	 */
	public MasterVO selectCheckProfile (MasterVO scrab);
	
	/**
	 * 프로필 스크랩 추가
	 * @param scrab : clientId, devId
	 * @return
	 */
	public int insertProfileScrab (MasterVO scrab);
	
	/**
	 * 
	 * @param scrab
	 * @return
	 */
	public int profileScrabListTotalRecord (MasterVO scrab);
	
	/**
	 * 특정 클라이언트의 스크랩 프로필 목록
	 * @param scrab : clientId + 페이징
	 * @return
	 */
	public List<MasterVO> selectProfileScrabList(MasterVO scrab);
	
	/**
	 * 스크랩한 프로필 삭제
	 * @param scrab : clientId, devId
	 * @return
	 */
	public int deleteProfileScrab (MasterVO scrab);
	
	public int updateReapply(MasterVO masterVO);
	
	public MasterVO selectProject(MasterVO masterVO);
}
