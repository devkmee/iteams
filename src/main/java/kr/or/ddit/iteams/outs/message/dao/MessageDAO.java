package kr.or.ddit.iteams.outs.message.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface MessageDAO {

	public int selectSendTotalRecord(MasterVO vo);
	
	public List<MasterVO> selectSendList(MasterVO vo);
	
	public int selectReceiveTotalRecord(MasterVO vo);
	
	public List<MasterVO> selectReceiveList(MasterVO vo);
	
	public MasterVO selectSendDetail(MasterVO vo);
	
	public MasterVO selectReceiveDetail(MasterVO vo);
	
	public int sendMessage(MasterVO vo); 
	
	public int deleteMessage(MasterVO vo);
	
	public int updateRead(MasterVO vo);
	
	public List<String> selectMemIdList(MasterVO masterVO);
	
	public int selectNotReadMessageCount(MasterVO masterVO);
}
