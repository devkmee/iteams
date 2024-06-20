package kr.or.ddit.iteams.pms.schedule.service;

import kr.or.ddit.iteams.common.vo.MasterVO;

public interface ScheduleService {
	/**
	 * 프로젝트 일정 목록 전체 조회
	 * @param masterVO
	 * @return
	 */
	public void selectScheduleList(MasterVO masterVO);
	
	/**
	 * 프로젝트 일정 수정
	 * @param masterVO
	 * @return
	 */
	public void updateSchedule(MasterVO masterVO);
	
	/**
	 * 프로젝트 일정 삭제
	 * @param masterVO
	 * @return
	 */
	public void deleteSchedule(MasterVO masterVO);
	
	/**
	 * 프로젝트 일정 생성
	 * @param masterVO
	 * @return
	 */
	public void insertSchedule(MasterVO masterVO);
	
}
