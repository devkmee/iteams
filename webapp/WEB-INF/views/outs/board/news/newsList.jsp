<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/board.css">
<div class="sub-wrap">
	<div class="sub-inner-box">
		<div class="sub-inner-txt2">
			<span class="sub-txt">뉴스피드 게시판<br>
				<span class="project-txt-lighter">최신 기술 동향 정보를 한눈에!!</span>
			</span>
		</div>
		<div class="main-outline-box-img m-right-10">
			<img alt="" src="<%=request.getContextPath()%>/resources/images/community.png">
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
		<span>뉴스피드 게시판 ></span>
	</div> 
	
	<!-- 게시판전체보기리스트로출력 -->
	<div class="board-search-wrap">
		<div class="input-group mb-3 width-400">
		  <select id="searchTag" class="form-select search-select" aria-label="Default select example">
		  <option value>전체</option>
		  <option value="memId" <c:if test="${not empty searchVO.memId }">selected</c:if> >작성자</option>
		  <option value="boTitle" <c:if test="${not empty searchVO.boTitle }">selected</c:if>>제목</option>
		  <option value="boContent" <c:if test="${not empty searchVO.boContent }">selected</c:if>>내용</option>
		</select>
		  <input value='<c:if test="${searchVO.memId }">${searchVO.memId }</c:if><c:if test="${searchVO.boTitle }">${searchVO.boTitle }</c:if><c:if test="${searchVO.boContent }">${searchVO.boContent }</c:if>'  id="searchValue" type="text" class="form-control" aria-label="Text input with dropdown button">
		</div>
		<div class="m-left-5">
			<button id="searchBtn" class="btn btn-search">검색</button>
			<input type="button" value="새글쓰기" class="linkBtn btn btn-gray" 
					data-gopage="${cPath }/outs/board/news/newsInsert.do"/>
		</div>
	</div>
	<div class="table-wrap">
		<table class="table">
		  <thead class="thead-light">
		    <tr class="tr-gray">
		      <td scope="col">No</td>
		      <td scope="col">글 제목</td>
		      <td scope="col">날짜</td>
		      <td scope="col">조회수</td>
		      <td scope="col">작성자</td>
		    </tr>
		  </thead>
		  <c:set var="boardList" value="${masterVO.dataList }" />
			<tbody>
				<c:choose>
					<c:when test="${not empty boardList }">
						<c:forEach items="${boardList }" var="board">
							<tr>
								<td>${board.rnum }</td>
								<td class="text-left">
									<c:url value="/outs/board/news/newsView.do" var="viewURL">
										<c:param name="what" value="${board.boNum }" />
									</c:url>
									<a href="${viewURL }">${board.boTitle }</a>
								</td>
								<td>${board.writeDate }</td>
								<td>
									<c:if test="${not empty board.boHit }">${board.boHit }</c:if>
									<c:if test="${empty board.boHit }">0</c:if>	
								</td>
								<td>${board.memId }</td>
								
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6">검색 조건에 맞는 게시글이 없음.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
	</table>

<!-- 검색기능구현 -->
	<div class="paging-wrap">
		<nav aria-label="Page navigation example">
		  <ul class="pagination">
		    <li class="page-item">

		    </li>
		    <form id="searchForm">
			<input id="boTitleTag" type="hidden" name="boTitle" value="${searchVO.boTitle }"/>
			<input id="boContentTag" type="hidden" name="boContent" value="${searchVO.boContent }"/>
			<input id="memIdTag" type="hidden" name="memId" value="${searchVO.memId }"/>
			<input type="hidden" name="page" value="${searchVO.page }"/>
			</form>
				<div id="pagingArea">
				${masterVO.pagingHTML }
				</div>
			  </ul>
		  </nav>	
		</div>
	</div>
</section>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript">	

$("#searchTag").on("change", function() {
	let temp = $(this).val()
	if(!temp) {
		$("#boTitleTag").val("")
		$("#boContentTag").val("")
		$("#memIdTag").val("")
	}
	
	if(temp == "boTitle") {
		$("#boContentTag").val("")
		$("#memIdTag").val("")
	} else if(temp == "boContent") {
		$("#boTitleTag").val("")
		$("#memIdTag").val("")
	} else if(temp == "memId") {
		$("#boTitleTag").val("")
		$("#boContentTag").val("")
	}
})

$("#searchBtn").on("click", function() {
	let searchType = $("#searchTag").val()
	let searchWord = $("#searchValue").val()
	console.log(searchType)
	console.log(searchWord)
	if(searchType) {
		console.log("1")
		$("#searchForm").find("[name='"+searchType+"']").val(searchWord)		
	}
	searchForm.submit()
})

let searchForm = $("#searchForm").paging();
</script>
