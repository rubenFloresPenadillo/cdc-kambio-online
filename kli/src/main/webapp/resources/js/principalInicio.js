$(document).ready(function() {
	$(".botonCerrarSesion").find("span").remove();
	$('#idModalRegistrarCuenta').modal('show');
	$('#idModalFueraDeHorario').modal('show');

});

function enlaceWhatsappVerificacion() {
	 var win = window.open('https://api.whatsapp.com/send?phone=51981319152&text=Hola,%20adjunto%20mi%20comprobante%20', '_blank');
	 win.focus();  
}

function operacionVerificacionExitosa(){
	$('#modalVerificacionEviada').modal({backdrop: 'static', keyboard: false})  
	$('#modalVerificacionEviada').modal('show');
}

function operacionVerificacionError(){
	$('#modalVerificacionError').modal({backdrop: 'static', keyboard: false})  
	$('#modalVerificacionError').modal('show');
}

function operacionSeleccionarCuentaError(){
	$('#modalSelecionarCuentaError').modal({backdrop: 'static', keyboard: false})  
	$('#modalSelecionarCuentaError').modal('show');
}

function seleccionarTabCompra() {
	document.getElementById("idFormIniciaOperacion:idIndicadorCompraVenta").value = "0";
	$("#idTabCompra").addClass( "activoVisual" );
	$("#idTabVenta").removeClass( "activoVisual" );
}

function seleccionarTabVenta() {
	document.getElementById("idFormIniciaOperacion:idIndicadorCompraVenta").value = "1";
	$("#idTabVenta").addClass( "activoVisual" );
	$("#idTabCompra").removeClass( "activoVisual" );
}

function rotarVistaCambio() {
	if ($( "#idTabVenta" ).hasClass( "activoVisual" )){
		$( "#idTabCompra" ).trigger( "click" );
	}else if($( "#idTabCompra" ).hasClass( "activoVisual" )){
		$( "#idTabVenta" ).trigger( "click" );
	}
}

function enviar(event) {
	
	$("[id='idFormIniciaOperacion:idMensajeResultadoProceso']").css("display","none");
//	
//	$("#idMensajeResultado").empty(); // elimina el contenido del div
//	$("#idMensajeResultado").removeClass( "alert alert-danger" )
//    $("#idMensajeResultado").addClass( "hidden" );

	$("#idMensajeResultadoCuentaBancariaE").empty();
	$("#idMensajeResultadoCuentaBancariaE").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoCuentaBancariaE").addClass( "hidden" );
	$("#idMensajeResultadoCuentaBancariaE").css("display","block");
	
	$("#idMensajeResultadoCuentaBancariaR").empty();
	$("#idMensajeResultadoCuentaBancariaR").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoCuentaBancariaR").addClass( "hidden" );
	$("#idMensajeResultadoCuentaBancariaR").css("display","block");
	
	$("#idMensajeResultadoOrigenFondos").empty();
	$("#idMensajeResultadoOrigenFondos").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeResultadoOrigenFondos").addClass( "hidden" );
	$("#idMensajeResultadoOrigenFondos").css("display","block");	
	
	
	/*Remueves los errores si es que hubieran*/
	
	$("[id='idFormIniciaOperacion:idComboCuentaBancariaE']").removeClass( "claseErrorFormulario");
	$("[id='idFormIniciaOperacion:idComboCuentaBancariaR']").removeClass( "claseErrorFormulario");
	$("[id='idFormIniciaOperacion:idComboOrigenFondo']").removeClass( "claseErrorFormulario");
	
	/*Captura valores*/
	
    var idComboCuentaBancariaE = $("[id='idFormIniciaOperacion:idComboCuentaBancariaE']").val();
    var idComboCuentaBancariaR = $("[id='idFormIniciaOperacion:idComboCuentaBancariaR']").val();
    var idComboOrigenFondo = $("[id='idFormIniciaOperacion:idComboOrigenFondo']").val();
	
    if (idComboCuentaBancariaE == null || idComboCuentaBancariaE == '-1'){ 
    	$("#idMensajeResultadoCuentaBancariaE").text("Debes seleccionar la cuenta de donde nos enviaras tu dinero.");
    	$("#idMensajeResultadoCuentaBancariaE").addClass( "alert alert-danger claseMensajeResultado")
        $("#idMensajeResultadoCuentaBancariaE").removeClass( "hidden" );
    	$("#idMensajeResultadoCuentaBancariaE").fadeOut(6000);
    	$("[id='idFormIniciaOperacion:idComboCuentaBancariaE']").focus().select();
    	$("[id='idFormIniciaOperacion:idComboCuentaBancariaE']").addClass( "claseErrorFormulario");
    	event.preventDefault();
    } else if (idComboCuentaBancariaR == null || idComboCuentaBancariaR == '-1'){ 
    	$("#idMensajeResultadoCuentaBancariaR").text("Debes seleccionar la cuenta a donde te enviaremos tu dinero.");
    	$("#idMensajeResultadoCuentaBancariaR").addClass( "alert alert-danger claseMensajeResultado")
        $("#idMensajeResultadoCuentaBancariaR").removeClass( "hidden" );
    	$("#idMensajeResultadoCuentaBancariaR").fadeOut(6000);
    	$("[id='idFormIniciaOperacion:idComboCuentaBancariaR']").focus().select();
    	$("[id='idFormIniciaOperacion:idComboCuentaBancariaR']").addClass( "claseErrorFormulario");
    	event.preventDefault();
    } else if (idComboOrigenFondo !== undefined && (idComboOrigenFondo == null || idComboOrigenFondo == '-1') ){ 
    	$("#idMensajeResultadoOrigenFondos").text("Debes seleccionar un origen de fondos.");
    	$("#idMensajeResultadoOrigenFondos").addClass( "alert alert-danger claseMensajeResultado")
        $("#idMensajeResultadoOrigenFondos").removeClass( "hidden" );
    	$("#idMensajeResultadoOrigenFondos").fadeOut(6000);
    	$("[id='idFormIniciaOperacion:idComboOrigenFondo']").focus().select();
    	$("[id='idFormIniciaOperacion:idComboOrigenFondo']").addClass( "claseErrorFormulario");
    	event.preventDefault();
    } else{
    	metodoRemotoEnviarFormulario();
    	window.scrollTo(0,0);
    }
}

function mostrarPopupCerrarSesion(){
	$('#idModalConfirmacionCerrarSesion').modal({backdrop: 'static', keyboard: false})  
	$('#idModalConfirmacionCerrarSesion').modal('show');

}


function getInputSelection(el) {
    var start = 0, end = 0, normalizedValue, range,
        textInputRange, len, endRange;

    alert('parametro' + el.value);
    
    
    if (typeof el.selectionStart == "number" && typeof el.selectionEnd == "number") {
        start = el.selectionStart;
        end = el.selectionEnd;
    } else {
        range = document.selection.createRange();

        if (range && range.parentElement() == el) {
            len = el.value.length;
            normalizedValue = el.value.replace(/\r\n/g, "\n");

            // Create a working TextRange that lives only in the input
            textInputRange = el.createTextRange();
            textInputRange.moveToBookmark(range.getBookmark());

            // Check if the start and end of the selection are at the very end
            // of the input, since moveStart/moveEnd doesn't return what we want
            // in those cases
            endRange = el.createTextRange();
            endRange.collapse(false);

            if (textInputRange.compareEndPoints("StartToEnd", endRange) > -1) {
                start = end = len;
            } else {
                start = -textInputRange.moveStart("character", -len);
                start += normalizedValue.slice(0, start).split("\n").length - 1;

                if (textInputRange.compareEndPoints("EndToEnd", endRange) > -1) {
                    end = len;
                } else {
                    end = -textInputRange.moveEnd("character", -len);
                    end += normalizedValue.slice(0, end).split("\n").length - 1;
                }
            }
        }
    }

    return {
        start: start,
        end: end
    };
}


function alineaNumeroA(){
	var idInputSuperior = document.getElementById("idFormIniciaOperacion:idInputSuperior");
	var valor = idInputSuperior.value;
	
	if(valor.includes(",")){
		const valoresCadena = valor.split(',');
		idInputSuperior.value = Number(valoresCadena[0]);
		metodoAlineaNumeroA();
	}else{
		idInputSuperior.value = Number(idInputSuperior.value);
	}
}


function alineaNumeroB(){
	var idInputInferior = document.getElementById("idFormIniciaOperacion:idInputInferior");
	var valor = idInputInferior.value;
	
	if(valor.includes(",")){
		const valoresCadena = valor.split(',');
		idInputInferior.value = Number(valoresCadena[0]);
		metodoAlineaNumeroB();
	}else{
		idInputInferior.value = Number(idInputInferior.value);
	}
}
