package mangedBean.opciones;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import file.util.FileUtil;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import cadenas.util.ValidacionesString;
import dto.TpCuentBancoDto;
import dto.TpFactuDocumCabecDto;
import dto.TpFactuDocumDetalDto;
import dto.TpOperaClienDto;
import file.util.FileUtil;
import hibernate.entidades.TpFactuDocumDetal;
import loggerUtil.LoggerUtil;
import managedThread.CorreoEnvioHilo;
import numeros.util.ValidacionesNumeros;
import service.ServiceCliente;
import service.ServiceFacturacionElectronica;
import service.ServiceOperacionCliente;
import service.impl.ServiceClienteImpl;
import service.impl.ServiceFacturacionElectronicaImpl;
import service.impl.ServiceOperacionClienteImpl;
import util.constantes.Constants;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.ElementosTablasType;
import util.types.EstadosInternosDocumentosFEType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;
import util.types.PlantillasType;
import util.types.RegistroActivoType;
import util.types.RutasBaseType;
import util.types.TipoDocumentoElectronicoType;
import util.types.TipoOperacionCambioType;

@ManagedBean(name="operacionesControlItemBean")
@ViewScoped
public class OperacionesControlItemBean {

	private String resultadoProcesoExito;
	private String resultadoProcesoError;
	private TpOperaClienDto operacionControlItem;
	private String valorNombre;
	private String ideUsuaEmai;
	private Boolean indMostrarVistaFinalizar;
	private Boolean indMostrarListaCambiarEstado;
	private Integer estadoFuturoOperacion;
//	private List<SelectItem> listaComboEstadoCuentaBancariaAdmin;
//	private List<SelectItem> listaComboDivisas;
//	private List<SelectItem> listaComboBancos;
//	private List<SelectItem> listaComboTipoCuentas;
//	private List<SelectItem> listaComboIndiTransfeBancarias;
//	private List<SelectItem> listaComboIndiCuentaPropia;
	
	private List<SelectItem> listaComboNuevoEstado;
	private String generaImagenGrafica;
	
	private Boolean indicadorModoConsulta;
	public OperacionesControlItemBean() {
		
		HttpSession sesion = ConeccionSesion.getSession();
		ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
		
		operacionControlItem = new TpOperaClienDto();
		indicadorModoConsulta = Boolean.FALSE;
		resultadoProcesoExito = CadenasType.VACIO.getValor();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		generaImagenGrafica = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor OperacionesControlItemBean");
	}
	
    /**
     * Realiza su ejecuciï¿½n despues del Constructor de la clase OperacionesControlBean.
     */
    @PostConstruct
    public void init() {
    	
    	HttpSession sesion = ConeccionSesion.getSession();
    	TpOperaClienDto operacionItemSeleccionado = (TpOperaClienDto) sesion.getAttribute("operacionItemSeleccionado");
		Boolean indicadorModoConsultaTemporal = (Boolean) sesion.getAttribute("indicadorModoConsulta");
//		sesion.removeAttribute("operacionItemSeleccionado");
//		sesion.removeAttribute("indicadorModoConsulta");
		
		indMostrarVistaFinalizar = Boolean.TRUE;
		indMostrarListaCambiarEstado = Boolean.TRUE;
	
		if (indicadorModoConsultaTemporal!=null) {
			indicadorModoConsulta = indicadorModoConsultaTemporal;
			indMostrarListaCambiarEstado = Boolean.FALSE;
		}

		if(operacionItemSeleccionado != null) {
			operacionControlItem = operacionItemSeleccionado;
			if( operacionControlItem.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OPERACION_VERIFICACION.getIdElemento())){
				estadoFuturoOperacion = ElementosTablasType.ESTADO_OPERACION_FINALIZADA.getIdElemento();
				indMostrarVistaFinalizar = Boolean.TRUE;
			}else if(operacionControlItem.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OPERACION_INICIADA.getIdElemento())) {
				estadoFuturoOperacion = ElementosTablasType.ESTADO_OPERACION_CANCELADA_OPERACION.getIdElemento();
				indMostrarVistaFinalizar = Boolean.FALSE;
			}else if(operacionControlItem.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OPERACION_CANCELADA_AUTOMATICA.getIdElemento()) ||
					operacionControlItem.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OPERACION_CANCELADA_OPERACION.getIdElemento()) ||
					operacionControlItem.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OPERACION_CANCELADA_CLIENTE.getIdElemento()) ) {
				indMostrarVistaFinalizar = Boolean.FALSE;
			}
			
			ServiceCliente serviceClienteDesde = new ServiceClienteImpl();
        	TpCuentBancoDto cuentaBancoClienDesde= serviceClienteDesde.getCuentaBanco(operacionControlItem.getTpCuentBancoByCodCuenBancClieOrig());
        	operacionControlItem.setTpCuentBancoByCodCuenBancClieOrig(cuentaBancoClienDesde);
        	
        	ServiceCliente serviceClienteCome = new ServiceClienteImpl();
        	TpCuentBancoDto cuentaBancoClienEnvi = serviceClienteCome.getCuentaBanco(operacionControlItem.getTpCuentBancoByCodCuenBancCome());
        	operacionControlItem.setTpCuentBancoByCodCuenBancCome(cuentaBancoClienEnvi);      	
        	
        	ServiceCliente serviceClienteReci = new ServiceClienteImpl();
        	TpCuentBancoDto cuentaBancoClienReci = serviceClienteReci.getCuentaBanco(operacionControlItem.getTpCuentBancoByCodCuenBancClieReci());
        	operacionControlItem.setTpCuentBancoByCodCuenBancClieReci(cuentaBancoClienReci);
        	
		} 
		
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
    	
    	getListaCargarListaComboEstadosNuevos();
    	asignarValoresDinamicos();
    	cargarImagenConstancia();
    	
    }

    public void cargarImagenConstancia() {
       if(!ValidacionesString.esNuloOVacio(operacionControlItem.getRutImagTranBanc())) {
    	   File targetFile = new File(RutasBaseType.RUTA_BASE_CONTANCIAS_OPERACIONES.getValor()+operacionControlItem.getCodUnicOperClie()+CadenasType.SLASH.getValor()+operacionControlItem.getRutImagTranBanc());
    	   Path path = targetFile.toPath();
    	   String mimeType;
    		try {
    			mimeType = Files.probeContentType(path);
    			generaImagenGrafica = "data:"+mimeType+";base64,"+FileUtil.fileToBase64(targetFile);	
    		} catch (IOException e) {
    			LoggerUtil.getInstance().getLogger().error("Ocurrio un error al mostrar el archivo: "+e.getMessage());
    			
    		}
    	   
       }
    }
    
	public void getListaCargarListaComboEstadosNuevos() {
    	listaComboNuevoEstado = new ArrayList<SelectItem>();
    	
    	if(!operacionControlItem.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OPERACION_INICIADA.getIdElemento())){
    		listaComboNuevoEstado.add(new SelectItem(ElementosTablasType.ESTADO_OPERACION_FINALIZADA.getIdElemento(), ElementosTablasType.ESTADO_OPERACION_FINALIZADA.getNombreElemento()));
    	}
    	listaComboNuevoEstado.add(new SelectItem(ElementosTablasType.ESTADO_OPERACION_CANCELADA_OPERACION.getIdElemento(), ElementosTablasType.ESTADO_OPERACION_CANCELADA_OPERACION.getNombreElemento()));
    	
	}

//	public void getListasDivisas() {
//		
//		listaComboDivisas = new ArrayList<SelectItem>();
//		
//		ServiceDivisa serviceDivisa = new ServiceDivisaImpl();
//
//		for(TpDivisDto temp : serviceDivisa.getDivisas(null)) {
//			listaComboDivisas.add(new SelectItem(temp.getCodDivi(), temp.getNomDiviSing()));
//		}
//
//	}
//	
//	public void getListaBancos() {
//		
//		listaComboBancos = new ArrayList<SelectItem>();
//		
//		ServiceBanco serviceBanco = new ServiceBancoImpl();
//		
//		TpBancoDto tpBancoDto = new TpBancoDto();
//
//		tpBancoDto.setIndVistAdmi( NumerosType.INDICADOR_POSITIVO_UNO.getValor());
//		
//		for(TpBancoDto temp : serviceBanco.getBancosDisponibles(tpBancoDto)) {
//			listaComboBancos.add(new SelectItem(temp.getCodBanc(), temp.getNomBanc()));
//		}
//
//	}
//	
//	
//	public void getListaTipoCuentas() {
//		
//		listaComboTipoCuentas = new ArrayList<SelectItem>();
//		
//		ServiceBanco serviceBanco = new ServiceBancoImpl();
//
//		for(TpTipoCuentDto temp : serviceBanco.getTipoCuentasBacarias(null)) { 
//			listaComboTipoCuentas.add(new SelectItem(temp.getCodTipoCuen(), temp.getDesTipoCuen()));
//		}
//
//	}
//	
//	public void getListaEstadosCuentasBancariasAdmin() {
//		listaComboEstadoCuentaBancariaAdmin = new ArrayList<SelectItem>();
//		listaComboEstadoCuentaBancariaAdmin.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
//		listaComboEstadoCuentaBancariaAdmin.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
//	}
	
//	public void procesarGuardarOperaControlItem() {
//		
//    	System.out.println("Entro a validar");
//    	String result = null;
        
//    	ServiceCliente serviceCliente = new ServiceClienteImpl();
//    	if(ValidacionesString.esNuloOVacio(operacionControlItem.getUsuApliCrea())) {
//        	operacionControlItem.setUsuApliCrea(String.valueOf(ideUsuaEmai));
//        	operacionControlItem.setFecCreaRegi(new Date());
//        	operacionControlItem.setIndCuenProp(RegistroActivoType.ACTIVO.getLlave());
//    	}
//    	operacionControlItem.getTpClien().setCodClie(NumerosType.INDICADOR_POSITIVO_UNO.getValor());
//    	operacionControlItem.setUsuApliModi(String.valueOf(ideUsuaEmai));
//    	operacionControlItem.setFecModiRegi(new Date());
//    	result = serviceCliente.insertUpdateCuentaBanco(operacionControlItem);
    	

    	
//        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
//
//        	operacionControlItem = new TpOperaClienDto();
//        	PrimeFaces.current().executeScript("operacionGuardarOperaControlItemExitoso();");
//        }else {
//        	resultadoProcesoError = result;
//        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
//        	PrimeFaces.current().executeScript("procesoConError();");
//        }
//
//    }

//	public void mostrarDialogConfirmacionFinalizarOperacion(TpOperaClienDto tpOperaClienDto) {
//    	operacionControlItem = tpOperaClienDto;
//    	System.out.println("Se debe cambiar el estado de: "+tpOperaClienDto.getCodOperClie());
//    	PrimeFaces.current().executeScript("mostrarPopupConfirmaFinalizar();");
//
//    }
//    
//    public void mostrarDialogConfirmacionCancelarOperacion(TpOperaClienDto tpOperaClienDtoCancelar) {
//    	operacionControlItem = tpOperaClienDtoCancelar;
//    	System.out.println("Se debe cancelar la operacion: "+tpOperaClienDtoCancelar.getCodOperClie());
//    	PrimeFaces.current().executeScript("mostrarPopupConfirmaCancelar();");
//    }
    
    
    public void procesarMostrarDialogConfirmacionCanceOpera() {
    	indMostrarVistaFinalizar = Boolean.FALSE;
    	PrimeFaces.current().executeScript("mostrarPopupConfirmaCancelar();");
    }
    
    public void procesarMostrarDialogConfirmacionFinOpera() {
    	
    	resultadoProcesoError = CadenasType.VACIO.getValor();
    	
    	if(operacionControlItem!=null && !ValidacionesString.esNuloOVacio(operacionControlItem.getNumOperBancCome())) {
    		indMostrarVistaFinalizar = Boolean.TRUE;
            PrimeFaces.current().executeScript("mostrarPopupConfirmaFinalizar();");
        }else {
        	resultadoProcesoError = "El número de operación del comercio es obligatorio, verifique por favor.";
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().executeScript("procesoConError();");
        }
    		
    	
    }
    
    
    
    
    public void asignarValoresDinamicos() {
    	if(ElementosTablasType.ESTADO_OPERACION_FINALIZADA.getIdElemento().equals(estadoFuturoOperacion)) {
    		indMostrarVistaFinalizar = Boolean.TRUE;
    		
    	}else if (ElementosTablasType.ESTADO_OPERACION_CANCELADA_OPERACION.getIdElemento().equals(estadoFuturoOperacion) ){
    		indMostrarVistaFinalizar = Boolean.FALSE;
    	}
    }
    
    public void procesarFinalizarOperacion() {
    	
    	resultadoProcesoError = CadenasType.VACIO.getValor();
    	
    	if(operacionControlItem!=null) {
//    		System.out.println("Ejecutar"+operacionControlItem.getCodOperClie());
    		
    		ServiceOperacionCliente serviceOperacionCliente = new ServiceOperacionClienteImpl();

    		operacionControlItem.getTpEstadOpera().setCodEstaOper(ElementosTablasType.ESTADO_OPERACION_FINALIZADA.getIdElemento());
    		operacionControlItem.getTpEstadOpera().setDesEstaOper(ElementosTablasType.ESTADO_OPERACION_FINALIZADA.getNombreElemento());
        	operacionControlItem.setUsuApliModi(ideUsuaEmai);
        	operacionControlItem.setFecModiRegi(new Date());
    		operacionControlItem.setUsuApliFinaOper(ideUsuaEmai);
        	operacionControlItem.setFecFinaOper(new Date());
    		
//        	String result = "OK";
        	String result = serviceOperacionCliente.actualizarEstadoOperacionCliente(operacionControlItem);

        	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        		
        		try {
        			grabaDatosFacturacionElectronica();
				} catch (Exception e) {
					LoggerUtil.getInstance().getLogger().error(e);
					e.getStackTrace();
				}
        		
        		
        		indicadorModoConsulta = Boolean.TRUE;
        		indMostrarListaCambiarEstado = Boolean.FALSE;
        		
        		PrimeFaces.current().executeScript("operacionFinalizadaExitosa();");
        		
        		LoggerUtil.getInstance().getLogger().info("Iniciando Hilo Principal");

    			CorreoEnvioHilo hiloEnvioOperacionesControlItemBean1 = new CorreoEnvioHilo(PlantillasType.PLANTILLA_ENVIAR_FINALIZO_OPERACION , "hiloEnvioOperacionesControlItemBean1", operacionControlItem.getTpClien().getTpUsuar().getIdeUsuaEmai() , operacionControlItem.getTpClien().getValPrimNombPers(), operacionControlItem.getCodUnicOperClie(), null);
    			 
    			Thread nuevoHiloEnvioCorreo = new Thread(hiloEnvioOperacionesControlItemBean1);
    			 
    			nuevoHiloEnvioCorreo.start();

    			LoggerUtil.getInstance().getLogger().info("Finalizando Hilo Principal");
    			
        	}else {
        		resultadoProcesoError = result;
        		LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        		PrimeFaces.current().executeScript("procesoConError();");
        	}
    		
    	}
    }
    
    public void grabaDatosFacturacionElectronica() {
    	
    	TpFactuDocumCabecDto tpFactuDocumCabecDto = new TpFactuDocumCabecDto();
    	
    	String tipoDocumentoExterno = CadenasType.VACIO.getValor();
    	String valorDocumento = CadenasType.VACIO.getValor();
    	String valorNombreDenominacion = CadenasType.VACIO.getValor();
    	short tipoComprobanteAGenerar = NumerosType.NUMERO_MINIMO_CERO.getValor().shortValue();
    	String desCortTipoDocuElec = CadenasType.VACIO.getValor();
    	
    	if (ElementosTablasType.TIPO_DOCUMENTO_PERSONA_DNI.getIdElemento().equals(operacionControlItem.getTpClien().getTpTipoDocumPerso().getCodTipoDocuPers())) {
    		tipoDocumentoExterno = Constants.FACTURACION_ELECTRONICA_TIPO_DOCUMENTO_DNI; 
    		valorDocumento = operacionControlItem.getTpClien().getValDocuPers();
    		valorNombreDenominacion = operacionControlItem.getTpClien().getValNombreCompleto();
    		tipoComprobanteAGenerar = Constants.FACTURACION_ELECTRONICA_TIPO_COMPROBANTE_BOLETA;
    		desCortTipoDocuElec = TipoDocumentoElectronicoType.BOLETA.getCodigoCorto();
    		
    	}else if (ElementosTablasType.TIPO_DOCUMENTO_PERSONA_RUC.getIdElemento().equals(operacionControlItem.getTpClien().getTpTipoDocumPerso().getCodTipoDocuPers())) {
    		tipoDocumentoExterno = Constants.FACTURACION_ELECTRONICA_TIPO_DOCUMENTO_RUC; 
    		valorDocumento = operacionControlItem.getTpClien().getValDocuEmpr();
    		valorNombreDenominacion = operacionControlItem.getTpClien().getValRazoSociPers();
    		tipoComprobanteAGenerar = Constants.FACTURACION_ELECTRONICA_TIPO_COMPROBANTE_FACTURA;
    		desCortTipoDocuElec = TipoDocumentoElectronicoType.FACTURA.getCodigoCorto(); 
    	}
    	
		tpFactuDocumCabecDto.setCodOperClie(operacionControlItem.getCodOperClie());
		tpFactuDocumCabecDto.setTipoDeComprobante(tipoComprobanteAGenerar);  // revisar, debe ser de acuerdo al perfil
		tpFactuDocumCabecDto.setClienteTipoDeDocumento(tipoDocumentoExterno);
		tpFactuDocumCabecDto.setClienteNumeroDeDocumento(valorDocumento);
		tpFactuDocumCabecDto.setClienteDenominacion(valorNombreDenominacion);
		tpFactuDocumCabecDto.setClienteDireccion(operacionControlItem.getTpClien().getValDirePers());
		tpFactuDocumCabecDto.setClienteEmail(operacionControlItem.getTpClien().getTpUsuar().getIdeUsuaEmai());
//		tpFactuDocumCabecDto.setClienteEmail1(tpFactuDocumCabecDto.getClienteEmail1());
//		tpFactuDocumCabecDto.setClienteEmail2(tpFactuDocumCabecDto.getClienteEmail2());
		tpFactuDocumCabecDto.setFechaDeEmision(new Date());
//		tpFactuDocumCabecDto.setFechaDeVencimiento(tpFactuDocumCabecDto.getFechaDeVencimiento());
//		tpFactuDocumCabecDto.setTipoDeCambio(tpFactuDocumCabecDto.getTipoDeCambio());
//		tpFactuDocumCabecDto.setDescuentoGlobal(tpFactuDocumCabecDto.getDescuentoGlobal());
//		tpFactuDocumCabecDto.setTotalDescuento(tpFactuDocumCabecDto.getTotalDescuento());
//		tpFactuDocumCabecDto.setTotalAnticipo(tpFactuDocumCabecDto.getTotalAnticipo());
//		tpFactuDocumCabecDto.setTotalGravada(tpFactuDocumCabecDto.getTotalGravada());
		tpFactuDocumCabecDto.setTotalInafecta(operacionControlItem.getMonReci().doubleValue());
//		tpFactuDocumCabecDto.setTotalExonerada(tpFactuDocumCabecDto.getTotalExonerada());
//		tpFactuDocumCabecDto.setTotalIgv(tpFactuDocumCabecDto.getTotalIgv());
//		tpFactuDocumCabecDto.setTotalGratuita(tpFactuDocumCabecDto.getTotalGratuita());
//		tpFactuDocumCabecDto.setTotalOtrosCargos(tpFactuDocumCabecDto.getTotalOtrosCargos());
//		tpFactuDocumCabecDto.setTotalIsc(tpFactuDocumCabecDto.getTotalIsc());
		tpFactuDocumCabecDto.setTotal(operacionControlItem.getMonReci().doubleValue());
//		tpFactuDocumCabecDto.setPercepcionTipo(tpFactuDocumCabecDto.getPercepcionTipo());
//		tpFactuDocumCabecDto.setPercepcionBaseImponible(tpFactuDocumCabecDto.getPercepcionBaseImponible());
//		tpFactuDocumCabecDto.setTotalPercepcion(tpFactuDocumCabecDto.getTotalPercepcion());
//		tpFactuDocumCabecDto.setTotalIncluidoPercepcion(tpFactuDocumCabecDto.getTotalIncluidoPercepcion());
//		tpFactuDocumCabecDto.setObservaciones(tpFactuDocumCabecDto.getObservaciones());
//		tpFactuDocumCabecDto.setDocumentoQueSeModificaTipo(tpFactuDocumCabecDto.getDocumentoQueSeModificaTipo());
//		tpFactuDocumCabecDto.setDocumentoQueSeModificaSerie(tpFactuDocumCabecDto.getDocumentoQueSeModificaSerie());
//		tpFactuDocumCabecDto.setDocumentoQueSeModificaNumero(tpFactuDocumCabecDto.getDocumentoQueSeModificaNumero());
//		tpFactuDocumCabecDto.setTipoDeNotaDeCredito(tpFactuDocumCabecDto.getTipoDeNotaDeCredito());
//		tpFactuDocumCabecDto.setTipoDeNotaDeDebito(tpFactuDocumCabecDto.getTipoDeNotaDeDebito());
//		tpFactuDocumCabecDto.setCodigoUnico(tpFactuDocumCabecDto.getCodigoUnico());
//		tpFactuDocumCabecDto.setCondicionesDePago(tpFactuDocumCabecDto.getCondicionesDePago());
//		tpFactuDocumCabecDto.setMedioDePago(tpFactuDocumCabecDto.getMedioDePago());
//		tpFactuDocumCabecDto.setPlacaVehiculo(tpFactuDocumCabecDto.getPlacaVehiculo());
//		tpFactuDocumCabecDto.setOrdenCompraServicio(tpFactuDocumCabecDto.getOrdenCompraServicio());
		
		tpFactuDocumCabecDto.setCodEstaDocu(EstadosInternosDocumentosFEType.PENDIENTE_ENVIO.getIdElemento().shortValue());
		tpFactuDocumCabecDto.setDesCortDocuGene(desCortTipoDocuElec);
		tpFactuDocumCabecDto.setFecCreaRegi(new Date());
		tpFactuDocumCabecDto.setIndEsta(RegistroActivoType.ACTIVO.getLlave().shortValue());
		tpFactuDocumCabecDto.setUsuApliCrea(String.valueOf(ideUsuaEmai));
		
		
    	
    	
		TpFactuDocumDetalDto tpFactuDocumDetalDto = new TpFactuDocumDetalDto();
		
		tpFactuDocumDetalDto.setTpFactuDocumCabec(tpFactuDocumCabecDto);
		tpFactuDocumDetalDto.setCodigo(NumerosType.NUMERO_MINIMO_CERO.getValor().equals(operacionControlItem.getIndCompVent()) ? TipoOperacionCambioType.COMPRA_DOLARES.getCodigo() : TipoOperacionCambioType.VENTA_DOLARES.getCodigo() );
		tpFactuDocumDetalDto.setDescripcion(NumerosType.NUMERO_MINIMO_CERO.getValor().equals(operacionControlItem.getIndCompVent()) ? 
				TipoOperacionCambioType.COMPRA_DOLARES.getDescripcion()+CadenasType.GUION.getValor()+operacionControlItem.getNumOperBancCome() : 
					TipoOperacionCambioType.VENTA_DOLARES.getDescripcion()+CadenasType.GUION.getValor()+operacionControlItem.getNumOperBancCome() );
		tpFactuDocumDetalDto.setCantidad(operacionControlItem.getMonEnvi().doubleValue());
		tpFactuDocumDetalDto.setValorUnitario(operacionControlItem.getValTipoCambUsad());
		tpFactuDocumDetalDto.setPrecioUnitario(operacionControlItem.getValTipoCambUsad());
//		tpFactuDocumDetalDto.setDescuento(tpFactuDocumDetalDto.getDescuento());
		tpFactuDocumDetalDto.setSubtotal(operacionControlItem.getMonReci().doubleValue());
		tpFactuDocumDetalDto.setTotal(operacionControlItem.getMonReci().doubleValue());
//		tpFactuDocumDetalDto.setAnticipoDocumentoSerie(tpFactuDocumDetalDto.getAnticipoDocumentoSerie());
//		tpFactuDocumDetalDto.setAnticipoDocumentoNumero(tpFactuDocumDetalDto.getAnticipoDocumentoNumero());
//		tpFactuDocumDetalDto.setCodigoProductoSunat(tpFactuDocumDetalDto.getCodigoProductoSunat());
		tpFactuDocumDetalDto.setFecCreaRegi(new Date());
		tpFactuDocumDetalDto.setIndEsta(RegistroActivoType.ACTIVO.getLlave().shortValue());
		tpFactuDocumDetalDto.setUsuApliCrea(String.valueOf(ideUsuaEmai));
		
		List<TpFactuDocumDetalDto> listTpFactuDocumDetalDto = new ArrayList<TpFactuDocumDetalDto>();
		listTpFactuDocumDetalDto.add(tpFactuDocumDetalDto);
		
		
		ServiceFacturacionElectronica serviceFacturacionElectronica = new ServiceFacturacionElectronicaImpl();
    	String result = serviceFacturacionElectronica.generarComprobanteElectronico(tpFactuDocumCabecDto, listTpFactuDocumDetalDto);

    	LoggerUtil.getInstance().getLogger().info("Registro comprobante: "+result);
		
    }
    
    
    
    
    public void procesarCancelarOperacion() {
    	
    	resultadoProcesoError = CadenasType.VACIO.getValor();
    	
    	if(operacionControlItem !=null) {
    		
        	ServiceOperacionCliente serviceOperacionCliente = new ServiceOperacionClienteImpl();

        	operacionControlItem.getTpEstadOpera().setCodEstaOper(ElementosTablasType.ESTADO_OPERACION_CANCELADA_OPERACION.getIdElemento());
        	operacionControlItem.getTpEstadOpera().setDesEstaOper(ElementosTablasType.ESTADO_OPERACION_CANCELADA_OPERACION.getNombreElemento());
        	operacionControlItem.setUsuApliModi(ideUsuaEmai);
        	operacionControlItem.setFecModiRegi(new Date());
    		operacionControlItem.setUsuApliCancOper(ideUsuaEmai);
        	operacionControlItem.setFecCancOper(new Date());

        	String result = serviceOperacionCliente.actualizarEstadoOperacionCliente(operacionControlItem);

        	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {

//        		getOperacionesCliente();
        		indicadorModoConsulta = Boolean.TRUE;
        		indMostrarListaCambiarEstado = Boolean.FALSE;
        		
        		PrimeFaces.current().executeScript("operacionCanceladaExitosa();");
        		
        		LoggerUtil.getInstance().getLogger().info("Iniciando Hilo Principal");

    			CorreoEnvioHilo hiloEnvioOperacionesControlBean2 = new CorreoEnvioHilo(PlantillasType.PLANTILLA_ENVIAR_CANCELA_OPERACION , "hiloEnvioOperacionesControlBean2", operacionControlItem.getTpClien().getTpUsuar().getIdeUsuaEmai() , operacionControlItem.getTpClien().getValPrimNombPers(), operacionControlItem.getCodUnicOperClie(), null);
    			 
    			Thread nuevoHiloEnvioCorreo = new Thread(hiloEnvioOperacionesControlBean2);
    			 
    			nuevoHiloEnvioCorreo.start();

    			LoggerUtil.getInstance().getLogger().info("Finalizando Hilo Principal");
    			
        	}else {
        		resultadoProcesoError = result;
        		LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        		PrimeFaces.current().executeScript("procesoConError();");
        	}
    		
    	}

    }
    
	public void regresar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_OPERACIONES_CONTROL.getValor());
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
	}
	
    public void procesarCerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_INDEX_PERSONAL.getValor());
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }

	public String getResultadoProcesoExito() {
		return resultadoProcesoExito;
	}

	public void setResultadoProcesoExito(String resultadoProcesoExito) {
		this.resultadoProcesoExito = resultadoProcesoExito;
	}

	public String getResultadoProcesoError() {
		return resultadoProcesoError;
	}

	public void setResultadoProcesoError(String resultadoProcesoError) {
		this.resultadoProcesoError = resultadoProcesoError;
	}

	public TpOperaClienDto getOperacionControlItem() {
		return operacionControlItem;
	}

	public void setOperacionControlItem(TpOperaClienDto operacionControlItem) {
		this.operacionControlItem = operacionControlItem;
	}

	public String getValorNombre() {
		return valorNombre;
	}

	public void setValorNombre(String valorNombre) {
		this.valorNombre = valorNombre;
	}

	public Boolean getIndicadorModoConsulta() {
		return indicadorModoConsulta;
	}

	public void setIndicadorModoConsulta(Boolean indicadorModoConsulta) {
		this.indicadorModoConsulta = indicadorModoConsulta;
	}

	public List<SelectItem> getListaComboNuevoEstado() {
		return listaComboNuevoEstado;
	}

	public void setListaComboNuevoEstado(List<SelectItem> listaComboNuevoEstado) {
		this.listaComboNuevoEstado = listaComboNuevoEstado;
	}

	public Boolean getIndMostrarVistaFinalizar() {
		return indMostrarVistaFinalizar;
	}

	public void setIndMostrarVistaFinalizar(Boolean indMostrarVistaFinalizar) {
		this.indMostrarVistaFinalizar = indMostrarVistaFinalizar;
	}

	public Integer getEstadoFuturoOperacion() {
		return estadoFuturoOperacion;
	}

	public void setEstadoFuturoOperacion(Integer estadoFuturoOperacion) {
		this.estadoFuturoOperacion = estadoFuturoOperacion;
	}

	public Boolean getIndMostrarListaCambiarEstado() {
		return indMostrarListaCambiarEstado;
	}

	public void setIndMostrarListaCambiarEstado(Boolean indMostrarListaCambiarEstado) {
		this.indMostrarListaCambiarEstado = indMostrarListaCambiarEstado;
	}

	public String getGeneraImagenGrafica() {
		return generaImagenGrafica;
	}

	public void setGeneraImagenGrafica(String generaImagenGrafica) {
		this.generaImagenGrafica = generaImagenGrafica;
	}

	
    
	
}
