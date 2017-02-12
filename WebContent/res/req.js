/**
 * 
 */

$(document).ready(function(){
    $("#all").click(function(){
        $.post("Start",
        {
          query: "All",
        },
        function(data,status){
        	$("#content").html($(data).find("data").html());
        });
    });
    
    $("#science").click(function(){
        $.post("Start",
        {
          query: "Science",
        },
        function(data,status){
        	$("#content").html($(data).find("data").html());
        });
    });
});