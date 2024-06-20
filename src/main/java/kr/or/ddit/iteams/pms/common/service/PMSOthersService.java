package kr.or.ddit.iteams.pms.common.service;

import java.util.List;

import kr.or.ddit.iteams.common.vo.MasterVO;

public interface PMSOthersService {
	
	/**
	 * 작업내역  리스트 조회
	 * @param masterVO
	 * @return
	 */
	public void selectTimeLineList(MasterVO masterVO);
	
	

}
