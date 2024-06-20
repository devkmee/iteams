<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>
<div class="page-breadcrumb">
	<button type="button" 
		class="linkBtn btn btn-primary" 
		data-gopage="${cPath}/pms/project/${authMember.proNo}/projectUpdate.do?what=${masterVO.what}&who=${masterVO.who}">수정</button>
	<button type="button" class="linkBtn btn btn-danger"
	data-gopage="${cPath}/pms/project/${authMember.proNo}/projectDelete.do?what=${masterVO.what}&writer=${masterVO.who}">삭제</button>
	<table class="table">
  <tbody>
 	<tr>
      <th scope="row">프로젝트 생성일 :</th>
      <td>${masterVO.createDate }</td>
    </tr>
  	<tr>
      <th scope="row">프로젝트제목 :</th>
      <td>${masterVO.projectName }</td>
    </tr>
    <tr>
    	<th scope="row">프로젝트 구성원:</th>
    	<c:set var="projectList" value="${proMemList }" />
			<td>
			<c:if test="${not empty projectList }">
				<c:set var="cnt" value="0"/>
				<c:forEach items="${projectList }" var="project" varStatus="st">
					<c:if test="${project.authority!='PM' }">
						<c:set var="cnt" value="${cnt=cnt+1 }"/>
						<c:if test="${cnt>1 }">, </c:if>
						${project.devName }(${project.authority })
					</c:if>	
					<c:if test="${project.authority=='PM' }">
						,${project.managerName }(${project.authority })
					</c:if>
				</c:forEach>
			
			</c:if>
			</td>
    </tr>
	
  
</table>
</div>
</body>
