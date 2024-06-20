package kr.or.ddit.iteams.pms.chatting;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.iteams.common.vo.ChattingVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.vo.MasterVOWrapper;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SocketHandler extends TextWebSocketHandler {
	
	@Inject
	private WebApplicationContext context;
	
	Map<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
	//한개의 프로젝트에 대한 모든 정보 매핑
	//key = 프로젝트 번호
	//value 의 맵 / key : memberMap => value : 프로젝트 소속 웹소켓세션 맵 , key : state => value : 시작,종료 확인값 
	// key : messageList => 메세지를 기록해둔 리스트
	Map<String, Map<String, Object>> projectListMap = new HashMap<>();
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws JsonParseException, JsonMappingException, IOException {
//		Map<String,Map<String,Object>>;
		//메시지 발송
		String msg = message.getPayload();
		JSONObject obj = jsonToObjectParser(msg);
		boolean startValid = true;
		String sendEvent = (String) obj.get("state");
		if(sendEvent != null) {
			if(sendEvent.equals("/start")) {
				startValid = startHandler(session);
				if(!startValid) {
					obj.put("msg", "이미 기록 회의 중 입니다.");
				}
			} else if(sendEvent.equals("/end")) {
				boolean valid = endHandler(session);
				if(!valid)  {
					obj.put("msg", "회의 기록이 시작하지 않았습니다.");
				} else {
					obj.put("msg", "회의 기록을 종료합니다.");					
				}
			} else if(sendEvent.equals("/out")) {
				outHandler(session);
			}
		}
			
		Authentication authentication =  (Authentication) session.getPrincipal();
		Object principal = authentication.getPrincipal();
		if(principal instanceof MasterVOWrapper) {
			MasterVO authMember = ((MasterVOWrapper) principal).getAuthMember();
			Map<String, Object> detailMap = projectListMap.get(authMember.getProNo());
			if(detailMap != null) {
				String curState = (String) detailMap.get("state");
				if("start".equals(curState)) {
					String flag = (String) obj.get("flag");
					if(!"/first".equals(flag)) {
						List<ChattingVO> messageList = (List<ChattingVO>) detailMap.get("messageList");
						ChattingVO chatVO = new ChattingVO();
						chatVO.setMemId(authMember.getMemId());
						chatVO.setMessage((String) obj.get("msg"));
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
						chatVO.setTimeStamp(sdf.format(new Date()));
						messageList.add(chatVO);						
					}
				}
			}
		}
		
		if(sendEvent != null) {
			if(sendEvent.equals("/start")) {
				if(startValid) {
					obj.put("msg", "회의 기록을 시작합니다.");					
				}
			}
		}
		
		if(principal instanceof MasterVOWrapper) {
			MasterVO authMember = ((MasterVOWrapper) principal).getAuthMember();
			Map<String, Object> detailMap = projectListMap.get(authMember.getProNo());
			Map<String, WebSocketSession> memberMap = (Map<String, WebSocketSession>) detailMap.get("memberMap");
			
			for(String memId : memberMap.keySet()) {
				log.info("받는사람 아이디 : {} / 메세지 : {}", memId, obj.get("msg"));
				WebSocketSession memberSession = memberMap.get(memId);
				try {
					memberSession.sendMessage(new TextMessage(obj.toJSONString()));
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		//소켓 연결
		super.afterConnectionEstablished(session);
		sessionMap.put(session.getId(), session);
		
		JSONObject obj = new JSONObject();
		obj.put("type", "getId");
		obj.put("sessionId", session.getId());
		
		Authentication authentication =  (Authentication) session.getPrincipal();
		Object principal = authentication.getPrincipal();
		if(principal instanceof MasterVOWrapper) {
			MasterVO authMember = ((MasterVOWrapper) principal).getAuthMember();
			Map<String, Object> detailMap = projectListMap.get(authMember.getProNo());
			if(detailMap == null) {
				log.info("웹소켓 접속 : {}", authMember.getMemId());
				Map<String, Object> newDetailMap = new HashMap<>();
				projectListMap.put(authMember.getProNo(), newDetailMap);
				Map<String ,WebSocketSession> newMemberMap = new HashMap<>();
				newMemberMap.put(authMember.getMemId(), session);
				newDetailMap.put("memberMap", newMemberMap);
			} else {
				Map<String, WebSocketSession> memberMap = (Map<String, WebSocketSession>) detailMap.get("memberMap");
				if(memberMap == null) {
					log.info("웹소켓 접속 : {}", authMember.getMemId());
					memberMap = new HashMap<>();
					memberMap.put(authMember.getMemId(), session);
					detailMap.put("memberMap", memberMap);
				} else {
					log.info("웹소켓 접속 : {}", authMember.getMemId());
					memberMap.put(authMember.getMemId(), session);
				}
			}
		}
		
		session.sendMessage(new TextMessage(obj.toJSONString()));	
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		//소켓 종료
		sessionMap.remove(session.getId());
		
		Authentication authentication =  (Authentication) session.getPrincipal();
		Object principal = authentication.getPrincipal();
		
		super.afterConnectionClosed(session, status);
		
		if(principal instanceof MasterVOWrapper) {
			MasterVO authMember = ((MasterVOWrapper) principal).getAuthMember();
			Map<String, Object> detailMap = projectListMap.get(authMember.getProNo());
			if(detailMap == null) {
				return;
			} else {
				Map<String, WebSocketSession> memberMap = (Map<String, WebSocketSession>) detailMap.get("memberMap");
				if(memberMap == null) {
					return;
				} else {
					memberMap.remove(authMember.getMemId());
				}
				
			}
		}
		
	}
	
	private static JSONObject jsonToObjectParser(String jsonStr) {
		
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		
		try {
			obj = (JSONObject) parser.parse(jsonStr);
		} catch (ParseException e) {
			
			e.printStackTrace();
			
		}
		return obj;
		
	}
	
	private void outHandler(WebSocketSession session) {
		Authentication authentication =  (Authentication) session.getPrincipal();
		Object principal = authentication.getPrincipal();
		if(principal instanceof MasterVOWrapper) {
			MasterVO authMember = ((MasterVOWrapper) principal).getAuthMember();
			Map<String, Object> detailMap = projectListMap.get(authMember.getProNo());
			if(detailMap == null) {
				return;
			} else {
				String curState = (String) detailMap.get("state");
				if(!"end".equals(curState)) {
					return;
				} else {
					ServletContext application = context.getServletContext();
					String key = "회의기록" + authMember.getProNo();
					application.removeAttribute(key);
					detailMap.remove("messageList");
				}
			}
		}
	}
	
	private boolean startHandler(WebSocketSession session) throws IOException {
		Authentication authentication =  (Authentication) session.getPrincipal();
		Object principal = authentication.getPrincipal();
		if(principal instanceof MasterVOWrapper) {
			MasterVO authMember = ((MasterVOWrapper) principal).getAuthMember();
			Map<String, Object> detailMap = projectListMap.get(authMember.getProNo());
			if(detailMap == null) {
				return true;
			}
			
			String curState = (String) detailMap.get("state");
						
			if("start".equals(curState)) {
				JSONObject obj = new JSONObject();
				obj.put("msg", "이미 기록 회의 중 입니다.");
				session.sendMessage(new TextMessage(obj.toJSONString()));
				return false;
			} else {
				List<ChattingVO> messageList = new ArrayList<>();
				String flag = "start";
				detailMap.put("messageList", messageList);
				detailMap.put("state", flag);
				ServletContext application = context.getServletContext();
				String key = "회의기록" + authMember.getProNo();
				application.setAttribute(key, messageList);
				return true;
			}
			
		}	
		return true;
	}
	
	private boolean endHandler(WebSocketSession session) throws IOException {
		Authentication authentication =  (Authentication) session.getPrincipal();
		Object principal = authentication.getPrincipal();
		if(principal instanceof MasterVOWrapper) {
			MasterVO authMember = ((MasterVOWrapper) principal).getAuthMember();
			Map<String, Object> detailMap = projectListMap.get(authMember.getProNo());
			if(detailMap == null) {
				return true;
			}
			String curState = (String) detailMap.get("state");
			if(!"start".equals(curState)) {
				JSONObject obj = new JSONObject();
				obj.put("msg", "회의 기록이 시작하지 않았습니다.");
				session.sendMessage(new TextMessage(obj.toJSONString()));
				return false;
			} else {
				String state = "end";
				List<ChattingVO> msgList = (List<ChattingVO>) detailMap.get("messageList");
				log.info("메세지 리스트 : {}", msgList.toString());
				detailMap.put("state", state);
				return true;
			}
	
		}
		return true;
	}
}