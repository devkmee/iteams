<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/hireList.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/projectList.css">
<script src="${cPath}/resources/js/hire/hire.js"></script>
<div class="sub-wrap">
	<div class="sub-inner-box">
		<div class="sub-inner-txt">
			<span class="sub-txt">팀원검색<br>
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
		<span>개발자 프로필 목록 ></span>
	</div>
	<div class="board-search-wrap">
		<div class="mb-3">
		  <div id="searchUI" class="input-group">
		  	<select name="devJob" id="devJob" class="search-select width-200">
		  		<option value>주요분야</option>
		  		<option value="인공지능">인공지능/머신러닝</option>
		  		<option value="프론트엔드">프론트엔드</option>
		  		<option value="게임">게임클라이언트</option>
		  		<option value="서버">서버/백엔드</option>
		  		<option value="데이터">데이터 엔지니어</option>
		  		<option value="보안">보안</option>
		  		<option value="SW">SW/솔루션</option>
		  		<option value="앱개발">앱개발</option>
		  	</select>

			<span class="spec-txt">기술/언어</span>
			<input id="skill" type="text" name="devTech" class="form-control form-radius" style="border: 1px solid #a9acaf; border-radius: 4px;">
			
			<span class="spec-txt">아이디</span>
			<input type="text" name="devId" class="form-control form-radius" style="border: 1px solid #a9acaf; border-radius: 4px;">
			<input type="button" value="검색" id="searchBtn" class="btn btn-gray m-left-5"/>
			

		  </div>
		</div>
	</div>
	<div class="hirelist-wrap">


<script>
$(document).ready(function(){ 
	var currentPosition = parseInt($(".quickmenu").css("top")); 
	
	$(window).scroll(function() {
		var position = $(window).scrollTop(); 
		$(".quickmenu").stop().animate({
			"top":position+currentPosition+"px"}, 1000); 
	}); 
});
</script>
<c:if test="${not empty traceList}">
		<div class="search-wrap quickmenu">
		<span>최근 조회한 프로필</span>
			<c:forEach var="trace" items="${traceList}">
				<c:url value="/outs/hire/profileView.do" var="traceURL">
					<c:param name="devId" value="${trace.devId}"></c:param>
				</c:url>
				
	
					<div class="search-list2 linkBtn" data-gopage="${traceURL}" style="cursor: pointer;">
					    <span class="span-title">이름 : ${trace.devName}</span>
					    <span class="span-txt">직무 : ${trace.devJob}</span>
					     <c:choose>
					      	  <c:when test="${empty trace.devTechValue}">
						      	<span class="span-txt">#${trace.devTech}</span>
						      </c:when>
						      <c:otherwise>
						      	<span class="span-txt">
							      	<c:forEach items="${trace.devTechValue}" var="tech" begin="0" end="1">
							      		#${tech}  
							      	</c:forEach>
						      	</span>
						      </c:otherwise>
					      </c:choose>
					 </div>
	
			</c:forEach>
		</div>
</c:if>
	
		<div class="table-wrap">
			<table class="table">
			  <thead>
			    <tr>
			      <th scope="col" style="width: 300px;">아이디</th>
			      <th scope="col">기술/언어</th>
			      <th scope="col">경력</th>
			      <th scope="col">학력</th>
			      <th scope="col">전공</th>
			    </tr>
			  </thead>
			  <tbody>
			  <c:set var="profileList" value="${data.dataList }"></c:set>
				  <c:choose>
				  	<c:when test="${empty profileList }">
				  		<tr>
					      <td colspan="5" scope="row" class="text-center">
					      	조건에 맞는 지원자가 없습니다.
					      </td>
					    </tr>
				  	</c:when>
				  	<c:otherwise>
				  		<c:forEach items="${profileList }" var="dev">
				  			<c:url value="/outs/hire/profileView.do" var="selectURL">
								<c:param name="devId" value="${dev.devId}"></c:param>
							</c:url>
				  			<tr scope="row" class="linkBtn profile-name" data-gopage="${selectURL }">
						      <td>
						      	<div class="profile-wrap">
							      	<div>
							      		<div>${dev.devName }</div>
								      	<div class="profile-name">${dev.devJob }</div>
							      	</div>
							      	<div class="profile-img-wrap">
							      		<img class="hireProfile" src="${cPath}/imageRender.do?what=${dev.devImg}" alt="${cPath}/resources/images/defaultProfileImage.png"/>
							      	</div>
						      	</div>
						      </td>
						       <c:choose>
						      	  <c:when test="${empty dev.devTechValue }">
							      	<td>#${dev.devTech }</td>
							      </c:when>
							      <c:otherwise>
							      	<td>
								      	<c:forEach items="${dev.devTechValue }" var="tech" begin="0" end="2">
								      		#${tech }
								      	</c:forEach>
							      	</td>
							      </c:otherwise>
						      </c:choose>
						      <td>${dev.devCareer }</td>
						      <td>${dev.devEdu }</td>
						      <td>${dev.devMajor }</td>
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
				   <div id="pagingArea"> ${data.pagingHTML } </div>	
			</div>
		</div>	
	</div>
</section>
<form id="searchForm">
	<input type="hidden" name="devTech" />
	<input type="hidden" name="devJob" />
	<input type="hidden" name="devId" />
	<input type="hidden" name="page" />
</form>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript">
$("[name='devJob']").val("${searchVO.devJob}");
$("[name='devTech']").val("${searchVO.devTech}");
$("[name='devId']").val("${searchVO.devId}");
	
	
	let searchJob = $("#devJob");
	$("#devJob").on("change", function(){
		let selectedJob = $(this).val();
		if(!selectedJob){ 
			searchJob.find("option").show();
		}else{ 
			searchJob.find("option").hide();
			searchJob.find("option."+selectedJob).show();
			searchJob.find("option:first").show();
		}
	});
	
	let searchForm = $("#searchForm").paging();
</script>
