/**
 * 
 */



$(document).ready(function(){

    

    $("#confirm").click(function(){
        $.get("/4413project/Start/?reqtype=confirm",
        function(data, status){
        	//alert(data);
        	document.open();
            document.write(data);
            document.close();
        });
    });
    
});