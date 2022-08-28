
$(document).ready(function() {
	
	$("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion1']").focus();
	
	$("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion1']").keyup(function(event){     
		
		var iKeyCode = (event.which) ? event.which : event.keyCode;
				
	       if( (iKeyCode > 47 && iKeyCode < 58 ) || (iKeyCode > 95 && iKeyCode < 106)  ){
	    	   $("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion2']").focus(); 
	       }else{
	    	   event.preventDefault(); 
	       }
	});
	
	$("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion2']").keyup(function(event){     
		
		var iKeyCode = (event.which) ? event.which : event.keyCode;

		  if( (iKeyCode > 47 && iKeyCode < 58 ) || (iKeyCode > 95 && iKeyCode < 106)  ){
	    	   $("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion3']").focus(); 
	       }else{
	    	   event.preventDefault(); 
	       }
	});
	
	$("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion3']").keyup(function(event){     
		
		var iKeyCode = (event.which) ? event.which : event.keyCode;

		  if( (iKeyCode > 47 && iKeyCode < 58 ) || (iKeyCode > 95 && iKeyCode < 106)  ){
	    	   $("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion4']").focus(); 
	       }else{
	    	   event.preventDefault(); 
	       }
	});	
	
    var fiveMinutes = 60 * 5,
    display = $('#time');
    startTimer(fiveMinutes, display);
});



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

	$("[id='idFormConfirmaCuentaCodigo:idMensajeResultadoProceso']").css("display","none");
	
	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );
	
	$("#idMensajeResultadoCodigo").empty();
	$("#idMensajeResultadoCodigo").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoCodigo").addClass( "hidden" );
	$("#idMensajeResultadoCodigo").css("display","block");
	
	
	$("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion1']").removeClass( "claseErrorFormulario" );
	$("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion2']").removeClass( "claseErrorFormulario" );
	$("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion3']").removeClass( "claseErrorFormulario" );
	$("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion4']").removeClass( "claseErrorFormulario" );
    
	var idTxtCodigoVerificacion1 = $("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion1']").val();
	var idTxtCodigoVerificacion2 = $("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion2']").val();
	var idTxtCodigoVerificacion3 = $("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion3']").val();
	var idTxtCodigoVerificacion4 = $("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion4']").val();
	
    if(idTxtCodigoVerificacion1 == null || idTxtCodigoVerificacion1 == '' ||
       idTxtCodigoVerificacion2 == null || idTxtCodigoVerificacion2 == '' ||
       idTxtCodigoVerificacion3 == null || idTxtCodigoVerificacion3 == '' ||
       idTxtCodigoVerificacion4 == null || idTxtCodigoVerificacion4 == ''){
    	$("#idMensajeResultadoCodigo").text("El codigo ingresado es incorrecto, por favor verifique.");
    	$("#idMensajeResultadoCodigo").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoCodigo").removeClass( "hidden" );
    	$("#idMensajeResultadoCodigo").fadeOut(6000);
    	$("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion1']").focus();
    	$("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion1']").addClass( "claseErrorFormulario" );
    	$("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion2']").addClass( "claseErrorFormulario" ); 
    	$("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion3']").addClass( "claseErrorFormulario" ); 
    	$("[id='idFormConfirmaCuentaCodigo:idTxtCodigoVerificacion4']").addClass( "claseErrorFormulario" );     	
    	event.preventDefault();
    } else{
    	metodoRemotoEnviarFormulario();
    }
}

function operacionConfirmaCuentaCodigoExitosa(){
	$('#idModalConfirmaCuentaCodigoExitosa').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmaCuentaCodigoExitosa').modal('show');
}

function startTimer(duration, display) {
    var timer = duration, minutes, seconds;
    setInterval(function () {
        minutes = parseInt(timer / 60, 10)
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.text(minutes + ":" + seconds);

        if (--timer < 0) {
            timer = duration;
        }
    }, 1000);
}

jQuery(function ($) {

});