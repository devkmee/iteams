package kr.or.ddit.iteams.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface GetRankDAO {

	public List<MasterVO> projectRank();
	
	public List<MasterVO> activeRank(); 
	
	public List<MasterVO> reviewRank();
	
}
