<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${cPath }/resources/js/ckeditor/ckeditor.js"></script>
<div>
	<div class="sub-wrap">
		<div class="sub-inner-box">
			<div class="sub-inner-txt">
				<span class="sub-txt">1:1질문 게시판<br>
					<span class="project-txt-lighter">1:1질문 게시판</span>
				</span>
			</div>
			<div class="main-outline-box-img m-right-10">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/project-img.png">
			</div>
		</div>
	</div>
<section class="section-wrap">
		<div class="project-list-title m-bottom-10 m-top-30">
			<span>1:1질문 작성 게시판 ></span>
		</div>
		<div class="writing-view-wrap">
			<form method="post" enctype="multipart/form-data">
				<table>
					<div class="writing-view">
						<tr>
							<th>내용</th>
							<td><textarea rows="5" cols="100" name="boContent" id="boContent" required>${board.boContent }</textarea>
							<form:errors path="boContent" element="span" cssClass="error"></form:errors></td>
						</tr>
							<th>첨부파일</th>
							<td id="fileArea">
								<div name="attachFiles">						 
									<input type="file" name="attachFiles" multiple />
									<input type="button" value="추가" id="plus" class="ctlBtn"/>
									<input type="button" value="제거" id="minus" class="ctlBtn"/>
								</div>
							</td>
					</div>
				</table>
					<div class="board-btn-wrap">
						<div class="flex-center">
							<input type="submit" class="btn btn-gray" value="등록" />
							<input type="reset" class="btn btn-gray m-left-5" value="취소" />
						</div>
					</div>
			</form>
		</div>
	</section>
</div>
<script>
	CKEDITOR.replace( "boContent", {
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
