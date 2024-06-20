package kr.or.ddit.iteams.outs.board.notice.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.dao.AttachDAO;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.notice.dao.NoticeBoardDAO;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService{

	@Inject
	private NoticeBoardDAO dao;
	
	@Inject
	private FTPServerFileManager file;
	
	@Inject
	private AttachDAO attachDao;

	@Override
	public void selectBoardList(MasterVO board) {
		int totalRecord = dao.selectTotalRecord(board);
		board.setTotalRecord(totalRecord);
		
		List<MasterVO> boardList = dao.selectBoardList(board);
		board.setDataList(boardList);
	}

	@Override
	public void selectBoard(MasterVO board) throws IllegalAccessException, InvocationTargetException {
		MasterVO saved = dao.selectBoard(board);
		if(saved != null) {
			Map<String, Object> pMap = new HashMap<>();
			pMap.put("boNum", board.getBoNum());
			pMap.put("incType", "BO_HIT");
			dao.incrementCount(pMap);
			BeanUtils.copyProperties(board, saved);
			board.setAttachList(attachDao.selectAttachListForGenBoard(board));
		}else {
			throw new PKNotFoundException(board.getBoNum() + "글을 찾을 수 없습니다");
		}
		
	}

	@Transactional
	@Override
	public void insertBoard(MasterVO board) throws Exception {
		ServiceResult result = null;
		int cnt = dao.insertBoard(board);
		if(cnt > 0) {
			file.uploadFilesForOuts(board);
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		}
		board.setResult(result);
	}

	@Transactional
	@Override
	public void updateBoard(MasterVO board) throws Exception {
		ServiceResult result = null;
		MasterVO saved = dao.selectBoard(board);
		if(saved != null) {
			int cnt = dao.updateBoard(board);
			if(cnt > 0) {
				file.uploadFilesForOuts(board);
				result = ServiceResult.OK;
			}else{
				result = ServiceResult.FAILED;
			}
			board.setResult(result);
		}else {
			throw new PKNotFoundException(board.getBoNum() + "번 글을 찾을 수 없습니다");
		}
	}

	@Override
	public void deleteBoard(MasterVO board) {
		ServiceResult result = null;
		MasterVO saved = dao.selectBoard(board);
		if(saved != null) {
			int cnt = dao.deleteBoard(board);
			if(cnt > 0) {
				result = ServiceResult.OK;
			}else{
				result = ServiceResult.FAILED;
			}
			board.setResult(result);
		}else {
			throw new PKNotFoundException(board.getBoNum() + "번 글을 찾을 수 없습니다");
		}
	}


	
	
}
