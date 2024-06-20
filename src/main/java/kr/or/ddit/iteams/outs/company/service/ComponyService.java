package kr.or.ddit.iteams.outs.company.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import kr.or.ddit.iteams.common.vo.MasterVO;

public interface ComponyService {
	
	/**
	 * 기업정보 메인화면에서 기업 순위
	 * @param company
	 * @return
	 */
	public void selectRankList (MasterVO company);
	
	public void selectComponyList (MasterVO compony);
	
	/**
	 * 로그인 유저가 조회중인 기업에 리뷰작성 권한이 있는지 체크
	 * @param member : memId, cliId
	 */
	public String selectCheckReviewInsertAuth (MasterVO member);
	
	/**
	 * 로그인 유저의 기업리뷰 수정 삭제 권한 체크
	 * @param member
	 * @return
	 */
	public String selectCheckUpDelAuth (MasterVO member);
	
	public void selectCompony (MasterVO compony) throws IllegalAccessException, InvocationTargetException ;
	
	public void selectReviewList (MasterVO company);
	
	public void insertReview (MasterVO review);
	
	public void updateReview (MasterVO review);
	
	public void deleteReview (MasterVO review);

}
