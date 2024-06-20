<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.form-select {
	border: 1px solid #b3b3b3; 
	padding: 4px;
	margin-bottom: 10px;
 }
</style>


<link href="${cPath }/resources/js/pms/work/Simple-Gant-master/frappe-gantt.css" rel="stylesheet" />
<script src="${cPath }/resources/js/pms/work/Simple-Gant-master/frappe-gantt.js"></script>
<select id="viewMode2" class="form-select" aria-label="Default select example">
  <option value selected>모드선택</option>
  <option value="Quarter Day">Quarter Day</option>
  <option value="Half Day">Half Day</option>
  <option value="Day">Day</option>
  <option value="Week">Week</option>
  <option value="Month">Month</option>
  <option value="Year">Year</option>
</select>

<div style="width: 500px; height: 300px;">
<div class="gantt-target2"></div>
</div>
<script>
let tasks2 = []
let gantt_chart2 = null
let gantt2 = $(".gantt-target2")
let writerMap2 = new Map();
let chargerMap2 = new Map();

$("#viewMode2").on("change", function() {
	let viewMode2 = $(this).val()
	if(!viewMode2) viewMode2 = "Month"
	tasks2 = []
	$.ajax({
	url : "${cPath}/pms/work/${authMember.proNo}/gantt.do",
	dataType : "json",
	success : function(resp) {
		$.each(resp, function(idx, work) {
			let charger = work.workCharger == null ? "없음" : work.workCharger
			let obj = {
				start: work.startDate,
			    end: work.endDate,
			    name: work.workTitle + " / 담당자 : " + charger,
			    people: charger,
			    id: work.workNum,
			    progress: work.workProgress,
			    dependencies: work.workParent,
			    writer : work.workWriter,
			    charger : charger
			}
			tasks2.push(obj)
		})
		gantt_chart2 = new Gantt(".gantt-target2", tasks2, {
		    header_height: 50,
		    column_width: 100,
		    step: 24,
		    view_modes: [
		      'Quarter Day',
		      'Half Day',
		      'Day',
		      'Week',
		      'Month',
		      'Year'
		    ],
		    bar_height: 20,
		    bar_corner_radius: 3,
		    arrow_curve: 5,
		    padding: 18,
		    view_mode: viewMode2,
		    date_format: 'YYYY-MM-DD',
		    popup_trigger: 'click',
// 		    custom_popup_html: null,
		    language: 'en',
		    
		    on_click: function (task, event) {
		        console.log(task);
		     },
		     on_date_change: function(task, start, end) {
		    	let startMonth = start.getMonth() + 1
		    	let endMonth = end.getMonth() + 1
		   
		    	let startDate = start.getFullYear() + "-" + startMonth + "-" + start.getDate()
		    	let endDate = end.getFullYear() + "-" + endMonth + "-" + end.getDate()
		        console.log(startDate, endDate);
		    	
		    	let workWriter = writerMap2.get(task.id)
		    	let workCharger = chargerMap2.get(task.id)
		    	
		    	console.log(workWriter)
		    	console.log(workCharger)
		    	
		    	$.ajax({
					url : "${cPath}/pms/work/${authMember.proNo}/ganttUpdate.do",
					method : "post",
					dataType : "json",
					data : {
						workNum : task.id,
						startDate : startDate,
						endDate : endDate,
						writer : workWriter,
						charger : workCharger
					},
					success : function(resp) {
						console.log(resp)
					},
					error : function(xhr, errorResp, error) {
						console.log(xhr);
						console.log(errorResp);
						console.log(error);
					}
				})
		     },
		     on_progress_change: function(task, progress) {
		        console.log(task, progress);
		        let workWriter = writerMap2.get(task.id)
		    	let workCharger = chargerMap2.get(task.id)
		        $.ajax({
					url : "${cPath}/pms/work/${authMember.proNo}/ganttUpdate.do",
					method : "post",
					dataType : "json",
					data : {
						workNum : task.id,
						startDate : task.start,
						endDate : task.end,
						workProgress : progress,
						writer : workWriter,
						charger : workCharger
					},
					success : function(resp) {
						console.log(resp)
					},
					error : function(xhr, errorResp, error) {
						console.log(xhr);
						console.log(errorResp);
						console.log(error);
					}
				})
		     },
		     on_view_change: function(mode) {
		        
		     },
		     
		     
		});
		
	},
	error : function(xhr, errorResp, error) {
		console.log(xhr);
		console.log(errorResp);
		console.log(error);
	}
})
})


$.ajax({
	url : "${cPath}/pms/work/${authMember.proNo}/gantt.do",
	dataType : "json",
	success : function(resp) {
		$.each(resp, function(idx, work) {
			let charger = work.workCharger == null ? "없음" : work.workCharger
			writerMap2.set(work.workNum, work.workWriter)
			chargerMap2.set(work.workNum, work.workCharger)
			let obj = {
				start: work.startDate,
			    end: work.endDate,
			    name: work.workTitle + " / 담당자 : " + charger,
			    people: charger,
			    id: work.workNum,
			    progress: work.workProgress,
			    dependencies: work.workParent
			}
			tasks2.push(obj)
		})
		gantt_chart2 = new Gantt(".gantt-target2", tasks2, {
		    header_height: 50,
		    column_width: 100,
		    step: 24,
		    view_modes: [
		      'Quarter Day',
		      'Half Day',
		      'Day',
		      'Week',
		      'Month',
		      'Year'
		    ],
		    bar_height: 20,
		    bar_corner_radius: 3,
		    arrow_curve: 5,
		    padding: 18,
		    view_mode: "Month",
		    date_format: 'YYYY-MM-DD',
		    popup_trigger: 'click',
// 		    custom_popup_html: null,
		    language: 'en',
		    
		    on_click: function (task, event) {
		        console.log(task);
		     },
		     on_date_change: function(task, start, end) {
		    	let startMonth = start.getMonth() + 1
		    	let endMonth = end.getMonth() + 1
		   
		    	let startDate = start.getFullYear() + "-" + startMonth + "-" + start.getDate()
		    	let endDate = end.getFullYear() + "-" + endMonth + "-" + end.getDate()
		        console.log(startDate, endDate);
		    	
		    	let workWriter = writerMap2.get(task.id)
		    	let workCharger = chargerMap2.get(task.id)
		    	$.ajax({
					url : "${cPath}/pms/work/${authMember.proNo}/ganttUpdate.do",
					method : "post",
					dataType : "json",
					data : {
						workNum : task.id,
						startDate : startDate,
						endDate : endDate,
						writer : workWriter,
						charger : workCharger
					},
					success : function(resp) {
						console.log(resp)
					},
					error : function(xhr, errorResp, error) {
						console.log(xhr);
						console.log(errorResp);
						console.log(error);
					}
				})
		     },
		     on_progress_change: function(task, progress) {
		        console.log(task, progress);
		        $.ajax({
					url : "${cPath}/pms/work/${authMember.proNo}/ganttUpdate.do",
					method : "post",
					dataType : "json",
					data : {
						workNum : task.id,
						startDate : task.start,
						endDate : task.end,
						workProgress : progress
					},
					success : function(resp) {
						console.log(resp)
					},
					error : function(xhr, errorResp, error) {
						console.log(xhr);
						console.log(errorResp);
						console.log(error);
					}
				})
		     },
		     on_view_change: function(mode) {
		        
		     },
		     
		     
		});
		
	},
	error : function(xhr, errorResp, error) {
		console.log(xhr);
		console.log(errorResp);
		console.log(error);
	}
})

</script>
