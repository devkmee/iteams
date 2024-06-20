package kr.or.ddit.iteams.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.login.vo.JoinDevRequestVO;

@Repository
public interface AttachDAO {
	
	/**
	 * 아웃소싱계열 작업에서 첨부파일 처리
	 * @param masterVO
	 * @return
	 */
	public int insertAttachForOuts(MasterVO masterVO);
	
	/**
	 * 첨부파일 번호, 게시글 번호 정보 저장
	 * @param masterVO
	 * @return
	 */
	public int insertAttachForPMSBoard(MasterVO masterVO);
	
	/**
	 * PMS계열 작업에서 첨부파일 처리
	 * @param masterVO
	 * @return
	 */
	public int insertAttachForPMS(MasterVO masterVO);
	
	/**
	 * 회원의 프로필, 프로필사진 첨부파일 메타데이터 처리
	 * @param masterVO
	 * @return
	 */
	public int insertAttachForProfile(JoinDevRequestVO masterVO);
	
	/**
	 * 아웃소싱 첨부파일 한 건 조회
	 * @param masterVO
	 * @return
	 */
	public AttachTotalVO selectAttachForOuts(MasterVO masterVO);
	
	/**
	 * 
	 * @param masterVO
	 * @return
	 */
	
	/**
	 * 프로필 첨부파일 한 건 조회
	 * @param masterVO
	 * @return
	 */
	public AttachTotalVO selectAttachForProfile(MasterVO masterVO);
	
	/**
	 * 첨부파일 insert 후 insert한 정보중 일감에 관한 내용 저장
	 * @param masterVO
	 * @return
	 */
	public int insertAttachInfoForWork(MasterVO masterVO);
	
	/**
	 * PMS 첨부파일 다운로드를 위한 한 건 조회
	 * @param masterVO
	 * @return
	 */
	public AttachTotalVO selectAttachForPMS(MasterVO masterVO);
	
	/**
	 * 일감 한 건에 대한 첨부파일 목록 조회
	 * @param masterVO
	 * @return
	 */
	public List<AttachTotalVO> selectAttachListForWork(MasterVO masterVO);
	
	/**
	 * 문서함 테이블 인서트
	 * @param masterVO
	 * @return
	 */
	public int insertDocumentAttach(MasterVO masterVO);
	
	/**
	 * PMS 첨부파일 삭제
	 * @param masterVO
	 * @return
	 */
	public int deleteAttachForPMS(MasterVO masterVO);
	
	public int deleteAttachForOuts(MasterVO masterVO);
	
	public int deleteAttachForProfile(MasterVO masterVO);
	
	/**
	 * 아웃소싱 게시글 1건에 대한 첨부파일 목록 조회
	 * @param master : boNum
	 * @return
	 */
	public List<AttachTotalVO> selectAttachListForGenBoard (MasterVO master);
	
}
