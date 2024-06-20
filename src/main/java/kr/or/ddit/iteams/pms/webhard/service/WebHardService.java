package kr.or.ddit.iteams.pms.webhard.service;


import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.MasterVO;


public interface WebHardService {
	
	public AttachTotalVO retrieveWebHardList(String aa);
	
	public void insertAttachfile(MasterVO master) throws Exception;
	
}
