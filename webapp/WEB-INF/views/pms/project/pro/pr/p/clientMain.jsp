<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
@font-face {
    font-family: 'NanumSquareRound';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
body {
	margin: 0px;
}
.list-wrap {
	position: relative;
	display: flex;
	justify-content: center;
	top: 90px;
}
.list-box {
    margin: 100px 30px;
    width: 580px;
    box-sizing: border-box;
    box-shadow: 5px 5px 15px 5px rgb(0 0 0 / 10%);
    border-radius: 30px;
    height: 368px;
    text-align: center;
    padding: 50px 10px;
    background-color: #fff;
    position: relative;
}
.sub-txt {
	font-size: 26px;
    line-height: 1.75em;
    margin-top: 25px;
    font-family: 'NanumSquareRound';
}
.project-select {
	margin-top: 20px;
}
.project-select > select {
	font-family: 'NanumSquareRound';
	font-size: 17px;
    padding: 6px;
    border: 1px solid #aeaeaf;
    border-radius: 4px;
    width: 500px;
}
.arrow-wrap {
	position: absolute;
    right: 50px;
    bottom: 38px;
}
.pro-wrap {
	width: 100vw;
    height: 100vh;
    background-color: #edf1f5;
}

.pro-top {
    position: absolute;
    width: 100%;
    height: 300px;
    background-color: #14366f;
}

.pmsclient-img {
	display: flex;
	justify-content: end;
	position: fixed;
    bottom: 0;
    right: 0;
}

.logo-wrap {
    margin: 20px;
}
</style>
<title>아이팀즈 프로젝트 관리</title>
</head>
<body>
	<div class="pro-wrap">
		<div class="pro-top">
			<div class="logo-wrap"><
				<a href="<%=request.getContextPath()%>/index.do"><img src="/iteams/resources/images/logo-white.png"></a>
			</div>
		</div>
		<div class="list-wrap">
			<div class="list-box">
				<div>
					<img src="/iteams/resources/images/project.png">
				</div>
				<div class="sub-txt">
					진행중인 프로젝트
				</div>
				<div class="project-select">
					<select name="target" onchange="window.open(value,'_self')" size=1>
						<option selected="selected" value="">선택해주세요</option>
						<c:set var="projectList" value="${masterVO.dataList }" />
							
							<c:forEach items="${projectList }" var="project">	
								<c:url value="/pms/${project.proNo }/main.do" var="viewURL">
								</c:url>
								<option value="/iteams/pms/${project.proNo }/main.do"><a href="${viewURL }">${project.projectName }</a></option>
							</c:forEach>
					</select>
				</div>
				<div class="arrow-wrap">
					<img alt="" src="<%=request.getContextPath()%>/resources/images/arrow.png">
				</div>
			</div>
				<div class="list-box">
				<div>
					<img src="/iteams/resources/images/newproject.png">
				</div>
				<div class="sub-txt">
					생성가능 프로젝트
				</div>
				<div class="project-select">
					<select name="target" onchange="window.open(value,'_self')" size=1>
						<option selected="selected" value="">선택해주세요</option>
						<c:set var="projectList" value="${masterVO.dataListt }" />
							<c:forEach items="${projectList }" var="project">
								<c:url value="/iteams/pms/project/${authMember.proNo }/projectInsert.do" var="viewURL">

								</c:url>
								<option value="/iteams/pms/project/${authMember.proNo }/projectInsert.do?boNum=${project.boNum}">${project.projectName }</option>
							</c:forEach>
					</select>
				</div>
				<div class="arrow-wrap">
					<img alt="" src="<%=request.getContextPath()%>/resources/images/arrow.png">
				</div>
			</div>
		</div>
		<div class="pmsclient-img">
			<img src="/iteams/resources/images/pmsclient.png">
		</div>
	</div>
</body>
</html>