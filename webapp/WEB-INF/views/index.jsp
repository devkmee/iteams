
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
	
<style>
	.project-list-box {
		float: left;
		cursor: pointer;
	}
</style>
<div class="main-wrap">
	<div class="main-inner-box">
		<div class="main-inner-txt">
			<span class="main-txt"> "함께 일할 사람" <span class="main-txt-lighter">의 소중함을<br> 우리는 알고 있습니다.
			</span>
			</span>
		</div>
	</div>
	<div class="main-outline-box">
		<div class="searchbar-wrap">
			<form id="totalSearchForm" action="${cPath }/outs/totalList.do">
			<div class="input-group search-btn">
				<input id="search" type="text" name="what" class="form-control" placeholder="검색어를 입력하세요"
					aria-label="Recipient's username" aria-describedby="basic-addon2">
				<div class="input-group-append">
					<span style="cursor: pointer;" class="input-group-text" id="basic-addon2">검색</span>
				</div>
			</div>
			</form>
			<script>
				$("#basic-addon2").on("click", function() {
					$("#totalSearchForm").submit()
				})
			</script>
			<div class="search-word-wrap">
				<span class="search-word-txt">추천 검색어</span>
				<c:if test="${not empty devInfo}">
					<div class="btn-wrap m-left-10">
						<c:forEach items="${devInfo.devTech}" var="tech">
							<button class="btn-search-blue" data-word="${tech}">#${tech}</button>
						</c:forEach>
						<button class="btn-search-blue" data-word="${devInfo.devJob}">#${devInfo.devJob}</button>
					</div>					
				</c:if>
				<c:if test="${empty devInfo}">
					<div class="btn-wrap m-left-10">
						<button class="btn-search-blue" data-word="프론트엔드">#프론트엔드</button>
						<button class="btn-search-blue" data-word="java">#JAVA</button>
						<button class="btn-search-blue" data-word="재택근무">#재택근무</button>
					</div>
				</c:if>
			<script>
				$(".btn-search-blue").on("click", function() {
					let data = $(this).data("word");
					console.log(data)
					$("#search").val(data)
					$("#totalSearchForm").submit()
				})
			</script>
			</div>
		</div>
		<div class="main-outline-box-img m-right-10">
			<img alt="" src="<%=request.getContextPath()%>/resources/images/main-img.png">
		</div>
	</div>
</div>
<section class="section-wrap">
	<div class="category-wrap">
		<div class="category-box" data-job="">
			<img src="<%=request.getContextPath()%>/resources/images/all.png">
			<span>전체</span>
		</div>
		<div class="category-box" data-job="인공지능/머신러닝">
			<img src="<%=request.getContextPath()%>/resources/images/cate1.png">
			<span>인공지능/머신러닝</span>
		</div>
		<div class="category-box" data-job="프론트엔드">
			<img src="<%=request.getContextPath()%>/resources/images/cate2.png">
			<span>프론트엔드</span>
		</div>
		<div class="category-box" data-job="게임클라이언트">
			<img src="<%=request.getContextPath()%>/resources/images/cate3.png">
			<span>게임클라이언트</span>
		</div>
		<div class="category-box" data-job="서버/백엔드">
			<img src="<%=request.getContextPath()%>/resources/images/cate4.png">
			<span>서버/백엔드</span>
		</div>
		<div class="category-box" data-job="데이터 엔지니어">
			<img src="<%=request.getContextPath()%>/resources/images/cate5.png">
			<span>데이터 엔지니어(DBA)</span>
		</div>
		<div class="category-box" data-job="보안">
			<img src="<%=request.getContextPath()%>/resources/images/cate6.png">
			<span>보안</span>
		</div>
		<div class="category-box" data-job="SW/솔루션">
			<img src="<%=request.getContextPath()%>/resources/images/cate7.png">
			<span>SW/솔루션</span>
		</div>
		<div class="category-box" data-job="앱개발">
			<img src="<%=request.getContextPath()%>/resources/images/cate8.png">
			<span>앱개발</span>
		</div>
	</div>
	<c:if test="${authMember.memRole eq 'DEV'}">
		<div class="project-list-title m-bottom-20 m-top-30">
				<span>회원님께 <span class="point-txt">딱 맞는</span> 프로젝트 공고에요!</span>
		</div>
		<div id="recBody" class="project-list-box-wrap">

		</div>
		<script>
			
			let body = $("#recBody");
		
			$.ajax({
				url : "${cPath}/index.do",
				method : "post",
				dataType : "json",
				success : function(resp) {
					
					body.empty()
					let stackList = []
					
					$.each(resp, function(idx, project) {
						
						if(project.projectTech != null) {
							stackList = project.projectTech.split(",")						
						}
						
						let div = $("<div>").attr({"class" : "linkBtn project-list-box",
							"data-gopage" : "${cPath}/outs/board/projectboard/projectView.do?what=" + project.boNum
							})
								.append(
									$("<span>").text("기업명 : " + project.clientName),
									$("<div>").attr("class", "protitle").text(project.projectName),
// 									$("<span>").text("예상단가 : " + project.projectPriceValue),
									$("<span>").text("모집인원 : " + project.projectRecruit + "명"),
									$("<span>").attr("class", "project-list-date").text("공고기한 : " + project.permissionDate + " ~ " + project.limitDate)
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
						
						body.append(div)
					})
				},
				error : function(xhr, errorResp, error) {
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			})
		</script>
	</c:if>
	<form action="${cPath }/index.do" id="searchForm">
		<input type="hidden" name="projectJob" />
	</form>
	<div class="project-list-wrap">
		<div class="project-list-title m-bottom-20 m-top-30">
			<span>모집중인 프로젝트 ></span>
		</div>

		<div id="listBody1" class="project-list-box-wrap">

		</div>
		<div id="listBody2" class="project-list-box-wrap">
			
		</div>
		<div id="listBody3" class="project-list-box-wrap">
		
		</div>
	</div>
</section>

<section>
	<div class="ranking-wrap">
		<div class="project-list-title m-bottom-20 m-top-30">
			<span>iteams 활동가 순위 ></span>
			<p class="ranking-txt">분야별 가장 많은 활동을 한 전문가 랭킹 입니다.</p>
		</div>
		<div class="ranking-list-box-wrap  m-bottom-20">
			<div>
				<div class="ranking-list-box">
					<div class="ranking-img-wrap">
						<img src="<%=request.getContextPath()%>/resources/images/rank-blue.png">
					</div>
					<div class="ranking-info-wrap">
						<h5>프로젝트 건수 순위</h5>
							<div class="ranking-list">
							<table class="table-ranking-project">
								<c:forEach var="project" items="${projectRank}" varStatus="status">
									<tr>
										<td>${status.count }위</td>
										<td>${project.completeCount }건</td>
										<td>${project.devId }님</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			<div>
				<div class="ranking-list-box">
					<div class="ranking-img-wrap">
						<img src="<%=request.getContextPath()%>/resources/images/rank-blue.png">
					</div>
					<div class="ranking-info-wrap">
						<h5>커뮤니티 활동 순위</h5>
							<div class="ranking-list">
							<table class="table-ranking-project">
								<c:forEach var="active" items="${activeRank}" varStatus="status">
									<tr>
										<td>${status.count }위</td>
										<td>${active.activeCount }건</td>
										<td>${active.memId }님</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			

			<div>
				<div class="ranking-list-box2">
					<div class="ranking-img-wrap">
						<img src="<%=request.getContextPath()%>/resources/images/rank-gray.png">
					</div>
					<div class="ranking-info-wrap">
						<h5>클라이언트 평점 순위</h5>
							<div class="ranking-list">
							<table class="table-ranking-project">
								<c:forEach var="review" items="${reviewRank}" varStatus="status">
									<tr>
										<td>${status.count }위</td>
										<td>${review.reviewCount }점</td>
										<td>${review.cliId }님</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>


<script>
	
	$(".category-box").on("click", function() {
		let what = $(this).data("job")
		$("[name='projectJob']").val(what)
		searchForm.submit()
	})
	
	let listBody1 = $("#listBody1")
	let listBody2 = $("#listBody2")
	let listBody3 = $("#listBody3")
	
	let searchForm = $("#searchForm").paging().ajaxForm({
		dataType : "json",
		success : function(resp) {
			
			listBody1.empty()
			listBody2.empty()
			listBody3.empty()
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
				
			} else {
				let div = $("<div>").attr("class", "project-list-box").append($("<span>").text("모집중인 프로젝트 공고가 없습니다."))
				listBody1.append(div)
			}
		},
		error : function(x) {
			
		}
		
	}).submit();
</script>



<!-- 고객센터 1:1채팅 -->
<script>
  (function() {
    var w = window;
    if (w.ChannelIO) {
      return (window.console.error || window.console.log || function(){})('ChannelIO script included twice.');
    }
    var ch = function() {
      ch.c(arguments);
    };
    ch.q = [];
    ch.c = function(args) {
      ch.q.push(args);
    };
    w.ChannelIO = ch;
    function l() {
      if (w.ChannelIOInitialized) {
        return;
      }
      w.ChannelIOInitialized = true;
      var s = document.createElement('script');
      s.type = 'text/javascript';
      s.async = true;
      s.src = 'https://cdn.channel.io/plugin/ch-plugin-web.js';
      s.charset = 'UTF-8';
      var x = document.getElementsByTagName('script')[0];
      x.parentNode.insertBefore(s, x);
    }
    if (document.readyState === 'complete') {
      l();
    } else if (window.attachEvent) {
      window.attachEvent('onload', l);
    } else {
      window.addEventListener('DOMContentLoaded', l, false);
      window.addEventListener('load', l, false);
    }
  })();
  ChannelIO('boot', {
    "pluginKey": "be250bf4-dc81-4bcd-8a87-6d6ceda85120"
  });
</script>
<!-- 고객센터 1:1채팅  -->

<!-- 자동완성 -->
<script type="text/javascript">
$('#search').on('keydown', function(event) {
	if (event.keyCode === $.ui.keyCode.TAB
			&& $(this).autocomplete('instance').menu.active) {
		event.preventDefault();
	}
}).autocomplete({ // autocomplete 구현 시작부
	source : function(req, response) {
	$.ajax({
		type : 'get',
		dataType : 'json',
		data : {
			keyword : req.term
		},
		url : CONTEXT_PATH + '/outs/login/findTechCodeByKeyword.do',
		success : function(res) {
			response(res);
		}
	})
	},
	focus : function() {
		return false;
	},
	selectFirst: true,
	// 최소 글자 수
	minLenght: 1,
	// true로 설정 시 메뉴가 표시 될 때, 첫 번째 항목에 자동으로 초점이 맞춰짐
	autoFocus : true,
	// 위젯 요소에 추가 할 클래스를 지정
	classes : { 
	'ui-autocomplete' : 'highlight'
	},
	// 입력창에 글자가 써지고 나서 autocomplete 이벤트 발생될 떄 까지 지연 시간(ms)
	delay : 500, 
	// 해당 값 true 시, 자동완성 기능 꺼짐
	disable : false, 
	// UI 변경
	}).autocomplete('instance')._renderItem = function(ul, item) {
		// 익스에서 쓰려면 아래 문법으로 해야함
		// var str = '<div><img src=' + CONTEXT_PATH + item.imgPath + ' />' + item.label + '</div>';
		const str = '<div><img class="skill-img" src=data:image/png;base64,' + item.imgPath + '>' + item.label + '</div>';
		return $('<li>').append(str).appendTo(ul);
	};
</script>