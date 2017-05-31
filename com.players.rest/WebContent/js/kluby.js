
$(document).ready(function() {

	 wybierzDruzyny();
	
	$(document.body).on('click', ':button, .UPDATE_BTN', function(e) {
		//console.log(this);
	});
	

	});




function wybierzDruzyny() {
	
	var d = new Date()
		, n = d.getTime();
	
	ajaxObj = {  
			type: "GET",
			url: "http://localhost:7001/com.players.rest/rest/v1/setteam/", 
			data: "ts="+n, 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) { 
				//console.log(data);
				var html_string = "";
				
				$.each(data, function(index1, val1) {
					//console.log(val1);
					html_string = html_string + templateGetInventory(val1);
				});
				
				$('#get_inventory').html("<table border='1'>" + html_string + "</table>");
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

function templateGetInventory(param) {
	return '<tr>' +
			 
				'<td class="CL_CLUB_NAME">' + param.CLUB_NAME + '</td>' +
				'<td class="CL_CLUB_CITY">' + param.CLUB_CITY + '</td>' +
				'<td class="CL_CLUB_PKT">' + param.CLUB_PKT + '</td>' +
			
				 
			'</tr>';
}