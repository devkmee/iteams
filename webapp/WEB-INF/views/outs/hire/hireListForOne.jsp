<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/hireList.css">
<div class="sub-wrap">
	<div class="sub-inner-box">
		<div class="sub-inner-txt">
			<span class="sub-txt">지원자 리스트<br>
				<span class="project-txt-lighter">모든 채용이 기대감으로 시작해 서로 악수하는 반가움이 되는 곳<br>아이팀즈입니다.</span>
			</span>
		</div>
		<div class="main-outline-box-img m-right-10">
			<img alt="" src="/iteams/resources/images/hireList-img.png">
		</div>
	</div>
</div>
<section class="section-wrap">
	<div class="project-list-title m-bottom-10 m-top-30">
		<span>${applyPro.projectName}</span><br />
		<span>지원자를 클릭하면 프로필과 포토폴리오를 확인 할 수 있습니다</span>
	</div>
	<div class="hirelist-wrap">
		<div class="table-wrap">
			<table class="table">
			  <thead>
			    <tr>
			      <th scope="col" style="width: 300px;">지원자</th>
			      <th scope="col">경력</th>
			      <th scope="col">학력</th>
			      <th scope="col">전공</th>
			      <th scope="col">지원일</th>
			      <th scope="col">지원결과</th>
			    </tr>
			  </thead>
			  <tbody>
			  <c:set var="applyList" value="${applyPro.dataList }"></c:set>
				  <c:choose>
				  	<c:when test="${empty applyList }">
				  		<tr>
					      <td scope="row">
					      	지원자가 없습니다.
					      </td>
					    </tr>
				  	</c:when>
				  	<c:otherwise>
				  		<c:forEach items="${applyList }" var="app">
				  			<c:url value="/outs/hire/mypage/applyProfile.do" var="selectURL">
								<c:param name="appNo" value="${app.appNo}"></c:param>
							</c:url>
				  			<tr scope="row" class="linkBtn profile-name" data-gopage="${selectURL }">
						      <td>
						      
						      	<div class="profile-wrap">
							      	<div>
							      		<div>${app.devName }</div>
								      	<div class="profile-name">${app.devJob }</div>
							      	</div>
							      	<div class="profile-img-wrap">
							      		<img class="hireProfile" src="${cPath}/imageRender.do?what=${app.devImg}" alt="${cPath}/resources/images/defaultProfileImage.png"/>
							      	</div>
						      	</div>
						      </td>
						      <td>${app.devCareer }</td>
						      <td>${app.devEdu }</td>
						      <td>${app.devMajor }</td>
						      <td>${app.appDate }</td>
						      <c:choose>
							  	<c:when test="${app.hiredNy eq '0'}">
									<td><h5><span class="badge badge-secondary" style="width:70px; padding:6px;">${app.hiredValue}</span></h5></td>			  	
							  	</c:when>
							  	<c:when test="${app.hiredNy eq '1'}">
									<td><h5><span class="badge badge-primary" style="width:70px; padding:6px;">${app.hiredValue}</span></h5></td>			  	
							  	</c:when>
							  	<c:otherwise>
							  		<td><h5><span class="badge badge-danger" style="width:70px; padding:6px;">${app.hiredValue}</span></h5></td>
							  	</c:otherwise>
							  </c:choose>
						    </tr>
				  		</c:forEach>
				  	</c:otherwise>
				  </c:choose>
			  </tbody>
			</table>
		</div>
	</div>
	<div class="paging-wrap">
		<div id="pagingArea"> ${applyPro.pagingHTML } </div>
	</div>
</section>
<form id="pagingForm">
	<input type="hidden" name="boNum" value="${applyPro.boNum }"/>
	<input type="hidden" name="page" />
</form>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script>
	let pagingForm = $("#pagingForm").paging();
</script>
