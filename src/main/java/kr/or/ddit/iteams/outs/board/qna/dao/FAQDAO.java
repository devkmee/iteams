package kr.or.ddit.iteams.outs.board.qna.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface FAQDAO {
	
	public int selectTotalRecord (MasterVO board);
	
	public List<MasterVO> selectFAQList (MasterVO board);
	
	public MasterVO selectFAQ (MasterVO board);
	
	public int insertFAQ (MasterVO board);
	
	/**
	 * 글 수정용 GEN_BOARD 테이블 update
	 * @param board
	 * @return
	 */
	public int updateGenboard (MasterVO board);
	
	/**
	 * 글 수정용  QNA_BOARD 테이블 update
	 * @param board
	 * @return
	 */
	public int updateQnaboard (MasterVO board);
	
	public int deleteFAQ (MasterVO board);
	
	/**
	 * 조회수증가 메서드
	 * @param pMap
	 * @return
	 */
	public int incrementCount(Map<String, Object> pMap);

}
