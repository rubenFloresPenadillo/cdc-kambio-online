ejecutoIncremento = false;  

$(document).ready(function() {
	$("#modalComo").on('hidden.bs.modal', function (e) {
	    $("#modalComo iframe").attr("src", $("#modalComo iframe").attr("src"));
	});
	
//	$("#idBlog").css("display","none");
	
//	var windowTop = $(document).scrollTop();
//	
//	var windowBottom = windowTop + window.innerHeight;
//	
//	var elementPositionTop = $("#respaldo").offset().top;
//	
//	var elementPositionBottom = elementPositionTop + $("#respaldo").height();
//	
//	var alturaElemeto = $("#respaldo").height();
//	
//	console.log("windowTop: "+windowTop);
//	console.log("windowBottom: "+windowBottom);
//	console.log("elementPositionTop: "+elementPositionTop);
//	console.log("elementPositionBottom: "+elementPositionBottom);
//	console.log("alturaElemeto: "+alturaElemeto);
	
});

function elementVisible(element) {
	var visible = true;
	var windowTop = $(document).scrollTop();
	var windowBottom = windowTop + window.innerHeight;
	var elementPositionTop = element.offset().top;
	var elementPositionBottom = elementPositionTop + element.height();
	if (elementPositionTop >= windowBottom || elementPositionBottom <= windowTop) {
	visible = false;
	}
	return visible;
}

//$(window).scroll(function (evt) {  
//	if(elementVisible($("#respaldo")) && !window.ejecutoIncremento){
//		
//		window.ejecutoIncremento = true;
//		var valor1 = $("#valor1").text();
//		var valor2 = $("#valor2").text();
//		var valor3 = $("#valor3").text();
//		
//		conteo(evt,valor1,$("#valor1"));
//		conteo(evt,valor2,$("#valor2"));
//		conteo(evt,valor3,$("#valor3"));
//	}
//	
//});


function conteo(evt, value, obj){
	var $el = obj; 
//    value = 89;

	evt.preventDefault();
	
	$({percentage: 0}).stop(true).animate({percentage: value}, {
	    duration : 3500,
	    easing: "easeOutExpo",
	    step: function () {
	        // percentage with 1 decimal;
	        var percentageVal = Math.round(this.percentage);
	        $el.text(percentageVal);
	    }
	}).promise().done(function () {
	    $el.text(value);
	});
}



function countA(){
	  var counter = { var: 0 };
	  var tm = TweenMax.to(counter, 2, {
	    var: 2000, 
	    onUpdate: function () {
	      var number = Math.ceil(counter.var);
	      $('.counter').html(number);
	      if(number === counter.var){ 
	    	 alert('entro kill');
//	    	 return false;
	    	  TweenMax.killAll();
	      }
	    },
	    onComplete: function(){
	    	alert('entro onComplete');
	    	countA();
	    },    
	    ease:Circ.easeOut
	    	
	    
	  });
}

function enlaceWhatsapp() {
	 var win = window.open('https://api.whatsapp.com/send?phone=51943395368&text=Hola,%20solicito%20información%20acerca%20de%20', '_blank');
	 win.focus();  
}



function calcularValorEnvio(){
	
	var mostrarCompra = document.getElementById("idFormPrincipal:idMostrarCompra").value;
	
	var isTrueMostrarCompra = (mostrarCompra == 'true');
	
	if(isTrueMostrarCompra){
		var dataSuperior = parseFloat($('.inputSuperior').val());
		var dataTipoCambioCompra = parseFloat($('.claseCompra').text());
		var resultado = dataSuperior * dataTipoCambioCompra;
		if(!isNaN(resultado)){
			$('.inputInferior').val(Math.round(resultado * 100) / 100);
		}else{
			$('.inputInferior').val('0');
		}
		
	}else{
//		alert('entro al else');
		var dataSuperior = parseFloat($('.inputSuperior').val());
		var dataTipoCambioVenta = parseFloat($('.claseVenta').text());
		var resultado = dataSuperior / dataTipoCambioVenta;
		if(!isNaN(resultado)){
			$('.inputInferior').val(Math.round(resultado * 100) / 100);
		}else{
			$('.inputInferior').val('0');
		}
	}
	
}

function calcularValorRecibo(){
	
	var mostrarCompra = document.getElementById("idFormPrincipal:idMostrarCompra").value;
	
	var isTrueMostrarCompra = (mostrarCompra == 'true');
	
	if(isTrueMostrarCompra){
		var dataInferior = parseFloat($('.inputInferior').val());
		var dataTipoCambioCompra = parseFloat($('.claseCompra').text());
		var resultado = dataInferior / dataTipoCambioCompra;
		if(!isNaN(resultado)){
			$('.inputSuperior').val(Math.round(resultado * 100) / 100);
		}else{
			$('.inputSuperior').val('0');
		}
	}else{
		var dataInferior = parseFloat($('.inputInferior').val());
		var dataTipoCambioVenta = parseFloat($('.claseVenta').text());
		var resultado = dataInferior * dataTipoCambioVenta;
		if(!isNaN(resultado)){
			$('.inputSuperior').val(Math.round(resultado * 100) / 100);
		}else{
			$('.inputSuperior').val('0');
		}
	}

	
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
	
//	$("[id='idFormPrincipal:idMensajeResultadoProceso']").css("display","none");
	
	$("#idMensajeResultado").empty(); // elimina el contenido del div
	$("#idMensajeResultado").removeClass( "alert alert-danger" )
    $("#idMensajeResultado").addClass( "hidden" );
	
	$("#idMensajeCorreoElectronico").empty();
	$("#idMensajeCorreoElectronico").removeClass( "alert alert-danger claseMensajeResultado" )
    $("#idMensajeCorreoElectronico").addClass( "hidden" );
	$("#idMensajeCorreoElectronico").css("display","block");
	
	/*Remueves los errores si es que hubieran*/
	
	$("[id='idFormPrincipal:idTxtCorreoElectronico']").removeClass( "claseErrorFormulario");
	
	/*Captura valores*/

    var idTxtCorreoElectronico = $("[id='idFormPrincipal:idTxtCorreoElectronico']").val();
    
    if(!idTxtCorreoElectronico.trim()){
    	$("#idMensajeCorreoElectronico").text("El correo es obligatorio.");
    	$("#idMensajeCorreoElectronico").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeCorreoElectronico").removeClass( "hidden" );
    	$("#idMensajeCorreoElectronico").fadeOut(6000);
    	$("[id='idFormPrincipal:idTxtCorreoElectronico']").focus().select();
    	$("[id='idFormPrincipal:idTxtCorreoElectronico']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if(!validarCorreo(idTxtCorreoElectronico)){
    	$("#idMensajeCorreoElectronico").text("El correo no es válido.");
    	$("#idMensajeCorreoElectronico").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeCorreoElectronico").removeClass( "hidden" );
    	$("#idMensajeCorreoElectronico").fadeOut(6000);
    	$("[id='idFormPrincipal:idTxtCorreoElectronico']").focus().select();
    	$("[id='idFormPrincipal:idTxtCorreoElectronico']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else if(validarContieneEspacio(idTxtCorreoElectronico)){
    	$("#idMensajeCorreoElectronico").text("El correo no puede contener espacios en blanco.");
    	$("#idMensajeCorreoElectronico").addClass( "alert alert-danger claseMensajeResultado" )
        $("#idMensajeCorreoElectronico").removeClass( "hidden" );
    	$("#idMensajeCorreoElectronico").fadeOut(6000);
    	$("[id='idFormPrincipal:idTxtCorreoElectronico']").focus().select();
    	$("[id='idFormPrincipal:idTxtCorreoElectronico']").addClass( "claseErrorFormulario" );
    	event.preventDefault();
    } else{
    	ejecutarEnviarSuscripcion();
    }
}

function operacionEnviarSucripcionExitosa(){
	$('#idModalEnviarSuscripcionExito').modal({backdrop: 'static', keyboard: false})  
	$('#idModalEnviarSuscripcionExito').modal('show');
}

function procesoConError(){
	$('#idModalErrorSistema').modal({backdrop: 'static', keyboard: false})  
	$('#idModalErrorSistema').modal('show');
}