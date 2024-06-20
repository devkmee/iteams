<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 

  <!-- 목록조회 --> 
    <div class="sub-title-wrap m-bottom-20">
         <i class="fas fa-chevron-right" aria-hidden="true"></i>
             정보공유게시판    
    </div>
   

		
  <div class="flex-end m-bottom-10" style="text-align:end;">
   	<div>
        <label for="filterAdd">검색조건 추가</label>
		<select name="" id="filterAdd" class="form-select pms-select" aria-label="Default select example">
			<option value selected></option>
			<option value="boTitle">제목</option>
			<option value="boWriter">작성자</option>
			<option value="boContent">내용</option>
		</select>
		<button type="button" class="btn pms-btn width-60" id="filterBtn">적용</button>
		
		<c:set scope="session" target="searchVO" var="searchTitle" property="boTitle"></c:set>
		<c:set scope="session" target="searchVO" var="searchWriter" property="boWriter"></c:set>
		<c:set scope="session" target="searchVO" var="searchContent" property="boContent"></c:set>
		<div style="text-align: end;">
			<div id="filterArea">	
				<div class="filterWrapper">		
				<label for="charger">작성자 : </label>
				<select id="boWriter" class="filterInput pms-select">
					<option value selected></option>
					<c:if test="${not empty proMemList}">
						<c:forEach items="${proMemList }" var="member">
							<c:if test="${member.authority eq 'PM'}">
								<option value="${member.devId }" <c:if test="${member.devId eq searchWriter}">selected</c:if> >${member.managerName }</option>
							</c:if>
							<c:if test="${member.authority ne 'PM'}">
								<option value="${member.devId }" <c:if test="${member.devId eq searchWriter}">selected</c:if> >${member.devName }</option>
							</c:if>
						</c:forEach>
					</c:if>
				</select>
				<button type="button" class="btn btn-delete width-60">제외</button>
				</div>
				
				
				<div class="filterWrapper">	
				<label for="">제목 : </label>
				<input type="text" id="boTitle" class="filterInput pms-select" value="${searchTitle}"/>
				<button type="button" class="btn btn-delete width-60">제외</button>
				</div>
				
				<div class="filterWrapper">	
				<label for="">내용 : </label>
				<input type="text" id="boContent" class="filterInput pms-select" value="${searchContent}"/>
				<button type="button" class="btn btn-delete width-60">제외</button>
				</div>
			</div>
		</div>
	</div>
	</div>		
      <div class="table-wrap text-align-center">
		<table class="table">
			
			<thead class="thead-light">
			<tr class="tr-gray">
             <td scope="col">No</td>
             <td scope="col">제목</td>
             <td scope="col">작성자</td>
             <td scope="col">작성일</td>
             <td scope="col">조회수</td> 
          	 </tr> 
          </thead>
          <tbody>
          <c:set var="infoList" value="${list }" /> 
            <c:choose>
             <c:when test="${not empty infoList }">
               <c:forEach items="${infoList }" var="board">
                 <tr style="cursor: pointer;">
                    <td>${board.rnum }</td>
                    <c:url value="/pms/board/infoshare/${proNo}/infoshareView.do" var="viewURL">
                         <c:param name="what" value="${board.boNum }" />
                    </c:url>
                    <td class="linkBtn text-left" data-gopage="${viewURL }" >${board.boTitle }</td>
                    <td>${board.realName}</td>
                    <td>${board.writeDate }</td>
                    <td>${board.boHit }</td> 
                 </tr>
               </c:forEach>
              </c:when>
             <c:otherwise>
               <tr>
                 <td colspan="5">검색 조건에 맞는 게시글이 없음.</td>
               </tr>
             </c:otherwise>
            </c:choose>
		   </tbody>     
          </table>
         </div>
                          
        <div class="paging-wrap">
			 <div id="pagingArea"> ${master.pagingHTML } </div>	
		</div>
		<c:url value="/pms/board/infoshare/${authMember.proNo}/infoshareInsert.do" var="insertURL">
		</c:url>
		
		
		<div class="pms-table-wrap">
		  <div id="searchUI" class="form-inline">
		</div>
		</div>
		
		<div class="pms-table-btn">
			<input type="button" value="글작성" class="linkBtn btn pms-btn width-60 float-right" data-gopage="${insertURL }"/>
		</div>
		
		
		<!-- 검색할부분 -->
		<form id="searchForm">
	 <input type="hidden" name="boWriter" value="${searchWriter}"/>
	<input type="hidden" name="boTitle" value="${searchTitle}"/>
	<input type="hidden" name="boContent" value="${searchContent}"/>
	<input type="hidden" name="page" /> <br> 
</form>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript">	
	
	let boWriter = $("[name='boWriter']").val()
	let boTitle = $("[name='boTitle']").val()
	let boContent = $("[name='boContent']").val()

	let boWriterTag = $("#boWriter");
	let boTitleTag = $("#boTitle");
	let boContentTag = $("#boContent");

	$(function() {
		if(boWriter) {
			$("#boWriter").parents(".filterWrapper").show()
		} else if(!boWriter) {
			$("#boWriter").parents(".filterWrapper").hide()
		}
		
		if(boTitle) {
			$("#boTitle").parents(".filterWrapper").show()
		} else if(!boTitle) {
			$("#boTitle").parents(".filterWrapper").hide()
		}
		
		if(boContent) {
			$("#boContent").parents(".filterWrapper").show()
		} else if(!boContent) {
			$("#boContent").parents(".filterWrapper").hide()
		}
	})

	$("#filterAdd").on("change", function() {
		let searchName = $("#filterAdd option:selected").val()
		let filterTag = $("#" + searchName)
		let filterDiv = filterTag.parents(".filterWrapper")
		filterDiv.show()
	});

	$(".btn-delete").on("click", function() {
//	 	event.preventDefault()
		let filterDiv = $(this).parents(".filterWrapper")
		filterDiv.toggle()
		filterDiv.find(".filterInput").val("")
	});

	$("#filterBtn").on("click", function() {
		
		let boWriter = boWriterTag.val()
		let boTitle = boTitleTag.val()
		let boContent = boContentTag.val()
		
		$("[name='boWriter']").val(boWriter)
		$("[name='boTitle']").val(boTitle)
		$("[name='boContent']").val(boContent)
		searchForm.submit()
	})
	
	let searchForm = $("#searchForm").paging();
	
	
</script>
  