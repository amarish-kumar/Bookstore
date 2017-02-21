/**
 * 
 */

$(document).ready(function(){
	var map = {};
    $("#all").click(function(){
        $.get("/4413project/Start/?query=All",
        function(data,status){
        	$("#content").html($(data).find("data").html());
        });
    });
    
    $("#science").click(function(){
        $.get("/4413project/Start/?query=Science",
        function(data,status){
        	$("#content").html($(data).find("data").html());
        });
    });
    
    $("#engineering").click(function(){
        $.get("/4413project/Start/?query=Engineering",
        function(data,status){
        	$("#content").html($(data).find("data").html());
        });
    });
    $("#fiction").click(function(){
        $.get("/4413project/Start/?query=Fiction",
        function(data,status){
        	$("#content").html($(data).find("data").html());
        });
    });
    
    $("#checkout").click(function(){
    	map["reqtype"] = "checkout";
        $.post("/4413project/Start/",
        map,
        function(data, status){
            //alert("Data: " + data + "\nStatus: " + status);
        	//$("body").html(data);;
        	//$("#content").html($(data).find("data").html());
        	document.open();
            document.write(data);
            document.close();
        });
    });
    
    $(document).on('click', '.addCart', function() {
        //alert("You clicked the new button");
    	//alert($(".addQuan").eq($(this).attr("value")).val());
    	//total +=  + $(".addQuan").eq($(this).attr("value")).val();
    	//$("#cart").val(total);
    	var key = $(this).attr("name");
    	var val = parseInt($(".addQuan").eq($(this).attr("value")).val());
    	if(val > 0){
    		//map[key] = val;
        	if(map.hasOwnProperty(key)){
        		var oldQuan = map[key];
        		var newQuan = oldQuan + val;
        		map[key] = newQuan;
        	}
        	else{
        		map[key] = val;
        		//alert(map[key]);
        	}
        	updateCart();
    	}
    	else{
    		alert("invalid quantity");
    	}
    
    	
    });
    
//    $(document).on('click', '.add', function() {
//        //alert("hello");
//    	var curQuan = parseInt($(".quan").eq($(this).attr("value")).text());
//    	var val = parseInt($(".quanInput").eq($(this).attr("value")).val());
//    	var newQuan = curQuan + val;
//    	$(".quan").eq($(this).attr("value")).text(newQuan);
//    	var key = $(this).attr("name");
//    	var quan = $(".quan").eq($(this).attr("value")).text();
//    	map[key] = quan;
//    	 $.post("/4413project/Start/",
//    		    map,
//    		   function(data, status){
//    		            //alert("Data: " + data + "\nStatus: " + status);
//         			$("#content").html($(data).find("data").html());
//    		 
//
//    		     		        	
//    	 		});
//    	
//    	
//    	
//    	
//    });
//    
//    $(document).on('click', '.remove', function() {
//        //alert("hello");
//    	var curQuan = parseInt($(".quan").eq($(this).attr("value")).text());
//    	var val = parseInt($(".quanInput").eq($(this).attr("value")).val());
//    	if(curQuan > 0 && curQuan >= val){
//    		
//    		var newQuan = curQuan - val;
//    		$(".quan").eq($(this).attr("value")).text(newQuan);
//    	}
//    	//alert($(".quan").eq($(this).attr("value")).text());
//    	//alert($(".quanInput").eq($(this).attr("value")).val());
//    	//total +=  + $(".addQuan").eq($(this).attr("value")).val();
//    	//$("#cart").val(total);
//    	//var key = $(this).attr("name");
//    	//var val = $(".addQuan").eq($(this).attr("value")).val();
//    	//var table = $("#tab");
//    	//var index = $(this).attr("value");
////    	var val = $("#q0").val();
////    	alert(val);
//    	
//    	
//    	
//    });
    
    function updateCart(){
    	var total = 0;
    	for(var prop in map){
    		total += + map[prop];
    	}
    	$("#cart").val(total);
    }
});