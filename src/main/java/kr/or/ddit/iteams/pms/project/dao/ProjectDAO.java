package kr.or.ddit.iteams.pms.project.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.CustomProjectMemVO;
import kr.or.ddit.iteams.common.vo.CustomProjectVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.PMSTotalVO;
import kr.or.ddit.iteams.common.vo.PagingVO;

@Repository
public interface ProjectDAO {
	public List<MasterVO> selectProjectBoardList(MasterVO masterVO);
	public List<MasterVO> selectProjectList(MasterVO masterVO);
	public List<MasterVO> selectProject(MasterVO masterVO);
	public int selectTotalRecord(MasterVO masterVO);
	public int insertProject(MasterVO masterVO);
	public int updateProject(MasterVO masterVO);
	public int deleteProject(MasterVO masterVO);
	public int inviteProject(MasterVO masterVO);
	/*
	 * 프로젝트 지원자 등록
	 */
	public int insertApp(CustomProjectMemVO mem);
	/*
	 * 프로젝트 멤버수정
	 */
	public int updateApp(CustomProjectMemVO mem);
	/*
	 * 프로젝트 멤버 추방
	 */
	public int deleteApp(CustomProjectMemVO mem);
	
}
