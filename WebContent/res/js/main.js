/**
 * 
 */

$(document).ready(function(){
	
	var map = {};
    $("#all").click(function(){
        $.get(path + "?reqtype=catalog&query=All",
        function(data,status){
        	$("#content").html($(data).find("data").html());
        });
    });
    
    $("#science").click(function(){
        $.get(path + "?reqtype=catalog&query=Science",
        function(data,status){
        	$("#content").html($(data).find("data").html());
        });
    });
    
    $("#engineering").click(function(){
    	$.get(path + "?reqtype=catalog&query=Engineering",
        function(data,status){
        	$("#content").html($(data).find("data").html());
        });
    });
    $("#fiction").click(function(){
        $.get(path + "?reqtype=catalog&query=Fiction",
        function(data,status){
        	$("#content").html($(data).find("data").html());
        });
    });
    
    $("#checkout").click(function(){
    	map["reqtype"] = "checkout";
        $.post(path,
        map,
        function(data, status){
        	$("#page-content").html($(data).find("data").html());
        });
    });
    
    $(document).on('click', '.addCart', function() {
      
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
    
    function updateCart(){
    	var total = 0;
    	for(var prop in map){
    		total += + map[prop];
    	}
    	$("#cart").val(total);
    }
    
    
    
//    checkout area js
    var map2 = {};
    $(document).on('click', '.add', function() {
        //alert("hello");
    	var curQuan = parseInt($(".quan").eq($(this).attr("value")).text());
    	var val = parseInt($(".quanInput").eq($(this).attr("value")).val());
    	var newQuan = curQuan + val;
    	$(".quan").eq($(this).attr("value")).text(newQuan);
    	var key = $(this).attr("name");
    	var quan = $(".quan").eq($(this).attr("value")).text();
    	if(curQuan > 0){
        	map2["reqtype"] = "checkout";
    		map2[key] = quan;
        	$.post(path,
        		    map2,
        		   function(data, status){
        		            //alert("Data: " + data + "\nStatus: " + status);
             			//$("#content").html($(data).find("data").html());
//    		    		 document.open();
//    		             document.write(data);
//    		             document.close();
            			$("#page-content").html($(data).find("data").html());

        		     		        	
        	 		});
    	}
    	else{
    		alert("invalid quantity");
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
        	map2["reqtype"] = "checkout";
        	map2[key] = quan;
        	$.post(path,
        		    map2,
        		   function(data, status){
        		            //alert("Data: " + data + "\nStatus: " + status);
             			//$("#content").html($(data).find("data").html());
//    		    		 document.open();
//    		             document.write(data);
//    		             document.close();
            			$("#page-content").html($(data).find("data").html());

       		 		     		        	
        	 		});
    	}
    	else{
    		alert("invalid quantity");
    	}
    });
    
	$(document).on('click', '.delete', function() {
       
    	var key = $(this).attr("name");
    	map2["reqtype"] = "checkout";
    	map2[key] = 0;
    	$.post(path,
    		    map2,
    		   function(data, status){
//    		         
//		    		 document.open();
//		             document.write(data);
//		             document.close();
        			$("#page-content").html($(data).find("data").html());

    		     		        	
    	 		});
    	
    });
	
	$(document).on('click', '#payment', function() {
		 $.get(path + "?reqtype=payment",
			        function(data,status){
//					 document.open();
//		             document.write(data);
//		             document.close();
	        			$("#page-content").html($(data).find("data").html());

	     		        	
			        });
    	
    });
	
//	login area js
	var map3 = {};
	$(document).on('click', '#sub', function(){
    	map3["reqtype"] = "login";
    	map3["login"] = $("#log").val();
    	map3["password"] = $("#pass").val();
        $.post(path,
        map3,
        function(data, status){
//        	document.open();
//            document.write(data);
//            document.close();
        	//alert(data);
        	$("#login-area").empty();
			$("#login-area").html($(data).find("data").html());

        });
    });
	
	var map3 = {};
	$(document).on('click', '#sub-main', function(){
    	map3["reqtype"] = "payment";
    	map3["login"] = $("#log-main").val();
    	map3["password"] = $("#pass-main").val();
        $.post(path,
        map3,
        function(data, status){
			$("#login-area").html($(data).find("user").html());
			$("#page-content").html($(data).find("data").html());

        });
    });
    

	$(document).on('click', '#acc', function(){
        $.get(path + "?reqtype=account",
        function(data, status){
			$("#page-content").html($(data).find("data").html());

        });
    });
	
	$(document).on('click', '#logout', function(){
        $.get(path + "?reqtype=logout",
        function(data, status){
        	$("#login-area").html($(data).find("data").html());
        });
    });
	

    
//    confirmation area
	$(document).on('click', '#confirm', function(){
		var logstatus = $('#logStatus').val();
		if(logstatus == "true"){
			$.get(path + "?reqtype=confirm",
			        function(data, status){
						$("#page-content").html($(data).find("data").html());

			       });
		}
		else{
  			$('#error').show("fast");
  			setTimeout(function() { $("#error").hide(); }, 3000);
  		}
      	
		
    });
    
    
//    credit card area
      var map4 = {};
      $(document).on('click', '#pay', function(){
    	  	var logstatus = $('#logStatus').val();
			if(logstatus == "true"){
		    	map4["reqtype"] = "confirm";
		    	map4["creditcard"] = $("#car_number").val();
		        $.post(path2,
		        map4,
		        function(data, status){
		        	$("#page-content").html($(data).find("data").html());
		        });
			}
			else{
	  			$('#error').show("fast");
	  			setTimeout(function() { $("#error").hide(); }, 3000);
	  		}
	    });
      
//   review section
      
      $(document).on('click', '.review', function(){
    	  var bid = $(this).val();
          $.get(path + "?reqtype=review&bid=" + bid,
          function(data, status){
  			$("#page-content").html($(data).find("data").html());

          });
      });
      
  
      $(document).on('click', '.rate', function(){
    	  var rating = $(this).val();
          $("#rating").val(rating);
      });
      
    var map5 = {};
  	$(document).on('click', '#revSub', function(){
  		var logstatus = $('#logStatus').val();
  		if(logstatus == "true"){
  			map5["reqtype"] = "review";
  	      	map5["bid"] = $("#bid").val();
  	      	map5["login"] = $("#login").val();
  	      	map5["rating"] = $("#rating").val();
  	      	map5["rev"] = $("#rev").val();

  	          $.post(path,
  	          map5,
  	          function(data, status){
  	        	  $("#page-content").html($(data).find("data").html());

  	          });
  		}
  		else{
  			$('#error').show("fast");
  			setTimeout(function() { $("#error").hide(); }, 3000);
  		}
      	
      });
      
//     search area
  	
  	$(document).on('click', '#search', function(){
  	  var searchInput = $("#searchInput").val();
        $.get(path + "?reqtype=catalog&query=search&text=" + searchInput,
        function(data, status){
        	$("#content").html($(data).find("data").html());

        });
    });


    
});