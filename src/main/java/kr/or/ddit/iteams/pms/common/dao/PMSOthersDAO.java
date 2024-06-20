package kr.or.ddit.iteams.pms.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface PMSOthersDAO {
	
	/**
	 * 현재 프로젝트 팀원 목록 조회
	 * @param masterVO
	 * @return
	 */
	public List<MasterVO> selectProjectMember(MasterVO masterVO);
	
	/**
	 * PMS 내의 작업 내역 추적 후 내용 등록
	 * @param masterVO
	 * @return
	 */
	public int insertTimeLine(MasterVO masterVO);
	
	/**
	 * 작업내역  리스트 조회
	 * @param masterVO
	 * @return
	 */
	public List<MasterVO> selectTimeLineList(MasterVO masterVO);
	
	/**
	 * 작업내역 전체 레코드 수 조회
	 * @param masterVO
	 * @return
	 */
	public int selectTimeLineTotalRecordCount(MasterVO masterVO);
	
	/**
	 * 게시글이 삭제된 작업내역에서 삭제여부를 Y로 변경
	 * @param masterVO
	 * @return
	 */
	public int deleteTimeLine(MasterVO masterVO);
	
	public int incrementCount(Map<String, Object> map);
	
}
