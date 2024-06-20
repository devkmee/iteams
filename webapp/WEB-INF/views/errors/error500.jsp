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
				<h4>죄송합니다.</h4> 
				페이지에 문제가 발생했습니다.<br>
				잠시후에 다시 시도해 주십시오.<br>
			</div>
			<div class="flex-center">
				<input onclick="history.back();" type="button" class="btn-basic" value="이전페이지">
			</div>
		</div>
	</div>
</section>