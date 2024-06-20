package kr.or.ddit.iteams.pms.board.request.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.annotations.TimeLine;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.PMSTotalVO;
import kr.or.ddit.iteams.pms.board.request.dao.RequestDAO;
import kr.or.ddit.iteams.pms.common.dao.PMSOthersDAO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RequestServiceImpl implements RequestService {

	@Inject
	private RequestDAO dao;
	
	@Inject
	private PMSOthersDAO othersDAO;
	
	@Inject
	private FTPServerFileManager manager;
	
	//게시물 전체목록
	@Override
	public List<MasterVO> RequestBoardList(MasterVO master) {
		master.setTotalRecord(dao.selectTotalRecord(master));
		List<MasterVO> boardList = dao.RequestBoardList(master);
		master.setDataList(boardList);
		return boardList;
	}

    //게시물 상세보기 
	@Override
	public MasterVO retrieveRequest(int number) {
		
	    MasterVO vo = dao.retrieveRequest(number);
	    if(vo == null) {
	    	throw new PKNotFoundException(number + "번 글이 없습니다.");
	    }
	    Map<String, Object> pMap = new HashMap<>();
	    pMap.put("boNum", number);
	    pMap.put("incType", "BO_HIT"); //replace text활용
	    othersDAO.incrementCount(pMap);
		return vo;
	}

	//게시물생성
	@Override
	@Transactional
	@TimeLine
	public ServiceResult insertRequestBoard(MasterVO master) throws Exception {
	    
		ServiceResult result = null;
		
		int rowcnt = dao.insertRequestBoard(master);
		
		if(rowcnt > 0) {
			manager.uploadFilesForPMSBoard(master);
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	//게시물수정
	@Override
	@Transactional
	@TimeLine
	public void updateRequest(MasterVO vo) throws Exception {
       manager.uploadFilesForPMSBoard(vo);
       dao.updateRequest(vo);
	}
	
    //게시물삭제
	@Transactional
	@TimeLine
	@Override
	public int deleteRequest(MasterVO masterVO) throws Exception {
		List<AttachTotalVO> attachList = dao.selectRequestBoardAttachList(masterVO.getBoNum());
		MasterVO saved = dao.retrieveRequest(Integer.parseInt(masterVO.getBoNum()));
		masterVO.setBoTitle(saved.getBoTitle());
		int cnt = dao.deleteRequest(masterVO);
		MasterVO master = new MasterVO();
		masterVO.setAttachList(attachList);
		manager.deleteFilesForPMS(master);
		return cnt;
	}

	/*@Override
	public AttatchVO download(int attNo) {
		AttatchVO atch = attatchDAO.selectAttatch(attNo);
		if(atch==null)
			throw new PKNotFoundException(attNo+"파일의 메타데이터가 없음.");
		return atch;
	}*/

}
