function iniciarBotonesAcciones(){
	$(".botonDetalle").find("span").remove();
	$(".botonEditar").find("span").remove();
}


function funcionCargaEstiloActividad(){

	$("#idTablaPrincipal tr").each(function() {

		var item = $(this).find("td:eq(6)");
	       
	       if(item.text() == 'Activo') {
	    	   item.addClass("claseIndicadorActivo");
	       }else if(item.text() == 'Inactivo'){
	    	   item.addClass("claseIndicadorInactivo");
	       }

	});
	
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
	funcionCargaEstiloActividad();
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
	
//	$("[id='idFormCuentaBancariaAdminItem:idMensajeResultadoProceso']").css("display","none");
	
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

	
	/*Remueves los errores si es que hubieran*/
	
	$("[id='idFormCuentaBancariaAdminItem:idComboBanco']").removeClass( "claseErrorFormulario" );
	$("[id='idFormCuentaBancariaAdminItem:idComboDivisa']").removeClass( "claseErrorFormulario");
	$("[id='idFormCuentaBancariaAdminItem:idComboTipoCuenta']").removeClass( "claseErrorFormulario");
	$("[id='idFormCuentaBancariaAdminItem:idTxtNumeroCuenta']").removeClass( "claseErrorFormulario");
	
	/*Captura valores*/
	
    var idComboBanco = $("[id='idFormCuentaBancariaAdminItem:idComboBanco']").val();
    var idComboDivisa = $("[id='idFormCuentaBancariaAdminItem:idComboDivisa']").val();
    var idComboTipoCuenta = $("[id='idFormCuentaBancariaAdminItem:idComboTipoCuenta']").val();
    var idTxtNumeroCuenta = $("[id='idFormCuentaBancariaAdminItem:idTxtNumeroCuenta']").val();
    
    if (idComboBanco == null || idComboBanco == '-1'){ 
    	$("#idMensajeComboBanco").text("El banco es obligarotio.");
    	$("#idMensajeComboBanco").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeComboBanco").removeClass( "hidden" );
    	$("#idMensajeComboBanco").fadeOut(6000);
    	$("[id='idFormCuentaBancariaAdminItem:idComboBanco']").focus().select();
    	$("[id='idFormCuentaBancariaAdminItem:idComboBanco']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idComboDivisa == null || idComboDivisa == '-1'){ 
    	$("#idMensajeComboDivisa").text("La divisa es obligarotia.");
    	$("#idMensajeComboDivisa").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeComboDivisa").removeClass( "hidden" );
    	$("#idMensajeComboDivisa").fadeOut(6000);
    	$("[id='idFormCuentaBancariaAdminItem:idComboDivisa']").focus().select();
    	$("[id='idFormCuentaBancariaAdminItem:idComboDivisa']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idComboTipoCuenta == null || idComboTipoCuenta == '-1'){ 
    	$("#idMensajeTipoCuenta").text("El tipo de cuenta es obligarotio.");
    	$("#idMensajeTipoCuenta").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeTipoCuenta").removeClass( "hidden" );
    	$("#idMensajeTipoCuenta").fadeOut(6000);
    	$("[id='idFormCuentaBancariaAdminItem:idComboTipoCuenta']").focus().select();
    	$("[id='idFormCuentaBancariaAdminItem:idComboTipoCuenta']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idTxtNumeroCuenta == null || idTxtNumeroCuenta == ''){
    	$("#idMensajeNumeroCuenta").text("El número de cuenta es obligatorio.");
    	$("#idMensajeNumeroCuenta").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeNumeroCuenta").removeClass( "hidden" );
    	$("#idMensajeNumeroCuenta").fadeOut(6000);
    	$("[id='idFormCuentaBancariaAdminItem:idTxtNumeroCuenta']").focus();
    	$("[id='idFormCuentaBancariaAdminItem:idTxtNumeroCuenta']").addClass( "claseErrorFormulario" );
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

function operacionGuardarCuentaBancariaAdminExitosa(){
	$('#idModalGuardarCuentaBancariaAdminExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalGuardarCuentaBancariaAdminExito').modal('show');
}

function procesoConError(){
	$('#idModalErrorSistema').modal({backdrop: 'static', keyboard: false})  
	$('#idModalErrorSistema').modal('show');
}
