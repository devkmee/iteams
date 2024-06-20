package kr.or.ddit.iteams.outs.hire.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.BaseVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.OutsTotalVO;


/**
 * 프로젝트 지원자 관련 DAO
 * @author pc21
 *
 */
@Repository
public interface ApplyProjectDAO {
	

	/**
	 * 1건의 지원 기록 조회
	 * @param applyPro : boNum + appId(개발자 아이디) / appNo
	 * @return 지원 기록이 없으면 null반환
	 */
	public MasterVO selectApply(MasterVO applyPro);
	
	/**
	 * 프로젝트 지원
	 * @param applyPro : memId
	 * @return 0보다 크면 성공, 아니면 실패
	 */
	public int insertApplyProject(OutsTotalVO applyPro);
	
	/**
	 * 프로젝트 지원시 해당 프로젝트의 클라이언트에게 쪽지 발송
	 * @param masterVO
	 * @return
	 */
	public int insertApplyMessage(MasterVO masterVO);
	
	/**
	 * 프로젝트 지원 취소
	 * @param applyPro : boNum + appId
	 * @return 0보다 크면 성공, 아니면 실패
	 */
	public int DeleteApplyProject(OutsTotalVO applyPro);

	
	/**
	 * 특정 클라이언트의 공고 프로젝트 레코드 수 조회
	 * @param applyPro : 페이징 + boNum(공고 게시글)
	 * @return
	 */
	public int selectBOARDTotalRecord(OutsTotalVO applyPro);
	
	/**
	 * 마이페이지용 특정 클라이언트의 최근 공고 프로젝트 목록
	 * @param applyPro : memId
	 * @return
	 */
	public List<OutsTotalVO> selectApplyListForClient(OutsTotalVO board);
	
	
	/**
	 * 지원자 수 전체 레코드 수 조회
	 * @param applyPro : 페이징 + boNum(클라이언트) / 페이징 + appId(개발자)
	 * @return
	 */
	public int selectAPPTotalRecord(OutsTotalVO applyPro);
	
	/**
	 * 마이페이지용 특정 개발자의 지원 프로젝트 목록
	 * @param applyPro : appId
	 * @return
	 */
	public List<OutsTotalVO> selectApplyListForDev(MasterVO applyPro);
	
	/**
	 * 마이페이지용 특정 프로젝트의 지원자 목록
	 * @param applyPro : boNum(공고 게시글) + 페이징
	 * @return 
	 */
	public List<OutsTotalVO> selectApplyListOneProject(OutsTotalVO applyPro);
	
	/**
	 * 1명의 지원자 프로필 조회
	 * @param appId
	 * @return
	 */
	public MasterVO selectDevProfile(OutsTotalVO appId) throws IllegalAccessException, InvocationTargetException;
	
	/**
	 * 클라이언트가 지원자 채용
	 * @param applyPro : appNo
	 * @return
	 */
	public int updateHireApply(MasterVO applyPro);
	
	/**
	 * 클라이언트가 지원자 반려 
	 * @param applyPro : appNo
	 * @return
	 */
	public int updateReturnApply(MasterVO applyPro);
	
	/**
	 * 면접 제안
	 * @return
	 */
	public int updateAppMeeting(MasterVO masterVO);
	
	
}
