package kr.or.ddit.iteams.outs.company.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.company.dao.CompanyDAO;

@Service
public class ComponyServiceImpl implements ComponyService{
	
	@Inject
	private CompanyDAO dao;
	
	@Override
	public void selectRankList(MasterVO company) {
		List<MasterVO> rankList = dao.selectCompanyRank(company);
		company.setDataList(rankList);
	}


	@Override
	public void selectComponyList(MasterVO compony) {
		int totalRecord = dao.selectTotalRecord(compony);
		compony.setTotalRecord(totalRecord);
		
		List<MasterVO> boardList = dao.selectComponyList(compony);
		compony.setDataList(boardList);
	}
	
	@Override
	public String selectCheckReviewInsertAuth(MasterVO member) {
		
		String reviewAuth = null;
		String endPro = dao.selectCheckReviewAuth(member);
		MasterVO saved = dao.selectCheckReview(member);
		if(endPro != null && saved == null) {
			reviewAuth = "Y";
		}
		return reviewAuth;
	}
	
	@Override
	public String selectCheckUpDelAuth(MasterVO member) {
		
		String reviewUpDelAuth = null;
		MasterVO saved = dao.selectCheckReview(member);
		if(saved != null) {
			reviewUpDelAuth = "Y";
		}
		return reviewUpDelAuth;
	}

	@Override
	public void selectCompony(MasterVO compony) throws IllegalAccessException, InvocationTargetException {
		MasterVO saved = dao.selectCompony(compony);
		if(saved != null) {
			BeanUtils.copyProperties(compony, dao.selectCompony(compony));
		}else throw new PKNotFoundException(compony.getCliId() + "기업의 정보를 찾을 수 없습니다");
		
		int reviewCnt = dao.selectReviewTotalRecord(compony);
		compony.setRowCnt(reviewCnt);
	}

	@Override
	public void selectReviewList(MasterVO company) {
		int totalRecord = dao.selectReviewTotalRecord(company);
		company.setTotalRecord(totalRecord);
		company.setRowCnt(totalRecord);
		
		List<MasterVO> reviewList = dao.selectReviewList(company);
		company.setDataList(reviewList);
	}

	@Override
	public void insertReview(MasterVO review) {
		ServiceResult result = null;
		int cnt = dao.insertReview(review);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		review.setResult(result);
	}

	@Override
	public void updateReview(MasterVO review) {
		ServiceResult result = null;
		MasterVO saved = dao.selectCheckReview(review);
		if(saved != null) {
			int cnt = dao.updateReview(review);
			if(cnt > 0) {
				result = ServiceResult.OK;
			}else{
				result = ServiceResult.FAILED;
			}
			review.setResult(result);
		}else {
			throw new PKNotFoundException(review.getRevNo() + "번 리뷰를 찾을 수 없습니다");
		}
	}

	@Override
	public void deleteReview(MasterVO review) {
		ServiceResult result = null;
		MasterVO saved = dao.selectCheckReview(review);
		if(saved != null) {
			int cnt = dao.deleteReview(review);
			if(cnt > 0) {
				result = ServiceResult.OK;
			}else{
				result = ServiceResult.FAILED;
			}
			review.setResult(result);
		}else {
			throw new PKNotFoundException(review.getRevNo() + "번 리뷰를 찾을 수 없습니다");
		}
	}


	



}
