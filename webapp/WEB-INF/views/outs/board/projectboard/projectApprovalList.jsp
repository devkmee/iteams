<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div>
	<div class="sub-wrap">
		<div class="sub-inner-box">
			<div class="sub-inner-txt">
				<span class="sub-txt">프로젝트 공고<br>
					<span class="project-txt-lighter">커리어 관리가 즐거워지고, 오늘보다 더 나은 내일을 꿈꿀 수 있는 곳<br>아이팀즈입니다.</span>
				</span>
			</div>
			<div class="main-outline-box-img m-right-10">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/project-img.png">
			</div>
		</div>
	</div>
	<section class="section-wrap">
		<div class="project-list-title m-bottom-10 m-top-30">
			<span>승인 대기중인 프로젝트 ></span> 
		</div>

		<c:set var="projectList" value="${base.dataList }" />
		
		<div class="table-wrap">
			<table class="table">
				<thead class="thead-light">
					<tr>
						<th scope="col">회사 이름</th>
						<th scope="col">프로젝트명</th>
						<th scope="col">단가</th>
						<th scope="col">작성자(클라이언트 회원 ID)</th>
					</tr>
				</thead>		
				
				<tbody>
					<c:choose>
						<c:when test="${not empty projectList }">
						<c:forEach items="${projectList }" var="project">
							<tr>
								<td>${project.clientName }</td>
								<td>
									<c:url value="/outs/board/projectboard/approvalView.do" var="viewURL">
										<c:param name="what" value="${project.boNum }" />
									</c:url>
									<a href="${viewURL }">${project.projectName }</a>
								</td>
								<td>${project.projectPrice }</td>
								<td>${project.memId }</td>
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
	
	</section>

<form id="searchForm" action="">
	<input type="hidden" name="page" />
</form>
</div>	

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>

<script>
	var searchForm = $("#searchForm").paging();
</script>

