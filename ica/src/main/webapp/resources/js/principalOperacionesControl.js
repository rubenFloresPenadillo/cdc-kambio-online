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
    
//    if (idComboBanco == null || idComboBanco == '-1'){ 
//    	$("#idMensajeComboBanco").text("El banco es obligarotio.");
//    	$("#idMensajeComboBanco").addClass( "alert alert-danger claseMensajeResultado" )
//        $("#idMensajeComboBanco").removeClass( "hidden" );
//    	$("#idMensajeComboBanco").fadeOut(6000);
//    	$("[id='idFormCuentaBancariaAdminItem:idComboBanco']").focus().select();
//    	$("[id='idFormCuentaBancariaAdminItem:idComboBanco']").addClass( "claseErrorFormulario" );
//    	event.preventDefault();
//    } else{
//    	mostrarPopupConfirmaGuardarOperaControlItem();
//    }
    	
		mostrarDialogConfirmacionFinOpera();
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

