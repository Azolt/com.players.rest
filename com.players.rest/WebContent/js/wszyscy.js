
$(document).ready(function() {
	
	wszyscyZawodnicy();
	
	$(document.body).on('click', ':button, .UPDATE_BTN', function(e) {
	
	});
	
	$put_example.submit(function(e) {
		e.preventDefault(); 
		

	});
});

function 
wszyscyZawodnicy() {
	
	var d = new Date()
		, n = d.getTime();
	
	ajaxObj = {  
			type: "GET",
			url: "http://localhost:7001/com.players.rest/rest/v1/set", 
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
				'<td class="CL_PC_PLAYER_NAME">' + param.PLAYER_NAME + '</td>' +
				'<td class="CL_PLAYER_SURNAME">' + param.PLAYER_SURNAME + '</td>' +
				'<td class="CL_PC_PLAYER_CLUB">' + param.PLAYER_CLUB + '</td>' +
				'<td class="CL_PC_PLAYER_NUMBER">' + param.PLAYER_NUMBER + '</td>' +
				 
			'</tr>';
}