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
				<span class="sub-mypage-txt">쪽지함 ></span>
			</div>
			
			<div class="flex-end">
				<input type="button" class="btn btn-gray" value="쪽지쓰기"
						id="sendMessage" /> 
				
				<form id="sendForm" action="${cPath}/outs/message/sendMessage.do" method="get"> 
					<input type="hidden">
				</form>
			</div>
			
			<script>
				$("#sendMessage").on("click", function(){
					sendForm.submit(); 
				})
			</script>
			
				<div class="tab-wrap">
					<ul class="nav nav-tabs m-bottom-20" id="myTab" role="tablist">
					  <li class="nav-item3 m-right-2" role="presentation">
					    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">발신함</a>
					  </li>
					  <li class="nav-item3  m-right-2" role="presentation">
					    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">수신함</a>
					  </li>
					</ul>
					
					<div class="tab-content" id="myTabContent">
					
			   	   	   <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
							<div class="member-basic-wrap">
								<div class="project-wrap-txt m-bottom-10">발신함 &gt;</div>
							
								<div class="table-list">
									<table class="table">
									  <thead>
									    <tr>
									      <th scope="col">번호</th>
									      <th scope="col">제목</th>
									      <th scope="col">받는사람</th>
									      <th scope="col">발신일</th>
									    </tr>
									  </thead>
									  <tbody>
									    <c:set var="send" value="${sendList.dataList }"></c:set>
										  <c:choose>
										  	<c:when test="${empty send }">
										  		<tr>
											      <td scope="row" colspan="4">
											      	보낸 쪽지가 없습니다.
											      </td>
											    </tr>
										  	</c:when>
										  	<c:otherwise>
										  		<c:forEach items="${send }" var="sss">
										  			<c:url value="/outs/message/sendView.do" var="selectURL">
														<c:param name="what" value="${sss.msgNum }"></c:param>
													</c:url>
										  			<tr class="linkBtn cursor" data-gopage="${selectURL }">
												      <td scope="row">
												      	<div class="profile-wrap">
													      	<div class="profile-name">
													      		<div>${sss.rnum }</div>
													      	</div>
												      	</div>
												      </td>
												      <td>${sss.msgTitle }</td>
												      <td>${sss.msgReceive }</td>
												      <td>${sss.sendDate }</td>
												    </tr>
										  		</c:forEach>
										  	</c:otherwise>
										  </c:choose>
									  </tbody>
									</table>
								</div>
								
							<c:if test="${not empty send}">
								
								<div class="paging-wrap">
								<div id="pagingArea"> 
										<div id="pagingArea"> ${sendList.pagingHTML} </div>	
									</div>	
								</div>							
							</c:if>
							</div>
					    </div>
					    
					    
					    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
							<div class="m-bottom-10">수신함 &gt;</div>
							<div class="project-wrap-txt m-bottom-10">
								<div class="table-list">
									<table class="table">
									  <thead>
									    <tr>
									      <th scope="col">번호</th>
									      <th scope="col">제목</th>
									      <th scope="col">보낸사람</th>
									      <th scope="col">수신일</th>
									      <th scope="col">읽음여부</th>									      
									    </tr>
									  </thead>
									  <tbody>
									    <c:set var="receive" value="${receiveList.dataList }"></c:set>
										  <c:choose>
										  	<c:when test="${empty receive }">
										  		<tr>
											      <td scope="row" colspan="4">
											      	받은 쪽지가 없습니다.
											      </td>
											    </tr>
										  	</c:when>
										  	<c:otherwise>
										  		<c:forEach items="${receive }" var="rrr">
										  			<c:url value="/outs/message/receiveView.do" var="selectURL">
														<c:param name="what" value="${rrr.msgNum }"></c:param>
													</c:url>
										  			<tr class="linkBtn cursor" data-gopage="${selectURL }">
												      <td scope="row">
												      	<div class="profile-wrap">
													      	<div class="profile-name">
													      		<div>${rrr.rnum }</div>
													      	</div>
												      	</div>
												      </td>
												      <td>${rrr.msgTitle }</td>
												      <td>${rrr.memId }</td>
												      <td>${rrr.receiveDate }</td>
												      <c:if test="${rrr.receiveNy eq 'Y'}">
												      	<td>확인</td>
												      </c:if>
												      <c:if test="${rrr.receiveNy eq 'N'}">
												      	<td>미확인</td>
												      </c:if>
												    </tr>
										  		</c:forEach>
										  	</c:otherwise>
										  </c:choose>
									  </tbody>
									</table>
								</div>
						
						<c:if test="${not empty receive}">
							<div class="paging-wrap">
								<div id="pagingArea"> 	<!-- id pagingArea가 중복되는데 괜춘한건지 모르게씀 ㅠㅠ -->
									<div class="paging-wrap">
									   <div id="pagingArea"> ${receiveList.pagingHTML } </div>	
									</div>
								</div>	
							</div>
						</c:if>
						<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
						
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>