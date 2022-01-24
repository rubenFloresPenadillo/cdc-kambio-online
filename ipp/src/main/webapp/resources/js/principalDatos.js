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

function enviar(event) {
	
	$("[id='idFormDatosPersonales:idMensajeResultadoProceso']").css("display","none");
	
	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );
	
	$("#idMensajeResultadoNombre1").empty();
	$("#idMensajeResultadoNombre1").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoNombre1").addClass( "hidden" );
	$("#idMensajeResultadoNombre1").css("display","block");
	
	$("#idMensajeResultadoApellido1").empty();
	$("#idMensajeResultadoApellido1").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoApellido1").addClass( "hidden" );
	$("#idMensajeResultadoApellido1").css("display","block");
	
	$("#idMensajeResultadoApellido2").empty();
	$("#idMensajeResultadoApellido2").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoApellido2").addClass( "hidden" );
	$("#idMensajeResultadoApellido2").css("display","block");
	
	$("#idMensajeResultadoTipoDocumento").empty();
	$("#idMensajeResultadoTipoDocumento").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoTipoDocumento").addClass( "hidden" );
	$("#idMensajeResultadoTipoDocumento").css("display","block");
	
	$("#idMensajeResultadoDocumento").empty();
	$("#idMensajeResultadoDocumento").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoDocumento").addClass( "hidden" );
	$("#idMensajeResultadoDocumento").css("display","block");
	
	$("#idMensajeResultadoFechaNacimiento").empty();
	$("#idMensajeResultadoFechaNacimiento").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoFechaNacimiento").addClass( "hidden" );
	$("#idMensajeResultadoFechaNacimiento").css("display","block");
	
	$("#idMensajeResultadoTelefono").empty();
	$("#idMensajeResultadoTelefono").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoTelefono").addClass( "hidden" );
	$("#idMensajeResultadoTelefono").css("display","block");
	
	$("#idMensajeResultadoNacionalidad").empty();
	$("#idMensajeResultadoNacionalidad").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoNacionalidad").addClass( "hidden" );
	$("#idMensajeResultadoNacionalidad").css("display","block");	
	
	$("#idMensajeResultadoPais").empty();
	$("#idMensajeResultadoPais").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoPais").addClass( "hidden" );
	$("#idMensajeResultadoPais").css("display","block");		
	
	$("#idMensajeResultadoDepartamento").empty();
	$("#idMensajeResultadoDepartamento").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoDepartamento").addClass( "hidden" );
	$("#idMensajeResultadoDepartamento").css("display","block");	
	
	$("#idMensajeResultadoProvincia").empty();
	$("#idMensajeResultadoProvincia").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoProvincia").addClass( "hidden" );
	$("#idMensajeResultadoProvincia").css("display","block");
	
	$("#idMensajeResultadoDistrito").empty();
	$("#idMensajeResultadoDistrito").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoDistrito").addClass( "hidden" );
	$("#idMensajeResultadoDistrito").css("display","block");
	
	$("#idMensajeResultadoDireccion").empty();
	$("#idMensajeResultadoDireccion").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoDireccion").addClass( "hidden" );
	$("#idMensajeResultadoDireccion").css("display","block");
	
	$("#idMensajeResultadoInstitucion").empty();
	$("#idMensajeResultadoInstitucion").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoInstitucion").addClass( "hidden" );
	$("#idMensajeResultadoInstitucion").css("display","block");	
	
	$("#idMensajeResultadoRol").empty();
	$("#idMensajeResultadoRol").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoRol").addClass( "hidden" );
	$("#idMensajeResultadoRol").css("display","block");
	
	/*Remueves los errores si es que hubieran*/
	
	$("[id='idFormDatosPersonales:idTxtNombre1']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosPersonales:idTxtApellido1']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosPersonales:idTxtApellido2']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosPersonales:idTipoDocumento']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosPersonales:idTxtDocumento']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosPersonales:idFechaNacDia']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosPersonales:idFechaNacMes']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosPersonales:idFechaNacAnio']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosPersonales:idComboNacionalidad']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosPersonales:idComboPais']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosPersonales:idComboDepartamento']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosPersonales:idComboProvincia']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosPersonales:idComboDistrito']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosPersonales:idTxtDireccion']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosPersonales:idTxtTelefono']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosPersonales:idTxtInstitucion']").removeClass( "claseErrorFormulario" );
	$("[id='idFormDatosPersonales:idTxtRol']").removeClass( "claseErrorFormulario" );
	
	/*Captura valores*/
	
    var idTxtNombre1 = $("[id='idFormDatosPersonales:idTxtNombre1']").val();
    var idTxtApellido1 = $("[id='idFormDatosPersonales:idTxtApellido1']").val();
    var idTxtApellido2 = $("[id='idFormDatosPersonales:idTxtApellido2']").val();
    var idTipoDocumento = $("[id='idFormDatosPersonales:idTipoDocumento']").val();
    var idTxtDocumento = $("[id='idFormDatosPersonales:idTxtDocumento']").val();
    var idFechaNacDia = $("[id='idFormDatosPersonales:idFechaNacDia']").val();
    var idFechaNacMes = $("[id='idFormDatosPersonales:idFechaNacMes']").val();
    var idFechaNacAnio = $("[id='idFormDatosPersonales:idFechaNacAnio']").val();
    var idComboNacionalidad = $("[id='idFormDatosPersonales:idComboNacionalidad']").val();
    var idComboPais = $("[id='idFormDatosPersonales:idComboPais']").val();
    var idComboDepartamento = $("[id='idFormDatosPersonales:idComboDepartamento']").val();
    var idComboProvincia = $("[id='idFormDatosPersonales:idComboProvincia']").val();
    var idComboDistrito = $("[id='idFormDatosPersonales:idComboDistrito']").val();
    var idTxtDireccion = $("[id='idFormDatosPersonales:idTxtDireccion']").val();
    var idTxtTelefono = $("[id='idFormDatosPersonales:idTxtTelefono']").val();
    var idTxtInstitucion = $("[id='idFormDatosPersonales:idTxtInstitucion']").val();
    var idTxtRol = $("[id='idFormDatosPersonales:idTxtRol']").val();
    
    if (idTxtNombre1 == null || idTxtNombre1 == ''){
    	$("#idMensajeResultadoNombre1").text("El primer nombre es obligatorio.");
    	$("#idMensajeResultadoNombre1").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoNombre1").removeClass( "hidden" );
    	$("#idMensajeResultadoNombre1").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idTxtNombre1']").focus();
    	$("[id='idFormDatosPersonales:idTxtNombre1']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idTxtApellido1 == null || idTxtApellido1 == ''){
    	$("#idMensajeResultadoApellido1").text("El primer apellido es obligatorio.");
    	$("#idMensajeResultadoApellido1").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoApellido1").removeClass( "hidden" );
    	$("#idMensajeResultadoApellido1").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idTxtApellido1']").focus();
    	$("[id='idFormDatosPersonales:idTxtApellido1']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if(idTxtApellido2 == null || idTxtApellido2 == ''){
    	$("#idMensajeResultadoApellido2").text("El segundo apellido es obligatorio.");
    	$("#idMensajeResultadoApellido2").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoApellido2").removeClass( "hidden" );
    	$("#idMensajeResultadoApellido2").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idTxtApellido2']").focus();
    	$("[id='idFormDatosPersonales:idTxtApellido2']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idTipoDocumento == null || idTipoDocumento == '-1'){ 
    	$("#idMensajeResultadoTipoDocumento").text("El tipo de documento es obligatorio.");
    	$("#idMensajeResultadoTipoDocumento").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoTipoDocumento").removeClass( "hidden" );
    	$("#idMensajeResultadoTipoDocumento").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idTipoDocumento']").focus().select();
    	$("[id='idFormDatosPersonales:idTipoDocumento']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idTxtDocumento == null || idTxtDocumento == ''){ 
    	$("#idMensajeResultadoDocumento").text("El documento es obligatorio.");
    	$("#idMensajeResultadoDocumento").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoDocumento").removeClass( "hidden" );
    	$("#idMensajeResultadoDocumento").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idTxtDocumento']").focus();
    	$("[id='idFormDatosPersonales:idTxtDocumento']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idFechaNacDia == null || idFechaNacDia == '-1'){ 
    	$("#idMensajeResultadoFechaNacimiento").text("El día es obligatorio.");
    	$("#idMensajeResultadoFechaNacimiento").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoFechaNacimiento").removeClass( "hidden" );
    	$("#idMensajeResultadoFechaNacimiento").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idFechaNacDia']").focus().select();
    	$("[id='idFormDatosPersonales:idFechaNacDia']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idFechaNacMes == null || idFechaNacMes == '-1'){ 
    	$("#idMensajeResultadoFechaNacimiento").text("El mes es obligatorio.");
    	$("#idMensajeResultadoFechaNacimiento").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoFechaNacimiento").removeClass( "hidden" );
    	$("#idMensajeResultadoFechaNacimiento").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idFechaNacMes']").focus().select();
    	$("[id='idFormDatosPersonales:idFechaNacMes']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if (idFechaNacAnio == null || idFechaNacAnio == '-1'){ 
    	$("#idMensajeResultadoFechaNacimiento").text("El año es obligatorio.");
    	$("#idMensajeResultadoFechaNacimiento").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoFechaNacimiento").removeClass( "hidden" );
    	$("#idMensajeResultadoFechaNacimiento").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idFechaNacAnio']").focus().select();
    	$("[id='idFormDatosPersonales:idFechaNacAnio']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idComboNacionalidad == null || idComboNacionalidad == '-1'){ 
    	$("#idMensajeResultadoNacionalidad").text("La nacionalidad es obligatoria.");
    	$("#idMensajeResultadoNacionalidad").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoNacionalidad").removeClass( "hidden" );
    	$("#idMensajeResultadoNacionalidad").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idComboNacionalidad']").focus().select();
    	$("[id='idFormDatosPersonales:idComboNacionalidad']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idComboPais == null || idComboPais == '-1'){ 
    	$("#idMensajeResultadoPais").text("El país es obligatorio.");
    	$("#idMensajeResultadoPais").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoPais").removeClass( "hidden" );
    	$("#idMensajeResultadoPais").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idComboPais']").focus().select();
    	$("[id='idFormDatosPersonales:idComboPais']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if ($("[id='idFormDatosPersonales:idComboDepartamento']").length && (idComboDepartamento == null || idComboDepartamento == '-1')){ 
    	$("#idMensajeResultadoDepartamento").text("El departamento es obligatorio.");
    	$("#idMensajeResultadoDepartamento").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoDepartamento").removeClass( "hidden" );
    	$("#idMensajeResultadoDepartamento").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idComboDepartamento']").focus().select();
    	$("[id='idFormDatosPersonales:idComboDepartamento']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if ($("[id='idFormDatosPersonales:idComboProvincia']").length && (idComboProvincia == null || idComboProvincia == '-1')){ 
    	$("#idMensajeResultadoProvincia").text("La provincia es obligatoria.");
    	$("#idMensajeResultadoProvincia").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoProvincia").removeClass( "hidden" );
    	$("#idMensajeResultadoProvincia").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idComboProvincia']").focus().select();
    	$("[id='idFormDatosPersonales:idComboProvincia']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if ($("[id='idFormDatosPersonales:idComboDistrito']").length && (idComboDistrito == null || idComboDistrito == '-1')){ 
    	$("#idMensajeResultadoDistrito").text("El distrito es obligatorio.");
    	$("#idMensajeResultadoDistrito").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoDistrito").removeClass( "hidden" );
    	$("#idMensajeResultadoDistrito").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idComboDistrito']").focus().select();
    	$("[id='idFormDatosPersonales:idComboDistrito']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idTxtDireccion == null || idTxtDireccion == ''){
    	$("#idMensajeResultadoDireccion").text("La dirección es obligatoria.");
    	$("#idMensajeResultadoDireccion").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoDireccion").removeClass( "hidden" );
    	$("#idMensajeResultadoDireccion").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idTxtDireccion']").focus();
    	$("[id='idFormDatosPersonales:idTxtDireccion']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if (idTxtTelefono == null || idTxtTelefono == ''){ 
    	$("#idMensajeResultadoTelefono").text("El teléfono es obligatorio.");
    	$("#idMensajeResultadoTelefono").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoTelefono").removeClass( "hidden" );
    	$("#idMensajeResultadoTelefono").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idTxtTelefono']").focus();
    	$("[id='idFormDatosPersonales:idTxtTelefono']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if ($("[id='idFormDatosPersonales:idTxtInstitucion']").length && (idTxtInstitucion == null || idTxtInstitucion == '')){ 
    	$("#idMensajeResultadoInstitucion").text("La institución es obligatoria.");
    	$("#idMensajeResultadoInstitucion").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoInstitucion").removeClass( "hidden" );
    	$("#idMensajeResultadoInstitucion").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idTxtInstitucion']").focus();
    	$("[id='idFormDatosPersonales:idTxtInstitucion']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    }else if ($("[id='idFormDatosPersonales:idTxtRol']").length && (idTxtRol == null || idTxtRol == '')){ 
    	$("#idMensajeResultadoRol").text("El rol es obligatorio.");
    	$("#idMensajeResultadoRol").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeResultadoRol").removeClass( "hidden" );
    	$("#idMensajeResultadoRol").fadeOut(6000);
    	$("[id='idFormDatosPersonales:idTxtRol']").focus();
    	$("[id='idFormDatosPersonales:idTxtRol']").addClass( "claseErrorFormulario" );
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

//function myFunction() {
//	  // Get the checkbox
//	  var checkBox = document.getElementById("idInputPep");
//
//	  // If the checkbox is checked, display the output text
//	  if (checkBox.checked == true){
//		  alert('valor checked');
//	  } else {
//		  alert('valor NO');
//	  }
//	}

$(function() {
    $('#idInputPep').change(function() {
//    	alert('valor '+$(this).prop('checked'));
    	var idIndicadorPep = document.getElementById("idFormDatosPersonales:idIndicadorPep");
    	var idTxtInstitucion = document.getElementById("idFormDatosPersonales:idTxtInstitucion");
    	var idTxtRol = document.getElementById("idFormDatosPersonales:idTxtRol");
    	
    	if($("[id='idFormDatosPersonales:idTxtInstitucion']").length ){
    		idTxtInstitucion.value = "";
    	}
    	
    	if($("[id='idFormDatosPersonales:idTxtRol']").length){
    		idTxtRol.value = "";
    	}

    	if($(this).prop('checked')){
    		idIndicadorPep.value = "1";
    	}else{
    		idIndicadorPep.value = "0";
    	}
    	
    	metodoRemotoDatosPepo();
      //$('#console-event').html('Toggle: ' + $(this).prop('checked'))
    })
  })