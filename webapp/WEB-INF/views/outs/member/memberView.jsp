<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/hireList.css">
<div class="sub-wrap">
	<div class="sub-inner-box">
		<div class="sub-inner-txt"">
			<span class="sub-txt">회원정보 조회<br>
				<span class="project-txt-lighter">회원정보 조회</span>
			</span>
		</div>
		<div class="main-outline-box-img m-right-10">
			<img alt="" src="/iteams/resources/images/hireList-img.png">
		</div>
	</div>
</div>
<section class="section-wrap">
	<div class="hirelist-wrap">
		<div class="table-wrap">
			<table class="table">
			 	<c:if test="${member.memRole eq 'CLIENT'}">
			 	<tbody>
			 		<tr>
				      <th scope="col">아이디</th>
				      <td>${member.memId }</td>
				    </tr>
				    <tr>
				      <th scope="col">회사명</th>
				      <td>${member.clientName }</td>
				    </tr>
				    <tr>
				      <th scope="col">사업자 등록번호</th>
				      <td>${member.businessNum }</td>
				    </tr>
				    <tr>
				      <th scope="col">회사 주소</th>
				      <td>${member.clientAdd }</td>
				    </tr>
				    <tr>
				      <th scope="col">CEO명</th>
				      <td>${member.clientCeo }</td>
				    </tr>
				    <tr>
				      <th scope="col">설립일</th>
				      <td>${member.clientAnni }</td>
				    </tr>
				    <tr>
				      <th scope="col">매출액</th>
				      <td>${member.clientSales }</td>
				    </tr>
				    <tr>
				      <th scope="col">직원수</th>
				      <td>${member.clientScale }</td>
				    </tr>
				    <tr>
				      <th scope="col">홈페이지</th>
				      <td>${member.memTel }</td>
				    </tr>
			   	 </tbody>
			   	 <tbody>
				     <tr>
				      <th scope="col">담당자명/직급</th>
				      <td>${member.managerName } / ${member.managerRank }</td>
				    </tr>
				    <tr>
				      <th scope="col">이메일</th>
				      <td>${member.memMail }</td>
				    </tr>
				    <tr>
				      <th scope="col">전화번호</th>
				      <td>${member.clientHomepage }</td>
				    </tr>
				  </tbody>
			 	</c:if>
			 	
			 	<c:if test="${member.memRole eq 'DEV'}">
			 		<tbody>
			 			<tbody>
							<tr>
								<th>이름</th>
								<td>
			                    	${member.devName }
			                    </td>
							</tr>
							</tr>
							<tr>
			                    <th>출생년도</th>
			                    <td>
			                    	${member.devBir }
			                    </td>
			                </tr>
			                <tr>
			                    <th>아이디</th>
			                    <td>
			                    	${member.devId }
			                    </td>
			                 </tr>
			                  <tr>
			                     <th>이메일 주소</th>
			                     <td>
			                        ${member.memMail }
			                     </td>
			                 </tr>
			                <tr>
			                    <th>휴대폰 번호</th>
			                    <td>
			                    	${member.memTel }
			                    </td>
			                </tr>
						</tbody>
							<tbody>
							<tr>
						      <th scope="col">기술스택</th>
						      <c:choose>
							      	<c:when test="${empty member.devTechValue }">
							      	<td>#${member.devTech }</td>
							      </c:when>
							      <c:otherwise>
							      	<td>
								      	<c:forEach items="${member.devTechValue }" var="tech">
								      		#${tech } 
								      	</c:forEach>
							      	</td>
							      </c:otherwise>
						      </c:choose>
						    </tr>
							<tr>
								<th>직무</th>
								<td>
									${member.devJob }
			                    </td>
							</tr>
							<tr>
								<th>경력</th>
								<td>
									${member.devCareer }
			                    </td>
							</tr>
							<tr>
								<th>학력</th>
								<td>
									${member.devEdu }
			                    </td>
							</tr>
							<tr>
								<th>포트폴리오</th>
								<td>
									<a>${member.devPort}</a>
			                    </td>
							</tr>	
						</tbody>
			 		</tbody>
			 	</c:if>
			 	
			  </tbody>
			</table>
		</div>
	</div>
</section>

