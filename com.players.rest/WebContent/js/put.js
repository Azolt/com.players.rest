
$(document).ready(function() {
	
	var $put_example = $('#put_example')
		, $SET_PLAYER_NAME = $('#SET_PLAYER_NAME')
		, $SET_PLAYER_SURNAME = $('#SET_PLAYER_SURNAME');
	
	getInventory();
	
	$(document.body).on('click', ':button, .UPDATE_BTN', function(e) {
		//console.log(this);
		var $this = $(this)
			, PLAYER_ID = $this.val()
			, $tr = $this.closest('tr')
			, PLAYER_NAME = $tr.find('.CL_PLAYER_NAME').text()
			, PLAYER_SURNAME = $tr.find('.CL_PLAYER_SURNAME').text()
			, PLAYER_NUMBER = $tr.find('.CL_PLAYER_NUMBER').text()
			, PLAYER_CLUB = $tr.find('.CL_PLAYER_CLUB').text();
		
		$('#SET_SET_PLAYER_ID').val(PLAYER_ID);
		$SET_PLAYER_NAME.text(PLAYER_NAME);
		$SET_PLAYER_SURNAME.text(PLAYER_SURNAME);
		$('#SET_PLAYER_NUMBER').val(PLAYER_NUMBER);
		$('#SET_PLAYER_CLUB').text(PLAYER_CLUB);
	
		
		$('#update_response').text("");
	});
	
	$put_example.submit(function(e) {
		e.preventDefault(); 
		
		var obj = $put_example.serializeObject()
			, PLAYER_NAME = $SET_PLAYER_NAME.text()
			, PLAYER_SURNAME = $SET_PLAYER_SURNAME.text();
		
		updateInventory(obj,  PLAYER_SURNAME,PLAYER_CLUB);
	});
});

function updateInventory(obj, maker, code) {
	
	ajaxObj = {  
			type: "PUT",
			url: "http://localhost:7001/com.players.rest/rest/v3/set/" + maker + "/" + code,
			data: JSON.stringify(obj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) {
				//console.log(data);
				$('#update_response').text( data[0].MSG );
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
				'<td class="CL_PC_PLAYER_NAME">' + param.PLAYER_NAME + '</td>' +
				'<td class="CL_PLAYER_SURNAME">' + param.PLAYER_SURNAME + '</td>' +
				'<td class="CL_PC_PLAYER_CLUB">' + param.PLAYER_CLUB + '</td>' +
				'<td class="CL_PC_PLAYER_NUMBER">' + param.PLAYER_NUMBER + '</td>' +
				'<td class="CL_OraclePlayers"> <button class="UPDATE_BTN" value=" ' + param.OraclePlayers + ' " type="button">Zmien</button> </td>' +
			'</tr>';
}