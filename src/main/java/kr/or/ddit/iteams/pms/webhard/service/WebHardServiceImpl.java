package kr.or.ddit.iteams.pms.webhard.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.logging.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.CustomProjectMemVO;
import kr.or.ddit.iteams.common.vo.CustomProjectVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.PMSTotalVO;
import kr.or.ddit.iteams.common.vo.PagingVO;
import kr.or.ddit.iteams.pms.project.dao.OthersDAO;
import kr.or.ddit.iteams.pms.project.dao.ProjectDAO;
import kr.or.ddit.iteams.pms.webhard.dao.WebHardDAO;
import kr.or.ddit.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WebHardServiceImpl implements WebHardService {
	@Inject
	private WebHardDAO dao;

	@Inject
	private FTPServerFileManager FTP;

	@Transactional
	@Override
	public AttachTotalVO retrieveWebHardList(String aa) {

		return dao.selectWebHardList(aa);
	}

	@Override
	public void insertAttachfile(MasterVO master) throws Exception {
		FTP.uploadFilesForPMS(master);
		master.setResult(ServiceResult.OK);
		
	}
	
}
