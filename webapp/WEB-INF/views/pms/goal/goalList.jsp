<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="page-breadcrumb">
			
 	<div class="table-wrap">
 		<div class="">
			
				<input type="button" value="새목표 생성" class="linkBtn btn btn-secondary" 
						data-gopage="${cPath }/pms/goal/${authMember.proNo }/goalInsert.do"/>
			</div>
			<br>
			<table class="table">
			  <thead class="thead-light">
			    <tr>
			      <th scope="col">No</th>
			      <th scope="col">작성자</th>
			      <th scope="col">목표명</th>
			      <th scope="col">작성일</th>
			      <th scope="col">최종수정자</th>
			      <th scope="col">최종수정일</th>
			      <th scope="col">완수여부</th>
			      <th scope="col">완수일</th>
			      <th scope="col">상위목표</th>
			    </tr>
			  </thead>
			  <c:set var="goalList" value="${masterVO.dataList }" />
	<tbody>
		<c:choose>
			<c:when test="${not empty goalList }">
				<c:forEach items="${goalList }" var="goal">
					<tr>
						<td>${goal.rnum }</td>
						<td>${goal.goalWriter }</td>
						<td>${goal.goalName }</td>
						<td>${goal.writeDate }</td>
						<td>${goal.modifyMember }</td>
						<td>${goal.modifyDate }</td>
						<td>${goal.completedNy }</td>
						<td>${goal.completeDate }</td>
						<td>${goal.goalParent }</td>
						
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6">생성된 목표가 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	
		</table>
		


	</div>
</div>