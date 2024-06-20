package kr.or.ddit.iteams.pms.documents.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface DocumentsDAO {
	
	public int insertDocuments(MasterVO masterVO);
	
	public MasterVO selectDocuments(MasterVO masterVO);
	
	public int updateDocuments(MasterVO masterVO);
	
	public List<MasterVO> selectDocumentsList(MasterVO masterVO);
	
	public int selectDocumentsTotalRecords(MasterVO masterVO);
	
	public int deleteDocuments(MasterVO masterVO);

}
