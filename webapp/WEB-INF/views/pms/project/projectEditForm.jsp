<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${cPath }/resources/js/ckeditor/ckeditor.js"></script>
<script language='javascript'>
 function rowDel(obj){
    	$(this).hide();
 }
 function goBack(){
	 window.history.back();
 }
</script>
	
<div class="page-breadcrumb">
 
<form method="post" enctype="multipart/form-data" id="insertForm">

  <div class="mb-3">
    <label for="inputProName" class="form-label">프로젝트 제목</label>
    <c:if test="${authMember.authority eq 'PM'}">
	    <input required="required" type="text" class="form-control" name="projectName" aria-describedby="emailHelp" value="${masterVO.projectName }">
    </c:if>
    <c:if test="${authMember.authority eq 'PL'}">
	    <input readonly="readonly" type="text" class="form-control" name="projectName" aria-describedby="emailHelp" value="${masterVO.projectName }">
    </c:if>
    
  </div>
  <div class="mb-3">
    <label for="inputMaName" class="form-label">생성자</label>
    <input type="hidden" class="form-control" name="cliId" value="${masterVO.memId }">
    <input type="" class="form-control" name="managerName" value="${masterVO.managerName }" readonly="readonly"/>
    
  </div>
		<table>
			<tr>
				<label for="inputmem" class="form-label">프로젝트 참여자(지원자-생년,파트,아이디)</label>
				<br>
					   <c:set var="devList" value="${masterVO.dataList }" />
				<c:choose>
					<c:when test="${not empty devList }">
						<c:forEach items="${devList }" var="dev" varStatus="loop">
						<c:if test="${authMember.authority eq 'PM'}">
							<c:if test="${dev.authority ne 'PM'}">
								<div id="dev_${loop.index }">
									<span>
									<input type="checkbox" class="devId" name="devList[${loop.index }].devId" value="${dev.devId }" checked ><c:if test="${dev.devName == null}">클라이언트(${dev.managerName },${dev.memId })</c:if><c:if test="${dev.devName != null}">${dev.devName }(${dev.devBir }년생 ,${dev.devJob },${dev.devId })</c:if>
								 	<select class="authority" name="devList[${loop.index }].authority">
								 		<option value="" >역할선택</option>
								 		<option value="PL" <c:if test="${dev.authority == 'PL'}">selected</c:if>>PL</option>
								 		<option value="DA" <c:if test="${dev.authority == 'DA'}">selected</c:if>>DA</option>
								 		<option value="UA" <c:if test="${dev.authority == 'UA'}">selected</c:if>>UA</option>
								 		<option value="AA" <c:if test="${dev.authority == 'AA'}">selected</c:if>>AA</option>
								 		<option value="BA" <c:if test="${dev.authority == 'BA'}">selected</c:if>>BA</option>
								 		<option value="TA" <c:if test="${dev.authority == 'TA'}">selected</c:if>>TA</option>
								 		<option value="PM" <c:if test="${dev.authority == 'PM'}">selected</c:if>>PM</option>
								 	</select>
								 	<input type="hidden" name="devList[${loop.index }].proNo" value="${dev.proNo }"/>
<%-- 								 	<c:if test="${authMember.authority eq 'PM'}"> --%>
<%-- 								 		<input type="button" value="추방" onclick="javascript:rowDel('dev_${loop.index }','${dev.devId }')" /> --%>
<%-- 								 	</c:if> --%>
								 	<br>
								 	</span>
					 			</div>
							</c:if>
						</c:if>
						<c:if test="${authMember.authority eq 'PL'}">
							<c:if test="${dev.authority ne 'PM' and dev.authority ne 'PL'}">
								<div id="dev_${loop.index }">
									<span>
									<input type="checkbox" class="devId" name="devList[${loop.index }].devId" value="${dev.devId }" checked ><c:if test="${dev.devName == null}">클라이언트(${dev.managerName },${dev.memId })</c:if><c:if test="${dev.devName != null}">${dev.devName }(${dev.devBir }년생 ,${dev.devJob },${dev.devId })</c:if>
								 	<select class="authority" name="devList[${loop.index }].authority">
								 		<option value="" >역할선택</option>
								 		<option value="PL" <c:if test="${dev.authority == 'PL'}">selected</c:if>>PL</option>
								 		<option value="DA" <c:if test="${dev.authority == 'DA'}">selected</c:if>>DA</option>
								 		<option value="UA" <c:if test="${dev.authority == 'UA'}">selected</c:if>>UA</option>
								 		<option value="AA" <c:if test="${dev.authority == 'AA'}">selected</c:if>>AA</option>
								 		<option value="BA" <c:if test="${dev.authority == 'BA'}">selected</c:if>>BA</option>
								 		<option value="TA" <c:if test="${dev.authority == 'TA'}">selected</c:if>>TA</option>
								 		<option value="PM" <c:if test="${dev.authority == 'PM'}">selected</c:if>>PM</option>
								 	</select>
								 	<input type="hidden" name="devList[${loop.index }].proNo" value="${dev.proNo }"/>
<%-- 								 	<c:if test="${authMember.authority eq 'PM'}"> --%>
<%-- 								 		<input type="button" value="추방" onclick="javascript:rowDel('dev_${loop.index }','${dev.devId }')"/> --%>
<%-- 								 	</c:if> --%>
								 	<br>
								 	</span>
					 			</div>
							</c:if>
						</c:if>				 	
					  </c:forEach>
					  </c:when>
					  </c:choose>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="저장" class="btn btn-primary"/>
					<input type="reset" value="취소" class="btn btn-second" onclick="goBack()"/>
				</td>
			</tr>
		</table>

	</form>
</div>


