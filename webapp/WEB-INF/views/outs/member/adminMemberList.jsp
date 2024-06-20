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
					    <a class="nav-link active" href="#">회원 관리</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/admin/blacklist/blackList.do">블랙리스트 관리</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/admin/blacklist/repBoardList.do">신고글 관리</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/admin/board/projectboard/approvalList.do">승인 대기 프로젝트</a>
					  </li>
					</ul>
					
					<div class="tab-content" id="myTabContent">
<!-- 회원관리 -->
			   	   	   <div>
							<div class="member-basic-wrap">
								<div class="board-search-wrap">
									<div class="m-top-20">
									  <div id="searchUI" class="input-group mb-3 width-400">
									  	<select name="memRole" id="memRole" class="form-select search-select">
									  		<option value>권한</option>
									  		<option value="DEV">개발자</option>
									  		<option value="CLIENT">클라이언트</option>
<!-- 									  		<option value="ADMIN">관리자</option> -->
									  	</select>
									    <select name="searchType"  class="form-select search-select m-left-5">
											<option value>이름</option>
											<option value="memId">아이디</option>
										</select>
										<input type="text" name="searchWord" class="form-control m-left-5">
										<input type="button" value="검색" id="searchBtn"  class="linkBtn btn btn-gray m-left-5"/>
									  </div>
									</div>
								</div>
								<table class="table" style="text-align: center;">
									<thead class="thead-light">
									    <tr>
									      <th scope="col">NO</th>
									      <th scope="col">권한</th>
									      <th scope="col">아이디</th>
									      <th scope="col">이름</th>
									    </tr>
								 	</thead>
								 	<c:set var="memberList" value="${base.dataList }" />
									<tbody>
										<!-- 회원 조회 for문 -->
										<c:choose>
											<c:when test="${not empty memberList }">
												<c:forEach items="${memberList }" var="member">
													<c:url value="/outs/admin/member/memberView.do" var="selectURL">
														<c:param name="memId" value="${member.memId }"></c:param>
														<c:param name="memRole" value="${member.memRole }"></c:param>
													</c:url>
													<tr class="linkBtn"  data-gopage="${selectURL }" style="cursor: pointer;">
														<td>${member.rnum }</td>
														<td>${member.memRoleValue }</td>
														<td>${member.memId }</td>
														<c:choose>
															<c:when test="${not empty member.devName }">
																<td>${member.devName }</td>
															</c:when>
															<c:when test="${not empty member.clientName }">
																<td>${member.clientName }</td>
															</c:when>
															<c:otherwise>
																<td>관리자</td>
															</c:otherwise>
														</c:choose>
													</tr>
													
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td colspan="6">조건에 해당하는 회원을 찾을 수 없습니다.</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
									<div class="paging-wrap">
										<div id="pagingArea"> ${base.pagingHTML } </div>	
									</div>
							</div>
					    </div>
					    
<form id="searchForm">
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="memRole" />
	<input type="hidden" name="page" />
</form>
<script type="text/javascript">
$("[name='memRole']").val("${detailSearch.memRole}");
$("[name='searchType']").val("${searchVO.searchType}");
$("[name='searchWord']").val("${searchVO.searchWord}");
let searchForm = $("#searchForm").paging();
</script>				
				</div>
			</div>
		</div>
	</div>
</div>