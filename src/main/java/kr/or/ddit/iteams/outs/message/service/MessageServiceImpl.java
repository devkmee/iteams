package kr.or.ddit.iteams.outs.message.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.error.PKNotFoundException;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.message.dao.MessageDAO;

@Service
public class MessageServiceImpl implements MessageService {

	@Inject
	private MessageDAO dao;
	
	@Override
	public void selectSendList(MasterVO vo) { 
		int totalRecord = dao.selectSendTotalRecord(vo); 
		vo.setTotalRecord(totalRecord); 
		
		List<MasterVO> sendList = dao.selectSendList(vo);
		vo.setDataList(sendList);
	}

	@Override
	public void selectReceiveList(MasterVO vo) {
		int totalRecord = dao.selectReceiveTotalRecord(vo); 
		vo.setTotalRecord(totalRecord); 
		
		List<MasterVO> receiveList = dao.selectReceiveList(vo); 
		vo.setDataList(receiveList);
	}

	@Override
	public MasterVO selectSendDetail(MasterVO vo) {
		MasterVO msg = dao.selectSendDetail(vo);
		
		if(msg==null)
			throw new PKNotFoundException(msg.getMsgNum() +"쪽지가 없음.");
		
		return msg;
	}

	@Override
	public MasterVO selectReceiveDetail(MasterVO vo) { 
		MasterVO msg = dao.selectReceiveDetail(vo); 
		
		if(msg==null)
			throw new PKNotFoundException(msg.getMsgNum() +"쪽지가 없음.");
		
		return msg;
	}

	@Override
	public ServiceResult sendMessage(MasterVO vo) {
		
		ServiceResult result = null;
		
		int rowcnt = dao.sendMessage(vo);
		
		if(rowcnt > 0) {
			result = ServiceResult.OK;		
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteMessage(MasterVO vo) {
		
		ServiceResult result = null; 
		
		int rowcnt = dao.deleteMessage(vo); 
		
		if(rowcnt > 0) { 
			result = ServiceResult.OK; 
		}else { 
			result = ServiceResult.FAILED; 
		} 
		return result;
	}

	@Override
	public void updateRead(MasterVO vo) {
		dao.updateRead(vo);
	}

}
