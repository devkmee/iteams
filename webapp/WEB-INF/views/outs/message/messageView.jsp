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
				<span class="sub-mypage-txt">쪽지상세 ></span>
			</div>
				<div class="flex-end">
					<input type="button" class="btn btn-gray" value="삭제"
							id="deleteMessage" /> 
					
					<form id="deleteForm" action="${cPath}/outs/message/deleteMessage.do" method="post"> 
						<input type="hidden" name="what" value="${msg.msgNum }" data-what="${msg.msgNum }"> 
					</form>
				</div>
				<div class="writing-view-wrap m-top-10">
		   	   	   <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
						<script>
							$("#deleteMessage").on("click", function(){
								deleteForm.submit(); 
							})
						</script>
						<div class="writing-view">
							<div class="writing-title">
								<span>${msg.msgTitle }</span>
							</div>
							<div class="writing-info-wrap">
								<div>발신일 : ${msg.sendDate }</div>
								<div>발신자 : ${msg.memId }</div>
							    <c:if test="${not empty msg.msgReceive}">
								      <div>수신자 : ${msg.msgReceive }</div>
							    </c:if>
							</div>
						</div>
						<div class="writing-content">
							<span>${msg.msgContent}</span>
						</div>
				    </div>
				</div>
			</div>
		</div>
	</div>