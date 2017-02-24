/**
 * 
 */
//
//function validate(){
//	var ok = true;
//	var p = document.getElementById("login").value;
//	var e1 = document.getElementById("e1");
//	//var e3 = document.getElementById("e3");
//	
//	//clear all previous error msg
//	if (isNaN(p)){
//		alert("login invalid!");
//		//document.getElementById("e1").innerHTML = "invalid principal";
//		//e1.innerHTML = "invalid login";
//		ok = false;
//	}
//	if (!ok) return false;
//	p = document.getElementById("password").value;
//	var e2 = document.getElementById("e2");
//	if (isNaN(p)){
//		//document.getElementById("e2").innerHTML = "invalid interest";
//		alert("password invalid!");
//		//e2.innerHTML = "invalid password";
//		ok = false;
//	}
//	return ok;
//}


$(document).ready(function(){

//    $("#sub").click(function(){
//    	//var data = $("#userform");
//    	//var formData = new FormData();
//    	//formData.append("reqtype","account");
//    	var map = {};
//    	map["reqtype"] = "account";
//        $.post("/4413project/Start/",
//        map,
//        function(data, status){
//            //alert("Data: " + data + "\nStatus: " + status);
//        	//$("body").html(data);;
//        	//$("#content").html($(data).find("data").html());
////        	document.open();
////            document.write(data);
////            document.close();
//        	alert("ok");
//        });
//    });
	
	
	$("#userform").submit(function(event) {
		  //alert( "Handler for .submit() called." );
		  event.preventDefault();
		  var data = $(this).serialize() + "&reqtype=account";
		  $.post("/4413project/Start/",
				  data,
				  function(data, status){
			  		alert("ok");
        		  });
	});
    
});
    


    
