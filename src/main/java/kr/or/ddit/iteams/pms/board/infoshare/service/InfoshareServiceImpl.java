 package kr.or.ddit.iteams.pms.board.infoshare.service;

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
import kr.or.ddit.iteams.pms.board.infoshare.dao.InfoDAO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InfoshareServiceImpl implements InfoshareService {

	@Inject
	private InfoDAO dao;
	
	@Inject
	private FTPServerFileManager manager;

	//게시글 전체목록 
	@Override
	public List<MasterVO> InfoBoardList(MasterVO master) {
		master.setTotalRecord(dao.selectTotalRecord(master));
		List<MasterVO> projectList = dao.InfoBoardList(master);
		master.setDataList(projectList);
		return projectList;
	}

	// 게시물 상세보기
	@Override
	public MasterVO retrieveInfo(int number) {
		MasterVO vo = dao.retrieveInfo(number);
		Map<String, String> pMap = new HashMap<>();
		pMap.put("boNum", String.valueOf(number));
		pMap.put("incType", "BO_HIT"); // replace text 활용
		dao.incrementCount(pMap);
		if(vo == null) {
			throw new PKNotFoundException(number + "번 글이 없습니다.");
		}
		return vo;
	}

	//게시물생성
	@Override
	@Transactional
	@TimeLine
	public ServiceResult insertInfoBoard(MasterVO master) throws Exception {

		ServiceResult result = null;
		
		int rowcnt = dao.insertInfoBoard(master);
		
		if(rowcnt > 0) {
			manager.uploadFilesForPMSBoard(master);
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	//게시물수정
	@Transactional
	@TimeLine
	@Override
	public void updateInfo(MasterVO vo) throws Exception {
        manager.uploadFilesForPMSBoard(vo);
        dao.updateInfo(vo);
	}
    
	//게시물삭제
	@Transactional
	@TimeLine
	@Override
	public int deleteInfo(MasterVO number) throws Exception {
		int boNum = Integer.parseInt(number.getBoNum());
		List<AttachTotalVO> attachList = dao.selectInfoBoardAttachList(boNum);
		MasterVO saved = dao.retrieveInfo(boNum);
		number.setBoTitle(saved.getBoTitle());
		int cnt = dao.deleteInfo(boNum);
		MasterVO master = new MasterVO();
		master.setAttachList(attachList);
		manager.deleteFilesForPMS(master);
		return cnt;
	}
}
