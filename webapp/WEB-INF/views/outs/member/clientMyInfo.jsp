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
					    <a class="nav-link active" href="${cPath}/outs/member/mypageCllientInfo.do">회원정보</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/member/mypageClientBoard.do">나의 게시글</a>
					  </li>
					  <li class="nav-item2 m-right-2">
					    <a class="nav-link" href="${cPath}/outs/member/mypageClientHire.do">모집 프로젝트</a>
					  </li>
					  <li class="nav-item2">
					    <a class="nav-link" href="${cPath}/outs/member/mypageClientEnd.do">완료 프로젝트</a>
					  </li>
					</ul>
					
					<div class="tab-content" id="myTabContent">
			   	   	   <div>
							<div class="member-basic-wrap">

								<table class="prolist-table m-top-20">
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
						<input type="button" class="btn btn-gray m-right-10 p-btn-1030" value="수정" onclick="updateHandler();"/> 
						<input type="button" class="btn btn-gray m-right-10 p-btn-1030" value="탈퇴" onclick="deleteHandler();"/> 
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
				</div>
			</div>
		</div>
	</div>
</div>