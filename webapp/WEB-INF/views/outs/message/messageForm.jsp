<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mypage.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<div class="login-content-wrap">
	<div class="mypage-wrap">
		<div class="sub-mypage-wrap">
			<div class="sub-inner-box">
				<div class="main-outline-box-img m-right-10">
					<img alt="" src="/iteams/resources/images/mypage-img.png">
				</div>
			</div>
		</div>
		<div class=" mypage-tab-wrap">
			<div class="sub-mypage-txt">
				<span class="sub-mypage-txt">쪽지작성 ></span>
			</div>
				<div class="tab-wrap">
					<ul class="nav nav-tabs m-bottom-20" id="myTab" role="tablist">
					  <li class="nav-item m-right-2" role="presentation">
					  </li>
					</ul>
					
					<div class="tab-content" id="myTabContent">
					
			   	   	   <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
							<div class="member-basic-wrap">
								
								<div class="table-list">
								<form:form modelAttribute="messageVO" method="post" enctype="multipart/form-data">
									<table class="table">
									    <tr>
											<th style="text-align: right; vertical-align: middle;">제목</th>
											<td><form:input path="msgTitle" type="text" name="msgTitle" style="border: 1px solid #d1d1d1; width: 100%; padding: 4px;"/>
												<form:errors path="msgTitle" cssClass="error" element="span"></form:errors></td>
										</tr>
									    <tr>
									      <th style="text-align: right; vertical-align: middle;">받는사람 아이디</th>
									      <td><form:input path="msgReceive" id="receiveId" type="text" name="msgReceive" style="border: 1px solid #d1d1d1; width: 100%; padding: 4px;" />
									      	  <form:errors path="msgReceive" cssClass="error" element="span"></form:errors></td>
									    </tr>
									    <tr>
									      <th style="text-align: right; vertical-align: middle;">내용</th>
									      <td><textarea name="msgContent" id="msgContent" cols="30" rows="10">${messageVO.msgContent}</textarea>
									      	  <form:errors path="msgContent" cssClass="error" element="span"></form:errors></td>
									    </tr>
									    <tr>
											<td colspan="2">
												<input type="submit" class="btn btn-gray" value="전송" />
												<input type="reset" class="btn btn-gray" value="취소" />
											</td>
										</tr>
									</table>
								</form:form>	
								</div>
								
							</div>
					    </div>
					    
					</div>
					
				</div>
			</div>
		</div>
	</div>
	
<script>

CKEDITOR.replace("msgContent", {
	filebrowserImageUploadUrl:"${cPath}/common/imageUpload.do?type=Images"
});

$("#receiveId").autocomplete({
    source : function(request, response) {
        $.ajax({
              url : "${cPath}/outs/memberList.do"
            , type : "GET"
            , data : {search : $("#receiveId").val()} // 검색 키워드
            , success : function(data){ // 성공
            	response(
                        $.map(data, function(item) {
                            return {
                                  label : item    //목록에 표시되는 값
                                , value : item    //선택 시 input창에 표시되는 값
                         
                            };
                        })
                    ); 
            }
            ,
            error : function(){ //실패
                alert("통신에 실패했습니다.");
            }
        });
    }
    , minLength : 1    
    , autoFocus : false    
    , select : function(evt, ui) {
//         console.log("전체 data: " + JSON.stringify(ui));
//         console.log("db Index : " + ui.item.idx);
//         console.log("검색 데이터 : " + ui.item.value);
		let id = ui.item.value.trim();
		$("#receiveId").val(id);
    }
    , focus : function(evt, ui) {
        return false;
    }
    , close : function(evt) {
    }
});
</script>