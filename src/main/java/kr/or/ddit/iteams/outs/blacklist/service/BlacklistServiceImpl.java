package kr.or.ddit.iteams.outs.blacklist.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.blacklist.dao.BlacklistDAO;

@Service
public class BlacklistServiceImpl implements BlacklistService{
	
	@Inject
	private BlacklistDAO dao;

	@Override
	public void selectBlackList(MasterVO master) {
		int totalRecord = dao.selectTotalRecord(master);
		master.setTotalRecord(totalRecord);
		
		List<MasterVO> boardList = dao.selectBlackList(master);
		master.setDataList(boardList);
	}

	@Override
	public void updateBlackToMem(MasterVO master) {
		ServiceResult result = null;
		MasterVO saved = dao.selectCheckBlack(master);
		if(saved != null) {
			int cnt = dao.updateBlackToMem(master);
			if(cnt > 0) {
				result = ServiceResult.OK;
			}else result = ServiceResult.FAILED;
		}else throw new PKNotFoundException(master.getBlackNo() + "번 블랙리스트를 찾을 수 없습니다");
		master.setResult(result);
	}

	@Transactional
	@Override
	public void insertMemToBlack(MasterVO member) {
		ServiceResult result = null;
		int cntBlack = dao.insertMemToBlack(member);
		int cntGen = dao.updateRepGen(member);
		if(cntBlack > 0 && cntGen >0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		member.setResult(result);
	}

	@Override
	public void selectRepBoardList(MasterVO board) {
		int totalRecord = dao.selectREPTotal(board);
		board.setTotalRecord(totalRecord);
		
		List<MasterVO> boardList = dao.selectRepBoardList(board);
		board.setDataList(boardList);
	}

	@Override
	public void updateClearBoard(MasterVO board) {
		String saved = dao.selectCheckGen(board);
		ServiceResult result = null;
		if(saved != null) {
			int cnt = dao.updateClearGen(board);
			if(cnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else throw new PKNotFoundException(board.getBoNum() + "번 글을 찾을 수 없습니다");
		board.setResult(result);
	}
	
	
	

}
