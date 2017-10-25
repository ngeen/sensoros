var i = 0;

var machineList = function() {
	$.ajax({
		type : 'GET',
		url : '/listMachinePings',
		dataType : "json", // data type of response
		success : renderList
	});
	setTimeout(machineList, 10000);
}

var renderList = function(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);

	$('#machinesDiv div').remove();
	var div_var = "";
	var row_var = "";
	$.each(
					list,
					function(index, mac) {
						
						row_var += '<div class="col-md-2">'
								+ '<div class="servive-block '+ (mac.access ? 'servive-block-green' : 'servive-block-red') +'">'
								+ '<img src="/img/server.png" class="img-responsive" />'
								+ '<h3>'+mac.machine.ip+'</h3>'
								+ '<p>'+ (mac.access ? 'Server is online' : 'Server is offline') +'</p>' + '</div>' + '</div>';
						
						if((index+1) % 4 == 0 ){
							div_var += '<div class="row">';
							div_var += row_var;
							div_var += '</div>';
							
							row_var = "";
						} else if(list.length % 4 != 0 && (index+1) >= list.length){
							div_var += '<div class="row">';
							div_var += row_var;
							div_var += '</div>';
							
							row_var = "";
						}			

						
					});
	
	$('#machinesDiv').append(div_var);
	
	var now = new Date(Date.now());
	var formatted = now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
	$("#tarih").text(formatted);
}

$(document).ready(function() {
	machineList();
});