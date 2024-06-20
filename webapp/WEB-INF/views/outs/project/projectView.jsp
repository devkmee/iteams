<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-breadcrumb">
	<div class="table-wrap">
		
		<table class="table">
			<tr>
				<th>프로젝트명</th>
				<td>${vo.projectName }</td>
			</tr>
			<tr>
				<th>프로젝트 규모</th>
				<td>${vo.projectScale }</td>
			</tr>
			<tr>
				<th>예상단가</th>
				<td>${vo.projectPrice }</td>
			</tr>
			<tr>
				<th>요구사항</th>
				<td>${vo.projectReq }</td>
			</tr>
			<tr>
				<th>요구기술스택</th>
				<td>${vo.projectTech }</td>
			</tr>
			<tr>
				<th>요구직무</th>
				<td>${vo.projectJob }</td>
			</tr>
			<tr>
				<th>공고기한</th>
				<td>${vo.limitDate }</td>
			</tr>
		</table>
	</div>	
</div>
