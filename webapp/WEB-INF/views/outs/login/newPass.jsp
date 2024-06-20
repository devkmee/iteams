<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/login.css">
<script src="${pageContext.request.contextPath}/resources/js/join/find.js"></script>
<div class="login-content-wrap">
	<input type="hidden" id="memId" value="${memId}">
	<div class="idpass-table-wrap">
	<div class="project-list-title m-bottom-10 m-top-30">
			<p class="idpass-font">회원 비밀번호 변경 &gt;</p> 
		</div>
		<div class="idpass-txt">
		새로운 비밀번호를 등록해 주세요
		</div>
		<table class="table">
		  <tbody>
		 	 <tr>
		      <td class="td-title">새 비밀번호</td>
		      <td>
		      	<input required type="password" id="newPass">
		      </td>
		    </tr>
		    <tr class="idpass-border">
		      <td class="td-title">새 비밀번호 확인</td>
		      <td>
		      	<input required type="password" id="newPassCheck">
		      </td>
		    </tr>
		  </tbody>
		</table>
		<div class="div-center-wrap">
			<div class="div-center">
				<input type="button" class="square-blue-btn width-160" value="비밀번호 변경" onclick="onClickChangePass()">
			</div>
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">비밀번호 변경</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		       	<span id="findId"></span>
		      </div>
		      <div class="modal-footer">
		        <button id="modalCloseBtn" type="button" class="btn btn-secondary" data-dismiss="modal">확인</button>
		      </div>
		    </div>
		  </div>
		</div>
		</div>
	</div>
</div>	
