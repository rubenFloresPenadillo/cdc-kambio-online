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
	
//	$("[id='idFormInformacionEmpresa:idMensajeResultadoProceso']").css("display","none");
	
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
	
	$("[id='idFormInformacionEmpresa:idComboDivisa']").removeClass( "claseErrorFormulario" );
	$("[id='idFormInformacionEmpresa:idComboDivisaCambio']").removeClass( "claseErrorFormulario");
	$("[id='idFormInformacionEmpresa:idTxtValorCompra']").removeClass( "claseErrorFormulario");
	$("[id='idFormInformacionEmpresa:idTxtValorVenta']").removeClass( "claseErrorFormulario");
	$("[id='idFormInformacionEmpresa:idComboEstadoTipoCambio']").removeClass( "claseErrorFormulario");
	
	/*Captura valores*/
	
    var idComboDivisa = $("[id='idFormInformacionEmpresa:idComboDivisa']").val();
    var idComboDivisaCambio = $("[id='idFormInformacionEmpresa:idComboDivisaCambio']").val();
    var idTxtValorCompra = $("[id='idFormInformacionEmpresa:idTxtValorCompra']").val();
    var idTxtValorVenta = $("[id='idFormInformacionEmpresa:idTxtValorVenta']").val();
    var idComboEstadoTipoCambio = $("[id='idFormInformacionEmpresa:idComboEstadoTipoCambio']").val();
    
    if (idComboDivisa == null || idComboDivisa == '-1'){ 
    	$("#idMensajeComboDivisa").text("La divisa es obligarotia.");
    	$("#idMensajeComboDivisa").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeComboDivisa").removeClass( "hidden" );
    	$("#idMensajeComboDivisa").fadeOut(6000);
    	$("[id='idFormInformacionEmpresa:idComboDivisa']").focus().select();
    	$("[id='idFormInformacionEmpresa:idComboDivisa']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idComboDivisaCambio == null || idComboDivisaCambio == '-1'){ 
    	$("#idMensajeComboDivisaCambio").text("La divisa de cambio es obligarotia.");
    	$("#idMensajeComboDivisaCambio").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeComboDivisaCambio").removeClass( "hidden" );
    	$("#idMensajeComboDivisaCambio").fadeOut(6000);
    	$("[id='idFormInformacionEmpresa:idComboDivisaCambio']").focus().select();
    	$("[id='idFormInformacionEmpresa:idComboDivisaCambio']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idTxtValorCompra == null || idTxtValorCompra == ''){
    	$("#idMensajeValorCompra").text("El valor de compra es obligatorio.");
    	$("#idMensajeValorCompra").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeValorCompra").removeClass( "hidden" );
    	$("#idMensajeValorCompra").fadeOut(6000);
    	$("[id='idFormInformacionEmpresa:idTxtValorCompra']").focus();
    	$("[id='idFormInformacionEmpresa:idTxtValorCompra']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idTxtValorVenta == null || idTxtValorVenta == ''){
    	$("#idMensajeValorVenta").text("El valor de venta es obligatorio.");
    	$("#idMensajeValorVenta").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeValorVenta").removeClass( "hidden" );
    	$("#idMensajeValorVenta").fadeOut(6000);
    	$("[id='idFormInformacionEmpresa:idTxtValorVenta']").focus();
    	$("[id='idFormInformacionEmpresa:idTxtValorVenta']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idComboEstadoTipoCambio == null || idComboEstadoTipoCambio == '-1'){ 
    	$("#idMensajeEstadoTipoCambio").text("El estado del tipo de cambio es obligatorio.");
    	$("#idMensajeEstadoTipoCambio").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeEstadoTipoCambio").removeClass( "hidden" );
    	$("#idMensajeEstadoTipoCambio").fadeOut(6000);
    	$("[id='idFormInformacionEmpresa:idComboEstadoTipoCambio']").focus().select();
    	$("[id='idFormInformacionEmpresa:idComboEstadoTipoCambio']").addClass( "claseErrorFormulario" );
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
