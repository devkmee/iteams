package kr.or.ddit.iteams.outs.message.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;

public interface MessageService {

	public void selectSendList(MasterVO vo);
	
	public void selectReceiveList(MasterVO vo);
	
	public MasterVO selectSendDetail(MasterVO vo);
	
	public MasterVO selectReceiveDetail(MasterVO vo);
	
	public ServiceResult sendMessage(MasterVO vo);
	
	public ServiceResult deleteMessage(MasterVO vo);
	
	public void updateRead(MasterVO vo);
}
