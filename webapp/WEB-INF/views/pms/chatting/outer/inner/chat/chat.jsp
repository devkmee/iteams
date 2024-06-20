<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String id = (String) session.getId();
%>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Chating</title>

<style>

button {
	margin-left: 4px;
}

.btn-default {
    border: 0;
    background-color: #657887;
    color: #fff;
    padding: 6px 6px 6px 6px;
    border-radius: 4px;
    font-size: 13px;
    line-height: 17px;
}

.btn-basic {
    border: 0;
    background-color: #14366f;
    color: #fff;
    padding: 6px 6px 6px 6px;
    border-radius: 4px;
    font-size: 13px;
    line-height: 17px;
}

.btn-basic:hover {
    color: #fff;
    background-color: #2e5599;
}

h3 {
    text-align: center;
    margin-top: 10px;
}

body {
    background-color: #eceff1;
	margin: 0;
	padding: 0;`
	width: 480px;
	height: 700px;
}

p {
	display: inline-block;
	width: 100%;
}

* {
	margin: 0;
	padding: 0;
}

.container {
	width: 500px;
	margin: 0 auto;
	padding: 25px
		height: 100%;
    margin: 0;
    padding: 0;
    border: none;
    overflow: hidden;
}

.container h1 {
	text-align: left;
	padding: 5px 5px 5px 15px;
	color: #000;
	border-left: 3px solid #000;
	margin-bottom: 20px;
}

.chating {
	
	width: 500px;
	height: 500px;
    padding: 10px;
    overflow: scroll;
}

.chating .chat .me {
    color: #000000;
    background-color:#d0e5ee;
    border-radius: 10px;
    display: flex;
    justify-content: flex-end;
    font-weight: bold;
    padding: 4px 10px 4px 10px;
    border-bottom-right-radius: 5px;
    border-top-right-radius: 0;
    width: 400px;
}

.chating .chat .others {
	background-color: #cfd8dc;
    border-radius: 10px;
    display: flex;
    justify-content: flex-starts;
    font-weight: bold;
    padding: 4px 10px 4px 10px;
    border-bottom-right-radius: 5px;
    border-top-left-radius: 0;
    width: 400px;
}

.chating .chat .enter {
	color: #cccccc;
	text-align: left;
}

.chating .chat {
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    border-radius: 5px;
    box-sizing: border-box;
    padding: 4px 14px 4px 19px;
    position: relative;
    width: 100%;
}

input {
	width: 330px;
	height: 30px;
}

#yourMsg {
	display: none;
	width: 100%;
}

#yourName {
	width: 500px;
}
.flex-center {
	display: flex;
	justify-content: center;
}
</style>
</head>

<script type="text/javascript">
	var ws;
	const host =location.host;
	const protocol = location.protocol=="https:"? "wss":"ws";
	function wsOpen() {
		
		ws = new WebSocket(
				protocol+"://"+host+"${cPath}/pms/websocket/${masterVO.proNo}/echo.do");
		wsEvt();
	}
	function wsClose() {
		ws.onclose = function(data) {
			out();
		}
	}
	function wsEvt() {
		ws.onopen = function(data) {
			//소켓이 열리면 동작
			enter();
		}
		ws.onmessage = function(data) {
			//메시지를 받으면 동작
			var msg = data.data;
			if (msg != null && msg.trim() != '') {
				var d = JSON.parse(msg);
				if (d.type == "getId") {
					var si = d.sessionId != null ? d.sessionId : "";
					if (si != '') {
						$("#sessionId").val(si);
					}
				} else if (d.type == "message") {
					if (d.sessionId == $("#sessionId").val()) {
						$("#chating").append(
								"<div class='chat'><p class='me'>나 :" + d.msg
										+ "</p></div>");
						$("#chating").scrollTop($("#chating")[0].scrollHeight);
					} else {

						$("#chating").append(
								"<div class='chat'><p class='others'>"
										+ d.userName + " :" + d.msg
										+ "</p></div>");
						$("#chating").scrollTop($("#chating")[0].scrollHeight);
					}
				} else {
					console.warn("unknown type!")
				}
			}
		}
		document.addEventListener("keypress", function(e) {
			if (e.keyCode == 13) { //enter press
				send();
			}
		});
	}
	function chatName() {
		var userName = $("#userName").val();
		if (userName == null || userName.trim() == "") {
			alert("사용자 이름을 입력해주세요.");
			$("#userName").focus();
		} else {
			wsOpen();
			$("#yourName").hide();
			$("#yourMsg").show();
		}
	}

	function send() {
		var option = {
			type : "message",
			sessionId : $("#sessionId").val(),
			userName : $("#userName").val(),
			msg : $("#chatting").val()
		}
		ws.send(JSON.stringify(option))
		$('#chatting').val("");
	}
	function enter() {
		var option = {
			type : "message",
			sessionId : $("#sessionId").val(),
			userName : $("#userName").val(),
			msg : '님이 입장하셨습니다'
		}
		ws.send(JSON.stringify(option))
		$('#chatting').val("");
	}
	function out() {
		var option = {
			type : "message",
			sessionId : $("#sessionId").val(),
			userName : $("#userName").val(),
			msg : '님이 퇴장하셨습니다'
		}
		ws.send(JSON.stringify(option))
		$('#chatting').val("");
	}

	function start() {
		var option = {
			type : "message",
			sessionId : $("#sessionId").val(),
			userName : $("#userName").val(),
			msg : "",
			state : "/start",
			flag : "/first"
		}
		ws.send(JSON.stringify(option))
	}
	function end() {
		var option = {
			type : "message",
			sessionId : $("#sessionId").val(),
			userName : $("#userName").val(),
			msg : "",
			state : "/end"
		}
		ws.send(JSON.stringify(option))
	}
	function chatfinish() {
		var option = {
			type : "message",
			sessionId : $("#sessionId").val(),
			userName : $("#userName").val(),
			msg : "",
			state : "/out"
		}
		ws.send(JSON.stringify(option))
// 		location.href = "${cPath}/pms/${authMember.proNo}/main.do"
		window.close();
	}
	
	function excelDownload() {
		location.href = "${cPath}/pms/chatting/${authMember.proNo}/chattingExcel.do";
	}
</script>
<body>
<div>
	<div id="container" class="container">
		<h3>프로젝트 회의</h3>
		<input type="hidden" id="sessionId" value="">
		<div id="chating" class="chating"></div>
		<div id="yourName">
			<table class="inputTable">
				<tr>
					<th>사용자명</th>
					<th><input type="text" name="userName" id="userName"
						readonly="readonly"
						value="<c:if test="${masterVO.devName == null}">${masterVO.managerName }(${masterVO.authority })</c:if><c:if test="${masterVO.devName != null}">${masterVO.devName }(${masterVO.authority })</c:if>"></th>
					<th><button onclick="chatName()" id="startBtn" class="btn-basic">이름 등록</button></th>
				</tr>
			</table>
		</div>
		<div id="yourMsg">
			<table class="inputTable">
				<tr>
					<th>메시지</th>
					<th><input id="chatting" placeholder="보내실 메시지를 입력하세요."></th>
					<th><button onclick="send()" id="sendBtn" class="btn-basic">보내기</button></th>
				</tr>
			</table>
		</div>
	</div>
	<div>
		<table>
			<tr>
				<th></th>
				<th></th>
			</tr>
		</table>
	</div>
	<div class='flex-center' style="margin-top:20px;">
		<button onclick="start()" class="btn-default">회의기록시작</button>
		<button onclick="end()" class="btn-default">회의기록종료</button>
		<button onclick="chatfinish()" class="btn-default">회의 퇴장</button>
		<button onclick="excelDownload()" class="linkBtn btn-default">회의 기록 내보내기</button>
	</div>
</div>
</body>
