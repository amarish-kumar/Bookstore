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
        $.post("/4413project/Start/",
        map,
        function(data, status){
            //alert("Data: " + data + "\nStatus: " + status);
        	$("body").html(data);
        });
    });
    
    $(document).on('click', '.addCart', function() {
        //alert("You clicked the new button");
    	//alert($(".addQuan").eq($(this).attr("value")).val());
    	//total +=  + $(".addQuan").eq($(this).attr("value")).val();
    	//$("#cart").val(total);
    	var key = $(this).attr("name");
    	var val = $(".addQuan").eq($(this).attr("value")).val();
    	map[key] = val;
    	if(map.hasOwnProperty(key) && map[key] == 0){
    		delete map[key];
    	}
    	else{
    		map[key] = val;
    		alert(map[key]);
    	}
    	updateCart();
    	
    });
    
    function updateCart(){
    	var total = 0;
    	for(var prop in map){
    		total += + map[prop];
    	}
    	$("#cart").val(total);
    }
});