<%@ page import="kr.or.ddit.iteams.common.vo.MasterVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
function onClickLogin() {
   location.href = CONTEXT_PATH + '/outs/login/login.do';
}
function onClickJoin() {
	   location.href = CONTEXT_PATH + '/outs/login/join.do';
}
</script>
<!-- 헤더영역 -->
<header class="header">
   <div class="header-wrap">
      <div class="logo-wrap">
         <a href="<%=request.getContextPath()%>/index.do"><img
            src="<%=request.getContextPath()%>/resources/images/logo.png"></a>
      </div>
      <div class="menu-wrap">
         <c:choose>
            <c:when test="${authMember.memRole eq 'DEV'}">
               <nav>
                  <ul>
                  	<c:if test="${not empty authMember.proNo}">
                  	 	<c:set target="${authMember.proNo}" var="proNo" value="${authMember.proNo}"></c:set>                  	
                  	</c:if>
                  	<c:if test="${empty authMember.proNo}">
                  	 	<c:set var="proNo" value="none"></c:set>                  	
                  	</c:if>
                     <li><a href="<%=request.getContextPath()%>/outs/board/projectboard/projectList.do">프로젝트 공고</a></li>
                     <c:if test="${not empty authMember.proNo}">
                     	<li><a href="<%=request.getContextPath()%>/pms/${authMember.proNo}/main.do">나의 프로젝트</li>
                     </c:if>
                     <c:if test="${empty authMember.proNo}">
                     	<li><a onclick="alert('진행중인 프로젝트가 없습니다.')" href="#" title="진행중인 프로젝트가 없습니다.">나의 프로젝트</li>
                     </c:if>
                     <li><a href="${cPath}/outs/company/companyList.do">기업정보</a></li>
                     <li><a href="<%=request.getContextPath()%>/outs/board/free/freeBoardList.do">커뮤니티</a></li>
                     <li><a href="<%=request.getContextPath()%>/outs/board/notice/noticeList.do">고객센터</a></li>
                  </ul>
                  
               </nav>
               <div class="login-info-wrap">
                  <span><a class="m-right-5" href="<%=request.getContextPath()%>/outs/member/mypageDevInfo.do">${authMember.memId}님</a></span>
                  <a href="<%=request.getContextPath()%>/outs/board/projectboard/scrapList.do"><img class="m-right-2" src="<%=request.getContextPath()%>/resources/images/bookmark.png"></a>
               	  <a href="<%=request.getContextPath()%>/outs/message/messageList.do"><img class="m-right-5" src="<%=request.getContextPath()%>/resources/images/note.png"></a>
               	  <span class="badge badge-pill badge-info receiveMsg"></span>
                  <button class="btn-default-white linkBtn" data-gopage="${cPath }/logout.do">로그아웃</button>
               </div>
            </c:when>
            <c:when test="${authMember.memRole eq 'CLIENT'}">
               <nav>
                  <ul>
                     <li><a href="<%=request.getContextPath()%>/outs/board/projectboard/projectList.do">프로젝트 공고</a></li>
                     <li><a href="<%=request.getContextPath() %>/pms/project/${authMember.proNo}/projectList.do">나의 프로젝트</a></li>
                     <li><a href="<%=request.getContextPath()%>/outs/hire/profileList.do">팀원검색</a></li>
                     <li><a href="<%=request.getContextPath()%>/outs/board/free/freeBoardList.do">커뮤니티</a></li>
                     <li><a href="<%=request.getContextPath()%>/outs/board/notice/noticeList.do">고객센터</a></li>
                  </ul>
               </nav>
               <div class="login-info-wrap">
                  <span><a class="m-right-5" href="<%=request.getContextPath()%>/outs/member/mypageCllientInfo.do">${authMember.memId}님</a></span>
                  <a href="${cPath }/outs/hire/profilescrabList.do"><img class="m-right-2" src="<%=request.getContextPath()%>/resources/images/bookmark.png"></a>
               	  <a href="<%=request.getContextPath()%>/outs/message/messageList.do"><img class="m-right-5" src="<%=request.getContextPath()%>/resources/images/note.png"></a>
               	  <span class="badge badge-pill badge-info receiveMsg"></span>
                  <button class="btn-default-white linkBtn" data-gopage="${cPath }/logout.do">로그아웃</button>
               </div>
            </c:when>
            <c:when test="${authMember.memRole eq 'ADMIN'}">
               <nav>
                  <ul>
                     <li><a href="<%=request.getContextPath()%>/outs/board/projectboard/projectList.do">프로젝트 공고</a></li>
                     <li><a href="${cPath}/outs/company/companyList.do">기업정보</a></li>
                     <li><a href="<%=request.getContextPath()%>/outs/hire/profileList.do">팀원검색</a></li>
                     <li><a href="<%=request.getContextPath()%>/outs/board/free/freeBoardList.do">커뮤니티</a></li>
                     <li><a href="<%=request.getContextPath()%>/outs/board/notice/noticeList.do">고객센터</a></li>
                  </ul>
               </nav>
               <div class="login-info-wrap">
                  <span><a class="m-right-5" href="${cPath }/outs/admin/member/mypageAdmin.do">${authMember.memId}님</a></span>
                  <button class="btn-default-white linkBtn" data-gopage="${cPath }/logout.do">로그아웃</button>
               </div>   
            </c:when>
            <c:otherwise>
               <nav>
                  <ul>
                     <li><a href="<%=request.getContextPath()%>/outs/board/projectboard/projectList.do">프로젝트 공고</a></li>
                     <li><a href="${cPath}/outs/company/companyList.do">기업정보</a></li>
                     <li><a href="<%=request.getContextPath()%>/outs/board/free/freeBoardList.do">커뮤니티</a></li>
                     <li><a href="<%=request.getContextPath()%>/outs/board/notice/noticeList.do">고객센터</a></li>
                  </ul>
               </nav>
               <div class="login-info-wrap">
                  <button class="btn-default-blue" onclick="onClickLogin()">로그인</button>
                  <button class="btn-default-white" onclick="onClickJoin()">회원가입</button>
               </div>
            </c:otherwise>
         </c:choose>
      </div>
   </div>
</header>

<script>
	$(function() {
		
		let memId = "${authMember.memId}";
		
		if(memId) {
			$.ajax({
				url : "${cPath}/outs/getMessageCount.do",
				dataType : "json",
				success : function(resp) {
					var count = resp.count
					console.log(count)
					if(count !== 0) {
						$(".receiveMsg").text(count);
					}
				},
				error : function(xhr, errorResp, error) {
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			})
		}
	})
</script>