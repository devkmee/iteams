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
		<c:url value="/outs/board/qna/qnaList.do" var="listURL"></c:url>
			<div class="linkBtn sub-inner-txt2" data-gopage="${listURL }">
				<span class="sub-txt">1:1 질문 게시판<br>
				<span class="project-txt-lighter">궁금하신 내용을 해결해 드립니다.</span>
				</span>
			</div>
			<div class="main-outline-box-img m-right-10">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/customer.png">
			</div>
		</div>
	</div>
<section class="section-wrap">
		<div class="project-list-title m-bottom-10 m-top-30">
			<span>1:1 질문 게시판 상세 조회 ></span>
		</div>
		<div class="writing-view-wrap">
			<div class="writing-view">
				<div class="writing-title">
					<c:choose>
					<c:when test="${empty board.boParent and empty board.qnaAdmin}">
						<span>
							<label>[</label>${board.qnaKeywordValue}<label>] </label>
							<h5 style="display:inline-block"><span class="badge badge-secondary">대기 </span></h5>
							 ${board.boTitle }
						</span>
					</c:when>
					<c:when test="${empty board.boParent and not empty board.qnaAdmin  }">
						<span>
							<label>[</label>${board.qnaKeywordValue}<label>] </label>
							<h5 style="display:inline-block"><span class="badge badge-primary">완료 </span></h5>
							 ${board.boTitle }
						</span>
					</c:when>
					<c:otherwise>
						<span>
							<label>[</label>${board.qnaKeywordValue}<label>] </label>
							 ${board.boTitle }
						</span>
					</c:otherwise>
					</c:choose>	
				</div>
				<div class="writing-info-wrap">
					<c:choose>
						<c:when test="${board.modifyDate == null}">
							<div>작성자 : ${board.memId }</div>
							<div>작성일 : ${board.writeDate } </div>
						</c:when>
						<c:otherwise>
							<div>작성자 : ${board.modifyMember }</div>
							<div>작성일 : ${board.modifyDate } </div>
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
				<c:choose>
					<c:when test="${authMember.memRole eq 'ADMIN'}">
						<c:url value="/outs/board/qna/answerInsert.do" var="insertURL">
							<c:param name="boParent" value="${board.boNum}"></c:param>
						</c:url>
						<c:if test="${empty board.boParent and empty board.qnaAdmin}">
							<input type="button" class="linkBtn btn btn-gray" value="답변 작성" data-gopage="${insertURL}">
						</c:if>
					</c:when>
					<c:when test="${authMember.memRole ne 'ADMIN' and authMember.memId eq board.memId}">
						<c:url value="/outs/board/qna/qnaUpdate.do" var="updateURL">
							<c:param name="writer" value="${authMember.memId}"></c:param>
							<c:param name="boNum" value="${board.boNum}"></c:param>
						</c:url>
						<input type="button" class="linkBtn btn btn-gray" value="수정" data-gopage="${updateURL}">
						<input type="button" class="btn btn-gray" value="삭제" onclick="delete_board('q')">
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${authMember.memRole eq 'ADMIN' and not empty board.boParent}">
						<c:url value="/outs/admin/board/qna/AnswerUpdate.do" var="updateAnswerURL">
							<c:param name="boNum" value="${board.boNum}"></c:param>
						</c:url>
						<input type="button" class="linkBtn btn btn-gray" value="수정" data-gopage="${updateAnswerURL}">
						<input type="button" class="btn btn-gray m-left-5" value="삭제" onclick="delete_board('a')">
					</c:when>
				</c:choose>
			</div>	
	</section>
</div>
<form id="deleteFormQuestion" method="post" action="${cPath}/outs/board/qna/questionDelete.do">
	<input type="hidden" name="writer" value="${authMember.memId}" />
	<input type="hidden" name="boNum" value="${board.boNum}">
</form>
<form id="deleteFormAnswer" method="post" action="${cPath}/outs/board/qna/answerDelete.do">
	<input type="hidden" name="boNum" value="${board.boNum}">
</form>
<script>
let deleteFormQuestion = $("#deleteFormQuestion");
let deleteFormAnswer = $("#deleteFormAnswer");

function delete_board(url) {
	result = confirm('정말 삭제하시겠습니까?')
	if(result){
		//deleteForm.action = ${cPath}url
		if(url == 'q'){
			deleteFormQuestion.submit();	
		}else{
			deleteFormAnswer.submit();
		}
	}else{
		return false;
	}
};
</script>
