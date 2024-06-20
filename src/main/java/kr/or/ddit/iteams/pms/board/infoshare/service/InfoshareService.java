 package kr.or.ddit.iteams.pms.board.infoshare.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;

//비즈니스로직 -> DB이외의 작업 
public interface InfoshareService {
	
	//게시글 전체목록 
	public List<MasterVO> InfoBoardList(MasterVO master);
	
	//게시물 상세보기
    public MasterVO retrieveInfo(int number);
    
    //게시물 생성 
    public ServiceResult insertInfoBoard(MasterVO master) throws Exception;

    // 게시물 수정
    public void updateInfo(MasterVO vo) throws Exception;

    // 게시물 삭제
    public int deleteInfo(MasterVO number) throws Exception;




}
