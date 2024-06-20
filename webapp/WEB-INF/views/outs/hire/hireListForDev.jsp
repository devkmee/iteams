<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 안 쓰는 화면 -->  
    
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/hireList.css">
<div class="sub-wrap">
	<div class="sub-inner-box">
		<div class="sub-inner-txt">
			<span class="sub-txt">나의 지원 리스트<br>
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
		<span>내가 지원한 프로젝트 ></span>
	</div>
	<div class="hirelist-wrap">
		<div class="table-wrap">
			<table class="table">
			  <thead>
			  <!-- 여기 삭제 버튼 넣어서 회원이 공고 마감된 결과 직접 삭제 할 수 있는 기능도 넣구싶다.. 근데 지금 말고 테스트배포기간에 여유되면! -->
			    <tr>
			      <th scope="col">프로젝트명</th>
			      <th scope="col">회사명</th>
			      <th scope="col">직무</th>
			      <th scope="col">지원일</th>
			      <th scope="col">마감일</th>
			      <th scope="col">지원결과</th>
			    </tr>
			  </thead>
			  <tbody>
			  <c:set var="applyList" value="${applyPro.dataList }"></c:set>
				  <c:choose>
				  	<c:when test="${empty applyList }">
				  		<tr>
					      <td scope="row">
					      	지원한 프로젝트가 없습니다.
					      </td>
					    </tr>
				  	</c:when>
				  	<c:otherwise>
				  		<c:forEach items="${applyList }" var="app">
				  			<c:url value="/outs/board/projectboard/projectView.do" var="selectURL">
								<c:param name="what" value="${app.boNum }"></c:param>
							</c:url>
				  			<tr class="linkBtn" data-gopage="${selectURL }">
						      <td scope="row">
						      	<div class="profile-wrap">
							      	<div class="profile-name">
							      		<div>${app.projectName }</div>
							      	</div>
						      	</div>
						      </td>
						      <td>${app.clientName }</td>
						      <td>${app.projectJob }</td>
						      <td>${app.appDate }</td>
						      <c:choose>
								<c:when test="${not empty app.limitModifyDate}">
									<td>${app.limitModifyDate}</td>
								</c:when>
								<c:otherwise>
									<td>${app.limitDate}</td>
								</c:otherwise>
							  </c:choose>
							  	<td>${app.hiredValue}</td>
						    </tr>
				  		</c:forEach>
				  	</c:otherwise>
				  </c:choose>
			  </tbody>
			</table>
		</div>
	</div>
	<div class="paging-wrap">
			<div id="pagingArea"> 
				<div class="paging-wrap">
				   <div id="pagingArea"> ${applyPro.pagingHTML } </div>	
			</div>
		</div>	
	</div>
</section>
