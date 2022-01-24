$(document).ready(function() {
	//    $('#idTablaOperacionesHistoricas').DataTable();

	$('#idTablaOperacionesHistoricas').DataTable({
		"language" : {
			"infoEmpty" : "No hay registros disponibles",
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
});

function mostrarPopupCerrarSesion(){
	$('#idModalConfirmacionCerrarSesion').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionCerrarSesion').modal('show');
}