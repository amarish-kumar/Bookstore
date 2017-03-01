/**
 * 
 */

$(document).ready(function(){
	var map = {};
    $("#all").click(function(){
        $.get("/4413project/Start/?reqtype=catalog&query=All",
        function(data,status){
        	$("#content").html($(data).find("data").html());
        });
    });
    
    $("#science").click(function(){
        $.get("/4413project/Start/?reqtype=catalog&query=Science",
        function(data,status){
        	$("#content").html($(data).find("data").html());
        });
    });
    
    $("#engineering").click(function(){
    	$.get("/4413project/Start/?reqtype=catalog&query=Engineering",
        function(data,status){
        	$("#content").html($(data).find("data").html());
        });
    });
    $("#fiction").click(function(){
        $.get("/4413project/Start/?reqtype=catalog&query=Fiction",
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


//    $("#login").click(function(){
//    	var loginmap = {};
//    	loginmap["reqtype"] = "login";
//    	loginmap["login"] = $("#log").val();
//    	loginmap["password"] = $("#pass").val();
//        $.post("/4413project/Start/",
//        loginmap,
//        function(data, status){
////        	document.open();
////            document.write(data);
////            document.close();
//        	//alert(data);
//        	$("#loginArea").html($(data).find("data").html());
//
//        });
//    });
//    
//
//    $("#create").click(function(){
//        $.get("/4413project/Start/?reqtype=account",
//        function(data, status){
//        	//alert(data);
//        	document.open();
//            document.write(data);
//            document.close();
//        });
//    });
    
    function updateCart(){
    	var total = 0;
    	for(var prop in map){
    		total += + map[prop];
    	}
    	$("#cart").val(total);
    }
});