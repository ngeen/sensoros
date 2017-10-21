$(document).ready(
		function() {
			$('.input-group.date').datepicker({
				todayBtn : "linked",
				language : "tr",
				calendarWeeks : true,
				autoclose : true,
				todayHighlight : true,
				format : "yyyy-mm-dd"
			});

			$("#arama").click(
					function() {
						var tarih = $('#tarihTxt').val();
						var zaman = tarih.split('-');
						if (tarih.length > 0) {
							var radio = parseInt($(
									'input[name=secimRadio]:checked',
									'#tarihSec').val());
							switch (radio) {
							case '1':
								tarih = zaman[0] + "-" + zaman[1] + "-"
										+ zaman[2];
								break;
							case 2:
								tarih = zaman[0] + "-" + zaman[1];
								break;
							case 3:
								tarih = zaman[0];
								break;
							}
							
							var sayfa;
							if(parseInt($('#sayfaId').val()) == 0){
								sayfa = '/ipGirisSaat/';
							} else if(parseInt($('#sayfaId').val()) == 1){
								sayfa = '/ipGirisGun/';
							} else if(parseInt($('#sayfaId').val()) == 2){
								sayfa = '/browserGirisGun/';
							}
							window.location = window.location.protocol + "//"
									+ window.location.host + sayfa
									+ tarih;
						} else {
							alert("Lütfen tarih seçimi yapınız !!!");
						}

					});
		});