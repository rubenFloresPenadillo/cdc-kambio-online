// A $( document ).ready() block.
$( document ).ready(function() {
	$('#modalBienvenido').modal('show');	
});

function operacionDatosGuardadosExito(){
	$('#idModalDatosGuardadosExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalDatosGuardadosExito').modal('show');
}

function operacionDatosActualizaExito(){
	$('#idModalDatosActualizaExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalDatosActualizaExito').modal('show');
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
	
	$("[id='idFormDatosEmpresa:idMensajeResultadoProceso']").css("display","none");
	
	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );
	
	$("#idMensajeResultadoRuc").empty();
	$("#idMensajeResultadoRuc").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoRuc").addClass( "hidden" );
	$("#idMensajeResultadoRuc").css("display","block");
	
	$("#idMensajeResultadoRSocial").empty();
	$("#idMensajeResultadoRSocial").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoRSocial").addClass( "hidden" );
	$("#idMensajeResultadoRSocial").css("display","block");
	
	$("#idMensajeResultadoNombrePerfil").empty();
	$("#idMensajeResultadoNombrePerfil").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoNombrePerfil").addClass( "hidden" );
	$("#idMensajeResultadoNombrePerfil").css("display","block");
	
//	$("#idMensajeResultadoEmail").empty();
//	$("#idMensajeResultadoEmail").removeClass( "alert alert-danger claseMensajeResultado" )
//    $("#idMensajeResultadoEmail").addClass( "hidden" );
//	$("#idMensajeResultadoEmail").css("display","block");
	
	$("#idMensajeResultadoSecEcono").empty();
	$("#idMensajeResultadoSecEcono").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoSecEcono").addClass( "hidden" );
	$("#idMensajeResultadoSecEcono").css("display","block");
	
	$("#idMensajeResultadoActEcono").empty();
	$("#idMensajeResultadoActEcono").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoActEcono").addClass( "hidden" );
	$("#idMensajeResultadoActEcono").css("display","block");
	
	/*Remueves los errores si es que hubieran*/
	
	$("[id='idFormDatosEmpresa:idTxtRuc']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosEmpresa:idTxtRazonSocial']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosEmpresa:idTxtNombrePerfil']").removeClass( "claseErrorFormulario" );
//	$("[id='idFormDatosEmpresa:txtEmailEmpresa']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosEmpresa:idComboSectorEconomico']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosEmpresa:idActividadEconomica']").removeClass( "claseErrorFormulario" );
	
	/*Captura valores*/
	
    var idTxtRuc = $("[id='idFormDatosEmpresa:idTxtRuc']").val();
    var idTxtRazonSocial = $("[id='idFormDatosEmpresa:idTxtRazonSocial']").val();
    var idTxtNombrePerfil = $("[id='idFormDatosEmpresa:idTxtNombrePerfil']").val();
//    var txtEmailEmpresa = $("[id='idFormDatosEmpresa:txtEmailEmpresa']").val();
    var idComboSectorEconomico = $("[id='idFormDatosEmpresa:idComboSectorEconomico']").val();
    var idComboActividadEconomica = $("[id='idFormDatosEmpresa:idComboActividadEconomica']").val();
    
    const regex = /^[0-9]*$/;
    const onlyNumbers = regex.test(`34563`); // true
    
    
    if (idTxtRuc == null || idTxtRuc == ''){
    	$("#idMensajeResultadoRuc").text("El RUC es obligatorio.");
    	$("#idMensajeResultadoRuc").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoRuc").removeClass( "hidden" );
    	$("#idMensajeResultadoRuc").fadeOut(6000);
    	$("[id='idFormDatosEmpresa:idTxtRuc']").focus();
    	$("[id='idFormDatosEmpresa:idTxtRuc']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if ( !(idTxtRuc.substr(0,2) == '10' || idTxtRuc.substr(0,2) == '20' || idTxtRuc.substr(0,2) == '15' || idTxtRuc.substr(0,2) == '17') ){
    	$("#idMensajeResultadoRuc").text("El RUC es invalido, verifique como inicia.");
    	$("#idMensajeResultadoRuc").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoRuc").removeClass( "hidden" );
    	$("#idMensajeResultadoRuc").fadeOut(6000);
    	$("[id='idFormDatosEmpresa:idTxtRuc']").focus();
    	$("[id='idFormDatosEmpresa:idTxtRuc']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if ( !regex.test(idTxtRuc) ){
    	$("#idMensajeResultadoRuc").text("El RUC es invalido, solo debe contener números.");
    	$("#idMensajeResultadoRuc").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoRuc").removeClass( "hidden" );
    	$("#idMensajeResultadoRuc").fadeOut(6000);
    	$("[id='idFormDatosEmpresa:idTxtRuc']").focus();
    	$("[id='idFormDatosEmpresa:idTxtRuc']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if ( idTxtRuc.length != 11 ){
    	$("#idMensajeResultadoRuc").text("El RUC es invalido, debe tener solo 11 digitos.");
    	$("#idMensajeResultadoRuc").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoRuc").removeClass( "hidden" );
    	$("#idMensajeResultadoRuc").fadeOut(6000);
    	$("[id='idFormDatosEmpresa:idTxtRuc']").focus();
    	$("[id='idFormDatosEmpresa:idTxtRuc']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idTxtRazonSocial == null || idTxtRazonSocial == ''){
    	$("#idMensajeResultadoRSocial").text("La Razon Social es obligatoria.");
    	$("#idMensajeResultadoRSocial").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoRSocial").removeClass( "hidden" );
    	$("#idMensajeResultadoRSocial").fadeOut(6000);
    	$("[id='idFormDatosEmpresa:idTxtRazonSocial']").focus();
    	$("[id='idFormDatosEmpresa:idTxtRazonSocial']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if(idTxtNombrePerfil == null || idTxtNombrePerfil == ''){
    	$("#idMensajeResultadoNombrePerfil").text("El Nombre de Perfil es obligatorio.");
    	$("#idMensajeResultadoNombrePerfil").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoNombrePerfil").removeClass( "hidden" );
    	$("#idMensajeResultadoNombrePerfil").fadeOut(6000);
    	$("[id='idFormDatosEmpresa:idTxtNombrePerfil']").focus();
    	$("[id='idFormDatosEmpresa:idTxtNombrePerfil']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idComboSectorEconomico == null || idComboSectorEconomico == '-1'){ 
    	$("#idMensajeResultadoSecEcono").text("El Sector Económico es obligatorio.");
    	$("#idMensajeResultadoSecEcono").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoSecEcono").removeClass( "hidden" );
    	$("#idMensajeResultadoSecEcono").fadeOut(6000);
    	$("[id='idFormDatosEmpresa:idComboSectorEconomico']").focus().select();
    	$("[id='idFormDatosEmpresa:idComboSectorEconomico']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idComboActividadEconomica == null || idComboActividadEconomica == '-1'){ 
    	$("#idComboActividadEconomica").text("La Actividad Económica es obligatoria.");
    	$("#idComboActividadEconomica").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idComboActividadEconomica").removeClass( "hidden" );
    	$("#idComboActividadEconomica").fadeOut(6000);
    	$("[id='idFormDatosEmpresa:idComboActividadEconomica']").focus().select();
    	$("[id='idFormDatosEmpresa:idComboActividadEconomica']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else{
    	mostrarPopupConfirmaDatosEmpresa();
    }
}

function mostrarPopupConfirmaDatosEmpresa(){
	$('#idModalConfirmacionDatosEmpresa').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionDatosEmpresa').modal('show');

}

$(document).ready(function() {
	$(".botonCerrarSesion").find("span").remove();
});

function mostrarPopupCerrarSesion(){
	$('#idModalConfirmacionCerrarSesion').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionCerrarSesion').modal('show');
}

