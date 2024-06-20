package kr.or.ddit.iteams.outs.board.notice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface NoticeBoardDAO {
	
	public int selectTotalRecord(MasterVO board);
	
	public List<MasterVO> selectBoardList(MasterVO board);
	
	public MasterVO selectBoard(MasterVO board);
	
	/**
	 * 조회수증가 메서드
	 * @param pMap
	 * @return
	 */
	public int incrementCount(Map<String, Object> pMap);
	
	public int insertBoard(MasterVO board);
	
	public int updateBoard(MasterVO board);
	
	public int deleteBoard(MasterVO board);

}
