package kr.or.ddit.iteams.common.service;

import kr.or.ddit.iteams.common.vo.MasterVO;

public interface AttachService {
	/**
	 * 아웃소싱계열 작업에서 첨부파일 처리
	 * @param masterVO
	 * @return
	 */
	public void insertAttachForOuts(MasterVO masterVO);
	
	/**
	 * PMS계열 작업에서 첨부파일 처리
	 * @param masterVO
	 * @return
	 */
	public void insertAttachForPMS(MasterVO masterVO);
	
	/**
	 * 첨부파일 insert 후 insert한 정보중 일감에 관한 내용 저장
	 * @param masterVO
	 * @return
	 */
	public void insertAttachInfoForWork(MasterVO masterVO);
	
	/**
	 * PMS 첨부파일 한개 조회
	 * @param masterVO
	 * @return
	 */
	public void selectAttachForPMS(MasterVO masterVO);
	
	public void deleteAttachForPMS(MasterVO masterVO) throws Exception;
	
	public void deleteAttachForOuts(MasterVO masterVO) throws Exception;
	
	public void deleteAttachForProfile(MasterVO masterVO) throws Exception;
}
