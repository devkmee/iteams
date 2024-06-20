package kr.or.ddit.iteams.outs.board.codebook.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface CodeBookDAO {
	
	//게시판 전체목록 조회
	public List<MasterVO> CodeBoardList(MasterVO master);
	//게시물전체항목갯수조회
	public int selectTotalRecord(MasterVO master);
	//게시물 상세보기 
	public MasterVO retrieveCode(int number);
	//조회수
	public int incrementCount(Map<String, Object> pMap);

}
