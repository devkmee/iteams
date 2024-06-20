<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mypage.css">

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
				<span class="sub-mypage-txt"> 마이페이지 ></span>
			</div>
			<!-- 개인일정 -->
			<div class="flex-end">
				<a href="<%=request.getContextPath()%>/outs/schedule/scheduleList.do">
					<img class="m-right-2" src="/iteams/resources/images/mycal.png">
				</a>
			</div>
			<!-- 개인일정 -->
				<div class="tab-wrap">
					<ul class="nav nav-tabs">
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/member/mypageDevInfo.do">회원정보</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/member/mypageBoard.do">나의 게시글</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link active" href="${cPath}/outs/member/mypageApplyInvite.do">지원/초대받은 프로젝트</a>
					  </li>
					  <li class="nav-item2">
					    <a class="nav-link" href="${cPath}/outs/member/mypageOngoingEnd.do">진행/완료 프로젝트</a>
					  </li>
					</ul>
					
					<div class="tab-content" id="myTabContent">
			   	   	   <div>
							<!-- 초대 프로젝트 -->
							<div class="project-wrap-txt m-bottom-10 m-top-20">초대받은 프로젝트 &gt;</div>
							<div class="table-list">
								<table class="table">
								  <thead>
								    <tr>
								      <th scope="col">프로젝트명</th>
								      <th scope="col">클라이언트</th>
								      <th scope="col">역할/권한</th>
								      <th scope="col">연봉</th>
								      <th scope="col">초대일자</th>
								      <th scope="col" colspan="2">초대결과</th>
								    </tr>
								  </thead>
								  <tbody>
								  <c:set var="profileList" value="${data.dataList }"></c:set>
									  <c:choose>
									  	<c:when test="${empty profileList }">
									  		<tr>
										      <td scope="row" colspan="7">
										      	초대받은 프로젝트가 없습니다.
										      </td>
										    </tr>
									  	</c:when>
									  	<c:otherwise>
									  		<c:forEach items="${profileList }" var="invite" varStatus="status">
									  			<tr class="inviteMessageOpen" style="cursor: pointer;" scope="row">
											      <td class="projectName">${invite.projectName }</td>
											      <td class="clientName">${invite.clientName }</td>
											      <td>${invite.inviteAuth }</td>
											      <td class="invitePrice">${invite.invitePriceValue }</td>
											      <td>${invite.inviteDate }</td>
											      	<form id="acceptForm_${status.index}" action="${cPath }/outs/hire/mypage/acceptEdit.do" method="post">
														<input type="hidden" name="inviteNo" value="${invite.inviteNo}"/>
														<input type="hidden" name="proNo" value="${invite.proNo}"/>
														<input type="hidden" name="inviteAuth" value="${invite.inviteAuth}"/>
													</form>
													 <form id="refuseForm_${status.index}" action="${cPath }/outs/hire/mypage/refuseEdit.do" method="post">
											      		<input type="hidden" name="inviteNo" value="${invite.inviteNo}"/>
											        </form>
											      	<c:choose>
											      		<c:when test="${invite.inviteState eq '0'}">
											      			<td><input type="button" class="btn btn-gray btn-sm" value="수락" onclick="resultSubmit(${status.index},'수락','acceptForm')"></td>
											     			<td><input type="button" class="btn btn-gray btn-sm" value="거절" onclick="resultSubmit(${status.index},'거절','refuseForm')"></td>
											      		</c:when>
											      		<c:when test="${invite.inviteState eq '1'}">
											      			<td colspan="2"><h5><span class="badge badge-primary" style="width:50px; padding:5px;">${invite.inviteStateValue}</span></h5></td>
											      		</c:when>
											      		<c:otherwise>
											      			<td colspan="2"><h5><span class="badge badge-danger" style="width:50px; padding:5px;">${invite.inviteStateValue}</span></h5></td>
											      		</c:otherwise>
											      	</c:choose>
												</tr>
									  		</c:forEach>
									  	</c:otherwise>
									  </c:choose>
								  </tbody>
								</table>
							</div>
							<!-- 초대 프로젝트 -->
							<!-- 지원 프로젝트 -->
							<div class="project-wrap-txt m-bottom-10">지원한 프로젝트 &gt;</div>
								<div class="table-list">
									<table class="table">
									  <thead>
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
											      <td scope="row" colspan="6">
											      	지원한 프로젝트가 없습니다.
											      </td>
											    </tr>
										  	</c:when>
										  	<c:otherwise>
										  		<c:forEach items="${applyList }" var="app">
										  			<c:url value="/outs/board/projectboard/projectView.do" var="selectURL">
														<c:param name="what" value="${app.boNum }"></c:param>
													</c:url>
										  			<tr style="cursor: pointer;" class="linkBtn" data-gopage="${selectURL }">
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
													  
													  <!-- 지원결과 -->
													  <c:choose>
													  	<c:when test="${app.hiredNy eq '0'}">
															<td><h5><span class="badge badge-secondary" style="width:50px; padding:5px;">${app.hiredValue}</span></h5></td>			  	
													  	</c:when>
													  	<c:when test="${app.hiredNy eq '1'}">
															<td><h5><span class="badge badge-primary" style="width:50px; padding:5px;">${app.hiredValue}</span></h5></td>			  	
													  	</c:when>
													  	<c:otherwise>
													  		<td><h5><span class="badge badge-danger" style="width:50px; padding:5px;">${app.hiredValue}</span></h5></td>
													  	</c:otherwise>
													  </c:choose>
												    </tr>
										  		</c:forEach>
										  	</c:otherwise>
										  </c:choose>
									  </tbody>
									</table>
								</div>
								
							<c:if test="${not empty applyList }">
								<div class="paging-wrap">
									<div id="pagingArea"> 
										<div class="paging-wrap">
											<div id="pagingArea"> ${applyPro.pagingHTML } </div>	
										</div>
									</div>	
								</div>
							</c:if>
							<!-- 지원 프로젝트 -->
					    </div>
					    
 
				</div>
			</div>
		</div>
	</div>
</div>


    <div class="modal fade" id="inviteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h5 class="modal-title" id="exampleModalLabel"><strong><span id="clientName"></span></strong>에서 받은 초대 메세지입니다.</h5>
      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div id="modalBody" class="modal-body">
 	<div style="margin-top: 20px;">
 		<span>프로젝트명 : </span><span id="projctName"></span>	
 		<br /><br /><span>연봉 <span id="invitePrice"></span> 원 내외의 조건으로</span>
		 		<br /><span>귀하를 저희 프로젝트에 초대합니다.</span>
		 		<br /><span>자세한 사항은 인사 담당자와 상의 할 수 있습니다.</span>
		 	</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
<form id="applyPagingForm">
	<input type="hidden" name="page" />
</form>
<script>
let applyPagingForm = $("#applyPagingForm").paging();
let modalProjectName = $("#projctName");
let modalInvitePrice = $("#invitePrice");
let modalClientName = $("#clientName");
let inviteModal = $("#inviteModal");

$(".inviteMessageOpen > .projectName").on("click", function() {
	console.log("dasdsa")
	
	modalProjectName.empty();
	modalInvitePrice.empty();
	modalClientName.empty();
	
	let projectName = $(this).parents(".inviteMessageOpen").find(".projectName").text();
	let invitePrice = $(this).parents(".inviteMessageOpen").find(".invitePrice").text();
	let clientName = $(this).parents(".inviteMessageOpen").find(".clientName").text();
	console.log(projectName)
	console.log(invitePrice)
	
	modalProjectName.text(projectName)
	modalInvitePrice.text(invitePrice)
	modalClientName.text(clientName)
	
	inviteModal.modal("show")
	
})

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