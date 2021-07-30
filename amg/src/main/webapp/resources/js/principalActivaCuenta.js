
//function validarCorreo(correo) {
//    var atpos = correo.indexOf("@");
//    var dotpos = correo.lastIndexOf(".");
//    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=correo.length) {
//        return false;
//    }else{
//    	return true;
//    }
//}
//
//function enviar(event) {
////	alert('hola enviar');
//	$("#idMensajeResultado").empty(); // elimina el contenido del div
//	$("#idMensajeResultado").removeClass( "alert alert-success alert-warning alert-danger" )
//    $("#idMensajeResultado").addClass( "hidden" );
//	
////	 $("#idMensajeResultadoProceso").css("display","none");
//	 $("[id='idFormRegistro:idMensajeResultadoProceso']").css("display","none");
//
//	var idTxtEmail = $("[id='idFormRegistro:idTxtEmail']").val();
//    var idTxtPassword1 = $("[id='idFormRegistro:idTxtPassword1']").val();
//    var idTxtPassword2 = $("[id='idFormRegistro:idTxtPassword2']").val();
//    
//    if(idTxtEmail == null || idTxtEmail == ''){
//    	$("#idMensajeResultado").text("El correo es obligatorio.");
//    	$("#idMensajeResultado").addClass( "alert alert-warning" )
//        $("#idMensajeResultado").removeClass( "hidden" );
//    	event.preventDefault();
//    } else if(!validarCorreo(idTxtEmail)){
//    	$("#idMensajeResultado").text("El correo no es válido.");
//    	$("#idMensajeResultado").addClass( "alert alert-warning" )
//        $("#idMensajeResultado").removeClass( "hidden" );
//    	event.preventDefault();
//    } else if (idTxtPassword1 == null || idTxtPassword1 == '') {
//    	$("#idMensajeResultado").text("El password es obligatorio.");
//    	$("#idMensajeResultado").addClass( "alert alert-warning" )
//        $("#idMensajeResultado").removeClass( "hidden" );
//    	event.preventDefault();
//    } else if (idTxtPassword2 == null || idTxtPassword2 == '') {
//    	$("#idMensajeResultado").text("La confirmación del password es obligatoria.");
//    	$("#idMensajeResultado").addClass( "alert alert-warning" )
//        $("#idMensajeResultado").removeClass( "hidden" );
//    	event.preventDefault();
//    } else if(idTxtPassword1 != idTxtPassword2 ){
//    	$("#idMensajeResultado").text("Passwords no coinciden, verifique.");
//    	$("#idMensajeResultado").addClass( "alert alert-warning" )
//        $("#idMensajeResultado").removeClass( "hidden" );
//    	event.preventDefault();
//    } else{
////    	alert('entro al else');
//    	metodoRemotoEnviarFormulario();
//    }
//}

function procesoConError(){
	$('#idModalErrorSistema').modal({backdrop: 'static', keyboard: false})  
	$('#idModalErrorSistema').modal('show');
}

