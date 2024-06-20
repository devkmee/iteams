package kr.or.ddit.iteams.common;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kr.or.ddit.iteams.common.vo.MasterVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SearchWordCollector {

	public static void makeSearchVO()
			throws IllegalAccessException, InvocationTargetException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		MasterVO searchVO = new MasterVO();
		HttpSession session = request.getSession();
		session.removeAttribute("searchVO");
		BeanUtils.populate(searchVO, request.getParameterMap());
		log.info("searchVO : {}", searchVO);
		session.setAttribute("searchVO", searchVO);

	}
}
