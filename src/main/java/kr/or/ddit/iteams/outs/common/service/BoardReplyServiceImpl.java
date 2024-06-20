package kr.or.ddit.iteams.outs.common.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.ReplyTotalVO;
import kr.or.ddit.iteams.outs.common.dao.BoardReplyDAO;

@Service
public class BoardReplyServiceImpl implements BoardReplyService{

	@Inject
	private BoardReplyDAO dao;
	
	@Override
	public void selectReplyList(MasterVO masterVO) {
		int totalRecords = dao.selectTotalRecords(masterVO);
		masterVO.setTotalRecord(totalRecords);
		List<ReplyTotalVO> replyList = dao.selectReplyList(masterVO);
		masterVO.setDataList(replyList);
	}

	@Override
	public void insertReply(ReplyTotalVO replyVO) {
		int cnt = dao.insertReply(replyVO);
		ServiceResult result = cnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
		replyVO.setResult(result);
	}

	@Override
	public void updateReply(ReplyTotalVO replyVO) {
		int cnt = dao.updateReply(replyVO);
		ServiceResult result = cnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
		replyVO.setResult(result);
	}

	@Override
	public void deleteReply(ReplyTotalVO replyVO) {
		int cnt = dao.deleteReply(replyVO);
		ServiceResult result = cnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
		replyVO.setResult(result);
	}

}
