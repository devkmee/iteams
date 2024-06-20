<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/board.css">	
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/table.css">	
<div class="sub-wrap">
	<div class="sub-inner-box">
		<div class="sub-inner-txt2">
			<span class="sub-txt"> 뉴스피드<br>
				<span class="project-txt-lighter">자발적인 에디터 참여로 만들어지는 디지털 미디어 뉴스피드!</span>
			</span>
		</div>
		<div class="main-outline-box-img m-right-10">
			<img alt="" src="<%=request.getContextPath()%>/resources/images/community.png">
		</div>
	</div>
</div>
<section class="section-wrap">
    <div class="project-list-title m-bottom-10 m-top-30">
		<span>뉴스피드 ></span>
	</div>
    <div class="writing-view-wrap">
	<form method="post" enctype="multipart/form-data">
		<input type="hidden" name="boNum" value="${detail.boNum }" />
		<table class="table border-table">
			<tr>
				<th>제목</th>
				<td><input class="write-input" type="text" name="boTitle" required value="${detail.boTitle }" style="width: 800px;" placeholder="제목을 입력하세요"/>
					<span class="error">${errors.boTitle }</span>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
		   			<div class="form-group" id="fileArea" name="attachFiles">
  						<div name="attachFiles">
						   	<input type="file" name="attachFiles" multiple />
							<input type="button" value="추가" id="plus" class="ctlBtn"/>
							<input type="button" value="제거" id="minus" class="ctlBtn"/>
						</div>
   		  			 </div>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="5" cols="100" name="boContent" id="boContent">${detail.boContent }</textarea>
					<span class="error">${errors.boContent }</span></td>
			</tr>
		</table>
		<div class="board-btn-wrap">
			<input type="submit" value="저장" class="btn btn-gray m-right-5"/>
			<input type="button" value="취소" data-gopage="${cPath}/outs/board/news/newsList.do" class="linkBtn btn btn-gray"/>
		</div>
	</form>
	</div>
</section>
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
	</script>


