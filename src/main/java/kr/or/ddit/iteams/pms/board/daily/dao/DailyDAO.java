package kr.or.ddit.iteams.pms.board.daily.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface DailyDAO {
	

	// 게시글 전체목록 
	public List<MasterVO> DailyBoardList(MasterVO master);

	// 게시물전체항목갯수조회
	public int selectTotalRecord(MasterVO master);
	
	// 게시물 상세보기
	public MasterVO retrieveDaily(int number);
		
	// 게시물 삭제
	public int deleteDaily(MasterVO number);

	// 게시물 수정
	public void updateDaily(MasterVO vo);
	
	// 게시물 생성  
	public int insertDailyBoard(MasterVO master);
	
	public List<AttachTotalVO> selectDailyBoardAttachList(int num);
}
