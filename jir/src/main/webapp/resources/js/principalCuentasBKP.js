function enviar(event) {
	
	$("[id='idFormCuentasPersonales:idMensajeResultadoProceso']").css("display","none");
	
	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );

	$("#idMensajeResultadoBancoCuenta").empty();
	$("#idMensajeResultadoBancoCuenta").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoBancoCuenta").addClass( "hidden" );
	$("#idMensajeResultadoBancoCuenta").css("display","block");
	
	$("#idMensajeResultadoTipoCuenta").empty();
	$("#idMensajeResultadoTipoCuenta").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoTipoCuenta").addClass( "hidden" );
	$("#idMensajeResultadoTipoCuenta").css("display","block");
	
	$("#idMensajeResultadoMoneda").empty();
	$("#idMensajeResultadoMoneda").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoMoneda").addClass( "hidden" );
	$("#idMensajeResultadoMoneda").css("display","block");
	
	$("#idMensajeResultadoNroCuenta").empty();
	$("#idMensajeResultadoNroCuenta").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoNroCuenta").addClass( "hidden" );
	$("#idMensajeResultadoNroCuenta").css("display","block");
	
	$("#idMensajeResultadoCuentaPropia").empty();
	$("#idMensajeResultadoCuentaPropia").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoCuentaPropia").addClass( "hidden" );
	$("#idMensajeResultadoCuentaPropia").css("display","block");
	
	$("#idMensajeResultadoAlias").empty();
	$("#idMensajeResultadoAlias").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoAlias").addClass( "hidden" );
	$("#idMensajeResultadoAlias").css("display","block");
	
	/*Remueves los errores si es que hubieran*/
	
	$("[id='idFormCuentasPersonales:idComboBancoCuenta']").removeClass( "claseErrorFormulario");
	$("[id='idFormCuentasPersonales:idComboMoneda']").removeClass( "claseErrorFormulario");
	$("[id='idFormCuentasPersonales:idComboCuentaPropia']").removeClass( "claseErrorFormulario");
	$("[id='idFormCuentasPersonales:idComboTipoCuenta']").removeClass( "claseErrorFormulario");
	$("[id='idFormCuentasPersonales:idTxtNroCuenta']").removeClass( "claseErrorFormulario");
	$("[id='idFormCuentasPersonales:idTxtAlias']").removeClass( "claseErrorFormulario");
	
	/*Captura valores*/
	
    var idComboBancoCuenta = $("[id='idFormCuentasPersonales:idComboBancoCuenta']").val();
    var idComboMoneda = $("[id='idFormCuentasPersonales:idComboMoneda']").val();
    var idComboCuentaPropia = $("[id='idFormCuentasPersonales:idComboCuentaPropia']").val();
    var idComboTipoCuenta = $("[id='idFormCuentasPersonales:idComboTipoCuenta']").val();
    var idTxtNroCuenta = $("[id='idFormCuentasPersonales:idTxtNroCuenta']").val();
    var idTxtAlias = $("[id='idFormCuentasPersonales:idTxtAlias']").val();
	
    if (idComboBancoCuenta == null || idComboBancoCuenta == '-1'){
    	$("#idMensajeResultadoBancoCuenta").text("El banco es obligatorio.");
    	$("#idMensajeResultadoBancoCuenta").addClass( "alert alert-danger claseMensajeResultado")
        $("#idMensajeResultadoBancoCuenta").removeClass( "hidden" );
    	$("#idMensajeResultadoBancoCuenta").fadeOut(6000);
    	$("[id='idFormCuentasPersonales:idComboBancoCuenta']").focus().select();;
    	$("[id='idFormCuentasPersonales:idComboBancoCuenta']").addClass( "claseErrorFormulario");
    	event.preventDefault();
    } else if (idComboTipoCuenta == null || idComboTipoCuenta == '-1'){ 
    	$("#idMensajeResultadoTipoCuenta").text("El tipo de cuenta es obligatorio.");
    	$("#idMensajeResultadoTipoCuenta").addClass( "alert alert-danger claseMensajeResultado")
        $("#idMensajeResultadoTipoCuenta").removeClass( "hidden" );
    	$("#idMensajeResultadoTipoCuenta").fadeOut(6000);
    	$("[id='idFormCuentasPersonales:idComboTipoCuenta']").focus().select();
    	$("[id='idFormCuentasPersonales:idComboTipoCuenta']").addClass( "claseErrorFormulario");
    	event.preventDefault();
    } else if (idComboMoneda == null || idComboMoneda == '-1'){ 
    	$("#idMensajeResultadoMoneda").text("La moneda es obligatoria.");
    	$("#idMensajeResultadoMoneda").addClass( "alert alert-danger claseMensajeResultado")
        $("#idMensajeResultadoMoneda").removeClass( "hidden" );
    	$("#idMensajeResultadoMoneda").fadeOut(6000);
    	$("[id='idFormCuentasPersonales:idComboMoneda']").focus().select();
    	$("[id='idFormCuentasPersonales:idComboMoneda']").addClass( "claseErrorFormulario");
    	event.preventDefault();
    } else if (idTxtNroCuenta == null || idTxtNroCuenta == ''){ 
    	$("#idMensajeResultadoNroCuenta").text("El número de cuenta es obligatorio.");
    	$("#idMensajeResultadoNroCuenta").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoNroCuenta").removeClass( "hidden" );
    	$("#idMensajeResultadoNroCuenta").fadeOut(6000);
    	$("[id='idFormCuentasPersonales:idTxtNroCuenta']").focus();
    	$("[id='idFormCuentasPersonales:idTxtNroCuenta']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idComboCuentaPropia == null || idComboCuentaPropia == '-1'){ 
    	$("#idMensajeResultadoCuentaPropia").text("Debe indicar si es una cuenta propia.");
    	$("#idMensajeResultadoCuentaPropia").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoCuentaPropia").removeClass( "hidden" );
    	$("#idMensajeResultadoCuentaPropia").fadeOut(6000);
    	$("[id='idFormCuentasPersonales:idComboCuentaPropia']").focus().select();
    	$("[id='idFormCuentasPersonales:idComboCuentaPropia']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idTxtAlias == null || idTxtAlias == ''){ 
    	$("#idMensajeResultadoAlias").text("El alias es obligatorio.");
    	$("#idMensajeResultadoAlias").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoAlias").removeClass( "hidden" );
    	$("#idMensajeResultadoAlias").fadeOut(6000);
    	$("[id='idFormCuentasPersonales:idTxtAlias']").focus();
    	$("[id='idFormCuentasPersonales:idTxtAlias']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else{
    	metodoRemotoEnviarFormulario();
    }
}

$(document).ready(function() {
	$(".botonCerrarSesion").find("span").remove();
});

function mostrarPopupCerrarSesion(){
	$('#idModalConfirmacionCerrarSesion').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionCerrarSesion').modal('show');
}

function operacionGuardarCuentaBancariaExitosa(){
	$('#idModalGuardarCuentaBancariaExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalGuardarCuentaBancariaExito').modal('show');
}