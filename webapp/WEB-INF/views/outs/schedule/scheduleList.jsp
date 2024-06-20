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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mypage.css">
<div class="login-content-wrap">
	<div class="mypage-wrap">
		<div class="sub-mypage-wrap">
			<div class="sub-inner-box">
				<div class="main-outline-box-img m-right-10">
					<img alt="" src="/iteams/resources/images/mypage-img.png">
				</div>
			</div>
		</div>
		<div class="mypage-tab-wrap">
			<div class="sub-mypage-txt">
				<span class="sub-mypage-txt">나의 일정관리 ></span>
			</div>
			<div class="page-breadcrumb m-top-30">
	 			<div id='calendar'></div>
			</div>
				  <div id="inputArea">
			      <form method="post" id="inputForm" action="">
			      	 내용 : <input type="text" name="planContent" required="required"/>
			      	 시작일 : <input type="text" name="startDate" id="datetimepicker" readonly="readonly" required="required"/>
			      	 종료일 : <input type="text" name="endDate" id="datetimepicker2" readonly="readonly" required="required"/>
			      	 <input type="hidden" name="what"/>
			      	 <input type="hidden" name="writer" />
			      <button>저장</button>
			      <input type="button" value="삭제" id="deleteBtn"/>
			      </form>
				  </div>
			<button id="modal-open-btn-example" data-modal-open="modal-example" style="display: none"></button>
			</div>
			</div>
		</div>
			
		</div>
	</div>
</div>

<script>
jQuery('#datetimepicker').datetimepicker();
jQuery('#datetimepicker2').datetimepicker();
let inputArea = $("#inputArea").hide();
let inputForm = $("#inputForm")

$("#deleteBtn").on("click", function() {
	
	let valid = confirm("정말 삭제하시겠습니까?");
	if(!valid) return;
	
	inputForm.attr("action", "${cPath }/outs/schedule/scheduleDelete.do")
	inputForm.submit()
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
    				url: '${cPath}/outs/schedule/scheduleList.do',
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
				  inputForm.attr("action", "${cPath }/outs/schedule/scheduleInsert.do");
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
                 $("[name='what']").val(planNum)
                 $("[name='writer']").val(writer)
                 $("[name='planContent']").val(content)
                 $("[name='startDate']").val(startDate)
                 $("[name='endDate']").val(endDate)
                 inputForm.attr("action", "${cPath }/outs/schedule/scheduleUpdate.do");
                 inputArea.toggle()
                 
                 return false;
             }
         }
    	
    });

    calendar.render();
  });

</script>
