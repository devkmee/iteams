package kr.or.ddit.iteams.pms.project.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface OthersDAO {
	/*
	 * 프로젝트 지원자 리스트
	 */
	public List<MasterVO> selectProMemList(MasterVO masterVO);
	/*
	 * 프로젝트 진행중인 사람 리스트
	 */
	public List<MasterVO> selectProingMemList(MasterVO masterVO);
	
}
