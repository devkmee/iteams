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
		data-gopage="${cPath}/pms/goal/${authMember.proNo}/goalUpdate.do?what=${masterVO.what}&who=${masterVO.who}">수정</button>
	<button type="button" class="linkBtn btn btn-danger"
	data-gopage="${cPath}/pms/goal/${authMember.proNo}/goalDelete.do?what=${masterVO.what}&writer=${masterVO.who}">삭제</button>
	<table class="table">
  <tbody>
 	<tr>
      <th scope="row">목표명 :</th>
      <td>${masterVO.goalName }</td>
    </tr>
  	<tr>
      <th scope="row">프로젝트제목 :</th>
      <td>${masterVO.projectName }</td>
    </tr>
	<tr>
      <th scope="row">상위목표 :</th>
      <td>${masterVO.projectName }</td>
    </tr>

  
</table>
</div>
</body>