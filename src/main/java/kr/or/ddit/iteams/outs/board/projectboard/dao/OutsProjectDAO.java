package kr.or.ddit.iteams.outs.board.projectboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.iteams.common.vo.BaseVO;
import kr.or.ddit.iteams.common.vo.MasterVO;

@Repository
public interface OutsProjectDAO {
   
   public int insertProject(MasterVO vo);
   public int selectTotalRecord(BaseVO base);
   public List<MasterVO> selectProjectList(MasterVO base);
   
   public MasterVO selectProject(String number);
   
   public MasterVO selectProjectApp(MasterVO masterVO);
   
   public int updateMeetingState(MasterVO masterVO);
   
   public int updateGen(MasterVO vo);
   public int updatePro(MasterVO vo);
   
   public int deleteProject(MasterVO vo);
   
   public int incrementCount(Map<String, Object> pMap);
   
   /**
    * 메인화면에서 보여줄 프로젝트 공고글 리스트 조회
    * @param masterVO
    * @return
    */
   public List<MasterVO> selectMainProjectList(MasterVO masterVO);
   
   public MasterVO selectTraceProject (MasterVO vo);
   
   /**
    * 추천 프로젝트 목록 조회
    * @param masterVO
    * @return
    */
   public List<MasterVO> selectRecProjectList(MasterVO masterVO);
   
   public MasterVO selectIngProject(MasterVO masterVO);
   
   public int deadline(String boNum);
   
}