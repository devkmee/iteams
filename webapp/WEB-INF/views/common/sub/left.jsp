<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <div class="pms-left">
    <div class="logo-wrap" onclick="location.href='${cPath}/index.do';" style="cursor:pointer;">
        <div class="logo-box">
            <img src="<%=request.getContextPath()%>/resources/images/logo.png">
        </div>
    </div>
       <div class="myprofile-wrap">
       		<div class="myprofile-img-wrap">
       			<img class="myprofile-img" src="${cPath }/imageRender.do?what=${authMember.devImg }">
       		</div>
       		<div class="myprofile-name">
       			<c:if test="${authMember.authority eq 'PM'}">
	       			<span><a href="${cPath}/pms/project/${authMember.proNo}/projectList.do">${authMember.memId} 님</a></span>
       			</c:if>
       			<c:if test="${authMember.authority ne 'PM'}">
	       			<span>${authMember.memId} 님</span>
       			</c:if>
       			<span>${authMember.authority} / <c:if test="${empty authMember.devJob}">미정</c:if> <c:if test="${not empty authMember.devJob}">${authMember.devJob}</c:if></span>
       		</div>
       		<div class="set-img">
       			<c:if test="${authMember.authority eq 'PM' || authMember.authority eq 'PL'}">
       				<c:url var="updateUrl" value="/pms/project/${authMember.proNo}/projectUpdate.do">
       					<c:param name="what" value="${authMember.proNo}"></c:param>
       				</c:url>
	       			<a href="${updateUrl}"><img src="<%=request.getContextPath()%>/resources/images/set.png"></a>
       			</c:if>
       		</div>
       </div>
       <nav class="left-nav">
        <ul>
         <li class="left-menu">
                <a href="/iteams/pms/work/${authMember.proNo }/workList.do" aria-expanded="false">
                    <span class="icon-wrap">
                        <i class="far fa-file-alt"></i>
                    </span>
                   	일감
                </a>
            </li>
            <li class="left-menu">
                <a href="${cPath }/pms/work/${authMember.proNo }/gantt.do" aria-expanded="false">
                    <span class="icon-wrap">
                        <i class="fas fa-chart-pie"></i>
                    </span>
              	     간트차트
                </a>
            </li>
            <li class="left-menu">
                <a href="${cPath }/pms/timeline/${authMember.proNo}/timelineList.do">
                    <span class="icon-wrap">
                        <i class="far fa-file-alt"></i>
                    </span>
      		  	           작업내역
                </a>
            </li>
            <li class="left-menu">
                <a href="${cPath }/pms/schedule/${authMember.proNo }/scheduleList.do" aria-expanded="false">
                    <span class="icon-wrap">
                        <i class="far fa-calendar-alt"></i>
                    </span>
               		   캘린더
                </a>
            </li>
            <li class="left-menu">
                <a href="#a">
                    <span class="icon-wrap">
                        <i class="fas fa-table"></i>
                    </span>
           		         게시판
                </a>
                <ul>
                    <li>
                        <a href="/iteams/pms/board/infoshare/${authMember.proNo }/infoshareList.do" aria-expanded="false">- 정보공유 게시판</a>
                    </li>
                    <li>
                        <a href="/iteams/pms/board/request/${authMember.proNo }/requestList.do" aria-expanded="false">- 요구사항 게시판</a>
                    </li>
                    <li>
                        <a href="/iteams/pms/board/daily/${authMember.proNo }/dailyList.do" aria-expanded="false">- 일일업무 보고 게시판</a>
                    </li>
                </ul>
            </li>
            <li class="left-menu">
                <a href="${cPath }/pms/poject/${authMember.proNo }/webhardList.do">
                    <span class="icon-wrap">
                        <i class="far fa-folder"></i>
                    </span>
            	        웹하드
                </a>
            </li>
            <li class="left-menu">
                <a href="${cPath }/pms/documents/${authMember.proNo}/documentsList.do">
                    <span class="icon-wrap">
                        <i class="far fa-file-alt"></i>
                    </span>
                   	 문서함
                </a>
            </li>
            <li class="left-menu">
                <a href="/iteams/pms/team/${authMember.proNo }/teamList.do">
                    <span class="icon-wrap">
                        <i class="fas fa-users"></i>
                    </span>
                   	 팀원디렉토리
                </a>
            </li>
            <li class="left-menu">
                <a href="#" target="_blank" onclick="window.open('https://192.168.0.151:3000/${projectInfo.projectName}${authMember.proNo}?memId=${authMember.memId}','new','scrollbars=yes,resizable=no width=2000 height=1000, left=0,top=0')">
                    <span class="icon-wrap">
                        <i class="fas fa-video"></i>
                    </span>
                	     화상회의
                </a>
            </li>
            <li class="left-menu">
            	<c:if test="${authMember.authority eq 'PM' }">
	                <a href="${cPath }/pms/workcheck/${authMember.proNo}/dayoffList.do">
	                    <span class="icon-wrap">
	                        <i class="far fa-calendar-check"></i>
	                    </span>
	                  	  근태관리
	                </a>
                </c:if>
                <c:if test="${authMember.authority ne 'PM' }">
	                <a href="${cPath }/pms/workcheck/${authMember.proNo}/workcheckList.do">
	                    <span class="icon-wrap">
	                        <i class="far fa-calendar-check"></i>
	                    </span>
	                  	  근태관리
	                </a>                
                </c:if>
            </li>
        </ul>
    </nav>
    <div class="logout-btn-wrap">
        <button type="button" data-gopage="/iteams/logout.do" class="linkBtn pms-logout-btn">로그아웃</button>
    </div>

</div>