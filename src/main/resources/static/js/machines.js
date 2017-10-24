var editMachine = function(id,name, ip){
	$('#name').val(name);
	$('#ip').val(ip);
	$('#id').val(id);
}

var newMachine = function(){
	$('#name').val('');
	$('#ip').val('');
	$('#id').val('');
}

var addOrUpdate = function() {
	var name = $('#name').val();
	var ip = $('#ip').val();
	var id = $('#id').val();
	if (id.length > 0) {
		updateMachine(id, name, ip);
	} else {
		addMachine(name, ip);
	}

}

var addMachine = function(name, ip) {
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '/addMachine',
		dataType : "json",
		data : '{"name":"' + name + '", "ip":"' + ip + '"}',
		success : function(data, textStatus, jqXHR) {
			var responseText = jQuery.parseJSON(jqXHR.responseText);
			$.notify(responseText, "success");
			machineList();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			var responseText = jQuery.parseJSON(jqXHR.responseText);
			$.notify(responseText, "error");
			machineList();
		}
	});
}

var updateMachine = function(id, name, ip) {
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '/updateMachine',
		dataType : "json",
		data : '{"id" : "'+id+'", "name":"' + name + '", "ip":"' + ip + '"}',
		success : function(data, textStatus, jqXHR) {
			var responseText = jQuery.parseJSON(jqXHR.responseText);
			$.notify(responseText, "success");
			machineList();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			var responseText = jQuery.parseJSON(jqXHR.responseText);
			$.notify(responseText, "error");
			machineList();
		}
	});
}

var deleteMachine = function(id) {
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '/deleteMachine',
		dataType : "json",
		data : '{"id":"' + id + '"}',
		success : function(data, textStatus, jqXHR) {
			var responseText = jQuery.parseJSON(jqXHR.responseText);
			$.notify(responseText, "success");
			machineList();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			var responseText = jQuery.parseJSON(jqXHR.responseText);
			$.notify(responseText, "error");
			machineList();
		}
	});

}

var machineList = function() {
	$.ajax({
		type : 'GET',
		url : '/listMachines',
		dataType : "json", // data type of response
		success : renderList
	});
}

var renderList = function(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);

	$('#tableBody tr').remove();
	$.each(list, function(index, mac) {
		var tr_var = '<tr><td>' + mac.name + '</td><td>' + mac.ip
				+ '</td>    <td> <button class="btn btn-primary btn-xs" onclick="editMachine(\''+mac.id+'\',\''+mac.name+'\',\''+mac.ip+'\');"><span class="glyphicon glyphicon-pencil"></span></button></td><td><button class="btn btn-danger btn-xs" onclick="deleteMachine(\''+mac.id+'\');"><span class="glyphicon glyphicon-trash"></span></button></td></tr>';
		$('#tableBody').append(tr_var);
	});
}

$(document).ready(function() {
	machineList();
});