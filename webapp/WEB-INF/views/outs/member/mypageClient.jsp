<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/join.css">  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/proListForClient.css">  

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
		<div class="sub-mypage-txt">
				<span class="sub-mypage-txt">마이페이지 ></span>
		</div>
		<div class="tab-wrap">
			<ul class="nav nav-tabs m-bottom-20" id="myTab" role="tablist">
			  <li class="nav-item m-right-2" role="presentation">
			    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">회원정보</a>
			  </li>
			  <li class="nav-item" role="presentation">
			    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">프로젝트 조회</a>
			  </li>
			</ul>
			
			
			
			<div class="tab-content" id="myTabContent">
			  <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
				<div class="mypage-info-wrap">
					<table class="join-table">
						<tbody>
							 <tr>
			                    <th>아이디</th> 
			                    <td> 
									<span>${clientInfo.memId }</span>
			                    </td>
			                 </tr>
							 <tr>
			                    <th>사업자 등록번호</th> 
			                    <td> 
			                    	<span>${clientInfo.businessNum }</span>
			                    </td> 
			                 </tr>
							<tr>
								<th>회사명</th>
								<td>
			                    	<span>${clientInfo.clientName }</span>
			                    </td>
							</tr>
							<tr>
								<th>회사 주소</th>
								<td>
<!-- 									<input type="text" id="sample4_postcode" placeholder="우편번호" style="width: 250px;"> -->
<!-- 									<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" style="margin-bottom: 4px;"><br> -->
<!-- 									<input type="text" id="sample4_roadAddress" placeholder="도로명주소" style="width: 250px;"> -->
<!-- 									<input type="text" id="sample4_jibunAddress" placeholder="지번주소"> -->
<!-- 									<span id="guide" style="color:#999;display:none"></span> -->
<!-- 									<input type="text" id="sample4_detailAddress" placeholder="상세주소"> -->
<!-- 									<input type="text" id="sample4_extraAddress" placeholder="참고항목"> -->
									${clientInfo.clientAdd }
								</td>
							</tr>
							<tr>
			                    <th>CEO명</th>
			                    <td>
									${clientInfo.clientCeo }
			                    </td>
			                </tr>
			                <tr>
			                    <th>설립일</th>
			                    <td>
			                        ${clientInfo.clientAnni }
			                    </td>
			                </tr>
			                 <tr> 
			                    <th>매출액</th>
			                    <td>
									${clientInfo.clientSalesValue }
			                    </td>
			                </tr>
			                <tr>
			                    <th>직원수</th>
			                    <td>
									${clientInfo.clientScale } 
			                    </td>
			                </tr>
			                  <tr>
			                     <th>회사 이메일 주소</th>
<!-- 			                     <td class="client-address"> -->
<!-- 			                         <input type="text" name="memMailList" style="width: 200px;"> -->
<!-- 			                         @ -->
<!-- 			                         <input type="hidden" name="memMailList" value="@"> -->
<!-- 			                         <input type="text" name="memMailList" style="width: 190px;"> -->
<!-- 			                         <select> -->
<!-- 			                             <option value>직접입력</option> -->
<!-- 			                             <option value="naver.com">naver.com</option> -->
<!-- 			                             <option value="gamil.com">gmail.com</option> -->
<!-- 			                         </select> -->
<!-- 			                     </td> -->
								 <td>${clientInfo.memMail }</td>
			                 </tr>
			                <tr>
			                    <th>회사 전화번호</th>
			                    <td>
									${clientInfo.memTel }
			                    </td>
			                </tr>
			                <tr>
			                    <th>회사 홈페이지</th>
			                    <td>
									${clientInfo.clientHomepage }
			                    </td>
			                </tr>
			                  <tr>
			                    <th>담당자명/직급</th>
			                    <td>
<!-- 			                         <input type="text" name="devBir" style="width: 120px;">&nbsp/&nbsp -->
<!-- 			                         <input type="text" name="devBir" style="width: 120px;"> -->
									${clientInfo.managerName } &nbsp/&nbsp ${clientInfo.managerRank }
			                    </td>
			                </tr>
						</tbody>

					</table>
					<div class="join-btn-wrap">
					<input type="button" class="btn btn-primary m-right-10 p-btn-1030" value="수정" onclick="updateHandler();"/> 
					</div>
					<div class="join-btn-wrap">
					<input type="button" class="btn btn-primary m-right-10 p-btn-1030" value="탈퇴" onclick="deleteHandler();"/> 
					</div>
				</div>
			  </div>
			  
			  <form name="updateForm" action="${cPath}/outs/member/clientPass.do" method="post">
					<input type="hidden" name="memPass" />
				</form>
				
				<form name="deleteForm" action="${cPath}/outs/member/deleteClient.do" method="post">
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
			  
<!-- 		  최근 공고중인 프로젝트 for 지원자				 -->
			  <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
				<div class="project-wrap">
					<div class="project-wrap-txt">최근 모집 중인 프로젝트 ></div>
					<div class="employment-wrap">
						<c:set var="projectList" value="${board.dataList }"></c:set> 
						<c:choose> 
							<c:when test="${empty projectList}">
								<div class="project-list-box">
									<sapn>모집 중인 프로젝트가 없습니다.</sapn>
								</div>
							</c:when>
							
							<c:otherwise>
								<c:forEach items="${projectList }" var="proj" varStatus="state">
									<c:url value="/outs/mypage/hire/hireList.do" var="selectURL">
										<c:param name="boNum" value="${proj.boNum }"></c:param>
									</c:url>
										<c:if test="${state.index mod 3 eq 0}">
											</div>
										</c:if>
										<c:if test="${state.index eq 0 || state.index mod 3 eq 0}">
				                 			<div class="project-list-box-wrap">
				                 		</c:if>
											<div id="projDiv" class="linkBtn project-list-box" data-gopage="${selectURL }">
											<span>${proj.clientName }</span>
											<span>${proj.boTitle }</span>
											<span>${proj.projectName }</span>
											<span>${proj.projectRecruit}명</span>
											<c:choose>
												<c:when test="${not empty proj.limitModifyDate}">
													<sapn class="project-list-date"> ~ ${proj.limitModifyDate}</sapn>
												</c:when>
												<c:otherwise>
													<sapn class="project-list-date"> ~ ${proj.limitDate}</sapn>
												</c:otherwise>
											</c:choose>
											<ul>
												<li>#${proj.projectTech}</li>
												<!-- 기술 아이콘 이미지 추가해야 함. div로 변경. 1개의 컬럼에 n개 값넣기로 했는데 더미 데이터 출력 방법 수정할 것 -->
											</ul>
										</div>
									<c:if test="${status.last}">
				                 		</div>
				                 	</c:if>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>

							
					
					
					
					
					
					<div class="project-wrap-txt">모집 완료된 프로젝트 ></div>
					<div class="employment-wrap">
						<div class="project-list-box employment-list-box">
							<span>??????</span> <span>블록체인 기반 플랫폼 관련 프로젝트 개발</span> <span>블록체인(FNT
								거래소) 신기술분야에서 프로젝트에 기여합니다.</span> <span>모집인원: 4명</span> <span class="project-list-date">~21/12/30</span>
							<ul>
								<li>#javascript</li>
								<li>#vue.js</li>
								<li>#html5</li>
							</ul>
						</div>
						<div class="project-list-box employment-list-box">
							<span>(주)소프트랩스</span> <span>블록체인 기반 플랫폼 관련 프로젝트 개발</span> <span>블록체인(FNT
								거래소) 신기술분야에서 프로젝트에 기여합니다.</span> <span>모집인원: 4명</span> <span class="project-list-date">~21/12/30</span>
							<ul>
								<li>#javascript</li>
								<li>#vue.js</li>
								<li>#html5</li>
							</ul>
						</div>
						<div class="project-list-box employment-list-box">
							<span>(주)소프트랩스</span> <span>블록체인 기반 플랫폼 관련 프로젝트 개발</span> <span>블록체인(FNT
								거래소) 신기술분야에서 프로젝트에 기여합니다.</span> <span>모집인원: 4명</span> <span class="project-list-date">~21/12/30</span>
							<ul>
								<li>#javascript</li>
								<li>#vue.js</li>
								<li>#html5</li>
							</ul>
						</div>					
					</div>
				</div>			  
			  
			  </div>
			</div>
		</div>
	</div>
</div>    