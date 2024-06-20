//$(function() {
//	eventInit();
//});

//function eventInit() {

function initClick(btn) {
	let data = btn.data("word")
	let type = btn.attr("value")
	let text = btn.text();
	
	const isChecked = btn.attr('data-clicked');
	if(isChecked === 'false') {
		btn.attr('data-clicked', 'true');
		btn.addClass('filter-btn-active');
		makeCheckedItem(data,type,text,"add");
	}
}

function initUserClick(btn) {
	
	var sibling = btn.siblings(".filter-btn4");
	let data = btn.data("word")
	let type = btn.attr("value")
	let text = btn.text();
	
	btn.attr('data-clicked', 'true');
	btn.addClass('filter-btn-active');
	sibling.attr('data-clicked', 'false');
	sibling.removeClass('filter-btn-active');
	
	makeCheckedItem(data,type,text,"add");			
	
}

function initStackClick(stackStr) {
	
	let stackList = stackStr.split(",");
	
	$.each(stackList, function(idx, stack) {
		makeCheckedItem(stack,"projectTech",stack,"add");			
	})
	
	
}

$(function() {
	
	
	// 필터 버튼 클릭 이벤트
	$('.filter-btn').on('click', function(e) {
		const isChecked = $(this).attr('data-clicked');
		
		let searchType = $(this).attr("value");
		let searchData = $(this).data("word");
		let text = $(this).text();
		
		console.log(searchType)
		console.log(searchData)
		
		let tempValue = $("#searchForm").find("[name='"+searchType+"']").val();
		
		
		if(isChecked === 'false') {
			$(this).attr('data-clicked', 'true');
			$(this).addClass('filter-btn-active');
			makeCheckedItem(searchData, searchType, text, "add");
			if(tempValue) {
				tempValue += ","+searchData;
				$("#searchForm").find("[name='"+searchType+"']").val(tempValue)				
			} else {
				$("#searchForm").find("[name='"+searchType+"']").val(searchData)
			}
		}else {
			$(this).attr('data-clicked', 'false');
			$(this).removeClass('filter-btn-active');
			makeCheckedItem(searchData, searchType, text, "delete");
			let split = tempValue.split(",")
			if(split.length == 1) {
				tempValue = tempValue.replace(searchData, "")				
			} else if(split.length > 2) {
				console.log("첫 위치 : " + tempValue.indexOf(searchData))
				if(tempValue.indexOf(searchData) == 0) {
					tempValue = tempValue.replace(searchData+",", "")
				} else {
					tempValue = tempValue.replace(","+searchData, "")					
				}
			} else if(split.length == 2) {
				tempValue = tempValue.replace(searchData, "")
				tempValue = tempValue.replace(",", "")
			}
			$("#searchForm").find("[name='"+searchType+"']").val(tempValue);
		}
		
		console.log(tempValue)
		$("#searchForm").submit();
		
	});
	
	$('.office').on('click', function(e) {
		const isChecked = $(this).attr('data-clicked');
		
		let searchType = $(this).attr("value");
		let searchData = $(this).data("word");
		let text = $(this).text();
		console.log(searchType)
		console.log(searchData)
		
		let tempValue = $("#searchForm").find("[name='"+searchType+"']").val();
		
		
		if(isChecked === 'false') {
			$(this).attr('data-clicked', 'true');
			$(this).addClass('filter-btn-active');
			makeCheckedItem(searchData, searchType, text, "add");
			if(tempValue) {
				tempValue += ","+searchData;
				$("#searchForm").find("[name='"+searchType+"']").val(tempValue)				
			} else {
				$("#searchForm").find("[name='"+searchType+"']").val(searchData)
			}
		}else {
			$(this).attr('data-clicked', 'false');
			$(this).removeClass('filter-btn-active');
			makeCheckedItem(searchData, searchType, text, "delete");
			let split = tempValue.split(",")
			if(split.length == 1) {
				tempValue = tempValue.replace(searchData, "")				
			} else {
				tempValue = tempValue.replace(searchData, "")
				tempValue = tempValue.replace(",", "")
			}
			$("#searchForm").find("[name='"+searchType+"']").val(tempValue);
		}
		
		console.log(tempValue)
		$("#searchForm").submit();
		
	});
	
//	$('.filter-btn2').on('click', function(e) {
//		const isChecked = $(this).attr('data-clicked');
//		if(isChecked === 'false') {
//			$(this).attr('data-clicked', 'true');
//			$(this).addClass('filter-btn-active');
//			makeCheckedItem();			
//		}else {
//			$(this).attr('data-clicked', 'false');
//			$(this).removeClass('filter-btn-active');
//			makeCheckedItem();
//		}
//	});
//	
//	$('.filter-btn3').on('click', function(e) {
//		const isChecked = $(this).attr('data-clicked');
//		if(isChecked === 'false') {
//			$(this).attr('data-clicked', 'true');
//			$(this).addClass('filter-btn-active');
//			makeCheckedItem();			
//		}else {
//			$(this).attr('data-clicked', 'false');
//			$(this).removeClass('filter-btn-active');
//			makeCheckedItem();
//		}
//	});
	
	$('.req').on('click', function(e) {
		const isChecked = $(this).attr('data-clicked');
		
		let searchType = $(this).attr("value");
		let searchData = $(this).data("word");
		let text = $(this).text();
		console.log(searchType)
		console.log(searchData)
		
		$("#searchForm").find("[name='"+searchType+"']").val(searchData)
		
		var sibling = $(this).siblings(".filter-btn4");
		const siblingCheck = sibling.attr('data-clicked');
		
		if(isChecked === 'false') {
			$(this).attr('data-clicked', 'true');
			$(this).addClass('filter-btn-active');
			if(siblingCheck === 'true') {
				sibling.attr('data-clicked', 'false');
				sibling.removeClass('filter-btn-active');
			}
			makeCheckedItem(searchData, searchType, text, "add");			
		}else {
			$(this).attr('data-clicked', 'false');
			$(this).removeClass('filter-btn-active');
			makeCheckedItem(searchData, searchType, text, "delete");
		}
		
		$("#searchForm").submit();
	});
	
	// 체크된 아이템 클릭 이벤트
	$(document).on('click', '.checked-item', function(e) {
//		const value = $(this).val();
//		$('.filter-btn-active').each(function(index) {
//			const item = $(this);
//			if(item.val() === value) {
//				item.attr('data-clicked', 'false');
//				$(this).removeClass('filter-btn-active');
//			}
//		});
		
		let type = $(this).attr("value");
		let text = $(this).text();
		const value = $(this).data("word");
		let activeBtn = $("[data-word='"+value+"']");
		activeBtn.attr('data-clicked', 'false');
		activeBtn.removeClass('filter-btn-active');
		
		if(type == "projectTech") {
			let tempSkillVal = $("#skill").val();
			console.log("쉼표 뺸 위치 :" + tempSkillVal.indexOf(value+","))
			if(tempSkillVal.indexOf(value+",") < 0) {
				tempSkillVal = tempSkillVal.replace(value, "");
			} else {
				tempSkillVal = tempSkillVal.replace(value+",", "");				
			}
			$("#skill").val(tempSkillVal)
		}
		
		$(this).remove();
		
		let searchType = $(this).attr("value");
		let searchData = $(this).data("word");
		let tempValue =  $("#searchForm").find("[name='"+searchType+"']").val();
		
		let split = tempValue.split(",")
		if(split.length == 1) {
			tempValue = tempValue.replace(searchData, "")				
		} else if(split.length > 2) {
			console.log("첫 위치 : " + tempValue.indexOf(searchData))
			if(tempValue.indexOf(searchData) == 0) {
				tempValue = tempValue.replace(searchData+",", "")
			} else {
				tempValue = tempValue.replace(","+searchData, "")					
			}
		} else if(split.length == 2) {
			tempValue = tempValue.replace(searchData, "")
			tempValue = tempValue.replace(",", "")
		}
		$("#searchForm").find("[name='"+searchType+"']").val(tempValue);		
	    $("#searchForm").submit();
	});
	
//}


})

function makeInputStack(word,type) {
	let str = $("#checkedFilter").html();
	console.log(word)
	console.log(type)
	str += `<button class="checked-item" data-word="${word}" value=${type}>${word}</button>`;
	$('#checkedFilter').html(str);
	let tempValue = $("#searchForm").find("[name='"+type+"']").val();
	if(tempValue) {
		tempValue += ","+word;
		$("#searchForm").find("[name='"+type+"']").val(tempValue)				
	} else {
		$("#searchForm").find("[name='"+type+"']").val(word)
	}
	
	$("#skill").val("")
	$("#searchForm").submit();
}

// 체크된 버튼 생성
function makeCheckedItem(word,type,text,token) {
//	let str = '';
//	$('.filter-btn-active').each(function(index) {
//		const item = $(this);
//		str += `<button class="checked-item" data-word="${item.data("word")}" value=${item.val()}>${item.text()}</button>`;
//	});
	if(token == "add") {
		let str = $("#checkedFilter").html();
		str += `<button class="checked-item" data-word="${word}" value=${type}>${text}</button>`;
		$('#checkedFilter').html(str);		
	} else if(token == "delete"){
		$("#checkedFilter").find("[data-word='"+word+"']").remove();
	}
}
