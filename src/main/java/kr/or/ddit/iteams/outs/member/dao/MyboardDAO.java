package kr.or.ddit.iteams.outs.member.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface MyboardDAO {
	
	public int selectTotalRecord(MasterVO base);
	
	/**
	 * 나의 게시글 목록
	 * @param board
	 * @return
	 */
	public List<MasterVO> selectMyBoardList(MasterVO board);

}
