function iniciarBotonesAcciones(){
	$(".botonDetalle").find("span").remove();
	$(".botonEditar").find("span").remove();
}

function funcionCargaEstiloActividad(){

	$("#idTablaPrincipal tr").each(function() {

		var item = $(this).find("td:eq(3)");
	       
	       if(item.text() == 'Activo') {
	    	   item.addClass("claseIndicadorActivo");
	       }else if(item.text() == 'Inactivo'){
	    	   item.addClass("claseIndicadorInactivo");
	       }
	       
	   var item = $(this).find("td:eq(4)");
	       
	       if(item.text() == 'Activo') {
	    	   item.addClass("claseIndicadorActivo");
	       }else if(item.text() == 'Inactivo'){
	    	   item.addClass("claseIndicadorInactivo");
	       }   
	       
	   var item = $(this).find("td:eq(5)");
	       
	       if(item.text() == 'Activo') {
	    	   item.addClass("claseIndicadorActivo");
	       }else if(item.text() == 'Inactivo'){
	    	   item.addClass("claseIndicadorInactivo");
	       } 
	       
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

/* Bloque para el Banco Item */


function enviar(event) {
	
//	$("[id='idFormBancoItem:idMensajeResultadoProceso']").css("display","none");
	
	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );
	
	$("#idMensajeCodigoBanco").empty();
	$("#idMensajeCodigoBanco").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeCodigoBanco").addClass( "hidden" );
	$("#idMensajeCodigoBanco").css("display","block");
	
	$("#idMensajeNombreBanco").empty();
	$("#idMensajeNombreBanco").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeNombreBanco").addClass( "hidden" );
	$("#idMensajeNombreBanco").css("display","block");
	
	$("#idMensajeIndVistaCliente").empty();
	$("#idMensajeIndVistaCliente").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeIndVistaCliente").addClass( "hidden" );
	$("#idMensajeIndVistaCliente").css("display","block");
	
	$("#idMensajeIndVistaAdmin").empty();
	$("#idMensajeIndVistaAdmin").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeIndVistaAdmin").addClass( "hidden" );
	$("#idMensajeIndVistaAdmin").css("display","block");
	
	$("#idMensajeIndCuentaNegocio").empty();
	$("#idMensajeIndCuentaNegocio").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeIndCuentaNegocio").addClass( "hidden" );
	$("#idMensajeIndCuentaNegocio").css("display","block");
	
	$("#idMensajeEstadoBanco").empty();
	$("#idMensajeEstadoBanco").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeEstadoBanco").addClass( "hidden" );
	$("#idMensajeEstadoBanco").css("display","block");
	
	/*Remueves los errores si es que hubieran*/
	
	$("[id='idFormBancoItem:idTxtCodigoBanco']").removeClass( "claseErrorFormulario" );
	$("[id='idFormBancoItem:idTxtNombreBanco']").removeClass( "claseErrorFormulario");
	$("[id='idFormBancoItem:idComboIndVistaCliente']").removeClass( "claseErrorFormulario");
	$("[id='idFormBancoItem:idComboIndVistaAdmin']").removeClass( "claseErrorFormulario");
	$("[id='idFormBancoItem:idComboIndCuentaNegocio']").removeClass( "claseErrorFormulario");
	$("[id='idFormBancoItem:idComboEstadoBanco']").removeClass( "claseErrorFormulario");
	
	/*Captura valores*/
	
    var idTxtCodigoBanco = $("[id='idFormBancoItem:idTxtCodigoBanco']").val();
    var idTxtNombreBanco = $("[id='idFormBancoItem:idTxtNombreBanco']").val();
    var idComboIndVistaCliente = $("[id='idFormBancoItem:idComboIndVistaCliente']").val();
    var idComboIndVistaAdmin = $("[id='idFormBancoItem:idComboIndVistaAdmin']").val();
    var idComboIndCuentaNegocio = $("[id='idFormBancoItem:idComboIndCuentaNegocio']").val();
    var idComboEstadoBanco = $("[id='idFormBancoItem:idComboEstadoBanco']").val();
    
    if (idTxtCodigoBanco == null || idTxtCodigoBanco == ''){
    	$("#idMensajeCodigoBanco").text("El código de banco es obligatorio.");
    	$("#idMensajeCodigoBanco").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeCodigoBanco").removeClass( "hidden" );
    	$("#idMensajeCodigoBanco").fadeOut(6000);
    	$("[id='idFormBancoItem:idTxtCodigoBanco']").focus();
    	$("[id='idFormBancoItem:idTxtCodigoBanco']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idTxtNombreBanco == null || idTxtNombreBanco == ''){
    	$("#idMensajeNombreBanco").text("El nombre de banco es obligatorio.");
    	$("#idMensajeNombreBanco").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeNombreBanco").removeClass( "hidden" );
    	$("#idMensajeNombreBanco").fadeOut(6000);
    	$("[id='idFormBancoItem:idTxtNombreBanco']").focus();
    	$("[id='idFormBancoItem:idTxtNombreBanco']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idComboIndVistaCliente == null || idComboIndVistaCliente == '-1'){ 
    	$("#idMensajeIndVistaCliente").text("El indicador de vista cliente es obligatorio.");
    	$("#idMensajeIndVistaCliente").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeIndVistaCliente").removeClass( "hidden" );
    	$("#idMensajeIndVistaCliente").fadeOut(6000);
    	$("[id='idFormBancoItem:idComboIndVistaCliente']").focus().select();
    	$("[id='idFormBancoItem:idComboIndVistaCliente']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idComboIndVistaAdmin == null || idComboIndVistaAdmin == '-1'){ 
    	$("#idMensajeIndVistaAdmin").text("El indicador de vista administración es obligatorio.");
    	$("#idMensajeIndVistaAdmin").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeIndVistaAdmin").removeClass( "hidden" );
    	$("#idMensajeIndVistaAdmin").fadeOut(6000);
    	$("[id='idFormBancoItem:idComboIndVistaAdmin']").focus().select();
    	$("[id='idFormBancoItem:idComboIndVistaAdmin']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idComboIndCuentaNegocio == null || idComboIndCuentaNegocio == '-1'){ 
    	$("#idMensajeIndCuentaNegocio").text("El indicador de cuenta negocio es obligatorio.");
    	$("#idMensajeIndCuentaNegocio").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeIndCuentaNegocio").removeClass( "hidden" );
    	$("#idMensajeIndCuentaNegocio").fadeOut(6000);
    	$("[id='idFormBancoItem:idComboIndCuentaNegocio']").focus().select();
    	$("[id='idFormBancoItem:idComboIndCuentaNegocio']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idComboEstadoBanco == null || idComboEstadoBanco == '-1'){ 
    	$("#idMensajeEstadoBanco").text("El indicador estado de registro obligatorio.");
    	$("#idMensajeEstadoBanco").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeEstadoBanco").removeClass( "hidden" );
    	$("#idMensajeEstadoBanco").fadeOut(6000);
    	$("[id='idFormBancoItem:idComboEstadoBanco']").focus().select();
    	$("[id='idFormBancoItem:idComboEstadoBanco']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else{
    	mostrarPopupConfirmaGuardarBanco();
    }
}

function mostrarPopupConfirmaGuardarBanco(){
	$('#idModalConfirmacionGuardaBanco').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionGuardaBanco').modal('show');

}

function operacionGuardarBancoExitosa(){
	$('#idModalGuardarBancoExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalGuardarBancoExito').modal('show');
}


