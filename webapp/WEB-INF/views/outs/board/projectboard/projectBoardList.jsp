<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/projectList.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/project/projectList.js"></script>
<script type="text/javascript">
	function onClickPr(boNum) {
		location.href = '<%=request.getContextPath()%>/outs/board/projectboard/projectView.do?what=' + boNum;
	};
</script>
<script src="${cPath}/resources/js/project/proBoardAutoComplete.js"></script>
<div>
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
		<div class="flex-end">
			<c:if test="${authMember.memRole eq 'CLIENT'}"> 
				<input type="button" value="글작성" class="linkBtn btn btn-gray" 
				data-gopage="${cPath }/outs/board/projectboard/projectInsert.do"/> 
			</c:if> 
		</div>
			<div class="filter-wrap">
		<div class="filter1-box flex-end">
				<div class="box1-wrap">
					기술스택
				</div>
			<div class="filter-content-wrap">
				<input style="width: 200px;" id="skill" type="text" class="form-control" placeholder="기술스택" aria-label="Username" aria-describedby="basic-addon1">
			</div>
			</div>
		</div>	
		<div class="filter-wrap">
			<div class="filter1-box">
				<div class="box1-wrap">
					분야
				</div>
				<div class="filter-content-wrap"> <!-- projectJob -->
					<button class="filter-btn" data-clicked='false' value="projectJob" data-word="인공지능/머신러닝">인공지능/머신러닝</button>
					<button class="filter-btn" data-clicked='false' value="projectJob" data-word="프론트엔드">프론트엔드</button>
					<button class="filter-btn" data-clicked='false' value="projectJob" data-word="게임클라이언트">게임클라이언트</button>
					<button class="filter-btn" data-clicked='false' value="projectJob" data-word="서버/백엔드">서버/백엔드</button>
					<button class="filter-btn" data-clicked='false' value="projectJob" data-word="데이터 엔지니어">데이터 엔지니어</button>
					<button class="filter-btn" data-clicked='false' value="projectJob" data-word="보안">보안</button>
					<button class="filter-btn" data-clicked='false' value="projectJob" data-word="SW/솔루션">SW/솔루션</button>
					<button class="filter-btn" data-clicked='false' value="projectJob" data-word="앱개발">앱개발</button>
				</div>
			</div>
			<div class="filter1-box">
				<div class="box1-wrap">
					팀규모
				</div>
				<div class="filter-content-wrap"> <!-- projectScale -->
					<button class="filter-btn" data-clicked='false' value="projectScale" data-word="소규모">소규모</button>
					<button class="filter-btn" data-clicked='false' value="projectScale" data-word="중규모">중규모</button>
					<button class="filter-btn" data-clicked='false' value="projectScale" data-word="대규모">대규모</button>
				</div>
			</div>
			<div class="filter1-box">
				<div class="box1-wrap">
					상주 여부
				</div>
				<div class="filter-content-wrap"> <!-- officeNy -->
					<button class="filter-btn3 office" data-clicked='false' value="officeNy" data-word="Y">상주근무</button>
					<button class="filter-btn3 office" data-clicked='false' value="officeNy" data-word="N">재택근무</button>
				</div>
			</div>
			<c:if test="${authMember.memRole eq 'CLIENT'}">
				<div class="filter1-box">
					<div class="box1-wrap">
						범위
					</div>
					<div class="filter-content-wrap"> <!-- officeNy -->
						<button class="filter-btn4 req" data-clicked='false' value="memId" data-word="${authMember.memId}">자사</button>
						<button class="filter-btn4 req" data-clicked='false' value="memId" data-word="ALL">전체</button>
					</div>
				</div>
			</c:if>
			<c:if test="${authMember.memRole eq 'DEV'}">
				<div class="filter1-box">
					<div class="box1-wrap">
						지원 여부
					</div>
					<div class="filter-content-wrap"> <!-- officeNy -->
						<button class="filter-btn4 req" data-clicked='false' value="appId" data-word="${authMember.memId}">지원중</button>
						<button class="filter-btn4 req" data-clicked='false' value="appId" data-word="ALL">미지원</button>
					</div>
				</div>
			</c:if>	
			<div class="filter1-box">
				<div class="box1-wrap">
					선택한 필터
				</div>
				<div id="checkedFilter" class="filter-content-wrap">
				</div>
			</div>
		</div>
		
	
<c:if test="${not empty traceList}">
	<div class="search-wrap quickmenu">
	<span class="span-title">최근 조회한 공고</span>
		<c:forEach var="trace" items="${traceList }">
			<c:url value="/outs/board/projectboard/projectView.do" var="traceURL">
				<c:param name="what" value="${trace.boNum}"></c:param>
			</c:url>
			
			<div class="search-list2 linkBtn" data-gopage="${traceURL}" style="cursor:pointer; max-width: 150px;">
			  
			    <span class="span-txt">${trace.projectName}</span>
			    <span class="span-txt">모집인원 : ${trace.projectRecruit}명</span>
			    <span class="span-txt">직무 : ${trace.projectJob}</span>
				 <c:choose>
			      	  <c:when test="${empty trace.projectTechValue }">
				      	<span class="span-txt">#${trace.projectTech }</span>
				      </c:when>
				      <c:otherwise>
				      	<span class="span-txt">
					      	<c:forEach items="${trace.projectTechValue }" var="tech" begin="0" end="2">
					      		#${tech }
					      	</c:forEach>
				      	</span>
				      </c:otherwise>
			      </c:choose>
			</div>
		</c:forEach>
	</div>
</c:if>
	
	<div class="project-list-wrap">
		<div id="listBody1" class="project-list-box-wrap">
		</div> 
		<div id="listBody2" class="project-list-box-wrap">
		</div> 
		<div id="listBody3" class="project-list-box-wrap">
		</div> 
		<div id="listBody4" class="project-list-box-wrap">
		</div> 
	</div>
		
	<div class="paging-wrap">
		<div id="pagingArea"></div>	
	</div>
	

</section>

<form id="searchForm">
	<input type="hidden" name="projectJob" value="${searchVO.projectJob}"/>
	<input type="hidden" name="projectScale" value="${searchVO.projectScale}"/>
	<input type="hidden" name="officeNy" value="${searchVO.officeNy}"/>
	<input type="hidden" name="memId" value="${searchVO.memId}" />
	<input type="hidden" name="appId" value="${searchVO.appId}" />
	<input type="hidden" name="projectTech" value="${searchVO.projectTech}" />
   <input type="hidden" name="page" value="${searchVO.page}" /> 
</form>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript">	

$(document).ready(function(){ 
	
	var currentPosition = parseInt($(".quickmenu").css("top")); 
	
	$(window).scroll(function() {
		var position = $(window).scrollTop(); 
		$(".quickmenu").stop().animate({
			"top":position+currentPosition+"px"}, 1000); 
	}); 
});

	let listBody1 = $("#listBody1")
	let listBody2 = $("#listBody2")
	let listBody3 = $("#listBody3")
	let listBody4 = $("#listBody4")
	let pagingArea = $("#pagingArea");
	
	let searchForm = $("#searchForm").paging().ajaxForm({
		dataType : "json",
		success : function(resp) {
				
			listBody1.empty()
			listBody2.empty()
			listBody3.empty()
			listBody4.empty()
			pagingArea.empty()
			
			let dataList = resp.dataList
			
			if(dataList.length > 0) {				
				for(let i = 0; i < 3; i++) {
					let stackList = [] 
					if(!dataList[i]) {
						break;
					}
					
					if(dataList[i].projectTech != null) {
						stackList = dataList[i].projectTech.split(",")						
					}
					
					let div = $("<div>").attr({"class" : "linkBtn project-list-box",
												"data-gopage" : "${cPath}/outs/board/projectboard/projectView.do?what=" + dataList[i].boNum
												})
								.append(
									$("<span>").text(dataList[i].clientName),
									$("<div>").attr("class", "protitle").text(dataList[i].projectName),
// 									$("<span>").text("예상단가 : " + dataList[i].projectPriceValue),
									$("<span>").text("모집인원 : " + dataList[i].projectRecruit + "명"),
									$("<span>").attr("class", "project-list-date").text("공고기한 : "+ dataList[i].permissionDate + "~" + dataList[i].limitDate)
								);
					let liTags = [];
					let tags = '';
					$.each(stackList, function(idx, stack) {
						tags += '<div class="tech-box-small">';
						tags += 	'<div class="tech-img-small">';
						tags += 		'<img src="<%=request.getContextPath()%>/resources/images/skill/' + stack + '.png">';
						tags += 	'</div>';
						tags +=		'<span class="tech-txt">' + stack + '</span>';
						tags += '</div>';
					});
					let ulTag = $("<div>").attr({"class": "flex-start m-top-10", "style": "flex-wrap: wrap"})
										  .append(tags);
					div.append(ulTag);
					listBody1.append(div);
				};
				for(let i = 3; i < 6; i++) {
					let stackList = [] 
					if(!dataList[i]) {
						break;
					}
					
					if(dataList[i].projectTech != null) {
						stackList = dataList[i].projectTech.split(",")						
					}
					let div = $("<div>").attr({"class" : "linkBtn project-list-box",
												"data-gopage" : "${cPath}/outs/board/projectboard/projectView.do?what=" + dataList[i].boNum
											})
								.append(
										$("<span>").text(dataList[i].clientName),
										$("<div>").attr("class", "protitle").text(dataList[i].projectName),
// 										$("<span>").text("예상단가 : " + dataList[i].projectPriceValue),
										$("<span>").text("모집인원 : " + dataList[i].projectRecruit + "명"),
										$("<span>").attr("class", "project-list-date").text("공고기한 : "+ dataList[i].permissionDate + "~" + dataList[i].limitDate)
								);
					let tags = '';
					let liTags = [];
					$.each(stackList, function(idx, stack) {
						tags += '<div class="tech-box-small">';
						tags += 	'<div class="tech-img-small">';
						tags += 		'<img src="<%=request.getContextPath()%>/resources/images/skill/' + stack + '.png">';
						tags += 	'</div>';
						tags +=		'<span class="tech-txt">' + stack + '</span>';
						tags += '</div>';
					});
					let ulTag = $("<div>").attr({"class": "flex-start m-top-10", "style": "flex-wrap: wrap"})
										  .append(tags);
					div.append(ulTag);
					listBody2.append(div);
				};
				for(let i = 6; i < 9; i++) {
					let stackList = [] 
					if(!dataList[i]) {
						break;
					}
					
					if(dataList[i].projectTech != null) {
						stackList = dataList[i].projectTech.split(",")						
					}
					
					let div = $("<div>").attr({"class" : "linkBtn project-list-box",
												"data-gopage" : "${cPath}/outs/board/projectboard/projectView.do?what=" + dataList[i].boNum
												})
								.append(
										$("<span>").text(dataList[i].clientName),
										$("<div>").attr("class", "protitle").text(dataList[i].projectName),
// 										$("<span>").text("예상단가 : " + dataList[i].projectPriceValue),
										$("<span>").text("모집인원 : " + dataList[i].projectRecruit + "명"),
										$("<span>").attr("class", "project-list-date").text("공고기한 : "+ dataList[i].permissionDate + "~" + dataList[i].limitDate)
								);
					let tags = '';
					let liTags = [];
					$.each(stackList, function(idx, stack) {
						tags += '<div class="tech-box-small">';
						tags += 	'<div class="tech-img-small">';
						tags += 		'<img src="<%=request.getContextPath()%>/resources/images/skill/' + stack + '.png">';
						tags += 	'</div>';
						tags +=		'<span class="tech-txt">' + stack + '</span>';
						tags += '</div>';
					});
					let ulTag = $("<div>").attr({"class": "flex-start m-top-10", "style": "flex-wrap: wrap"})
										  .append(tags);
					div.append(ulTag);
					listBody3.append(div);
				}
				
				for(let i = 9; i < 12; i++) {
					let stackList = [] 
					if(!dataList[i]) {
						break;
					}
					
					if(dataList[i].projectTech != null) {
						stackList = dataList[i].projectTech.split(",")						
					}
					
					let div = $("<div>").attr({"class" : "linkBtn project-list-box",
												"data-gopage" : "${cPath}/outs/board/projectboard/projectView.do?what=" + dataList[i].boNum
												})
								.append(
										$("<span>").text(dataList[i].clientName),
										$("<div>").attr("class", "protitle").text(dataList[i].projectName),
// 										$("<span>").text("예상단가 : " + dataList[i].projectPriceValue),
										$("<span>").text("모집인원 : " + dataList[i].projectRecruit + "명"),
										$("<span>").attr("class", "project-list-date").text("공고기한 : "+ dataList[i].permissionDate + "~" + dataList[i].limitDate)
								);
					let tags = '';
					let liTags = [];
					$.each(stackList, function(idx, stack) {
						tags += '<div class="tech-box-small">';
						tags += 	'<div class="tech-img-small">';
						tags += 		'<img src="<%=request.getContextPath()%>/resources/images/skill/' + stack + '.png">';
						tags += 	'</div>';
						tags +=		'<span class="tech-txt">' + stack + '</span>';
						tags += '</div>';
					});
					let ulTag = $("<div>").attr({"class": "flex-start m-top-10", "style": "flex-wrap: wrap"})
										  .append(tags);
					div.append(ulTag);
					listBody4.append(div);
				}
				
				pagingArea.html(resp.pagingHTML)
				
			} else {
				let div = $("<div>").attr("class", "project-list-box").append($("<span>").text("모집중인 프로젝트 공고가 없습니다."))
				listBody1.append(div)
			}
		},
		error : function(x) {
			
		}
	}).submit();
	
	
	$(function() {
		if("${searchVO.projectJob}") {
			let splitJob = "${searchVO.projectJob}".split(",");
			$.each(splitJob, function(idx, projectJob) {
				let button = $("[data-word='"+projectJob+"']")
// 				button.trigger("click")
				initClick(button)
			})
			
		}
		if("${searchVO.projectScale}") {
			let splitScale = "${searchVO.projectScale}".split(",");
			$.each(splitScale, function(idx, projectScale) {
				let button = $("[data-word='"+projectScale+"']")
// 				button.trigger("click")
				initClick(button)
			})
			
		}
		if("${searchVO.officeNy}") {
			let splitOffice = "${searchVO.officeNy}".split(",");
			$.each(splitOffice, function(idx, officeNy) {
				let button = $("[data-word='"+officeNy+"']")
// 				button.trigger("click")
				initClick(button)
			})			
		}
		
		if("${searchVO.memId}") {
			let memId = "${searchVO.memId}"
			let button = $("[data-word='"+memId+"']")
			console.log(button + "전체버튼")
// 			button.trigger("click") 
			initUserClick(button)
		} 
		
		if("${searchVO.appId}") {
			let appId = "${searchVO.appId}"
			let button = $("[data-word='"+appId+"']")
// 			button.trigger("click") 
			initUserClick(button)
		} 
		if("${searchVO.projectTech}") {
			initStackClick("${searchVO.projectTech}")
		}
		
	})
</script>





