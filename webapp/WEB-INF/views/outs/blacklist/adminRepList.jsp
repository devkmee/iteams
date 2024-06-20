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
					    <a class="nav-link" href="${cPath}/outs/admin/blacklist/blackList.do">블랙리스트 관리</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link active" href="${cPath}/outs/admin/blacklist/repBoardList.do">신고글 관리</a>
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
								<table class="table text-center">
									<thead class="thead-light">
									    <tr>
									      <th scope="col">NO</th>
									      <th scope="col">게시판</th>
									      <th scope="col">글번호</th>
									      <th scope="col">제목</th>
									      <th scope="col">작성자</th>
									      <th scope="col" colspan="2">관리</th>
									    </tr>
								 	</thead>
								 	<c:set var="repList" value="${rep.dataList}" />
									<tbody>
										<c:choose>
											<c:when test="${not empty repList}">
												<c:forEach items="${repList}" var="rep">
													<c:choose>
														<c:when test="${rep.boCode eq 'GP'}">
															<c:url value="/outs/board/projectboard/projectView.do" var="GPurl">
																<c:param name="what" value="${rep.boNum}"></c:param>
															</c:url>
															<tr style="cursor: pointer;">
																<td class="linkBtn"  data-gopage="${GPurl}">${rep.rnum}</td>
																<td class="linkBtn"  data-gopage="${GPurl}">${rep.boCodeValue}</td>
																<td class="linkBtn"  data-gopage="${GPurl}">${rep.boNum}</td>
																<td class="linkBtn text-left"  data-gopage="${GPurl}">${rep.boTitle}</td>
																<td class="linkBtn"  data-gopage="${GPurl}">${rep.memId}</td>
														</c:when>
														<c:when test="${rep.boCode eq 'GF'}">
															<c:url value="/outs/board/free/freeBoardDetail.do" var="GFurl">
																<c:param name="what" value="${rep.boNum}"></c:param>
															</c:url>
															<tr style="cursor: pointer;">
																<td class="linkBtn"  data-gopage="${GFurl}">${rep.rnum}</td>
																<td class="linkBtn"  data-gopage="${GFurl}">${rep.boCodeValue}</td>
																<td class="linkBtn"  data-gopage="${GFurl}">${rep.boNum}</td>
																<td class="linkBtn text-left"  data-gopage="${GFurl}">${rep.boTitle}</td>
																<td class="linkBtn"  data-gopage="${GPurl}">${rep.memId}</td>
														</c:when>
													</c:choose>
																<td><input type="button" onclick="insertBlackMem('${rep.memId}','${rep.boNum}')" value="차단" class="linkBtn btn btn-sm btn-gray"/></td>
															<form>
																<td><input type="button" onclick="clearRep('${rep.boNum}')" value="신고 해제" class="linkBtn btn btn-sm btn-gray"></td>
															</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td colspan="6">신고된 게시글이 없습니다</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
									<div class="paging-wrap">
										<div id="pagingArea"> ${rep.pagingHTML } </div>	
									</div>
							</div>
					    </div>
					    
					    
<div class="modal fade" id="blackModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <h6 class="modal-title" id="exampleModalLabel">블랙리스트 등록 사유 </h6>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div id="modalBody" class="modal-body">
		      <input type="checkbox"  name="blackContent" value="도배 게시물" aria-label="Checkbox for following text input">
		      <span>도배 게시물</span><br>
		      <input type="checkbox"  name="blackContent" value="부적절한 홍보" aria-label="Checkbox for following text input">
		      <span>부적절한 홍보</span><br>
		       <input type="checkbox"  name="blackContent" value="명예훼손 및 저작권 침해" aria-label="Checkbox for following text input">
		      <span>명예훼손 및 저작권 침해</span><br>
		      <input type="checkbox"  name="blackContent" value="음란성 또는 청소년에게 부적합한 내용" aria-label="Checkbox for following text input">
		      <span>음란성 또는 청소년에게 부적합한 내용</span><br>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-sm btn-gray" data-dismiss="modal">취소</button>
        <button type="button" id="blackSubmitBtn" class="btn btn-sm btn-primary">등록</button>
      </div>
    </div>
  </div>
</div>
					    
<form id="searchForm">
	<input type="hidden" name="page" />
</form>

<form id="blackForm" action="${cPath}/outs/blacklist/blackInsert.do" method="post">
	<input type="hidden" name="blackContent">
	<input type="hidden" name="devId">
	<input type="hidden" name="boNum">
</form>

<form id="clearForm" action="${cPath}/outs/admin/blacklist/clearRep.do" method="post">
	<input type="hidden" name="boNum">
</form>

<script type="text/javascript">	
let searchForm = $("#searchForm").paging();

//블랙리스트 등록
let blackModal = $("#blackModal");
let blackForm = $("#blackForm");
let modalBody = $("#modalBody");

function insertBlackMem(devId, boNum) {
	blackModal.modal("show")
	blackForm.find(":input[name='devId']").val(devId);
	blackForm.find(":input[name='boNum']").val(boNum);
};

blackModal.on('hide.bs.modal', function (event) {
	blackModal.find(":input[name='blackContent']").val("");
})

$("#blackSubmitBtn").on('click', function (event) {
	let selected = modalBody.find("[name='blackContent']").val()
	blackForm.find(":input[name='blackContent']").val(selected);
	blackForm.submit();
	blackModal.modal("hide");
})


//신고해제
let clearForm = $("#clearForm");

function clearRep(boNum) {
	clearForm.find(":input[name='boNum']").val(boNum);
	clearForm.submit();
};
</script>
				</div>
			</div>
		</div>
	</div>