package kr.or.ddit.iteams.outs.board.news.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.PagingVO;
import kr.or.ddit.iteams.outs.board.news.dao.NewsReplyDAO;
import kr.or.ddit.vo.ReplyVO;


@Service
public class NewsReplyServiceImpl implements NewsReplyService {

	@Inject
	private NewsReplyDAO dao;
	
//	private void encryptPassword(ReplyVO reply) {
//		reply.setRepPass(CryptoUtils.sha512EncryptBase64(reply.getRepPass()));
//	}
	
	public ServiceResult createReply(ReplyVO reply) {
//		encryptPassword(reply);
		int rowcnt = dao.insertReply(reply);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
	}

	@Override
	public List<ReplyVO> retrieveReplyList(PagingVO<ReplyVO> pagingVO) {
		pagingVO.setTotalRecord(dao.selectTotalRecord(pagingVO));
		List<ReplyVO> replyList = dao.selectReplyList(pagingVO);
		pagingVO.setDataList(replyList);
		return replyList;
	}

	@Override
	public ServiceResult modifyReply(ReplyVO reply) {
//		encryptPassword(reply);
		int rowcnt = dao.updateReply(reply);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
	}

	@Override
	public ServiceResult removeReply(ReplyVO reply) {
//		encryptPassword(reply);
		int rowcnt = dao.deleteReply(reply);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
	}

}
