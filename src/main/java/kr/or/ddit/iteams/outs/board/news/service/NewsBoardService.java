package kr.or.ddit.iteams.outs.board.news.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;

public interface NewsBoardService {
	
	//게시글 전체목록 조회 
	public List<MasterVO> NewsBoardList(MasterVO master);
	//게시글 상세보기
	public MasterVO retrieveNews(int number);
	//게시물 생성
	public ServiceResult insertNewsBoard(MasterVO master) throws Exception;
	//게시물 수정
	public void updateNews(MasterVO vo) throws Exception;
	//게시물 삭제
	public int deleteNews(MasterVO masterVO) throws Exception; 
}
	
	

