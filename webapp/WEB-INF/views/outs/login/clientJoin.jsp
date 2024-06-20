<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/join.css">    
<script src="${pageContext.request.contextPath}/resources/js/join/join.js"></script>    
<script src="${pageContext.request.contextPath}/resources/js/join/address.js"></script>    
<div>
	<div class="login-content-wrap">
		<div class="join-member-wrap">
		<h3>기업 회원가입</h3>
			<div class="member-basic-wrap">
				<form:form commandName="member" method="post" enctype="multipart/form-data" id="memberForm">
					<table class="join-table">
					<tbody>
						 <tr>
		                    <th>아이디</th>
		                    <td>
		                        <input type="text" name="memId" style="width: 300px;">
		                        <a href="#a" class="btn btn-primary" id="checkBtn">아이디 중복확인</a>
								<form:errors path="memId" element="span" cssClass="error" />
		                        <span class="info">아이디 입력 후 '아이디 중복확인'버튼을 클릭해주세요.</span>
		                    </td>
		                 </tr>
		                  <tr>
		                      <th>비밀번호</th>
		                      <td>
		                          <input type="password" name="memPass" style="width: 300px;">
		                          <span class="info">비밀번호는 4~10자리로 입력해주시기 바랍니다.</span>
		                      </td>
		                  </tr>
		                  <tr>
		                      <th>비밀번호확인</th>
		                      <td>
		                          <input type="password" name="memPassCheck" style="width: 300px;">
		                      </td>
		                 </tr>
						<tr>
							<th>회사명</th>
							<td>
		                    <input type="text" name="devName" value="${member.memName }" style="width: 300px;">
		                    <form:errors path="devName" element="span" cssClass="error" />
		                    </td>
						</tr>
						<tr>
							<th>회사 주소</th>
							<td>
								<input type="text" id="sample4_postcode" placeholder="우편번호" style="width: 300px;">
								<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" style="margin-bottom: 4px;"><br>
								<input type="text" id="sample4_roadAddress" placeholder="도로명주소" style="width: 300px;">
								<input type="text" id="sample4_jibunAddress" placeholder="지번주소">
								<span id="guide" style="color:#999;display:none"></span>
								<input type="text" id="sample4_detailAddress" placeholder="상세주소">
								<input type="text" id="sample4_extraAddress" placeholder="참고항목">
							</td>
						</tr>
						<tr>
		                    <th>CEO명</th>
		                    <td>
		                        <input type="text" name="devBir" style="width: 300px;">
		                    </td>
		                </tr>
		                <tr>
		                    <th>회사전화번호</th>
		                    <td>
		                        <input type="text" name="devBir" style="width: 300px;">
		                    </td>
		                </tr>
		                <tr>
		                    <th>설립일</th>
		                    <td>
		                        <input type="text" name="devBir" style="width: 300px;">
		                    </td>
		                </tr>
		                 <tr>
		                    <th>매출액</th>
		                    <td>
		                        <input type="text" name="devBir" style="width: 300px;">
		                    </td>
		                </tr>
		                <tr>
		                    <th>직원수</th>
		                    <td>
		                        <input type="text" name="devBir" style="width: 300px;">
		                    </td>
		                </tr>
		                  <tr>
		                     <th>회사 이메일 주소</th>
		                     <td class="client-address">
		                         <input type="text" name="memMailList" style="width: 200px;">
		                         @
		                         <input type="hidden" name="memMailList" value="@">
		                         <input type="text" name="memMailList" style="width: 190px;">
		                         <select>
		                             <option value>직접입력</option>
		                             <option value="naver.com">naver.com</option>
		                             <option value="gamil.com">gmail.com</option>
		                         </select>
		                     </td>
		                 </tr>
		                <tr>
		                    <th>회사 전화번호</th>
		                    <td>
		                        <input type="text" name="memTel" placeholder='- 제외한 숫자만 적어주세요.' value="${member.memHp }" style="width: 220px;">
		                        <form:errors path="memTel" element="span" cssClass="error" />
		                    </td>
		                </tr>
		                  <tr>
		                    <th>담당자명/직급</th>
		                    <td>
		                         <input type="text" name="devBir" style="width: 120px;">&nbsp/&nbsp
		                         <input type="text" name="devBir" style="width: 120px;">
		                    </td>
		                </tr>
					</tbody>

					</table>
				<div class="join-btn-wrap">
					<button type="submit" class="btn btn-primary m-right-10 p-btn-1030">가입</button>
					<button type="reset" class="btn btn-secondary p-btn-1030">취소</button>
				</div>
				</form:form>
				<c:if test="${command eq 'INSERT' }">
					<script type="text/javascript" src="${cPath }/resources/js/join/joinInsert.js" ></script>
				</c:if>
			</div>
		</div>
	</div>
</div>