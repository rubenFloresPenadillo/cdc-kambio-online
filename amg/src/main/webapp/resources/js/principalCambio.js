function iniciarBotonesAcciones(){
	$(".botonDetalle").find("span").remove();
	$(".botonEditar").find("span").remove();
}


function funcionCargaEstiloActividad(){
//	alert('entro funcionCargaEstiloActividad');
	$("#idTablaPrincipal tr").each(function() {

		var item = $(this).find("td:eq(5)");
	       
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
	
//	$("[id='idFormTipoCambioItem:idMensajeResultadoProceso']").css("display","none");
	
	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );
	
	$("#idMensajeComboDivisa").empty();
	$("#idMensajeComboDivisa").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeComboDivisa").addClass( "hidden" );
	$("#idMensajeComboDivisa").css("display","block");
	
	$("#idMensajeComboDivisaCambio").empty();
	$("#idMensajeComboDivisaCambio").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeComboDivisaCambio").addClass( "hidden" );
	$("#idMensajeComboDivisaCambio").css("display","block");
	
	$("#idMensajeValorCompra").empty();
	$("#idMensajeValorCompra").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeValorCompra").addClass( "hidden" );
	$("#idMensajeValorCompra").css("display","block");
	
	$("#idMensajeValorVenta").empty();
	$("#idMensajeValorVenta").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeValorVenta").addClass( "hidden" );
	$("#idMensajeValorVenta").css("display","block");
	
	$("#idMensajeEstadoTipoCambio").empty();
	$("#idMensajeEstadoTipoCambio").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeEstadoTipoCambio").addClass( "hidden" );
	$("#idMensajeEstadoTipoCambio").css("display","block");
	
	
	
	/*Remueves los errores si es que hubieran*/
	
	$("[id='idFormTipoCambioItem:idComboDivisa']").removeClass( "claseErrorFormulario" );
	$("[id='idFormTipoCambioItem:idComboDivisaCambio']").removeClass( "claseErrorFormulario");
	$("[id='idFormTipoCambioItem:idTxtValorCompra']").removeClass( "claseErrorFormulario");
	$("[id='idFormTipoCambioItem:idTxtValorVenta']").removeClass( "claseErrorFormulario");
	$("[id='idFormTipoCambioItem:idComboEstadoTipoCambio']").removeClass( "claseErrorFormulario");
	
	/*Captura valores*/
	
    var idComboDivisa = $("[id='idFormTipoCambioItem:idComboDivisa']").val();
    var idComboDivisaCambio = $("[id='idFormTipoCambioItem:idComboDivisaCambio']").val();
    var idTxtValorCompra = $("[id='idFormTipoCambioItem:idTxtValorCompra']").val();
    var idTxtValorVenta = $("[id='idFormTipoCambioItem:idTxtValorVenta']").val();
    var idComboEstadoTipoCambio = $("[id='idFormTipoCambioItem:idComboEstadoTipoCambio']").val();
    
    if (idComboDivisa == null || idComboDivisa == '-1'){ 
    	$("#idMensajeComboDivisa").text("La divisa es obligarotia.");
    	$("#idMensajeComboDivisa").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeComboDivisa").removeClass( "hidden" );
    	$("#idMensajeComboDivisa").fadeOut(6000);
    	$("[id='idFormTipoCambioItem:idComboDivisa']").focus().select();
    	$("[id='idFormTipoCambioItem:idComboDivisa']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idComboDivisaCambio == null || idComboDivisaCambio == '-1'){ 
    	$("#idMensajeComboDivisaCambio").text("La divisa de cambio es obligarotia.");
    	$("#idMensajeComboDivisaCambio").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeComboDivisaCambio").removeClass( "hidden" );
    	$("#idMensajeComboDivisaCambio").fadeOut(6000);
    	$("[id='idFormTipoCambioItem:idComboDivisaCambio']").focus().select();
    	$("[id='idFormTipoCambioItem:idComboDivisaCambio']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idTxtValorCompra == null || idTxtValorCompra == ''){
    	$("#idMensajeValorCompra").text("El valor de compra es obligatorio.");
    	$("#idMensajeValorCompra").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeValorCompra").removeClass( "hidden" );
    	$("#idMensajeValorCompra").fadeOut(6000);
    	$("[id='idFormTipoCambioItem:idTxtValorCompra']").focus();
    	$("[id='idFormTipoCambioItem:idTxtValorCompra']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idTxtValorVenta == null || idTxtValorVenta == ''){
    	$("#idMensajeValorVenta").text("El valor de venta es obligatorio.");
    	$("#idMensajeValorVenta").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeValorVenta").removeClass( "hidden" );
    	$("#idMensajeValorVenta").fadeOut(6000);
    	$("[id='idFormTipoCambioItem:idTxtValorVenta']").focus();
    	$("[id='idFormTipoCambioItem:idTxtValorVenta']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idComboEstadoTipoCambio == null || idComboEstadoTipoCambio == '-1'){ 
    	$("#idMensajeEstadoTipoCambio").text("El estado del tipo de cambio es obligatorio.");
    	$("#idMensajeEstadoTipoCambio").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeEstadoTipoCambio").removeClass( "hidden" );
    	$("#idMensajeEstadoTipoCambio").fadeOut(6000);
    	$("[id='idFormTipoCambioItem:idComboEstadoTipoCambio']").focus().select();
    	$("[id='idFormTipoCambioItem:idComboEstadoTipoCambio']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else{
//    	metodoRemotoEnviarFormulario();
    	mostrarPopupConfirmaGuardarTipoCambio();
    }
}

function mostrarPopupConfirmaGuardarTipoCambio(){
	$('#idModalConfirmacionGuardaTipoCambio').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionGuardaTipoCambio').modal('show');

}

function operacionGuardarTipoCambioExitosa(){
	$('#idModalGuardarTipoCambioExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalGuardarTipoCambioExito').modal('show');
}

function procesoConError(){
	$('#idModalErrorSistema').modal({backdrop: 'static', keyboard: false})  
	$('#idModalErrorSistema').modal('show');
}
