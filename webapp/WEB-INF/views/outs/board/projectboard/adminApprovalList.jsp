<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mypage.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
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
				<span class="sub-mypage-txt"> 관리페이지 ></span>
			</div>
				<div class="tab-wrap">
					<ul class="nav nav-tabs">
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/admin/member/mypageAdmin.do">회원 관리</a>
					  </li>
 					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/admin/blacklist/blackList.do">블랙리스트 관리</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/admin/blacklist/repBoardList.do">신고글 관리</a>
					  </li>
					  <li class="nav-item2">
					    <a class="nav-link active" href="${cPath}/outs/admin/board/projectboard/approvalList.do">승인 대기 프로젝트</a>
					  </li>
					</ul>
					
					<div class="tab-content" id="myTabContent">

					   <c:set var="projectList" value="${base.dataList }" />
						<div class="table-wrap m-top-20">
							<table class="table" style="min-height: 300px;">
								<thead class="thead-light">
									<tr>
										<th scope="col">NO</th>
										<th scope="col">클라이언트명</th>
										<th scope="col">아이디</th>
										<th scope="col">프로젝트명</th>
										<th scope="col">단가</th>
										<th scope="col">신청일</th>
									</tr>
								</thead>		
								
								<tbody>
									<c:choose>
										<c:when test="${not empty projectList }">
										<c:forEach items="${projectList }" var="project">
										<c:url value="/outs/board/projectboard/approvalView.do" var="viewURL">
											<c:param name="what" value="${project.boNum }" />
										</c:url>
											<tr class="linkBtn" data-gopage="${viewURL}" style="cursor: pointer;">
												<td>${project.rnum }</td>
												<td>${project.clientName }</td>
												<td>${project.memId }</td>
												<td>${project.projectName }</td>
												<td>${project.projectPriceValue }</td>
												<td>${project.writeDate }</td>
											</tr>
										</c:forEach>
										</c:when>
										<c:otherwise> 
											<tr> 
												<td colspan="4">승인 대기중인 프로젝트가 없습니다.</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>	
						<div class="paging-wrap">
							<div id="pagingArea">
								${base.pagingHTML}
							</div>	
						</div>
					   </div>
					    
<form id="searchForm" action="">
	<input type="hidden" name="page" />
</form>
<script>
	var searchForm = $("#searchForm").paging();
</script>
				</div>
			</div>
		</div>
	</div>