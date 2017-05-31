$(document).ready(function() {
	
	getInventory();
	
	$(document.body).on('click', ':button, .DELETE_BTN', function(e) {
		//console.log(this);
		var $this = $(this)
			, PLAYER_ID = $this.val()
			, obj = {PLAYER_ID : PLAYER_ID}
			, $tr = $this.closest('tr')
			, PLAYER_SURNAME = $tr.find('.CL_PLAYER_SURNAME').text()
			, PLAYER_NAME = $tr.find('.CL_PLAYER_NAME').text();
			
		
		deleteInventory(obj, PLAYER_NAME);
	});
});

function deleteInventory(obj, name) {
	
	ajaxObj = {  
			type: "DELETE",
			url: "http://localhost:7001/com.players.rest/rest/v3/set/" + name,
			data: JSON.stringify(obj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) {
				//console.log(data);
				$('#delete_response').text( data[0].MSG );
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
				getInventory();
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

function getInventory() {
	
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
				'<td class="CL_PLAYER_NAME">' + param.PLAYER_NAME + '</td>' +
				'<td class="CL_PLAYER_SURNAME">' + param.PLAYER_SURNAME + '</td>' +
				'<td class="CL_PLAYER_NUMBER">' + param.PLAYER_NUMBER + '</td>' +
				'<td class="CL_PLAYER_CLUB">' + param.PLAYER_CLUB + '</td>' +
				
				'<td class="CL_PC_PARTS_BTN"> <button class="DELETE_BTN" value=" ' + param.PLAYER_ID + ' " type="button">Delete</button> </td>' +
			'</tr>';
}