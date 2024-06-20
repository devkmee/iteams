package kr.or.ddit.iteams.outs.board.free.service;

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
import kr.or.ddit.iteams.outs.board.free.dao.FreeBoardDAO;
import kr.or.ddit.vo.AttatchVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FreeBoardServiceImpl implements FreeBoardService {
	
	@Inject
	private FreeBoardDAO boardDAO;
	@Inject
	private AttatchDAO attatchDAO;
	
	@Inject
	private FTPServerFileManager manager;
	
//	@Value("#{appInfo.attatchPath}")
//	private String saveFolderPath;
//	@Value("#{appInfo.attatchPath}")
//	private File saveFolder;
	
//	@PostConstruct
//	public void init() throws IOException {
//		saveFolder = new File(saveFolderPath);
//		log.info("첨부파일 저장 위치 : {}", saveFolder.getCanonicalPath());
//	}
	
//	private int processAttatches(BoardVO board) {
//		int rowcnt = 0;
//		List<AttatchVO> attatchList = board.getAttatchList();
//		if(attatchList!=null && !attatchList.isEmpty()) {
//			rowcnt = attatchDAO.insertAttatches(board);
//			
//			if(1==1) throw new RuntimeException("강제 발생 예외");
//			
//			try {
//				for(AttatchVO atch : attatchList) {
//					atch.saveTo(saveFolder);
//				}
//			}catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//		}
//		return rowcnt;
//	}

//	private void encryptPassword(BoardVO board) {
//		board.setBoPass(CryptoUtils.sha512EncryptBase64(board.getBoPass()));
//	}
	
	@Transactional
	@Override
	public void createBoard(MasterVO board) throws Exception {
		ServiceResult result = null;
		
//		encryptPassword(board);
		
		int rowcnt = boardDAO.insertBoard(board);
		if(rowcnt > 0) {
			manager.uploadFilesForOuts(board);
			result = ServiceResult.OK;		
			board.setResult(result);
		}else {
			result = ServiceResult.FAILED;
			board.setResult(result);
		}
	}

	@Override
	public void retrieveBoardList(MasterVO masterVO) {
		
		masterVO.setTotalRecord(boardDAO.selectTotalRecord(masterVO));
		
		List<MasterVO> boardList = boardDAO.selectBoardList(masterVO);
		
		masterVO.setDataList(boardList);

	}

	@Override
	public MasterVO retrieveBoard(MasterVO masterVO) {
		MasterVO board = boardDAO.selectBoard(masterVO);
		if(board==null)
			throw new PKNotFoundException(masterVO.getWhat() +"번 글이 없음.");
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("boNum", masterVO.getWhat());
		pMap.put("incType", "BO_HIT"); // replace text 활용
		boardDAO.incrementCount(pMap);
		return board;
	}

	@Transactional
	@Override
	public void modifyBoard(MasterVO board) throws Exception {
		int rowcnt = boardDAO.updateBoard(board);
		manager.uploadFilesForOuts(board);
		ServiceResult result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
		board.setResult(result);
	}

//	private Object authenticated(BoardVO board){
//		BoardVO saved = retrieveBoard(board.getBoNum());
//		return saved;
//
////		inputPass = CryptoUtils.sha512EncryptBase64(inputPass);
//	
//	}
	
	@Transactional
	@Override
	public ServiceResult removeBoard(MasterVO board) throws Exception {
		MasterVO saved = boardDAO.selectBoard(board);
		manager.deleteFilesForOuts(saved);
		int cnt = boardDAO.deleteBoard(board.getBoNum());
		
		return cnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
	}

	@Override
	public AttatchVO download(int attNo) {
		AttatchVO atch = attatchDAO.selectAttatch(attNo);
		if(atch==null)
			throw new PKNotFoundException(attNo+"파일의 메타데이터가 없음.");
		return atch;
	}

}










