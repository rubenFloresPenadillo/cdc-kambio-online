$(document).ready(function() {
	//    $('#idTablaOperacionesHistoricas').DataTable();

	$('#idTablaOperacionesHistoricas').DataTable({
		"language" : {
			"emptyTable" : "No hay registros disponibles",
			"paginate": {
			      "previous": "Anterior",
			      "next": "Siguiente",
			    }
		},

		"searching" : false,
		"lengthChange" : false,
		"ordering" : false,
		"info" : false
		
		

	});

});

$(document).ready(function() {
	$(".botonCerrarSesion").find("span").remove();
	$('#idModalOperacionEnCurso').modal('show');
});

function mostrarPopupCerrarSesion(){
	$('#idModalConfirmacionCerrarSesion').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionCerrarSesion').modal('show');

}

function mostrarPopupConfirmaCancelarCliente(){
	$('#idModalConfirmacionCancelarCliente').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionCancelarCliente').modal('show');

}

function operacionCanceladaExitosa(){
	$('#idModalOperacionCancelada').modal({backdrop: 'static', keyboard: false})  
	$('#idModalOperacionCancelada').modal('show');
}

function procesoConError(){
	$('#idModalErrorSistema').modal({backdrop: 'static', keyboard: false})  
	$('#idModalErrorSistema').modal('show');
}

function enlaceWhatsappVerificacion(codigoOperacion) {
	 var win = window.open('https://api.whatsapp.com/send?phone=51992278011&text=Hola%20JirehPlus!%20He%20visto%20sus%20servicios%20desde%20su%20plataforma%20web%20y%20tengo%20una%20consulta.'+codigoOperacion, '_blank');
	 win.focus();  
}