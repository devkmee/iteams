<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mypage.css">

<!-- 
	안쓰는 jsp
 -->




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
				<span class="sub-mypage-txt">마이페이지 ></span>
			</div>
			<div class="flex-end">
				<a href="<%=request.getContextPath()%>/outs/schedule/scheduleList.do">
					<img class="m-right-2" src="/iteams/resources/images/mycal.png">
				</a>
			</div>
				<div class="tab-wrap">
					<ul class="nav nav-tabs m-bottom-20" id="myTab" role="tablist">
					  <li class="nav-item2 m-right-2" role="presentation">
					    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">회원정보</a>
					  </li>
					  <li class="nav-item2  m-right-2" role="presentation">
					    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">지원/초대받은 프로젝트</a>
					  </li>
					  <li class="nav-item2" role="presentation">
					    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile2" role="tab" aria-controls="profile2" aria-selected="false">진행/완료 프로젝트</a>
					  </li>
					</ul>
					
					<div class="tab-content" id="myTabContent">
			   	   	   <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
							<div class="member-basic-wrap">
								<table class="join-table">
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
									<input type="button" class="btn btn-gray m-right-10 p-btn-1030" 
											value="수정" onclick="updateHandler();"/> 
									<input type="button" class="btn btn-gray m-right-10 p-btn-1030" 
											value="탈퇴" onclick="deleteHandler();"/> 
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
					    
					    
					    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
							<div class="project-wrap-txt m-bottom-10">지원한 프로젝트 &gt;</div>
								<div class="table-list">
									<table class="table">
									  <thead>
									    <tr>
									      <th scope="col">프로젝트명</th>
									      <th scope="col">회사명</th>
									      <th scope="col">직무</th>
									      <th scope="col">지원일</th>
									      <th scope="col">마감일</th>
									      <th scope="col">지원결과</th>
									    </tr>
									  </thead>
									  <tbody>
									    <c:set var="applyList" value="${applyPro.dataList }"></c:set>
										  <c:choose>
										  	<c:when test="${empty applyList }">
										  		<tr>
											      <td scope="row" colspan="6">
											      	지원한 프로젝트가 없습니다.
											      </td>
											    </tr>
										  	</c:when>
										  	<c:otherwise>
										  		<c:forEach items="${applyList }" var="app">
										  			<c:url value="/outs/board/projectboard/projectAppView.do" var="selectURL">
														<c:param name="what" value="${app.boNum }"></c:param>
													</c:url>
										  			<tr class="linkBtn" data-gopage="${selectURL }">
												      <td scope="row">
												      	<div class="profile-wrap">
													      	<div class="profile-name">
													      		<div>${app.projectName }</div>
													      	</div>
												      	</div>
												      </td>
												      <td>${app.clientName }</td>
												      <td>${app.projectJob }</td>
												      <td>${app.appDate }</td>
												      <c:choose>
														<c:when test="${not empty app.limitModifyDate}">
															<td>${app.limitModifyDate}</td>
														</c:when>
														<c:otherwise>
															<td>${app.limitDate}</td>
														</c:otherwise>
													  </c:choose>
													  	<td>${app.hiredValue}</td>
												    </tr>
										  		</c:forEach>
										  	</c:otherwise>
										  </c:choose>
									  </tbody>
									</table>
								</div>
								
							<c:if test="${not empty applyList }">
								<div class="paging-wrap">
								<div id="pagingArea"> 
											<div class="paging-wrap">
											   <div id="pagingArea"> ${applyPro.pagingHTML } </div>	
										</div>
									</div>	
								</div>
							</c:if>	
								
							<div class="project-wrap-txt m-bottom-10">초대받은 프로젝트 &gt;</div>
							<div class="table-list">
								<table class="table">
								  <thead>
								    <tr>
								      <th scope="col">프로젝트명</th>
								      <th scope="col">클라이언트</th>
								      <th scope="col">역할/권한</th>
								      <th scope="col">초대일자</th>
								      <th scope="col">상태</th>
								      <th scope="col">수락</th>
								      <th scope="col">거절</th>
								    </tr>
								  </thead>
								  <tbody>
								  <c:set var="profileList" value="${data.dataList }"></c:set>
									  <c:choose>
									  	<c:when test="${empty profileList }">
									  		<tr>
										      <td scope="row" colspan="7">
										      	초대받은 프로젝트가 없습니다.
										      </td>
										    </tr>
									  	</c:when>
									  	<c:otherwise>
									  		<c:forEach items="${profileList }" var="invite" varStatus="status">
									  			<tr scope="row">
											      <td>${invite.projectName }</td>
											      <td>${invite.clientName }</td>
											      <td>${invite.inviteAuth }</td>
											      <td>${invite.inviteDate }</td>
											      <td>${invite.inviteStateValue }</td>
													<form id="acceptForm_${status.index}" action="${cPath }/outs/hire/mypage/acceptEdit.do" method="post">
														<input type="hidden" name="inviteNo" value="${invite.inviteNo}"/>
														<input type="hidden" name="proNo" value="${invite.proNo}"/>
														<input type="hidden" name="inviteAuth" value="${invite.inviteAuth}"/><br>
													</form>
												<c:if test="${invite.inviteStateValue eq '미답변'}">
											      <td><input type="button" value="수락" onclick="resultSubmit(${status.index},'수락','acceptForm')"></td>
											      <td><input type="button" value="거절" onclick="resultSubmit(${status.index},'거절','refuseForm')"></td>
											     </c:if>
											        <form id="refuseForm_${status.index}" action="${cPath }/outs/hire/mypage/refuseEdit.do" method="post">
											      		<input type="hidden" name="inviteNo" value="${invite.inviteNo}"/>
											        </form>
											        <script>
												        function resultSubmit(index, text, formId){
												        	result = confirm("초대를 "+text+ "하시겠습니까?")
												        	if(result){
												        		console.log($("#"+formId+"_"+index).toString())
												        		$("#"+formId+"_"+index).submit();
												        	}else{
												        		return false;	
												        	}
												        }
											        </script>
											    </tr>
									  		</c:forEach>
									  	</c:otherwise>
									  </c:choose>
								  </tbody>
								</table>
							<c:if test="${not empty profileList }">
								<div class="paging-wrap">
								   <div id="pagingArea"> ${data.pagingHTML } </div>	
								</div>
							</c:if>
								
							</div>
						</div>
						<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
							
							<div class="tab-pane fade" id="profile2" role="tabpanel" aria-labelledby="profile-tab">
							<div class="project-wrap-txt m-bottom-10">진행중인 프로젝트 &gt;</div>
								<div class="table-list">
									<table class="table">
									  <thead>
									    <tr>
									      <th scope="col">프로젝트명</th>
									      <th scope="col">생성일자</th>
									      <th scope="col">권한</th>
									      <th scope="col">참여일자</th>
									    </tr>
									  </thead>
									  <tbody>
								  <c:set var="ongoingList" value="${ongoing.dataList }"></c:set>
									  <c:choose>
									  	<c:when test="${empty ongoingList }">
									  		<tr>
										      <td scope="row" colspan="4">
										      	진행중인 프로젝트가 없습니다.
										      </td>
										    </tr>
									  	</c:when>
									  	<c:otherwise>
									  		<c:forEach items="${ongoingList }" var="ongoing" varStatus="status">
									  			<tr scope="row">
											      <td>${ongoing.projectName }</td>
											      <td>${ongoing.createDate }</td>
											      <td>${ongoing.authority }</td>
											      <td>${ongoing.joinDate }</td>
											    </tr>
									  		</c:forEach>
									  	</c:otherwise>
									  </c:choose>
								  </tbody>
									</table>
								</div>
								
							<div class="project-wrap-txt m-bottom-10">완료된 프로젝트 &gt;</div>
							<div class="table-list">
								<table class="table">
									  <thead>
									    <tr>
									      <th scope="col">프로젝트명</th>
									      <th scope="col">완료일자</th>
									      <th scope="col">권한</th>
									      <th scope="col">참여일자</th>
									    </tr>
									  </thead>
									  <tbody>
								  <c:set var="endList" value="${end.dataList }"></c:set>
									  <c:choose>
									  	<c:when test="${empty endList }">
									  		<tr>
										      <td scope="row" colspan="4">
										      	완료한 프로젝트가 없습니다.
										      </td>
										    </tr>
									  	</c:when>
									  	<c:otherwise>
									  		<c:forEach items="${endList }" var="end" varStatus="status">
									  			<tr scope="row">
											      <td>${end.projectName }</td>
											      <td>${end.completeDate }</td>
											      <td>${end.authority }</td>
											      <td>${end.joinDate }</td>
											    </tr>
									  		</c:forEach>
									  	</c:otherwise>
									  </c:choose>
								  </tbody>
								</table>
							</div>
							
							<c:if test="${not empty endList }">
								<div class="paging-wrap">
								   <div id="pagingArea"> ${end.pagingHTML } </div>	
								</div>
							</c:if>
							</div>						  
						</div>
				</div>
			</div>
		</div>
	</div>