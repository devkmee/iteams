<%@page import="java.io.IOException"%>
<%@page import="org.apache.commons.net.ftp.FTPFile"%>
<%@page import="org.apache.commons.net.ftp.FTPReply"%>
<%@page import="org.apache.commons.net.ftp.FTPClient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/skin-win8/ui.fancytree.min.css" rel="stylesheet">
<script type="text/javascript" href="/iteams/webapp/resources/js/fancytree/skin-win8/ui.fancytree.css">
<!--

-->
</script>
<script src="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/jquery.fancytree-all-deps.min.js"></script>
<div class="sub-title-wrap m-bottom-20">
   <i class="fas fa-chevron-right" aria-hidden="true"></i>
          웹하드
</div>
<div class="tree">
</div>
<br>
<form:form id="inputForm" method="post" action="${cPath }/pms/webhard/${authMember.proNo }/webhardInsert.do" modelAttribute="masterVO" enctype="multipart/form-data">
<div class="form-group" id="fileArea" name="attachFiles">
   		<div name="attachFiles">
	   		<label for="exampleFormControlInput1">첨부파일</label>
		   	<input type="file" name="attachFiles" multiple />
			<input type="button" value="추가" id="plus" class="pms-btn width-60"/>
			<input type="button" value="제거" id="minus" class="pms-btn width-60"/>
		</div>
   </div>
   <button type="submit" class="pms-btn btn-primary width-60" >업로드</button>
   <button type="button" id="download" class="pms-btn btn-primary width-60">다운로드</button>
   <button type="button" id="delete" class="pms-btn btn-primary width-60">삭제</button>
</form:form>

<form id="downloadForm" name="form" method="post">
<input type="hidden" name="attNo" />

</form>
<script>
	let a =[];
	<c:set var="filelist" value="${fileList }" />
	<c:forEach items="${filelist }" var="file" >
		a.push({
			title : "${file.fileName}", checkbox:true, click: function(event,data){},
			attNo: "${file.attNo}"
		
		})
	</c:forEach>
	
	$(function(){
    
	// using default options
    $(".tree").fancytree({
    	source: [
    	    {title: "${authMember.proNo }",expanded:true, folder: true, children: 	
    	    	a
    	      }
    	    ],
    	    
        select: function(e, data) {
            var selAttNos = $.map(data.tree.getSelectedNodes(), function(node){
                return node.data.attNo;
            });
            let attNoValue = selAttNos.join(",");
            $("[name='attNo']").val(attNoValue)
        }
    })

  });

	let fileArea = $("#fileArea").on("click", ".pms-btn", function(){
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
	 
	$(".tree").click(function(event,data){
		  var tree = $(".tree").fancytree("getTree"),
		      node = tree.getActiveNode();
		
		
// 		  window.location.href = '${cPath}/pms/${authMember.proNo }/download.do?what='+node.data.attNo
		}); 
	
	$("#download").on("click", function(){
		form =document.form;
		form.action="${cPath }/pms/webhard/${authMember.proNo}/webhardDownload.do"
		form.submit();
	})
	$("#delete").on("click", function(){
		form =document.form;
		form.action="${cPath }/pms/webhard/${authMember.proNo}/webhardDelete.do"
		form.method = "post"
		form.submit();
	})
	
	
</script>