<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/board.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/table.css">
<div class="sub-wrap">
	<div class="sub-inner-box">
		<div class="sub-inner-txt2">
			<span class="sub-txt">자주묻는 질문<br>
				<span class="project-txt-lighter">궁금한 점이 있으면 검색해주세요!</span>
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
		<span>자주묻는 질문 ></span>
	</div>
	<div class="flex-end m-bottom-10">
		<div id="searchUI" class="input-group-prepend width-200">
			<select name="qnaKeyword" id="qnaKeyword" class="form-control">
		  		<option value="0">키워드</option>
		  		<option value="1" <c:if test="${searchVO.qnaKeyword eq '1'}">selected</c:if> >계정 정보</option>
		  		<option value="2" <c:if test="${searchVO.qnaKeyword eq '2'}">selected</c:if> >프로젝트 관리</option>
		  		<option value="3" <c:if test="${searchVO.qnaKeyword eq '3'}">selected</c:if> >프로젝트 검색/지원</option>
		  		<option value="4" <c:if test="${searchVO.qnaKeyword eq '4'}">selected</c:if> >개발자 검색/초대</option>
		  		<option value="5" <c:if test="${searchVO.qnaKeyword eq '5'}">selected</c:if> >PMS</option>
		  	</select>
		</div>
				<c:if test="${authMember.memRole eq 'ADMIN' }">
			<c:url value="/outs/admin/board/qna/faqInsert.do" var="insertURL"></c:url>
			<input type="button" value="새글쓰기" class="linkBtn btn btn-gray m-left-5" data-gopage="${insertURL}"/>
		</c:if>
	</div>
	<div class="table-wrap">
		<table class="table">
			  <thead class="thead-light">
			    <tr class="tr-gray">
			      <td scope="col">No</td>
			      <td scope="col">키워드</td>
			      <td scope="col">제목</td>
			      <td scope="col">날짜</td>
			      <td scope="col">조회수</td>
			      <td scope="col">작성자</td>
			    </tr>
			  </thead>
			<tbody>
				<c:set var="boardList" value="${board.dataList}"></c:set>
				<c:choose>
					<c:when test="${empty boardList}">
						<tr>
							<td scope="row">자주묻는 질문 글이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${boardList }" var="board">
							<tr>
								<td>${board.rnum }</td>
								<td>${board.qnaKeywordValue }</td>
								<td class="text-left"><a href="${cPath }/outs/board/qna/faqView.do?boNum=${board.boNum }">${board.boTitle }</a></td>
								<c:choose>
									<c:when test="${empty board.modifyDate}">
										<td>${board.writeDate }</td>
										<td>${board.boHit }</td>
										<td>${board.memId }</td>
									</c:when>
									<c:otherwise>
										<td>${board.modifyDate }</td>
										<td>${board.boHit }</td>
										<td>${board.modifyMember }</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>

					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<div class="paging-wrap">
			<div id="pagingArea">
				${board.pagingHTML }
			</div>
		</div>
	</div>
 </section>
<form id="searchForm" >
	<input type="hidden" name="qnaKeyword" />
	<input type="hidden" name="page" /> <br>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript">
	let searchForm = $("#searchForm");
	let qnaKeyword = $("#qnaKeyword")
	
	$("#qnaKeyword").on("change", function(){
		let selected = $(this).val();
		if(selected == 0){
			location.href="${cPath}/outs/board/qna/faqList.do";
		}else{
			searchForm.find("input[name='qnaKeyword']").val(selected);
			searchForm.submit();
		}
	});
	
	searchForm.pagingForQna();
	
</script>