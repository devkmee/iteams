<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/table.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/board.css">	
	<div>
		<div class="sub-wrap">
			<div class="sub-inner-box">
				<div class="sub-inner-txt2">
					<span class="sub-txt"> 고객센터<br>
						<span class="project-txt-lighter">무엇을 도와드릴까요? </span>
					</span>
				</div>
				<div class="main-outline-box-img m-right-10">
					<img alt="" src="<%=request.getContextPath()%>/resources/images/customer.png">
				</div>
			</div>
		</div>
		<section class="section-wrap">
			<div class="community-box-wrap">
				<div class="community-box" id="noticeDiv">
					<img alt="" src="<%=request.getContextPath()%>/resources/images/notice.png">
				</div>
				<div class="community-box" id="FAQDiv">
					<img alt="" src="<%=request.getContextPath()%>/resources/images/faq.png">
				</div>
				<div class="community-box" id="QNADiv">
					<img alt="" src="<%=request.getContextPath()%>/resources/images/qna.png">
				</div>
			</div>
			<div class="project-list-title m-bottom-10 m-top-30">
				<span>자주묻는 질문 ></span>
			</div>
			<div class="table-wrap">
				<table class="table">
				  <thead class="thead-light">
				    <tr class="tr-gray">
				      <td scope="col">No</td>
				      <td scope="col">키워드</td>
				      <td scope="col">제목</td>
				      <td scope="col">조회수</td>
				      <td scope="col">작성자</td>
				    </tr>
				  </thead>
				  <tbody>
				  <c:set var="boardList" value="${board.dataList }"></c:set>
				    <c:choose>
				    	<c:when test="${empty boardList}">
				    		<tr><td>자주묻는 질문글이 없습니다</td></tr>
				    	</c:when>
				    	<c:otherwise>
				    		<c:forEach items="${boardList}" var="bo">
				    			<tr>
				    				<td>${bo.rnum}</td>
				    				<td>${bo.qnaKeywordValue}</td>
				    				<td class="text-left"><a href="${cPath}/outs/board/notice/noticeView.do?boNum=${bo.boNum}">${bo.boTitle }</a></td>
				    				<td>${bo.boHit}</td>
				    				<td>${bo.memId}</td>
				    			</tr>
				    		</c:forEach>
				    	</c:otherwise>
				    </c:choose>
				  </tbody>
			</table>
		</div>
		</section>
	</div>
	
<script>
$("#noticeDiv").on("click", function () {
	location.href="${cPath }/outs/board/notice/noticeList.do";
});
$("#FAQDiv").on("click", function () {
	location.href="${cPath }/outs/board/qna/faqList.do";
});
$("#QNADiv").on("click", function () {
	location.href="${cPath }/outs/board/qna/qnaList.do";
});
</script>


