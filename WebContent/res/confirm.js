/**
 * 
 */

$(document).ready(function(){

    $("#pay").click(function(){
    	var map = {};
    	map["reqtype"] = "confirm";
    	map["creditcard"] = $("#credit").val();
        $.post("/4413project/Start/",
        map,
        function(data, status){
        	document.open();
            document.write(data);
            document.close();
        	//alert(data);
        });
    });
    
});