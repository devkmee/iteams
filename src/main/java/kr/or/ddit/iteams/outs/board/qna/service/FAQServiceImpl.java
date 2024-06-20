package kr.or.ddit.iteams.outs.board.qna.service;

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
import kr.or.ddit.iteams.outs.board.qna.dao.FAQDAO;

@Service
public class FAQServiceImpl implements FAQService {
	
	@Inject
	private FAQDAO dao;
	
	@Inject
	private FTPServerFileManager upload;
	
	@Inject
	private AttachDAO attachDao;

	@Override
	public void selectFAQList(MasterVO board) {
		board.setBoCode("FQ");
		int totalRecord = dao.selectTotalRecord(board);
		board.setTotalRecord(totalRecord);
		
		List<MasterVO> boardList = dao.selectFAQList(board);
		board.setDataList(boardList);
	}

	@Override
	public void selectFAQ(MasterVO board) throws IllegalAccessException, InvocationTargetException {
		MasterVO saved = dao.selectFAQ(board);
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
	public void insertFAQ(MasterVO board) throws Exception {
		ServiceResult result = null;
		board.setBoCode("FQ");
		int cnt = dao.insertFAQ(board);
		if(cnt > 0) {
			upload.uploadFilesForOuts(board);
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		board.setResult(result);
	}

	@Transactional
	@Override
	public void updateFAQ(MasterVO board) throws Exception {
		ServiceResult result = null;
		MasterVO saved = dao.selectFAQ(board);
		if(saved != null) {
			int cntGen = dao.updateGenboard(board);
			int cntQna = dao.updateQnaboard(board);
			if(cntGen > 0 && cntQna > 0) {
				upload.uploadFilesForOuts(board);
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
	public void deleteFAQ(MasterVO board) {
		ServiceResult result = null;
		MasterVO saved = dao.selectFAQ(board);
		if(saved != null) {
			int cnt = dao.deleteFAQ(board);
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
