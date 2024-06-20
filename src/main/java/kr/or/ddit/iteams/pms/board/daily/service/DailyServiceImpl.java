 package kr.or.ddit.iteams.pms.board.daily.service;

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
import kr.or.ddit.iteams.pms.board.daily.dao.DailyDAO;
import kr.or.ddit.iteams.pms.common.dao.PMSOthersDAO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DailyServiceImpl implements DailyService {
	
	@Inject
	private DailyDAO dao;
	
	@Inject
	private PMSOthersDAO otherDAO;
	
	@Inject
	private FTPServerFileManager manager;
	
	//게시글 전체목록  -> 목록조회
	@Override
	public List<MasterVO> DailyBoardList(MasterVO master){
		master.setTotalRecord(dao.selectTotalRecord(master));
		List<MasterVO> projectList = dao.DailyBoardList(master);
		master.setDataList(projectList);
		return projectList;
	}
	
	//게시글 상세목록 
	@Override
	public MasterVO retrieveDaily(int number) {
		MasterVO vo = dao.retrieveDaily(number);
		if (vo == null) {
			throw new PKNotFoundException(number +"번 글이 없습니다."); 
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("incType", "BO_HIT");
		map.put("boNum", number);
		otherDAO.incrementCount(map);
		return vo;
	}
	 
	// 게시물 삭제
	@Override
	@Transactional
	@TimeLine
	public int deleteDaily(MasterVO masterVO) throws Exception {
		List<AttachTotalVO> attachList = dao.selectDailyBoardAttachList(Integer.parseInt(masterVO.getBoNum()));
		MasterVO saved = dao.retrieveDaily(Integer.parseInt(masterVO.getBoNum()));
		masterVO.setBoTitle(saved.getBoTitle());
		int cnt = dao.deleteDaily(masterVO);
		MasterVO master = new MasterVO();
		masterVO.setAttachList(attachList);
		manager.deleteFilesForPMS(master);
		return cnt; 
	}
	
	// 게시물 수정
	@Override
	@Transactional
	@TimeLine
	public void updateDaily(MasterVO vo) throws Exception {
		vo.setWorkNum("");
		manager.uploadFilesForPMSBoard(vo);
		 dao.updateDaily(vo);
	}

	
	// 게시물생성 
	@Override
	@Transactional
	@TimeLine
	public ServiceResult insertDailyBoard(MasterVO master) throws Exception {
       
		ServiceResult result = null;
		
		int rowcnt = dao.insertDailyBoard(master);
		
		if(rowcnt > 0) {
			manager.uploadFilesForPMSBoard(master);
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
}
	

	