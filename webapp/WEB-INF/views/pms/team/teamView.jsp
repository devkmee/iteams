<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<div class="pms-main-wrap">
     <div class="sub-title-wrap">
       <i class="fas fa-chevron-right"></i>
       	팀원 상세보기
     </div>
</div>

<div class="card-deck" >
	<div class="card" >
		<img src="${cPath}/imageRender.do?what=${detail.devImg}" alt="${cPath}${detail.defaultImage}" style="width:100%; height:400PX; object-fit: contain;">
		<div class="card-body" style="text-align:center; ">
			<h5 class="card-title"  >아이디 : ${detail.devId }</h5>
			<p class="card-text">이름 : ${detail.devName}</p>
			<p class="card-text">개발포지션 : ${detail.authority }</p>
			<p class="card-text">연락처 : ${detail.memTel}</p>
			<p class="card-text">이메일 : ${detail.memMail}</p>
			<p class="card-text">사용언어 : ${detail.devTech}</p>
			<p class="card-text">개발경력 : ${detail.devCareer}</p>
			<%-- <p class="card-text"><small class="text-muted">참여일자 : ${detail.joinDate}</small></p> --%>
<button type="button" class="btn btn-gray"  onclick="location.href='/iteams/pms/team/${authMember.proNo }/teamList.do';">리스트</button>
<!-- 쪽지보내기 기능 완료 시 링크 알려주면 onclick에 연결해주겠음(찌눈꺼에다!) -->
<button id="messageBtn" type="button" class="btn btn-gray" >쪽지보내기</button>
<!-- onclick="location.href='/iteams/outs/message/sendMessage.do';" -->
		</div>
	</div>
</div>
<div id="messageForm">
	<jsp:include page="/pms/team/${authMember.proNo}/messageForm.do?memId=${detail.devId}"></jsp:include>
</div>



<script>
var messageForm = $("#messageForm").hide();

$("#messageBtn").on("click", function() {
	messageForm.toggle();
})

</script>


