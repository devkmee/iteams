<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" integrity="sha512-aOG0c6nPNzGk+5zjwyJaoRUgCdOrfSDhmMID2u4+OIslr0GjpLKo7Xm0Ao3xmpM4T8AmIouRkqwj1nrdVsLKEQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script type="text/javascript" src="${cPath }/resources/js/ckeditor/ckeditor.js"></script>
	<div class="page-breadcrumb">
<script>
$(function() { 
	$( "#Date" ).datepicker({ 
		dateFormat: 'yy-mm-dd'
	}); 
});

</script>	
 
<form name="masterVO" method="post" enctype="multipart/form-data">
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">목표명(ex.요구사항정의서작성)</label>
    <input type="text" class="form-control" name="goalName" >
  </div>
  <div class="mb-3">
    <label class="form-label">생성자</label>
    <input type="hidden" class="form-control" name="goalWriter" value="${authMember.memId }" />
    <input type="text" class="form-control" name="managerName" value="${authMember.managerName }" readonly="true" />
  </div>
  <div class="mb-3">
    <label  class="form-label">완료일 선택</label>
    <input type="text" id="Date" name="completeDate" class="form-control"  />
  </div>
  <div class="mb-3">
    <label  class="form-label">상위목표</label>
    <input type="text" class="form-control" name="goalParent"  />
  </div>
  
		<table>
			<tr>
				<td colspan="2">
					<input type="submit" value="설정" class="btn btn-primary"/>
					<input type="reset" value="취소" class="btn btn-second" />
				</td>
			</tr>
		</table>
	</form>
</div>
