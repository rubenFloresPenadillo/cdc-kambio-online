function iniciarBotonesAcciones(){
	$(".botonDetalle").find("span").remove();
	$(".botonCancelar").find("span").remove();
	$(".botonCheck").find("span").remove();
	$(".botonAtender").find("span").remove();
}

function iniciarTabla(){
	$('#idTablaOperacionesControl').DataTable({
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
		"info" : false,
		"fnDrawCallback": function( oSettings ) {
			iniciarBotonesAcciones();
		 }
		
	});
}

$(document).ready(function() {
	$(".botonCerrarSesion").find("span").remove();
});

function mostrarPopupCerrarSesion(){
	$('#idModalConfirmacionCerrarSesion').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionCerrarSesion').modal('show');

}

$(document).ready(function() {
	iniciarTablaYBotones();
});

function iniciarTablaYBotones(){
	iniciarTabla();
	iniciarBotonesAcciones();
}


function procesoConError(){
	$('#idModalErrorSistema').modal({backdrop: 'static', keyboard: false})  
	$('#idModalErrorSistema').modal('show');
}

function enviarFinalizar(event) {
		
	$("#idMensajeNumOperaComercio").empty();
	$("#idMensajeNumOperaComercio").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeNumOperaComercio").addClass( "hidden" );
	$("#idMensajeNumOperaComercio").css("display","block");
	
	$("[id='idFormOperacionControlItem:idTxtNumOperaComercio']").removeClass( "claseErrorFormulario" );
	
    var idTxtNumOperaComercio = $("[id='idFormOperacionControlItem:idTxtNumOperaComercio']").val();
    
    if (idTxtNumOperaComercio == null || idTxtNumOperaComercio == ''){
    	$("#idMensajeNumOperaComercio").text("El numero de operación del comercio es obligatorio.");
    	$("#idMensajeNumOperaComercio").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeNumOperaComercio").removeClass( "hidden" );
    	$("#idMensajeNumOperaComercio").fadeOut(6000);
    	$("[id='idFormOperacionControlItem:idTxtNumOperaComercio']").focus();
    	$("[id='idFormOperacionControlItem:idTxtNumOperaComercio']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else{
    	mostrarDialogConfirmacionFinOpera();
    }

}

function enviarCancelar(event) {
    
	$("#idMensajeMotivoCancelacion").empty();
	$("#idMensajeMotivoCancelacion").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeMotivoCancelacion").addClass( "hidden" );
	$("#idMensajeMotivoCancelacion").css("display","block");
	
	
	$("[id='idFormOperacionControlItem:idTxtMotivoCancelacion']").removeClass( "claseErrorFormulario" );
  	
	/*Captura valores*/
	
    var idTxtMotivoCancelacion = $("[id='idFormOperacionControlItem:idTxtMotivoCancelacion']").val();
    
    if (idTxtMotivoCancelacion == null || idTxtMotivoCancelacion == ''){
    	$("#idMensajeMotivoCancelacion").text("El motivo de cancelación es obligatorio.");
    	$("#idMensajeMotivoCancelacion").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeMotivoCancelacion").removeClass( "hidden" );
    	$("#idMensajeMotivoCancelacion").fadeOut(6000);
    	$("[id='idFormOperacionControlItem:idTxtMotivoCancelacion']").focus();
    	$("[id='idFormOperacionControlItem:idTxtMotivoCancelacion']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else{
    	mostrarDialogConfirmacionCanceOpera();
    }

}


function mostrarPopupConfirmaGuardarOperaControlItem(){
	$('#idModalConfirmacionGuardaOperaControlItem').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionGuardaOperaControlItem').modal('show');

}

//function operacionGuardarOperaControlItemExitoso(){
//	$('#idModalGuardarOperacionControlExito').modal({backdrop: 'static', keyboard: false})  
//	$('#idModalGuardarOperacionControlExito').modal('show');
//}

function mostrarPopupConfirmaFinalizar(){
	$('#idModalConfirmacionFinalizar').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionFinalizar').modal('show');
}

function mostrarPopupConfirmaCancelar(){
	$('#idModalConfirmacionCancelar').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionCancelar').modal('show');
}

function operacionFinalizadaExitosa(){
	$('#idModalOperacionFinalizada').modal({backdrop: 'static', keyboard: false})  
	$('#idModalOperacionFinalizada').modal('show');
}

function operacionCanceladaExitosa(){
	$('#idModalOperacionCancelada').modal({backdrop: 'static', keyboard: false})  
	$('#idModalOperacionCancelada').modal('show');
}



function enviarDatosReporte(event) {
	
//	$("[id='idFormTipoCambioItem:idMensajeResultadoProceso']").css("display","none");
	
	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );
	
	$("#idMensajeFechaDesde").empty();
	$("#idMensajeFechaDesde").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeFechaDesde").addClass( "hidden" );
	$("#idMensajeFechaDesde").css("display","block");
	
	$("#idMensajeFechaHasta").empty();
	$("#idMensajeFechaHasta").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeFechaHasta").addClass( "hidden" );
	$("#idMensajeFechaHasta").css("display","block");
	
	
	/*Remueves los errores si es que hubieran*/
	
	$("[id='idFormReporteOperacionesControl:idFechaDesde']").removeClass( "claseErrorFormulario" );
	$("[id='idFormReporteOperacionesControl:idFechaHasta']").removeClass( "claseErrorFormulario");
	
	/*Captura valores*/
	
    var idFechaDesde = $("[id='idFormReporteOperacionesControl:idFechaDesde_input']").val();
    var idFechaHasta = $("[id='idFormReporteOperacionesControl:idFechaHasta_input']").val();

    if (idFechaDesde == null || idFechaDesde == '' ){ 
    	$("#idMensajeFechaDesde").text("La fecha desde es obligarotia.");
    	$("#idMensajeFechaDesde").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeFechaDesde").removeClass( "hidden" );
    	$("#idMensajeFechaDesde").fadeOut(6000);
    	$("[id='idFormReporteOperacionesControl:idFechaDesde']").focus().select();
    	$("[id='idFormReporteOperacionesControl:idFechaDesde']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idFechaHasta == null || idFechaHasta == '' ){ 
    	$("#idMensajeFechaHasta").text("La fecha hasta es obligarotia.");
    	$("#idMensajeFechaHasta").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeFechaHasta").removeClass( "hidden" );
    	$("#idMensajeFechaHasta").fadeOut(6000);
    	$("[id='idFormReporteOperacionesControl:idFechaHasta']").focus().select();
    	$("[id='idFormReporteOperacionesControl:idFechaHasta']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else{
//    	metodoRemotoEnviarFormulario();
//    	ejecutarReporte();
    }
}
