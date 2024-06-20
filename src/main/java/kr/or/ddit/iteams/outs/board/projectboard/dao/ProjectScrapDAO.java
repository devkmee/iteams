package kr.or.ddit.iteams.outs.board.projectboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.BaseVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.OutsTotalVO;

@Repository
public interface ProjectScrapDAO {

	public MasterVO selectScrap(MasterVO vo); 
	
	public int scarp(MasterVO vo); 
	
	public int unScrap(MasterVO vo); 
	
	public List<OutsTotalVO> selectScrapList(MasterVO base); 
	
	public int selectTotalRecord(MasterVO base);
	
	public MasterVO selectScrapView(String number);
	
	public int incrementCount(Map<String, Object> pMap);
}
