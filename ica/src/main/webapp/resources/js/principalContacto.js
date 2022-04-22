ejecutoIncremento = false;  

$(document).ready(function() {
	$("#modalComo").on('hidden.bs.modal', function (e) {
	    $("#modalComo iframe").attr("src", $("#modalComo iframe").attr("src"));
	});
});



function enlaceWhatsapp() {
	 var win = window.open('https://api.whatsapp.com/send?phone=51981148030&text=Hola,%20solicito%20información%20acerca%20de%20', '_blank');
	 win.focus();  
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

function enviar(event) {
	
//	$("[id='idFormContacto:idMensajeResultadoProceso']").css("display","none");
	
	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );
	
	$("#idMensajeNombreCompleto").empty();
	$("#idMensajeNombreCompleto").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeNombreCompleto").addClass( "hidden" );
	$("#idMensajeNombreCompleto").css("display","block");
	
	$("#idMensajeTelefonoCelular").empty();
	$("#idMensajeTelefonoCelular").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeTelefonoCelular").addClass( "hidden" );
	$("#idMensajeTelefonoCelular").css("display","block");
	
	$("#idMensajeCorreoElectronico").empty();
	$("#idMensajeCorreoElectronico").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeCorreoElectronico").addClass( "hidden" );
	$("#idMensajeCorreoElectronico").css("display","block");
	
	$("#idMensajeAsunto").empty();
	$("#idMensajeAsunto").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeAsunto").addClass( "hidden" );
	$("#idMensajeAsunto").css("display","block");
	
	$("#idMensaje").empty();
	$("#idMensaje").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensaje").addClass( "hidden" );
	$("#idMensaje").css("display","block");
	
	/*Remueves los errores si es que hubieran*/
	
	$("[id='idFormContacto:idTxtNombreCompleto']").removeClass( "claseErrorFormulario" );
	$("[id='idFormContacto:idTxtTelefonoCelular']").removeClass( "claseErrorFormulario");
	$("[id='idFormContacto:idTxtCorreoElectronico']").removeClass( "claseErrorFormulario");
	$("[id='idFormContacto:idTxtAsunto']").removeClass( "claseErrorFormulario");
	$("[id='idFormContacto:idTxtMensaje']").removeClass( "claseErrorFormulario");
	
	/*Captura valores*/
	
    var idTxtNombreCompleto = $("[id='idFormContacto:idTxtNombreCompleto']").val();
    var idTxtTelefonoCelular = $("[id='idFormContacto:idTxtTelefonoCelular']").val();
    var idTxtCorreoElectronico = $("[id='idFormContacto:idTxtCorreoElectronico']").val();
    var idTxtAsunto = $("[id='idFormContacto:idTxtAsunto']").val();
    var idTxtMensaje = $("[id='idFormContacto:idTxtMensaje']").val();
    
    if (!idTxtNombreCompleto.trim()){
    	$("#idMensajeNombreCompleto").text("El nombre es obligatorio.");
    	$("#idMensajeNombreCompleto").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeNombreCompleto").removeClass( "hidden" );
    	$("#idMensajeNombreCompleto").fadeOut(6000);
    	$("[id='idFormContacto:idTxtNombreCompleto']").focus();
    	$("[id='idFormContacto:idTxtNombreCompleto']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (!idTxtTelefonoCelular.trim()){
    	$("#idMensajeTelefonoCelular").text("El teléfono es obligatorio.");
    	$("#idMensajeTelefonoCelular").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeTelefonoCelular").removeClass( "hidden" );
    	$("#idMensajeTelefonoCelular").fadeOut(6000);
    	$("[id='idFormContacto:idTxtTelefonoCelular']").focus();
    	$("[id='idFormContacto:idTxtTelefonoCelular']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if(!idTxtCorreoElectronico.trim()){
    	$("#idMensajeCorreoElectronico").text("El correo es obligatorio.");
    	$("#idMensajeCorreoElectronico").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeCorreoElectronico").removeClass( "hidden" );
    	$("#idMensajeCorreoElectronico").fadeOut(6000);
    	$("[id='idFormContacto:idTxtCorreoElectronico']").focus().select();
    	$("[id='idFormContacto:idTxtCorreoElectronico']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if(!validarCorreo(idTxtCorreoElectronico)){
    	$("#idMensajeCorreoElectronico").text("El correo no es válido.");
    	$("#idMensajeCorreoElectronico").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeCorreoElectronico").removeClass( "hidden" );
    	$("#idMensajeCorreoElectronico").fadeOut(6000);
    	$("[id='idFormContacto:idTxtCorreoElectronico']").focus().select();
    	$("[id='idFormContacto:idTxtCorreoElectronico']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if(validarContieneEspacio(idTxtCorreoElectronico)){
    	$("#idMensajeCorreoElectronico").text("El correo no puede contener espacios en blanco.");
    	$("#idMensajeCorreoElectronico").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeCorreoElectronico").removeClass( "hidden" );
    	$("#idMensajeCorreoElectronico").fadeOut(6000);
    	$("[id='idFormContacto:idTxtCorreoElectronico']").focus().select();
    	$("[id='idFormContacto:idTxtCorreoElectronico']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (!idTxtAsunto.trim()){ 
    	$("#idMensajeAsunto").text("El asunto es obligatorio.");
    	$("#idMensajeAsunto").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeAsunto").removeClass( "hidden" );
    	$("#idMensajeAsunto").fadeOut(6000);
    	$("[id='idFormContacto:idTxtAsunto']").focus().select();
    	$("[id='idFormContacto:idTxtAsunto']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (!idTxtMensaje.trim()){ 
    	$("#idMensaje").text("El mensaje es obligatorio.");
    	$("#idMensaje").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensaje").removeClass( "hidden" );
    	$("#idMensaje").fadeOut(6000);
    	$("[id='idFormContacto:idTxtMensaje']").focus().select();
    	$("[id='idFormContacto:idTxtMensaje']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else{
    	mostrarPopupConfirmaEnviarMensaje();
    }
}

function mostrarPopupConfirmaEnviarMensaje(){
	$('#idModalConfirmacionEnviarMensaje').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionEnviarMensaje').modal('show');

}

function operacionEnviarMensajeExitoso(){
	$('#idModalEnviarMensajeoExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalEnviarMensajeoExito').modal('show');
}

function procesoConError(){
	$('#idModalErrorSistema').modal({backdrop: 'static', keyboard: false})  
	$('#idModalErrorSistema').modal('show');
}

