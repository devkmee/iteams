package kr.or.ddit.iteams.outs.hire.service;

import java.lang.reflect.InvocationTargetException;

import kr.or.ddit.iteams.common.vo.MasterVO;

public interface PublicHireService {
	
	/**
	 * 공개된 프로필 목록 조회
	 * @param master : 페이징 + 검색
	 */
	public void selectProfileList (MasterVO master);
	
	/**
	 * 공개된 프로필 상세조회
	 * @param master : devId
	 */
	public void selectProfile (MasterVO master) throws IllegalAccessException, InvocationTargetException;
	
	/**
	 * 개발자 초대를 위한 특정 클라이언트의 진행 프로젝트 목록 조회
	 * @param master : memId
	 */
	public void selectProjectListForInvite (MasterVO master);
	
	/**
	 * 클라이언트가 진행 프로젝트에 개발자 초대
	 * OK:성공, FAILED:실패, NONEPRO:참여불가 프로젝트, NONEDEV:참여불가 개발자
	 * @param master : proNo + devId
	 */
	public void insertInviteDev (MasterVO master);
	
	/**
	 * 특정 개발자의 프로젝트 초대 목록
	 * @param master : memId
	 */
	public void selectInviteListForDev (MasterVO master);
	
	/**
	 * 특정 클라이언트의 프로젝트 초대 목록
	 * @param master : memId
	 */
	public void selectInviteListForClient (MasterVO master);
	
	/**
	 * 특정 개발자의 초대 수락
	 * 초대 테이블 update + pms멤버테이블 insert
	 * OK:성공, FAILED:실패, DUPLICATED: 참여중인 프로젝트 있음
	 * @param invite
	 */
	public void updateAcceptInvitation (MasterVO invite);
	
	/**
	 * 특정 개발자의 초대 거절
	 * OK:성공, FAILED:실패
	 * @param invite
	 */
	public void updateRefuseInvitation (MasterVO invite);
	
	/**
	 * 스크랩 기록이 없을 경우 삭제, 있을 경우 추가
	 * function='추가/삭제', OK=성공, FAILED=실패
	 * @param scrab: clientId, devId
	 */
	public void ProfileScrab (MasterVO scrab);
	
	/**
	 * 특정 클라이언트의 스크랩 프로필 목록
	 * @param scrab
	 */
	public void selectProfileScrabList (MasterVO scrab);
	
	/**
	 * 특정 클라이언트의 스크랩 목록에서 삭제용
	 * @param scrab
	 */
	public void onlyDelectScrab (MasterVO scrab);
	
	public void updateReapply(MasterVO masterVO);

}
