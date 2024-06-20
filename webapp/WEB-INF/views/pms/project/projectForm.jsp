<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${cPath }/resources/js/ckeditor/ckeditor.js"></script>
<script>
function goBack(){
	 window.history.back();
}

</script>
	<div class="page-breadcrumb">
 
<form method="post" enctype="multipart/form-data" id="insertForm">
  <div class="mb-3">
    <label for="inputProName" class="form-label">프로젝트 제목</label>
   
    <input type="text" class="form-control" name="projectName" aria-describedby="emailHelp" value="${masterVO.projectName }">
    
  </div>
  <div class="mb-3">
    <label for="inputMaName" class="form-label">생성자</label>
    <input type="hidden" class="form-control" name="cliId" value="${authMember.memId }">
    <input type="" class="form-control" name="managerName" value="${authMember.managerName }" readonly="readonly"/>
  </div>
		<table>
			<tr>
				<label for="inputmem" class="form-label">프로젝트 참여자(지원자-생년,파트,아이디)</label>
				<br>
				<c:set var="devList" value="${masterVO.dataList }" />
				<c:choose>
					<c:when test="${not empty devList }">
						<c:forEach items="${devList }" var="dev" varStatus="loop">
						<c:if test="${dev.authority ne 'PM'}">
						<span>
							<input type="checkbox" class="devId" name="devList[${loop.index }].devId" value="${dev.appId }" checked>${dev.devName }(${dev.devBir }년생 ,${dev.devJob },${dev.appId })
						 	<select class="authority" name="devList[${loop.index }].authority">
						 		<option value="" selected>역할선택</option>
						 		<option value="PL" >PL</option>
						 		<option value="DA" >DA</option>
						 		<option value="UA" >UA</option>
						 		<option value="AA" >AA</option>
						 		<option value="BA" >BA</option>
						 		<option value="TA" >TA</option>
						 	</select>
						 	<br>
						 	<input type="hidden" name="devName" value="${dev.devName }"/>	
<!-- 						 	<input type="text" class="authority"  placeholder="역할"/><span>PL,DA,UA,AA,BA,TA</span><br> -->
					 	</span>
					 	</c:if>
					  </c:forEach>
					  </c:when>
				</c:choose>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="저장" class="btn btn-primary"/>
					<input type="reset" value="취소" class="btn btn-second" onclick="goBack()" />
				</td>
			</tr>
		</table>
<!-- 		<input type="hidden" name="devList[0].devId"/> -->
<!-- 		<input type="hidden" name="devList[0].authority"/> -->
<!-- 		<input type="hidden" name="devList[1].devId"/> -->
<!-- 		<input type="hidden" name="devList[1].authority"/> -->
	</form>
</div>
<!-- <script> 
// $(".btn btn-primary").on("click", function(){
// 	let span = $(this).closest("span");
// 	span.hide();
// 	let devNo = span.data("dev");
// 	insertForm.append(			
// 		$("<input>").attr({
// 			type:"hidden",
// 			name:"devId"
// 		}).val(devNo)
// 	);
	
// });

</script> -->
