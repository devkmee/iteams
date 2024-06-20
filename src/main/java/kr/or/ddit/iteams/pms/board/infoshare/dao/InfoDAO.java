package kr.or.ddit.iteams.pms.board.infoshare.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository               
public interface InfoDAO {
	
	//인터페이스 
	
	//게시글 전체목록 
	public List<MasterVO> InfoBoardList(MasterVO master);
	
	//게시글 전체 항목 갯수 조회
	public int selectTotalRecord(MasterVO master);
	
	//게시물 상세보기
    public MasterVO retrieveInfo(int number) ;
    
    //게시물 생성
    public int insertInfoBoard(MasterVO master);
    
    // 게시물 수정
 	public void updateInfo(MasterVO vo);
 	
 	// 게시물 삭제
 	public int deleteInfo(int number);
 	
 	//첨부파일
 	public List<AttachTotalVO> selectInfoBoardAttachList(int num);
 	
 	public int incrementCount(Map<String, String> map);
	
}
