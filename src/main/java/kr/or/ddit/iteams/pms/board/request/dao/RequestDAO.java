package kr.or.ddit.iteams.pms.board.request.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface RequestDAO {
	
	//게시물 전체목록 
	public List<MasterVO> RequestBoardList(MasterVO master);
	//게시글 전체 항목 갯수 조회 
	public int selectTotalRecord(MasterVO master);
	//게시물 상세보기
	public MasterVO retrieveRequest(int number);
	//게시물 생성 
	public int insertRequestBoard(MasterVO master);
	//게시물 수정
 	public void updateRequest(MasterVO vo);
	//게시물 삭제
 	public int deleteRequest(MasterVO number);
	//첨부파일 
	public List<AttachTotalVO> selectRequestBoardAttachList(String num);
	//조회수
	//public int incrementCount(Map<String, Object> pMap);
	

}
