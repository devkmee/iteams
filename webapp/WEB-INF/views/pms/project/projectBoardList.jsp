<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="page-breadcrumb">
			
 	<div class="table-wrap">
 		<div class="">
			

			<br>
			<table class="table">
			  <thead class="thead-light">
			    <tr>
			      <th scope="col">프로젝트 게시판 번호</th>
			      <th scope="col">프로젝트 게시판제목</th>
			      <th scope="col">허가된 날짜</th>
			      <th scope="col">프로젝트 스케일</th>
			    </tr>
			  </thead>
			  <c:set var="projectList" value="${masterVO.dataList }" />
	<tbody>
		<c:choose>
			<c:when test="${not empty projectList }">
				<c:forEach items="${projectList }" var="project">
					<tr>
						<td>${project.boNum }</td>
						<td>
						<c:url value="/pms/project/${authMember.proNo}/projectView.do" var="viewURL">
								<c:param name="what" value="${project.proNo }" />
						</c:url>
						<a href="${viewURL }">${project.projectName }</a></td>
						<td>${project.permissionDate }</td>
						<td>${project.projectScale }</td>
						
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6">검색 조건에 맞는 게시글이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	
		</table>
		
		<div class="board-search-wrap">
			
			
			<br>
		</div>
		<div class="paging-wrap">
			<nav aria-label="Page navigation example">
			  <ul class="pagination">
			    <li class="page-item">
			    </li>
			    <form id="searchForm">
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="page" />
</form>
 				<div id="pagingArea">
					${pagingVO.pagingHTML }
				</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript">	
	$("[name='searchWord']").val("${searchVO.searchWord}");
	$("[name='searchType']").val("${searchVO.searchType}")
	let searchForm = $("#searchForm").paging();
</script>
			   
			  </ul>
		  </nav>	
		</div>

	</div>
</div>
