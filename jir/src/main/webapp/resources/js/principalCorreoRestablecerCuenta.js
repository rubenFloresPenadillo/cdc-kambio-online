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
	
	$("[id='idFormIngreso:idMensajeResultadoProceso']").css("display","none");

	var idTxtEmail = $("[id='idFormIngreso:idTxtEmail']").val();
    
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
    } else{
    	metodoRemotoEnviarFormulario();
    }
}
