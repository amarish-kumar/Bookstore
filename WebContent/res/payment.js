/**
 * 
 */


$(document).ready(function(){
	var map = {};

    $("#sub").click(function(){
    	map["reqtype"] = "payment";
    	map["login"] = $("#log").val();
    	map["password"] = $("#pass").val();
        $.post("/4413project/Start/",
        map,
        function(data, status){
            //alert("Data: " + data + "\nStatus: " + status);
        	//$("body").html(data);;
        	//$("#content").html($(data).find("data").html());
//        	document.open();
//            document.write(data);
//            document.close();
        	alert(data);
        });
    });
    
});