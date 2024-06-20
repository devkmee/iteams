<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/board.css">
<div class="sub-wrap">
	<div class="sub-inner-box">
		<div class="sub-inner-txt2">
			<span class="sub-txt">개발자 인터뷰<br>
				<span class="project-txt-lighter">개발자가 궁금해하는 질문만 모아모아!</span>
			</span>
		</div>
		<div class="main-outline-box-img m-right-10">
			<img alt="" src="<%=request.getContextPath()%>/resources/images/community.png">
		</div>
	</div>
</div>   
<section class="section-wrap">
	<div class="project-list-title m-bottom-10 m-top-30">
		<span>개발자 인터뷰 ></span>
	</div> 
	<div class="writing-view-wrap">
		<div class="writing-view">
			<div class="writing-title">
				<span>${board.boTitle}</span>
			</div>
			<div class="writing-info-wrap">
				<div>작성자 : ${board.devName} 님</div>
				<div>추천수 : ${board.boRec} </div>
				<div>작성일 : ${board.writeDate} </div>
			</div>
		</div>
		<div class="writing-content">
			<span>${board.boContent}</span>
		</div>
		<div class="board-btn-wrap">
			<c:url value="/board/boardUpdate.do" var="updateURL">
				<c:param name="what" value="${board.boNum }" />
			</c:url>
			<c:if test="${authMember.memRole eq 'ADMIN'}">
				<input type="button" value="글수정" class="linkBtn btn btn-gray m-right-5"data-gopage="${updateURL }" />
				<c:url value="/board/boardDelete.do" var="deleteURL">
					<c:param name="what" value="${board.boNum }" />
				</c:url>	
				<input type="button" value="글삭제" class="linkBtn btn btn-gray"data-gopage="${deleteURL }" />
			</c:if>	
		</div>
		<div class="recommend-wrap">
			<div>댓글수</div>
			<div class="recommend-line"></div>
			<div class="recommend">3</div>
		</div>
		<div class="comment-wrap">
			<div class="input-group mb-3">
			  <input type="text" id="commentInput" class="form-control" placeholder="" aria-label="Recipient's username" aria-describedby="button-addon2">
			  <div class="input-group-append">
			    <input class="btn btn-gray" type="button" value="댓글등록" id="button-addon2">
			  </div>
			</div>
		</div>
		<div class="comment-list-wrap">
			<div class="comment-list">
				안녕하세요 
			</div>
			<div class="comment-box-wrap">
				<div class="comment-list-date">
					2021/11/02
				</div>
				<div class="comment-list-info">
					작성자 : zzinew 님
				</div>
			</div>
		</div>
		<div class="comment-list-wrap">
			<div class="comment-list">
				안녕하세요 
			</div>
			<div class="comment-box-wrap">
				<div class="comment-list-date">
					2021/11/02
				</div>
				<div class="comment-list-info">
					작성자 : zzinew 님
				</div>
			</div>
		</div>
		<div class="comment-list-wrap">
			<div class="comment-list">
				안녕하세요 
			</div>
			<div class="comment-box-wrap">
				<div class="comment-list-date">
					2021/11/02
				</div>
				<div class="comment-list-info">
					작성자 : zzinew 님
				</div>
			</div>
		</div>
	</div>
</section>
