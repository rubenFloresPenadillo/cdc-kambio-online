function iniciarBotonesAcciones(){
	$(".botonDetalle").find("span").remove();
	$(".botonEditar").find("span").remove();
}

function iniciarTabla(){
	$('#idTablaPrincipal').DataTable({
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
	iniciarTablaYBotones();
	$(".botonCerrarSesion").find("span").remove();
});

function mostrarPopupCerrarSesion(){
	$('#idModalConfirmacionCerrarSesion').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionCerrarSesion').modal('show');

}

function iniciarTablaYBotones(){
	iniciarTabla();
	iniciarBotonesAcciones();
}

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

function procesoConError(){
	$('#idModalErrorSistema').modal({backdrop: 'static', keyboard: false})  
	$('#idModalErrorSistema').modal('show');
}

/* Bloque para el la divisa Item */


function enviar(event) {
	
//	$("[id='idFormCuentaItem:idMensajeResultadoProceso']").css("display","none");
	
	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );
	
	$("#idMensajeComboBanco").empty();
	$("#idMensajeComboBanco").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeComboBanco").addClass( "hidden" );
	$("#idMensajeComboBanco").css("display","block");
	
	$("#idMensajeComboDivisa").empty();
	$("#idMensajeComboDivisa").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeComboDivisa").addClass( "hidden" );
	$("#idMensajeComboDivisa").css("display","block");
	
	$("#idMensajeTipoCuenta").empty();
	$("#idMensajeTipoCuenta").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeTipoCuenta").addClass( "hidden" );
	$("#idMensajeTipoCuenta").css("display","block");
	
	$("#idMensajeNumeroCuenta").empty();
	$("#idMensajeNumeroCuenta").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeNumeroCuenta").addClass( "hidden" );
	$("#idMensajeNumeroCuenta").css("display","block");  
	
	$("#idMensajeNumeroCCI").empty();
	$("#idMensajeNumeroCCI").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeNumeroCCI").addClass( "hidden" );
	$("#idMensajeNumeroCCI").css("display","block");  
	
	$("#idMensajeResultadoAlias").empty();
	$("#idMensajeResultadoAlias").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoAlias").addClass( "hidden" );
	$("#idMensajeResultadoAlias").css("display","block");

	
	/*Remueves los errores si es que hubieran*/
	
	$("[id='idFormCuentaItem:idComboBanco']").removeClass( "claseErrorFormulario" );
	$("[id='idFormCuentaItem:idComboDivisa']").removeClass( "claseErrorFormulario");
	$("[id='idFormCuentaItem:idComboTipoCuenta']").removeClass( "claseErrorFormulario");
	$("[id='idFormCuentaItem:idTxtNumeroCuenta']").removeClass( "claseErrorFormulario");
	$("[id='idFormCuentaItem:idTxtNumeroCCI']").removeClass( "claseErrorFormulario");
	$("[id='idFormCuentaItem:idTxtAlias']").removeClass( "claseErrorFormulario");
	
	/*Captura valores*/
	
    var idComboBanco = $("[id='idFormCuentaItem:idComboBanco']").val();
    var idComboDivisa = $("[id='idFormCuentaItem:idComboDivisa']").val();
    var idComboTipoCuenta = $("[id='idFormCuentaItem:idComboTipoCuenta']").val();
    var idTxtNumeroCuenta = $("[id='idFormCuentaItem:idTxtNumeroCuenta']").val();
	var idTxtNumeroCCI = $("[id='idFormCuentaItem:idTxtNumeroCCI']").val();
    var idTxtAlias = $("[id='idFormCuentaItem:idTxtAlias']").val();
    
    if (idComboBanco == null || idComboBanco == '-1'){ 
    	$("#idMensajeComboBanco").text("El banco es obligarotio.");
    	$("#idMensajeComboBanco").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeComboBanco").removeClass( "hidden" );
    	$("#idMensajeComboBanco").fadeOut(6000);
    	$("[id='idFormCuentaItem:idComboBanco']").focus().select();
    	$("[id='idFormCuentaItem:idComboBanco']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idComboDivisa == null || idComboDivisa == '-1'){ 
    	$("#idMensajeComboDivisa").text("La divisa es obligarotia.");
    	$("#idMensajeComboDivisa").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeComboDivisa").removeClass( "hidden" );
    	$("#idMensajeComboDivisa").fadeOut(6000);
    	$("[id='idFormCuentaItem:idComboDivisa']").focus().select();
    	$("[id='idFormCuentaItem:idComboDivisa']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idComboTipoCuenta == null || idComboTipoCuenta == '-1'){ 
    	$("#idMensajeTipoCuenta").text("El tipo de cuenta es obligarotio.");
    	$("#idMensajeTipoCuenta").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeTipoCuenta").removeClass( "hidden" );
    	$("#idMensajeTipoCuenta").fadeOut(6000);
    	$("[id='idFormCuentaItem:idComboTipoCuenta']").focus().select();
    	$("[id='idFormCuentaItem:idComboTipoCuenta']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idTxtNumeroCuenta == null || idTxtNumeroCuenta == ''){
    	$("#idMensajeNumeroCuenta").text("El número de cuenta es obligatorio.");
    	$("#idMensajeNumeroCuenta").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeNumeroCuenta").removeClass( "hidden" );
    	$("#idMensajeNumeroCuenta").fadeOut(6000);
    	$("[id='idFormCuentaItem:idTxtNumeroCuenta']").focus();
    	$("[id='idFormCuentaItem:idTxtNumeroCuenta']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idTxtNumeroCCI == null || idTxtNumeroCCI == ''){
    	$("#idMensajeNumeroCCI").text("El CCI es obligatorio.");
    	$("#idMensajeNumeroCCI").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeNumeroCCI").removeClass( "hidden" );
    	$("#idMensajeNumeroCCI").fadeOut(6000);
    	$("[id='idFormCuentaItem:idTxtNumeroCCI']").focus();
    	$("[id='idFormCuentaItem:idTxtNumeroCCI']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idTxtAlias == null || idTxtAlias == ''){ 
    	$("#idMensajeResultadoAlias").text("El alias es obligatorio.");
    	$("#idMensajeResultadoAlias").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoAlias").removeClass( "hidden" );
    	$("#idMensajeResultadoAlias").fadeOut(6000);
    	$("[id='idFormCuentaItem:idTxtAlias']").focus();
    	$("[id='idFormCuentaItem:idTxtAlias']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else{
//    	metodoRemotoEnviarFormulario();
    	mostrarPopupConfirmaGuardarCuentaBancariaAdmin();
    }
}

function mostrarPopupConfirmaGuardarCuentaBancariaAdmin(){
	$('#idModalConfirmacionGuardaCuentaBancariaAdmin').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionGuardaCuentaBancariaAdmin').modal('show');

}

function operacionGuardarCuentaExitosa(){
	$('#idModalGuardarCuentaExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalGuardarCuentaExito').modal('show');
}

function procesoConError(){
	$('#idModalErrorSistema').modal({backdrop: 'static', keyboard: false})  
	$('#idModalErrorSistema').modal('show');
}
