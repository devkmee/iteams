<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://bossanova.uk/jspreadsheet/v4/jexcel.js"></script>
<script src="https://jsuites.net/v4/jsuites.js"></script>
<link rel="stylesheet" href="https://bossanova.uk/jspreadsheet/v4/jexcel.css" type="text/css" />
<link rel="stylesheet" href="https://jsuites.net/v4/jsuites.css" type="text/css" />

<body>
<div class="input-group mb-3" style="width: 50%;">
	<input type="hidden" id="auth" />
  <span class="input-group-text2" id="basic-addon1">제목</span>
  <input readonly name="docTitle" id="docTitle" type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1">
  <c:if test="${authMember.authority eq 'PL' or authMember.authority eq 'PM'}">
  	<div style="line-height: 36px; margin: 0px 10px 0px 10px;">
  	<span>수정권한 : </span>
	<select disabled="disabled" id="editRange" class="form-select" aria-label="Default select example">
	  <option value selected></option>
	  <option value="PM">PM</option>
	  <option value="PL">PL</option>
	  <option value="TA">TA</option>
	  <option value="BA">BA</option>
	  <option value="DA">DA</option>
	  <option value="UA">UA</option>
	  <option value="AA">AA</option>
	</select>
	</div>
  </c:if>
  <c:if test="${authMember.authority eq 'PL' or authMember.authority eq 'PM' or authMember.authority eq masterVO.editRange or masterVO.editRange eq 'ALL' or authMember.memId eq masterVO.devId}">
  	<button id="updateBtn" type="button" class="btn btn-delete width-60 m-bottom-30 ">수정</button>
  </c:if>
</div>
<div id="downloadArea">
<span>원본파일 : </span>
</div>
<!-- <button id="pdfBtn" type="button" class="btn btn-secondary">PDF내보내기</button> -->

<div id="spreadsheet"></div>
<div class="flex-center">
	<c:if test="${authMember.authority eq 'PL' or authMember.authority eq 'PM' or authMember.authority eq masterVO.editRange or masterVO.editRange eq 'ALL' or authMember.memId eq masterVO.devId}">
	<form id="deleteForm" action="${cPath }/pms/documents/${authMember.proNo }/documentsDelete.do" method="post">
	<button id="submitBtn" type="button" class="btn btn-gray">저장</button>
	<input type="hidden" id="attNo" name="attNo" />
	<input type="hidden" id="editVal" name="auth" value="${masterVO.editRange}"/>
	<input type="hidden" name="docTitle" value="${masterVO.docTitle}"/>
	<input type="hidden" id="docNumVal" name="what" value="${docNum}"/>
	<input type="hidden" id="writer" name="writer" />
	<button id="deleteBtn" type="button" class="btn btn-gray">삭제</button>
	</form>
	</c:if>
</div>
<script>

let table = null;
let excelData = null;
let titleTag = $("#docTitle");

$("#deleteBtn").on("click", function(event) {
	
	let valid = confirm("정말 삭제하시겠습니까?");
	if(!valid) return false
	
	$("#deleteForm").submit()
})

$("#updateBtn").on("click", function() {
	let readAttr = $("#docTitle").attr("readonly");
	let disableAttr = $("#editRange").attr("disabled");
	
	if(readAttr) {
		$("#docTitle").removeAttr("readonly");
	} else {
		$("#docTitle").attr("readonly", "readonly");
	}
	
	if(disableAttr) {
		$("#editRange").removeAttr("disabled");
	} else {
		$("#editRange").attr("disabled", "disabled");
	}
})

$("#pdfBtn").on("click", function() {
	let attNo = $("#attNo").val()
	location.href = "${cPath}/pms/${authMember.proNo}/downloadPDF.do?what=" + attNo
})

	$(function() {
		
		
		var changed = function(instance, cell, x, y, value) {
		    var cellName = jexcel.getColumnNameFromId([x,y]);
		    console.log('New change on cell' + cellName + ' to: ' + value)
		}
		
		$.ajax({
			url : "${cPath}/pms/documents/${authMember.proNo}/documentsView.do",
			data : {what : "${docNum}"},
			dataType : "json",
			success : function(resp) {
				console.log(resp)
				let data = resp.data
				excelData = resp.data
				table = jspreadsheet(document.getElementById('spreadsheet'), {
				    data:data,
				    minDimensions: [15, 20],
				    defaultColWidth: 100,
				    tableOverflow: true,
				    tableWidth: "1570px",
				    tableHeight: "1200px",
				    rowResize:true,
				    columnDrag:true,
				    onchange: changed

				});
				
				
				let metaData = resp.meta[0]
				console.log(metaData[0])
				$("#docTitle").text(metaData[0])
				$("#docTitle").val(metaData[0])
				console.log(metaData[2])
				$("#editRange").find("[value='"+metaData[2]+"']").attr("selected", "true")
				$("#auth").val(metaData[2])
				$("#writer").val(metaData[3])
							
				let downloadTag = $("<a>").attr("href", "${cPath}/pms/${authMember.proNo}/download.do?what=" + metaData[1])
											.text(metaData[0] + ".xls");
				
				$("#attNo").val(metaData[1])
				console.log(downloadTag)
				$("#downloadArea").append(downloadTag)
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		})
		
		$("#submitBtn").on("click", function() {
	 		let title = titleTag.val()
	 		console.log(title)
	 		let docNum = "${docNum}"
	 		let editRange = $("#editRange").val()
	 		let originEditRange = $("#auth").val()
	 		
	 		let metaList = []
	 		metaList.push(docNum)
	 		metaList.push(title)
			metaList.push(editRange)
			metaList.push(originEditRange)
	 		
	 		excelData.push(metaList)
	 		 		
	 		$.ajax({
				url : "${cPath}/pms/documents/${authMember.proNo}/documentsUpdate.do",
				data : JSON.stringify(excelData),
				method : "POST",
				dataType : "json",
				contentType : "application/json;charset=UTF-8",
				success : function(resp) {
					console.log(resp.url)
					console.log(resp.message)
					if(resp.message == "success") {
						alert("성공적으로 문서를 수정했습니다.")
						location.href = "${cPath}" + resp.url
					} else if(resp.message === "notValid") {
						alert("제목은 공백일 수 없습니다.")
					} else {
						alert("문서 수정에 실패했습니다.")
						location.href = "${cPath}" + resp.url
					}
				},
				error : function(request, xhr, errorResp, error) {
					let header = request.getResponseHeader('content-Type')
					let isHtml = header.split(";")[0]
					if(isHtml === "text/html") {
						alert("작성자 혹은 권한이 있는 사람만 수정할 수 있습니다.")
					}
					console.log(errorResp);
					console.log(error);
				}
			})
	 		
		})

		
	})
	
</script>	
</body>
