<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/board.css">
<div class="sub-wrap">
	<div class="sub-inner-box">
		<div class="sub-inner-txt2">
			<span class="sub-txt">코드북 게시판<br>
				<span class="project-txt-lighter">고민되는 것들 이곳에다 물어보자!</span>
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
		<span>코드북 게시판 ></span>
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
			<button id="searchBtn" class="btn btn-gray">검색</button>
			<input type="button" value="새글쓰기" class="linkBtn btn btn-gray" 
					data-gopage="${cPath }/outs/board/codebook/codeInsert.do"/>
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
			<tbody>
			<c:set var="boardList" value="${masterVO.dataList }" />
				<c:choose>
					<c:when test="${not empty boardList }">
						<c:forEach items="${boardList }" var="board">
						<tr>
							<td>${board.rnum }</td>
								<c:url value="/outs/board/codebook/codeView.do" var="viewURL">
							  	<c:param name="what" value="${board.boTitle }" />
							  </c:url>
							  <td class="linkBtn text-left" data-gopage="${viewURL }" >${board.boTitle }</td>
		                      <td>${board.writeDate }</td>
		                      <td>${board.boHit }</td> 
		                      <td>${board.memId}</td>
		                   </tr>    
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5">검색 조건에 맞는 게시글이 없음.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<div class="paging-wrap">
		<nav aria-label="Page navigation example">
		  <ul class="pagination">
		    <li class="page-item">

		    </li>
		    <form id="searchForm">
			<input type="hidden" name="boTitle" value="${searchVO.boTitle }"/>
			<input type="hidden" name="boContent" value="${searchVO.boContent }"/>
			<input type="hidden" name="memId" value="${searchVO.memId }"/>
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

<script>

let boWriter = $("[name='boWriter']").val()
let boTitle = $("[name='boTitle']").val()

let boWriterTag = $("#boWriter");
let boTitleTag = $("#boTitle");

$(function() {
	if(boWriter) {
		$("#boWriter").parents(".filterWrapper").show()
	} else if(!boWriter) {
		$("#boWriter").parents(".filterWrapper").hide()
	}
	
	if(boTitle) {
		$("#boTitle").parents(".filterWrapper").show()
	} else if(!boTitle) {
		$("#boTitle").parents(".filterWrapper").hide()
	}
})

$("#filterAdd").on("change", function() {
	let searchName = $("#filterAdd option:selected").val()
	let filterTag = $("#" + searchName)
	let filterDiv = filterTag.parents(".filterWrapper")
	filterDiv.show()
});

$(".btn-secondary").on("click", function() {
// 	event.preventDefault()
	let filterDiv = $(this).parents(".filterWrapper")
	filterDiv.toggle()
	filterDiv.find(".filterInput").val("")
});

$("#filterBtn").on("click", function() {
	
	let boWriter = boWriterTag.val()
	let boTitle = boTitleTag.val()
	
	$("[name='boWriter']").val(boWriter)
	$("[name='boTitle']").val(boTitle)
	searchForm.submit()
})
</script>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript">	
	

	let searchForm = $("#searchForm").paging();
	
	
</script>
