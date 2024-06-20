<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   


<!-- 

안씀

 --> 
    
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/hireList.css">
<div class="sub-wrap">
	<div class="sub-inner-box">
		<div class="sub-inner-txt">
			<span class="sub-txt">내가 받은 프로젝트 초대목록<br>
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
		<span>초대 목록 ></span>
	</div>
	<div class="hirelist-wrap">
		<div class="table-wrap">
			<table class="table">
			  <thead>
			    <tr>
			      <th scope="col">프로젝트명</th>
			      <th scope="col">클라이언트</th>
			      <th scope="col">역할/권한</th>
			      <th scope="col">연봉</th>
			      <th scope="col">초대일자</th>
			      <th scope="col">상태</th>
			      <th scope="col">수락</th>
			      <th scope="col">거절</th>
			    </tr>
			  </thead>
			  <tbody>
			  <c:set var="profileList" value="${data.dataList }"></c:set>
				  <c:choose>
				  	<c:when test="${empty profileList }">
				  		<tr>
					      <td scope="row">
					      	초대받은 프로젝트가 없습니다.
					      </td>
					    </tr>
				  	</c:when>
				  	<c:otherwise>
				  		<c:forEach items="${profileList }" var="invite" varStatus="status">
				  			<tr id="inviteMessage" scope="row">
						      <td>${invite.projectName }</td>
						      <td>${invite.clientName }</td>
						      <td>${invite.inviteAuth }</td>
						      <td>${invite.invitePriceValue }</td>
						      <td>${invite.inviteDate }</td>
						      <td>${invite.inviteStateValue }</td>
								<form id="acceptForm_${status.index}" action="${cPath }/outs/hire/mypage/acceptUpdate.do" method="post">
									<input type="hidden" name="inviteNo" value="${invite.inviteNo}"/>
									<input type="hidden" name="proNo" value="${invite.proNo}"/>
									<input type="hidden" name="inviteAuth" value="${invite.inviteAuth}"/><br>
								</form>
						      <td><input type="button" value="수락" onclick="resultSubmit(${status.index},'수락','acceptForm')"></td>
						      <td><input type="button" value="거절" onclick="resultSubmit(${status.index},'거절','refuseForm')"></td>
						        <form id="refuseForm_${status.index}" action="${cPath }/outs/hire/mypage/refuseUpdate.do" method="post">
						      		<input type="text" name="inviteNo" value="${invite.inviteNo}"/>
						        </form>
						    </tr>
				  		</c:forEach>
				  	</c:otherwise>
				  </c:choose>
			  </tbody>
			</table>
		</div>
	</div>
	<c:if test="${not empty profileList }">
		<div class="paging-wrap">
			<div id="pagingArea"> 
				<div class="paging-wrap">
				   <div id="pagingArea"> ${data.pagingHTML } </div>	
				</div>
			</div>	
		</div>
	</c:if>
</section>
<form id="pagingForm">
	<input type="text" name="page" />
</form>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript">	
let pagingForm = $("#pagingForm").paging();

function resultSubmit(index, text, formId){
	result = confirm("초대를 "+text+ "하시겠습니까?")
	if(result){
		console.log($("#"+formId+"_"+index).toString())
		$("#"+formId+"_"+index).submit();
	}else{
		return false;	
	}
}
</script>
