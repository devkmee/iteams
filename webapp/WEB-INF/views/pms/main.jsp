<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="<%=request.getContextPath() %>/resources/css/pmsMainDev.css" rel="stylesheet">
<link rel="stylesheet" href="${cPath}/resources/js/jquery-ui-1.13.0/jquery-ui.css">
<link rel="stylesheet" href="${cPath}/resources/js/jquery-ui-1.13.0/jquery-ui.theme.css">
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<style>
.pms-main-wrap {
	width: 1210px;
	margin: 0 auto;
}
.img-wrap {
    width: 50px;
    height: 50px;
    border-radius: 50% !important;
    border: 2px solid #fff;
    overflow: hidden;
}

.team-img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.team-wrap {
	display: flex;
    align-items: center;
    width: 220px;
}
 .box1 {
  	width: 570px;
  	height:500px; 
  	padding: 10px;
}
.box2 {
	width:300px; 
	height:500px;  
}

.box3 {
	width: 640px;
  	height:100%; 
}

.box3 > table {
	text-align: center;
}
  
.tg  {
	border-collapse:collapse;
	border-spacing:0;
	margin-top: 40px;
}
.tg td{
	border-color:black;
	border-style:solid;
	border-width:1px;
	font-family:Arial, sans-serif;
	font-size:14px;
	overflow:hidden;
	padding:10px 5px;
	word-break:normal;
}
.tg th{
	border-color:black;
	border-style:solid;
	border-width:1px;
	font-family:Arial, sans-serif;
	font-size:14px;
	font-weight:normal;
	overflow:hidden;
	padding:10px 5px;
	word-break:normal;
}
.tg .tg-0lax{
	text-align:center;
	vertical-align:top;
}

.profile-img-wrap {
	width: 70px;
	height: 70px;
	background-position: center center;
	background-repeat: no-repeat;
    border-radius: 50% !important;
    margin-left: 20px;
}


.progress-label {
    position:absolute;
    left:50%;
    top:8px;
    font-weight:bold;
    margin-left:-40px;
}



</style>

<%-- <c:forEach items="${workCnt}" var="cnt" /> --%>
<div class="pms-main-wrap"> 
<div>
	<div>
		<c:if test="${not empty devWorkList}">
		<div class="flex-end">
			<input class="pms-btn width-60" id="myWork" type="button" style="margin-right: 5px;" value="개인" />
			<input class="pms-btn width-60" id="totalWork" type="button" value="전체" />
		</div>
		</c:if>
	</div>
	<div style="display: flex;">
		<div class="box1">
						<div class="sub-title-wrap" style="margin-bottom: 20px;">
				프로젝트 개요 
			</div>
	
	     	<h4>${pmsList }</h4>
	     	<span style="margin-bottom: 30px;"></span>
			<table class="table">
			<colgroup>
				<col style="width: 108px">
				<col style="width: 108px">
				<col style="width: 108px">
				<col style="width: 108px">
			</colgroup>
			<thead class="thead-light">
				<tr>
					<th class="tg-0lax"></th>
					<th class="tg-0lax">진행중</th>
					<th class="tg-0lax">완료됨</th>
					<th class="tg-0lax">합계</th>
				</tr>
			</thead>
			<c:if test="${not empty devWorkList}">
				<tbody class="myList">
				    <tr>
						<th class="tg-0lax">신규</th>
						<th class="tg-0lax">${devWorkList.NEW_PROGRESS }</th>
						<th class="tg-0lax">${devWorkList.NEW_COMPLETED }</th>
						<th class="tg-0lax">${devWorkList.NEW_SUM }</th>
					</tr>
					<tr>
						<td class="tg-0lax">결함</td>
						<td class="tg-0lax">${devWorkList.DEFECT_PROGRESS }</td>
						<td class="tg-0lax">${devWorkList.DEFECT_COMPLETED }</td>
						<td class="tg-0lax">${devWorkList.DEFECT_SUM }</td>
					</tr>
					<tr>
						<td class="tg-0lax">지원</td>
						<td class="tg-0lax">${devWorkList.SUPPORT_PROGRESS }</td>
						<td class="tg-0lax">${devWorkList.SUPPORT_COMPLETED }</td>
						<td class="tg-0lax">${devWorkList.SUPPORT_SUM }</td>
					</tr>
					<tr>
						<th class="tg-0lax">공지${cnt}</th>
						<th class="tg-0lax">${devWorkList.NOTICE_PROGRESS }</th>
						<th class="tg-0lax">${devWorkList.NOTICE_COMPLETED }</th>
						<th class="tg-0lax">${devWorkList.NOTICE_SUM }</th>
					</tr>
				</tbody>
						
				<tbody class="totalList">
				    <tr>
						<th class="tg-0lax">신규</th>
						<th class="tg-0lax">${workCnt.NEW_PROGRESS }</th>
						<th class="tg-0lax">${workCnt.NEW_COMPLETED }</th>
						<th class="tg-0lax">${workCnt.NEW_SUM }</th>
					</tr>
					<tr>
						<td class="tg-0lax">결함</td>
						<td class="tg-0lax">${workCnt.DEFECT_PROGRESS }</td>
						<td class="tg-0lax">${workCnt.DEFECT_COMPLETED }</td>
						<td class="tg-0lax">${workCnt.DEFECT_SUM }</td>
					</tr>
					<tr>
						<td class="tg-0lax">지원</td>
						<td class="tg-0lax">${workCnt.SUPPORT_PROGRESS }</td>
						<td class="tg-0lax">${workCnt.SUPPORT_COMPLETED }</td>
						<td class="tg-0lax">${workCnt.SUPPORT_SUM }</td>
					</tr>
					<tr>
						<th class="tg-0lax">공지${cnt}</th>
						<th class="tg-0lax">${workCnt.NOTICE_PROGRESS }</th>
						<th class="tg-0lax">${workCnt.NOTICE_COMPLETED }</th>
						<th class="tg-0lax">${workCnt.NOTICE_SUM }</th>
					</tr>
				</tbody>
				<script>
					$(".totalList").hide();
					$(".totalGantt").hide();
					
					$("#myWork").on("click", function() {
						$(".totalList").hide();
						$(".myList").show();
						$(".myGantt").show()
						$(".totalGantt").hide();
					})
					
					$("#totalWork").on("click", function() {
						$(".totalList").show();
						$(".myList").hide();
						$(".myGantt").hide();
						$(".totalGantt").show();
					})
				</script>		
			</c:if>
			<c:if test="${empty devWorkList}">
				<tbody>
				    <tr>
						<th class="tg-0lax">신규</th>
						<th class="tg-0lax">${workCnt.NEW_PROGRESS }</th>
						<th class="tg-0lax">${workCnt.NEW_COMPLETED }</th>
						<th class="tg-0lax">${workCnt.NEW_SUM }</th>
					</tr>
					<tr>
						<td class="tg-0lax">결함</td>
						<td class="tg-0lax">${workCnt.DEFECT_PROGRESS }</td>
						<td class="tg-0lax">${workCnt.DEFECT_COMPLETED }</td>
						<td class="tg-0lax">${workCnt.DEFECT_SUM }</td>
					</tr>
					<tr>
						<td class="tg-0lax">지원</td>
						<td class="tg-0lax">${workCnt.SUPPORT_PROGRESS }</td>
						<td class="tg-0lax">${workCnt.SUPPORT_COMPLETED }</td>
						<td class="tg-0lax">${workCnt.SUPPORT_SUM }</td>
					</tr>
					<tr>
						<th class="tg-0lax">공지${cnt}</th>
						<th class="tg-0lax">${workCnt.NOTICE_PROGRESS }</th>
						<th class="tg-0lax">${workCnt.NOTICE_COMPLETED }</th>
						<th class="tg-0lax">${workCnt.NOTICE_SUM }</th>
					</tr>
				</tbody>
			</c:if>
			</table>
<!-- 				파이차트 -->
				<canvas id="pie-chart" width="250" height="250"></canvas>
<script>
new Chart(document.getElementById("pie-chart"), {
    type: 'pie',
    data: {
      labels: ["낮음", "보통", "높음", "긴급", "즉시"],
      datasets: [{
        label: "Population (millions)",
        backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
        data: [${pieMap.WORK_LOW},${pieMap.WORK_NORMAL},${pieMap.WORK_HIGH},${pieMap.WORK_EMER},${pieMap.WORK_NOW}]
      }]
    },
    options: {
      title: {
        display: true,
        text: ''
      }
    }
});
</script>
				<div id="progressbar"></div>
				<span>진행도 : ${progress}%</span>
		</div>
		<div class="box3">
			<c:if test="${authMember.memRole eq 'DEV'}">
					<div class="sub-title-wrap" style="margin-bottom: 20px;">
						프로젝트 구성원
					</div>
					<div class="wrap">
						<table>
							<c:forEach items="${vo}" var="member" varStatus="status">
								<c:if test="${status.index % 3 eq 0 }">
									<tr>
									<c:forEach var="j" begin="${status.index }" end="${status.index + (3 - 1) }">
										<c:if test="${ vo[j] ne null }">
											<td>
												<div class="team-wrap">
	<!-- 												<div class="img-wrap"> -->
	<%-- 											    	<img class="team-img" src="${cPath}/imageRender.do?what=${vo[j].devImg}" alt="${cPath}${vo[j].defaultImage}" /> --%>
	<!-- 												</div> -->
													<div class="">${vo[j].realName } ${vo[j].authority } </div>
												</div>
											</td>
										
										</c:if>
									</c:forEach>
									</tr>
								</c:if>
							</c:forEach>
						</table>
					</div>
			</c:if>
			
			<c:if test="${authMember.memRole eq 'CLIENT'}">
				<div id="workTableArea">
					<div id="whoList" class="sub-title-wrap" style="margin-bottom: 20px;">
						프로젝트 구성원
					</div>
					
					<span style="margin-bottom: 30px;"></span>
				<table class="table">
				<colgroup>
					<col style="width: 108px">
					<col style="width: 108px">
					<col style="width: 108px">
					<col style="width: 108px">
				</colgroup>
				<thead class="thead-light">
					<tr>
						<th class="tg-0lax"></th>
						<th class="tg-0lax">담당자</th>
						<th class="tg-0lax">진행중</th>
						<th class="tg-0lax">완료됨</th>
						<th class="tg-0lax">합계</th>
					</tr>
				</thead>
				<tbody>
				    <tr>
						<th class="tg-0lax">신규</th>
						<th class="tg-0lax">
							<c:forEach items="${totalWorkList}" var="work">
								${work.realName}<br />
							</c:forEach>
						</th>
						<th class="tg-0lax">
							<c:forEach items="${totalWorkList}" var="work">
								${work.devWorkMap.NEW_PROGRESS} <br />
							</c:forEach>
						</th>
						<th class="tg-0lax">
							<c:forEach items="${totalWorkList}" var="work">
								${work.devWorkMap.NEW_COMPLETED} <br />
							</c:forEach>
						</th>
						<th class="tg-0lax">
							<c:forEach items="${totalWorkList}" var="work">
								${work.devWorkMap.NEW_SUM} <br />
							</c:forEach>
						</th>
					</tr>
					<tr>
						<th class="tg-0lax">결함</th>
						<th class="tg-0lax">
							<c:forEach items="${totalWorkList}" var="work">
								${work.realName}<br />
							</c:forEach>
						</th>
						<th class="tg-0lax">
							<c:forEach items="${totalWorkList}" var="work">
								${work.devWorkMap.DEFECT_PROGRESS} <br />
							</c:forEach>
						</th>
						<th class="tg-0lax">
							<c:forEach items="${totalWorkList}" var="work">
								${work.devWorkMap.DEFECT_COMPLETED} <br />
							</c:forEach>
						</th>
						<th class="tg-0lax">
							<c:forEach items="${totalWorkList}" var="work">
								${work.devWorkMap.DEFECT_SUM} <br />
							</c:forEach>
						</th>
					</tr>
					<tr>
						<td class="tg-0lax">지원</td>
						<th class="tg-0lax">
							<c:forEach items="${totalWorkList}" var="work">
								${work.realName}<br />
							</c:forEach>
						</th>
						<th class="tg-0lax">
							<c:forEach items="${totalWorkList}" var="work">
								${work.devWorkMap.SUPPORT_PROGRESS} <br />
							</c:forEach>
						</th>
						<th class="tg-0lax">
							<c:forEach items="${totalWorkList}" var="work">
								${work.devWorkMap.SUPPORT_COMPLETED} <br />
							</c:forEach>
						</th>
						<th class="tg-0lax">
							<c:forEach items="${totalWorkList}" var="work">
								${work.devWorkMap.SUPPORT_SUM} <br />
							</c:forEach>
						</th>
					</tr>
					<tr>
						<th class="tg-0lax">공지${cnt}</th>
						<th class="tg-0lax">
							<c:forEach items="${totalWorkList}" var="work">
								${work.realName}<br />
							</c:forEach>
						</th>
						<th class="tg-0lax">
							<c:forEach items="${totalWorkList}" var="work">
								${work.devWorkMap.NOTICE_PROGRESS} <br />
							</c:forEach>
						</th>
						<th class="tg-0lax">
							<c:forEach items="${totalWorkList}" var="work">
								${work.devWorkMap.NOTICE_COMPLETED} <br />
							</c:forEach>
						</th>
						<th class="tg-0lax">
							<c:forEach items="${totalWorkList}" var="work">
								${work.devWorkMap.NOTICE_SUM} <br />
							</c:forEach>
						</th>
					</tr>
				</tbody>
				</table>
				</div>
			</c:if>
		</div>

		</div>  
	</div>
</div>

<script>
  $( function() {
    $( "#progressbar" ).progressbar({
      value: ${progress}
    });
  } );
</script>
 
 
 <script>
 	
//  	$("#workTableArea").hide();
 
//  	$(".workListTrace").on("click", function() {
// 		let memId = $(this).data("id");
// 		let name = $(this).data("name")
				
// 		$.ajax({
// 			url : "${cPath}/pms/${authMember.proNo}/mainWorkList.do",
// 			data : {
// 				memId : memId
// 			},
// 			dataType : "json",
// 			success : function(resp) {		
// 				$("#workTableArea").show();
// 				$("#whoList").empty();
// 				$(".devList").empty();
// 				$("#whoList").html(name + "프로젝트 구성원").attr("data-name", name);
				
// 				let tr1 = $("<tr>").append(
// 					$("<th>").text("신규").addClass("tg-0lax"),
// 					$("<td>").text(resp.NEW_PROGRESS).addClass("tg-0lax"),
// 					$("<td>").text(resp.NEW_COMPLETED).addClass("tg-0lax"),
// 					$("<td>").text(resp.NEW_SUM).addClass("tg-0lax")
// 				)
				
// 				$(".devList").append(tr1)
				
// 				let tr2 = $("<tr>").append(
// 					$("<th>").text("결함").addClass("tg-0lax"),
// 					$("<td>").text(resp.DEFECT_PROGRESS).addClass("tg-0lax"),
// 					$("<td>").text(resp.DEFECT_COMPLETED).addClass("tg-0lax"),
// 					$("<td>").text(resp.DEFECT_SUM).addClass("tg-0lax")
// 				)
				
// 				$(".devList").append(tr2)
				
// 				let tr3 = $("<tr>").append(
// 					$("<th>").text("지원").addClass("tg-0lax"),
// 					$("<td>").text(resp.SUPPORT_PROGRESS).addClass("tg-0lax"),
// 					$("<td>").text(resp.SUPPORT_COMPLETED).addClass("tg-0lax"),
// 					$("<td>").text(resp.SUPPORT_SUM).addClass("tg-0lax")		
// 				)
				
// 				$(".devList").append(tr3)
				
// 				let tr4 = $("<tr>").append(
// 					$("<th>").text("공지").addClass("tg-0lax"),
// 					$("<td>").text(resp.NOTICE_PROGRESS).addClass("tg-0lax"),
// 					$("<td>").text(resp.NOTICE_COMPLETED).addClass("tg-0lax"),
// 					$("<td>").text(resp.NOTICE_SUM).addClass("tg-0lax")		
// 				)
				
// 				$(".devList").append(tr4)
				
// 			},
// 			error : function(xhr, errorResp, error) {
// 				console.log(xhr);
// 				console.log(errorResp);
// 				console.log(error);
// 			}
// 		})
// 	})
 </script>
 