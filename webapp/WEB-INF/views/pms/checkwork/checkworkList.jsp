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
<body>
<div class="sub-title-wrap m-bottom-20">
       		<i class="fas fa-chevron-right" aria-hidden="true"></i>
       	근태관리
</div>
<div id='calendar'></div>

<script>
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
    				url: '${cPath}/pms/workcheck/${authMember.proNo}/workcheckList.do',
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
    	}]
    	       	
    });

    calendar.render();
  });
</script>
</body>
