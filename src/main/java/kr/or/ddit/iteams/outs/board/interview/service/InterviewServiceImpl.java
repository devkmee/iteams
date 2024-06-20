package kr.or.ddit.iteams.outs.board.interview.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.free.dao.AttatchDAO;
import kr.or.ddit.iteams.outs.board.interview.dao.InterviewDAO;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InterviewServiceImpl implements InterviewService {
	
	@Inject
	private InterviewDAO boardDAO;
	@Inject
	private AttatchDAO attatchDAO;
	
	@Value("#{appInfo.attatchPath}")
	private String saveFolderPath;
	@Value("#{appInfo.attatchPath}")
	private File saveFolder;
	
	@PostConstruct
	public void init() throws IOException {
		log.info("첨부파일 저장 위치 : {}", saveFolder.getCanonicalPath());
	}
	
	private int processAttatches(BoardVO board) {
		int rowcnt = 0;
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList!=null && !attatchList.isEmpty()) {
			rowcnt = attatchDAO.insertAttatches(board);
			
			if(1==1) throw new RuntimeException("강제 발생 예외");
			
			try {
				for(AttatchVO atch : attatchList) {
					atch.saveTo(saveFolder);
				}
			}catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return rowcnt;
	}
	@Transactional
	@Override
	public ServiceResult createBoard(BoardVO board) {
		ServiceResult result = null;
		
//		encryptPassword(board);
		
		int rowcnt = boardDAO.insertBoard(board);
		if(rowcnt > 0) {
			rowcnt += processAttatches(board);
			result = ServiceResult.OK;		
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public void retrieveBoardList(MasterVO masterVO) {
		
		masterVO.setTotalRecord(boardDAO.selectTotalRecord(masterVO));
		
		List<MasterVO> boardList = boardDAO.selectBoardList(masterVO);
		
		masterVO.setDataList(boardList);

	}

	@Override
	public BoardVO retrieveBoard(int boNum) {
		BoardVO board = boardDAO.selectBoard(boNum);
		if(board==null)
			throw new PKNotFoundException(boNum +"번 글이 없음.");
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("boNum", boNum);
		pMap.put("incType", "BO_HIT"); // replace text 활용
		boardDAO.incrementCount(pMap);
		return board;
	}

	@Transactional
	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		Object authenticated = authenticated(board);
		ServiceResult result = null;
		if(authenticated instanceof BoardVO) {
			BoardVO saved = (BoardVO) authenticated;
			int rowcnt = boardDAO.updateBoard(board);
			if(rowcnt > 0) {
				// 올릴 파일 처리
				processAttatches(board);
				// 지울 파일 처리
				int[] delAttNos = board.getDelAttNos();
				if(delAttNos!=null && delAttNos.length > 0) {
					List<AttatchVO> attatchList = saved.getAttatchList();
					attatchDAO.deleteAttatches(board);
					Arrays.sort(delAttNos);
					for(AttatchVO tmp : attatchList) {
						if(Arrays.binarySearch(delAttNos, tmp.getAttNo()) >= 0)
							FileUtils.deleteQuietly(new File(saveFolder, tmp.getAttSavename()));
					}
				}
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	private Object authenticated(BoardVO board){
		BoardVO saved = retrieveBoard(board.getBoNum());
		return saved;

	}
	
	@Transactional
	@Override
	public ServiceResult removeBoard(BoardVO board) {
		Object authenticated = authenticated(board);
		ServiceResult result = null;
		if(authenticated instanceof BoardVO) {
			BoardVO saved = (BoardVO) authenticated;
			boardDAO.deleteBoard(board.getBoNum());
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public AttatchVO download(int attNo) {
		AttatchVO atch = attatchDAO.selectAttatch(attNo);
		if(atch==null)
			throw new PKNotFoundException(attNo+"파일의 메타데이터가 없음.");
		return atch;
	}

}

