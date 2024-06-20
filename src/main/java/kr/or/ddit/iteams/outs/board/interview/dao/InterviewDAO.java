package kr.or.ddit.iteams.outs.board.interview.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.vo.BoardVO;

@Repository
public interface InterviewDAO {
	public int insertBoard(BoardVO board);
	
	public int selectTotalRecord(MasterVO masterVO);
	
	public List<MasterVO> selectBoardList(MasterVO MasterVO);
	
	public BoardVO selectBoard(int boNum);
	
	public int updateBoard(BoardVO board);
	
	public int deleteBoard(int boNum);
	
	public int incrementCount(Map<String, Object> pMap);
}
