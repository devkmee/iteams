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
			<div>
				<h4>죄송합니다.</h4> 
				요청하신 페이지를 찾을 수 없습니다.<br>
				찾으시려는 페이지는 주소를 잘못 입력하였거나,<br>
				페이지의 주소의 변경 또는 삭제 등의 이유로 페이지를 찾을 수 없습니다.<br>
				입력하신 페이지의 주소와 경로가 정확한지 한번더 확인 후 이용하시기 바랍니다.
			</div>
			<div class="flex-end">
				<input onclick="history.back();" type="button" class="btn-basic" value="이전페이지">
			</div>
		</div>
	</div>
</section>