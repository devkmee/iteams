package kr.or.ddit.iteams.outs.blacklist.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface BlacklistDAO {
	
	public int selectTotalRecord(MasterVO base);
	
	public List<MasterVO> selectBlackList(MasterVO master);
	
	/**
	 * 블랙리스트 해제
	 * @param member : blackNo, memId(admin)
	 * @return
	 */
	public int updateBlackToMem (MasterVO member);
	
	/**
	 * 블랙리스트 해제 전 pk체크
	 * @param member
	 * @return
	 */
	public MasterVO selectCheckBlack (MasterVO member);
	
	/**
	 * 블랙리스트 등록
	 * @param member
	 * @return
	 */
	public int insertMemToBlack (MasterVO member);
	
	/**
	 * 블랙리스트 등록 후 게시글 삭제
	 * @param board
	 * @return
	 */
	public int updateRepGen (MasterVO board);
	
	public int selectREPTotal(MasterVO base);
	
	/**
	 * 신고글 목록
	 * @param board
	 * @return
	 */
	public List<MasterVO> selectRepBoardList(MasterVO board);
	
	/**
	 * 신고수 초기화 전 pk체크
	 * @param board
	 * @return
	 */
	public String selectCheckGen (MasterVO board);

	/**
	 * 블랙리스트 대상이 아닌 신고글 신고수 초기화
	 * @param board
	 * @return
	 */
	public int updateClearGen (MasterVO board);
	

}
