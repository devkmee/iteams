<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/hireList.css">
<div class="sub-wrap">
	<div class="sub-inner-box">
		<div class="sub-inner-txt">
			<span class="sub-txt">지원자 프로필<br>
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
		<span>${dev.devName }님의 프로필 ></span>
		
	</div>
		<c:if test="${dev.hiredNy eq '1'}">
			<span>이미 채용한 개발자입니다.</span>
		</c:if>
		<c:if test="${dev.hiredNy eq '2'}">
			<span>이미 반려한 개발자입니다.</span> <br />
			<span>반려사유 : ${dev.appReturn}</span>
		</c:if>
	
	<div class="hirelist-wrap">
		<div class="table-wrap">
			<table class="prolist-table">
			 	<tbody>
			 	<tr>
			      <th scope="col">사진</th>
			      <td>
			      	<img style="width: 70px; height: 70px; background-position: center center; background-repeat: no-repeat; border-radius: 50% !important; margin-left: 20px;" class="Profile" id="profileImg" src="${cPath }/imageRender.do?what=${dev.devImg}" />
			      </td>			  
			    </tr>
			    <tr>
			      <th scope="col">이름</th>
			      <td>${dev.devName }</td>
			    </tr>
			    <tr>
			      <th scope="col">출생년도</th>
			      <td>${dev.devBir }</td>
			    </tr>
			    <tr>
			      <th scope="col">연락처</th>
			      <td>${dev.memTel }</td>
			    </tr>
			    <tr>
			      <th scope="col">이메일</th>
			      <td>${dev.memMail }</td>
			    </tr>
			    <tr>
			      <th scope="col">직무</th>
			      <td>${dev.devJob }</td>
			    </tr>
			    <tr>
			      <th scope="col">기술스택</th>
			      <td>${dev.devTech }</td>
			    </tr>
			    <tr>
			      <th scope="col">학교</th>
			      <td>${dev.devEdu }</td>
			    </tr>
			    <tr>
			      <th scope="col">전공</th>
			      <td>${dev.devMajor }</td>
			    </tr>
			    <tr>
			      <th scope="col">경력</th>
			      <td>${dev.devCareer }</td>
			    </tr>
			    <tr>
			      <th scope="col">포토폴리오</th>
			      <td>${dev.devPort }</td>
			    </tr>
			  </tbody>
			</table>
		</div>
		  <div class="flex-center m-top-10">
  			<c:if test="${not empty dev.meetingDate and dev.meetingNy eq 'N' and dev.hiredNy eq '0'}">
  				${dev.meetingBtn}
  			</c:if>
  			<c:if test="${empty dev.meetingDate and dev.hiredNy eq '0'}">
  				<button class="btn btn-gray" id="meetingOffer">면접 제안</button>			  			
  			</c:if>
  			<c:if test="${not empty dev.meetingDate and dev.meetingNy eq 'Y' and dev.hiredNy eq '0'}">
  				<button disabled="disabled" title="이미 면접이 종료되었습니다.">면접 제안</button>
  			</c:if>
  			
  			<c:url value="/outs/hire/mypage/hireEdit.do" var="updateURL" >
				<c:param name="devId" value="${dev.devId}"></c:param>
			</c:url>
			<c:if test="${dev.hiredNy eq '0'}">
  				<input class="btn btn-gray m-left-10" type="button" value="채용하기" id="hireBtn">
  			</c:if>
  			
  			<c:if test="${dev.hiredNy eq '0'}">
  				<input class="btn btn-gray m-left-10" type="button" value="반려하기" id="returnBtn"> <br></br>
  				<form:form modelAttribute="dev" id="returnForm" action="${cPath}/outs/hire/mypage/returnEdit.do" method="post">
					<input type="hidden" name="appNo" value="${dev.appNo}">
					<input type="hidden" name="devId" value="${dev.devId}">
					<div id="returnArea">
						<label for="">반려사유 : </label>
						<input id="returnInput" type="text" name="appReturn" value="${dev.appReturn}"/>
						<input type="button" id="returnSubmit" value="반려" />
						<br /><form:errors path="appReturn" cssClass="error" element="span"></form:errors>
					</div>
				</form:form>
  			</c:if>
  		
  	</div>
	</div>
</section>

<form:form id="hireForm" action="${cPath}/outs/hire/mypage/hireEdit.do" method="post">
	<input type="hidden" name="appNo" value="${dev.appNo}">
	<input type="hidden" name="devId" value="${dev.devId}">
</form:form>



<div class="modal">
  <div class="modal_content">
  <form id="meetingForm" method="post" action="${cPath}/outs/hire/meetingOffer.do">
	<input type="hidden" name="appNo" value="${dev.appNo}">
	<input type="hidden" name="devId" value="${dev.devId}">
  	<input name="meetingDate" type="datetime-local" />
  <button type="button" style="position: relative; top: 90px;" class="modalMeeting btn btn-sm btn-gray">제안</button>
  <button type="button" style="position: relative; top: 90px;" class="modalClose btn btn-sm btn-gray">닫기</button>
  </form>
  </div>
</div>

<script>

let returnArea = $("#returnArea").hide()

$(function(){ 
	
	$(".meetingJoinBtn").on("click", function() {
		var roomId = $(this).data("room")
		window.open('https://192.168.0.151:3000/'+roomId + '?memId=${authMember.memId}','new','scrollbars=yes,resizable=no width=2000 height=1000, left=0,top=0')
	})

	  $("#meetingOffer").click(function(){
	    $(".modal").fadeIn();
	  });
	  
	  $(".modalClose").click(function(){
	    $(".modal").fadeOut();
	  });
	  
	$(".modalMeeting").on("click", function() {
		$("#meetingForm").ajaxForm({
			dataType : "json",
			success : function(resp) {
				alert(resp.message)
// 				$(".modalClose").trigger("click")
				location.reload(true)
			},
			error : function(x) {
				console.log(x.status)
			}
		}).submit()
	});
});
	
	
	let returnForm = $("#returnForm")
	let result;
	
	$("#hireBtn").on("click", function () {
		var meetingNy = "${dev.meetingNy}"
		if(meetingNy == 'N') {
			result = confirm('${dev.devName}님은 아직 면접을 실시하지 않았습니다. 그래도 채용하시겠습니까?')
		} else {
			result = confirm('${dev.devName}님을 채용하시겠습니까?')			
		}
		if(result){
			hireForm.submit();
		}else{
			return false;
		}
	});
	
	$("#returnBtn").on("click", function () {
		
		returnArea.toggle();
		$("#returnInput").val("")
		
	});
	
	$("#returnSubmit").on("click", function() {
		result = confirm('${dev.devName}님의 지원을 반려하시겠습니까?')
		if(result){
			returnForm.submit();	
		}else{
			return false;
		}
	})
</script>
