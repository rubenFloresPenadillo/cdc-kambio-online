function operacionGuardarRegistroUsuario(){
	$('#idModalGuardarRegistroExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalGuardarRegistroExito').modal('show');
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


function enviar(event) {
//	alert('hola enviar');
	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-success alert-warning alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );
	
//	 $("#idMensajeResultadoProceso").css("display","none");
//	 $("[id='idFormRegistro:idMensajeResultadoProceso']").css("display","none");

	var idTxtEmail = $("[id='idFormRegistro:idTxtEmail']").val();
    var idTxtPassword1 = $("[id='idFormRegistro:idTxtPassword1']").val();
    var idTxtPassword2 = $("[id='idFormRegistro:idTxtPassword2']").val();
    
    if(idTxtEmail == null || idTxtEmail == ''){
    	$("#idMensajeResultado").text("El correo es obligatorio.");
    	$("#idMensajeResultado").addClass( "alert alert-warning" )
        $("#idMensajeResultado").removeClass( "hidden" );
    	event.preventDefault();
    } else if(!validarCorreo(idTxtEmail)){
    	$("#idMensajeResultado").text("El correo no es válido.");
    	$("#idMensajeResultado").addClass( "alert alert-warning" )
        $("#idMensajeResultado").removeClass( "hidden" );
    	event.preventDefault();
    } else if(validarContieneEspacio(idTxtEmail)){
    	$("#idMensajeResultado").text("El correo no puede contener espacios en blanco.");
    	$("#idMensajeResultado").addClass( "alert alert-warning" )
        $("#idMensajeResultado").removeClass( "hidden" );
    	event.preventDefault();
    } else if (idTxtPassword1 == null || idTxtPassword1 == '') {
    	$("#idMensajeResultado").text("El password es obligatorio.");
    	$("#idMensajeResultado").addClass( "alert alert-warning" )
        $("#idMensajeResultado").removeClass( "hidden" );
    	event.preventDefault();
    } else if(validarContieneEspacio(idTxtPassword1)){
    	$("#idMensajeResultado").text("El password no puede contener espacios en blanco.");
    	$("#idMensajeResultado").addClass( "alert alert-warning" )
        $("#idMensajeResultado").removeClass( "hidden" );
    	event.preventDefault();
    } else if(/(?=.{8,})/.test(idTxtPassword1) == false){
    	$("#idMensajeResultado").text("El password debe ser de 8 carácteres como mínimo.");
    	$("#idMensajeResultado").addClass( "alert alert-warning" )
        $("#idMensajeResultado").removeClass( "hidden" );
    	event.preventDefault();
    } else if(/^(?=.*[0-9])/.test(idTxtPassword1) == false){
    	$("#idMensajeResultado").text("El password debe contener al menos un carácter numérico.");
    	$("#idMensajeResultado").addClass( "alert alert-warning" )
        $("#idMensajeResultado").removeClass( "hidden" );
    	event.preventDefault();
    } else if(/^(?=.*[A-Z])/.test(idTxtPassword1) == false){
    	$("#idMensajeResultado").text("El password debe contener al menos un carácter mayúscula.");
    	$("#idMensajeResultado").addClass( "alert alert-warning" )
        $("#idMensajeResultado").removeClass( "hidden" );
    	event.preventDefault();
    }else if (idTxtPassword2 == null || idTxtPassword2 == '') {
    	$("#idMensajeResultado").text("La confirmación del password es obligatoria.");
    	$("#idMensajeResultado").addClass( "alert alert-warning" )
        $("#idMensajeResultado").removeClass( "hidden" );
    	event.preventDefault();
    } else if(validarContieneEspacio(idTxtPassword2)){
    	$("#idMensajeResultado").text("La confirmación del password no puede contener espacios en blanco.");
    	$("#idMensajeResultado").addClass( "alert alert-warning" )
        $("#idMensajeResultado").removeClass( "hidden" );
    	event.preventDefault();
    } else if(idTxtPassword1 != idTxtPassword2 ){
    	$("#idMensajeResultado").text("Passwords no coinciden, verifique.");
    	$("#idMensajeResultado").addClass( "alert alert-warning" )
        $("#idMensajeResultado").removeClass( "hidden" );
    	event.preventDefault();
    } else{
    	metodoRemotoEnviarFormulario();
    }
}

function seleccionarPersoneriaN(){
	var a = document.getElementById("idFormRegistro:idTipoPersoneria");
//	alert('valor actual: '+a.value);
	a.value = "1";
//	alert('valor despues: '+a.value);
}

function seleccionarPersoneriaJ(){
//	alert('seleccionarPersoneriaJ');
	var a = document.getElementById("idFormRegistro:idTipoPersoneria");
//	alert('valor actual: '+a.value);
	a.value = "2";
//	alert('valor despues: '+a.value);
}

