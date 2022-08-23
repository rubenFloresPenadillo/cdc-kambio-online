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
	 var win = window.open('https://api.whatsapp.com/send?phone=51939722555&text=Hola,%20solicito%20información%20de%20mi%20operación%20con%20código%20'+codigoOperacion, '_blank');
	 win.focus();  
}