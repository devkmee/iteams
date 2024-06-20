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

<!-- 							<form:form commandName="member" method="post" enctype="multipart/form-data" id="memberForm"> -->
								<form action="${cPath}/outs/member/clientEdit.do" method="post">
								
								
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
						                    	<input type="text" name="clientName" required value="${clientInfo.clientName }" />
						                    </td>
										</tr>
						                <tr>
						                    <th>회사 주소</th>
						                    <td>
						                    	<input type="text" name="clientAdd" required value="${clientInfo.clientAdd }" />
						                    </td>
						                 </tr>
						                  <tr>
						                     <th>CEO명</th>
						                     <td>
						                     	<input type="text" name="clientCeo" required value="${clientInfo.clientCeo }" />
						                     </td>
						                 </tr>
						                <tr>
						                    <th>설립일</th>
						                    <td>
						                    	<span>${clientInfo.clientAnni }</span>
						                    </td>
						                </tr>
										<tr>
											<th>매출액</th>
											<td>
												<input type="text" name="clientSales" required value="${clientInfo.clientSales }" />
						                    </td>
										</tr>
										<tr>
											<th>직원수</th>
											<td>
												<input type="text" name="clientScale" required value="${clientInfo.clientScale }" />
						                    </td>
										</tr>
										<tr>
											<th>회사 이메일 주소</th>
											<td>
												${clientInfo.memMail }
						                    </td>
										</tr>
										<tr>
											<th>회사 전화번호</th>
											<td>
												<input type="text" name="memTel" required value="${clientInfo.memTel }" />
						                    </td>
										</tr>
										<tr>
											<th>회사 홈페이지</th>
											<td>
												<input type="text" name="clientHomepage" required value="${clientInfo.clientHomepage }" />
						                    </td>
										</tr>	
										<tr>
											<th>담당자명/직급</th>
											<td>
												<input type="text" name="managerName" required value="${clientInfo.managerName }" />
												<input type="text" name="managerRank" required value="${clientInfo.managerRank }" />
						                    </td>
										</tr>	
									</tbody>
								
								</table>
								
								<div class="join-btn-wrap">
									<input type="submit" class="btn btn-gray m-right-10 p-btn-1030" 
											value="저장" /> 
								</div>
								</form>
<!-- 							</form:form> -->
					
								
							</div>
					    </div>

				</div>
			</div>
		</div>
	</div>
</div>