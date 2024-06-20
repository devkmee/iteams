<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<body>
<div class="page-breadcrumb">
<form:form id="inputForm" method="post" modelAttribute="masterVO" enctype="multipart/form-data">

	<input type="hidden" name="proNo" value="${authMember.proNo }" />
	<input type="hidden" name="workWriter" value="${authMember.memId }" />
	<input type="hidden" name="workNum" value="${masterVO.workNum }" />
	<input type="hidden" name="chargerName" value=""/>
	
	<c:if test="${not empty authMember.devName }">
		<input type="hidden" name="writerName" value="${authMember.devName }"/>
	</c:if>
	<c:if test="${not empty authMember.managerName }">
		<input type="hidden" name="writerName" value="${authMember.managerName }"/>
	</c:if>
	
  <div class="form-group">
    <label for="exampleFormControlInput1">제목</label>
    <form:input path="workTitle" type="text" class="form-control" name="workTitle" id="exampleFormControlInput1"/>
    <form:errors path="workTitle" cssClass="error"></form:errors>
  </div>
  <div class="form-group">
    <label for="exampleFormControlTextarea1">설명</label>
    <form:textarea path="workContent" class="form-control" name="workContent" id="exampleFormControlTextarea1" rows="3"/>
  </div>
  <div class="form-group">
    <label for="exampleFormControlSelect1">담당자</label>
    <select class="form-control" name="workCharger" id="workCharger">
    	<option value></option>
    <form:errors path="workCharger" cssClass="error"></form:errors>
    </select>
  </div>
  <div class="form-group">
    <label for="exampleFormControlInput1">시작시각</label>
    <form:input path="startDate" type="date" name="startDate" class="form-control" id="exampleFormControlInput2"/>
  </div>
  <div class="form-group">
    <label for="exampleFormControlInput1">종료기한</label>
    <form:input path="endDate" type="date" class="form-control" name="endDate" id="exampleFormControlInput3"/>
    <form:errors path="endDate" cssClass="error"></form:errors>
  </div>
  <div class="form-group">
    <label for="exampleFormControlSelect1">유형</label>
    <form:select class="form-control" name="workType" id="workType" path="workType">
    	<option value="1">새기능</option>
    	<option value="2">결함</option>
    	<option value="3">지원</option>
    	<option value="4">공지</option>
    </form:select>
  </div>
  <div class="form-group">
    <label for="exampleFormControlSelect1">우선순위</label>
    <select class="form-control" name="workPriority" id="workPriority">
    	<option value="1">낮음</option>
    	<option value="2">보통</option>
    	<option value="3">높음</option>
    	<option value="4">긴급</option>
    	<option value="5">즉시</option>
    </select>
  </div>
  <div class="form-group">
    <label for="exampleFormControlSelect1">상태</label>
    <select class="form-control" name="workState" id="workState">
    	<option value="1">신규</option>
    	<c:if test="${not empty masterVO.workNum }">
    		<option value="2">진행</option>
    		<option value="3">해결</option>
    		<option value="4">의견</option>
    		<option value="5">완료</option>
    	</c:if>
    </select>
  </div>
  <div class="form-group">
    <label for="exampleFormControlSelect1">진척도</label>
    <form:select class="form-control" name="workProgress" id="workProgress" path="workProgress">
    	<option class="workProgress" value="0">0%</option>
    	<option class="workProgress" value="10">10%</option>
    	<option class="workProgress" value="20">20%</option>
    	<option class="workProgress" value="30">30%</option>
    	<option class="workProgress" value="40">40%</option>
    	<option class="workProgress" value="50">50%</option>
    	<option class="workProgress" value="60">60%</option>
    	<option class="workProgress" value="70">70%</option>
    	<option class="workProgress" value="80">80%</option>
    	<option class="workProgress" value="90">90%</option>
    	<option class="workProgress" value="100">100%</option>
    </form:select>
  </div>
  <c:if test="${masterVO.crudToken eq 'UPDATE' }">
	  <div class="form-group" id="" name="">
	   		<div name="">
		   		<label for="exampleFormControlInput1">기존첨부파일</label>
				<c:if test="${not empty masterVO.attachList }">
					<c:forEach items="${masterVO.attachList }" var="attach">
						<span data-atch="${attach.attNo }">
							${attach.attachOrigin }
							<input type="button" value="삭제" class="atchDelBtn"/>
						</span>
					</c:forEach>
				</c:if>
				<c:if test="${empty masterVO.attachList }">
					<span>첨부파일이 없습니다.</span>
				</c:if>
			</div>
	   </div>
   </c:if>
   <div class="form-group" id="fileArea" name="attachFiles">
   		<div name="attachFiles">
	   		<label for="exampleFormControlInput1">첨부파일</label>
		   	<input type="file" name="attachFiles" multiple />
			<input type="button" value="추가" id="plus" class="ctlBtn"/>
			<input type="button" value="제거" id="minus" class="ctlBtn"/>
		</div>
   </div>
  <div class="form-group">
    <label for="exampleFormControlInput1">상위일감</label>
    <input type="text" class="form-control" id="workParent">
    <input type="hidden" name="workParent" />
  </div>
  <button type="submit" class="btn btn-outline-primary">저장</button>
  <button id="testInputBtn" type="button" class="btn btn-outline-primary">시연용</button>
  <c:if test="${crudToken eq 'INSERT'}">
  	<button data-gopage="${cPath }/pms/work/${authMember.proNo}/workList.do" type="button" class="linkBtn btn btn-outline-danger">취소</button>
  </c:if>
  <c:if test="${crudToken eq 'UPDATE'}">
  	<button data-gopage="${cPath }/pms/work/${authMember.proNo}/workView.do?what=${masterVO.workNum}" type="button" class="linkBtn btn btn-outline-danger">취소</button>
  </c:if>
</form:form>
</div>
</body>

<!-- 이전값으로 셋팅 -->
<!-- 외부 스크립트 파일로 분리 불가능해서 여기다 코딩 -->
<script>
	$("#workProgress").find("[value='${masterVO.workProgress}']").attr("selected", "true")
	$("#workCharger").find("[value='${masterVO.workCharger}']").attr("selected", "true")
	$("#workState").find("[value='${masterVO.workState}']").attr("selected", "true")
	$("#workType").find("[value='${masterVO.workType}']").attr("selected", "true")
	$("#workPriority").find("[value='${masterVO.workPriority}']").attr("selected", "true")
	
</script>
<script>

	$("#testInputBtn").on("click", function() {
		$("[name='workTitle']").val("최종 테스트")
		$("[name='workContent']").text("최종 테스트를 진행한다.")
		$("[name='endDate']").val("2021-12-22")
		
	})


	let chargerArea = $("#workCharger")
	
	
	$(".atchDelBtn").on("click", function() {
		
		let valid = confirm("해당 첨부파일을 삭제하시겠습니까?")
		if(!valid) return
		
		let span = $(this).closest("span")
		let attNo = span.data("atch")
		$.ajax({
			url : "${cPath}/pms/${authMember.proNo}/attachDel.do",
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
	
	chargerArea.on("change", function() {
		let chargerName = $("#workCharger option:selected").text().trim()
		console.log(chargerName)
		$("[name='chargerName']").val(chargerName)
	})
	
	$.ajax({
		url : "${cPath}/pms/${authMember.proNo}/projectMemberList.do",
		method : "post",
		dataType : "json",
		success : function(resp) {
			let options = []
			if(resp) {
				$.each(resp, function(idx, teamMember) {
					let option = null
					
					if(teamMember.devName) {
						console.log(teamMember.devName)
						option = $("<option>").text(teamMember.devName).val(teamMember.devId).attr("data-charger", teamMember.devName)
					} else {
						option = $("<option>").text(teamMember.managerName).val(teamMember.devId).attr("data-charger", teamMember.managerName)
					}
					options.push(option)
				})
			} else {
				let option = $("<option>").text("소속팀원없음")
				options.push(option)
			}
			chargerArea.append(options)
			chargerArea.find("[value='${masterVO.workCharger}']").attr("selected", "true")
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	})
</script>
<script>
$(function() {
	$.ajax({
	    type : 'get',
	    url: '${cPath}/pms/work/${authMember.proNo}/workTitleList.do',
	    dataType : 'json',
	    success : function(resp) {
	    	let titleList = []
	    	let workNumMap = new Map();
	    	
	    	$.each(resp, function(idx, work) {
				titleList.push(work.workTitle)
				workNumMap.set(work.workTitle, work.workNum)
			})
			
			console.log(titleList)
	    	$('#workParent').autocomplete({ // autocomplete 구현 시작부
	            source : titleList, //source 는 자동완성의 대상
	            select : function(event, ui) { // item 선택 시 이벤트
	                console.log(ui.item.value);
	            	let workNum = workNumMap.get(ui.item.value)
	            	console.log(workNum)
	            	$("[name='workParent']").val(workNum)
	            },
	            focus : function(event, ui) { // 포커스 시 이벤트
	                return false;
	            },
	            minLength : 1, // 최소 글자 수
	            autoFocus : true, // true로 설정 시 메뉴가 표시 될 때, 첫 번째 항목에 자동으로 초점이 맞춰짐
	            classes : { // 위젯 요소에 추가 할 클래스를 지정
	                'ui-autocomplete' : 'highlight'
	            },
	            delay : 500, // 입력창에 글자가 써지고 나서 autocomplete 이벤트 발생될 떄 까지 지연 시간(ms)
	            disable : false, // 해당 값 true 시, 자동완성 기능 꺼짐
	            position : { my : 'right top', at : 'right bottom'}, // 제안 메뉴의 위치를 식별
	            close : function(event) { // 자동완성 창 닫아질 때의 이벤트
	                console.log(event);
	            }
	        });
	    }
	});
});
</script>
<!-- ck에디터, 현재 시간으로 시작 시각 설정 스크립트 -->
<script>
  CKEDITOR.replace("workContent", {
		filebrowserImageUploadUrl:"${cPath}/common/imageUpload.do?type=Images"
	});
</script>
<script src="${cPath }/resources/js/pms/work/workForm.js"></script>

