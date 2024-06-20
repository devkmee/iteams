package kr.or.ddit.advice;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.PMSTotalVO;
import kr.or.ddit.iteams.pms.common.dao.PMSOthersDAO;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
public class TimeLineAdvice {
	
	@Inject
	private PMSOthersDAO dao;
	
	@Pointcut("@annotation(kr.or.ddit.annotations.TimeLine)")
	public void pointCut() {};
	
	@Around("pointCut()")
	public Object getLog(ProceedingJoinPoint joinPoint) throws Throwable {

		Object retValue = null;

		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			MasterVO authMember = (MasterVO) session.getAttribute("authMember");
			String worker = authMember.getDevName() == null ? authMember.getManagerName() : authMember.getDevName();
			String suffix = request.getParameter("who") == null ? "?what=" : "?who=";
			String viewParam = request.getParameter("who") == null ? request.getParameter("what") : request.getParameter("who");
			String requestURL = request.getRequestURL().toString();
			String workURL = null;
			String workContent = null;

			Object commandObject = joinPoint.getArgs()[0];
			MasterVO masterVO = new MasterVO();
			if(commandObject instanceof MasterVO) {
				masterVO = (MasterVO) commandObject;
			} else if(commandObject instanceof PMSTotalVO) {
				masterVO = (MasterVO) commandObject;
			}
			
			if(StringUtils.contains(requestURL, "Delete.do")) {
				dao.deleteTimeLine(masterVO);
			}

			retValue = joinPoint.proceed(joinPoint.getArgs());

			masterVO.setMemId(authMember.getMemId());
			masterVO.setTimelineName(worker);
			masterVO.setProNo(authMember.getProNo());

			if(StringUtils.contains(requestURL, "Insert.do")) {
				if(StringUtils.contains(requestURL, "/board")) {
					masterVO.setWorkType("BOARD");
					workContent = worker + " 님이 " + masterVO.getBoTitle() + " 게시글을 작성했습니다.";
					workURL = StringUtils.replace(requestURL, "Insert.do", "View.do") + suffix + masterVO.getBoNum();
				}
				if(StringUtils.contains(requestURL, "/work")) {
					masterVO.setWorkType("WORK");
					workContent = worker + " 님이 " + masterVO.getWorkTitle() + " 일감을 작성했습니다.";
					workURL = StringUtils.replace(requestURL, "Insert.do", "View.do") + suffix + masterVO.getWorkNum();
				}
				if(StringUtils.contains(requestURL, "/schedule")) {
					masterVO.setWorkType("SCHEDULE");
					workContent = worker + " 님이 " + masterVO.getPlanContent() + " 일정을 등록했습니다.";
					workURL = StringUtils.replace(requestURL, "Insert.do", "List.do");
				}
				if(StringUtils.contains(requestURL, "/documents")) {
					masterVO.setWorkType("DOCUMENT");
					workContent = worker + " 님이 " + masterVO.getDocTitle() + " 문서를 작성했습니다.";
					workURL = StringUtils.replace(requestURL, "Insert.do", "List.do");
				}
				log.info("작업자 : {} , 작업 내용 : {}, 작업URL : {}", worker, workContent, workURL);
			} else if(StringUtils.contains(requestURL, "Update.do")) {
				if(StringUtils.contains(requestURL, "/board"))  {
					masterVO.setWorkType("BOARD");
					workContent = worker + " 님이 " + masterVO.getBoTitle() + " 게시글을 수정했습니다.";
					workURL = StringUtils.replace(requestURL, "Update.do", "View.do") + suffix + masterVO.getBoNum();
				}
				if(StringUtils.contains(requestURL, "/work"))  {
					masterVO.setWorkType("WORK");
					workContent = worker + " 님이 " + masterVO.getWorkTitle() + " 일감을 수정했습니다.";
					workURL = StringUtils.replace(requestURL, "Update.do", "View.do") + suffix + masterVO.getWorkNum();
				}
				if(StringUtils.contains(requestURL, "/schedule"))  {
					masterVO.setWorkType("SCHEDULE");
					workContent = worker + " 님이 " + masterVO.getPlanContent() + " 일정을 수정했습니다.";
					workURL = StringUtils.replace(requestURL, "Update.do", "List.do");
				}
				if(StringUtils.contains(requestURL, "/documents"))  {
					masterVO.setWorkType("DOCUMENT");
					workContent = worker + " 님이 " + masterVO.getDocTitle() + " 문서를 수정했습니다.";
					workURL = StringUtils.replace(requestURL, "Update.do", "View.do") + suffix + masterVO.getDocNum();
				}
				log.info("작업자 : {} , 작업 내용 : {}, 작업URL : {}", worker, workContent, workURL);

			} else if(StringUtils.contains(requestURL, "Delete.do")){
				if(StringUtils.contains(requestURL, "/board")) {
					masterVO.setWorkType("BOARD");
					masterVO.setBoNum("");
					workContent = worker + " 님이 " + masterVO.getBoTitle() +" 게시글을 삭제했습니다.";
					workURL = StringUtils.replace(requestURL, "Delete.do", "List.do");
				}
				if(StringUtils.contains(requestURL, "/work")) {
					masterVO.setWorkType("WORK");
					masterVO.setWorkNum("");
					workContent = worker + " 님이 " + masterVO.getWorkTitle() +" 일감을 삭제했습니다.";
					workURL = StringUtils.replace(requestURL, "Delete.do", "List.do");
				}
				if(StringUtils.contains(requestURL, "/schedule")) {
					masterVO.setWorkType("SCHEDULE");
					masterVO.setPlanNum("");
					workContent = worker + " 님이 " + masterVO.getPlanContent() +" 일정을 삭제했습니다.";
					workURL = StringUtils.replace(requestURL, "Delete.do", "List.do");
				}
				if(StringUtils.contains(requestURL, "/documents")) {
					masterVO.setWorkType("DOCUMENT");
					masterVO.setDocNum("");
					workContent = worker + " 님이 " + masterVO.getDocTitle() +" 문서를 삭제했습니다.";
					workURL = StringUtils.replace(requestURL, "Delete.do", "List.do");
				}
				log.info("작업자 : {} , 작업 내용 : {}, 작업URL : {}", worker, workContent, workURL);
			}

			masterVO.setTimelineContent(workContent);
			masterVO.setTimelineUrl(workURL);
			dao.insertTimeLine(masterVO);
		}
		catch (Exception e) {
			if(retValue != null) {
				return retValue;
			}
			throw new RuntimeException(e);
		} finally {
			if(retValue != null) {
				return retValue;
			}
		}

		return retValue;

	}
}
