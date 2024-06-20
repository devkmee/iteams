package kr.or.ddit.iteams.outs.board.free.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

/**
 * FreeBoard 테이블을 대상으로 한 CRUD
 *
 */
@Repository
public interface FreeBoardDAO {
	public int insertBoard(MasterVO board);
	
	public int selectTotalRecord(MasterVO masterVO);
	
	public List<MasterVO> selectBoardList(MasterVO MasterVO);
	
	public MasterVO selectBoard(MasterVO boNum);
	
	public int updateBoard(MasterVO board);
	public int deleteBoard(String boNum);
	
	public int incrementCount(Map<String, Object> pMap);
	
}





