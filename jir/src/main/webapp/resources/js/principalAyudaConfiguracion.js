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
	
//	$("[id='idFormPreguntaItem:idMensajeResultadoProceso']").css("display","none");
	
	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );
	
	$("#idMensajeTipoPregunta").empty();
	$("#idMensajeTipoPregunta").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeTipoPregunta").addClass( "hidden" );
	$("#idMensajeTipoPregunta").css("display","block");	
	
	$("#idMensajePosicion").empty();
	$("#idMensajePosicion").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajePosicion").addClass( "hidden" );
	$("#idMensajePosicion").css("display","block");
	
	$("#idMensajePregunta").empty();
	$("#idMensajePregunta").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajePregunta").addClass( "hidden" );
	$("#idMensajePregunta").css("display","block");
	
	$("#idMensajeRespuesta").empty();
	$("#idMensajeRespuesta").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeRespuesta").addClass( "hidden" );
	$("#idMensajeRespuesta").css("display","block");
	
	
	/*Remueves los errores si es que hubieran*/
	$("[id='idFormPreguntaItem:idComboTipoPregunta']").removeClass( "claseErrorFormulario" );
	$("[id='idFormPreguntaItem:idTxtPosicion']").removeClass( "claseErrorFormulario" );
	$("[id='idFormPreguntaItem:idTxtPregunta']").removeClass( "claseErrorFormulario" );
	$("[id='idFormPreguntaItem:idTxtRespuesta_editor']").removeClass( "claseErrorFormulario" );
	
	/*Captura valores*/
	var idComboTipoPregunta = $("[id='idFormPreguntaItem:idComboTipoPregunta']").val();
    var idTxtPosicion = $("[id='idFormPreguntaItem:idTxtPosicion']").val();
    var idTxtPregunta = $("[id='idFormPreguntaItem:idTxtPregunta']").val();
    var idTxtRespuesta = $("[id='idFormPreguntaItem:idTxtRespuesta_editor']").val();
    
	if (idComboTipoPregunta !== undefined && (idComboTipoPregunta == null || idComboTipoPregunta == '-1') ){ 
    	$("#idMensajeTipoPregunta").text("Debes seleccionar un tipo de pregunta.");
    	$("#idMensajeTipoPregunta").addClass( "alert alert-danger claseMensajeResultado")
        $("#idMensajeTipoPregunta").removeClass( "hidden" );
    	$("#idMensajeTipoPregunta").fadeOut(6000);
    	$("[id='idFormPreguntaItem:idComboTipoPregunta']").focus().select();
    	$("[id='idFormPreguntaItem:idComboTipoPregunta']").addClass( "claseErrorFormulario");
    	event.preventDefault();
    }else if (idTxtPosicion == null || idTxtPosicion == ''){
    	$("#idMensajePosicion").text("La posición es obligatoria.");
    	$("#idMensajePosicion").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajePosicion").removeClass( "hidden" );
    	$("#idMensajePosicion").fadeOut(6000);
    	$("[id='idFormPreguntaItem:idTxtPosicion']").focus();
    	$("[id='idFormPreguntaItem:idTxtPosicion']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idTxtPregunta == null || idTxtPregunta == ''){
    	$("#idMensajePregunta").text("La pregunta es obligatoria.");
    	$("#idMensajePregunta").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajePregunta").removeClass( "hidden" );
    	$("#idMensajePregunta").fadeOut(6000);
    	$("[id='idFormPreguntaItem:idTxtPregunta']").focus();
    	$("[id='idFormPreguntaItem:idTxtPregunta']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else{
    	mostrarPopupConfirmaGuardarPregunta();
    }
}

function mostrarPopupConfirmaGuardarPregunta(){
	$('#idModalConfirmacionGuardaPregunta').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionGuardaPregunta').modal('show');

}

function operacionGuardarPreguntaExitosa(){
	$('#idModalGuardarPreguntaExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalGuardarPreguntaExito').modal('show');
}

function procesoConError(){
	$('#idModalErrorSistema').modal({backdrop: 'static', keyboard: false})  
	$('#idModalErrorSistema').modal('show');
}
