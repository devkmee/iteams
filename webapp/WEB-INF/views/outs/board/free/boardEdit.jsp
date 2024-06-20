<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/board.css">	
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/table.css">	
<div class="sub-wrap">
	<div class="sub-inner-box">
		<div class="sub-inner-txt2">
			<span class="sub-txt"> 일상이야기<br>
				<span class="project-txt-lighter">다양한 일상 이야기를 자유롭게!</span>
			</span>
		</div>
		<div class="main-outline-box-img m-right-10">
			<img alt="" src="<%=request.getContextPath()%>/resources/images/community.png">
		</div>
	</div>
</div>	
<section class="section-wrap">
	<div class="community-box-wrap">
		<div style="cursor: pointer;" class="linkBtn community-box" data-gopage="${cPath }/outs/board/free/freeBoardList.do">
			<a href="<%=request.getContextPath()%>/outs/board/free/freeBoardList.do">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/c1.png">
			</a>
		</div>
		<div class="community-box">
			<a href="<%=request.getContextPath()%>/outs/board/codebook/codeList.do">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/c2.png">
			</a>
		</div>
		<div class="community-box">
			<a href="<%=request.getContextPath()%>/outs/board/interview/interviewList.do">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/c3.png">
			</a>
		</div>
		<div class="community-box">
			<a href="<%=request.getContextPath()%>/outs/board/news/newsList.do">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/c4.png">
				</a>
			</div>
	</div>
	<div class="project-list-title m-bottom-10 m-top-20">
		<span>일상이야기 ></span>
	</div>
	<div class="writing-view-wrap">
	<form:form modelAttribute="board" method="post" enctype="multipart/form-data" id="editForm">
		<input type="hidden" name="boNum" value="${board.boNum }" />
		<table  class="table border-table">
			<tr>
				<th>제목</th>
				<td><input class="write-input" type="text" name="boTitle" style="width: 800px;"  value="${board.boTitle }" />
					<form:errors path="boTitle" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${board.memId }
					<form:errors path="memId" element="span" cssClass="error" /></td>
			</tr>
			
			<tr>			
				<th>기존 첨부파일</th>
				
				<td>
					<c:if test="${not empty board.attachList}">
					<c:forEach items="${board.attachList }" var="atch">
						<span data-atch="${atch.attNo }">
							${atch.attachOrigin }
							<input type="button" value="삭제" class="atchDelBtn"/>
						</span>
					</c:forEach>
					</c:if>
					<c:if test="${empty board.attachList}">
						<span>첨부파일이 없습니다.</span>
					</c:if>
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
					<textarea rows="5" cols="100" name="boContent" id="boContent">${board.boContent }</textarea>
					<form:errors path="boContent" element="span" cssClass="error" /></td>
			</tr>
			</table>
			<div class="board-btn-wrap">
				<input type="submit" value="저장" class="btn btn-gray m-right-5"/>
				<input data-gopage="${cPath}/outs/board/free/freeBoardDetail.do?what=${board.boNum}" type="reset" value="취소" class="linkBtn btn btn-gray"/>
			</div>
	</form:form>
	</div>
</section>
	<script type="text/javascript">
		CKEDITOR.replace("boContent", {
			filebrowserImageUploadUrl:"${cPath}/board/imageUpload.do?type=Images"
		});
		let editForm = $("#editForm");
	
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
		
		
		$(".atchDelBtn").on("click", function() {
			
			let valid = confirm("해당 첨부파일을 삭제하시겠습니까?")
			if(!valid) return
			
			let span = $(this).closest("span")
			let attNo = span.data("atch")
			console.log(attNo)
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
	</script>










