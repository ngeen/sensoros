var i = 0;

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

	$('#machinesDiv div').remove();
	var div_var = "<div class=\"row\" id=\"machines\">";
	$.each(
					list,
					function(index, mac) {
						
						div_var += '<div class="col-md-2">'
								+ '<div class="servive-block">'
								+ '<p class="lastUpdate text-left"></p>'
								+ '<input type="hidden" id="id" value="'+mac.id+'">'
								+ '<img src="/img/server.png" class="img-responsive" />'
								+ '<h3>'+mac.ip+'</h3>'
								+ '<p class="access text-left"></p>'
								+ '<p class="time text-left"></p>'
								+ '<p class="ttl text-left"></p>'
								+ '<p class="size text-left"></p>'
								+ '</div>' + '</div>';
						
					});
	
	div_var += "</div>";
	
	$('#machinesDiv').append(div_var);
	
	pingMachine();
}

var pingMachine = function(){
	$('#machines > div > div').each(function(index, obj){
		var id = $(obj).find('input').val();
		$.ajax({
			type : 'GET',
			url : '/machinePing/'+id,
			dataType : "json", // data type of response
			success : function(data){
				var time = new Date();
				var timeText = 
				    ("0" + time.getHours()).slice(-2)   + ":" + 
				    ("0" + time.getMinutes()).slice(-2) + ":" + 
				    ("0" + time.getSeconds()).slice(-2);
				$(obj).find(".lastUpdate").text("LastUpdate "+timeText);
				$(obj).find(".ttl").text("TTL "+data.ttl);
				$(obj).find(".size").text("Size "+data.size);
				$(obj).find(".time").text("Time "+data.time);
				$(obj).removeAttr('class');
				if(data.access){
					$(obj).addClass("servive-block servive-block-green");
					$(obj).find(".access").text('Server is online');
				} else {
					$(obj).addClass("servive-block servive-block-red");
					$(obj).find(".access").text('Server is offline');
				}
			}
		});
		
	});
	
	setTimeout(pingMachine, 20000);
}

var zoneHeat = function() {
	$.ajax({
		type : 'GET',
		url : '/getHeat',
		dataType : "json", // data type of response
		success : renderHeat
	});
}

var renderHeat = function(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	
	$('#heat > div ').each(function(index, obj){
				var time = new Date();
				var timeText = 
				    ("0" + time.getHours()).slice(-2)   + ":" + 
				    ("0" + time.getMinutes()).slice(-2) + ":" + 
				    ("0" + time.getSeconds()).slice(-2);
				$(obj).find(".lastUpdate").text("LastUpdate "+timeText);
				$(obj).find(".temp").text('Temprature  '+data.temp.toFixed(2)+' °');
				$(obj).find(".humidity").text('Humidity  % '+data.humidity.toFixed(2));
		
	});
	
	setTimeout(zoneHeat, 3000);
}

$(document).ready(function() {
	
	$('#heatDiv div').remove();
	var div_var = "<div class=\"row\" id=\"heat\">";
						
						div_var += '<div class="col-md-2">'
								+ '<div class="servive-block servive-block-gray">'
								+ '<p class="lastUpdate"></p>'
								+ '<img src="/img/dataCenter.png" class="img-responsive" />'
								+ '<p class="temp"></p>'
								+ '<p class="humidity"></p>'
								+ '</div>' + '</div>';
	
	div_var += "</div>";
	
	$('#heatDiv').append(div_var);
	
	zoneHeat();
	machineList();
});