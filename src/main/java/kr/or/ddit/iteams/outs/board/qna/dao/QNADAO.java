package kr.or.ddit.iteams.outs.board.qna.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface QNADAO {
	
	public int selectTotalRecord (MasterVO board);
	
	public List<MasterVO> selectQnAList (MasterVO board);
	
	/**
	 * 회원이 답변 조회 시 질문글 작성자 체크
	 * @param board
	 * @return
	 */
	public String selectCheckQMem (MasterVO board);
	
	/**
	 * 답변 작성용 GEN_BOARD, QNA_BOARD insert
	 * @param board : boNum, boParent, memId, boContent
	 * @return
	 * @throws Exception
	 */
	public int insertAnswer (MasterVO board) throws Exception;
	
	/**
	 * 답변 작성용 QNA_BOARD 질문글 상태 변경
	 * @param board : boParent, memId
	 * @return
	 */
	public int updateQForAnswer (MasterVO board);
	
	/**
	 * 질문 수정 or 삭제 시 답변 있는지 체크하는 용도
	 * @param board : boNum
	 * @return null = 답변없음 = 수정삭제 가능
	 */
	public MasterVO selectCheckAnswer (MasterVO board);
	
	public int updateAnswer (MasterVO board);

}
