<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="sub-title-wrap m-bottom-20">
       		<i class="fas fa-chevron-right" aria-hidden="true"></i>
       	문서함
</div>
<div class="flex-end m-bottom-10" style="text-align:end;">
   	<div> 
<label for="filterAdd">검색조건 추가</label>
		<select name="" id="filterAdd" class="form-select pms-select" aria-label="Default select example">
			<option value selected></option>
			<option value="docWriter">작성자</option>
			<option value="editRange">수정권한</option>
			<option value="docTitle">제목</option>
		</select>
		<button type="button" class="btn pms-btn width-60" id="filterBtn">적용</button>
		<button type="button" class="linkBtn btn pms-btn width-120" data-gopage="${cPath }/pms/documents/${authMember.proNo }/documentsInsert.do">새 문서 작성</button>
		
		<div id="filterArea">
			<div class="filterWrapper">		
				<label for="charger">작성자 : </label>
				<select id="docWriter" class="filterInput pms-select">
					<option value></option>
					<c:if test="${not empty proMemList }" >
						<c:forEach items="${proMemList }" var="member">
					
<%-- 							<c:if test="${member.authority eq 'PM'}"> --%>
<%-- 								<option value="${member.devId }" <c:if test="${member.devId eq searchVO.docWriter}">selected</c:if> >${member.managerName }</option> --%>
<%-- 							</c:if> --%>
<%-- 							<c:if test="${member.authority ne 'PM'}"> --%>
<%-- 								<option value="${member.devId }" <c:if test="${member.devId eq searchVO.docWriter}">selected</c:if> >${member.devName }</option> --%>
<%-- 							</c:if> --%>
							<option value="${member.devId }" <c:if test="${member.devId eq searchVO.docWriter}">selected</c:if> >${member.realName }</option>
						</c:forEach>
					</c:if>
					<c:if test="${empty proMemList }">
						<option value>소속 팀원이 없음.</option>
					</c:if>
				</select>
				<button type="button" class="btn btn-delete width-60">제외</button>
			</div>
			
			<div class="filterWrapper" >
				<label for="">제목 : </label>
				<input type="text" id="docTitle" class="filterInput pms-select" value="${searchVO.workTitle }"/>
				<button type="button" class="btn btn-delete width-60">제외</button>
			</div>
			
			<div class="filterWrapper" >	
				<label for="">수정권한 : </label>
				<select id="editRange" class="filterInput pms-select">
					<option value selected></option>
					<option value="PM" <c:if test="${searchVO.editRange eq 'PM'}">selected</c:if>  >PM</option>
					<option value="PL" <c:if test="${searchVO.editRange eq 'PL'}">selected</c:if>  >PL</option>
					<option value="AA" <c:if test="${searchVO.editRange eq 'AA'}">selected</c:if>  >AA</option>
					<option value="BA" <c:if test="${searchVO.editRange eq 'BA'}">selected</c:if>  >BA</option>
					<option value="TA" <c:if test="${searchVO.editRange eq 'TA'}">selected</c:if>  >TA</option>
					<option value="DA" <c:if test="${searchVO.editRange eq 'DA'}">selected</c:if>  >DA</option>
					<option value="UA" <c:if test="${searchVO.editRange eq 'UA'}">selected</c:if>  >UA</option>
				</select>
				<button type="button" class="btn btn-delete width-60">제외</button>
			</div>
		</div>
	</div>
</div>		
<div class="page-breadcrumb">
		<table class="table text-align-center" style="cursor: pointer;">
			<thead class="thead-light">
			    
   	 			<tr class="tr-gray" >
			      <th scope="col">#</th>
			      <th scope="col">제목</th>
			      <th scope="col">담당 아키텍트</th>
			      <th scope="col">작성자</th>
			      <th scope="col">작성일</th>
    			</tr>
  			</thead>
  			<tbody id="listBody">
			</tbody>
		</table>
	<div class="paging-wrap">
		<div id="pagingArea"></div>
	</div>
</div>

<form action="" id="searchForm">
<input type="hidden" name="page" value="${searchVO.page }"/>
<input type="hidden" name="docTitle" value="${searchVO.docTitle }"/>
<input type="hidden" name="docWriter" value="${searchVO.docWriter }"/>
<input type="hidden" name="editRange" value="${searchVO.editRange }"/>
</form>

<script>
let listBody = $("#listBody");
let pagingArea = $("#pagingArea")
let pageTag = $("[name='page']")


let editRange = $("[name='editRange']").val()
let title = $("[name='docTitle']").val()
let writer = $("[name='docWriter']").val()

if(editRange) {
	$("#editRange").parents(".filterWrapper").show()
} else if(!editRange) {
	$("#editRange").parents(".filterWrapper").hide()
}

if(title) {
	$("#docTitle").parents(".filterWrapper").show()
} else if(!title) {
	$("#docTitle").parents(".filterWrapper").hide()
}

if(writer) {
	$("#docWriter").parents(".filterWrapper").show()
} else if(!writer) {
	$("#docWriter").parents(".filterWrapper").hide()
}

$("#filterAdd").on("change", function() {
	let searchName = $("#filterAdd option:selected").val()
	let filterTag = $("#" + searchName)
	let filterDiv = filterTag.parents(".filterWrapper")
	filterDiv.show()
});

$(".btn-delete").on("click", function() {
	let filterDiv = $(this).parents(".filterWrapper")
	filterDiv.toggle()
	filterDiv.find(".filterInput").val("")
});

$("#filterBtn").on("click", function() {
	
	let editRange = $("#editRange").val()
	let title = $("#docTitle").val()
	let writer = $("#docWriter").val()
	
	$("[name='editRange']").val(editRange)
	$("[name='docTitle']").val(title)
	$("[name='docWriter']").val(writer)

	searchForm.submit()
})

let searchForm = $("#searchForm").paging().ajaxForm({
	dataType : "json",
	success : function(resp) {
		console.log(resp)
		let dataList = resp.dataList
		let trTags = []
		let pagingHTML = resp.pagingHTML
		
		listBody.empty();
		pagingArea.empty();
		console.log(dataList)
		
		if(dataList.length > 0) {
			$.each(dataList, function(idx, doc) {
				console.log(doc)
				let trTag = $("<tr>").append(
					$("<th>").attr("scope", "row").text(doc.rnum),
					$("<td>").text(doc.docTitle).addClass("text-left"), <!--하나의 클래스일땐, addClass 여러개일땐 attr-->
					$("<td>").text(doc.editRange),
					$("<td>").text(doc.devName),
					$("<td>").text(doc.attachVO.uploadDate)
				).attr({
					"class" : "linkBtn",
					"data-gopage" : "${cPath}/pms/documents/${authMember.proNo}/documentsView.do?what="+doc.docNum
				})
				trTags.push(trTag)
			})
			
		} else {
			let trTag = $("<tr>").append($("<td>").attr("colspan", "5").text("등록된 문서가 없습니다."))
			trTags.push(trTag)
		}
		
		listBody.append(trTags)
		pagingArea.html(pagingHTML)
		pageTag.val("")
		
	},
	error : function(x) {
		console.log(x.status + "ddddddddd")
		console.log(x)
	}
}).submit()
</script>
