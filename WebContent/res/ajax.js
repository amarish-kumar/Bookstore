/**
 * 
 */

function doSimpleAjax(address){
	var request = new XMLHttpRequest();
	/* add your code here to grab all parameters from form*/
	request.open("GET",address, true);
	request.onreadystatechange = function() {
	handler(request);
	};
	request.send(null);
}

function handler(request){
	if ((request.readyState == 4) && (request.status == 200)){
	//var target = document.getElementById("ajaxTarget");
	//target.innerHTML = request.responseText;
		document.open();
        document.write(request.responseText);
        document.close();
	}
}