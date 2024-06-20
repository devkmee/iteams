package kr.or.ddit.iteams.pms.board.request.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;

public interface RequestService {

	
	    //게시물 전체목록 
		public List<MasterVO> RequestBoardList(MasterVO master);
		//게시물 상세보기
		public MasterVO retrieveRequest(int number);
		//게시물 생성 
		public ServiceResult insertRequestBoard(MasterVO master) throws Exception;
		//게시물 수정
	 	public void updateRequest(MasterVO vo) throws Exception;
		//게시물 삭제
	 	public int deleteRequest(MasterVO number) throws Exception;
	 	//첨부파일다운로드
	 	//public AttatchVO download(int attNo);
}
