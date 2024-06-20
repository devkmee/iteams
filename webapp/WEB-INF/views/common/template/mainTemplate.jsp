<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>it 프로젝트 중개 서비스 아이팀즈</title>
<tiles:insertAttribute name="preScript" />
</head>
<body>
	<tiles:insertAttribute name="header" />
	<div>
		<tiles:insertAttribute name="content" />
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>
