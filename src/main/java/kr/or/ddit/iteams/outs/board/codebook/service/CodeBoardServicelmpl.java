package kr.or.ddit.iteams.outs.board.codebook.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.codebook.dao.CodeBookDAO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CodeBoardServicelmpl implements CodeBoardService {
	
	
	@Inject
	private CodeBookDAO boardDAO;

    /*@Inject
    private FTPServerFileManager manager;*/
	
    //게시글전체조회
    @Override
	public List<MasterVO> CodeBoardList(MasterVO master) {
		master.setTotalRecord(boardDAO.selectTotalRecord(master));
		List<MasterVO> boardList = boardDAO.CodeBoardList(master);
		master.setDataList(boardList);
		return boardList;
	}

    //게시글상세조회
	@Override
	public MasterVO retrieveCode(int number) {
		MasterVO board = boardDAO.retrieveCode(number);
		if(board == null) {
			throw new PKNotFoundException(number + "번 글이 없습니다.");
	  }
		Map<String, Object> map = new HashMap<>();
		map.put("incType", "BO_HIT");
		map.put("boNum", number);
		boardDAO.incrementCount(map);
		
		return board;
	}

}
