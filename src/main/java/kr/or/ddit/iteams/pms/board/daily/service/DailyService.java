 package kr.or.ddit.iteams.pms.board.daily.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;

//비즈니스로직 -> DB이외의 작업 
public interface DailyService {
	
  // 게시글 전체목록 
  public List<MasterVO> DailyBoardList(MasterVO master);

  // 게시물 상세보기
  public MasterVO retrieveDaily(int number);

  // 게시물 삭제
  public int deleteDaily(MasterVO number) throws Exception;

  // 게시물 수정
  public void updateDaily(MasterVO vo) throws Exception;
  
  //게시물 생성  
  public ServiceResult insertDailyBoard(MasterVO master) throws Exception;




}