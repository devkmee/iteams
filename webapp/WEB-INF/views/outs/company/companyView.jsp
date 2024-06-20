<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/projectList.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/company.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/board.css">

<div class="main-wrap">
	<div class="company-inner-box">
		<div class="company-inner-txt">
			<span class="main-txt"> 기업정보<br> <span class="company-txt-lighter">나에게 맞는 회사를 찾는 일, 생각보다 쉽지 않죠?
			<br>아이팀즈가 좋은 기업 발굴부터 자료 분석까지 함께 연구합니다.
			</span>
			</span>
		</div>
	</div>
	
<form id="searchForm" action="${cPath}/outs/company/companyList.do">
	<input type="hidden" name="searchWord" id="searchWord"/>
	<input type="hidden" name="page" />
</form>
		<div class="main-outline-box">
		<div class="searchbar-wrap">
			<div id="searchUI" class="input-group search-btn">
				<input type="text" name="searchWord" class="form-control" placeholder="기업명을 입력해주세요"
					aria-label="Recipient's username" aria-describedby="basic-addon2">
				<div class="input-group-append">
					<span style="cursor: pointer;" class="input-group-text" id="searchBtn">검색</span> 
				</div>
			</div>
			<div class="search-word-wrap">
				<span class="search-word-txt">추천 검색어</span>
			<c:if test="${not empty authMember.recList}">
				<div class="btn-wrap m-left-10">	
					<c:forEach items="${authMember.recList}" var="rec">
						<button class="btn-search-blue" data-word="${rec}">#${rec}</button>					
					</c:forEach>
				</div>
			</c:if>
				<c:if test="${empty authMember.recList}">	
					<div class="btn-wrap m-left-10">
						<c:forEach items="${rankList.dataList}" var="rank" begin="0" end="2">
							<button class="btn-search-blue" data-word="${rank.clientName}">${rank.clientName}</button>
						</c:forEach>
					</div>
				</c:if>
			</div>
		<script>
			$(".btn-search-blue").on("click", function() {
				let data = $(this).data("word");
				console.log(data)
				$("#searchWord").val(data)
				$("#searchForm").submit()
			})
		</script>
		</div>
		<div class="main-outline-box-img m-right-10">
			<img alt="" src="<%=request.getContextPath()%>/resources/images/company.png">
		</div>
	</div>
</div>

<section class="section-wrap">
		<div class="flex-end m-bottom-10">
			<input type="button" class="btn btn-gray" value="목록" onclick="location.href='${cPath}/outs/company/companyList.do'">
		</div>
	<div class="writing-view-wrap">
		<div class="writing-view">
			<div class="writing-title">
				<span>${compony.clientName}</span>
			</div>
			<div class="writing-info-wrap">
				<div>평점 : ${compony.reviewScore}</div>
			</div>
		</div>
		<div class="writing-content">
			<table class="table company-table">
				<tr>
					<th scope="col" style="width:300px;">대표자 </th>
					<td>${compony.clientCeo}</td>
				</tr>
				<tr>
					<th scope="col">전화번호</th>
					<td>${compony.memTel} </td>
				</tr>
				<tr>
					<th scope="col">이메일</th>
					<td>${compony.memMail} </td>
				</tr>
				<tr>
					<th scope="col">주소 </th>
					<td>${compony.clientAdd}</td>
				</tr>
				<tr>
					<th scope="col">홈페이지 </th>
					<td><a href="${compony.clientHomepage}" sty>${compony.clientHomepage}</a></td>
				</tr>
				<tr>
					<th scope="col">설립일 </th>
					<td>${compony.clientAnni}</td>
				</tr>
				<tr>
					<th scope="col">직원수 </th>
					<td>${compony.clientScale} 명</td>
				</tr>
				<tr>
					<th scope="col">매출 </th>
					<td>${compony.clientSalesValue} </td>
				</tr>
			</table>
		</div>
		<div class="recommend-wrap m-bottom-10" style="justify-content: space-between;">
			<div style="display: flex;">
				<div class="m-right-5">리뷰수</div>
				<div class="recommend-line m-right-5"></div>
				<div class="recommend">${compony.rowCnt}</div>
			</div>
		</div>
		
		<c:if test="${not empty reviewInsertAuth}">
			<form action="${cPath}/outs/company/reviewInsert.do" method="post">
				<div class="comment-wrap">
					<div class="input-group mb-3">
					  <input type="hidden" name="cliId" value="${compony.cliId}">
					  <input id="reviewContent" type="text" name="reviewContent" class="form-control" required>
					  <select name="reviewScore" style="border: 1px solid #a9acaf; color: #0476d1;">
					  	<option value="5">★★★★★</option>
					  	<option value="4">★★★★☆</option>
					  	<option value="3">★★★☆☆</option>
					  	<option value="2">★★☆☆☆</option>
					  	<option value="1">★☆☆☆☆</option>
					  </select>
					  <div class="input-group-append">
					    <input class="btn btn-gray" type="submit" value="등록" id="insertBtn">
					  </div>
					</div>
				</div>
			</form>
		</c:if>
		
		<c:if test="${not empty reviewUpDelAuth}">
			<div id="updateReDiv" style="display:none;">
				<form action="${cPath}/outs/company/reviewUpdate.do" method="post">
					<div class="comment-wrap">
						<div id="updateParam" class="input-group mb-3">
						  <input type="hidden" name="revNo">
						  <input type="hidden" name="writer">
						  <input type="hidden" name="cliId" value="${compony.cliId}">
						  <input type="text" name="reviewContent" class="form-control" required>
						  <select name="reviewScore" style="color: #0476d1;">
						  	<option value="5">★★★★★</option>
						  	<option value="4">★★★★☆</option>
						  	<option value="3">★★★☆☆</option>
						  	<option value="2">★★☆☆☆</option>
						  	<option value="1">★☆☆☆☆</option>
						  </select>
						  <div class="input-group-append">
						    <input class="btn btn-gray" type="submit" value="등록" id="insertBtn">
						  </div>
						</div>
					</div>
				</form>
			</div>
		</c:if>
		
		<table class="table table-sm">
			<thead class="thead-light">
				<tr class="tr-gray">
					<th scope="col" class="text-center" style="width:80px;">No</th>
					<th scope="col" class="text-center">리뷰</th>
					<th scope="col" style="width:110px;"></th>
					<th scope="col" class="text-center" style="width:110px;">평점</th>
					<th scope="col" class="text-center" style="width:150px;">작성일</th>
				</tr>
			</thead>
			<tbody id="reviewBody"></tbody>
		</table>
<!-- 		<div class="flex-center" id="pagingArea"></div> -->

<!-- 모집중인 프로젝트 -->
<c:if test="${not empty boardList.dataList}">
	<div class="company-list-txt m-top-20">
		<span style="display: block; margin-bottom: 10px;">${compony.clientName}의 공고중인 프로젝트 ></span>
		<div class="company-search-wrap">
			<c:forEach var="board" items="${boardList.dataList}">
				<c:url value="/outs/board/projectboard/projectView.do" var="selectURL">
					<c:param name="what" value="${board.boNum}"></c:param>
				</c:url>
	
						<div class="company-search-list2 linkBtn" data-gopage="${selectURL}">
						    <span class="company-span-title">${board.projectName}</span>
						    <span class="company-span-txt">직무 : ${board.projectJob}</span>
			 			<c:choose>
							<c:when test="${not empty board.limitModifyDate}">
								<span class="company-span-txt">공고기한 : ${board.permissionDate} ~ ${board.limitModifyDate}</span>
							</c:when>
							<c:otherwise>
								<span class="company-span-txt">공고기한 : ${board.permissionDate} ~ ${board.limitDate}</span>
							</c:otherwise>
						</c:choose>
						 <c:choose>
					      	  <c:when test="${empty board.projectTechValue }">
						      	<div>#${board.projectTech }</div>
						      </c:when>
						      <c:otherwise>
					      		<div class="flex-start m-top-10" style="flex-wrap: wrap;">
							      	<c:forEach items="${board.projectTechValue }" var="tech" begin="0" end="2">
							      		<span class="company-span-txt"># ${tech}</span>
							      	</c:forEach>
					      		</div>
						      </c:otherwise>
					      </c:choose>
						 </div>
		
			</c:forEach>
	</div>
</c:if>
		</div>
</div>
</section>


<form id="reviewForm" action="${cPath }/outs/company/reviewList.do">
	<input type="hidden" name="cliId" value="${compony.cliId}">
	<input type="hidden" name="rePage">
</form>

<script>
//상단 검색
let searchForm = $("#searchForm").paging();

//리뷰 목록
let reviewBody = $("#reviewBody");
let pageTag = $("[name=rePage]");
let pagingArea = $("#pagingArea");

let reviewForm = $("#reviewForm").paging({
	pagingArea:"#pagingArea",
	pageKey:"rePage",
	pageName:"[name='rePage']"
}).ajaxForm({
	dataType:"json",
	success :function(resp){
		reviewBody.empty();
		pagingArea.empty();
		pageTag.val("");
		
		let reList = resp.dataList;	 //서비스에서 저장
		let pagingHTML = resp.pagingHTML;
		let trTags = [];
		let trT;
		
		if(reList){
			$.each(reList, function (idx, review) {
					let memId = "${authMember.memId}";
					let writer = review.memWrite;
					let revNo = review.revNo;
					let reviewContent = review.reviewContent;
					
					if(memId == writer){
						trT = $("<tr>").append(
								$("<td>").html(review.rnum).attr({
									class:"text-center"
								}),
								$("<td>").html(reviewContent),
								$("<td>").append(
										$("<input>").attr({
											type:"button",
											value:"수정",
											class:"btn btn-gray btn-sm m-right-10 reviewBtn",
											onclick:"updateReview(" + revNo + ", " + "'" + reviewContent + "'" + "," +"'" + writer + "'" + ")"
										}),
										$("<input>").attr({
											type:"button",
											value:"삭제",
											class:"btn btn-gray btn-sm reviewBtn",
											onclick:"deleteReview(" + revNo + "," +"'" + writer + "'" + ")"
										})
								),
								$("<td>").html(review.reviewScoreValue).attr({
									class:"text-center"
								}),
								$("<td>").html(review.writeDate).attr({
									class:"text-center"
								})
							);	
					}else{
						trT = $("<tr>").append(
								$("<td>").html(review.rnum).attr({
									class:"text-center"
								}),
								$("<td>").html(reviewContent),
								$("<td>"),
								$("<td>").html(review.reviewScoreValue).attr({
									class:"text-center"
								}),
								$("<td>").html(review.writeDate).attr({
									class:"text-center"
								})
							);	
					}
				trTags.push(trT);
			});
		}else{
			let trT = $("<tr>").html(
				$("<td>").attr({
					colspan:"5"
				}).html("작성된 리뷰가 없습니다")
			);
			trTags.push(trT);
		}
		reviewBody.append(trTags);
		pagingArea.html(pagingHTML);
	}
}).submit();


//리뷰 수정
let updateReviewBody = $("#updateReviewBody");
let updateReDiv = $("#updateReDiv");
let updateParam = $("#updateParam");
 
function updateReview(revNo, reviewContent, writer) {
	updateParam.find(":input[name='revNo']").val(revNo)
	updateParam.find(":input[name='reviewContent']").val(reviewContent)
	updateParam.find(":input[name='writer']").val(writer)
	updateReDiv.css("display", "block");
	$(".reviewBtn").css("display", "none");
};

//리뷰 삭제
function deleteReview(revNo, writer) {
	result = confirm('리뷰를 삭제할까요?')
	if(result){
		$("#deleteRev").val(revNo)
		$("#deleteWriter").val(writer)
		$("#deleteReForm").submit();
	}else{
		return false;
	}
};

</script>

<form id="deleteReForm" action="${cPath}/outs/company/reviewDelete.do" method="post">
	<input id="deleteRev" type="hidden" name="revNo">
	<input id="deleteWriter" type="hidden" name="writer">
	<input type="hidden" name="cliId" value="${compony.cliId}">
</form>
