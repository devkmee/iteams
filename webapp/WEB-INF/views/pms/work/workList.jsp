<%@page import="kr.or.ddit.iteams.common.vo.MasterVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="sub-title-wrap m-bottom-20">
   <i class="fas fa-chevron-right" aria-hidden="true"></i>
          일감
</div>
	<c:if test="${authMember.memRole eq 'DEV'}"> 
		<div>
			<input id="myWork" type="button" class="pms-btn width-60" value="개인" />
			<input id="totalWork" type="button" class="pms-btn width-60" value="전체" />
		</div>
	</c:if>
     <div class="flex-end m-bottom-10">
     	<div style="text-align: end;">
	      <label for="filterAdd">검색조건 추가</label>
	      <c:if test="${authMember.memRole eq 'DEV'}">
			      <select name="" class="filterAdd myFilter pms-select" aria-label="Default select example">
			         <option value selected></option>
			         <option value="workTitle">제목</option>
			         <option value="workPriority">우선순위</option>
			         <option value="workType">유형</option>
			         <option value="workState">상태</option>
			      </select>
		      	  <select name="" class="filterAdd totalFilter pms-select" aria-label="Default select example">
			         <option value selected></option>
			         <option value="workTitle">제목</option>
			         <option value="workCharger">담당자</option>
			         <option value="workPriority">우선순위</option>
			         <option value="workType">유형</option>
			         <option value="workState">상태</option>
			      </select>
		      <button type="button" class="btn pms-btn width-60" id="myFilterBtn">적용</button>
		      <script>
		     	 $(".totalFilter").hide();
		      
		      	$("#myWork").on("click", function() {
					$(".myFilter").show();
					$(".totalFilter").hide();
				})
				
		      	$("#totalWork").on("click", function() {
					$(".myFilter").hide();
					$(".totalFilter").show();
				})
		      </script>	      
	      </c:if>
	      <c:if test="${authMember.memRole ne 'DEV'}">
		      <select name="" class="filterAdd pms-select" aria-label="Default select example">
		         <option value selected></option>
		         <option value="workCharger">담당자</option>
		         <option value="workTitle">제목</option>
		         <option value="workPriority">우선순위</option>
		         <option value="workType">유형</option>
		         <option value="workState">상태</option>
		      </select>
		      <button type="button" class="btn pms-btn width-60" id="filterBtn">적용</button>
	      </c:if>
	      
	      <div style="text-align: end;">
		      <div id="filterArea">   
		         <div class="filterWrapper">      
		         <label for="charger">담당자 : </label>
		         <select id="workCharger" class="filterInput pms-select">
		            <option value selected></option>
		            <c:if test="${not empty projectMember}">
		               <c:forEach items="${projectMember }" var="member">
		                  <c:if test="${member.authority eq 'PM'}">
		                     <option value="${member.devId }" <c:if test="${member.devId eq searchVO.workCharger}">selected</c:if> >${member.managerName }</option>
		                  </c:if>
		                  <c:if test="${member.authority ne 'PM'}">
		                     <option value="${member.devId }" <c:if test="${member.devId eq searchVO.workCharger}">selected</c:if> >${member.devName }</option>
		                  </c:if>
		               </c:forEach>
		            </c:if>
		         </select>
		         <button type="button" class="btn btn-delete width-60">제외</button>
		         </div>
		         
		         <div class="filterWrapper">   
		         <label for="">제목 : </label>
		         <input type="text" id="workTitle" class="filterInput" value="${searchVO.workTitle }"/>
		         <button type="button" class="btn btn-delete width-60">제외</button>
		         </div>
		         
		         <div class="filterWrapper" >   
		         <label for="">우선순위 : </label>
		         <select id="workPriority" class="filterInput pms-select">
		            <option value selected></option>
		            <option value="1" <c:if test="${searchVO.workPriority eq '1'}">selected</c:if> >낮음</option>
		             <option value="2" <c:if test="${searchVO.workPriority eq '2'}">selected</c:if> >보통</option>
		             <option value="3" <c:if test="${searchVO.workPriority eq '3'}">selected</c:if> >높음</option>
		             <option value="4" <c:if test="${searchVO.workPriority eq '4'}">selected</c:if> >긴급</option>
		             <option value="5" <c:if test="${searchVO.workPriority eq '5'}">selected</c:if> >즉시</option>
		         </select>
		         <button type="button" class="btn btn-delete width-60">제외</button>
		         </div>
		         
		         <div class="filterWrapper">   
		         <label for="">유형 : </label>
		         <select id="workType" class="filterInput pms-select">
		            <option value selected></option>
		            <option value="1" <c:if test="${searchVO.workType eq '1'}">selected</c:if> >새기능</option>
		             <option value="2" <c:if test="${searchVO.workType eq '2'}">selected</c:if> >결함</option>
		             <option value="3" <c:if test="${searchVO.workType eq '3'}">selected</c:if> >지원</option>
		             <option value="4" <c:if test="${searchVO.workType eq '4'}">selected</c:if> >공지</option>
		         </select>
		         <button type="button" class="btn btn-delete width-60">제외</button>
		         </div>
		         
		         <div class="filterWrapper">   
		         <label for="">상태 : </label>
		         <select id="workState" class="filterInput pms-select">
		            <option value selected></option>
		            <option value="1" <c:if test="${searchVO.workState eq '1'}">selected</c:if> >신규</option>
		            <option value="2" <c:if test="${searchVO.workState eq '2'}">selected</c:if> >진행</option>
		             <option value="3" <c:if test="${searchVO.workState eq '3'}">selected</c:if> >해결</option>
		             <option value="4" <c:if test="${searchVO.workState eq '4'}">selected</c:if> >의견</option>
		             <option value="5" <c:if test="${searchVO.workState eq '5'}">selected</c:if> >완료</option>
		         </select>
		         <button type="button" class="btn btn-delete width-60">제외</button>
		         </div>
		      </div>
	      </div>
     	</div>
     </div>
      
      <table class="table" style="text-align: center;">
         <thead>
             <tr class="tr-gray">
               <th scope="col">#</th>
               <th scope="col">유형</th>
               <th scope="col">상태</th>
               <th scope="col">우선순위</th>
               <th scope="col">제목</th>
               <th scope="col">담당자</th>
               <th scope="col">변경일</th>
               <th scope="col">첨부파일</th>
             </tr>
           </thead>
           <tbody id="listBody">
         </tbody>
      </table>
      <div class="paging-wrap">
          <div id="pagingArea"></div>   
      </div>
      <div class="flex-end">
      	<div>
		   <button type="button" class="linkBtn btn pms-btn width-120" data-gopage="${cPath }/pms/work/${authMember.proNo }/workInsert.do">새 일감 등록</button>
      	</div>
      </div>

<form action="" id="searchForm">
<input type="hidden" name="page" value="${searchVO.page }"/>
<input type="hidden" name="workCharger" value="${searchVO.workCharger }"/>
<input type="hidden" name="workTitle" value="${searchVO.workTitle }"/>
<input type="hidden" name="workPriority" value="${searchVO.workPriority }"/>
<input type="hidden" name="workType" value="${searchVO.workType }"/>
<input type="hidden" name="workState" value="${searchVO.workState }"/>
</form>
<script>
   
   let listBody = $("#listBody");
   let pagingArea = $("#pagingArea")
   let pageTag = $("[name='page']")
   

      let charger = $("[name='workCharger']").val()
      let title = $("[name='workTitle']").val()
      let priority = $("[name='workPriority']").val()
      let type = $("[name='workType']").val()
      let state = $("[name='workState']").val()
      console.log(charger)
      console.log(title)
      console.log(priority)
      console.log(type)
      console.log(state)
      
         
      if(charger) {
         $("#workCharger").parents(".filterWrapper").show()
      } else if(!charger) {
         $("#workCharger").parents(".filterWrapper").hide()
      }
      
      if(title) {
         $("#workTitle").parents(".filterWrapper").show()
      } else if(!title) {
         $("#workTitle").parents(".filterWrapper").hide()
      }
      
      if(priority) {
         $("#workPriority").parents(".filterWrapper").show()
      } else if(!priority) {
         $("#workPriority").parents(".filterWrapper").hide()
      }
      
      if(type) {
         $("#workType").parents(".filterWrapper").show()
      } else if(!type) {
         $("#workType").parents(".filterWrapper").hide()
      }
      
      if(state) {
         $("#workState").parents(".filterWrapper").show()
      } else if(!state) {
         console.log("dsa")
         $("#workState").parents(".filterWrapper").hide()
      }
   
   if("${authMember.memRole}" === "DEV") {	   
	   myWork()
	   $("#workCharger").parents(".filterWrapper").hide()
	   function myWork() {
		   $("#workCharger").parents(".filterWrapper").hide()
		   $("#searchForm").find("[name='workCharger']").val("${authMember.memId}")
		   $("[name='workTitle']").val("")
      	   $("[name='workPriority']").val("")
           $("[name='workType']").val("")
           $("[name='workState']").val("")
		   let searchForm = $("#searchForm").paging().ajaxForm({
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
		            $.each(dataList, function(idx, work) {
		               console.log(work)
		               let trTag = $("<tr>").append(
		                  $("<th>").attr("scope", "row").text(work.rnum),
		                  $("<td>").text(work.workTypeValue),
		                  $("<td>").text(work.workStateValue),
		                  $("<td>").text(work.workPriorityValue),
		                  $("<td>").text(work.workTitle).attr({class:"text-left"}),
		                  $("<td>").text(work.chargerName),
		                  $("<td>").text(work.modifyDate)
		               ).attr({
		                  "class" : "linkBtn",
		                  "data-gopage" : "${cPath}/pms/work/${authMember.proNo}/workView.do?what="+work.workNum,
		                  "style" : "cursor : pointer;"
		               })
		               
		               let att = null;
		               
		               if(work.attachList.length > 0) {
		                  att = $("<td>").text("O")
		               } else {
		                  att = $("<td>").text("X")
		               }
		               
		               trTag.append(att)
		               
		               trTags.push(trTag)
		            })
		            
		         } else {
		            let trTag = $("<tr>").append($("<td>").text("등록된 일감이 없습니다."))
		            trTags.push(trTag)
		         }
		         
		         listBody.append(trTags)
		         pagingArea.html(pagingHTML)
		         pageTag.val("")
		         $("[name='workCharger']").val("")
		      },
		      error : function(x) {
		         console.log(x.status + "ddddddddd")
		         console.log(x)
		      }
		   }).submit()
		}
	   
	   $("#totalWork").on("click", function() {
			totalWork();
		})
		
	   $("#myWork").on("click", function() {
			myWork();
		})
	   
	   function totalWork() {

		   let searchForm = $("#searchForm").paging().ajaxForm({
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
			            $.each(dataList, function(idx, work) {
			               console.log(work)
			               let trTag = $("<tr>").append(
			                  $("<th>").attr("scope", "row").text(work.rnum),
			                  $("<td>").text(work.workTypeValue),
			                  $("<td>").text(work.workStateValue),
			                  $("<td>").text(work.workPriorityValue),
			                  $("<td>").text(work.workTitle).attr({class:"text-left"}),
			                  $("<td>").text(work.chargerName),
			                  $("<td>").text(work.modifyDate)
			               ).attr({
			                  "class" : "linkBtn",
			                  "data-gopage" : "${cPath}/pms/work/${authMember.proNo}/workView.do?what="+work.workNum,
			                  "style" : "cursor : pointer;"
			               })
			               
			               let att = null;
			               
			               if(work.attachList.length > 0) {
			                  att = $("<td>").text("O")
			               } else {
			                  att = $("<td>").text("X")
			               }
			               
			               trTag.append(att)
			               
			               trTags.push(trTag)
			            })
			            
			         } else {
			            let trTag = $("<tr>").append($("<td>").text("등록된 일감이 없습니다."))
			            trTags.push(trTag)
			         }
			         
			         listBody.append(trTags)
			         pagingArea.html(pagingHTML)
			         pageTag.val("")
			         
			      },
			      error : function(x) {
			         console.log(x.status + "ddddddddd")
			         console.log(x)
			      }
			   }).submit()
			}	   
   } else {
	   let searchForm = $("#searchForm").paging().ajaxForm({
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
	            $.each(dataList, function(idx, work) {
	               console.log(work)
	               let trTag = $("<tr>").append(
	                  $("<th>").attr("scope", "row").text(work.rnum),
	                  $("<td>").text(work.workTypeValue),
	                  $("<td>").text(work.workStateValue),
	                  $("<td>").text(work.workPriorityValue),
	                  $("<td>").text(work.workTitle).attr({class:"text-left"}),
	                  $("<td>").text(work.chargerName),
	                  $("<td>").text(work.modifyDate)
	               ).attr({
	                  "class" : "linkBtn",
	                  "data-gopage" : "${cPath}/pms/work/${authMember.proNo}/workView.do?what="+work.workNum,
	                  "style" : "cursor : pointer;"
	               })
	               
	               let att = null;
	               
	               if(work.attachList.length > 0) {
	                  att = $("<td>").text("O")
	               } else {
	                  att = $("<td>").text("X")
	               }
	               
	               trTag.append(att)
	               
	               trTags.push(trTag)
	            })
	            
	         } else {
	            let trTag = $("<tr>").append($("<td>").text("등록된 일감이 없습니다."))
	            trTags.push(trTag)
	         }
	         
	         listBody.append(trTags)
	         pagingArea.html(pagingHTML)
	         pageTag.val("")
	         
	      },
	      error : function(x) {
	         console.log(x.status + "ddddddddd")
	         console.log(x)
	      }
	   }).submit()	   
   }

   let chargerArea = $("#workCharger");
   
   let workCharger = $("#workCharger");
   let workTitle = $("#workTitle");
   let workPriority = $("#workPriority");
   let workType = $("#workType");
   let workState = $("#workState");
   
//    $(".filterWrapper").hide()
   
   $(".filterAdd").on("change", function() {
      let searchName = $(this).find("option:selected").val()
      let filterTag = $("#" + searchName)
      let filterDiv = filterTag.parents(".filterWrapper")
      filterDiv.show()
   });
   
   $(".btn-delete").on("click", function() {
      let filterDiv = $(this).parents(".filterWrapper")
      filterDiv.toggle()
      filterDiv.find(".filterInput").val("")
   });
   
   
   $("#myFilterBtn").on("click", function() {
	  let chargerFilterVal = workCharger.val()
      let titleFilterVal = workTitle.val()
      let priorityFilterVal = workPriority.val()
      let typeFilterVal = workType.val()
      let stateFilterVal = workState.val()
      
      if(chargerFilterVal !== "${authMember.memId}") {
    	  $("[name='workCharger']").val(chargerFilterVal)
      } else {
	      $("[name='workCharger']").val("${authMember.memId}")    	  
      }
      
      $("[name='workTitle']").val(titleFilterVal)
      $("[name='workPriority']").val(priorityFilterVal)
      $("[name='workType']").val(typeFilterVal)
      $("[name='workState']").val(stateFilterVal);
      
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
	            $.each(dataList, function(idx, work) {
	               console.log(work)
	               let trTag = $("<tr>").append(
	                  $("<th>").attr("scope", "row").text(work.rnum),
	                  $("<td>").text(work.workTypeValue),
	                  $("<td>").text(work.workStateValue),
	                  $("<td>").text(work.workPriorityValue),
	                  $("<td>").text(work.workTitle).attr({class:"text-left"}),
	                  $("<td>").text(work.chargerName),
	                  $("<td>").text(work.modifyDate)
	               ).attr({
	                  "class" : "linkBtn",
	                  "data-gopage" : "${cPath}/pms/work/${authMember.proNo}/workView.do?what="+work.workNum,
	                  "style" : "cursor : pointer;"
	               })
	               
	               let att = null;
	               
	               if(work.attachList.length > 0) {
	                  att = $("<td>").text("O")
	               } else {
	                  att = $("<td>").text("X")
	               }
	               
	               trTag.append(att)
	               
	               trTags.push(trTag)
	            })
	            
	         } else {
	            let trTag = $("<tr>").append($("<td>").text("등록된 일감이 없습니다."))
	            trTags.push(trTag)
	         }
	         
	         listBody.append(trTags)
	         pagingArea.html(pagingHTML)
	         pageTag.val("")
	         $("[name='workCharger']").val("")
	      },
	      error : function(x) {
	         console.log(x.status + "ddddddddd")
	         console.log(x)
	      }
      }).submit();   
	});
   
   $("#filterBtn").on("click", function() {
      
      let chargerFilterVal = workCharger.val()
      let titleFilterVal = workTitle.val()
      let priorityFilterVal = workPriority.val()
      let typeFilterVal = workType.val()
      let stateFilterVal = workState.val()
      
      $("[name='workCharger']").val(chargerFilterVal)
      $("[name='workTitle']").val(titleFilterVal)
      $("[name='workPriority']").val(priorityFilterVal)
      $("[name='workType']").val(typeFilterVal)
      $("[name='workState']").val(stateFilterVal);
      
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
	            $.each(dataList, function(idx, work) {
	               console.log(work)
	               let trTag = $("<tr>").append(
	                  $("<th>").attr("scope", "row").text(work.rnum),
	                  $("<td>").text(work.workTypeValue),
	                  $("<td>").text(work.workStateValue),
	                  $("<td>").text(work.workPriorityValue),
	                  $("<td>").text(work.workTitle).attr({class:"text-left"}),
	                  $("<td>").text(work.chargerName),
	                  $("<td>").text(work.modifyDate)
	               ).attr({
	                  "class" : "linkBtn",
	                  "data-gopage" : "${cPath}/pms/work/${authMember.proNo}/workView.do?what="+work.workNum,
	                  "style" : "cursor : pointer;"
	               })
	               
	               let att = null;
	               
	               if(work.attachList.length > 0) {
	                  att = $("<td>").text("O")
	               } else {
	                  att = $("<td>").text("X")
	               }
	               
	               trTag.append(att)
	               
	               trTags.push(trTag)
	            })
	            
	         } else {
	            let trTag = $("<tr>").append($("<td>").text("등록된 일감이 없습니다."))
	            trTags.push(trTag)
	         }
	         
	         listBody.append(trTags)
	         pagingArea.html(pagingHTML)
	         pageTag.val("")
	         $("[name='workCharger']").val("")
	      },
	      error : function(x) {
	         console.log(x.status + "ddddddddd")
	         console.log(x)
	      }
      }).submit();
   })
   
</script>