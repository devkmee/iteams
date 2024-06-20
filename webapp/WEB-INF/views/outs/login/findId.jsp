<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/login.css">
<script src="${pageContext.request.contextPath}/resources/js/join/find.js"></script>

<div class="login-content-wrap">
	<div class="idpass-table-wrap">
	<div class="project-list-title m-bottom-10 m-top-30">
			<p class="idpass-font">회원 아이디 찾기 &gt;</p> 
		</div>
		<div class="idpass-txt">
		회원정보에 등록된 정보로 아이디를 찾을 수 있습니다.
		</div>
		<table class="table">
		  <tbody>
		    <tr>
		      <td class="td-title">이메일</td>
		      <td>
		      	<input type="text" id="email">
		      	<input type="button" class="idpass-btn" id="chkmail" value="인증번호 발송" onclick="checkMail()">
		      </td>
		    </tr>
		    <tr class="idpass-border">
		      <td class="td-title">인증번호</td>
		      <td>
		      	<input id="certNum" type="text">
		      </td>
		    </tr>
		  </tbody>
		</table>
		<div class="div-center-wrap">
			<div class="div-center">
				<input type="button" id="chknumber" class="square-blue-btn width-160" value="아이디 찾기" onclick="onClickFindId()" >
			</div>
			<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">아이디 찾기</h5>
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
