<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/company.css">
<div class="main-wrap">
	<div class="company-inner-box">
		<div class="company-inner-txt">
			<span class="main-txt"> 기업정보<br> <span class="company-txt-lighter">나에게 맞는 회사를 찾는 일, 생각보다 쉽지 않죠?
			<br>아이팀즈가 좋은 기업 발굴부터 자료 분석까지 함께 연구합니다.
			</span>
			</span>
		</div>
	</div>
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
		</div>
		<script>
			$(".btn-search-blue").on("click", function() {
				let data = $(this).data("word");
				console.log(data)
				$("#searchWord").val(data)
				$("#searchForm").submit()
			})
		</script>
		
		
		<div class="main-outline-box-img m-right-10">
			<img alt="" src="<%=request.getContextPath()%>/resources/images/company.png">
		</div>
	</div>
</div>
<section class="section-wrap">
	<div class="company-list-wrap">
		<div class="project-list-title m-bottom-20">
			<span>추천 기업 소개 ></span>
		</div>
		<div class="company-wrap">
			<div class="company-left">
				<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel"">
				  <ol class="carousel-indicators">
				    <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
				    <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
				    <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
				  </ol>
				  <div class="carousel-inner">
				  	<c:url value="/outs/company/companyView.do" var="URL_1">
						<c:param name="cliId" value="ddit"></c:param>
					</c:url>
				    <div class="carousel-item active linkBtn" data-gopage="${URL_1}" style="cursor: pointer;">
				      <img src="<%=request.getContextPath()%>/resources/images/company1.jpg" class="d-block w-100" alt="...">
				      <div class="carousel-caption d-none d-md-block">
				        <h5>(주)DDIT</h5>
				        <p>글로벌 수준 CDO 전문 연구개발 기업</p>
				      </div>
				    </div>
				  	<c:url value="/outs/company/companyView.do" var="URL_2">
						<c:param name="cliId" value="jinu"></c:param>
					</c:url>				    
				    <div class="carousel-item linkBtn" data-gopage="${URL_2}" style="cursor: pointer;">
				      <img src="<%=request.getContextPath()%>/resources/images/company2.jpg" class="d-block w-100" alt="...">
				      <div class="carousel-caption d-none d-md-block">
				       	<h5>위메이드</h5>
				        <p>블록체인 기반 플랫폼 전문 기업</p>
				      </div>
				    </div>
				  	<c:url value="/outs/company/companyView.do" var="URL_3">
						<c:param name="cliId" value="ayoung"></c:param>
					</c:url>				    
				    <div class="carousel-item linkBtn" data-gopage="${URL_3}" style="cursor: pointer;">
				      <img src="<%=request.getContextPath()%>/resources/images/company3.jpg" class="d-block w-100" alt="...">
				      <div class="carousel-caption d-none d-md-block">
				        <h5>(주)엽아이티</h5>
				        <p>글로벌 Top-tier 정보 보안 전문 기업</p>
				      </div>
				    </div>
				  </div>
				  <button class="carousel-control-prev" type="button" data-target="#carouselExampleCaptions" data-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="sr-only">Previous</span>
				  </button>
				  <button class="carousel-control-next" type="button" data-target="#carouselExampleCaptions" data-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="sr-only">Next</span>
				  </button>
				</div>
			</div>
			<div class="company-right">
				<div class="company-ranking-list">
						<div class="ranking-img-wrap">
							<img src="<%=request.getContextPath()%>/resources/images/rank-gray.png">
						</div>
						<div class="ranking-info-wrap">
							<h5>기업 평점 순위</h5>
								<div class="company-list">
								<table class="table-ranking-company">
									<c:set var="ranks" value="${rankList.dataList}"></c:set>
									<c:forEach items="${ranks}" var="rank" varStatus="status">
										<tr>
											<td>${status.count}위</td>
											<td>${rank.reviewCount}점</td>
											<td>${rank.clientName}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
				</div>
			</div>
		</div>
		<br>
		<div class="table-wrap m-top-30">
			<div class="employment-wrap">
			  	<c:set var="componyList" value="${data.dataList}"></c:set>
			  	<c:choose>
			  		<c:when test="${empty componyList}">
			  			<sapn>조건에 맞는 기업 정보가 없습니다</sapn>
			  		</c:when>
			  		<c:otherwise>
			  			<c:forEach items="${componyList}" var="compony" varStatus="state">
			  				<c:url value="/outs/company/companyView.do" var="selectURL">
								<c:param name="cliId" value="${compony.cliId}"></c:param>
							</c:url>
							<c:if test="${state.index mod 3 eq 0}">
								</div>
							</c:if>
							<c:if test="${state.index eq 0 || state.index mod 3 eq 0}">
	                 			<div class="project-list-box-wrap">
	                 		</c:if>
						  	<div data-gopage="${selectURL}" class="linkBtn card border-primary mb-3" style="width: 400px; cursor: pointer;">
							  <div class="card-header">평점 : ${compony.reviewScore}</div>
							  <div class="card-body">
							    <h5 class="">${compony.clientName}</h5>
							    <p>직원수 : ${compony.clientScale}명 | CEO :${compony.clientCeo}</p>
							    <p># ${compony.clientScaleValue}</p>
							  </div>
							</div>
						<c:if test="${status.last}">
	                 		</div>
	                 	</c:if>
					  	</c:forEach>
			  		</c:otherwise>
			  	</c:choose>
			  
		</div>
		<div class="paging-wrap">
			<div id="pagingArea">
				${data.pagingHTML}
			</div>
		</div>
	</div>
</section>

<form id="searchForm">
	<input type="hidden" name="searchWord" id="searchWord"/>
	<input type="hidden" name="page" />
</form>

<script>
let searchForm = $("#searchForm").paging();
</script>