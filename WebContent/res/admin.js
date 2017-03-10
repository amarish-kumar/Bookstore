/**
 * 
 */

$(document).ready(function(){

    

    $("#sub").click(function(){
    	var val = $("#selc").val();
        $.get("/4413project/Admin/?s=" + val,
        function(data, status){
        	//alert(data);
        	$("#content").html($(data).find("data").html());
//        	document.open();
//            document.write(data);
//            document.close();
        });
    });
    
});