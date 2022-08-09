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
//	$(".ui-widget-content").text("¡Suelte aquí su imagen .png o .jpg!");  
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
	
//	$("[id='idFormBlogGeneralItem:idMensajeResultadoProceso']").css("display","none");
	
	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );
	
	$("#idMensajeTitulo").empty();
	$("#idMensajeTitulo").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeTitulo").addClass( "hidden" );
	$("#idMensajeTitulo").css("display","block");
	
	$("#idMensajeCategoria").empty();
	$("#idMensajeCategoria").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeCategoria").addClass( "hidden" );
	$("#idMensajeCategoria").css("display","block");
	
	$("#idMensaje").empty();
	$("#idMensaje").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensaje").addClass( "hidden" );
	$("#idMensaje").css("display","block");
	
	/*Remueves los errores si es que hubieran*/
	
	$("[id='idFormBlogGeneralItem:idTxtTitulo']").removeClass( "claseErrorFormulario" );
	$("[id='idFormBlogGeneralItem:idTxtCategoria']").removeClass( "claseErrorFormulario" );	
	$("[id='idFormBlogGeneralItem:idTxtContenido']").removeClass( "claseErrorFormulario");
	
	/*Captura valores*/
	
    var idTxtTitulo = $("[id='idFormBlogGeneralItem:idTxtTitulo']").val();
    var idTxtCategoria = $("[id='idFormBlogGeneralItem:idTxtCategoria']").val();
    var idTxtContenido = $("[id='idFormBlogGeneralItem:idTxtContenido']").val();
    
    
    if (!idTxtTitulo.trim()){
    	$("#idMensajeTitulo").text("El título es obligatorio.");
    	$("#idMensajeTitulo").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeTitulo").removeClass( "hidden" );
    	$("#idMensajeTitulo").fadeOut(6000);
    	$("[id='idFormBlogGeneralItem:idTxtTitulo']").focus();
    	$("[id='idFormBlogGeneralItem:idTxtTitulo']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (!idTxtCategoria.trim()){
    	$("#idMensajeCategoria").text("La categoría es obligatoria.");
    	$("#idMensajeCategoria").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeCategoria").removeClass( "hidden" );
    	$("#idMensajeCategoria").fadeOut(6000);
    	$("[id='idFormBlogGeneralItem:idTxtCategoria']").focus();
    	$("[id='idFormBlogGeneralItem:idTxtCategoria']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (!idTxtContenido.trim()){
    	$("#idMensaje").text("El contenido es obligatorio.");
    	$("#idMensaje").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensaje").removeClass( "hidden" );
    	$("#idMensaje").fadeOut(6000);
    	$("[id='idFormBlogGeneralItem:idTxtContenido']").focus();
    	$("[id='idFormBlogGeneralItem:idTxtContenido']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else{
    	mostrarPopupConfirmaGuardarEntrada();
    }
}

function mostrarPopupConfirmaGuardarEntrada(){
	$('#idModalConfirmacionGuardaEntrada').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionGuardaEntrada').modal('show');

}

function operacionGuardarEntradaExitosa(){
	$('#idModalGuardarEntradaExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalGuardarEntradaExito').modal('show');
}


