ejecutoIncremento = false;  

$(document).ready(function() {
	$("#modalComo").on('hidden.bs.modal', function (e) {
	    $("#modalComo iframe").attr("src", $("#modalComo iframe").attr("src"));
	});
});



function enlaceWhatsapp() {
	 var win = window.open('https://api.whatsapp.com/send?phone=51992951162&text=Hola,%20solicito%20información%20acerca%20de%20', '_blank');
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

function enviarOld(event) {
	//var x = document.getElementById("idFormLibroReclamacion:idCheckRespuesta").checked;
	
	var idCheckRespuestaCorreo = document.getElementById("idFormLibroReclamacion:idCheckRespuestaCorreo").checked;
	var idCheckTitularidad = document.getElementById("idFormLibroReclamacion:idCheckTitularidad").checked;
	
	alert("idCheckRespuestaCorreo "+idCheckRespuestaCorreo+ " - idCheckTitularidad "+idCheckTitularidad);	
	
}

function enviar(event) {
	
//	$("[id='idFormLibroReclamacion:idMensajeResultadoProceso']").css("display","none");

	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );
	
	$("#idMensajeResultadoTipoPersona").empty();
	$("#idMensajeResultadoTipoPersona").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoTipoPersona").addClass( "hidden" );
	$("#idMensajeResultadoTipoPersona").css("display","block");
	
	$("#idMensajeResultadoNombres").empty();
	$("#idMensajeResultadoNombres").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoNombres").addClass( "hidden" );
	$("#idMensajeResultadoNombres").css("display","block");
	
	$("#idMensajeResultadoApellidos").empty();
	$("#idMensajeResultadoApellidos").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoApellidos").addClass( "hidden" );
	$("#idMensajeResultadoApellidos").css("display","block");
	
	$("#idMensajeResultadoTipoDocumento").empty();
	$("#idMensajeResultadoTipoDocumento").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoTipoDocumento").addClass( "hidden" );
	$("#idMensajeResultadoTipoDocumento").css("display","block");
	
	$("#idMensajeResultadoDocumento").empty();
	$("#idMensajeResultadoDocumento").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoDocumento").addClass( "hidden" );
	$("#idMensajeResultadoDocumento").css("display","block");
	
	$("#idMensajeResultadoEmail").empty();
	$("#idMensajeResultadoEmail").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoEmail").addClass( "hidden" );
	$("#idMensajeResultadoEmail").css("display","block");
	
	$("#idMensajeResultadoTelefono").empty();
	$("#idMensajeResultadoTelefono").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoTelefono").addClass( "hidden" );
	$("#idMensajeResultadoTelefono").css("display","block");
	
	$("#idMensajeResultadoTipoServicio").empty();
	$("#idMensajeResultadoTipoServicio").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoTipoServicio").addClass( "hidden" );
	$("#idMensajeResultadoTipoServicio").css("display","block");
	
	$("#idMensajeResultadoMontoCambiado").empty();
	$("#idMensajeResultadoMontoCambiado").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoMontoCambiado").addClass( "hidden" );
	$("#idMensajeResultadoMontoCambiado").css("display","block");
	
	$("#idMensajeResultadoTipoReclamo").empty();
	$("#idMensajeResultadoTipoReclamo").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoTipoReclamo").addClass( "hidden" );
	$("#idMensajeResultadoTipoReclamo").css("display","block");
	
	$("#idMensajeDescripcionReclamo").empty();
	$("#idMensajeDescripcionReclamo").removeClass( "alert alert-danger claseMensajeResultado" )
    $("idMensajeDescripcionReclamo").addClass( "hidden" );
	$("#idMensajeDescripcionReclamo").css("display","block");
	
	$("#idMensajeResultadoRespuestaCorreo").empty();
	$("#idMensajeResultadoRespuestaCorreo").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoRespuestaCorreo").addClass( "hidden" );
	$("#idMensajeResultadoRespuestaCorreo").css("display","block");
	
	$("#idMensajeResultadoCheckTitularidad").empty();
	$("#idMensajeResultadoCheckTitularidad").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoCheckTitularidad").addClass( "hidden" );
	$("#idMensajeResultadoCheckTitularidad").css("display","block");
	
	/*Remueves los errores si es que hubieran*/
	
	$("[id='idFormLibroReclamacion:idTipoPersona']").removeClass( "claseErrorFormulario" );
	$("[id='idFormLibroReclamacion:idTxtNombres']").removeClass( "claseErrorFormulario");
	$("[id='idFormLibroReclamacion:idTxtApellidos']").removeClass( "claseErrorFormulario");
	$("[id='idFormLibroReclamacion:idTxtRazonSocial']").removeClass( "claseErrorFormulario");
	$("[id='idFormLibroReclamacion:idTxtRuc']").removeClass( "claseErrorFormulario");
	$("[id='idFormLibroReclamacion:idTipoDocumento']").removeClass( "claseErrorFormulario");
	$("[id='idFormLibroReclamacion:idTxtDocumento']").removeClass( "claseErrorFormulario");
	$("[id='idFormLibroReclamacion:idTxtEmail']").removeClass( "claseErrorFormulario");
	$("[id='idFormLibroReclamacion:idTxtTelefono']").removeClass( "claseErrorFormulario");
	$("[id='idFormLibroReclamacion:idTipoServicio']").removeClass( "claseErrorFormulario");
	$("[id='idFormLibroReclamacion:idTxtMontoCambiado']").removeClass( "claseErrorFormulario");
	$("[id='idFormLibroReclamacion:idTxtCodigoOperacion']").removeClass( "claseErrorFormulario");
	$("[id='idFormLibroReclamacion:idTipoReclamo']").removeClass( "claseErrorFormulario");
	$("[id='idFormLibroReclamacion:idTxtDescripcionReclamo']").removeClass( "claseErrorFormulario");
	$("[id='idFormLibroReclamacion:idCheckRespuestaCorreo']").removeClass( "claseErrorFormulario");
	$("[id='idFormLibroReclamacion:idCheckTitularidad']").removeClass( "claseErrorFormulario");	
	
	/*Captura valores*/
	
	var idTipoPersona = $("[id='idFormLibroReclamacion:idTipoPersona']").val();
	var idTxtNombres = $("[id='idFormLibroReclamacion:idTxtNombres']").val();
	var idTxtApellidos = $("[id='idFormLibroReclamacion:idTxtApellidos']").val();
	var idTipoDocumento = $("[id='idFormLibroReclamacion:idTipoDocumento']").val();
	var idTxtDocumento = $("[id='idFormLibroReclamacion:idTxtDocumento']").val();
	var idTxtEmail = $("[id='idFormLibroReclamacion:idTxtEmail']").val();
	var idTxtTelefono = $("[id='idFormLibroReclamacion:idTxtTelefono']").val();
	var idTipoServicio = $("[id='idFormLibroReclamacion:idTipoServicio']").val();
	var idTxtMontoCambiado = $("[id='idFormLibroReclamacion:idTxtMontoCambiado']").val();
	var idTxtCodigoOperacion = $("[id='idFormLibroReclamacion:idTxtCodigoOperacion']").val();
	var idTipoReclamo = $("[id='idFormLibroReclamacion:idTipoReclamo']").val();
	var idTxtDescripcionReclamo = $("[id='idFormLibroReclamacion:idTxtDescripcionReclamo']").val();
	var idCheckRespuestaCorreo = document.getElementById("idFormLibroReclamacion:idCheckRespuestaCorreo").checked;
	var idCheckTitularidad = document.getElementById("idFormLibroReclamacion:idCheckTitularidad").checked;
    
    if (idTipoPersona == null || idTipoPersona == '-1'){
    	$("#idMensajeResultadoTipoPersona").text("El tipo de persona es obligatorio.");
    	$("#idMensajeResultadoTipoPersona").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoTipoPersona").removeClass( "hidden" );
    	$("#idMensajeResultadoTipoPersona").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idTipoPersona']").focus();
    	$("[id='idFormLibroReclamacion:idTipoPersona']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (!idTxtNombres.trim()){
    	$("#idMensajeResultadoNombres").text("El nombre es obligatorio.");
    	$("#idMensajeResultadoNombres").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoNombres").removeClass( "hidden" );
    	$("#idMensajeResultadoNombres").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idTxtNombres']").focus();
    	$("[id='idFormLibroReclamacion:idTxtNombres']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if(!idTxtApellidos.trim()){
    	$("#idMensajeResultadoApellidos").text("El apellido es obligatorio.");
    	$("#idMensajeResultadoApellidos").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoApellidos").removeClass( "hidden" );
    	$("#idMensajeResultadoApellidos").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idTxtApellidos']").focus().select();
    	$("[id='idFormLibroReclamacion:idTxtApellidos']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if(idTipoDocumento == null || idTipoDocumento == '-1' ){
    	$("#idMensajeResultadoTipoDocumento").text("El tipo de documento es obligatorio.");
    	$("#idMensajeResultadoTipoDocumento").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoTipoDocumento").removeClass( "hidden" );
    	$("#idMensajeResultadoTipoDocumento").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idTipoDocumento']").focus().select();
    	$("[id='idFormLibroReclamacion:idTipoDocumento']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if(!idTxtDocumento.trim()){
    	$("#idMensajeResultadoDocumento").text("El apellido es obligatorio..");
    	$("#idMensajeResultadoDocumento").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoDocumento").removeClass( "hidden" );
    	$("#idMensajeResultadoDocumento").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idTxtDocumento']").focus().select();
    	$("[id='idFormLibroReclamacion:idTxtDocumento']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if(!validarCorreo(idTxtEmail)){
    	$("#idMensajeResultadoEmail").text("El correo no es válido.");
    	$("#idMensajeResultadoEmail").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoEmail").removeClass( "hidden" );
    	$("#idMensajeResultadoEmail").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idTxtEmail']").focus().select();
    	$("[id='idFormLibroReclamacion:idTxtEmail']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if(validarContieneEspacio(idTxtEmail)){
    	$("#idMensajeResultadoEmail").text("El correo no puede contener espacios en blanco.");
    	$("#idMensajeResultadoEmail").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoEmail").removeClass( "hidden" );
    	$("#idMensajeResultadoEmail").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idTxtEmail']").focus().select();
    	$("[id='idFormLibroReclamacion:idTxtEmail']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (!idTxtTelefono.trim()){ 
    	$("#idMensajeResultadoTelefono").text("El teléfono es obligatorio.");
    	$("#idMensajeResultadoTelefono").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoTelefono").removeClass( "hidden" );
    	$("#idMensajeResultadoTelefono").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idTxtTelefono']").focus().select();
    	$("[id='idFormLibroReclamacion:idTxtTelefono']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idTipoServicio == null || idTipoServicio == '-1' ){ 
    	$("#idMensajeResultadoTipoServicio").text("El tipo de servicio es obligatorio.");
    	$("#idMensajeResultadoTipoServicio").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoTipoServicio").removeClass( "hidden" );
    	$("#idMensajeResultadoTipoServicio").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idTipoServicio']").focus().select();
    	$("[id='idFormLibroReclamacion:idTipoServicio']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (!idTxtMontoCambiado.trim()){ 
    	$("#idMensajeResultadoMontoCambiado").text("El monto cambiado es obligatorio.");
    	$("#idMensajeResultadoMontoCambiado").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoMontoCambiado").removeClass( "hidden" );
    	$("#idMensajeResultadoMontoCambiado").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idTxtMontoCambiado']").focus().select();
    	$("[id='idFormLibroReclamacion:idTxtMontoCambiado']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (!idTxtCodigoOperacion.trim()){ 
    	$("#idMensajeResultadoMontoCambiado").text("El código de operación.");
    	$("#idMensajeResultadoMontoCambiado").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoMontoCambiado").removeClass( "hidden" );
    	$("#idMensajeResultadoMontoCambiado").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idTxtCodigoOperacion']").focus().select();
    	$("[id='idFormLibroReclamacion:idTxtCodigoOperacion']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idTipoReclamo == null || idTipoReclamo == '-1' ){ 
    	$("#idMensajeResultadoTipoReclamo").text("El tipo de reclamo es obligatorio.");
    	$("#idMensajeResultadoTipoReclamo").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoTipoReclamo").removeClass( "hidden" );
    	$("#idMensajeResultadoTipoReclamo").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idTipoReclamo']").focus().select();
    	$("[id='idFormLibroReclamacion:idTipoReclamo']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (!idTxtDescripcionReclamo.trim()){ 
    	$("#idMensajeDescripcionReclamo").text("El descripción del reclamo es obligatorio.");
    	$("#idMensajeDescripcionReclamo").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeDescripcionReclamo").removeClass( "hidden" );
    	$("#idMensajeDescripcionReclamo").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idTxtDescripcionReclamo']").focus().select();
    	$("[id='idFormLibroReclamacion:idTxtDescripcionReclamo']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (!idTxtTelefono.trim()){ 
    	$("#idMensajeResultadoTelefono").text("El teléfono es obligatorio.");
    	$("#idMensajeResultadoTelefono").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoTelefono").removeClass( "hidden" );
    	$("#idMensajeResultadoTelefono").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idTxtTelefono']").focus().select();
    	$("[id='idFormLibroReclamacion:idTxtTelefono']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (!idTxtTelefono.trim()){ 
    	$("#idMensajeResultadoTelefono").text("El teléfono es obligatorio.");
    	$("#idMensajeResultadoTelefono").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoTelefono").removeClass( "hidden" );
    	$("#idMensajeResultadoTelefono").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idTxtTelefono']").focus().select();
    	$("[id='idFormLibroReclamacion:idTxtTelefono']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (!idTxtTelefono.trim()){ 
    	$("#idMensajeResultadoTelefono").text("El teléfono es obligatorio.");
    	$("#idMensajeResultadoTelefono").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoTelefono").removeClass( "hidden" );
    	$("#idMensajeResultadoTelefono").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idTxtTelefono']").focus().select();
    	$("[id='idFormLibroReclamacion:idTxtTelefono']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }  else if (!idCheckRespuestaCorreo){ 
    	$("#idMensajeResultadoRespuestaCorreo").text("La autorización de respuesta a su correo es obligatoria.");
    	$("#idMensajeResultadoRespuestaCorreo").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoRespuestaCorreo").removeClass( "hidden" );
    	$("#idMensajeResultadoRespuestaCorreo").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idCheckRespuestaCorreo']").focus().select();
    	$("[id='idFormLibroReclamacion:idCheckRespuestaCorreo']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }  else if (!idCheckTitularidad){ 
    	$("#idMensajeResultadoCheckTitularidad").text("La declaración jurada es obligatoria.");
    	$("#idMensajeResultadoCheckTitularidad").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoCheckTitularidad").removeClass( "hidden" );
    	$("#idMensajeResultadoCheckTitularidad").fadeOut(6000);
    	$("[id='idFormLibroReclamacion:idCheckTitularidad']").focus().select();
    	$("[id='idFormLibroReclamacion:idCheckTitularidad']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else{
    	mostrarPopupConfirmaGuardar();
    }

}

function mostrarPopupConfirmaGuardar(){
	$('#idModalConfirmacionGuardar').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionGuardar').modal('show');
}

function operacionGuardarReclamoExitoso(){
	$('#idModalGuardarReclamoExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalGuardarReclamoExito').modal('show');
}

function procesoConError(){
	$('#idModalErrorSistema').modal({backdrop: 'static', keyboard: false})  
	$('#idModalErrorSistema').modal('show');
}
