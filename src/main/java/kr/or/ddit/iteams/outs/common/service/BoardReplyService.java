package kr.or.ddit.iteams.outs.common.service;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.ReplyTotalVO;

public interface BoardReplyService {
	
	public void selectReplyList(MasterVO masterVO);
	
	public void insertReply(ReplyTotalVO replyVO);
	
	public void updateReply(ReplyTotalVO replyVO);
	
	public void deleteReply(ReplyTotalVO replyVO);
}
