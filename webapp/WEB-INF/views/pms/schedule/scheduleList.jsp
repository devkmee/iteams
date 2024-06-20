<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://cdn.jsdelivr.net/npm/@fullcalendar/core@4.4.2/main.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/@fullcalendar/daygrid@4.4.2/main.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/@fullcalendar/timegrid@4.4.2/main.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/@fullcalendar/core@4.4.2/main.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@fullcalendar/interaction@4.4.2/main.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@fullcalendar/daygrid@4.4.2/main.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@fullcalendar/timegrid@4.4.2/main.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script src="${cPath }/resources/js/accessible-minimodal-master/dist/accessible-minimodal.min.js"></script>
<link rel="stylesheet" href="${cPath }/resources/js/datetimepicker-master/build/jquery.datetimepicker.min.css" />
<script src="${cPath }/resources/js/datetimepicker-master/build/jquery.datetimepicker.full.min.js"></script>
<div class="page-breadcrumb">
<div class="sub-title-wrap m-bottom-20">
    <i class="fas fa-chevron-right" aria-hidden="true"></i>
     캘린더
</div>
	  <div id="inputArea" style="margin-bottom: 10px;">
      <form method="post" id="inputForm" action="">
      	 내용 : <input type="text" name="planContent" />
      	 시작일 : <input type="text" name="startDate" class="datetimepicker" readonly="readonly" />
      	 종료일 : <input type="text" name="endDate" class="datetimepicker2" readonly="readonly" />
      	 <input type="hidden" name="what"/>
      	 <input type="hidden" name="writer" />
      <button class="pms-btn width-60">저장</button>
      </form>
	  </div>
	  <div id="updateArea" style="margin-bottom: 10px;">
      <form method="post" id="updateForm" action="">
      	 내용 : <input type="text" id="planContent" name="planContent"/>
      	 시작일 : <input type="text" id="startDate" name="startDate" class="datetimepicker" readonly="readonly" />
      	 종료일 : <input type="text" id="endDate" name="endDate" class="datetimepicker2" readonly="readonly"/>
      	 <input type="hidden" id="what" name="what"/>
      	 <input type="hidden" id="writer" name="writer" />
      <button class="pms-btn width-60" >저장</button>
      <input class="pms-btn width-60" type="button" value="삭제" id="deleteBtn"/>
      </form>
	  </div>
 <div id='calendar'></div>
</div>
<div class="m-top-10 flex-end">
	<button data-gopage="${cPath }/pms/schedule/${authMember.proNo}/scheduleExcel.do" type="button" class="linkBtn width-120 pms-btn">Excel로 내보내기</button>  
	<button id="modal-open-btn-example" data-modal-open="modal-example" style="display: none"></button>
</div>
<script>
jQuery('.datetimepicker').datetimepicker();
jQuery('.datetimepicker2').datetimepicker();

let inputArea = $("#inputArea").hide();
let inputForm = $("#inputForm")
let updateArea = $("#updateArea").hide();
let updateForm = $("#updateForm")

$("#deleteBtn").on("click", function() {
	
	let valid = confirm("정말 삭제하시겠습니까?");
	if(!valid) return;
	
	updateForm.attr("action", "${cPath }/pms/schedule/${authMember.proNo }/scheduleDelete.do")
	updateForm.submit()
});

const modal = AccessibleMinimodal.init({
	outsideClose: true,
	style: {
        use: true,
        width: 400,
        valign: 'center', // center, top, bottom
        openAnimation: 'from-bottom', // // from-bottom, from-top, from-left, from-right, zoom-in, zoom-out, fade
        animationDuration: 400
      },
      triggers: {
        open: 'data-modal-open',
        close: 'data-modal-close'
      }
})
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
    	plugins: [ 'dayGrid', 'timeGrid', 'list', 'interaction' ],
    	header: {
    		left: 'prev,next, today addEventButton',
    		center: 'title',
    		right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
    	},
    	defaultView: 'timeGridWeek',
    	locale: 'ko',
    	navLinks: true, // can click day/week names to navigate views
    	editable: true,
    	allDaySlot: false,
    	eventLimit: true, // allow "more" link when too many events
    	minTime: '10:00:00',
    	maxTime: '24:00:00',
    	contentHeight: 'auto',
    	eventSources: [{
    		events: function(info, successCallback, failureCallback) {
    			$.ajax({
    				url: '${cPath}/pms/schedule/${authMember.proNo}/scheduleList.do',
    				type: 'POST',
    				dataType: 'json',
    				data: {
    					startDate : moment(info.startStr).format('YYYY-MM-DD'),
    					endDate : moment(info.endStr).format('YYYY-MM-DD')
    				},
    				success: function(data) {
    					successCallback(data);
    				}
    			});
    		}
    	}],
    	
    	customButtons: {
            addEventButton: {
              text: '일정 등록',
              click: function() {
            	  inputArea.toggle()
				  inputForm.attr("action", "${cPath }/pms/schedule/${authMember.proNo }/scheduleInsert.do");
              }        
            }
                    
         },
         
         eventClick:function(event) {
             if(event) {
            	 console.log(event.event._def.extendedProps)
                 let planNum = event.event._def.extendedProps.planNum;
                 let startDate = event.event._def.extendedProps.startDate;
                 let endDate = event.event._def.extendedProps.endDate;
                 let writer = event.event._def.extendedProps.writer;
                 let content = event.event._def.extendedProps.planContent;
                 $("#what").val(planNum)
                 $("#writer").val(writer)
                 $("#planContent").val(content)
                 $("#startDate").val(startDate)
                 $("#endDate").val(endDate)
                 updateForm.attr("action", "${cPath }/pms/schedule/${authMember.proNo }/scheduleUpdate.do");
                 updateArea.toggle()
                 
                 return false;
             }
         }
    	
    });

    calendar.render();
  });

</script>
