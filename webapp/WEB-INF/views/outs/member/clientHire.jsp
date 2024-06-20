<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/proListForClient.css"> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mypage.css">
<<style>
a:hover {
	color: #fff;
}
</style>

<div class="login-content-wrap">
	<div class="mypage-wrap">
		<div class="sub-mypage-wrap">
			<div class="sub-inner-box">
				<div class="main-outline-box-img m-right-10">
					<img alt="" src="/iteams/resources/images/mypage-img.png">
				</div>
			</div>
		</div>
		<div class=" mypage-tab-wrap">
			<div class="sub-mypage-txt">
				<span class="sub-mypage-txt"> 마이페이지 ></span>
			</div>
			<!-- 개인일정 -->
			<div class="flex-end">
				<a href="<%=request.getContextPath()%>/outs/schedule/scheduleList.do">
					<img class="m-right-2" src="/iteams/resources/images/mycal.png">
				</a>
			</div>
			<!-- 개인일정 -->
				<div class="tab-wrap">
					<ul class="nav nav-tabs">
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/member/mypageCllientInfo.do">회원정보</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/member/mypageClientBoard.do">나의 게시글</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link active" href="${cPath}/outs/member/mypageClientHire.do">모집 프로젝트</a>
					  </li>
					  <li class="nav-item2">
					    <a class="nav-link" href="${cPath}/outs/member/mypageClientEnd.do">완료 프로젝트</a>
					  </li>
					</ul>
					
			<div class="tab-content" id="myTabContent">
					
				<div class="project-wrap">
					<div class="project-wrap-txt">초대한 개발자 ></div>
						<div class="table-list">
							<table class="table">
								 <thead>
									    <tr>
									      <th scope="col">프로젝트명</th>
									      <th scope="col">개발자명</th>
									      <th scope="col">역할/권한</th>
									      <th scope="col">초대일자</th>
									      <th scope="col">상태</th>
									    </tr>
								</thead>
								 <tbody>
								 	  <c:set var="inviteList" value="${invite.dataList }"></c:set>
									  <c:choose>
									  	<c:when test="${empty inviteList }">
									  		<tr>
										      <td scope="row" colspan="7">
										      	초대 한 개발자가 없습니다
										      </td>
										    </tr>
									  	</c:when>
									  	<c:otherwise>
									  		<c:forEach items="${inviteList }" var="invite" varStatus="status">
									  			<tr scope="row">
											      <td>${invite.projectName }</td>
											      <td>${invite.devName }</td>
											      <td>${invite.inviteAuth }</td>
											      <td>${invite.inviteDate }</td>
											    <c:choose>
											    	<c:when test="${invite.inviteState eq '0'}">
											    		<td><h5><span class="badge badge-secondary" style="width:50px; padding:5px;">${invite.inviteStateValue }</span></h5></td>	
											    	</c:when>
											    	<c:when test="${invite.inviteState eq '1'}">
											    		<td><h5><span class="badge badge-secondary2" style="width:50px; padding:5px;">${invite.inviteStateValue }</span></h5></td>	
											    	</c:when>
											    	<c:when test="${invite.inviteState eq '2'}">
											    		<td><h5><span class="badge badge-danger" style="width:50px; padding:5px;">${invite.inviteStateValue }</span></h5></td>	
											    	</c:when>
											    </c:choose>
												<c:if test="">
											      <td><input type="button" class="btn btn-gray btn-sm" value="수락" onclick="resultSubmit(${status.index},'수락','acceptForm')"></td>
											      <td><input type="button" class="btn btn-gray btn-sm" value="거절" onclick="resultSubmit(${status.index},'거절','refuseForm')"></td>
											     </c:if>
											    </tr>
									  		</c:forEach>
									  	</c:otherwise>
									  </c:choose>
								 </tbody>
							</table>
						</div>
				</div>	
					
					
					
				<!-- 최근 모집 중인 프로젝트 -->
				<div class="project-wrap">
					<div class="project-wrap-txt">최근 모집 중인 프로젝트 ></div>
					
					<div class="employment-wrap">
						<c:set var="projectList" value="${board.dataList }"></c:set> 
						<c:choose> 
							<c:when test="${empty projectList}">
								<div class="project-list-box">
									<sapn>모집 중인 프로젝트가 없습니다.</sapn>
								</div>
							</c:when>
							
							<c:otherwise>
								<c:forEach items="${projectList }" var="proj" varStatus="state">
									<c:url value="/outs/board/projectboard/projectView.do" var="selectURL">
			                    		<c:param name="what" value="${proj.boNum }" />
									</c:url>
									<c:url value="/outs/mypage/hire/hireList.do" var="applyURL">
										<c:param name="boNum" value="${proj.boNum}"></c:param>
									</c:url>
										<c:if test="${state.index mod 3 eq 0}">
											</div>
										</c:if>
										<c:if test="${state.index eq 0 || state.index mod 3 eq 0}">
				                 			<div class="project-list-box-wrap">
				                 		</c:if>
											<div id="projDiv" class="linkBtn project-list-box" data-gopage="${selectURL}">
												<c:choose>
													<c:when test="${not empty proj.appNy}">
														<h5><a style="float: right; padding:5px;" href="${cPath}/outs/mypage/hire/hireList.do?boNum=${proj.boNum}" class="badge badge-secondary2">지원자 확인</a></h5>
													</c:when>
													<c:otherwise>
														<h5><a style="float:right; padding:5px;" href="#" class="badge badge-secondary">모집중</a></h5>
													</c:otherwise>
												</c:choose>
												<span>${proj.clientName }</span>
												<span>${proj.projectName }</span>
												<span>모집인원 : ${proj.projectRecruit}명 | 지원자 : ${proj.appCount}명</span>
											<c:choose>
												<c:when test="${not empty proj.limitModifyDate}">
													<sapn class="project-list-date">공고기한 : ${proj.permissionDate} ~ ${proj.limitModifyDate}</sapn>
												</c:when>
												<c:otherwise>
													<sapn class="project-list-date">공고기한 : ${proj.permissionDate} ~ ${proj.limitDate}</sapn>
												</c:otherwise>
											</c:choose>
											<ul>
											 <c:choose>
										      	  <c:when test="${empty proj.projectTechValue }">
											      	<li>#${proj.projectTech }</li>
											      </c:when>
											      <c:otherwise>
											      	<li>
												      	<c:forEach items="${proj.projectTechValue }" var="tech" begin="0" end="3">
												      		#${tech }
												      	</c:forEach>
											      	</li>
											      </c:otherwise>
										      </c:choose>
										    </ul>
										</div>
									<c:if test="${status.last}">
				        </div>
				                 	</c:if>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						
						
						
						
					</div>
						<c:if test="${not empty projectList }">
								<div class="paging-wrap">
									<div id="pagingArea"> 
										<div class="paging-wrap">
											<div id="pagingArea"> ${board.pagingHTML} </div>	
										</div>
									</div>	
								</div>
						</c:if>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<form id="searchForm">
	<input type="hidden" name="page" />
</form>
<script>
let searchForm = $("#searchForm").paging();
</script>