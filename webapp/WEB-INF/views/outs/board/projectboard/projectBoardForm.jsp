<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="${pageContext.request.contextPath}/resources/js/join/join.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/join.css">
<script type="text/javascript" src="${cPath }/resources/js/ckeditor/ckeditor.js"></script>

<div>
	<div class="sub-wrap">
		<div class="sub-inner-box">
			<div class="sub-inner-txt">
				<span class="sub-txt">프로젝트 공고<br>
					<span class="project-txt-lighter">커리어 관리가 즐거워지고, 오늘보다 더 나은 내일을 꿈꿀 수 있는 곳<br>아이팀즈입니다.</span>
				</span>
			</div>
			<div class="main-outline-box-img m-right-10">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/project-img.png">
			</div>
		</div>
	</div>
	<section class="section-wrap">
		<div class="project-list-title m-bottom-10 m-top-30">
			<span>프로젝트 입력 </span> 
		</div>

	<div class="writing-view-wrap">
	<form:form id="inputForm" modelAttribute="project" method="post" enctype="multipart/form-data">
		<input type="hidden" name="boNum" value="${project.boNum}" />
		<input type="hidden" name="writer" value="${project.memId}" />
		<table class="table border-table">
			<tr>
				<th>제목</th>
				<td>
					<input type="text" class="write-input" style="width: 100%;" name="boTitle" value="${project.boTitle }" />
					<form:errors path="boTitle" cssClass="error" element="span"></form:errors>
				</td>
			</tr>
			<tr>
				<th>글내용</th>
				<td>
	<!-- 				<input type="hidden" id="boContentVal" name="boContent"/> -->
	<%-- 				<textarea rows="5" cols="100" name="boContent" id="boContent">${project.boContent }</textarea> --%>
					<textarea class="editable write-input" id="boContent" name="boContent">
						<c:if test="${not empty project.boContent}">
							${project.boContent}
						</c:if>
						<c:if test="${empty project.boContent}">
							<div id="normal">
							<div class="prolist-group">
								<h5>📌주요업무</h5>
								<ul>
									<li></li>
								</ul>
							</div>
							<div class="prolist-group">
								<h5>📌자격요건</h5>
								<ul>
									<li></li>
								</ul>
							</div>
							<div class="prolist-group">
								<h5>📌우대사항</h5>
								<ul>
									<li></li>
								</ul>
							</div>
							<div class="prolist-group">
								<h5>🔎복지 및 혜택</h5>
								<ul>
									<li></li>
								</ul>
							</div>
							</div>
							<div id="test">
							<div class="prolist-group">
								<h5>📌주요업무</h5>
								<ul>
									<li></li>
								</ul>
							</div>
							<div class="prolist-group">
								<h5>📌자격요건</h5>
								<ul>
									<li></li>
								</ul>
							</div>
							<div class="prolist-group">
								<h5>📌우대사항</h5>
								<ul>
									<li></li>
								</ul>
							</div>
							<div class="prolist-group">
								<h5>🔎복지 및 혜택</h5>
								<ul>
									<li></li>
								</ul>
							</div>
							</div>
						</c:if>
					</textarea>
					<form:errors path="boContent" cssClass="error" element="span"></form:errors>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td id="fileArea">
					<div>
						<input type="file" name="attachFiles" multiple />
						<input type="button" value="추가" id="plus" class="ctlBtn write-input"/>
						<input type="button" value="제거" id="minus" class="ctlBtn write-input"/>
					</div>
				</td>
			</tr>
			<c:if test="${project.crudToken eq 'UPDATE'}">
			<tr>
				<th><label for="exampleFormControlInput1">기존첨부파일</label></th>
				<td>
				  <div class="form-group" id="" name="">
				   		<div name="">
					   		
							<c:if test="${not empty project.attachList }">
								<c:forEach items="${project.attachList }" var="attach">
									<span data-atch="${attach.attNo }">
										${attach.attachOrigin }
										<input type="button" value="삭제" class="atchDelBtn"/>
									</span>
								</c:forEach>
							</c:if>
							<c:if test="${empty project.attachList }">
								<span>첨부파일이 없습니다.</span>
							</c:if>
						</div>
				   </div>			
				</td>
			</tr>
			</c:if>
			<tr>
				<th>프로젝트명</th>
				<td>
					<input type="text" name="projectName" class="write-input" style="width: 100%;" value="${project.projectName }" />
					<form:errors path="projectName" cssClass="error" element="span"></form:errors>
				</td>
			</tr>
			<tr>
				<th>프로젝트규모</th>
				<td>
					<form:select name="projectScale" path="projectScale" class="write-input">
						<option value="소규모">소규모</option>
						<option value="중규모">중규모</option>
						<option value="대규모">대규모</option>
					</form:select>
					<form:errors path="projectScale" cssClass="error" element="span"></form:errors>
				</td>
			</tr>
			<tr>
				<th>예상단가</th>
				<td><input id="price" class="write-input" type="text" name="" required value="${project.projectPrice }" />
					<input id="priceInput" class="write-input" style="width: 100%;" type="hidden" name="projectPrice" />
					<span id="fancyPrice"></span>
					<form:errors path="projectPrice" cssClass="error" element="span"></form:errors>
				</td>
			</tr>
			<tr>
				<th>요구사항</th>
				<td>
					<textarea rows="5" class="write-input" cols="100" style="width: 100%;" name="projectReq" id="projectReq">${project.projectReq }</textarea>
					
				</td>
			</tr>
			<tr>
				<th>요구기술스택</th>
				<td><input id="skill" type="text" class="write-input" style="width: 100%;" name="projectTech"  value="${project.projectTech }" />
					<form:errors path="projectTech" cssClass="error" element="span"></form:errors>
				</td>
			</tr>
			<tr>
				<th>요구직무</th>
				<td><form:select name="projectJob" class="write-input" path="projectJob">
						<option value="프론트엔드">프론트엔드</option>
						<option value="서버/백엔드">서버/백엔드</option>
						<option value="데이터 엔지니어">데이터 엔지니어</option>
						<option value="SW/솔루션">SW/솔루션</option>
						<option value="보안">보안</option>
						<option value="앱개발">앱개발</option>
						<option value="인공지능/머신러닝">인공지능/머신러닝</option>
						<option value="게임클라이언트">게임클라이언트</option>
						<option value="기타">기타</option>
					</form:select>
					<form:errors path="projectJob" cssClass="error" element="span"></form:errors>
				</td>
			</tr>
			<tr>
				<th>공고기한</th>
				<td><form:input path="limitDate" type="date" name="limitDate" class="write-input" value="${project.limitDate}" />
					<form:errors path="limitDate" cssClass="error" element="span"></form:errors>
				</td>
			</tr>
			<tr>
				<th>모집인원</th>
				<td><input type="number" name="projectRecruit" class="write-input" value="${project.projectRecruit }" />
					<form:errors path="projectRecruit" cssClass="error" element="span"></form:errors>
				</td>
			</tr>
			<tr>
				<th>상주여부</th>
				<td>
					<div class="flex-start">
						<div class="m-right-10">
					 		<input type='radio' class="write-input" name='officeNy' value='Y' checked="checked"/>
					 		예
						</div>
						<div>
							<input type='radio' class="write-input" name='officeNy' value='N' />아니오
							<form:errors path="officeNy" cssClass="error" element="span"></form:errors>
						</div>	
					</div>
				</td>
			</tr>
		</table>
		<div class="flex-center">
			<input type="submit" class="btn btn-gray m-right-5" value="저장"/>
			<input type="reset" class="btn btn-gray" value="취소" />
		</div>
	</form:form>
	</div>

	</section>
</div>

<div class="flex-center">
<input type="button" value="시연용" id="testInputBtn" />
</div>
<script type="text/javascript">

$("#test").hide();

	CKEDITOR.replace("boContent", {
		filebrowserImageUploadUrl:"${cPath}/common/imageUpload.do?type=Images"
	});
	
	
	
$("#testInputBtn").on("click", function() {
	$("[name='boTitle']").val("웹/모바일 서비스 개발자");
	$("[name='projectName']").val("웹/모바일 서비스 개발자");
	$("#price").val("250000000")
	$("[name='projectRecruit']").val("30")
	$("[name='limitDate']").val("2021-12-22")
	$("#projectReq").text("위하이어(B2B SaaS) or 커리어리 or 퍼블리 멤버십 웹 / 앱 개발 \n확장성/생산성/안정성을 고려하여 서비스 주요 피처(API, Web, App 포함) 개발 \n사용자 문제 해결을 위한 신규 피처(feature) 개발에 관한 논의에 참여")
	$("#normal").hide();
	$("#test").show();
	
})	
	
	$(".atchDelBtn").on("click", function() {
		
		let valid = confirm("해당 첨부파일을 삭제하시겠습니까?")
		if(!valid) return
		
		let span = $(this).closest("span")
		let attNo = span.data("atch")
		$.ajax({
			url : "${cPath}/outs/attachDel.do",
			data : {"what" : attNo},
			method : "post",
			dataType : "json",
			success : function(resp) {
				if(resp.message == "success") {
					alert("첨부파일을 삭제했습니다.")
					span.hide()
				} else {
					alert("첨부파일 삭제에 실패했습니다.")
				}
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		})
		
	})
	
	$("#inputForm").on("submit", function() {
		var last = $("#skill").val().slice(-1)
		if(last === ",") {
			var temp =  $("#skill").val()
			var value = temp.substring(0, (temp.length - 1))
			$("#skill").val(value)
		}
		
		let contentValue = $("#boContent").text();
		console.log(contentValue)
// 		$("#boContentVal").val(contentValue);
		
		return true;
	})
	
	let fileArea = $("#fileArea").on("click", ".ctlBtn", function(){
		let id = this.id;
		console.log(id);
		let divTag = $(this).closest("div");
		if(id == 'plus'){
			let clone = divTag.clone();
			$(clone).find(":input[name]").val("");
			divTag.after(clone);
		}else{
			let divs = fileArea.find("div");
			if(divs.length>1)
				divTag.remove();
		}
	});
	
	
	String.prototype.replaceAll = function(org, dest) {
	    return this.split(org).join(dest);
	}
	
	$("#price").on("change keyup paste", function() {
		let value = $(this).val()
		
		value = value.replaceAll("," ,"");
		
		let devideValue = parseInt(value / 1000000);
		if(devideValue >= 1) {
			$("#fancyPrice").text(new Intl.NumberFormat().format(devideValue) + " 백만원")
		} else {
			$("#fancyPrice").text("")
		}
		
		let wonValue = new Intl.NumberFormat().format(value)
		
		$(this).val(wonValue)
		
		
		$("#priceInput").val(value)
	})
</script>
