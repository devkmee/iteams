<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/board.css">
<div class="sub-wrap">
	<div class="sub-inner-box">
		<div class="sub-inner-txt">
			<span class="sub-txt">프로젝트 공고<br>
				<span class="project-txt-lighter">커리어 관리가 즐거워지고, 오늘보다 더 나은 내일을 꿈꿀 수 있는 곳<br>아이팀즈입니다.</span>
			</span>
		</div>
		<div class="main-outline-box-img m-right-10">
			<img alt="" src="<%=request.getContextPath()%>/resources/images/project-img.png">
		</div>
	</div>
</div>
<section class="section-wrap">
	<div class="project-list-title m-bottom-10 m-top-30">
		<span>검색 결과 리스트 ></span>
	</div>
	<div class="board-search-wrap">
		<div class="input-group mb-3 width-400">
			<input id="what" type="text" class="form-control" placeholder="검색어" value="${searchVO.what }"/>
			<input type="button" id="searchBtn" class="btn btn-gray m-left-5" value="검색">
		</div>
	</div>
	<div class="table-wrap">
	<table class="table">
		<thead class="thead-light">
			<tr class="tr-gray">
				<td scope="col">번호</td>
				<td scope="col">제목</td>
				<td scope="col">종류</td>
				<td scope="col">작성자</td>
				<td scope="col">조회수</td>
			</tr>
		</thead>
		<tbody id="listBody">
			<c:if test="${not empty masterVO.dataList }">
				<c:forEach items="${masterVO.dataList }" var="board">
					<c:if test="${board.boCode eq 'GF'}">
						<c:url var="viewUrl" value="/outs/board/free/freeBoardDetail.do">
							<c:param name="what" value="${board.boNum}"></c:param>
						</c:url>
					</c:if>
					<c:if test="${board.boCode eq 'GI'}">
						<c:url var="viewUrl" value="/outs/board/interview/interviewView.do">
							<c:param name="what" value="${board.boNum}"></c:param>
						</c:url>
					</c:if>
					<c:if test="${board.boCode eq 'GC'}">
						<c:url var="viewUrl" value="/outs/board/codebook/codeView.do">
							<c:param name="what" value="${board.boNum}"></c:param>
						</c:url>
					</c:if>
					<c:if test="${board.boCode eq 'GN'}">
						<c:url var="viewUrl" value="/outs/board/news/newsView.do">
							<c:param name="what" value="${board.boNum}"></c:param>
						</c:url>
					</c:if>
					<c:if test="${board.boCode eq 'GP'}">
						<c:url var="viewUrl" value="/outs/board/projectboard/projectView.do">
							<c:param name="what" value="${board.boNum}"></c:param>
						</c:url>
					</c:if>
					<tr class="linkBtn" style="cursor: pointer;" data-gopage="${viewUrl}">
						<th scope="row">${board.rnum }</th>
						<td class="text-left">${board.boTitle }</td>
						<td>${board.boCodeValue }</td>
						<td>${board.memId }</td>
						<td>${board.boHit }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty masterVO.dataList }">
				<tr>
					<td colspan="5">검색 결과가 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	</div>	
	<div class="paging-wrap">
		<div id="pagingArea">${masterVO.pagingHTML}</div>
	</div>

	<form action="" id="searchForm">
		<input type="hidden" name="page" value="${searchVO.page }" /> <input
			type="hidden" name="what" value="${searchVO.what }" />
	</form>
</section>

	<script>
		let searchForm = $("#searchForm").paging()
		
		$("#searchBtn").on("click", function() {
			let what = $("#what").val()
			$("[name='what']").val(what)
			searchForm.submit()
		})
	</script>
