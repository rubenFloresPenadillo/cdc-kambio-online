function iniciarBotonesAcciones(){
	$(".botonDetalle").find("span").remove();
	$(".botonEditar").find("span").remove();
}

function funcionCargaEstiloActividad(){

	$("#idTablaPrincipal tr").each(function() {

		var item = $(this).find("td:eq(9)");
	       
	       if(item.text() == 'Activo') {
	    	   item.addClass("claseIndicadorActivo");
	       }else if(item.text() == 'Inactivo'){
	    	   item.addClass("claseIndicadorInactivo");
	       }
	       
	   var item = $(this).find("td:eq(10)");
	       
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

function cargarEstilosBotonesPaginando() {
	$(".paginate_button ").on('click', function(event){
		funcionCargaEstiloActividad();
	});
}

$(document).ready(function() {
	iniciarTablaYBotones();
	cargarEstilosBotonesPaginando();
	$(".botonCerrarSesion").find("span").remove();
	// Inicia el toggle de Activo o Inactivo
	var idIndicadorActivo = document.getElementById("idFormCuponItem:idIndicadorActivo").value;
	if (idIndicadorActivo == '1') {
		$('#idIndActivo').bootstrapToggle('on');
	}else{
		$('#idIndActivo').bootstrapToggle('off');
	}
	
	var idIndicadorReu = document.getElementById("idFormCuponItem:idIndicadorReu").value;
	if (idIndicadorReu == '1') {
		$('#idReutClie').bootstrapToggle('on');
	}else{
		$('#idReutClie').bootstrapToggle('off');
	}
	
});


$(function() {
	
    $('#idIndActivo').change(function() {
    	var idIndicadorActivo = document.getElementById("idFormCuponItem:idIndicadorActivo");


    	if($(this).prop('checked')){
    		idIndicadorActivo.value = "1";
    	}else{
    		idIndicadorActivo.value = "0";
    	}
    	
		metodoRemotoHabilitarIndActivo();
      //$('#console-event').html('Toggle: ' + $(this).prop('checked'))
    });

    $('#idReutClie').change(function() {
//    	alert('valor '+$(this).prop('checked'));
    	var idIndicadorReu = document.getElementById("idFormCuponItem:idIndicadorReu");


    	if($(this).prop('checked')){
    		idIndicadorReu.value = "1";
    	}else{
    		idIndicadorReu.value = "0";
    	}
    	
    	metodoRemotoHabilitarIndReutClie();
      //$('#console-event').html('Toggle: ' + $(this).prop('checked'))
    });

  })


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



function validarCorreo(correo) {
    var atpos = correo.indexOf("@");
    var dotpos = correo.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=correo.length) {
        return false;
    }else{
    	return true;
    }
}

function validarContieneEspacio(cadena) {
	var regexEspacio = /\s/;
	
	// se chequea el regex de que el string no tenga espacio
	if(regexEspacio.test(cadena.trim())){
	    return true; 
	}
	else{
	    // alert ("Ok"); 
	    return false; 
	}
}

function validacionSoloNumeros(cadena) {
	
	var reg = /^\d+$/;
	
    // se chequea el regex de que el string solo tenga numeros
	if(reg.test(cadena.trim())){
	    return true; 
	}
	else{
	    // alert ("Ok"); 
	    return false; 
	}
	
}


/* Bloque para el Banco Item */


function enviar(event) {
	
//	$("[id='idFormCuponItem:idMensajeResultadoProceso']").css("display","none");
	
	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );
	
	$("#idMensajeTipoPerfil").empty();
	$("#idMensajeTipoPerfil").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeTipoPerfil").addClass( "hidden" );
	$("#idMensajeTipoPerfil").css("display","block");
	
	$("#idMensajeTipoOperacion").empty();
	$("#idMensajeTipoOperacion").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeTipoOperacion").addClass( "hidden" );
	$("#idMensajeTipoOperacion").css("display","block");
	
	$("#idMensajeEmailCliente").empty();
	$("#idMensajeEmailCliente").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeEmailCliente").addClass( "hidden" );
	$("#idMensajeEmailCliente").css("display","block");
	
	$("#idMensajeNombreCupon").empty();
	$("#idMensajeNombreCupon").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeNombreCupon").addClass( "hidden" );
	$("#idMensajeNombreCupon").css("display","block");
	
	$("#idMensajeCantidadCupon").empty();
	$("#idMensajeCantidadCupon").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeCantidadCupon").addClass( "hidden" );
	$("#idMensajeCantidadCupon").css("display","block");
	
	$("#idMensajeDescuentoCupon").empty();
	$("#idMensajeDescuentoCupon").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeDescuentoCupon").addClass( "hidden" );
	$("#idMensajeDescuentoCupon").css("display","block");
	
	
	/*Remueves los errores si es que hubieran*/
	
	$("[id='idFormCuponItem:idComboTipoPerfil']").removeClass( "claseErrorFormulario");
	$("[id='idFormCuponItem:idComboTipoOperacion']").removeClass( "claseErrorFormulario" );
	$("[id='idFormCuponItem:idTxtEmailCliente']").removeClass( "claseErrorFormulario");
	$("[id='idFormCuponItem:idTxtNombreCupon']").removeClass( "claseErrorFormulario");
	$("[id='idFormCuponItem:idTxtCantidadCupon']").removeClass( "claseErrorFormulario");
	$("[id='idFormCuponItem:idTxtDescuentoCupon']").removeClass( "claseErrorFormulario");
	
	/*Captura valores*/
	
	var idComboTipoPerfil = $("[id='idFormCuponItem:idComboTipoPerfil']").val();
	var idComboTipoOperacion = $("[id='idFormCuponItem:idComboTipoOperacion']").val();
    var idTxtEmailCliente = $("[id='idFormCuponItem:idTxtEmailCliente']").val();
    var idTxtNombreCupon = $("[id='idFormCuponItem:idTxtNombreCupon']").val();
    var idTxtCantidadCupon = $("[id='idFormCuponItem:idTxtCantidadCupon']").val();
    var idTxtDescuentoCupon = $("[id='idFormCuponItem:idTxtDescuentoCupon_input']").val();
    
    if (idComboTipoPerfil != '0' && idComboTipoPerfil != '1' && idComboTipoPerfil != '2'){ 
    	$("#idMensajeIndVistaCliente").text("El tipo de perfil esta errado, verifique por favor.");
    	$("#idMensajeIndVistaCliente").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeIndVistaCliente").removeClass( "hidden" );
    	$("#idMensajeIndVistaCliente").fadeOut(6000);
    	$("[id='idFormCuponItem:idComboTipoPerfil']").focus().select();
    	$("[id='idFormCuponItem:idComboTipoPerfil']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idComboTipoOperacion != '0' && idComboTipoOperacion != '1' && idComboTipoOperacion != '2'){ 
    	$("#idMensajeTipoOperacion").text("El tipo de operación esta errado, verifique por favor.");
    	$("#idMensajeTipoOperacion").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeTipoOperacion").removeClass( "hidden" );
    	$("#idMensajeTipoOperacion").fadeOut(6000);
    	$("[id='idFormCuponItem:idComboTipoOperacion']").focus().select();
    	$("[id='idFormCuponItem:idComboTipoOperacion']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if(idTxtEmailCliente && !validarCorreo(idTxtEmailCliente)){
    	$("#idMensajeEmailCliente").text("El email cliente no es válido.");
    	$("#idMensajeEmailCliente").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeEmailCliente").removeClass( "hidden" );
	    $("#idMensajeTipoOperacion").fadeOut(6000);
    	$("[id='idFormCuponItem:idTxtEmailCliente']").focus().select();
    	$("[id='idFormCuponItem:idTxtEmailCliente']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if(idTxtEmailCliente && validarContieneEspacio(idTxtEmailCliente)){
    	$("#idMensajeEmailCliente").text("El email cliente no puede contener espacios en blanco.");
    	$("#idMensajeEmailCliente").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeEmailCliente").removeClass( "hidden" );
	    $("#idMensajeTipoOperacion").fadeOut(6000);
    	$("[id='idFormCuponItem:idTxtEmailCliente']").focus().select();
    	$("[id='idFormCuponItem:idTxtEmailCliente']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idTxtNombreCupon == null || idTxtNombreCupon == ''){
    	$("#idMensajeNombreCupon").text("El nombre de cupon es obligatorio.");
    	$("#idMensajeNombreCupon").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeNombreCupon").removeClass( "hidden" );
    	$("#idMensajeNombreCupon").fadeOut(6000);
    	$("[id='idFormCuponItem:idTxtNombreCupon']").focus();
    	$("[id='idFormCuponItem:idTxtNombreCupon']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (!validacionSoloNumeros(idTxtCantidadCupon)){
    	$("#idMensajeCantidadCupon").text("La cantidad de cupones debe ser númerica.");
    	$("#idMensajeCantidadCupon").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeCantidadCupon").removeClass( "hidden" );
    	$("#idMensajeCantidadCupon").fadeOut(6000);
    	$("[id='idFormCuponItem:idTxtCantidadCupon']").focus();
    	$("[id='idFormCuponItem:idTxtCantidadCupon']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idTxtCantidadCupon == null || idTxtCantidadCupon == ''){
    	$("#idMensajeCantidadCupon").text("La cantidad de cupones es obligatoria.");
    	$("#idMensajeCantidadCupon").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeCantidadCupon").removeClass( "hidden" );
    	$("#idMensajeCantidadCupon").fadeOut(6000);
    	$("[id='idFormCuponItem:idTxtCantidadCupon']").focus();
    	$("[id='idFormCuponItem:idTxtCantidadCupon']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idTxtDescuentoCupon == null || idTxtDescuentoCupon == ''){ 
    	$("#idMensajeDescuentoCupon").text("El descuento es obligatorio.");
    	$("#idMensajeDescuentoCupon").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeDescuentoCupon").removeClass( "hidden" );
    	$("#idMensajeDescuentoCupon").fadeOut(6000);
    	$("[id='idFormCuponItem:idTxtDescuentoCupon']").focus().select();
    	$("[id='idFormCuponItem:idTxtDescuentoCupon']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else{
    	mostrarPopupConfirmaGuardarCupon();
    }
}

function mostrarPopupConfirmaGuardarCupon(){
	$('#idModalConfirmacionGuardaCupon').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionGuardaCupon').modal('show');

}

function operacionGuardarCuponExitosa(){
	$('#idModalGuardarCuponExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalGuardarCuponExito').modal('show');
}


