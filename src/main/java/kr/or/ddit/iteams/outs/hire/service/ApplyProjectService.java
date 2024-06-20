package kr.or.ddit.iteams.outs.hire.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.OutsTotalVO;
import kr.or.ddit.iteams.outs.hire.dao.ApplyProjectDAO;

public interface ApplyProjectService {
	
	/**
	 * 프로젝트 지원 등록 + 취소
	 * function='등록/취소', OK=성공, FAILED=실패
	 * @param applyPro : PRO_APP + boNum
	 */
	public void insertDeleteApplyPro(MasterVO applyPro);
	
	/**
	 * 특정 클라이언트의 공고 프로젝트 목록
	 * @param applyPro
	 * @return
	 */
	public void selectProBoardListForClient(OutsTotalVO board);
	
	/**
	 * 특정 프로젝트의 지원자 목록
	 * @param applyPro
	 */
	public void selectApplyListOneProject(OutsTotalVO applyPro);
	
	/**
	 * 특정 개발자의 지원 프로젝트 목록
	 * @param board
	 */
	public void selectApplyListForDev(MasterVO board);
	
	/**
	 * 특정 지원자의 프로필 조회
	 * @param applyPro : appId
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void selectDevProfile(MasterVO applyPro) throws IllegalAccessException, InvocationTargetException;
	
	/**
	 * 지원자 채용
	 * OK=성공, FAILED=실패, DUPLICATED=이미결과 통보
	 * @param applyPro : appNo
	 */
	public void updateApplyHire(MasterVO applyPro) throws IllegalAccessException, InvocationTargetException;
	
	/**
	 * 지원자 반려
	 * OK=성공, FAILED=실패, DUPLICATED=이미 결과 통보
	 * @param applyPro : appNo
	 */
	public void updateApplyReturn(MasterVO applyPro) throws IllegalAccessException, InvocationTargetException;
	
	/**
	 * 면접 제안
	 * @return
	 */
	public void updateAppMeeting(MasterVO masterVO);

}
