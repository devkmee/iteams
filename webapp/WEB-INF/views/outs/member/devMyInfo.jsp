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
					    <a class="nav-link active" href="${cPath}/outs/member/mypageDevInfo.do">회원정보</a>
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

								<table class="prolist-table m-top-20">
									<tbody>
										<tr>
											<th>이름</th>
											<td>
						                    	${devInfo.devName }
						                    </td>
										</tr>
										<tr>
											<th>프로필 이미지</th>
											<td class="file-wrap">
												<img style="width: 70px; height: 70px; background-position: center center; background-repeat: no-repeat; border-radius: 50% !important; margin-left: 20px;" class="Profile" id="profileImg" src="${cPath }/imageRender.do?what=${devInfo.devImg }" />
<!-- 						                    <input type="file" name="devImg" accept="image/*" style="width: 400px;"/> -->
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
						                    	${devInfo.memTel }
						                    </td>
						                </tr>
									</tbody>
										<tbody>
										<tr>
											<th>기술스택</th>
											<td>
												${devInfo.devTech }
						                    </td>
										</tr>
										<tr>
											<th>직무</th>
											<td>
												${devInfo.devJob }
						                    </td>
										</tr>
										<tr>
											<th>경력</th>
											<td>
												${devInfo.devCareer }
						                    </td>
										</tr>
										<tr>
											<th>학력</th>
											<td>
												${devInfo.devEdu }
						                    </td>
										</tr>
										<tr>
											<th>포트폴리오 링크</th>
											<td>
												${devInfo.devPort }
						                    </td>
										</tr>	
										<tr>
											<th>첨부파일</th>
											<td class="file-wrap">
												<c:choose>
													<c:when test="${empty att }">
														첨부파일이 없습니다.
													</c:when>
													<c:otherwise>
														<a href="${cPath }/profile/download.do?what=${att.attNo}">${att.attachVO.attachOrigin }</a>
													</c:otherwise>
												</c:choose>
						                    </td>	
										</tr>	
									</tbody>
									</table>
								
								<div class="board-btn-wrap">
									<input type="button" class="btn btn-gray m-right-10" value="수정" onclick="updateHandler();"/> 
									<input type="button" class="btn btn-gray m-right-10" value="탈퇴" onclick="deleteHandler();"/> 
								</div>
								
							</div>
					    </div>
					    
 <form name="updateForm" action="${cPath}/outs/member/devPass.do" method="post">
	<input type="hidden" name="memPass" />
</form>

<form name="deleteForm" action="${cPath}/outs/member/deleteDev.do" method="post">
	<input type="hidden" name="del" />
</form>

<script type="text/javascript">
	function updateHandler(){
		let password = prompt("비밀번호를 입력하세요");
		if(password){
			let form = document.updateForm;
			form.memPass.value = password;
			form.submit();
		}
	}
	
	function deleteHandler(){
		let password = prompt("비밀번호를 입력하세요");
		if(password){
			let form = document.deleteForm;
			form.del.value = password;
			form.submit();
		}
	}
</script>
				</div>
			</div>
		</div>
	</div>
</div>