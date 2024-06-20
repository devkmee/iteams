package kr.or.ddit.iteams.outs.schedule.service;

import kr.or.ddit.iteams.common.vo.MasterVO;

public interface OutsScheduleService {
	/**
	 * 프로젝트 일정 목록 전체 조회
	 * @param masterVO
	 * @return
	 */
	public void selectScheduleList(MasterVO vo);
	
	/**
	 * 프로젝트 일정 수정
	 * @param masterVO
	 * @return
	 */
	public void updateSchedule(MasterVO vo);
	
	/**
	 * 프로젝트 일정 삭제
	 * @param masterVO
	 * @return
	 */
	public void deleteSchedule(MasterVO vo);
	
	/**
	 * 프로젝트 일정 생성
	 * @param masterVO
	 * @return
	 */
	public void insertSchedule(MasterVO vo);
	
}
