package kr.or.ddit.iteams.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface TotalSearchDAO {
	
	public int selectTotalRecordsSearchResult(MasterVO masterVO);
	
	/**
	 * 통합 검색 결과 리스트 조회
	 * @return
	 */
	public List<MasterVO> selectTotalSearchList(MasterVO masterVO);
}
