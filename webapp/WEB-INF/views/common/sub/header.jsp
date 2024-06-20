<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 헤더영역 -->

<div class="top-box-wrap">
    <div class="topbar-wrap">
        <div class="topbar-left">
            <div class="teams-wrap">
 								
            	<c:if test="${not empty projectMemberList}">
            		<c:forEach items="${projectMemberList}" var="team">
	                <div class="teams-box">
	                    <div class="teams-profile">
	                    	<img class="teams-profile-img" src="${cPath}/imageRender.do?what=${team.devImg}" alt="${cPath}${team.defaultImage}">
	                    </div>
	                    <div>${team.realName}</div>
	                </div>
	                </c:forEach>    		
            	</c:if>
            	<c:if test="${empty projectMemberList}">
            		<div class="teams-box">
            			<div>소속 중인 팀원 없음.</div>
            		</div>
            	</c:if>      
            	
<!--                 <div class="teams-box"> -->
<!--                     <div class="teams-profile"> -->
<%--                	     <img class="teams-profile-img" src="<%=request.getContextPath()%>/resources/images/zzinew.jpg"> --%>
<!--                     </div> -->
<!--                     <div>PL</div> -->
<!--                 </div> -->
<!--                 <div class="teams-box"> -->
<!--                     <div class="teams-profile"> -->
<%--                    	 <img class="teams-profile-img" src="<%=request.getContextPath()%>/resources/images/sik.jpg"> --%>
<!--                     </div> -->
<!--                     <div>BA</div> -->
<!--                 </div> -->
<!--                 <div class="teams-box"> -->
<!--                     <div class="teams-profile"> -->
<%--                      <img class="teams-profile-img" src="<%=request.getContextPath()%>/resources/images/naa.jpg"> --%>
<!--                     </div> -->
<!--                     <div>TA</div> -->
<!--                 </div> -->
                
            </div>
        </div>
        <c:if test="${authMember.memRole eq 'DEV'}">
	        <script>
	        	$.ajax({
					url : "${cPath}/pms/${authMember.proNo}/workInCheck.do",
					dataType : "json",
					success : function(resp) {
	
					},
					error : function(xhr, errorResp, error) {
						console.log(xhr);
						console.log(errorResp);
						console.log(error);
					}
				})
	        </script>
        </c:if>
        <div class="topbar-right">
            <div class="profile-wrap"></div>
            <div class="logout-wrap">
            	<form method="post" id="workCheckForm"></form>
            	<c:if test="${authMember.memRole eq 'DEV'}">
	            	<c:if test="${workIn eq '0' and workOut eq '0'}">
		                <span data-gopage="${cPath }/pms/workcheck/${authMember.proNo}/workIn.do" class="workCheckBtn" style="cursor: pointer;">출근</span>
	            	</c:if>
	            	<c:if test="${workIn eq '1' and workOut eq '0'}">
		                <span data-gopage="${cPath }/pms/workcheck/${authMember.proNo}/workOut.do" class="workCheckBtn" style="cursor: pointer;">퇴근</span>
	            	</c:if>
	            	<c:if test="${workOut eq '1'}">
	            		<span>퇴근</span>
	            	</c:if>
            	</c:if>
	            <div>
	            <a href="<%=request.getContextPath()%>/pms/${authMember.proNo}/chat.do" target="_blank" onclick="window.open(this.href, 'popup',  'width=500,left=1500, height=700,location=no,menubar=no ,status=no,scrollbars=no'); return false;">
	            <img class="teams-profile-img" src="<%=request.getContextPath()%>/resources/images/chat.png">
	            </a>
	            </div>
            </div>
        </div>
        <script>
        	$(".workCheckBtn").on("click", function() {
        		let action = $(this).data("gopage");
				$("#workCheckForm").attr("action", action).submit()		
			})
        </script>
    </div>
    <div class="project-title-wrap">
        <span>${projectInfo.projectName}</span><br>
        <span class="sub-date-tilte">${projectInfo.createDate} ~ <c:if test="${not empty projectInfo.completeDate}">${projectInfo.completeDate}</c:if><c:if test="${empty projectInfo.completeDate}">진행중</c:if></span>
    </div>
</div>
<div class="pms-img">
    <img alt="" src="<%=request.getContextPath()%>/resources/images/pms-img.png">
</div>

