<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="${cPath }/resources/js/ckeditor/ckeditor.js"></script>
<div>
	<div class="sub-wrap">
		<div class="sub-inner-box">
			<div class="sub-inner-txt">
				<span class="sub-txt">1:1 질문 게시판<br>
					<span class="project-txt-lighter">1:1 질문 게시판</span>
				</span>
			</div>
			<div class="main-outline-box-img m-right-10">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/project-img.png">
			</div>
		</div>
	</div>
<section class="section-wrap">
		<div class="project-list-title m-bottom-10 m-top-30">
			<span>1:1 질문  수정 ></span>
		</div>
		<div class="writing-view-wrap">
			<form:form modelAttribute="board" method="post" enctype="multipart/form-data">
			<input type="hidden" name="boNo" value="${board.boNum}" />
			<input type="hidden" name="writer" value="${authMember.memId}" />
				<table>
					<div class="writing-view">
						<tr>
							<select name="qnaKeyword" id="qnaKeyword" required>
						  		<option value="1">계정 정보</option>
						  		<option value="2">프로젝트 관리</option>
						  		<option value="3">프로젝트 검색/지원</option>
						  		<option value="4">개발자 검색/초대</option>
						  		<option value="5">PMS</option>
						  	</select>
						</tr>
						<tr>
							<th>제목</th>
							<td><input type="text" name="boTitle" value="${board.boTitle }" required>
							<form:errors path="boTitle" element="span" cssClass="error"></form:errors></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea rows="5" cols="100" name="boContent" id="boContent" required>${board.boContent }</textarea>
							<form:errors path="boContent" element="span" cssClass="error"></form:errors>
							</td>
						</tr>
						<tr>
							<c:choose>
								<c:when test="${fn:length(board.attachList) != 0 }">
									<th>기존 첨부파일</th>
									<td>
									<c:forEach items="${board.attachList }" var="att">
									
										<span data-atch="${att.attNo }">${att.attachOrigin }
											<input type="button" value="삭제" class="atchDelBtn"/>
										</span>
									</c:forEach>
									</td>
								</c:when>
							</c:choose>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td id="fileArea">
								<div name="attachFiles">						 
									<input type="file" name="attachFiles" multiple />
									<input type="button" value="추가" id="plus" class="ctlBtn"/>
									<input type="button" value="제거" id="minus" class="ctlBtn"/>
								</div>
							</td>
						</tr>
					</div>
					<div class="board-btn-wrap">
						<tr>
							<td>
								<input type="submit" class="btn btn-gray" value="수정" />
							</td>
						</tr>
					</div>
				</table>
			</form:form>
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
	});
</script>
