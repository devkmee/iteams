<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/board.css">	
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
		<span>뉴스피드 ></span>
	</div>
    <div class="writing-view-wrap">
	<div class="writing-view">
			<div class="writing-title">
				<span>${board.boTitle}</span>
			</div>
			<div class="writing-info-wrap m-top-10 m-bottom-10">
				<div>작성자 : ${board.memId} 님</div>
				<div>조회수 : ${board.boHit}</div>
				<div>추천수 : ${board.boRec} </div>
				<div>신고수 : ${board.boRep} </div>
				<div>작성일 : ${board.writeDate} </div>
				<div class="btn-group" role="group" aria-label="Basic example">
				   <img id="recBtn" class="m-right-10" src="<%=request.getContextPath()%>/resources/images/recommend.png"></img>
				  <img id="reportBtn" src="<%=request.getContextPath()%>/resources/images/siren.png"></img>
				</div>
			</div>
		</div>
		<div class="writing-content">
			<span>${board.boContent}</span>
		</div>
	    <div class="board-btn-wrap">
			<c:url value="/outs/board/news/newsUpdate.do" var="updateURL">
				<c:param name="what" value="${board.boNum }" />
				<c:param name="writer" value="${board.memId }" />				
			</c:url>
			<c:if test="${authMember.memId eq board.memId}">
				<input type="button" value="글수정" class="linkBtn btn-delete width-60 m-right-5" data-gopage="${updateURL}" />
				<c:url value="/outs/board/news/newsDelete.do" var="deleteURL">
					<c:param name="what" value="${board.boNum }" />
					<c:param name="writer" value="${board.memId }" />				
				</c:url>	
			</c:if>
			<c:if test="${authMember.memRole eq 'ADMIN' or authMember.memId eq board.memId}">
				<input id="deleteBtn" type="button" value="글삭제" class="linkBtn btn-delete width-60 m-right-5" data-gopage="${deleteURL}" />
			</c:if>	
			<c:if test="${authMember.memRole eq 'ADMIN'}">
				<input type="button" id="blackBtn" value="블랙리스트 등록" class="linkBtn btn-delete width-120 m-right-5"/>
			</c:if>
		</div> 
		
<div class="modal fade" id="blackModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">블랙리스트 등록</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div id="modalBody" class="modal-body">
      		<select name="blackContent">
      			<option value>차단 사유</option>
      			<option value="도배">도배</option>
      			<option value="광고글">광고글</option>
      			<option value="욕설 및 싸움">욕설 및 싸움</option>
      		</select>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-delete" data-dismiss="modal">취소</button>
        <button type="button" id="blackSubmitBtn" class="btn btn-delete">등록</button>
      </div>
    </div>
  </div>
</div>
		<div>
			<c:if test="${not empty board.attachList }">
				<span>첨부파일 : </span>
				<c:forEach items="${board.attachList }" var="attach">
					<a href="${cPath}/outs/download.do?what=${attach.attNo}">${attach.attachOrigin}</a>
					</c:forEach>
			</c:if>
			<c:if test="${fn:length(board.attachList) < 1 }">
						<span>첨부파일이 없습니다.</span>
			</c:if>			
		</div>
		<div class="comment-wrap">
			<form id="replyInsertForm" method="post" action="${cPath}/outs/board/free/replyInsert.do">
			<input type="hidden" name="boNum" value="${board.boNum}" />
			<div class="input-group mb-3">
			  <input name="repContent" type="text" class="form-control" placeholder="" aria-label="Recipient's username" aria-describedby="button-addon2">
			  <div class="input-group-append">
			    <input class="btn btn-gray" type="button" value="댓글등록" id="repInsertBtn">
			  </div>
			</div>
			</form>
		</div>
		<div>
		<form id="repForm">
			<input type="hidden" name="writer" id="repWriter"/>
			<input type="hidden" name="repNo" id="repNum" />
			<div id="listBody">
			</div>
		</form>
		</div>
	</div>
	<form action="${cPath}/outs/board/free/replyList.do" id="searchForm">
		<input type="hidden" name="boNum" value="${board.boNum}" />
		<input type="hidden" name="page" />
	</form>
</section>

<form id="blackForm" action="${cPath}/outs/blacklist/blackInsert.do" method="post">
	<input type="hidden" name="boNum" value="${board.boNum}" />
	<input type="hidden" name="blackContent">
	<input type="hidden" name="devId" value="${board.memId}">
</form>
<script>
	//블랙리스트 등록
	let blackModal = $("#blackModal");
	let blackForm = $("#blackForm");
	let modalBody = $("#modalBody");
	
	$("#blackBtn").on("click", function () {
		blackModal.modal("show")
	})
	
	blackModal.on('hide.bs.modal', function (event) {
		blackModal.find(":input[name='blackContent']").empty();
	})
	
	$("#blackSubmitBtn").on('click', function (event) {
		let selected = modalBody.find("[name='blackContent']").val()
		blackForm.find(":input[name='blackContent']").val(selected);
		blackForm.submit();
		blackModal.modal("hide");
	})
	
	
	

	$("#recBtn").on("click", function() {
		$.ajax({
			url : "${cPath}/outs/board/free/recommend.do",
			data : {"what" : "${board.boNum}"},
			dataType : "json",
			success : function(resp) {
				alert(resp.message)
				location.reload(true)
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		})
	})
	
	$("#reportBtn").on("click", function() {
		$.ajax({
			url : "${cPath}/outs/board/free/report.do",
			data : {"what" : "${board.boNum}"},
			dataType : "json",
			success : function(resp) {
				alert(resp.message)
				location.reload(true)
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		})
	})	


	$("#deleteBtn").on("click", function() {
		var valid = confirm("삭제하시겠습니까?")
		if(!valid) return false;
	})
	
	var listBody = $("#listBody");
	var pagingArea = $("#pagingArea")
	var pageTag = $("[name='page']")
	
	$("#searchForm").paging().ajaxForm({
		dataType : "json",
		success : function(resp) {
			console.log(resp)
			let dataList = resp.dataList
			console.log(dataList)
			let trTags = []
			let pagingHTML = resp.pagingHTML
			
			listBody.empty();
			pagingArea.empty();
					
			if(dataList.length > 0) {
				$.each(dataList, function(idx, reply) {
					let wrapperDiv = $("<div>").addClass("comment-list-wrap")
					
					let contentDiv = $("<div>").addClass("comment-list").text(reply.repContent)		
								
					let infoDiv = $("<div>").addClass("comment-box-wrap").append(
						$("<div>").addClass("comment-list-info replyUpdate").text("수정"),
						$("<div>").addClass("comment-list-info replyDelete").text("삭제"),
						$("<div>").addClass("comment-list-info").text("작성일 : " + reply.repWriteDate),
						$("<div>").addClass("comment-list-info").text("수정일 : " + reply.repModifyDate),
						$("<div>").addClass("comment-list-info").text("작성자 : " + reply.repWriter)
					);
					
					let hiddenRepNo = $("<input>").attr({
						"type" : "hidden",
						"value" : reply.repNo,
						"class" : "repNo" 
					})
					
					let hiddenRepWriter = $("<input>").attr({
						"type" : "hidden",
						"value" : reply.repWriter,
						"class" : "repWriter" 
					})
					
					wrapperDiv.append(contentDiv)
					wrapperDiv.append(infoDiv)
					wrapperDiv.append(hiddenRepNo)
					wrapperDiv.append(hiddenRepWriter)
					
					listBody.append(wrapperDiv)
				})
				
			} else {
				let contentDiv = $("<div>").addClass("comment-list").text("댓글이 없습니다.")
				contentDivs.push(contentDiv)
			}
			
			pagingArea.html(pagingHTML)
			pageTag.val("")
			
		},
		error : function(x) {
			console.log(x.status + "ddddddddd")
			console.log(x)
		}
	}).submit()
	
	
	$("#repInsertBtn").on("click", function() {
		console.log("3")
		$("#replyInsertForm").ajaxForm({
			dataType : "json",
			success : function(resp) {
				alert(resp.message)
				location.reload(true)			    	 	     
			},
			error : function(x) {
				if(x.status == 200) {
					alert("로그인이 필요한 서비스입니다.")
					location.href = "${cPath}/outs/login/login.do"
				}
			}
		}).submit()
	})
	
	$("#listBody").on("click", ".replyUpdate", function() {
				
		var wrapper = $(this).parents(".comment-list-wrap")
		var replyContentTag = wrapper.find(".comment-list");
		var replyContent = replyContentTag.text()
		var replyWriter = wrapper.find(".repWriter").val()
		var repNo = wrapper.find(".repNo").val()
		
		var authMember = "${authMember.memId}"
		
		if(replyWriter != authMember) {
			alert("작성자만 수정할 수 있습니다.")
			return false;
		}
		
		$("#repWriter").val(replyWriter)
		$("#repNum").val(repNo)
		
		replyContentTag.empty()
		var input = $("<textarea>").attr({
			"name" : "repContent",
			"cols" : "30"
		}).text(replyContent)
		
		var button = $("<input>").attr({
			"type" : "button",
			"class" : "repUpdateSubmit",
			"value" : "저장"
		})
		
		var resetButton = $("<input>").attr({
			"type" : "button",
			"value" : "취소",
			"onclick" : "location.reload()"
		})
		
		replyContentTag.append(input)
		replyContentTag.append(button)
		replyContentTag.append(resetButton)
			
	})
	
	$("#listBody").on("click", ".repUpdateSubmit", function() {
		$("#repForm").ajaxForm({
			url : "${cPath}/outs/board/free/replyUpdate.do",
			dataType : "json",
			method : "post",
			success : function(resp) {
				alert(resp.message)
				location.reload(true)
			},
			error : function(x) {
				if(x.status == 403) {
					alert("작성자 본인만 수정할 수 있습니다.")
				}
			}
		}).submit()
	})
	
	$("#listBody").on("click", ".replyDelete", function() {
		
		var valid = confirm("삭제 하시겠습니까?")
		
		if(!valid) return false;
		
		var wrapper = $(this).parents(".comment-list-wrap")
		var replyWriter = wrapper.find(".repWriter").val()
		var repNo = wrapper.find(".repNo").val()
		
		$("#repWriter").val(replyWriter)
		$("#repNum").val(repNo)
		
		$("#repForm").ajaxForm({
			url : "${cPath}/outs/board/free/replyDelete.do",
			dataType : "json",
			method : "post",
			success : function(resp) {
				alert(resp.message)
				location.reload(true)
			},
			error : function(x) {
				if(x.status == 403) {
					alert("작성자 본인만 삭제할 수 있습니다.")
				}
			}
			
		}).submit()
	})
	
	
</script>
 
