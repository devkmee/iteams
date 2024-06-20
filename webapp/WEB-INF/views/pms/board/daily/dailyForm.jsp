<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- <section class="section-wrap"> -->
		<div class="project-list-title m-bottom-10 m-top-30">
			<span>일일업무보고</span> 
		</div>

<form:form modelAttribute="detail" method="post" enctype="multipart/form-data">
	<%-- <input  id="boNum" type="hidden" name="boNum" value="${master.boNum }" /> --%>
			<input id="boNum" name="boNum" value="${detail.boNum }" type="hidden"/>
	
	
	
<!-- 	    <tr> -->
<!-- 		    <th>게시물 번호</th> -->
<!-- <td> -->
<!-- 	    </tr> -->
  <div class="form-group">
    <label for="exampleFormControlInput1">제목</label>
			<input type="text" name="boTitle" required value="${detail.boTitle }" />
				<form:errors path="boTitle" cssClass="error" element="span"></form:errors>
	</div>
		  <div class="form-group">
    <label for="exampleFormControlInput1">내용</label>

				<textarea rows="5" cols="100" name="boContent" id="boContent">${detail.boContent }</textarea>
				<form:errors path="boContent" cssClass="error" element="span"></form:errors>
	</div>
		
  <c:if test="${detail.crudToken eq 'UPDATE' }">
	  		<div class="form-group">
		   		<label for="exampleFormControlInput1">기존첨부파일</label>
				<c:if test="${not empty detail.attachList }">
					<c:forEach items="${detail.attachList }" var="attach">
						<span data-atch="${attach.attNo }">
							${attach.attachOrigin }
							<input type="button" value="삭제" class="atchDelBtn"/>
						</span>
					</c:forEach>
				</c:if>
				<c:if test="${empty detail.attachList }">
					<span>첨부파일이 없습니다.</span>
				</c:if>
			</div>
   </c:if>
		
		<div class="form-group">
		   <label for="exampleFormControlInput1">기존첨부파일</label>
			<div id="fileArea" id="fileArea">
				<div>
					<input type="file" name="attachFiles" multiple />
					<input type="button" value="추가" id="plus" class="ctlBtn"/>
					<input type="button" value="제거" id="minus" class="ctlBtn"/>
				</div>
			</div>
		</div>
		

			    <input type="submit" value="수정/저장" class="btn btn-gray"/>
				<input type="reset" value="취소" class="btn btn-gray"/>
				<input type="button" value="리스트" class="btn" onclick="location.href='/iteams/pms/board/daily/${authMember.proNo }/dailyList.do'" class="btn"/>


</form:form>

<!-- 	</section> -->


<script>
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
</script>

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





<!-- 게시글수정 -->
<%-- <div class="page-breadcrumb">
	<div class="table-wrap">
		<form modelAttribute="board" method='post'>
		<table class="table">
			<tr>
				<th>작성일</th>
				<td>${detail.writeDate}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${detail.boHit}</td>
			</tr>
			<tr>
				<th>게시물 제목</th>
				<td><input id="boTitle" name="boTitle" value="${detail.boTitle }"/></td>
			</tr>
			<tr>
				<th>게시물 내용</th>
				<td><input id="boContent" name="boContent" value="${detail.boContent }"/></td>
			</tr>
			<tr>
				<th>게시물 번호</th>
				<td><input id="boNum" name="boNum" value="${detail.boNum }" type="hidden"/>${detail.boNum }</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정하기" class="btn btn-primary">
					<input type="reset" value="취소" class="btn btn-warning">
					<input type="button" value="리스트" class="btn" onclick="location.href='/iteams/pms/board/daily/${authMember.proNo }/dailyList.do'" class="btn"/>
				</td>
			</tr>
		</table>
		</form>
	</div>	
</div> --%>