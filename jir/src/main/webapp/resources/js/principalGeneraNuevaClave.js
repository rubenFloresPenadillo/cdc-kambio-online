function operacionGenerarNuevaClave(){
	$('#idModalGenerarNuevaClaveExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalGenerarNuevaClaveExito').modal('show');
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

function enviar(event) {

	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-success alert-warning alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );

    var idTxtPassword1 = $("[id='idFormRegistro:idTxtPassword1']").val();
    var idTxtPassword2 = $("[id='idFormRegistro:idTxtPassword2']").val();
    
    if (idTxtPassword1 == null || idTxtPassword1 == '') {
    	$("#idMensajeResultado").text("El password es obligatorio.");
    	$("#idMensajeResultado").addClass( "alert alert-warning" )
        $("#idMensajeResultado").removeClass( "hidden" );
    	event.preventDefault();
    } else if (idTxtPassword2 == null || idTxtPassword2 == '') {
    	$("#idMensajeResultado").text("La confirmación del password es obligatoria.");
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

 function mostrarContrasena(nombreFormulario, idInputPassword, iconoOjoPassword){
      var tipo = document.getElementById(nombreFormulario+':'+idInputPassword);
      if(tipo.type == "password"){
         tipo.type = "text";
		 $('#'+iconoOjoPassword.id).removeClass( "pi-eye-slash" )
         $('#'+iconoOjoPassword.id).addClass( "pi-eye" );
      }else{
          tipo.type = "password";
		  $('#'+iconoOjoPassword.id).removeClass( "pi-eye" )
          $('#'+iconoOjoPassword.id).addClass( "pi-eye-slash" );
      }
  }