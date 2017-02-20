/**
 * 
 */

$(document).ready(function(){
	var map = {};
	$(document).on('click', '.add', function() {
        //alert("hello");
    	var curQuan = parseInt($(".quan").eq($(this).attr("value")).text());
    	var val = parseInt($(".quanInput").eq($(this).attr("value")).val());
    	var newQuan = curQuan + val;
    	$(".quan").eq($(this).attr("value")).text(newQuan);
    	var key = $(this).attr("name");
    	var quan = $(".quan").eq($(this).attr("value")).text();
    	if(curQuan > 0){
    		map[key] = quan;
        	$.post("/4413project/Start/",
        		    map,
        		   function(data, status){
        		            //alert("Data: " + data + "\nStatus: " + status);
             			//$("#content").html($(data).find("data").html());
    		    		 document.open();
    		             document.write(data);
    		             document.close();
        		     		        	
        	 		});
    	}
    	
    	
    });
    
    $(document).on('click', '.remove', function() {
        //alert("hello");
    	var curQuan = parseInt($(".quan").eq($(this).attr("value")).text());
    	var val = parseInt($(".quanInput").eq($(this).attr("value")).val());
    	if(curQuan > 0 && curQuan >= val){
    		
    		var newQuan = curQuan - val;
    		$(".quan").eq($(this).attr("value")).text(newQuan);
    		var key = $(this).attr("name");
        	var quan = $(".quan").eq($(this).attr("value")).text();
        	map[key] = quan;
        	$.post("/4413project/Start/",
        		    map,
        		   function(data, status){
        		            //alert("Data: " + data + "\nStatus: " + status);
             			//$("#content").html($(data).find("data").html());
    		    		 document.open();
    		             document.write(data);
    		             document.close();
       		 		     		        	
        	 		});
    	}
    	
    });
    
	$(document).on('click', '.delete', function() {
        //alert("hello");
    	//var curQuan = parseInt($(".quan").eq($(this).attr("value")).text());
    	//var val = parseInt($(".quanInput").eq($(this).attr("value")).val());
    	//var newQuan = curQuan + val;
    	//$(".quan").eq($(this).attr("value")).text(newQuan);
    	var key = $(this).attr("name");
    	//var quan = $(".quan").eq($(this).attr("value")).text();
    	map[key] = 0;
    	$.post("/4413project/Start/",
    		    map,
    		   function(data, status){
    		            //alert("Data: " + data + "\nStatus: " + status);
         			//$("#content").html($(data).find("data").html());
		    		 document.open();
		             document.write(data);
		             document.close();
    		     		        	
    	 		});
    	
    });
});