package kr.or.ddit.iteams.outs.board.qna.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.dao.AttachDAO;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.qna.dao.FAQDAO;
import kr.or.ddit.iteams.outs.board.qna.dao.QNADAO;

@Service
public class QNAServiceImpl implements QNAService{
	
	@Inject
	private QNADAO daoQNA;
	
	@Inject
	private FAQDAO dao;
	
	@Inject
	private FTPServerFileManager upload;
	
	@Inject
	private AttachDAO attachDao;

	@Override
	public void selectQnAList(MasterVO board) {
		int totalRecord = daoQNA.selectTotalRecord(board);
		board.setTotalRecord(totalRecord);
		
		List<MasterVO> boardList = daoQNA.selectQnAList(board);
		board.setDataList(boardList);
	}

	@Override
	public ServiceResult selectCheckAuth(MasterVO member) {
		ServiceResult result = null;
		MasterVO saved = dao.selectFAQ(member);	
		
		if(member.getMemRole().equals("ADMIN")) {
			result = ServiceResult.OK;
			
		}else {
			if(StringUtils.equals(member.getMemId(), saved.getMemId())) {
				result = ServiceResult.OK;
				
			}else {
				String writerQ = daoQNA.selectCheckQMem(member);
				if(StringUtils.equals(member.getMemId(), writerQ)) {
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.FAILED;
				}
			}
		}
		return result;
	}
		
		
	@Override
	public void selectQnA(MasterVO board) throws IllegalAccessException, InvocationTargetException {
		MasterVO saved = dao.selectFAQ(board);		
		if(saved != null) {
			if(dao.selectFAQ(board) != null) {
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
	}

	@Transactional
	@Override
	public void insertQuestion(MasterVO board) throws Exception {
		ServiceResult result = null;
		board.setBoCode("GQ");
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
	public void insertAnswer(MasterVO board) throws Exception {
		ServiceResult result = null;
		int cntAnswer = daoQNA.insertAnswer(board);
		int cntQuestion = daoQNA.updateQForAnswer(board);
		if(cntAnswer > 0 && cntQuestion > 0) {
			upload.uploadFilesForOuts(board);
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		board.setResult(result);
	}
	
	@Override
	public void selectCheckAnswer(MasterVO board) {
		ServiceResult result = null;
		MasterVO answer = daoQNA.selectCheckAnswer(board);
		if (answer != null) {
			result = ServiceResult.HAVECHILD;
		} else result = ServiceResult.OK;
		board.setResult(result);
	}

	@Transactional
	@Override
	public void updateQuestion(MasterVO board) throws Exception {
		ServiceResult result = null;
		MasterVO saved = dao.selectFAQ(board);
		if (saved != null) {
				int cntGen = dao.updateGenboard(board);
				int cntQna = dao.updateQnaboard(board);
				if (cntGen > 0 && cntQna > 0) {
					upload.uploadFilesForOuts(board);
					result = ServiceResult.OK;
				} else result = ServiceResult.FAILED;
			} else throw new PKNotFoundException(board.getBoNum() + "번 글을 찾을 수 없습니다");
		board.setResult(result);
	}

	@Transactional
	@Override
	public void updateAnswer(MasterVO board) throws Exception {
		ServiceResult result = null;
		MasterVO saved = dao.selectFAQ(board);
		if(saved != null) {
			int cntGen = daoQNA.updateAnswer(board);
			if(cntGen > 0) {
				upload.uploadFilesForOuts(board);
				result = ServiceResult.OK;
			}else result = ServiceResult.FAILED;
		}else throw new PKNotFoundException(board.getBoNum() + "번 글을 찾을 수 없습니다");
	
		board.setResult(result);
	}







	
	

}
