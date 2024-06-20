<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<div>
	<div class="sub-wrap">
		<div class="sub-inner-box">
			<div class="sub-inner-txt">
				<span class="sub-txt">마이페이지<br>
					<span class="project-txt-lighter">커리어 관리가 즐거워지고, 오늘보다 더 나은 내일을 꿈꿀 수 있는 곳<br>아이팀즈입니다.</span>
				</span>
			</div>
			<div class="main-outline-box-img m-right-10">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/project-img.png">
			</div>
		</div>
	</div>
	<section class="section-wrap">
		<div class="project-list-title m-bottom-10 m-top-30">
			<span>프로젝트 상세 조회 ></span> 
		</div>
		
<input type="button" value="신고" id="repBtn" 
		data-url="${cPath }/outs/board/projectboard/report.do" data-what="${number }"/>
		
<c:if test="${authMember.memRole eq 'DEV'}">
	<input type="button" value="스크랩해제" id="scrapBtn">
</c:if> 
		
<div class="page-breadcrumb">
	<div class="table-wrap">
		<table class="table">
			<tr>
				<th>프로젝트명</th>
				<td>${vo.projectName }</td>
			</tr>
			<tr>
				<th>프로젝트 규모</th>
				<td>${vo.projectScale }</td>
			</tr>
			<tr>
				<th>예상단가</th>
				<td>${vo.projectPriceValue }</td>
			</tr>
			<tr>
				<th>요구사항</th>
				<td>${vo.projectReq }</td>
			</tr>
			<tr>
				<th>요구기술스택</th>
				<td>${vo.projectTech }</td>
			</tr>
			<tr>
				<th>요구직무</th>
				<td>${vo.projectJob }</td>
			</tr>
			<tr>
				<th>공고기한</th>
				<td>${vo.limitDate }</td>
			</tr>
		</table>
	</div>	
</div>		
		
<c:if test="${authMember.memRole eq 'DEV'}">
<input type="button" value="지원" id="applyBtn">
</c:if>		
		
	</section>
</div>		

<form id="applyForm" action="${cPath}/outs/hire/Apply.do" method="post">
	<input type="hidden" name="boNum" value="${vo.boNum}">
</form>
<form id="scrapForm" action="${cPath}/outs/board/projectboard/projectUnscrap.do" method="post">
	<input type="hidden" name="boNum" value="${vo.boNum}">
</form>

<script>
$("#repBtn").on("click", function(){
	let clickBtn = $(this);
	let url = clickBtn.data("url");
	let what = $(this).data("what");
	$.ajax({
		url : url,
		data : {
			what:what
		},
		dataType : "text",
		success : function(resp) {
			if(resp=="OK"){
				alert("신고 완료!")
			}else{
				alert("신고 실패!")
			}
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
});


	let returnForm = $("#returnForm")
	let scrapForm = $("#scrapForm")
	let result;
	
	$("#applyBtn").on("click", function () {
		//테스트 배포기간에 여유되면 글 조회 할 때 지원기록 조회해서 ${funtion} + 버튼 아이콘 변경
		result = confirm('${vo.projectName }에 지원/취소하시겠습니까?')
		if(result){
			applyForm.submit();
		}else{
			return false;
		}
	});
	
	
	$("#scrapBtn").on("click", function(){
		result = confirm('스크랩을 해제 하시겠습니까?')
		if(result){
			scrapForm.submit();
		}else{
			return false; 
		}
	})

</script>




