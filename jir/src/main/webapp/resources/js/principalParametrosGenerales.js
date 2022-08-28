function iniciarBotonesAcciones(){
	$(".botonDetalle").find("span").remove();
	$(".botonEditar").find("span").remove();
}


function funcionCargaEstiloActividad(){

	$("#idTablaPrincipal tr").each(function() {

		var item = $(this).find("td:eq(4)");
	       
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

/* Bloque para el parametro tem */


function enviar(event) {
	
//	$("[id='idFormParametroGeneralItem:idMensajeResultadoProceso']").css("display","none");
	
	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );
	
	$("#idMensajeNombreParametro").empty();
	$("#idMensajeNombreParametro").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeNombreParametro").addClass( "hidden" );
	$("#idMensajeNombreParametro").css("display","block");
	
	$("#idMensajeValorParametro").empty();
	$("#idMensajeValorParametro").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeValorParametro").addClass( "hidden" );
	$("#idMensajeValorParametro").css("display","block");
	
	$("#idMensajeEstadoParametroGeneral").empty();
	$("#idMensajeEstadoParametroGeneral").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeEstadoParametroGeneral").addClass( "hidden" );
	$("#idMensajeEstadoParametroGeneral").css("display","block");
	
	$("#idMensajeDescValorParametro").empty();
	$("#idMensajeDescValorParametro").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeDescValorParametro").addClass( "hidden" );
	$("#idMensajeDescValorParametro").css("display","block");
	
	/*Remueves los errores si es que hubieran*/
	
	$("[id='idFormParametroGeneralItem:idTxtNombreParametro']").removeClass( "claseErrorFormulario" );
	$("[id='idFormParametroGeneralItem:idTxtValorParametro']").removeClass( "claseErrorFormulario" );
	$("[id='idFormParametroGeneralItem:idComboEstadoParametroGeneral']").removeClass( "claseErrorFormulario" );
	$("[id='idFormParametroGeneralItem:idTxtDescValorParametro']").removeClass( "claseErrorFormulario" );
	
	
	/*Captura valores*/
	
    var idTxtNombreParametro = $("[id='idFormParametroGeneralItem:idTxtNombreParametro']").val();
    var idTxtValorParametro = $("[id='idFormParametroGeneralItem:idTxtValorParametro']").val();
    var idComboEstadoParametroGeneral = $("[id='idFormParametroGeneralItem:idComboEstadoParametroGeneral']").val();
    var idTxtDescValorParametro = $("[id='idFormParametroGeneralItem:idTxtDescValorParametro']").val();
    
    if (idTxtNombreParametro == null || idTxtNombreParametro == ''){
    	$("#idMensajeNombreParametro").text("El nombre del parámetro es obligatorio.");
    	$("#idMensajeNombreParametro").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeNombreParametro").removeClass( "hidden" );
    	$("#idMensajeNombreParametro").fadeOut(6000);
    	$("[id='idFormParametroGeneralItem:idTxtNombreParametro']").focus();
    	$("[id='idFormParametroGeneralItem:idTxtNombreParametro']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idTxtValorParametro == null || idTxtValorParametro == ''){
    	$("#idMensajeValorParametro").text("El valor del parámetro es obligatorio.");
    	$("#idMensajeValorParametro").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeValorParametro").removeClass( "hidden" );
    	$("#idMensajeValorParametro").fadeOut(6000);
    	$("[id='idFormParametroGeneralItem:idTxtValorParametro']").focus();
    	$("[id='idFormParametroGeneralItem:idTxtValorParametro']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idComboEstadoParametroGeneral == null || idComboEstadoParametroGeneral == '-1'){ 
    	$("#idMensajeEstadoParametroGeneral").text("El estado del parámetro es obligatorio.");
    	$("#idMensajeEstadoParametroGeneral").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeEstadoParametroGeneral").removeClass( "hidden" );
    	$("#idMensajeEstadoParametroGeneral").fadeOut(6000);
    	$("[id='idFormParametroGeneralItem:idComboEstadoParametroGeneral']").focus().select();
    	$("[id='idFormParametroGeneralItem:idComboEstadoParametroGeneral']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idTxtDescValorParametro == null || idTxtDescValorParametro == '-1'){ 
    	$("#idMensajeDescValorParametro").text("El indicador estado de registro obligatorio.");
    	$("#idMensajeDescValorParametro").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeDescValorParametro").removeClass( "hidden" );
    	$("#idMensajeDescValorParametro").fadeOut(6000);
    	$("[id='idFormParametroGeneralItem:idTxtDescValorParametro']").focus().select();
    	$("[id='idFormParametroGeneralItem:idTxtDescValorParametro']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else{
    	mostrarPopupConfirmaGuardarParametroGeneral();
    }
}

function mostrarPopupConfirmaGuardarParametroGeneral(){
	$('#idModalConfirmacionGuardaParametroGeneral').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionGuardaParametroGeneral').modal('show');

}

function operacionGuardarParametroGeneralExitosa(){
	$('#idModalGuardarParametroGeneralExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalGuardarParametroGeneralExito').modal('show');
}

function procesoConError(){
	$('#idModalErrorSistema').modal({backdrop: 'static', keyboard: false})  
	$('#idModalErrorSistema').modal('show');
}
