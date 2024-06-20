package kr.or.ddit.iteams.outs.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.ReplyTotalVO;

@Repository
public interface BoardReplyDAO {
	
	public int selectTotalRecords(MasterVO masterVO);
	
	public List<ReplyTotalVO> selectReplyList(MasterVO masterVO);
	
	public int insertReply(ReplyTotalVO replyVO);
	
	public int updateReply(ReplyTotalVO masterVO);
	
	public int deleteReply(ReplyTotalVO masterVO);
}
