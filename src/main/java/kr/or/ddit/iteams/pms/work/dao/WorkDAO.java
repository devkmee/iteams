package kr.or.ddit.iteams.pms.work.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface WorkDAO {
	
	
	/**
	 * 전체 일감 레코드 수 조회
	 * @param masterVO
	 * @return
	 */
	public int selectWorkTotalRecordCount(MasterVO masterVO);
	
	/**
	 * 일감 목록 전체 조회
	 * @param masterVO
	 * @return
	 */
	public List<MasterVO> selectWorkList(MasterVO masterVO);
	
	/**
	 * 엑셀 다운용 페이징 처리 하지 않은 전체 목록 조회
	 * @param masterVO
	 * @return
	 */
	public List<MasterVO> selectWorkListTotal(MasterVO masterVO);
	
	/**
	 * 간트챠트용 일감 전체 리스트 조회
	 * @param masterVO
	 * @return
	 */
	public List<MasterVO> selectWorkListForGantt(MasterVO masterVO);
	
	/**
	 * 개발자용 자신의 일감 간트챠트
	 * @param masterVO
	 * @return
	 */
	public List<MasterVO> selectWorkListFormyGantt(MasterVO masterVO);
	
	/**
	 * 일감 한건 조회
	 * @param masterVO
	 * @return
	 **/
	public MasterVO selectWork(MasterVO masterVO);
	
	/**
	 * 일감 한건 등록
	 * @param masterVO
	 * @return
	 */
	public int insertWork(MasterVO masterVO);
	
	/**
	 * 일감 한건 수정
	 * @param masterVO
	 * @return
	 */
	public int updateWork(MasterVO masterVO);
	
	/**
	 * 간트챠트를 통한 일감의 날짜, 진행도 수정
	 * @param masterVO
	 * @return
	 */
	public int updateWorkByGantt(MasterVO masterVO);
	
	/**
	 * 일감 한건 삭제 상태로 변경
	 * @param masterVO
	 * @return
	 */
	public int deleteWork(MasterVO masterVO);
	
	/**
	 * 상위일감 자동 검색용 전체 일감 제목 리스트 조회
	 * @param masterVO
	 * @return
	 */
	public List<Map<String, String>> selectWorkTitleList(MasterVO masterVO);
	
}
