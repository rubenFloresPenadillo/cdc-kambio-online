function iniciarBotonesAcciones(){
	$(".botonDetalle").find("span").remove();
	$(".botonEditar").find("span").remove();
}


function funcionCargaEstiloActividad(){

	$("#idTablaPrincipal tr").each(function() {

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

/* Bloque para el la divisa Item */


function enviar(event) {
	
//	$("[id='idFormDivisaItem:idMensajeResultadoProceso']").css("display","none");
	
	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );
	
	$("#idMensajeCodigoISO").empty();
	$("#idMensajeCodigoISO").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeCodigoISO").addClass( "hidden" );
	$("#idMensajeCodigoISO").css("display","block");
	
	$("#idMensajeNombreSingular").empty();
	$("#idMensajeNombreSingular").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeNombreSingular").addClass( "hidden" );
	$("#idMensajeNombreSingular").css("display","block");
	
	$("#idMensajeNombrePural").empty();
	$("#idMensajeNombrePural").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeNombrePural").addClass( "hidden" );
	$("#idMensajeNombrePural").css("display","block");
	
	$("#idMensajeSimbolo").empty();
	$("#idMensajeSimbolo").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeSimbolo").addClass( "hidden" );
	$("#idMensajeSimbolo").css("display","block");
	
	$("#idMensajeIndAplicaCuenBanca").empty();
	$("#idMensajeIndAplicaCuenBanca").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeIndAplicaCuenBanca").addClass( "hidden" );
	$("#idMensajeIndAplicaCuenBanca").css("display","block");
	
	$("#idMensajeEstadoDivisa").empty();
	$("#idMensajeEstadoDivisa").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeEstadoDivisa").addClass( "hidden" );
	$("#idMensajeEstadoDivisa").css("display","block");
	
	/*Remueves los errores si es que hubieran*/
	
	$("[id='idFormDivisaItem:idTxtCodigoIso']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDivisaItem:idTxtNombreSingular']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDivisaItem:idTxtNombrePlural']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDivisaItem:idTxtSimbolo']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDivisaItem:idComboAplicaCuenBanca']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDivisaItem:idComboEstadoDivisa']").removeClass( "claseErrorFormulario" );
	
	/*Captura valores*/
	
    var idTxtCodigoIso = $("[id='idFormDivisaItem:idTxtCodigoIso']").val();
    var idTxtNombreSingular = $("[id='idFormDivisaItem:idTxtNombreSingular']").val();
    var idTxtNombrePlural = $("[id='idFormDivisaItem:idTxtNombrePlural']").val();
    var idTxtSimbolo = $("[id='idFormDivisaItem:idTxtSimbolo']").val();
    var idComboAplicaCuenBanca = $("[id='idFormDivisaItem:idComboAplicaCuenBanca']").val();
    var idComboEstadoDivisa = $("[id='idFormDivisaItem:idComboEstadoDivisa']").val();
    
    if (idTxtCodigoIso == null || idTxtCodigoIso == ''){
    	$("#idMensajeCodigoISO").text("El codigo ISO es obligatorio.");
    	$("#idMensajeCodigoISO").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeCodigoISO").removeClass( "hidden" );
    	$("#idMensajeCodigoISO").fadeOut(6000);
    	$("[id='idFormDivisaItem:idTxtCodigoIso']").focus();
    	$("[id='idFormDivisaItem:idTxtCodigoIso']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idTxtNombreSingular == null || idTxtNombreSingular == ''){
    	$("#idMensajeNombreSingular").text("El nombre singular es obligatorio.");
    	$("#idMensajeNombreSingular").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeNombreSingular").removeClass( "hidden" );
    	$("#idMensajeNombreSingular").fadeOut(6000);
    	$("[id='idFormDivisaItem:idTxtNombreSingular']").focus();
    	$("[id='idFormDivisaItem:idTxtNombreSingular']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idTxtNombrePlural == null || idTxtNombrePlural == ''){
    	$("#idMensajeNombrePural").text("El nombre plural es obligatorio.");
    	$("#idMensajeNombrePural").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeNombrePural").removeClass( "hidden" );
    	$("#idMensajeNombrePural").fadeOut(6000);
    	$("[id='idFormDivisaItem:idTxtNombrePlural']").focus();
    	$("[id='idFormDivisaItem:idTxtNombrePlural']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idTxtSimbolo == null || idTxtSimbolo == ''){
    	$("#idMensajeSimbolo").text("El símbolo es obligatorio.");
    	$("#idMensajeSimbolo").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeSimbolo").removeClass( "hidden" );
    	$("#idMensajeSimbolo").fadeOut(6000);
    	$("[id='idFormDivisaItem:idTxtSimbolo']").focus();
    	$("[id='idFormDivisaItem:idTxtSimbolo']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idComboAplicaCuenBanca == null || idComboAplicaCuenBanca == '-1'){ 
    	$("#idMensajeIndAplicaCuenBanca").text("El indicador aplica cuenta bancaria es obligatorio.");
    	$("#idMensajeIndAplicaCuenBanca").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeIndAplicaCuenBanca").removeClass( "hidden" );
    	$("#idMensajeIndAplicaCuenBanca").fadeOut(6000);
    	$("[id='idFormDivisaItem:idComboAplicaCuenBanca']").focus().select();
    	$("[id='idFormDivisaItem:idComboAplicaCuenBanca']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idComboEstadoDivisa == null || idComboEstadoDivisa == '-1'){ 
    	$("#idMensajeEstadoDivisa").text("El indicador estado de registro obligatorio.");
    	$("#idMensajeEstadoDivisa").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeEstadoDivisa").removeClass( "hidden" );
    	$("#idMensajeEstadoDivisa").fadeOut(6000);
    	$("[id='idFormDivisaItem:idComboEstadoDivisa']").focus().select();
    	$("[id='idFormDivisaItem:idComboEstadoDivisa']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else{
//    	metodoRemotoEnviarFormulario();
    	mostrarPopupConfirmaGuardarDivisa();
    }
}

function mostrarPopupConfirmaGuardarDivisa(){
	$('#idModalConfirmacionGuardaDivisa').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionGuardaDivisa').modal('show');

}

function operacionGuardarDivisaExitosa(){
	$('#idModalGuardarDivisaExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalGuardarDivisaExito').modal('show');
}

function procesoConError(){
	$('#idModalErrorSistema').modal({backdrop: 'static', keyboard: false})  
	$('#idModalErrorSistema').modal('show');
}
