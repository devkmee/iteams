<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--     <link type="image/png" href="https://i.pinimg.com/originals/22/e4/e0/22e4e079c332b3f3589f4a8b73545076.png" rel="icon" style="color: tomato;">  -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<style>
#header{
    width: 100%;
    text-align: center;
    height: 100%;
    font-size: xx-large;
    color:blue;
    
}
@media screen and (max-width: 900px) {
    #form{
        width: 100%;
    }
  }
  
  /* Responsive layout - makes the two columns stack on top of each other instead of next to each other */
  @media screen and (max-width: 600px) {
    #form{
        width: 100%;
    }
  }
#form{
    background-color: #333;
    color: yellow;
    height: 400px;
    width: 400px;
    overflow: hidden;
    margin: auto;
    align-content: center;
    text-align: center;
    padding: 20px 20px;
    text-align: center;
    
}
input:nth-child(odd){
    padding: 20px 20px;
    background-color: #333;
    color: tomato;
}
input:nth-child(even){
    padding: 10px 20px;
    border: none;
    background-color: tomato;
    color: #333;
}
input{
    position: relative;
    width: 100%;
    outline: none;
    margin-top: 10px;
    left: 0;
    right: 0;
    display: block;

}


</style>

<body>
<!-- <div id="header"> -->
<!-- <h1>화상 면접실 입장</h1> -->
<!-- </div> -->
<div id="form">

        <input type="text" name="roomId" id="value" placeholder="면접실 ID" value="" required="required">
        <input type="button" value="Join Room" id="submit">
        <input type="button" value="Create Room" id="newRoom">
</div>

<script >
    
    var form = document.getElementById("submit")
    form.addEventListener('click', ()=>{
        const x = document.getElementById("value");
        if(x.value == ""){
            //alert("Room id is empty!!!")
            swal("면접실 ID를 입력해야 합니다.")
        }else{
            location.replace("https://192.168.0.151:3000/"+x.value);
//             location.replace("https://videocallappwebrtc.herokuapp.com/"+x.value);
            
            x.value = "";
        }
    });

    const y = document.getElementById("newRoom");
    y.addEventListener('click', ()=>{
    	const x = document.getElementById("value");
    	if(!x.value) {
    		 swal("면접실 ID를 입력해야 합니다.")
    		 return
    	}  	
        swal("새로운 면접실이 생성되었습니다. 방번호 : " + x.value).then((value)=>{
            if(value == true){
                window.location.href = "https://192.168.0.151:3000/" + x.value;
//                 window.location.href = "https://videocallappwebrtc.herokuapp.com/";
            }else{
                swal("면접방 생성에 실패했습니다.");
            }
        })
    });
</script>
</body>