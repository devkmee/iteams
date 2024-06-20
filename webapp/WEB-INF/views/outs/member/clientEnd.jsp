<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mypage.css">

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
					    <a class="nav-link" href="${cPath}/outs/member/mypageClientHire.do">모집 프로젝트</a>
					  </li>
					  <li class="nav-item2">
					    <a class="nav-link active" href="${cPath}/outs/member/mypageClientEnd.do">완료 프로젝트</a>
					  </li>
					</ul>
					
					<div class="tab-content" id="myTabContent">
			   	   	   <div>
							<!-- 완료프로젝트 -->
							<div class="project-wrap-txt m-bottom-10 m-top-10">완료된 프로젝트 &gt;</div>
							<div class="table-list">
								<table class="table">
									  <thead>
									    <tr>
									      <th scope="col">프로젝트명</th>
									      <th scope="col">완료일자</th>
									      <th scope="col">권한</th>
									      <th scope="col">참여일자</th>
									    </tr>
									  </thead>
									  <tbody>
								  <c:set var="endList" value="${end.dataList }"></c:set>
									  <c:choose>
									  	<c:when test="${empty endList }">
									  		<tr>
										      <td scope="row" colspan="4">
										      	완료한 프로젝트가 없습니다.
										      </td>
										    </tr>
									  	</c:when>
									  	<c:otherwise>
									  		<c:forEach items="${endList }" var="end" varStatus="status">
									  			<tr scope="row">
											      <td>${end.projectName }</td>
											      <td>${end.completeDate }</td>
											      <td>${end.authority }</td>
											      <td>${end.joinDate }</td>
											    </tr>
									  		</c:forEach>
									  	</c:otherwise>
									  </c:choose>
								  </tbody>
								</table>
							</div>
							
							<c:if test="${not empty endList }">
								<div class="paging-wrap">
								   <div id="pagingArea"> ${end.pagingHTML } </div>	
								</div>
							</c:if>
							<!-- 완료 프로젝트 -->
					    </div>
					    
 
				</div>
			</div>
		</div>
	</div>
</div>

<form id="pagingForm">
	<input type="hidden" name="page" />
</form>
<script>
let pagingForm = $("#pagingForm").paging();
</script>