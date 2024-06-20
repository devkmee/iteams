<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/hireList.css">
<div class="sub-wrap">
	<div class="sub-inner-box">
		<c:url value="/outs/hire/profileList.do" var="listURL"></c:url>
		<div class="sub-inner-txt linkBtn" data-gopage="${listURL }">
			<span class="sub-txt">개발자 프로필<br>
				<span class="project-txt-lighter">모든 채용이 기대감으로 시작해 서로 악수하는 반가움이 되는 곳<br>아이팀즈입니다.</span>
			</span>
		</div>
		<div class="main-outline-box-img m-right-10">
			<img alt="" src="/iteams/resources/images/hireList-img.png">
		</div>
	</div>
</div>
<section class="section-wrap">
	<div class="project-list-title m-bottom-10 m-top-30">
		<span>${dev.devName }님의 프로필 ></span>
		<div class="flex-between m-top-10">
			<button data-gopage="${cPath}/outs/hire/profileList.do" class="linkBtn btn btn-gray">목록</button>
			<div>
				<c:if test="${authMember.memRole eq 'CLIENT'}">				
					<c:if test="${scrapCnt eq '0'}">
						<input class="btn btn btn-gray" type="button" class="linkBtn btn btn-gray" value="스크랩" id="scrabBtn">
					</c:if>
					<c:if test="${scrapCnt eq '1'}">
						<input class="btn btn-gray" type="button" value="스크랩해제" id="scrabBtn">
					</c:if>
					<input type="button" class="btn btn-gray" value="초대" id="inviteBtn">
				</c:if>
			</div>
		</div>
	</div>
	<div class="hirelist-wrap">
		<div class="table-wrap">
			<table class="prolist-table">
			 	<tbody>
			 	<tr>
			      <th scope="col">사진</th>
			      <td><img style="width: 70px; height: 70px; background-position: center center; background-repeat: no-repeat; border-radius: 50% !important; margin-left: 20px;" src="${cPath}/imageRender.do?what=${dev.devImg}" alt="" /></td>
			    </tr>
			    <tr>
			      <th scope="col">이름</th>
			      <td>${dev.devName }</td>
			    </tr>
			    <tr>
			      <th scope="col">출생년도</th>
			      <td>${dev.devBir }</td>
			    </tr>
			    <tr>
			      <th scope="col">연락처</th>
			      <td>${dev.memTel }</td>
			    </tr>
			    <tr>
			      <th scope="col">이메일</th>
			      <td>${dev.memMail }</td>
			    </tr>
			    <tr>
			      <th scope="col">직무</th>
			      <td>${dev.devJob }</td>
			    </tr>
			    <tr>
			      <th scope="col">기술스택</th>
			      <c:choose>
				      	<c:when test="${empty dev.devTechValue }">
				      	<td>#${dev.devTech }</td>
				      </c:when>
				      <c:otherwise>
				      	<td>
					      	<c:forEach items="${dev.devTechValue }" var="tech">
					      		#${tech }
					      	</c:forEach>
				      	</td>
				      </c:otherwise>
			      </c:choose>
			    </tr>
			    <tr>
			      <th scope="col">학교</th>
			      <td>${dev.devEdu }</td>
			    </tr>
			    <tr>
			      <th scope="col">전공</th>
			      <td>${dev.devMajor }</td>
			    </tr>
			    <tr>
			      <th scope="col">경력</th>
			      <td>${dev.devCareer}</td>
			    </tr>
			    <tr>
			    	<th>프로젝트 경력</th>
			    	<td>
				    	<c:if test="${not empty dev.profileProjectList}">
				      		<c:forEach items="${dev.profileProjectList}" var="project">
				      			<span>${project.projectName} / ${project.createDate} ~ <c:if test="${not empty project.completeDate}">${project.completeDate}</c:if><c:if test="${empty project.completeDate}">진행중</c:if></span> <br />
				      		</c:forEach>
				      	</c:if>
				      	<c:if test="${empty dev.profileProjectList}">
				      		완료/진행 프로젝트 경력이 없습니다.
				      	</c:if>
			    	</td>
			    </tr>
			    <tr>
			      <th scope="col">포토폴리오</th>
			      <td>${dev.devPort }</td>
			    </tr>
			  </tbody>
			</table>
		</div>
	</div>
</section>

<div class="modal fade" id="inviteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"><strong>${dev.devName }</strong>님을 프로젝트에 초대합니다</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div id="modalBody" class="modal-body">
      		<select name="project">
      			<option value>프로젝트 선택</option>
      			<c:forEach items="${client.dataList}" var="proj">
      				<option value="${proj.proNo }">${proj.projectName }</option>
      			</c:forEach>
      		</select>
       		<select name="authority">
		 		<option value>역할 선택</option>
		 		<option value="PL" >PL</option>
		 		<option value="DA" >DA</option>
		 		<option value="UA" >UA</option>
		 		<option value="AA" >AA</option>
		 		<option value="BA" >BA</option>
		 		<option value="TA" >TA</option>
		 	</select>
		 	<div id="inviteMessage" style="margin-top: 20px;">
		 		<span>${dev.devId} 님에게 보내는 초대 메세지.</span>
		 		<br /><span>연봉 </span><input dir="rtl" id="invitePrice" type="text" /><span>원 내외의 조건으로</span>
		 		<br /><span>귀하를 저희 프로젝트에 초대합니다.</span>
		 		<br /><span>자세한 사항은 인사 담당자와 상의 할 수 있습니다.</span>
		 	</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
        <button type="button" id="submitBtn" class="btn btn-primary">초대</button>
      </div>
    </div>
  </div>
</div>
<form id="inviteForm" action="${cPath}/outs/hire/inviteInsert.do" method="post">
	<input type="hidden" name="devId" value="${dev.devId}">
	<input type="hidden" name="proNo">
	<input type="hidden" name="authority">
	<input type="hidden" name="invitePrice" />
</form>
<form id="scrabForm" action="${cPath }/outs/hire/scrab.do" method="post">
	<input type="hidden" name="devId" value="${dev.devId}">
</form>
<script>
	let inviteModal = $("#inviteModal");
	let modalBody = $("#modalBody");
	let inviteForm = $("#inviteForm");
	let scrabForm = $("#scrabForm");
	
	$("#inviteBtn").on("click", function () {
		inviteModal.modal("show")
	});
	
	inviteModal.on('hide.bs.modal', function (event) {
		inviteForm.find(":input[name='proNo']").empty();
		inviteForm.find(":input[name='authority']").empty();
	})
	
	$("#submitBtn").on('click', function (event) {
		let selectedProNo = modalBody.find("[name='project']").val()
		let selectedAuthority = modalBody.find("[name='authority']").val()
		inviteForm.find(":input[name='proNo']").val(selectedProNo);
		inviteForm.find(":input[name='authority']").val(selectedAuthority);
		
		console.log(selectedProNo)
		console.log(selectedAuthority)
		inviteForm.submit();
		inviteModal.modal("hide");
	})
	
	$("#scrabBtn").on("click", function () {
		scrabForm.submit();
	}) 
	
	
	$("#invitePrice").on("change keyup paste", function() {
	let value = $(this).val()
	
	value = value.replaceAll("," ,"");
		
	let wonValue = new Intl.NumberFormat().format(value)
	
	$(this).val(wonValue)
	$("[name='invitePrice']").val(value)
	console.log(value)
	console.log($("[name='invitePrice']").val() + "폼")
})
	
</script>
