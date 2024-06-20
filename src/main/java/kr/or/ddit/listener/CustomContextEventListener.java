package kr.or.ddit.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomContextEventListener {
	
	@EventListener(ContextRefreshedEvent.class)
	public void contextRefresedEventHandler(ContextRefreshedEvent event) {
		WebApplicationContext context = (WebApplicationContext) event.getApplicationContext();
		ServletContext application = context.getServletContext();
//		List<Map<String, String>> workStateMap = new ArrayList<>();
//		List<Map<String, String>> workTypeMap = new ArrayList<>();
//		List<Map<String, String>> workPriorityMap = new ArrayList<>();
//		List<Map<String, String>> workProgressMap = new ArrayList<>();
		
		application.setAttribute("cPath", application.getContextPath());
		application.setAttribute("userList", new HashSet<>());
		log.info("{} 컨텍스트 시작 : {}", application.getContextPath(), context);
	}
}














