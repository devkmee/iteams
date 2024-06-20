<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
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
			<div class="writing-view">
				<div class="writing-title m-bottom-20">
					<input class="form-control" type="text" placeholder="제목을 입력하세요">
				</div>
			</div>
			<div class="writing-content">
			<textarea id = "editor4" name = "editor4"></textarea>
			</div>
		   <div class="form-group" id="fileArea" name="attachFiles">
  			<div name="attachFiles">
		   		<label for="exampleFormControlInput1">첨부파일</label>
			   	<input type="file" name="attachFiles" multiple />
				<input type="button" value="추가" id="plus" class="ctlBtn"/>
				<input type="button" value="제거" id="minus" class="ctlBtn"/>
			</div>
   		   </div>
			<div class="board-btn-wrap">
				<button type="button" class="btn btn-primary">완료</button>
				<button type="button" class="btn btn-secondary">취소</button>
			</div>
		</div>
</section>

<script type="text/javascript">
      CKEDITOR.replace("editor4", {
         filebrowserImageUploadUrl:"${cPath}/board/imageUpload.do?type=Images"
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
