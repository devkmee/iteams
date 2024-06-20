<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/login.css">

<div class="login-content-wrap">
	<div class="login-tab-wrap">
		<div class="login-wrap">
			<div class="login-input-wrap">
				<div class="login-message">
					<h4>로그인이 필요한 서비스입니다.</h4>
					<p>아이팀즈 회원이 아니면, 지금 <span><a class="text-deco" href="<%=request.getContextPath()%>/outs/login/join.do">회원가입</a></span>을 해주세요.</p>
				</div>

                <div class="tab-wrap">
					<ul class="nav nav-tabs m-bottom-20" id="myTab" role="tablist">
					  <li class="nav-item m-right-2" role="presentation">
					    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">개인회원</a>
					  </li>
					  <li class="nav-item" role="presentation">
					    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">기업회원</a>
					  </li>
					</ul>
	                <form id="loginForm" method="post" action="${cPath }/login.do">
					<div class="login-form">
	                    <div class="id-input-box">
	                        <input type="text" id="id" name="memId" class="txt-tool" placeholder="아이디" value="${failId}">
	                    </div>
	                    <div class="pw-input-box">
	                    	 <input type="password" id="pw" name="memPass" class="txt-tool" placeholder="비밀번호">
	                    </div>
	                    
	                    <button type="submit" class="btn-login">로그인</button>
	                </div>
	                </form>
					<div class="idpass-wrap">
						<a href="<%=request.getContextPath()%>/outs/login/findId.do">아이디 찾기</a>
						<a href="<%=request.getContextPath()%>/outs/login/findPass.do">비밀번호 찾기</a>
						<select name="" id="testLogin">
							<option value="">로그인선택</option>
							<option value="" data-id="kyungmin" data-pass="1111">개발자회원</option>
							<option value="" data-id="sungyeop1" data-pass="1111">기업회원</option>
							<option value="" data-id="admin" data-pass="1111">관리자</option>
						</select>
					</div>
				</div>
			</div>
			<div class="line"></div>
			<div class="login-banner-wrap">
				<img src="<%=request.getContextPath()%>/resources/images/login-img.jpg">
			</div>
		</div>
	</div>
	<script>
		$("#testLogin").on("change", function() {
			var id = $(this).find("option:selected").data("id");
			var pass = $(this).find("option:selected").data("pass");
			
			$("#id").val(id);
			$("#pw").val(pass);
			
			$("#loginForm").submit()
			
		})
	</script>
</div>

