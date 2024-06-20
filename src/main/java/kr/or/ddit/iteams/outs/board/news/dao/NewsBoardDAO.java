package kr.or.ddit.iteams.outs.board.news.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface NewsBoardDAO {
	
	//게시글 전체목록 조회 
	public List<MasterVO> NewsBoardList(MasterVO master);
	//게시물전체항목갯수조회
	public int selectTotalRecord(MasterVO master);
	//게시물상세보기
	public MasterVO retrieveNews(int number);
	//게시물 생성
	public int insertNewsBoard(MasterVO master);
	//게시물 수정
	public void updateNews(MasterVO vo);
	//게시물 삭제
	public int deleteNews(MasterVO masterVO);
	//조회수
	public int incrementCount(Map<String, Object> pMap);
	//첨부파일
	//public List<AttachTotalVO> selectNewsBoardAttachList(int parseInt);

}
