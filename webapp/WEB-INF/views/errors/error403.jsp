<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/error.css">
<div class="error-content-wrap">
</div>
<section class="section-wrap">
      <div class="logo-wrap">
         <a href="<%=request.getContextPath()%>/index.do"><img
            src="<%=request.getContextPath()%>/resources/images/logo.png"></a>
      </div>
	<div class="error-content">
		<div class="error-img">
			<img src="<%=request.getContextPath()%>/resources/images/error.png">
		</div>
		<div class="error-txt">
			<div style="text-align: center;">
				<h4>잘못된 접근입니다.</h4> 
				요청하신 페이지에 대한 접근 권한이 없습니다.<br>
			</div>
			<div class="flex-center">
				<input onclick="history.back();" type="button" class="btn-basic" value="이전페이지">
			</div>
		</div>
	</div>
</section>