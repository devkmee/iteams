<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 

	<div class="member-basic-wrap">
	
		<div class="board-search-wrap">
				<div class="input-group mb-3">
				  <div id="searchUI" class="input-group-prepend">
				  	<select name="memRole" id="memRole" class="form-control mr-3">
				  		<option value="DEV">개발자</option>
				  		<option value="CLIENT">클라이언트</option>
				  		<option value="ADMIN">관리자</option>
				  	</select>
				    <select name="searchType" class="form-control mr-3">
						<option value>이름</option>
						<option value="memId">아이디</option>
					</select>
					<input type="text" name="searchWord" class="form-control">
					<input type="button" value="검색" id="searchBtn" class="btn btn-primary"/>
				  </div>
				</div>
			</div>
			
			<table class="table">
			  <thead class="thead-light">
			    <tr>
			      <th scope="col">NO</th>
			      <th scope="col">권한</th>
			      <th scope="col">아이디</th>
			      <th scope="col">이름</th>
			    </tr>
			  </thead>
				<!-- 조회 할 때 블랙리스트 보이게 하고 클릭하면 상세조회 페이지 관리자용으로 따로 만들어서 블랙리스트 관련 정보 조회하기 -->	  
		    <c:set var="memberList" value="${base.dataList }" />
			<tbody>
				<!-- 회원 조회 for문 -->
				<c:choose>
					<c:when test="${not empty memberList }">
						<c:forEach items="${memberList }" var="member">
							<tr>
								<td>${member.rnum }</td>
								<td>${member.memRoleValue }</td>
								<td>${member.memId }</td>
								<c:choose>
									<c:when test="${not empty member.devName }">
										<td>${member.devName }</td>
									</c:when>
									<c:when test="${not empty member.clientName }">
										<td>${member.clientName }</td>
									</c:when>
									<c:otherwise>
										<td>관리자</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</c:when>
					
					<c:otherwise>
						<tr>
							<td colspan="6">조건에 해당하는 회원을 찾을 수 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		
		<div class="paging-wrap">
		   <div id="pagingArea"> ${base.pagingHTML } </div>	
		</div>
	</div>		

<form id="searchForm">
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="memRole" />
	<input type="hidden" name="page" /> <br>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript">	
	
	$("[name='searchType']").val("${searchVO.searchType}");
	$("[name='searchWord']").val("${searchVO.searchWord}");
	let searchForm = $("#searchForm").paging();
	
	
</script>