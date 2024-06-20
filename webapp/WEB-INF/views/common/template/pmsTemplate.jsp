<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>아이팀즈 프로젝트 관리</title>
<tiles:insertAttribute name="preScript"/>
</head>
<body>
    <div class="pms-wrap">
        <div class="pms-layout-wrap">
        	<tiles:insertAttribute name="left"/>
        	<div class="pms-right">
        		<div class="content-top">
	        		<tiles:insertAttribute name="header"/>
	        		<div class="content-bottom">
	        			<div class="pms-content-wrap">
			        		<tiles:insertAttribute name="content" />
	        			</div>
	        		</div>
        		</div>
        	</div>	
        </div>
    </div>   
</body>
</html>
