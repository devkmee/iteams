<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="page-breadcrumb">
 	<div class="table-wrap text-align-center">
 		<div class="flex-end">
				<input type="button" id="choiceBtn" value="새프로젝트 생성" class="linkBtn btn pms-btn width-150" 
						data-gopage="${cPath }/pms/project/${authMember.proNo }/projectInsert.do"/>
			</div>
			<br>
			<table class="table">
			  <thead class="thead-light">
			    <tr class="tr-gray">
			      <th scope="col">프로젝트 번호</th>
			      <th scope="col">프로젝트 제목</th>
			      <th scope="col">날짜</th>
			      <th scope="col">현재상태</th>
			      <th scope="col">클라이언트</th>
			    </tr>
			  </thead>
			  <c:set var="projectList" value="${masterVO.dataList }" />
	<tbody>
		<c:choose>
			<c:when test="${not empty projectList }">
				<c:forEach items="${projectList }" var="project">
					<tr>
						<td>${project.proNo }</td>
						<td class="text-left">
						<c:url value="/pms/project/${authMember.proNo}/projectView.do" var="viewURL">
								<c:param name="what" value="${project.proNo }" />
						</c:url>
						<a href="${viewURL }">${project.projectName }</a></td>
						<td>${project.createDate }</td>
						<td><c:if test="${project.projectState == 'ING'}">진행중</c:if><c:if test="${project.projectState == 'END'}">완료</c:if></td>
						<td>${project.managerName }</td>
						
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
			    
<div class="modal fade" id="choiceModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">프로젝트를 선택해 주세요</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div id="modalBody" class="modal-body">
      		<select name="project">
      			<option value>프로젝트 선택</option>
      			<c:forEach items="${client.dataList }" var="proj">
      				<option value="${proj.proNo }">${proj.projectName }</option>
      			</c:forEach>
      		</select>
      </div>
      <div class="modal-footer">
        <button type="button" class="pms-btn btn-secondary" data-dismiss="modal">닫기</button>
        <button type="button" id="submitBtn" class="pms-btn btn-primary">선택</button>
      </div>
    </div>
  </div>
</div>
<form id="choiceForm" action="${cPath}/pms/project/{proNo}/projectInsert.do" method="post">
	<input type="hidden" name="proNo">
</form>

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
<script>
	let choiceModal = $("#choiceModal");
	let modalBody = $("#modalBody");
	let choiceForm = $("#choiceForm");
	
	$("#choiceBtn").on("click", function () {
		inviteModal.modal("show")
	});
	
	inviteModal.on('hide.bs.modal', function (event) {
		inviteForm.find(":input[name='proNo']").empty();
		
	})
	
	$("#submitBtn").on('click', function (event) {
		let selectedProNo = modalBody.find("[name='project']").val()
		let selectedAuthority = modalBody.find("[name='authority']").val()
		inviteForm.find(":input[name='proNo']").val(selectedProNo);
		
		console.log(selectedProNo)
		console.log(selectedAuthority)
		inviteForm.submit();
		inviteModal.modal("hide");
	})
	
</script>
