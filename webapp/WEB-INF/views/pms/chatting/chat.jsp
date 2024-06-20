<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <% String id = (String)session.getId(); %>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="UTF-8">
	<title>Chating</title>

	<style>
		*{
			margin:0;
			padding:0;
		}
		.container{
			width: 500px;
			margin: 0 auto;
			padding: 25px
		}
		.container h1{
			text-align: left;
			padding: 5px 5px 5px 15px;
			color: #000;
			border-left: 3px solid #000;
			margin-bottom: 20px;
		}
		.chating{
			background-color: #f7e1e1;
			width: 500px;
			height: 500px;
			overflow: auto;
		}
		.chating .chat .me{
			color: #000000;
			text-align: right;
		}
		.chating .chat .others{
			color: #000000;
			text-align: left;
		}
		.chating .chat .enter{
			color: #cccccc;
			text-align: left;
		}
		.chating .chat{
			background-color: #e9ffed;
		}
		input{
			width: 330px;
			height: 25px;
		}
		#yourMsg{
			display: none;
			width: 500px;
			background-color: #f7e1e1;
		}
		#yourName{
			width: 500px;
			background-color: #f7e1e1;
		}
	</style>
</head>

<script type="text/javascript">
var ws;
function wsOpen(){
	ws = new WebSocket("wss://localhost${cPath}/pms/websocket/${masterVO.proNo}/echo.do");
	wsEvt();
}
function wsClose(){
	ws.onclose = function(data){
		out();
	}
}
function wsEvt() {
	ws.onopen = function(data){
		//소켓이 열리면 동작
		enter();
	}
		ws.onmessage = function(data) {
			//메시지를 받으면 동작
			var msg = data.data;
			if(msg != null && msg.trim() != ''){
				var d = JSON.parse(msg);
				if(d.type == "getId"){
					var si = d.sessionId != null ? d.sessionId : "";
					if(si != ''){
						$("#sessionId").val(si); 
					}
				}else if(d.type == "message"){
					if(d.sessionId == $("#sessionId").val()){
						$("#chating").append("<div class='chat'><p class='me'>나 :" + d.msg + "</p></div>");	
						$("#chating").scrollTop($("#chating")[0].scrollHeight);
					}else{
						
						$("#chating").append("<div class='chat'><p class='others'>" + d.userName + " :" + d.msg + "</p></div>");
						$("#chating").scrollTop($("#chating")[0].scrollHeight);
					}	
				}else{
					console.warn("unknown type!")
				}
			}
		}
		document.addEventListener("keypress", function(e){
			if(e.keyCode == 13){ //enter press
				send();
			}
		});
	}
	function chatName(){
		var userName = $("#userName").val();
		if(userName == null || userName.trim() == ""){
			alert("사용자 이름을 입력해주세요.");
			$("#userName").focus();
		}else{
			wsOpen();
			$("#yourName").hide();
			$("#yourMsg").show();
		}
	}
	
	function send() {
		var option ={
			type: "message",
			sessionId : $("#sessionId").val(),
			userName : $("#userName").val(),
			msg : $("#chatting").val()
		}
		ws.send(JSON.stringify(option))	
		$('#chatting').val("");
	}
	function enter() {
		var option ={
			type: "message",
			sessionId : $("#sessionId").val(),
			userName : $("#userName").val(),
			msg :'님이 입장하셨습니다'
		}
		ws.send(JSON.stringify(option))	
		$('#chatting').val("");
	}
	function out() {
		var option ={
			type: "message",
			sessionId : $("#sessionId").val(),
			userName : $("#userName").val(),
			msg :'님이 퇴장하셨습니다'
		}
		ws.send(JSON.stringify(option))	
		$('#chatting').val("");
	}
	
	function start(){
		var option = {
				type: "message",
				sessionId : $("#sessionId").val(),
				userName : $("#userName").val(),
				msg : "",
				state : "/start",
				flag : "/first"
		}
		ws.send(JSON.stringify(option))	
	}
	function end(){
		var option = {
				type: "message",
				sessionId : $("#sessionId").val(),
				userName : $("#userName").val(),
				msg : "",
				state : "/end"
		}
		ws.send(JSON.stringify(option))	
	}
	function chatfinish(){
		var option = {
				type: "message",
				sessionId : $("#sessionId").val(),
				userName : $("#userName").val(),
				msg : "",
				state : "/out"
		}
		ws.send(JSON.stringify(option))
		location.href = "${cPath}/pms/${authMember.proNo}/main.do"
	}
</script>
<body>

	<div id="container" class="container">
		<h1>채팅</h1>
		<input type="hidden" id="sessionId" value="">	
		<div id="chating" class="chating"></div>
		<div id="yourName">
			<table class="inputTable">
				<tr>
					<th>사용자명</th>
					<th><input type="text" name="userName" id="userName" readonly="readonly" value="<c:if test="${masterVO.devName == null}">${masterVO.managerName }(${masterVO.authority })</c:if><c:if test="${masterVO.devName != null}">${masterVO.devName }(${masterVO.authority })</c:if>"></th>
					<th><button onclick="chatName()" id="startBtn">이름 등록</button></th>
				</tr>
			</table>
		</div>
		<div id="yourMsg">
			<table class="inputTable">
				<tr>
					<th>메시지</th>
					<th><input id="chatting" placeholder="보내실 메시지를 입력하세요."></th>
					<th><button onclick="send()" id="sendBtn">보내기</button></th>
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
	<div>
		<button onclick="start()" >회의기록시작</button>
		<button onclick="end()" >회의기록종료</button>
		<button onclick="chatfinish()" >채팅 퇴장</button>
		<button data-gopage="${cPath}/pms/chatting/${authMember.proNo}/chattingExcel.do" class="linkBtn">회의 기록 내보내기</button>
	</div>
</body>
	