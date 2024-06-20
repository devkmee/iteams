<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mypage.css">

<div class="login-content-wrap">
	<div class="mypage-wrap">
		<div class="sub-mypage-wrap">
			<div class="sub-inner-box">
				<div class="main-outline-box-img m-right-10">
					<img alt="" src="/iteams/resources/images/mypage-img.png">
				</div>
			</div>
		</div>
		<div class=" mypage-tab-wrap">
			<div class="sub-mypage-txt">
				<span class="sub-mypage-txt"> 마이페이지 ></span>
			</div>
			<!-- 개인일정 -->
			<div class="flex-end">
				<a href="<%=request.getContextPath()%>/outs/schedule/scheduleList.do">
					<img class="m-right-2" src="/iteams/resources/images/mycal.png">
				</a>
			</div>
			<!-- 개인일정 -->
				<div class="tab-wrap">
					<ul class="nav nav-tabs">
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link active" href="#">회원정보</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/member/mypageBoard.do">나의 게시글</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/member/mypageApplyInvite.do">지원/초대받은 프로젝트</a>
					  </li>
					  <li class="nav-item2">
					    <a class="nav-link" href="${cPath}/outs/member/mypageOngoingEnd.do">진행/완료 프로젝트</a>
					  </li>
					</ul>
					
					<div class="tab-content" id="myTabContent">
			   	   	   <div>
							<div class="member-basic-wrap">

<!-- 							<form:form commandName="member" method="post" enctype="multipart/form-data" id="memberForm"> -->
								<form action="${cPath}/outs/member/devEdit.do" method="post" enctype="multipart/form-data">
								<table class="join-table">
									<tbody>
										<tr>
											<th>이름</th>
											<td>
						                    	<input type="text" name="devName" required value="${devInfo.devName }" />
						                    	<form:errors path="devName" cssClass="error" element="span"></form:errors>
						                    </td>
										</tr>
										<tr>
											<th>프로필 이미지</th>
											<td class="file-wrap">
												<img style="width: 70px; height: 70px; background-position: center center; background-repeat: no-repeat; border-radius: 50% !important; margin-left: 20px;" class="Profile" id="profileImg" src="${cPath }/imageRender.do?what=${devInfo.devImg }" />
						                    	<input id="profile" type="file" name="profileImage" accept="image/*" />
						                    </td>
										</tr>
										<tr>
						                    <th>출생년도</th>
						                    <td>
						                    	${devInfo.devBir }
						                    </td>
						                </tr>
						                <tr>
						                    <th>아이디</th>
						                    <td>
						                    	${devInfo.devId }
						                    </td>
						                 </tr>
						                  <tr>
						                     <th>이메일 주소</th>
						                     <td>
						                     	${devInfo.memMail }
						                     </td>
						                 </tr>
						                <tr>
						                    <th>휴대폰 번호</th>
						                    <td>
						                    	<input type="text" name="memTel" required value="${devInfo.memTel }" />
						                    </td>
						                </tr>
										<tr>
											<th>기술스택</th>
											<td>
												<input id="skill" type="text" name="devTech" required value="${devInfo.devTech }" />
						                    </td>
										</tr>
										<tr>
											<th>직무</th>
											<td>
												<input type="text" name="devJob" required value="${devInfo.devJob }" />
						                    </td>
										</tr>
										<tr>
											<th>경력</th>
											<td>
												<input type="text" name="devCareer" required value="${devInfo.devCareer }" />
						                    </td>
										</tr>
										<tr>
											<th>학력</th>
											<td>
												<input type="text" name="devEdu" required value="${devInfo.devEdu }" />
						                    </td>
										</tr>
										<tr>
											<th>포트폴리오 링크</th>
											<td>
												<input type="text" name="devPort" required value="${devInfo.devPort }" />
						                    </td>
										</tr>	
										<tr>
											<th>첨부파일</th>
											<td class="file-wrap">
						                    	<input type="file" name="attachFiles" />
						                    </td>
										</tr>	
									</tbody>
								
								</table>
								
								<div class="join-btn-wrap">
									<input type="submit" class="btn btn-gray m-right-10 p-btn-1030" 
											value="저장" /> 
								</div>

<!-- 							</form:form> -->
								</form>
							</div>
					    </div>
					    
<script>
document.getElementById("profile").onchange = function () {
    var reader = new FileReader();

    reader.onload = function (e) {
        // get loaded data and render thumbnail.
        document.getElementById("profileImg").src = e.target.result;
    };

    // read the image file as a data URL.
    reader.readAsDataURL(this.files[0]);
};
</script>
				</div>
			</div>
		</div>
	</div>
</div>