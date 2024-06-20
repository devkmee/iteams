/**
 * 
 */

$(function() {
	autocomplete();
});

//콤마를 기준으로 자른다.
function split(val) {
	return val.split(/,\s*/);
}
// 배열을 인자로 받아서 콤마 기준으로 자른 뒤 마지막 값을 반환한다.
function extractLast(term) {
	return split(term).pop();
}

function autocomplete() {
	$('#skill').on('keydown', function(event) {
				if (event.keyCode === $.ui.keyCode.TAB
						&& $(this).autocomplete('instance').menu.active) {
					event.preventDefault();
				}
			}).autocomplete({ // autocomplete 구현 시작부
		source : function(req, response) {
			$.ajax({
				type : 'get',
				dataType : 'json',
				data : {
					keyword : extractLast(req.term)
				},
				url : CONTEXT_PATH + '/outs/login/findTechCodeByKeyword.do',
				success : function(res) {
					response(res);
				}
			})
		},
		// Input에 검색어를 입력했을 때 적용되는 콜백함수
		search: function() {
		  // 멀티 셀렉트이기 때문에 마지막 검색어로만 검색이 되도록 하기 위해 다음과 같이 처리를 한다.
          const term = extractLast(this.value);
          if ( term.length < 1 ) {
            return false;
          }
        },
        // 자동완성된 데이터를 선택했을 때 적용되는 콜백함수
		select : function(event, ui) {
			var terms = split(this.value);
			// pop -> 배열의 마지막 요소를 제거하고 반환한다.(Input에 검색어를 제거하고 선택한 값을 넣기 위함)
			terms.pop();
			// 현재 선택한 값을 배열에 추가한다.
			terms.push(ui.item.value);
			terms.push('');
			// 현재 배열을 콤마를 붙여서 String값으로 변환시킨다.
			this.value = terms.join(",");
			return false;
		},
		focus : function() {
			return false;
		},
		selectFirst: true,
		// 최소 글자 수
		minLenght: 1,
		// true로 설정 시 메뉴가 표시 될 때, 첫 번째 항목에 자동으로 초점이 맞춰짐
		autoFocus : true,
		// 위젯 요소에 추가 할 클래스를 지정
		classes : { 
			'ui-autocomplete' : 'highlight'
		},
		// 입력창에 글자가 써지고 나서 autocomplete 이벤트 발생될 떄 까지 지연 시간(ms)
		delay : 500, 
		// 해당 값 true 시, 자동완성 기능 꺼짐
		disable : false, 
	// UI 변경
	}).autocomplete('instance')._renderItem = function(ul, item) {
		// 익스에서 쓰려면 아래 문법으로 해야함
		// var str = '<div><img src=' + CONTEXT_PATH + item.imgPath + ' />' + item.label + '</div>';
		const str = `<div><img class="skill-img" src=data:image/png;base64,${item.imgPath}>${item.label}</div>`;
		return $('<li>').append(str).appendTo(ul);
	};
}