<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/join.css">
<script src="${pageContext.request.contextPath}/resources/js/join/join.js"></script> 
<script src="${pageContext.request.contextPath}/resources/js/join/address.js"></script> 
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1e797e4b2640b2367d333de97e65700d&libraries=services"></script>     
<div class="login-content-wrap">
	<div class="join-tab-wrap">
			<div class="tab-wrap">
				<ul class="nav nav-tabs m-bottom-20" id="myTab" role="tablist">
				  <li class="nav-item m-right-2" role="presentation">
				    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">개인 회원가입</a>
				  </li>
				  <li class="nav-item" role="presentation">
				    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">기업회원 가입</a>
				  </li>
				</ul>
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
			<div class="mypage-info-wrap">
				<div class="join-member-wrap">
				<h4>개인 회원가입</h4>
					<div class="member-basic-wrap">
						<form:form commandName="member" method="post" enctype="multipart/form-data" id="memberForm">
							<input name="memRole" hidden="true" value="DEV">
							<table class="join-table">
							<tbody>
								<tr>
									<th>이름</th>
									<td>
				                    <input type="text" name="devName" value="${member.devName }" style="width: 300px;">
				                    <form:errors path="devName" cssClass="error" element="span" ></form:errors>
				                    </td>
								</tr>
								<tr>
									<th>프로필 이미지</th>
									<td class="file-wrap">
				                    <input type="file" name="profileImage" accept="image/*" style="width: 400px;"/>
				                    </td>
								</tr>
								<tr>
				                    <th>출생년도</th>
				                    <td>
				                        <input type="text" name="devBir" style="width: 300px;">
				                        <form:errors path="devBir" cssClass="error" element="span" ></form:errors>
				                    </td>
				                </tr>
				                <tr>
				                    <th>아이디</th>
				                    <td>
				                        <input id="memId" type="text" name="memId" style="width: 300px;" data-check_id="false">
				                        <a href="#a" class="btn btn-primary" id="checkBtn">아이디 중복확인</a>
				                        <br /><form:errors path="memId" cssClass="error" element="span" ></form:errors>
				                        <span id="isSuccess" class="info">아이디 입력 후 '아이디 중복확인'버튼을 클릭해주세요.</span>
				                    </td>
				                 </tr>
				                  <tr>
				                      <th>비밀번호</th>
				                      <td>
				                          <input type="password" name="memPass" style="width: 300px;">
				                          <br /><span class="info">비밀번호는 숫자, 문자, 특수문자 1개 이상 길이는 8~16자 이하, 특수문자는 ~!@#$%^&*()+|= 만 허용.</span>
				                          <br /><form:errors path="memPass" cssClass="error" element="span" ></form:errors>
				                      </td>
				                  </tr>
				                  <tr>
				                      <th>비밀번호확인</th>
				                      <td>
				                          <input type="password" name="memPassCheck" style="width: 300px;">
				                      </td>
				                 </tr>
				                  <tr>
				                     <th>이메일 주소</th>
				                     <td>
				                         <input type="text" name="memMailPrefix" style="width: 200px;">
				                         @
				                         <input type="hidden" value="@">
				                         <input id="memMailInput" type="text" name="memMailSuffix" style="width: 190px;">
				                         <select id="memMailCombo" onchange="onChangeMemMail()">
				                             <option value="">직접입력</option>
				                             <option value="naver.com">naver.com</option>
				                             <option value="gmail.com">gmail.com</option>
				                         </select>
				                         <br /><form:errors path="memMailPrefix" cssClass="error" element="span" ></form:errors>
				                        <br /> <form:errors path="memMailSuffix" cssClass="error" element="span" ></form:errors>
				                     </td>
				                 </tr>
				                <tr>
				                    <th>휴대폰 번호</th>
				                    <td>
				                        <input type="text" name="memTel" placeholder='- 제외한 숫자만 적어주세요.' value="${member.memTel }" style="width: 220px;">
				                        <form:errors path="memTel" cssClass="error" element="span" ></form:errors>
				                    </td>
				                </tr>
							</tbody>
								<tbody>
								<tr>
									<th>기술스택</th>
									<td>
				                    <input id="skill" type="text" name="devTech" style="width: 400px;">
				                    <form:errors path="devTech" cssClass="error" element="span" ></form:errors>
				                    </td>
								</tr>
								<tr>
									<th>직무</th>
									<td>
									<select name="devJob">
										<option value="프론트엔드">프론트엔드</option>
										<option value="서버/백엔드">서버/백엔드</option>
										<option value="데이터 엔지니어">데이터 엔지니어</option>
										<option value="SW/솔루션">SW/솔루션</option>
										<option value="보안">보안</option>
										<option value="앱개발">앱개발</option>
										<option value="인공지능/머신러닝">인공지능/머신러닝</option>
										<option value="게임클라이언트">게임클라이언트</option>
										<option value="기타">기타</option>
									</select>
									<form:errors path="devJob" cssClass="error" element="span" ></form:errors>
				                    </td>
								</tr>
								<tr>
									<th>경력</th>
									<td>
										<select name="devCareer">
											<option value="신입">없음</option>
											<option value="1년~2년">1년~2년</option>
											<option value="2년~3년">2년~3년</option>
											<option value="3년~5년">3년~4년</option>
											<option value="5년이상">5년 이상</option>
										</select>
										<form:errors path="devCareer" cssClass="error" element="span" ></form:errors>
				                    </td>
								</tr>
								<tr>
									<th>학력</th>
									<td>
				                    <input type="text" name="devEdu" placeholder='학교' style="width: 200px;">
				                    <input type="text" name="devMajor" placeholder='전공' style="width: 200px;">
				                    </td>
								</tr>
								<tr>
									<th>포트폴리오 링크</th>
									<td>
				                    <input type="text" name="devPort" style="width: 400px;">
				                    <form:errors path="devPort" cssClass="error" element="span" ></form:errors>
				                    </td>
								</tr>	
								<tr>
									<th>첨부파일</th>
									<td class="file-wrap">
				                    <input type="file" name="attachFiles" accept="image/*" style="width: 400px;"/>
				                    </td>
								</tr>	
							</tbody>
							</table>
						<div class="join-btn-wrap">
							<button type="submit" class="btn btn-primary m-right-10 p-btn-1030">가입</button>
							<button type="reset" class="btn btn-secondary p-btn-1030">취소</button>
						</div>
						</form:form>
					</div>
				</div>	
		
			</div>
	 </div>
				  
	<!-- 		  기업회원 가입			 -->
	  <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
					<div class="join-member-wrap">
		<h4>기업 회원가입</h4>
			<div class="member-basic-wrap">
				<form:form class="cliForm" action="${cPath}/outs/login/cliJoin.do" commandName="member" method="post" id="memberForm">
					<input name="memRole" hidden="true" value="CLIENT">
					<input id="salesInput" type="hidden" name="clientSales" />
					<table class="join-table">
					<tbody>
						 <tr>
		                    <th>아이디</th>
		                    <td>
		                        <input id="cliId" type="text" name="memId" style="width: 300px;">
		                        <a href="#a" class="btn btn-primary" id="checkBtn2">아이디 중복확인</a>
								<br /><form:errors path="memId" element="span" cssClass="error" />
		                        <span id="isSuccess2" class="info">아이디 입력 후 '아이디 중복확인'버튼을 클릭해주세요.</span>
		                    </td>
		                 </tr>
		                  <tr>
		                      <th>비밀번호</th>
		                      <td>
		                          <input type="password" name="memPass" style="width: 300px;">
		                          <br /><span class="info">비밀번호는 숫자, 문자, 특수문자 1개 이상 길이는 8~16자 이하, 특수문자는 ~!@#$%^&*()+|= 만 허용.</span>
		                          <br /><form:errors path="memPass" cssClass="error" element="span" ></form:errors>
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
		                    <input type="text" name="clientName" value="${member.clientName }" style="width: 300px;">
		                    <form:errors path="clientName" element="span" cssClass="error" />
		                    </td>
						</tr>
						<tr>
							<th>회사 주소</th>
							<td>
								<input type="text" id="sample6_postcode" name="clientZip" placeholder="우편번호">
								<input type="button" class="m-bottom-5" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" ><br>
								<input type="text" id="sample6_address" name="clientAdd" placeholder="주소" style="width: 300px;">
								<input type="text" id="sample6_detailAddress" name="clientAddret" placeholder="상세주소" style="width: 300px;">
								<br /><form:errors path="clientZip" cssClass="error" element="span" ></form:errors>
								<br /><form:errors path="clientAdd" cssClass="error" element="span" ></form:errors>
								<br /><form:errors path="clientAddret" cssClass="error" element="span" ></form:errors>
							</td>
						</tr>
						<tr>
		                    <th>CEO명</th>
		                    <td>
		                        <input type="text" name="clientCeo" style="width: 300px;">
		                        <form:errors path="clientCeo" cssClass="error" element="span" ></form:errors>
		                    </td>
		                </tr>
		                <tr>
		                    <th>사업자번호</th>
		                    <td>
		                        <input type="text"  id="businessNumInput" name="businessNum" style="width: 300px;">
		                        <input type="button" id="businessNumCheckBtn" value="인증 확인"/>
		                        <form:errors path="businessNum" cssClass="error" element="span" ></form:errors>
		                    </td>
		                </tr>
		                <tr>
		                    <th>설립일</th>
		                    <td>
		                        <input type="date" name="clientAnni" style="width: 300px;">
		                        <form:errors path="clientAnni" cssClass="error" element="span" ></form:errors>
		                    </td>
		                </tr>
		                 <tr>
		                    <th>매출액</th>
		                    <td>
		                        <input id="sales" type="text" name="" style="width: 300px;">
		                        <span id="fancySales"></span>
		                        <form:errors path="clientSales" cssClass="error" element="span" ></form:errors>
		                    </td>
		                </tr>
		                <tr>
		                    <th>직원수</th>
		                    <td>
		                        <input type="text" name="clientScale" style="width: 300px;">
		                        <form:errors path="clientScale" cssClass="error" element="span" ></form:errors>
		                    </td>
		                </tr>
	                  	<tr>
		                     <th>회사 이메일 주소</th>
		                     <td class="client-address">
		                         <input type="text" name="memMailPrefix" style="width: 200px;">
		                         @
		                         <input type="hidden" value="@">
		                         <input  id="memMailInput2" type="text" name="memMailSuffix" style="width: 190px;">
		                         <select id="memMailCombo2" onchange="onChangeMemMail()">
		                             <option value="">직접입력</option>
		                             <option value="naver.com">naver.com</option>
		                             <option value="gmail.com">gmail.com</option>
		                         </select>
		                         <br /><form:errors path="memMailPrefix" cssClass="error" element="span" ></form:errors>
		                         <br /><form:errors path="memMailSuffix" cssClass="error" element="span" ></form:errors>
		                     </td>
		                 </tr>
		                <tr>
		                    <th>회사 전화번호</th>
		                    <td>
		                        <input type="text" name="memTel" placeholder='- 제외한 숫자만 적어주세요.' value="${member.memTel }" style="width: 220px;">
		                        <form:errors path="memTel" element="span" cssClass="error" />
		                    </td>
		                </tr>
		                  <tr>
		                    <th>담당자명/직급</th>
		                    <td>
		                         <input type="text" name="managerName" style="width: 120px;">&nbsp/&nbsp
		                         <input type="text" name="managerRank" style="width: 120px;">
		                        <br /><form:errors path="managerName" cssClass="error" element="span" ></form:errors>
		                        <form:errors path="managerRank" cssClass="error" element="span" ></form:errors>
		                    </td>
		                </tr>
					</tbody>
					</table>
					<input type="hidden" id ="clientAddx" name="clientAddx">
					<input type="hidden" id ="clientAddy" name="clientAddy">
					<div class="join-btn-wrap">
						<button type="submit" class="btn btn-primary m-right-10 p-btn-1030">가입</button>
						<button type="reset" class="btn btn-secondary p-btn-1030">취소</button>
					</div>
					</form:form>
							<c:if test="${command eq 'INSERT' }">
							</c:if>
						</div>
					</div>
	 			 </div>
			</div>
		</div>
	</div>
</div>

<script>

String.prototype.replaceAll = function(org, dest) {
    return this.split(org).join(dest);
}

$("#sales").on("change keyup paste", function() {
	let value = $(this).val()
	
	value = value.replaceAll("," ,"");
	
	let devideValue = parseInt(value / 1000000);
	if(devideValue >= 1) {
		$("#fancySales").text(new Intl.NumberFormat().format(devideValue) + " 백만원")
	} else {
		$("#fancySales").text("")
	}
	
	let wonValue = new Intl.NumberFormat().format(value)
	
	$(this).val(wonValue)
	
	
	$("#salesInput").val(value)
})





function checkCorporateRegiNumber(number){
	var numberMap = number.replace(/-/gi, '').split('').map(function (d){
		return parseInt(d, 10);
	});
	
	if(numberMap.length == 10){
		var keyArr = [1, 3, 7, 1, 3, 7, 1, 3, 5];
		var chk = 0;
		
		keyArr.forEach(function(d, i){
			chk += d * numberMap[i];
		});
		
		chk += parseInt((keyArr[8] * numberMap[8])/ 10, 10);
		console.log(chk);
		return Math.floor(numberMap[9]) === ( (10 - (chk % 10) ) % 10);
	}
	
	return false;
}

let flag = false;

	$("#businessNumCheckBtn").on("click", function() {
		let value = $("#businessNumInput").val()
		
		let checkValid = checkCorporateRegiNumber(value)
		console.log(checkValid)
		
		if(!checkValid) {
			alert("유효하지 않은 사업자 등록번호입니다.")
			return false;
		}
		
		alert("인증 되었습니다.")
		flag = true;
		
	})

	
	$(".cliForm").on("submit", function() {
		if(!flag) {
			alert("사업자 등록번호를 인증하세요.")
			return false;
		}
		
		return true;
	})
</script>
