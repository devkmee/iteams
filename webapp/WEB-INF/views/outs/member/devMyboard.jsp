<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mypage.css">

<div class="login-content-wrap">
	<div class="mypage-wrap">
		<div class="sub-mypage-wrap">
			<div class="sub-inner-box">
				<div class="main-outline-box-img m-right-10">
					<img alt="" src="/iteams/resources/images/mypage-img.png">
				</div>
			</div>
		</div>
		<div class=" mypage-tab-wrap">
			<div class="sub-mypage-txt">
				<span class="sub-mypage-txt"> 마이페이지 ></span>
			</div>
			<!-- 개인일정 -->
			<div class="flex-end">
				<a href="<%=request.getContextPath()%>/outs/schedule/scheduleList.do">
					<img class="m-right-2" src="/iteams/resources/images/mycal.png">
				</a>
			</div>
			<!-- 개인일정 -->
				<div class="tab-wrap">
					<ul class="nav nav-tabs">
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/member/mypageDevInfo.do">회원정보</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link active" href="${cPath}/outs/member/mypageBoard.do">나의 게시글</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/member/mypageApplyInvite.do">지원/초대받은 프로젝트</a>
					  </li>
					  <li class="nav-item2">
					    <a class="nav-link" href="${cPath}/outs/member/mypageOngoingEnd.do">진행/완료 프로젝트</a>
					  </li>
					</ul>
					
					<div class="tab-content" id="myTabContent">
			   	   	 			   	   	   <div>
							<div class="member-basic-wrap">
								<div class="board-search-wrap">
									<div class="input-group mb-3">
									</div>
								</div>
								<table class="table text-center">
									<thead class="thead-light">
									    <tr>
									      <th scope="col">NO</th>
									      <th scope="col">게시판</th>
									      <th scope="col">제목</th>
									      <th scope="col">작성일</th>
									    </tr>
								 	</thead>
								 	<c:set var="boardList" value="${data.dataList}" />
									<tbody>
										<c:choose>
											<c:when test="${not empty boardList}">
												<c:forEach items="${boardList}" var="board">
													<c:choose>
														<c:when test="${board.boCode eq 'GP'}">
															<c:url value="/outs/board/projectboard/projectView.do" var="GPurl">
																<c:param name="what" value="${board.boNum}"></c:param>
															</c:url>
															<tr style="cursor: pointer;">
																<td class="linkBtn"  data-gopage="${GPurl}">${board.rnum}</td>
																<td class="linkBtn"  data-gopage="${GPurl}">${board.boCodeValue}</td>
																<td class="linkBtn text-left"  data-gopage="${GPurl}">${board.boTitle}</td>
																<td class="linkBtn"  data-gopage="${GPurl}">${board.writeDate}</td>
															</tr>
														</c:when>
														<c:when test="${board.boCode eq 'GF'}">
															<c:url value="/outs/board/free/freeBoardDetail.do" var="GFurl">
																<c:param name="what" value="${board.boNum}"></c:param>
															</c:url>
															<tr style="cursor: pointer;">
																<td class="linkBtn"  data-gopage="${GFurl}">${board.rnum}</td>
																<td class="linkBtn"  data-gopage="${GFurl}">${board.boCodeValue}</td>
																<td class="linkBtn text-left"  data-gopage="${GFurl}">${board.boTitle}</td>
																<td class="linkBtn"  data-gopage="${GPurl}">${board.writeDate}</td>
															</tr>
														</c:when>
														<c:when test="${board.boCode eq 'GN'}">
															<c:url value="/outs/board/news/newsView.do" var="GNurl">
																<c:param name="what" value="${board.boNum}"></c:param>
															</c:url>
															<tr style="cursor: pointer;">
																<td class="linkBtn"  data-gopage="${GNurl}">${board.rnum}</td>
																<td class="linkBtn"  data-gopage="${GNurl}">${board.boCodeValue}</td>
																<td class="linkBtn text-left"  data-gopage="${GNurl}">${board.boTitle}</td>
																<td class="linkBtn"  data-gopage="${GNurl}">${board.writeDate}</td>
															</tr>
														</c:when>
														<c:when test="${board.boCode eq 'GQ'}">
															<c:url value="/outs/board/qna/qnaView.do" var="QAurl">
																<c:param name="boNum" value="${board.boNum}"></c:param>
															</c:url>
															<tr style="cursor: pointer;">
																<td class="linkBtn"  data-gopage="${QAurl}">${board.rnum}</td>
																<td class="linkBtn"  data-gopage="${QAurl}">${board.boCodeValue}</td>
																<td class="linkBtn text-left"  data-gopage="${QAurl}">${board.boTitle}</td>
																<td class="linkBtn"  data-gopage="${QAurl}">${board.writeDate}</td>
															</tr>
														</c:when>
													</c:choose>
															
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td colspan="6">작성 게시물이 없습니다</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
									<div class="paging-wrap">
										<div id="pagingArea"> ${data.pagingHTML} </div>	
									</div>
							</div>
					    </div>
				</div>
			</div>
		</div>
	</div>
</div>

<form id="searchForm">
	<input type="hidden" name="page" />
</form>
<script>
let searchForm = $("#searchForm").paging();
</script>