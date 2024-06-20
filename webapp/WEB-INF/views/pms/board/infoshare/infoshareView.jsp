<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 게시판명 -->
<div class="sub-title-wrap m-bottom-20">
       		<i class="fas fa-chevron-right" aria-hidden="true"></i>
       	정보공유게시판
     </div>
 <!-- 게시글상세 -->    
	<div class="flex-end m-bottom-10">
	 	<a class="btn pms-btn width-80" href="/iteams/pms/board/infoshare/${authMember.proNo }/infoshareList.do">리스트</a>
 	</div>
			<div class="content-line"></div>
	<div class="content-wrap">
		<div class="content-title">
			<div>${detail.boTitle }</div>
		</div>
		<div class="content-info-txt">
			<div>조회수 : ${detail.boHit+1}0</div>
			<div>작성일 : ${detail.writeDate}</div>
			<div>작성자 : ${detail.boWriter }</div>
		</div>
		<div class="content-txt m-top-20">
			${detail.boContent }
		</div>
		<div>
			첨부파일
			<c:if test="${not empty detail.attachList }">
			<c:forEach items="${detail.attachList }" var="attach"> 
				<a href="${cPath }/pms/${authMember.proNo}/download.do?what=${attach.attNo}">${attach.attachOrigin }</a>
			</c:forEach> 
			</c:if> 
			<c:if test="${empty detail.attachList }"> 
				<span>첨부파일이 없습니다.</span> 
 			</c:if> 
		</div>
	</div>
	<div class="flex-center">
	<div class="pms-btn-wrap">
		<c:url value="/pms/board/infoshare/${authMember.proNo }/infoshareUpdate.do" var="updateURL">
			<c:param name="what" value="${detail.boNum }" />
			<c:param name="writer" value="${detail.boWriter }" />
			<c:param name="proNo" value="${authMember.proNo }" />
		</c:url>
		<c:if test="${authMember.memId eq detail.boWriter or authMember.authority eq 'PM' or authMember.authority eq 'PL'}">
			<a class="btn btn-gray" href="${updateURL }">게시물 수정</a>
			 <%-- <input type="text" value="${authMember.proNo }">  일단여기는묶어두기 --%>
			
			<!-- 삭제 -->
			<c:url value="/pms/board/infoshare/${authMember.proNo }/infoshareDelete.do" var="deleteURL">
				<c:param name="what" value="${detail.boNum }" />
				<c:param name="writer" value="${detail.boWriter }" />
				<c:param name="proNo" value="${authMember.proNo }" />
			</c:url>
			<a id="deleteBtn" class="btn btn-gray" href="${deleteURL }">게시물 삭제</a>
		</c:if>
	</div> 
</div>

<!-- 롤에 따라 수정 or 삭제 등 진행 가능하도록 하는 부분. 아래 test값은 바뀌어야 함.(memId 가 일치 할 때 수정, 삭제 가능하도록) -->
<%-- <c:if test="${authMember.memRole eq 'DEV'}"> --%>
<%-- 	<form action="${cPath}/outs/hire/Apply.do" method="post"> --%>
<%-- 		<input type="text" name="boNum" value="${detail.boNum}">boNum --%>
<!-- 		<input type="submit" value="수정?"> -->
<%-- 	</form> --%>
<%-- </c:if> --%>

<script>
	$("#deleteBtn").on("click", function() {
	let valid = confirm("삭제 하시겠습니까?")
	if(!valid) return false;
})
</script>
