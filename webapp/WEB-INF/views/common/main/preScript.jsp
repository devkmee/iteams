<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 웹폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/innks/NanumSquareRound/master/nanumsquareround.min.css">

<!-- css 라이브러리 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">

<!-- css -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/index.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/sub.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/table.css">

<!-- 라이브러리 -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- <span></span><script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script> -->
<!-- <link href="//cdn.jsdelivr.net/npm/@sweetalert2/theme-bootstrap-4/bootstrap-4.css" rel="stylesheet"> -->
<!-- <script src="//cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script> -->
<script src="<%=request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/ckeditor/lang/ko.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/custom/commons.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.form.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/custom/paging.js"></script>
<script type="text/javascript">
const CONTEXT_PATH = "${pageContext.request.contextPath}";
</script>

<c:if test="${not empty message}">
	<script type="text/javascript">
		//Swal.fire("${message}");
		alert("${message}")
	</script>
</c:if>