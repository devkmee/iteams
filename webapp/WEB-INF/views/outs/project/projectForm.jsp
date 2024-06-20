<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${cPath }/resources/js/ckeditor/ckeditor.js"></script>

<form method="post" enctype="multipart/form-data">
	<input type="hidden" name="boNum" value="${project.boNum }" />
	<table>
		<tr>
			<th>제목</th>
			<td><input type="text" name="boTitle" required value="${project.boTitle }" />
				<span class="error">${errors.boTitle }</span></td>
		</tr>
		<tr>
			<th>글내용</th>
			<td>
				<textarea rows="5" cols="100" name="boContent" id="boContent">${project.boContent }</textarea>
				<span class="error">${errors.boContent }</span></td>
		</tr>
<!-- 		<tr> -->
<!-- 			<th>첨부파일</th> -->
<!-- 			<td id="fileArea"> -->
<!-- 				<div> -->
<!-- 					<input type="file" name="boFiles" multiple /> -->
<!-- 					<input type="button" value="추가" id="plus" class="ctlBtn"/> -->
<!-- 					<input type="button" value="제거" id="minus" class="ctlBtn"/> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 		</tr> -->
		<tr>
			<th>프로젝트명</th>
			<td><input type="text" name="projectName" required value="${project.projectName }" />
				<span class="error">${errors.projectName }</span></td>
		</tr>
		<tr>
			<th>프로젝트규모</th>
			<td><input type="text" name="projectScale" required value="${project.projectScale }" />
				<span class="error">${errors.projectScale }</span></td>
		</tr>
		<tr>
			<th>예상단가</th>
			<td><input id="price" type="text" name="" required value="${project.projectPrice }" />
				<input id="priceInput" type="hidden" name="projectPrice" />
				<span id="fancyPrice"></span>
				<span class="error">${errors.projectPrice }</span></td>
		</tr>
		<tr>
			<th>요구사항</th>
			<td>
				<textarea rows="5" cols="100" name="projectReq" id="projectReq">${project.projectReq }</textarea>
				<span class="error">${errors.projectReq }</span></td>
		</tr>
		<tr>
			<th>요구기술스택</th>
			<td><input type="text" name="projectTech" required value="${project.projectTech }" />
				<span class="error">${errors.projectTech }</span></td>
		</tr>
		<tr>
			<th>요구직무</th>
			<td><input type="text" name="projectJob" required value="${project.projectJob }" />
				<span class="error">${errors.projectJob }</span></td>
		</tr>
		<tr>
			<th>공고기한</th>
			<td><input type="date" name="limitDate" required value="${project.limitDate }" />
				<span class="error">${errors.limitDate }</span></td>
		</tr>
		<tr>
			<th>모집인원</th>
			<td><input type="text" name="projectRecruit" required value="${project.projectRecruit }" />
				<span class="error">${errors.projectRecruit }</span></td>
		</tr>
		<tr>
			<th>상주여부</th>
			<td>
				<input type='radio' name='officeNy' value='N' />아니오
		 		<input type='radio' name='officeNy' value='Y' />예
				<span class="error">${errors.officeNy }</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" />
				<input type="reset" value="취소" />
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	CKEDITOR.replace("boContent", {
		filebrowserImageUploadUrl:"${cPath}/common/imageUpload.do?type=Images"
	});
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
