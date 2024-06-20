package kr.or.ddit.iteams.outs.company.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface CompanyDAO {
	
	public int selectTotalRecord (MasterVO compony);
	
	/**
	 * 기업정보 메인화면에서 기업 순위
	 * @param company
	 * @return
	 */
	public List<MasterVO> selectCompanyRank (MasterVO company);
	
	public List<MasterVO> selectComponyList (MasterVO compony);
	
	public MasterVO selectCompony (MasterVO compony);
	
	
	public int selectReviewTotalRecord (MasterVO compony);
	
	public List<MasterVO> selectReviewList (MasterVO compony);
	
	/**
	 * 리뷰 작성 시 로그인 유저가 특정 기업의 프로젝트에 참여했었는지 체크
	 * @param member : memId, cliId
	 * @return
	 */
	public String selectCheckReviewAuth (MasterVO member);
	
	/**
	 * 리뷰 작성 시 중복리뷰 방지를 위한 로그인 유저의 특정 기업 리뷰 체크
	 * 리뷰 수정/삭제 시 로그인 유저의 리뷰 기록 체크
	 * @param member : memId, cliId
	 * @return
	 */
	public MasterVO selectCheckReview (MasterVO member);
	
	public int insertReview (MasterVO review);
	
	public int updateReview (MasterVO review);
	
	public int deleteReview (MasterVO review);
	
	public List<String> selectRecComponyList(MasterVO masterVO);
}
