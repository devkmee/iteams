<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
			<span>프로젝트 상세 조회 ></span>
		</div>
		<div class="flex-between m-bottom-10">
			<button data-gopage="${cPath}/outs/board/projectboard/projectList.do" class="linkBtn btn btn-gray">목록</button>
			
			<c:if test="${vo.memId eq authMember.memId}">
				<c:url var="deadline" value="/outs/board/projectboard/deadline.do"> 
					<c:param name="what" value="${vo.boNum}"></c:param>
					<c:param name="writer" value="${authMember.memId}"></c:param>
				</c:url>
					<input id="deadlineBtn" type="button" value="공고마감" class="linkBtn btn btn-gray" data-gopage="${deadline}" />
			</c:if>
		
		</div>

<div class="page-breadcrumb">
	<div class="writing-view-wrap">
		<div class="writing-view">
			<div class="writing-title">
				<span>${vo.projectName }</span>
			</div>
			
			<div class="bottom-line"></div>
			<div class="writing-info-wrap">
				<div>${vo.clientName}</div>
				<div class="btn-group" role="group" aria-label="Basic example">
				<c:if test="${authMember.memRole eq 'DEV'}">
				  <img id="scrapBtn" class="m-right-10" src="<%=request.getContextPath()%>/resources/images/pscrap.png"></img>
				</c:if>
				<c:if test="${vo.memId ne authMember.memId}">   
				  <img id="reportBtn" src="<%=request.getContextPath()%>/resources/images/siren.png"></img>
				</c:if>
				</div>
			</div>
			<div class="prolist-group">
				<h5>기술스택</h5>
				<c:set var="techArr" value="${fn:split(vo.projectTech,',')}" />
				<div class="flex-start m-top-10">
					<c:forEach items="${techArr}" var="tech">
						<div class="tech-box">
							<div class="tech-img">
								<img alt="${tech}" src="<%=request.getContextPath()%>/resources/images/skill/${tech}.png"> 
							</div>
							<span class="tech-txt">${tech}</span>
						</div>
					</c:forEach>
				</div>
			</div>
			<div>
				${vo.boContent}
			</div>
			<div class="prolist-group">
				<h5>주요 직무</h5>
				<ul>
					<li>${vo.projectJob}</li>
				</ul>
			</div>
			<div class="prolist-group">
				<h5>모집 인원</h5>
				<ul>
					<li>${vo.projectRecruit} 명</li>
				</ul>
			</div>
			<div class="prolist-group">
				<h5>모집기간</h5>
				<ul>
					<c:if test="${not empty vo.permissionDate}">
						<li>${vo.permissionDate} ~ ${vo.limitDate}</li>
					</c:if>
					<c:if test="${empty vo.permissionDate}">
						<li>미승인 ~ ${vo.limitDate}</li>
					</c:if>
				</ul>
			</div>
			<div class="prolist-group">
				<h5>첨부파일</h5>
				<ul>
					<c:if test="${not empty vo.attachList}">
						<c:forEach items="${vo.attachList}" var="attach">
							<a href="${cPath}/outs/download.do?what=${attach.attNo}">${attach.attachOrigin}</a>
						</c:forEach>
					</c:if>
					<c:if test="${empty vo.attachList}">
						<li>첨부파일 없음.</li>
					</c:if>
				</ul>
			</div>					
		</div>
			<span>${board.boContent}</span>
		<c:if test="${vo.memId eq authMember.memId}">
			<div class="board-btn-wrap">
				<c:url var="updateUrl" value="/outs/board/projectboard/projectUpdate.do">
					<c:param name="what" value="${vo.boNum}"></c:param>
					<c:param name="writer" value="${authMember.memId}"></c:param>
				</c:url>
					<input type="button" value="글수정" class="linkBtn btn btn-gray m-right-5" data-gopage="${updateUrl}" />
				<c:url var="deleteUrl" value="/outs/board/projectboard/projectDelete.do">
					<c:param name="what" value="${vo.boNum}"></c:param>
					<c:param name="writer" value="${authMember.memId}"></c:param>
				</c:url>
					<input id="deleteBtn" type="button" value="글삭제" class="linkBtn btn btn-gray" data-gopage="${deleteUrl}" />
			</div>
		</c:if>

		<div>
			<c:if test="${not empty board.attachList}">
				<span>첨부파일 : </span>
				<c:forEach items="${board.attachList}" var="attach" varStatus="status">
					<a href="${cPath}/outs/download.do?what=${attach.attNo}">${attach.attachOrigin}</a>
					<c:if test="${not status.last}">,</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${empty board.attachList }"></c:if>
		</div>

<div style="padding:30px;">
	<c:if test="${authMember.memRole eq 'DEV'}">
	<c:if test="${appInfo.hiredNy eq '0' and empty ingProject}">
		<div class="flex-center" style="margin-bottom:15px;"><input type="button" value="지원취소" id="applyBtn" class="flex-center btn btn-basic apply"></div>
		<span class="flex-center" style="color: gray;">지원한 프로젝트입니다. 채용 결과를 기다려주세요.</span>
		<span class="flex-center" style="color: gray;">지원 일시 : ${appInfo.appDate}</span>
	</c:if>
	<c:if test="${appInfo.hiredNy eq '2' and empty ingProject and empty appInfo.meetingDate}">
		<span class="flex-center" style="color: gray;">지원이 반려된 프로젝트입니다</span>
		<span class="flex-center" style="color: gray;">반려 사유 : ${appInfo.appReturn}</span>
	</c:if>
	<c:if test="${empty appInfo and empty ingProject}">
		<div class="flex-center"><input type="button" value="지원하기" id="applyBtn" class="btn btn-basic apply"></div>
	</c:if>
	<c:if test="${not empty ingProject and empty appInfo}">
		<span class="flex-center" style="color: gray;">프로젝트에 참여 중이므로 다른 프로젝트에 지원 할 수 없습니다</span>
	</c:if>
	<c:if test="${not empty appInfo.meetingDate and appInfo.meetingNy eq 'N'}">
		<div class="flex-center">${appInfo.meetingBtn}</div>
	</c:if>
	</c:if>
</div>
<c:if test="${appInfo.hiredNy eq '2' and empty ingProject}">
	<div class="flex-center">
		<c:url var="reApplyUrl" value="/outs/hire/reApply.do">
			<c:param name="what" value="${appInfo.appNo}"></c:param>
			<c:param name="boNum" value="${vo.boNum}"></c:param>
		</c:url>
		<input id="reApply" data-gopage="${reApplyUrl}" type="button" class="linkBtn btn btn-basic apply" value="재지원" />
	</div>
</c:if>
		</div>
		<div class="paging-wrap">
			 <div id="pagingArea"></div>	
		</div>
	</div>
</section>

<form id="applyForm" action="${cPath}/outs/hire/Apply.do" method="post">
	<input type="hidden" name="boNum" value="${vo.boNum}">
	<input type="hidden" name="memId" value="${vo.memId}" />
	<input type="hidden" name="projectName" value="${vo.projectName}" />
</form>
<form id="scrapForm" action="${cPath}/outs/board/projectboard/projectScrap.do" method="post">
	<input type="hidden" name="boNum" value="${vo.boNum}">
</form>

<script>

$("#reApply").on("click", function() {
	let valid = confirm("재지원 하시겠습니까?")
	if(!valid) return false;
	return true;
})

$(".meetingJoinBtn").on("click", function() {
	var roomId = $(this).data("room")
	$.ajax({
		url : "${cPath}/outs/board/projectboard/meetingJoin.do",
		data : {
			appNo : "${appInfo.appNo}"
		},
		method : "post",
		dataType : "json",
		success : function(resp) {
			if(resp.message == 'success') {
				window.open('https://192.168.0.151:3000/'+roomId + '?memId=${authMember.memId}','new','scrollbars=yes,resizable=no width=2000 height=1000, left=0,top=0')
				location.reload()
			} else {
				alert("면접실 참가에 실패했습니다.")
			}
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	})
})

$("#reportBtn").on("click", function() {
	$.ajax({
		url : "${cPath }/outs/board/projectboard/report.do",
		data : {"what" : "${number}"},
		dataType : "json",
		success : function(resp) {
			alert(resp.message)
			location.reload(true)
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	})
})


	let returnForm = $("#returnForm")
	let scrapForm = $("#scrapForm")
	let result;
	
	$("#applyBtn").on("click", function () {
		let temp = $(this).attr("value");
		if(temp === "지원하기") {
			result = confirm('${vo.projectName }에 지원하시겠습니까?')		
		} else {
			result = confirm('${vo.projectName } 지원을 취소하시겠습니까?')					
		}
		if(result){
			applyForm.submit();
		}else{
			return false;
		}
	});
	
	
	$("#scrapBtn").on("click", function(){
		result = confirm('공고글을 스크랩 하시겠습니까?')
		if(result){
			scrapForm.submit();
		}else{
			return false; 
		}
	})

</script>
