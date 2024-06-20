package kr.or.ddit.iteams.outs.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.member.dao.MyboardDAO;

@Service
public class MyboardServiceImpl implements MyboardService{
	
	@Inject
	private MyboardDAO dao;

	@Override
	public void selectMyBoardList(MasterVO board) {
		int totalRecord = dao.selectTotalRecord(board);
		board.setTotalRecord(totalRecord);
		
		List<MasterVO> boardList = dao.selectMyBoardList(board);
		board.setDataList(boardList);
	}

}