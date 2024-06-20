<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/board.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/interview.css">
<div class="sub-wrap">
	<div class="sub-inner-box">
		<div class="sub-inner-txt2">
			<span class="sub-txt">개발자 인터뷰<br>
				<span class="project-txt-lighter">개발자가 궁금해하는 질문만 모아모아!</span>
			</span>
		</div>
		<div class="main-outline-box-img m-right-10">
			<img src="<%=request.getContextPath()%>/resources/images/community.png">
		</div>
	</div>
</div>   
<section class="section-wrap">
	<div class="community-box-wrap">
		<div style="cursor: pointer;" class="linkBtn community-box" data-gopage="${cPath }/outs/board/free/freeBoardList.do">
			<a href="<%=request.getContextPath()%>/outs/board/free/freeBoardList.do">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/c1.png">
			</a>
		</div>
		<div class="community-box">
			<a href="<%=request.getContextPath()%>/outs/board/codebook/codeList.do">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/c2.png">
			</a>
		</div>
		<div class="community-box">
			<a href="<%=request.getContextPath()%>/outs/board/interview/interviewList.do">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/c3.png">
			</a>
		</div>
		<div class="community-box">
			<a href="<%=request.getContextPath()%>/outs/board/news/newsList.do">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/c4.png">
				</a>
			</div>
	</div>
	<div class="project-list-title m-top-20">
		<span>개발자 인터뷰 ></span>
	</div> 
	<div class="board-search-wrap">
		<div class="input-group mb-3 width-400">
		  <select id="searchTag" class="form-select search-select" aria-label="Default select example">
		  <option value>선택</option>
		  <option value="memId">작성자</option>
		  <option value="boTitle">제목</option>
		  <option value="boContent">내용</option>
		</select>
		  <input id="searchValue" type="text" class="form-control" aria-label="Text input with dropdown button">
		</div>
		<div class="m-left-5">
			<button id="searchBtn" class="linkBtn btn btn-gray" data-gopage="${cPath }/outs/board/interview/interviewList.do">검색</button>
			<input type="button" value="새글쓰기" class="linkBtn btn btn-gray" 
					data-gopage="${cPath }/outs/board/interview/interviewInsert.do"/>
		</div>
	</div>
	<div class="table-wrap">
		<c:set var="list" value="${master.dataList }" />
		<c:choose>
			<c:when test="${empty list }">
				<div class="interview-box-wrap">
                    <span>게시글이 없습니다.</span>
                </div>  
			</c:when>
			<c:otherwise>
			<c:forEach var="interview" items="${list}" varStatus="status">
			  <c:if test="${ status.index % 3 eq 0 }">
			    <div class="interview-box-wrap">
			      <c:forEach var="j" begin="${ status.index }" end="${ status.index + (3 - 1) }" step="1">
			        <c:if test="${ list[j] ne null }">
						<c:url value="/outs/board/interview/interviewView.do" var="interviewURL">
		                    <c:param name="what" value="${list[j].boNum }"/>
						</c:url>
			          <div class="interview-box" onclick="onClickDetailView('${interviewURL}')">
						<div class="interview-card">
							<div class="interview-img-wrap">
								<img class="interview-img" src="${cPath }/imageRender.do?what=${list[j].devImg }">
							</div>
							<div class="interview-txt-wrap">
								<c:set var="titleArr" value="${fn:split(list[j].boTitle,'|')}" />
								<h6>${titleArr[1]}</h6>
								<span>${titleArr[0]}</span>
							</div>
						</div>
						<div class="arrow-wrap">
							<img alt="" src="<%=request.getContextPath()%>/resources/images/arrow.png">
						</div>
					</div>
			        </c:if>
			      </c:forEach>
			    </div>
			  </c:if>
			</c:forEach>
				
			</c:otherwise>
		</c:choose>
		</div>
		<div class="paging-wrap">
		    <form id="searchForm">
				<input type="hidden" name="boTitle" value="${searchVO.boTitle }"/>
				<input type="hidden" name="boContent" value="${searchVO.boContent }"/>
				<input type="hidden" name="memId" value="${searchVO.memId }"/>
				<input type="hidden" name="page" value="${searchVO.page }"/>
			</form>
			<div id="pagingArea">${masterVO.pagingHTML }</div>
		</div>
</section>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript">	
$("#searchBtn").on("click", function() {
	let searchType = $("#searchTag").val()
	let searchWord = $("#searchValue").val()
	console.log(searchType)
	console.log(searchWord)
	if(searchType) {
		$("#searchForm").find("[name='"+searchType+"']").val(searchWord)		
	}
	$("#searchForm").submit()
})

	let searchForm = $("#searchForm").paging();

function onClickDetailView(url) {
	location.href = url;
}
</script>
