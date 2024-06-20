/**
 * 
 */

let id;
let keyNumber;

$(function(){
	
})

// 이메일 확인
function checkMail() {
	const checkIdInput = document.getElementById('chkid');
	if(checkIdInput) {
		const checkId = checkIdInput.getAttribute('data-check_id');
		if(checkId === 'false') {
			callModal('아이디를 먼저 확인해주세요.');
			return;
		}
	}
	
	//입력한 이메일값을 가져온다 //공백체크
	let emailvalue = $('#email').val().trim();
	let inputId = $("#chkid").val();
	
	$.ajax({
		url : `${CONTEXT_PATH}/outs/login/emailId.do`,
		method : 'post',
		data : {
			memId : inputId,
			what: emailvalue
		},
		dataType : 'json',
		success : function(res){
			console.log(res.keyNumber)
			if(res.keyNumber === '1'){
				callModal('존재하지 않는 이메일입니다.');
				return;
			} else if(res.keyNumber === '2') {
				callModal('아이디와 이메일 정보가 맞지 않습니다.');
			} 
			else{
				callModal('이메일 발송 완료!');
				id = res.id
				keyNumber = res.keyNumber;
			}	
		},
		error : function(xhr){
			console.log(xhr);
			alert("상태  : " + xhr.status)
		}
	});
}

//아이디 찾기 버튼 이벤트
function onClickFindId() {
	const certNum = document.getElementById('certNum').value;
	
	if(certNum === keyNumber){
		document.getElementById('findId').innerText = '회원님의 아이디는 ' + id + '입니다.';
		const btn = document.getElementById('modalCloseBtn');
		btn.addEventListener('click', function() {
			location.href = CONTEXT_PATH + '/outs/login/login.do';
		});
		$('#exampleModal').modal('show');
	}else{
		document.getElementById('findId').innerText = '인증번호가 맞지 않습니다.';
		$('#exampleModal').modal('show');
		return;
	}
}

// 아이디 확인
function onClickCheckId() {
	let memId = document.getElementById('chkid').value;
	$.ajax({
		url : CONTEXT_PATH + "/outs/login/idCheck.do",
		data : {
			memId: memId
		},
		dataType : "json",
		success : function(res) {
			console.log(res);
			// 통과
			if(res === 0) {
				callModal('존재하지 않는 아이디입니다.');
				$('#chkid').attr('data-check_id', 'false');
			}else {
				callModal('아이디가 확인되었습니다.');
				document.getElementById('memId').value = memId;
				$('#chkid').attr('data-check_id', 'true');
			}
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
}

// 비밀번호 찾기 버튼 이벤트
function onClickFindPass() {
	const checkId = document.getElementById('chkid').getAttribute('data-check_id="false"');
	if(checkId === 'true') {
		callModal('아이디를 먼저 확인해주세요.');
		return;
	}else {
		const certNum = document.getElementById('certNum').value;
		if(certNum === keyNumber){
			document.getElementById('findForm').submit();
		}else{
			callModal('인증번호가 맞지 않습니다.');
			return;
		}
	}
}

// 비밀번호 변경 버튼 이벤트
function onClickChangePass() {
	const newPass = document.getElementById('newPass').value;
	const newPassCheck = document.getElementById('newPassCheck').value;
	
	if(newPass === newPassCheck) {
		const memId = document.getElementById('memId').value;
		console.log(memId);
		const params = {
			memId: memId,
			memPass: newPass,
		}
		console.log(params);
		$.ajax({
			url : CONTEXT_PATH + "/outs/login/changePass.do",
			method: 'POST',
			dataType : "json",
			contentType: 'application/json',
			data : JSON.stringify(params),
			success : function(res) {
				console.log(res);
				// 통과
				if(res.isSuccess) {
					callModal('비밀번호가 변경되었습니다.');
					const btn = document.getElementById('modalCloseBtn');
					btn.addEventListener('click', function() {
						location.href = CONTEXT_PATH + res.redirectUrl;
					});
				}else {
					callModal('비밀번호가 변경 실패.\n 다시 시도해주세요.');
				}
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	}else {
		callModal('비밀번호가 맞지 않습니다.')
	}
}

function callModal(content) {
	document.getElementById('findId').innerText = content;
	$('#exampleModal').modal('show');
}
