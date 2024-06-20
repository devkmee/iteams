<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/hireList.css">
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
			<span>승인대기 프로젝트 상세 ></span> 
		</div>
		
<div class="page-breadcrumb">
	<div class="table-wrap">
		<table class="prolist-table">
			<tr>
				<th>프로젝트명</th>
				<td>${vo.projectName }</td>
			</tr>
			<tr>
				<th>프로젝트 규모</th>
				<td>${vo.projectScale }</td>
			</tr>
			<tr>
				<th>예상단가</th>
				<td>${vo.projectPriceValue }</td>
			</tr>
			<tr>
				<th>요구사항</th>
				<td>${vo.projectReq }</td>
			</tr>
			<tr>
				<th>요구기술스택</th>
				<td>${vo.projectTech }</td>
			</tr>
			<tr>
				<th>요구직무</th>
				<td>${vo.projectJob }</td>
			</tr>
			<tr>
				<th>공고기한</th>
				<td>${vo.limitDate }</td>
			</tr>
		</table>
	</div>	
</div>


 
<a id="modalOpen" href="#ex1" rel="modal:open" style="display: none;"></a>

<%-- <c:if test="${authMember.memRole eq 'ADMIN'}">   --%>
<div class="flex-center" style="padding: 10px;">
	<form action="${cPath}/outs/board/projectboard/projectApprove.do" method="post"> 
		<input type="hidden" name="boNum" value="${vo.boNum}"> 
		<input type="submit" value="프로젝트 승인" class="btn btn-gray"> 
	</form> 
	<form:form modelAttribute="vo" action="${cPath}/outs/board/projectboard/projectRefuse.do" method="post">
		<input type="hidden" name="boNum" value="${vo.boNum}">
		<input type="button" id="refuseBtn" value="프로젝트 반려" class="btn btn-gray m-left-5"/> <br />
		<div id="refuseArea">
			<label for="">반려사유 : </label>
			<input id="refuseInput" type="text" name="projectRefuse" value="${vo.projectRefuse}"/>
			<input type="submit" value="반려하기" />
			<form:errors path="projectRefuse" cssClass="error" element="span"></form:errors>
		</div>
	</form:form>
</div>
<%-- </c:if> --%>

	</section>
</div>

<script>

let refuseArea = $("#refuseArea").hide()

let input = $("#refuseInput").val()

if(input) {
	refuseArea.show()
}

$("#refuseBtn").on("click", function() {
	$("#refuseInput").val("")
	refuseArea.toggle()
})
</script>
