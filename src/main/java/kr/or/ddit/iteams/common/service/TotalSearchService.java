package kr.or.ddit.iteams.common.service;

import java.util.List;

import kr.or.ddit.iteams.common.vo.MasterVO;

public interface TotalSearchService {
	/**
	 * 통합 검색 결과 리스트 조회
	 * @return
	 */
	public void selectTotalSearchList(MasterVO masterVO);
}
