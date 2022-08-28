ejecutoIncremento = false;  

$(document).ready(function() {
	$("#modalComo").on('hidden.bs.modal', function (e) {
	    $("#modalComo iframe").attr("src", $("#modalComo iframe").attr("src"));
	});
	
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



function enlaceWhatsapp() {
	 var win = window.open('https://api.whatsapp.com/send?phone=51992278011&text=Hola,%20solicito%20información%20acerca%20de%20', '_blank');
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


