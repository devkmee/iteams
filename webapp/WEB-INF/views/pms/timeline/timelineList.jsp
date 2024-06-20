<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>

a:hover {
    color: #3f51b5;
    text-decoration: none;
}
a {
    color: #5a5a5a;
    text-decoration: none;
    background-color: transparent;
    list-style: none;
}
</style>
<div class="sub-title-wrap m-bottom-20">
    <i class="fas fa-chevron-right" aria-hidden="true"></i>
             작업내역 
</div>
<div class="page-breadcrumb">
	<ul class="list-group" id="listArea">
	</ul>
	<div id="pagingArea" class="m-top-20" style="display: flex; justify-content: center;"></div>

</div>
<form action="" id="searchForm">
<input type="hidden" name="page" />
</form>
<script>	
	let listArea = $("#listArea");
	let pagingArea = $("#pagingArea")
	let pageTag = $("[name='page']")
	
	let searchForm = $("#searchForm").paging().ajaxForm({
		dataType : "json",
		success : function(resp) {
			console.log(resp)
			let dataList = resp.dataList
			let listTags = []
			let pagingHTML = resp.pagingHTML
			
			listArea.empty();
			pagingArea.empty();
			
			if(dataList) {
				$.each(dataList, function(idx, timeLine) {
					console.log(timeLine)
					var state = timeLine.deletedNy
					var listTag = null;
					if(state === 'N') {
						listTag = $("<li>").append(
							$("<a>").text("작업 시각 : " + timeLine.timelineDate + " / " + timeLine.timelineContent)
								.attr("href", timeLine.timelineUrl)
						).attr("class", "list-group-item")
					} else if(state === 'Y') {
						listTag = $("<li>").text("작업시각 : " + timeLine.timelineDate + " / " + timeLine.timelineContent)
												.attr("class", "list-group-item")
												.css("color", "#f53c2e")				
					}
					listTags.push(listTag)
				})
							
			} else {
				let listTag = $("<li>").text("작업 내역이 없습니다.").attr("class", "list-group-item")
				listTags.push(listTag)
			}
			
			listArea.append(listTags)
			pagingArea.html(pagingHTML)
			pageTag.val("")
			
		},
		error : function(x) {
			console.log(x.status)
		}
	}).submit()
</script>