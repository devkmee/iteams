package kr.or.ddit.iteams.outs.board.news.service;

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
import kr.or.ddit.iteams.outs.board.news.dao.NewsBoardDAO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NewsBoardServiceImpl implements NewsBoardService {
	
	@Inject
	private NewsBoardDAO boardDAO;
    @Inject
	private FTPServerFileManager manager;
	
    //게시글 전체목록 조회 
    @Override
	public List<MasterVO> NewsBoardList(MasterVO master) {
    	master.setTotalRecord(boardDAO.selectTotalRecord(master));
		List<MasterVO> boardList = boardDAO.NewsBoardList(master);
		master.setDataList(boardList);
		return boardList;
		
	}

	//게시글 상세보기
	@Override
	public MasterVO retrieveNews(int number) {
		MasterVO board = boardDAO.retrieveNews(number);
		if(board == null) {
			throw new PKNotFoundException(number + "번 글이 없습니다.");
	  }
		Map<String, Object> map = new HashMap<>();
		map.put("incType", "BO_HIT");
		map.put("boNum", number);
		boardDAO.incrementCount(map);
		
		return board;
	}

	//게시물 생성
	@Override
	@Transactional
	@TimeLine
	public ServiceResult insertNewsBoard(MasterVO master) throws Exception {
		
		ServiceResult result = null;
		
		int rowcnt = boardDAO.insertNewsBoard(master);
		
		if(rowcnt > 0) {
			manager.uploadFilesForOuts(master);
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	//게시물 수정
	@Transactional
	@Override
	public void updateNews(MasterVO vo) throws Exception {
		vo.setWorkNum("");
		manager.uploadFilesForOuts(vo);
		boardDAO.updateNews(vo);
		
	}

	//게시물삭제
	@Transactional
	@Override
	public int deleteNews(MasterVO masterVO) throws Exception {
		//List<AttachTotalVO> attachList = boardDAO.selectNewsBoardAttachList(Integer.parseInt(masterVO.getBoNum()));
    	MasterVO saved = boardDAO.retrieveNews(Integer.parseInt(masterVO.getBoNum()));	
		masterVO.setBoTitle(saved.getBoTitle());
		int cnt = boardDAO.deleteNews(masterVO);
		MasterVO master = new MasterVO();
		//masterVO.setAttachList(attachList);
		manager.deleteFilesForOuts(master);
		return cnt;
	}
	
	
	

}












