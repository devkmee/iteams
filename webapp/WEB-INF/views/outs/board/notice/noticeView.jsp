<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div>
	<div class="sub-wrap">
		<div class="sub-inner-box">
			<div class="sub-inner-txt2">
				<c:url value="/outs/board/notice/noticeList.do" var="listURL"></c:url>
				<span class="linkBtn sub-txt"  data-gopage="${listURL }">공지사항<br>
					<span class="project-txt-lighter">아이팀즈 공지사항입니다.</span>
				</span>
			</div>
			<div class="main-outline-box-img m-right-10">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/customer.png">
			</div>
		</div>
	</div>
<section class="section-wrap">
		<div class="project-list-title m-bottom-10 m-top-30">
			<span>공지사항 상세 조회 ></span>
		</div>
		<div class="writing-view-wrap">
			<div class="writing-view">
				<div class="writing-title">
					<span>${board.boTitle }</span>
				</div>
				<div class="writing-info-wrap">
					<c:choose>
						<c:when test="${board.modifyDate == null}">
							<div>작성자 : ${board.memId }</div>
							<div>작성일 : ${board.writeDate } </div>
							<div>조회수 : ${board.boHit }</div>
						</c:when>
						<c:otherwise>
							<div>작성자 : ${board.modifyMember }</div>
							<div>작성일 : ${board.modifyDate } </div>
							<div>조회수 : ${board.boHit }</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="writing-content">
				<span>${board.boContent }</span>
			</div>
			<div>
				<c:choose>
					<c:when test="${fn:length(board.attachList) != 0 }">
						<span>첨부파일</span>
						<c:forEach items="${board.attachList }" var="att">
							<a href="${cPath}/outs/download.do?what=${att.attNo}">${att.attachOrigin }</a>
						</c:forEach>
					</c:when>
				</c:choose>
			</div>
			<div class="board-btn-wrap">
				<c:if test="${authMember.memRole eq 'ADMIN' }">
					<c:url value="/outs/admin/board/notice/noticeUpdate.do" var="updateURL">
						<c:param name="boNum" value="${board.boNum }"></c:param>
					</c:url>
					<input type="button" class="linkBtn btn btn-gray" value="수정" data-gopage="${updateURL }">
					
					<input type="button" class="btn btn-gray m-left-5" value="삭제" id="deleteBtn">
				</c:if>
			</div>	
	</section>
</div>
<form id="deleteForm" action="${cPath }/outs/admin/board/notice/noticeDelete.do" method="post">
	<input type="hidden" name="boNum" value="${board.boNum}">
</form>
<script>
	$("#deleteBtn").on("click", function () {
		result = confirm('정말 삭제하시겠습니까?')
		if(result){
			deleteForm.submit();	
		}else{
			return false;
		}
	});
</script>
