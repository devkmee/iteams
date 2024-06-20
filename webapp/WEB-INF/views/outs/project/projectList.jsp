<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/projectList.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/project/projectList.js"></script>   
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
		<span>프로젝트 공고 리스트 ></span> 
	</div>
	<div class="filter-wrap">
		<div class="filter1-box">
			<div class="box1-wrap">
				분야
			</div>
			<div class="filter-content-wrap">
				<button class="filter-btn" data-clicked='false' value="ai">인공지능/머신러닝</button>
				<button class="filter-btn" data-clicked='false' value="frontend">프론트엔드</button>
				<button class="filter-btn" data-clicked='false' value="gameClient">게임클라이언트</button>
				<button class="filter-btn" data-clicked='false' value="backend">서버/백엔드</button>
				<button class="filter-btn" data-clicked='false' value="data">데이터 엔지니어</button>
				<button class="filter-btn" data-clicked='false' value="security">보안</button>
				<button class="filter-btn" data-clicked='false' value="sw">SW/솔루션</button>
				<button class="filter-btn" data-clicked='false' value="app">앱개발</button>
			</div>
		</div>
		<div class="filter1-box">
			<div class="box1-wrap">
				팀규모
			</div>
			<div class="filter-content-wrap">
				<button class="filter-btn" data-clicked='false' value="small">소규모</button>
				<button class="filter-btn" data-clicked='false' value="middle">중규모</button>
				<button class="filter-btn" data-clicked='false' value="large">대규모</button>
			</div>
		</div>
		<div class="filter1-box">
			<div class="box1-wrap">
				상주 여부
			</div>
			<div class="filter-content-wrap">
				<button class="filter-btn" data-clicked='false' value="office">상주근무</button>
				<button class="filter-btn" data-clicked='false' value="house">재택근무</button>
			</div>
		</div>
		<div class="filter1-box">
			<div class="box1-wrap">
				선택한 필터
			</div>
			<div id="checkedFilter" class="filter-content-wrap">
			</div>
		</div>
	</div>
	<div class="project-list-wrap">
		<div class="project-list-box-wrap">
			<div class="project-list-box">
				<span>(주)소프트랩스</span> <span>블록체인 기반 플랫폼 관련 프로젝트 개발</span> <span>블록체인(FNT
					거래소) 신기술분야에서 프로젝트에 기여합니다.</span> <span>모집인원: 4명</span> <span
					class="project-list-date">~21/12/30</span>
				<ul>
					<li>#javascript</li>
					<li>#vue.js</li>
					<li>#html5</li>
				</ul>
			</div>
			<div class="project-list-box">
				<span>(주)소프트랩스</span> <span>블록체인 기반 플랫폼 관련 프로젝트 개발</span> <span>블록체인(FNT
					거래소) 신기술분야에서 프로젝트에 기여합니다.</span> <span>모집인원: 4명</span> <span
					class="project-list-date">~21/12/30</span>
				<ul>
					<li>#javascript</li>
					<li>#vue.js</li>
					<li>#html5</li>
				</ul>
			</div>
			<div class="project-list-box">
				<span>(주)소프트랩스</span> <span>블록체인 기반 플랫폼 관련 프로젝트 개발</span> <span>블록체인(FNT
					거래소) 신기술분야에서 프로젝트에 기여합니다.</span> <span>모집인원: 4명</span> <span
					class="project-list-date">~21/12/30</span>
				<ul>
					<li>#javascript</li>
					<li>#vue.js</li>
					<li>#html5</li>
				</ul>
			</div>
		</div>
		<div class="project-list-box-wrap">
			<div class="project-list-box">
				<span>(주)소프트랩스</span> <span>블록체인 기반 플랫폼 관련 프로젝트 개발</span> <span>블록체인(FNT
					거래소) 신기술분야에서 프로젝트에 기여합니다.</span> <span>모집인원: 4명</span> <span
					class="project-list-date">~21/12/30</span>
				<ul>
					<li>#javascript</li>
					<li>#vue.js</li>
					<li>#html5</li>
				</ul>
			</div>
			<div class="project-list-box">
				<span>(주)소프트랩스</span> <span>블록체인 기반 플랫폼 관련 프로젝트 개발</span> <span>블록체인(FNT
					거래소) 신기술분야에서 프로젝트에 기여합니다.</span> <span>모집인원: 4명</span> <span
					class="project-list-date">~21/12/30</span>
				<ul>
					<li>#javascript</li>
					<li>#vue.js</li>
					<li>#html5</li>
				</ul>
			</div>
			<div class="project-list-box">
				<span>(주)소프트랩스</span> <span>블록체인 기반 플랫폼 관련 프로젝트 개발</span> <span>블록체인(FNT
					거래소) 신기술분야에서 프로젝트에 기여합니다.</span> <span>모집인원: 4명</span> <span
					class="project-list-date">~21/12/30</span>
				<ul>
					<li>#javascript</li>
					<li>#vue.js</li>
					<li>#html5</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="paging-wrap">
		 <div id="pagingArea"> ${base.pagingHTML } </div>	
		</div>
	</section>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript">	
$("[name='searchType']").val("${searchVO.searchType}");
$("[name='searchWord']").val("${searchVO.searchWord}");
let searchForm = $("#searchForm").paging();
</script>





