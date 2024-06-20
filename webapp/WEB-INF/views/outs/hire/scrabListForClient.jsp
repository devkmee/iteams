<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/hireList.css">
<div class="sub-wrap">
   <div class="sub-inner-box">
      <div class="sub-inner-txt">
         <span class="sub-txt">나의 스크랩
            <span class="project-txt-lighter">오늘보다 더 나은 내일을 꿈꿀 수 있는 곳<br>아이팀즈입니다.</span>
         </span>
      </div>
      <div class="main-outline-box-img m-right-10">
         <img alt="" src="/iteams/resources/images/hireList-img.png">
      </div>
   </div>
</div>
<section class="section-wrap">
<div class="project-list-title m-bottom-10 m-top-30">
      <span>스크랩한 팀원 목록 ></span>
   </div>
   <div class="table-wrap">
         <c:set var ="list" value="${data.dataList }"></c:set>
   <c:choose>
          <c:when test="${empty list }">
            <div class="interview-box-wrap">
                  <span>스크랩한 프로필이 없습니다</span>
               </div>  
          </c:when>
          <c:otherwise>
          <c:forEach var="interview" items="${list}" varStatus="status">
            <c:if test="${ status.index % 2 eq 0 }">
             <div class="scrap-box-wrap">
               <c:forEach var="j" begin="${ status.index }" end="${ status.index + (2 - 1) }" step="1">
               	<c:url value="/outs/hire/profileView.do" var="selectURL">
					<c:param name="devId" value="${list[j].devId}"></c:param>
				</c:url>
                <c:if test="${ list[j] ne null }">
                  <div class="scrap-box">
                  <button type="button" class="close btn-close" onclick="deleteScrab('${list[j].devId }')" aria-label="Close">
                     	<span aria-hidden="true">&times;</span>
                   	</button>
                  <div class="scrap-img linkBtn" data-gopage="${selectURL}">
                     <img src="${cPath}/imageRender.do?what=${list[j].devImg}" alt="${cPath}/resources/images/defaultProfileImage.png" style="width:100%; height:200px; object-fit:cover;"/>
                  </div>
                  <div class="scrap-txt linkBtn" data-gopage="${selectURL}">
                    <div>
                     <h5>${list[j].devName }</h5>
                     <span class="span-txt">직무 : ${list[j].devJob }</span>
                     <span class="span-txt">경력 : ${list[j].devCareer }</span>
                     <c:choose>
                           <c:when test="${empty list[j].devTechValue }">
                           <p><small class="text-muted">#${list[j].devTech }</small></p>
                        </c:when>
                        <c:otherwise>
                           <p><small>
                             <c:forEach items="${list[j].devTechValue }" var="tech">
                               #${tech }  
                             </c:forEach>
                           </small></p>
                        </c:otherwise>
                     </c:choose>
                    </div>
                  </div>
                 </div>
                </c:if>
               </c:forEach>
             </div>
            </c:if>
          </c:forEach>
            
          </c:otherwise>
        </c:choose>
   </div>
   <div class="paging-wrap">
      <div id="pagingArea"> ${data.pagingHTML } </div>   
   </div>
   <form id="pagingForm">
      <input type="hidden" name="page" />
   </form>
   
   <form id="deleteScrab" action="${cPath}/outs/hire/scrabdelete.do" method="post">
   	<input type="hidden" name="devId" />
   </form>
</section>
<script>
   let searchForm = $("#pagingForm").paging();
   
   //스크랩 삭제
   function deleteScrab(devId) {
		result = confirm('정말 삭제하시겠습니까?')
		if(result){
			$("#deleteScrab").find(":input[name=devId]").val(devId);
			$("#deleteScrab").submit();	
		}else{
			return false;
		}
	}
</script>