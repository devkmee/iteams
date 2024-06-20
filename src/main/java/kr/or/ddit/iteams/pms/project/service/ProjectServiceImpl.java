package kr.or.ddit.iteams.pms.project.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.logging.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.CustomProjectMemVO;
import kr.or.ddit.iteams.common.vo.CustomProjectVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.PMSTotalVO;
import kr.or.ddit.iteams.common.vo.PagingVO;
import kr.or.ddit.iteams.pms.project.dao.OthersDAO;
import kr.or.ddit.iteams.pms.project.dao.ProjectDAO;
import kr.or.ddit.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {
	@Inject
	private ProjectDAO dao;
	
	@Inject
	private OthersDAO Dao;
	
	@Inject
	private FTPServerFileManager FTP;
	

	
	@Transactional
	@Override
	public ServiceResult createProject(MasterVO masterVO) throws Exception {
		int rowcnt  = dao.insertProject(masterVO);
		
		String proNo= masterVO.getProNo();
		
		List<CustomProjectMemVO> memList= masterVO.getDevList();
		masterVO.setDataList(memList);
		for(CustomProjectMemVO mem: memList) {
			mem.setProNo(proNo);
			dao.insertApp(mem);
			
		}
		
		//선택된 지원자  추가
		CustomProjectMemVO cPMVO = new CustomProjectMemVO();
		cPMVO.setProNo(proNo);
		cPMVO.setDevId(masterVO.getCliId());
		cPMVO.setAuthority("PM");
		dao.insertApp(cPMVO);
		
		FTP.createPMSRootFolder(masterVO.getProNo());
		
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
	}
	
	@Transactional
	@Override
	public ServiceResult modifyProject(MasterVO masterVO) {
		int rowcnt = dao.updateProject(masterVO);
		
		List<CustomProjectMemVO> memList= masterVO.getDevList();
		masterVO.setDataList(memList);
		for(CustomProjectMemVO mem:memList) {
			
			dao.updateApp(mem);
		}
		
		

		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
	}
	@Transactional
	@Override
	public ServiceResult removeProject(MasterVO masterVO) {
		int rowcnt = dao.deleteProject(masterVO);
		
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
	}
	@Transactional
	@Override
	public List<MasterVO> retrieveProjectList(MasterVO masterVO) {
		
		List<MasterVO> projectList = dao.selectProjectList(masterVO);
		
		masterVO.setDataList(projectList); 
		
		return projectList;
	}
	
	@Transactional
	@Override
	public List<MasterVO> retrieveProMemList(MasterVO masterVO){
		List<MasterVO> projectList = Dao.selectProMemList(masterVO);
		masterVO.setDataList(projectList);
		return projectList;
	}
	@Transactional
	@Override
	public List<MasterVO> retrieveProingMemList(MasterVO masterVO){
		List<MasterVO> projectList = Dao.selectProingMemList(masterVO);
		masterVO.setDataList(projectList);
		for(MasterVO proList:projectList) {
			masterVO.setProjectName(proList.getProjectName());
			masterVO.setManagerName(proList.getManagerName());
			masterVO.setCreateDate(proList.getCreateDate());
		}
		return projectList;
	}
	
	@Transactional
	@Override
	public List<MasterVO> retrieveProject(MasterVO masterVO) {
		List<MasterVO> retProject = dao.selectProject(masterVO);

		masterVO.setDataList(retProject);
		return retProject;
	}
	@Transactional
	@Override
	public List<MasterVO> retrieveProBoardList(MasterVO masterVO) {
		List<MasterVO> projectList = dao.selectProjectBoardList(masterVO);
		masterVO.setDataListt(projectList);
		return projectList;
	}



	

	
}
