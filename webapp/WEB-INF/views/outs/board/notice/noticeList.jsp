<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/board.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/table.css">
<div class="sub-wrap">
	<div class="sub-inner-box">
		<div class="sub-inner-txt2">
			<span class="sub-txt">공지사항<br>
				<span class="project-txt-lighter">아이팀즈 공지사항입니다.</span>
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
			<a href="<%=request.getContextPath()%>/outs/board/notice/noticeList.do">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/notice.png">
			</a>
		</div>
		<div class="community-box" id="FAQDiv">
			<a href="<%=request.getContextPath()%>/outs/board/qna/faqList.do">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/faq.png">
			</a>
		</div>
		<div class="community-box" id="QNADiv">
			<a href="<%=request.getContextPath()%>/outs/board/qna/qnaList.do">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/qna.png">
			</a>
		</div>
	</div>
	<div class="project-list-title m-top-20">
		<span>공지사항 ></span>
	</div>
	<div class="board-search-wrap">
		<div class="mb-3">
			<div id="searchUI" class="input-group">
				<select name="searchType" class="search-select">
					<option value>전체</option>
					<option value="boTitle">제목</option>
					<option value="boContent">내용</option>
				</select>
				<input type="text" name="searchWord" class="form-control form-radius"/>
				<input type="button" id="searchBtn" class="btn btn-gray m-left-5" value="검색"/>
				<c:if test="${authMember.memRole eq 'ADMIN' }">
					<c:url value="/outs/admin/board/notice/noticeInsert.do" var="insertURL"></c:url>
					<input type="button" value="새글쓰기" class="linkBtn btn btn-gray m-left-5" data-gopage="${insertURL}"/>
				</c:if>
			</div>
		</div>
	</div>
	<div class="table-wrap">
		<table class="table">
			  <thead class="thead-light">
			    <tr class="tr-gray">
			      <td scope="col">No</td>
			      <td scope="col">제목</td>
			      <td scope="col">날짜</td>
			      <td scope="col">조회수</td>
			      <td scope="col">작성자</td>
			    </tr>
			  </thead>
			<tbody id="listBody"></tbody>
		</table>
		<div class="paging-wrap">
			<div id="pagingArea">
				${data.pagingHTML }
			</div>
		</div>
	</div>
 </section>

<form id="searchForm">
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="page" />
</form>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript">	
	let listBody = $("#listBody");
	let searchForm = $("#searchForm");
	let pagingArea = $("#pagingArea");
	let pageTag = $("[name='page']");
	
	searchForm.paging({
		pagingArea	: pagingArea,
		pageKey		: "page",
		pageName	: "[name='page']"
	}).ajaxForm({
		dataType	: "json",
		success		: function (resp) {
			listBody.empty();
			pagingArea.empty();
			pageTag.val("");
			let boardList = resp.dataList;
			let pagingHTML = resp.pagingHTML;
			let trTags = [];
			if(boardList){
				$.each(boardList, function (idx, board) {
					let date =null;
					let boHit =null;
					let member =null;
					
					let trTag = $("<tr>").append(
						$("<td>").html(board.rnum).attr({
							class:"text-left"
						}),
						$("<td>").html(
							$("<a>").html(board.boTitle).attr({
								href:"${pageContext.request.contextPath }/outs/board/notice/noticeView.do?boNum="+board.boNum
							})
						).attr({
							class:"text-left"
						}));
						if(board.modifyDate == null){
							date = $("<td>").html(board.writeDate),
							boHit = $("<td>").html(board.boHit),
							member = $("<td>").html(board.memId)
						}else{
							date = $("<td>").html(board.modifyDate),
							boHit = $("<td>").html(board.boHit),
							member = $("<td>").html(board.modifyMember)
						}
						trTag.append(date);
						trTag.append(boHit);
						trTag.append(member);
					trTags.push(trTag);
				})
			}else{
				let trTag = $("<tr>").html(
					$("<td>").attr({
						colspan:"5"
					})		
				);
				trTags.push(trTag);
			}
			listBody.append(trTags);
			pagingArea.html(pagingHTML);
		}
	}).submit();
</script>