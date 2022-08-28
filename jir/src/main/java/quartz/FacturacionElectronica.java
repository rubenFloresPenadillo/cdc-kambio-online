package quartz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dto.TpFactuDocumCabecDto;
import dto.TpFactuDocumDetalDto;
import fecha.util.FechaUtil;
import loggerUtil.LoggerUtil;
import service.ServiceFacturacionElectronica;
import service.impl.ServiceFacturacionElectronicaImpl;
import util.constantes.Constants;
import util.types.CadenasType;
import util.types.EstadosInternosDocumentosFEType;
import util.types.NumerosType;

public class FacturacionElectronica implements Job{
	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		LoggerUtil.getInstance().getLogger().info("TAREA PARA LA FACTURACION ELECTRONICA, horass: "+new Date());
		
		ServiceFacturacionElectronica serviceFacturacionElectronica = new ServiceFacturacionElectronicaImpl();
		
		List<TpFactuDocumCabecDto> resultadoListaCabeceras = new LinkedList<TpFactuDocumCabecDto>() ;
		
		TpFactuDocumCabecDto documCabecDtoFiltro = new TpFactuDocumCabecDto();
		documCabecDtoFiltro.setCodEstaDocu(EstadosInternosDocumentosFEType.PENDIENTE_ENVIO.getIdElemento().shortValue());
		
		try {
			resultadoListaCabeceras = serviceFacturacionElectronica.getDocumentosPorEstado(documCabecDtoFiltro);
			
			LoggerUtil.getInstance().getLogger().info("Cantidad de Documentos a Procesar: "+resultadoListaCabeceras.size());

			for (TpFactuDocumCabecDto cabec : resultadoListaCabeceras) {
				
				  HttpClient cliente = new DefaultHttpClient();
		          HttpPost post = new HttpPost(Constants.FACTURACION_ELECTRONICA_RUTA);
		          post.addHeader("Authorization", "Token token=" + Constants.FACTURACION_ELECTRONICA_TOKEN); // Cabecera del token
		          post.addHeader("Content-Type","application/json"); // Cabecera del Content-Type
					
		          JSONObject objetoCabecera = new JSONObject(); // Instancear el  segundario
					
		          LoggerUtil.getInstance().getLogger().info("PROCESANDO: "+cabec.getSerie()+" - "+cabec.getNumero());
		          
				  objetoCabecera.put("operacion", "generar_comprobante");
			      objetoCabecera.put("tipo_de_comprobante", cabec.getTipoDeComprobante() );
			      objetoCabecera.put("serie", cabec.getSerie());
			      objetoCabecera.put("numero", cabec.getNumero());
			      objetoCabecera.put("sunat_transaction", cabec.getSunatTransaction());
			      objetoCabecera.put("cliente_tipo_de_documento", cabec.getClienteTipoDeDocumento());
			      objetoCabecera.put("cliente_numero_de_documento", cabec.getClienteNumeroDeDocumento());
			      objetoCabecera.put("cliente_denominacion", cabec.getClienteDenominacion());
			      objetoCabecera.put("cliente_direccion", cabec.getClienteDireccion());
			      objetoCabecera.put("cliente_email", cabec.getClienteEmail());
			      objetoCabecera.put("cliente_email_1", cabec.getClienteEmail1());
			      objetoCabecera.put("cliente_email_2", cabec.getClienteEmail2());
			      objetoCabecera.put("fecha_de_emision", FechaUtil.formatoFechaGuiones(cabec.getFechaDeEmision()));
			      objetoCabecera.put("fecha_de_vencimiento", cabec.getFechaDeVencimiento()  == null ? CadenasType.VACIO.getValor() : cabec.getFechaDeVencimiento() );
			      objetoCabecera.put("moneda", cabec.getMoneda());
			      objetoCabecera.put("tipo_de_cambio", cabec.getTipoDeCambio());
			      objetoCabecera.put("porcentaje_de_igv", cabec.getPorcentajeDeIgv());
			      objetoCabecera.put("descuento_global", cabec.getDescuentoGlobal() == null ? CadenasType.VACIO.getValor() : cabec.getDescuentoGlobal());
			      objetoCabecera.put("total_descuento", cabec.getTotalDescuento());
			      objetoCabecera.put("total_anticipo", cabec.getTotalAnticipo());
			      objetoCabecera.put("total_gravada", cabec.getTotalGravada() == null ? CadenasType.VACIO.getValor() : cabec.getTotalGravada());
			      objetoCabecera.put("total_inafecta", cabec.getTotalInafecta());
			      objetoCabecera.put("total_exonerada", cabec.getTotalExonerada());
			      objetoCabecera.put("total_igv", cabec.getTotalIgv() == null ? CadenasType.VACIO.getValor() : cabec.getTotalIgv());
			      objetoCabecera.put("total_gratuita", cabec.getTotalGratuita());
			      objetoCabecera.put("total_otros_cargos", cabec.getTotalOtrosCargos() == null ? CadenasType.VACIO.getValor() : cabec.getTotalOtrosCargos());
			      objetoCabecera.put("total", cabec.getTotal());
			      objetoCabecera.put("percepcion_tipo", cabec.getPercepcionTipo() == null ? CadenasType.VACIO.getValor() : cabec.getPercepcionTipo() );
			      objetoCabecera.put("percepcion_base_imponible", cabec.getPercepcionBaseImponible() == null ? CadenasType.VACIO.getValor() : cabec.getPercepcionBaseImponible());
			      objetoCabecera.put("total_percepcion", cabec.getTotalPercepcion());
			      objetoCabecera.put("total_incluido_percepcion", cabec.getTotalIncluidoPercepcion());
			      objetoCabecera.put("detraccion", cabec.getDetraccion() == NumerosType.INDICADOR_POSITIVO_UNO.getValor().shortValue() ? "true" : "false");
			      objetoCabecera.put("observaciones", cabec.getObservaciones());
			      objetoCabecera.put("documento_que_se_modifica_tipo", cabec.getDocumentoQueSeModificaTipo());
			      objetoCabecera.put("documento_que_se_modifica_serie", cabec.getDocumentoQueSeModificaSerie());
			      objetoCabecera.put("documento_que_se_modifica_numero", cabec.getDocumentoQueSeModificaNumero());
			      objetoCabecera.put("tipo_de_nota_de_credito", cabec.getTipoDeNotaDeCredito());
			      objetoCabecera.put("tipo_de_nota_de_debito", cabec.getTipoDeNotaDeDebito() == null ? CadenasType.VACIO.getValor() : cabec.getTipoDeNotaDeDebito() );
			      objetoCabecera.put("enviar_automaticamente_a_la_sunat", cabec.getEnviarAutomaticamenteALaSunat() == NumerosType.INDICADOR_POSITIVO_UNO.getValor().shortValue() ? "true" : "false");
			      objetoCabecera.put("enviar_automaticamente_al_cliente", cabec.getEnviarAutomaticamenteAlCliente() == NumerosType.INDICADOR_POSITIVO_UNO.getValor().shortValue() ? "true" : "false");
			      objetoCabecera.put("codigo_unico", cabec.getCodigoUnico());
			      objetoCabecera.put("condiciones_de_pago", cabec.getCondicionesDePago());
			      objetoCabecera.put("medio_de_pago", cabec.getMedioDePago()  == null ? CadenasType.VACIO.getValor() : cabec.getMedioDePago());
			      objetoCabecera.put("placa_vehiculo", cabec.getPlacaVehiculo());
			      objetoCabecera.put("orden_compra_servicio", cabec.getOrdenCompraServicio());
			      objetoCabecera.put("formato_de_pdf", cabec.getFormatoDePdf());
			            
				  ServiceFacturacionElectronica serviceFacturacionElectronicaDetal = new ServiceFacturacionElectronicaImpl();
				
				  List<TpFactuDocumDetalDto> resultadoListaDetalle = serviceFacturacionElectronicaDetal.getDetallePorCodCabecera(cabec.getCodFactDocuCabec());

				  LoggerUtil.getInstance().getLogger().info("DETALLE DE "+cabec.getSerie()+" - "+cabec.getNumero()+" , Cantidad: "+resultadoListaDetalle.size());
				  
				  JSONArray lista = new JSONArray();
	            
	              for (TpFactuDocumDetalDto temDetal : resultadoListaDetalle) {
	            	  
	                  JSONObject detalle_linea = new JSONObject();
	                  
	                  detalle_linea.put("unidad_de_medida", temDetal.getUnidadDeMedida());
	                  detalle_linea.put("codigo", temDetal.getCodigo());
	                  detalle_linea.put("descripcion", temDetal.getDescripcion());
	                  detalle_linea.put("cantidad", temDetal.getCantidad());
	                  detalle_linea.put("valor_unitario", temDetal.getValorUnitario());
	                  detalle_linea.put("precio_unitario", temDetal.getPrecioUnitario());
	                  detalle_linea.put("descuento",temDetal.getDescuento());
	                  detalle_linea.put("subtotal", temDetal.getSubtotal());
	                  detalle_linea.put("tipo_de_igv", temDetal.getTipoDeIgv());
	                  detalle_linea.put("igv", temDetal.getIgv());
	                  detalle_linea.put("total", temDetal.getTotal());
	                  detalle_linea.put("anticipo_regularizacion", temDetal.getAnticipoRegularizacion() == NumerosType.INDICADOR_POSITIVO_UNO.getValor().shortValue() ? "true" : "false");
	                  detalle_linea.put("anticipo_documento_serie", temDetal.getAnticipoDocumentoSerie());
	                  detalle_linea.put("anticipo_documento_numero", temDetal.getAnticipoDocumentoNumero());
	                  detalle_linea.put("codigo_producto_sunat", temDetal.getCodigoProductoSunat());
	                  
	                  lista.add(detalle_linea);
	            	  
	              }
	              
	              objetoCabecera.put("items", lista);
	             
	              StringEntity parametros = new StringEntity(objetoCabecera.toString() ,"UTF-8");
//	              System.out.println("parametros: "+parametros);
	              LoggerUtil.getInstance().getLogger().info("parametros: "+parametros);
	              LoggerUtil.getInstance().getLogger().info("json objetoCabecera: "+objetoCabecera.toString());	              
	              post.setEntity(parametros);
	              HttpResponse response = cliente.execute(post);   
	              BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));           
	              String linea = "";
	              
	              if((linea = rd.readLine()) != null) {
	                  
	                  JSONParser parsearRsptaJson = new JSONParser();
	                  JSONObject json_rspta = (JSONObject) parsearRsptaJson.parse(linea);
               
	                  if(json_rspta.get("errors") != null ){
	                      LoggerUtil.getInstance().getLogger().info("Error => " + json_rspta.get("errors"));
	                      LoggerUtil.getInstance().getLogger().info("Codigo => " + json_rspta.get("codigo"));
	                      
	                      cabec.setRepuestaErrors((String)json_rspta.get("errors"));
	                      cabec.setRepuestaCodigoError(String.valueOf(json_rspta.get("codigo")));
	                      
	                  }else{
	                      
	                      JSONParser parsearRsptaDetalleOK = new JSONParser();
	                      JSONObject json_rspta_ok = (JSONObject) parsearRsptaDetalleOK.parse(json_rspta.toString());
	                      
	                      LoggerUtil.getInstance().getLogger().info("tipo_de_comprobante: "+ json_rspta_ok.get("tipo_de_comprobante"));
	                      LoggerUtil.getInstance().getLogger().info("serie: "+json_rspta_ok.get("serie"));
	                      LoggerUtil.getInstance().getLogger().info("numero: "+json_rspta_ok.get("numero"));
	                      LoggerUtil.getInstance().getLogger().info("enlace: "+json_rspta_ok.get("enlace"));
	                      LoggerUtil.getInstance().getLogger().info("aceptada_por_sunat: "+json_rspta_ok.get("aceptada_por_sunat"));
	                      LoggerUtil.getInstance().getLogger().info("sunat_description: "+json_rspta_ok.get("sunat_description"));
	                      LoggerUtil.getInstance().getLogger().info("sunat_note: "+json_rspta_ok.get("sunat_note"));
	                      LoggerUtil.getInstance().getLogger().info("sunat_responsecode: "+json_rspta_ok.get("sunat_responsecode"));
	                      LoggerUtil.getInstance().getLogger().info("sunat_soap_error: "+json_rspta_ok.get("sunat_soap_error"));
	                      LoggerUtil.getInstance().getLogger().info("anulado: "+json_rspta_ok.get("anulado"));
	                      LoggerUtil.getInstance().getLogger().info("pdf_zip_base64: "+json_rspta_ok.get("pdf_zip_base64"));
	                      LoggerUtil.getInstance().getLogger().info("xml_zip_base64: "+json_rspta_ok.get("xml_zip_base64"));
	                      LoggerUtil.getInstance().getLogger().info("cdr_zip_base64: "+json_rspta_ok.get("cdr_zip_base64"));
	                      LoggerUtil.getInstance().getLogger().info("cadena_para_codigo_qr: "+json_rspta_ok.get("cadena_para_codigo_qr"));
	                      LoggerUtil.getInstance().getLogger().info("codigo_hash: "+json_rspta_ok.get("codigo_hash"));
	                      LoggerUtil.getInstance().getLogger().info("enlace_del_pdf: "+json_rspta_ok.get("enlace_del_pdf"));
	                      LoggerUtil.getInstance().getLogger().info("enlace_del_xml: "+json_rspta_ok.get("enlace_del_xml"));
	                      LoggerUtil.getInstance().getLogger().info("enlace_del_cdr: "+json_rspta_ok.get("enlace_del_cdr"));
	                      
	                      
	                      // Seteamos el objeto con la respuesta
	                      cabec.setRepuestaEnlace((String) json_rspta_ok.get("enlace"));
	                      cabec.setRepuestaAceptadaPorSunat(json_rspta_ok.get("aceptada_por_sunat") == "true" ? NumerosType.INDICADOR_POSITIVO_UNO.getValor().shortValue() : NumerosType.NUMERO_MINIMO_CERO.getValor().shortValue());
	                      cabec.setRepuestaSunatDescription((String) json_rspta_ok.get("sunat_description"));
	                      cabec.setRepuestaSunatNote((String) json_rspta_ok.get("sunat_note"));
	                      cabec.setRepuestaSunatResponsecode((String) json_rspta_ok.get("sunat_responsecode"));
	                      cabec.setRepuestaSunatSoapError((String) json_rspta_ok.get("sunat_soap_error"));
	                      cabec.setRepuestaAnulado(json_rspta_ok.get("anulado") == "true" ? NumerosType.INDICADOR_POSITIVO_UNO.getValor().shortValue() : NumerosType.NUMERO_MINIMO_CERO.getValor().shortValue()  );
	                      cabec.setRepuestaPdfZipBase64((String) json_rspta_ok.get("pdf_zip_base64"));
	                      cabec.setRepuestaXmlZipBase64((String) json_rspta_ok.get("xml_zip_base64"));
	                      cabec.setRepuestaCdrZipBase64((String) json_rspta_ok.get("cdr_zip_base64"));
	                      cabec.setRepuestaCadenaParaCodigoQr((String) json_rspta_ok.get("cadena_para_codigo_qr"));
	                      cabec.setRepuestaCodigoHash((String) json_rspta_ok.get("codigo_hash"));
	                      cabec.setRepuestaEnlaceDelPdf((String) json_rspta_ok.get("enlace_del_pdf"));
	                      cabec.setRepuestaEnlaceDelXml((String) json_rspta_ok.get("enlace_del_xml"));
	                      cabec.setRepuestaEnlaceDelCdr((String) json_rspta_ok.get("enlace_del_cdr"));

	                      
	                      cabec.setCodEstaDocu(EstadosInternosDocumentosFEType.ENVIADO.getIdElemento().shortValue());
	                  	  cabec.setFecModiRegi(new Date());
	                  	  cabec.setUsuApliModi("QUARTZFACTURACIONENVIOCPE");
	                  	  cabec.setRepuestaErrors(CadenasType.VACIO.getValor());
	                      cabec.setRepuestaCodigoError(CadenasType.VACIO.getValor());

	                  } 
	                  
	                  ServiceFacturacionElectronica serviceFacturacionElectronicaUpdate = new ServiceFacturacionElectronicaImpl();
	      				
    				  String resultadoUpdate = serviceFacturacionElectronicaUpdate.updateComprobante(cabec);
    				  
    				  LoggerUtil.getInstance().getLogger().info("TERMINA PROCESO ENVIO CPE: "+cabec.getSerie()+" - "+cabec.getNumero()+ " Resultado de Persistencia: "+resultadoUpdate);
	              } 
	              
	              
			}
			
		} catch (Exception e) {
			LoggerUtil.getInstance().getLogger().error(e);
			e.getStackTrace();
		}
		
		
		
		
	}
	
	

}
