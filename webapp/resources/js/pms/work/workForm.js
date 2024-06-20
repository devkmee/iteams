/**
 * 
 */
 //현재 시각으로 시작시간 설정
  document.getElementById('exampleFormControlInput2').value = new Date().toISOString().substring(0, 10);
 

	let fileArea = $("#fileArea").on("click", ".ctlBtn", function(){
		let id = this.id;
		console.log(id);
		let divTag = $(this).closest("div");
		if(id == 'plus'){
			let clone = divTag.clone();
			$(clone).find(":input[name]").val("");
			divTag.after(clone);
		}else{
			let divs = fileArea.find("div");
			if(divs.length>1)
				divTag.remove();
		}
	});
	
	