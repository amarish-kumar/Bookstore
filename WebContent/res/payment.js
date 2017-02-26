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
        	document.open();
            document.write(data);
            document.close();
        	//alert(data);
        });
    });
    

    $("#create").click(function(){
        $.get("/4413project/Start/?reqtype=account",
        function(data, status){
        	//alert(data);
        	document.open();
            document.write(data);
            document.close();
        });
    });
    
});