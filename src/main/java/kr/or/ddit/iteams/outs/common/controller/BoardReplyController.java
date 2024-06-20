package kr.or.ddit.iteams.outs.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.ReplyTotalVO;
import kr.or.ddit.iteams.outs.common.service.BoardReplyService;

@Controller
public class BoardReplyController {
	
	@Inject
	private BoardReplyService service;
	
	@GetMapping(value="/outs/board/free/replyList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object getReplyList(@Validated @ModelAttribute MasterVO masterVO) {
		masterVO.setCurrentPage(masterVO.getPage());
		service.selectReplyList(masterVO);		
		return masterVO;
	}
	
	@PostMapping(value="/outs/board/free/replyInsert.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object insertReply(@Validated @ModelAttribute ReplyTotalVO replyVO
			, @Authenticated MasterVO authMember) {
		replyVO.setRepWriter(authMember.getMemId());
		service.insertReply(replyVO);
		ServiceResult result = replyVO.getResult();
		String message = result == ServiceResult.OK ? "댓글을 등록했습니다." : "댓글 등록에 실패했습니다.";
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("message", message);
		return resultMap;
	}
	
	@PostMapping(value="/outs/board/free/replyUpdate.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object updateReply(@Validated @ModelAttribute ReplyTotalVO replyVO) {
		service.updateReply(replyVO);
		ServiceResult result = replyVO.getResult();
		String message = result == ServiceResult.OK ? "댓글을 수정했습니다." : "댓글 수정에 실패했습니다.";
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("message", message);
		return resultMap;
	}
	
	@PostMapping(value="/outs/board/free/replyDelete.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object deleteReply(@Validated @ModelAttribute ReplyTotalVO replyVO) {
		service.deleteReply(replyVO);
		ServiceResult result = replyVO.getResult();
		String message = result == ServiceResult.OK ? "댓글을 삭제했습니다." : "댓글 삭제에 실패했습니다.";
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("message", message);
		return resultMap;
	}
}
