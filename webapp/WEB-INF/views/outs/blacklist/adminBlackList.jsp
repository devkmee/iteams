<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mypage.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
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
				<span class="sub-mypage-txt"> 관리페이지 ></span>
			</div>
				<div class="tab-wrap">
					<ul class="nav nav-tabs">
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/admin/member/mypageAdmin.do">회원 관리</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link active" href="#">블랙리스트 관리</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/admin/blacklist/repBoardList.do">신고글 관리</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/admin/board/projectboard/approvalList.do">승인 대기 프로젝트</a>
					  </li>
					</ul>
					
					<div class="tab-content" id="myTabContent">
			   	   	   <div>
							<div class="member-basic-wrap">
								<div class="board-search-wrap">
									<div class="input-group mb-3">
									</div>
								</div>
								<table class="table">
									<thead class="thead-light">
									    <tr>
									      <th scope="col" class="text-center">NO</th>
									      <th scope="col" class="text-center">아이디</th>
									      <th scope="col" class="text-center">차단사유</th>
									      <th scope="col" class="text-center">차단 관리자</th>
									      <th scope="col" class="text-center">차단일</th>
									      <th scope="col" class="text-center">해제 관리자</th>
									      <th scope="col" class="text-center">해제일</th>
									    </tr>
								 	</thead>
								 	<c:set var="memberList" value="${member.dataList}" />
									<tbody>
										<c:choose>
											<c:when test="${not empty memberList}">
												<c:forEach items="${memberList}" var="black">
													<c:url value="/outs/admin/member/memberView.do" var="selectURL">
														<c:param name="memId" value="${black.memId}"></c:param>
														<c:param name="memRole" value="${black.memRole}"></c:param>
													</c:url>
													<tr style="cursor: pointer;">
														<td class="linkBtn text-center" data-gopage="${selectURL}">${black.rnum}</td>
														<td class="linkBtn text-center" data-gopage="${selectURL}">${black.memId}</td>
														<td class="linkBtn text-center" data-gopage="${selectURL}">${black.blackContent}</td>
														<td class="linkBtn text-center" data-gopage="${selectURL}">${black.adminId}</td>
														<td class="linkBtn text-center" data-gopage="${selectURL}">${black.regDate}</td>
														
														<c:choose>
															<c:when test="${not empty black.clearAdmin }">
																<td class="linkBtn text-center"  data-gopage="${selectURL}">${black.clearAdmin}</td>
																<td class="text-center">${black.clearDate }</td>
															</c:when>
															<c:otherwise>
																<td class="linkBtn text-center" data-gopage="${selectURL}">차단중</td>
															<form action="${cPath }/outs/admin/blacklist/blackUpdate.do" method="post">
																<input type="hidden" name="blackNo" value="${black.blackNo}">
																<td><div class="flex-center"><input type="submit" value="차단해제" class="btn btn-sm btn-gray"></div></td>
															</form>
															</c:otherwise>
														</c:choose>
													</tr>
													
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td colspan="6">블랙리스트 회원이 존재하지 않습니다</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
									<div class="paging-wrap">
										<div id="pagingArea"> ${member.pagingHTML } </div>	
									</div>
							</div>
					    </div>
					   </div>
					    
<form id="searchForm">
	<input type="hidden" name="page" />
</form>
<script type="text/javascript">	
let searchForm = $("#searchForm").paging();
</script>
				</div>
			</div>
		</div>
	</div>