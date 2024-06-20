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

<div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon1">제목</span>
  <input id="docTitle" type="text" name="docTitle" class="form-control" placeholder="제목" aria-label="Username" aria-describedby="basic-addon1">
</div>

<label for="">수정 권한 : </label>
<select id="editRange" class="form-select" aria-label="Default select example">
  <option value selected></option>
  <option value="PM">PM</option>
  <option value="PL">PL</option>
  <option value="TA">TA</option>
  <option value="BA">BA</option>
  <option value="DA">DA</option>
  <option value="UA">UA</option>
  <option value="AA">AA</option>
</select>

<div id="spreadsheet"></div>

<button id="insertBtn" type="button" class="btn btn-primary">등록</button>

<script>
data = [

];
console.log(data)
// $('#spreadsheet').jexcel({ data:data, colWidths: [ 300, 80, 100 ] });

var changed = function(instance, cell, x, y, value) {
    var cellName = jexcel.getColumnNameFromId([x,y]);
    console.log('New change on cell' + cellName + ' to: ' + value)
}

$("#insertBtn").on("click", function() {
	let docTitle = $("#docTitle").val()
	let editRange = $("#editRange").val()
	
	let metaList = []
	metaList.push(docTitle)
	metaList.push(editRange)
	
	data.push(metaList)
	
	$.ajax({
		url : "${cPath}/pms/documents/${authMember.proNo}/documentsInsert.do",
		data : JSON.stringify(data),
		method : "POST",
		dataType : "json",
		contentType : "application/json;charset=UTF-8",
		success : function(resp) {
			console.log(resp.message)
			console.log(resp.url)
			if(resp.message == "success") {
				alert("문서 등록에 성공했습니다.")
				location.href = "${cPath}" + resp.url
				
			} else if(resp.message === "notValid") {
				alert("제목은 공백일 수 없습니다.")
			}
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	})
})


jspreadsheet(document.getElementById('spreadsheet'), {
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


</script>
</body>
