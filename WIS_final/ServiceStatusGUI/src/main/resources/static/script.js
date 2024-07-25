
var socket;

function connect() {
    var host = document.location.host;
    var pathname = "/"                   //document.location.pathname;
    var addr = "ws://" + host + pathname + "ServiceStatusGUI";

    socket = new WebSocket(addr);
    

    socket.onmessage = function (event) {
    	var dataString = event.data
    	if(dataString.includes("@")){
    		dataString = dataString.substring(18);
    		console.log("ws-status:" + dataString);
    		var map=dataString.replaceAll("@","<br>");
    		document.getElementById("map").innerHTML=map
    	}else{
    		dataString = dataString.substring(24);
	        console.log("ws-status:" + dataString);
	        
	
	        var dataItems = dataString.split("\t");
	
	        var table = document.getElementById("data-table");
	        while (table.rows.length > 1) {
	        	table.deleteRow(1);
	   		}
	
	        dataItems.forEach(function (item) {
	            var [name, value] = item.split(": ");
	            var row = table.insertRow();
	            var cell1 = row.insertCell(0);
	            var cell2 = row.insertCell(1);
	            cell1.textContent = name;
	            cell2.textContent = value;
	        });
    	}
    }
}