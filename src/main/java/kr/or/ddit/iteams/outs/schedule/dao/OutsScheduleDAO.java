package kr.or.ddit.iteams.outs.schedule.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface OutsScheduleDAO {
	
	/**
	 * 프로젝트 일정 목록 전체 조회
	 * @param masterVO
	 * @return
	 */
	public List<MasterVO> selectScheduleList(MasterVO masterVO);
	
	public List<MasterVO> selectScheduleListTotal(MasterVO masterVO);
	
	/**
	 * 프로젝트 일정 수정
	 * @param masterVO
	 * @return
	 */
	public int updateSchedule(MasterVO masterVO);
	
	/**
	 * 프로젝트 일정 생성
	 * @param masterVO
	 * @return
	 */
	public int insertSchedule(MasterVO masterVO);
	
	/**
	 * 프로젝트 일정 삭제
	 * @param masterVO
	 * @return
	 */
	public int deleteSchedule(MasterVO masterVO);
	
	/**
	 * 프로젝트 일정을 삭제한 후 부모 테이블인 PLAN_LIST에서도 삭제
	 * @param masterVO
	 * @return
	 */
	public int deletePlan(MasterVO masterVO);
	
}
