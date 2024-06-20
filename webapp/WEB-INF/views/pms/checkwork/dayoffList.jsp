<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>
<div class="sub-title-wrap m-bottom-20">
       		<i class="fas fa-chevron-right" aria-hidden="true"></i>
       	근태관리
</div>
<div class="flex-end m-bottom-10">
	<div style="text-align: end;">
<!-- 	<label for="charger">이름 : </label> -->
	<select id="listType" class="filterInput pms-select">
<!-- 		<option value></option> -->
<%-- 		<c:if test="${not empty proMemList }"> --%>
<%-- 			<c:forEach items="${proMemList}" var="member"> --%>
<%-- 				<c:if test="${member.authority ne 'PM' }"> --%>
<%-- 					<option value="${member.devId }" <c:if test="${member.devId eq searchVO.devId }">selected</c:if> >${member.devName }</option> --%>
<%-- 				</c:if> --%>
<%-- 			</c:forEach> --%>
<%-- 		</c:if> --%>
<%-- 		<c:if test="${empty proMemList }"> --%>
<!-- 			<option value>소속 팀원이 없습니다.</option> -->
<%-- 		</c:if> --%>
		<option value="dayoff">결근목록</option>
		<option value="late">지각목록</option>
	</select>
<!-- 	<button id="searchBtn" type="button" class="btn pms-btn width-60">적용</button> -->
	</div>
</div>
<table id="dayoffTable" class="table" style="cursor: pointer;">
  <thead>
    <tr>
      <th scope="col">날짜</th>
      <th scope="col">결근목록</th>
    </tr>
  </thead>
  <tbody id="dayoffBody">
  	<c:if test="${not empty monthDayoffList}">
	  	<c:forEach items="${monthDayoffList}" var="dayoff">
	  		<tr>
	  			<td>${dayoff.solarDate}</td>
	  			<td>
	  				<c:forEach items="${dayoff.monthDayoffList}" var="monthDayoff">		  			
		  				<span><c:if test="${not empty monthDayoff.devId}">${monthDayoff.devId}</c:if><c:if test="${empty monthDayoff.devId}">총합</c:if></span>
		  				<span> : ${monthDayoff.dayoffCount} 회</span>
		  				<br />  					
	  				</c:forEach>
	  			</td>
	  		</tr>
	  	</c:forEach>
  	</c:if>
  	<c:if test="${empty monthDayoffList}">
  		<tr>
  			<td colspan="2">결근 목록이 없습니다.</td>
  		</tr>
  	</c:if>
  </tbody>  
</table>

<table id="lateTable" class="table" style="cursor: pointer;">
  <thead>
    <tr>
      <th scope="col">날짜</th>
      <th scope="col">지각목록</th>
    </tr>
  </thead>
  <tbody id="">
  	<c:if test="${not empty monthLateList}">
	  	<c:forEach items="${monthLateList}" var="late">
	  		<tr>
	  			<td>${late.solarDate}</td>
	  			<td>
	  				<c:forEach items="${late.monthLateList}" var="monthLate">		  			
		  				<span><c:if test="${not empty monthLate.devId}">${monthLate.devId}</c:if><c:if test="${empty monthLate.devId}">총합</c:if></span>
		  				<span> : ${monthLate.lateCount} 회</span>
		  				<br />  					
	  				</c:forEach>
	  			</td>
	  		</tr>
	  	</c:forEach>
  	</c:if>
  	<c:if test="${empty monthLateList}">
  		<tr>
  			<td colspan="2">지각 목록이 없습니다.</td>
  		</tr>
  	</c:if>
  </tbody>  
</table>

<!-- <div class="paging-wrap"> -->
<!-- 	 <div id="pagingArea"></div>	 -->
<!-- </div> -->

<%-- <form id="searchForm"> --%>
<%-- <input type="hidden" name="page" value="${searchVO.page }"/> --%>
<%-- <input type="hidden" name="devId" value="${searchVO.devId }"/> --%>
<%-- </form> --%>

<script>

$("#lateTable").hide();

$("#listType").on("change", function() {
	let selected = $("#listType option:selected").val();
	if(selected === "dayoff") {
		$("#dayoffTable").show();
		$("#lateTable").hide();
	} else {
		$("#dayoffTable").hide();
		$("#lateTable").show();
	}
})

// let listBody = $("#listBody");
// let pagingArea = $("#pagingArea")
// let pageTag = $("[name='page']")

// $("#searchBtn").on("click", function() {
// 	searchForm.submit()
// })

// let searchForm = $("#searchForm").paging().ajaxForm({
// 	dataType : "json",
// 	success : function(resp) {
// 		console.log(resp)
// 		let dataList = resp.dataList
// 		console.log(dataList)
// 		let trTags = []
// 		let pagingHTML = resp.pagingHTML
		
// 		listBody.empty();
// 		pagingArea.empty();
		
// 		if(dataList.length > 0) {
// 			$.each(dataList, function(idx, dayoff) {
// 				var list = dayoff.dayOffList
// 				var text = "";
// 				for(i = 0; i < list.length; i++) {
// 					text += list[i].devName;
// 					if(i < list.length-1) {
// 						text += ","
// 					}
// 				}
// 				let trTag = $("<tr>").append(
// 					$("<td>").text(dayoff.solarDate),
// 					$("<td>").text(text)
// 				);
				
// 				trTags.push(trTag)
// 			})
			
// 		} else {
// 			let trTag = $("<tr>").append($("<td>").text("결근 목록이 없습니다."))
// 			trTags.push(trTag)
// 		}
		
// 		listBody.append(trTags)
// 		pagingArea.html(pagingHTML)
// 		pageTag.val("")
		
// 	},
// 	error : function(x) {
// 		console.log(x.status + "ddddddddd")
// 		console.log(x)
// 	}
// }).submit();

// let chargerArea = $("#workCharger")

// $.ajax({
// 	url : "${cPath}/pms/${authMember.proNo}/projectMemberList.do",
// 	method : "post",
// 	dataType : "json",
// 	success : function(resp) {
// 		let options = []
// 		if(resp) {
// 			$.each(resp, function(idx, teamMember) {
// 				let option = null
				
// 				if(teamMember.devName) {
// 					console.log(teamMember.devName)
// 					option = $("<option>").text(teamMember.devName).val(teamMember.devId).attr("data-devId", teamMember.devName)
// 				} else {
// 					option = $("<option>").text(teamMember.managerName).val(teamMember.devId).attr("data-devId", teamMember.managerName)
// 				}
// 				options.push(option)
// 			})
// 		} else {
// 			let option = $("<option>").text("소속팀원없음")
// 			options.push(option)
// 		}
		
// 		chargerArea.append(options);
				
// 	},
// 	error : function(xhr, errorResp, error) {
// 		console.log(xhr);
// 		console.log(errorResp);
// 		console.log(error);
// 	}
// });

// chargerArea.on("change", function() {
// 	let devId = $(this).val()
// 	$("[name='devId']").val(devId)
// })

</script>
</body>
