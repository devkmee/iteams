package kr.or.ddit.iteams.pms.work.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import kr.or.ddit.iteams.common.vo.MasterVO;

public interface WorkService {
	
	/**
	 * 일감 목록 전체 조회
	 * @param masterVO
	 * @return
	 */
	public void selectWorkList(MasterVO masterVO);
	
	/**
	 * 일감 한건 조회
	 * @param masterVO
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 **/
	public void selectWork(MasterVO masterVO) throws IllegalAccessException, InvocationTargetException;
	
	/**
	 * 일감 한건 등록
	 * @param masterVO
	 * @throws Exception 
	 */
	public void insertWork(MasterVO masterVO) throws Exception;
	
	/**
	 * 일감 한건 수정
	 * @param masterVO
	 * @return
	 * @throws Exception 
	 */
	public void updateWork(MasterVO masterVO) throws Exception;
	
	/**
	 * 일감 한건 삭제 상태로 변경
	 * @param masterVO
	 * @return
	 * @throws Exception 
	 */
	public void deleteWork(MasterVO masterVO) throws Exception;

}
