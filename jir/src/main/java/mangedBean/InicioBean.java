package mangedBean;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.primefaces.model.file.UploadedFile;

import cadenas.util.ValidacionesString;
import dto.TpBancoDto;
import dto.TpCuentBancoDto;
import dto.TpCuponDto;
import dto.TpOperaClienDto;
import dto.TpOrigeFondoDto;
import dto.TpParamGenerDto;
import dto.TpTipoCambiDto;
import dto.TpUsuarDto;
import loggerUtil.LoggerUtil;
import managedThread.CorreoEnvioHilo;
import numeros.util.ValidacionesNumeros;
import service.ServiceBanco;
import service.ServiceCliente;
import service.ServiceCupon;
import service.ServiceOperacionCliente;
import service.ServiceOrigenFondo;
import service.ServiceParametroGeneral;
import service.ServiceTipoCambio;
import service.ServiceUsuario;
import service.impl.ServiceBancoImpl;
import service.impl.ServiceClienteImpl;
import service.impl.ServiceCuponImpl;
import service.impl.ServiceOperacionClienteImpl;
import service.impl.ServiceOrigenFondoImpl;
import service.impl.ServiceParametroGeneralImpl;
import service.impl.ServiceTipoCambioImpl;
import service.impl.ServiceUsuarioImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.DivisaType;
import util.types.ElementosTablasType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;
import util.types.ParametroGeneralType;
import util.types.PerfilesType;
import util.types.PlantillasType;
import util.types.RegistroActivoType;

@ManagedBean(name="inicioBean")
@ViewScoped
public class InicioBean {

	private static final int CANTIDAD_ENVIO_INICIAL = 100;
	private static final String BASE_CODIGOS = "123456789abcdefghijklmnopqrstuvwxyz";
	private static final String MOTIVO_CANCELACION_CLIENTE_DESDE_REALIZAR_TRANSFERENCIA = "Desistio desde realiza la transferencia";
	private static final Integer LONGITUD_CODIGO = 7;
	private String resultadoProcesoError;
	private Double tipoCambioCompraDolar;
	private Double tipoCambioVentaDolar;
	private Double tipoCambioUsado;
	private BigDecimal cantidadEnvio;
	private BigDecimal cantidadRecibo;
	private Boolean mostrarCompra;
	private String cadenaDivisaCantidadEnvio;
	private String cadenaDivisaCantidadRecibo;
	private String cadenaDivisaCantidadEnvioInformacion;
	private String cadenaDivisaCantidadReciboInformacion;
	private TpOperaClienDto operacionClienteFormulario;
	private TpCuentBancoDto cuentaBancoAdminDetalle;
	private TpCuentBancoDto cuentaClienteUsoDetalle;
	
	private List<SelectItem> listaComboBancosEnvioDinero;
	private List<SelectItem> listaComboCuentasBancariasReci;
	private List<SelectItem> listaComboCuentasBancariasEnvi;	
	private List<SelectItem> listaComboOrigenFondo;	
	private List<TpCuentBancoDto> listaCuentasBancariasTemporal;
	private List<TpOrigeFondoDto> listaComboOrigenFondoTemporal;
	private Integer valorTiempoLimite;
	
	
	/*Flag para mostrar*/
	private Boolean mostrarCuentas;
	private Boolean mostrartransferencia;
	private Boolean mostrarVerificacion;
	private String valorNombre;
	private String usuario;
	private Integer indCompleDatos;
	private Integer indFueraDeHorario;
	private Integer codigoUsuario;
	private Integer codigoUsuarioPadre;	
	private Integer codigoCliente;
	private Integer codOperClie;
	private Integer codEstaOper;
	private Integer codPerfUsua;
	private String ideUsuaEmai;
	private Integer indRegistroCuentaBancaria;
	private Boolean mostrarMensajeCCI;
	private Boolean mostrarMensajeClienteCCI;
	private String mensajeHorarioAtencion;
	private Boolean indHabilitarOrigenFondo;
	private Boolean indDatosEmpresa;
	private Boolean indAplicoCupon;
	private Boolean mostrarCampoOperacion;
	private Boolean mostrarCampoSubeImagenOperacion;
	private UploadedFile file;
	
	public InicioBean() {
		
		operacionClienteFormulario = new TpOperaClienDto();
//		vistaInicialCalculadora();
		System.out.println("Entro al constructor inicioBean");
		tipoCambioCompraDolar = new Double(-1D);
		resultadoProcesoError = CadenasType.VACIO.getValor();
		indRegistroCuentaBancaria = NumerosType.INDICADOR_NEGATIVO_UNO.getValor();
		indFueraDeHorario = NumerosType.NUMERO_MINIMO_CERO.getValor();
		mostrarMensajeCCI = Boolean.FALSE;
		mostrarMensajeClienteCCI = Boolean.FALSE;
		indDatosEmpresa = Boolean.FALSE;
		indAplicoCupon = Boolean.FALSE;
		mostrarCampoOperacion = Boolean.TRUE;
		mostrarCampoSubeImagenOperacion = Boolean.TRUE;
	}
	
    /**
     * Realiza su ejecuciï¿½n despues del Constructor de la clase AccesoBean.
     */
    @PostConstruct
    public void init() {
    	HttpSession sesion = ConeccionSesion.getSession();
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	indCompleDatos = (Integer) sesion.getAttribute("indCompleDatos");
    	codigoUsuario = (Integer) sesion.getAttribute("codigoUsuario");
    	codigoUsuarioPadre = (Integer) sesion.getAttribute("codigoUsuarioPadre");
    	codigoCliente = (Integer) sesion.getAttribute("codigoCliente");
//    	codOperClie = (Integer) sesion.getAttribute("codOperClie");
    	codPerfUsua = (Integer) sesion.getAttribute("codPerfUsua");
    	usuario = (String) sesion.getAttribute("usuario");
    	ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
    	
    	if(codigoUsuario.intValue() != codigoUsuarioPadre.intValue()) {
    		indDatosEmpresa = Boolean.TRUE;
    	}
    	
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
    	
    	if(indCompleDatos.equals(NumerosType.NUMERO_MINIMO_CERO.getValor())) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_DATOS.getValor());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (PerfilesType.CLIENTE.getIdElemento().equals(codPerfUsua)) {
			getTipoCambioDolar();
			getListaBancosEnvioDinero();
			getListaCuentasBancariasReci();
			getListaCuentasBancariasEnvi();
			getListaOrigenFondos();
			
			ServiceUsuario serviceUsuario = new ServiceUsuarioImpl();
			
			TpUsuarDto  resultadoTemporal = serviceUsuario.getOperacionEnCurso(codigoUsuario);
			
			codOperClie = resultadoTemporal.getCodOperClie();
			codEstaOper = resultadoTemporal.getCodEstaOper();
			
			
			//Tomamos los parametros para el horario de atencion
			String valorParametroHorarioLaV = CadenasType.VACIO.getValor();        				;
    		ServiceParametroGeneral serviceParametroGeneralHoraLaV = new ServiceParametroGeneralImpl();
    		TpParamGenerDto tempporalHorarioLaV = new TpParamGenerDto();
    		tempporalHorarioLaV.setNomParaGene(ParametroGeneralType.HORARIO_ATENCION_LUNES_A_VIERNES.getValor());
    		tempporalHorarioLaV.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
    		List<TpParamGenerDto> listaParametroHorarioLaV = serviceParametroGeneralHoraLaV.getParametrosGenerales(tempporalHorarioLaV);
    		if(listaParametroHorarioLaV!=null && !listaParametroHorarioLaV.isEmpty()) {
    			valorParametroHorarioLaV  = listaParametroHorarioLaV.get(NumerosType.NUMERO_MINIMO_CERO.getValor()).getValParaGene();
    		}
    		
			String valorParametroHorarioSaba = CadenasType.VACIO.getValor();        				;
    		ServiceParametroGeneral serviceParametroGeneralSabado = new ServiceParametroGeneralImpl();
    		TpParamGenerDto tempporalHorarioSab = new TpParamGenerDto();
    		tempporalHorarioSab.setNomParaGene(ParametroGeneralType.HORARIO_ATENCION_SABADOS.getValor());
    		tempporalHorarioSab.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
    		List<TpParamGenerDto> listaParametroHorarioSab = serviceParametroGeneralSabado.getParametrosGenerales(tempporalHorarioSab);
    		if(listaParametroHorarioSab!=null && !listaParametroHorarioSab.isEmpty()) {
    			valorParametroHorarioSaba  = listaParametroHorarioSab.get(NumerosType.NUMERO_MINIMO_CERO.getValor()).getValParaGene();
    		}
			
    		LocalTime horaInicioLaV = null;
    		LocalTime horaFinalLaV = null; 
    		LocalTime horaInicioSab = null;
    		LocalTime horaFinalSab = null;
    		
    		if(!ValidacionesString.esNuloOVacio(valorParametroHorarioLaV) && !ValidacionesString.esNuloOVacio(valorParametroHorarioSaba)) {
    			
    			LocalTime horaActual = LocalTime.now();
    			
    			String[] horarioLaV = valorParametroHorarioLaV.split("-");
    			horaInicioLaV = LocalTime.parse(horarioLaV[0]);
    			horaFinalLaV = LocalTime.parse(horarioLaV[1]);
    			
    			String[] horarioSab = valorParametroHorarioSaba.split("-");
    			horaInicioSab = LocalTime.parse(horarioSab[0]);
    			horaFinalSab = LocalTime.parse(horarioSab[1]);
    			
    			System.out.println("horaInicioLaV: "+horaInicioLaV);
        		System.out.println("horaFinalLaV: "+horaFinalLaV);
        		
    			System.out.println("horaInicioSab: "+horaInicioSab);
        		System.out.println("horaFinalSab: "+horaFinalSab);
        		
        		LocalDate diaActual =  LocalDate.now();
        		
        		DayOfWeek diaDeLaSemana = diaActual.getDayOfWeek();
        		
        		System.out.println("horaActual: "+diaDeLaSemana+" "+horaActual);
        		
        		//Si es de Lunes a Viernes
        		if(diaDeLaSemana.getValue()>=DayOfWeek.MONDAY.getValue() && diaDeLaSemana.getValue()<=DayOfWeek.FRIDAY.getValue()) {
        			if(horaActual.isAfter(horaFinalLaV) || horaActual.isBefore(horaInicioLaV)) {
        				indFueraDeHorario = NumerosType.INDICADOR_POSITIVO_UNO.getValor();
        			}
        			
        		//Si es sabado	
        		}else if(diaDeLaSemana.getValue() == DayOfWeek.SATURDAY.getValue()){
        			if(horaActual.isAfter(horaFinalSab) || horaActual.isBefore(horaInicioSab)) {
        				indFueraDeHorario = NumerosType.INDICADOR_POSITIVO_UNO.getValor();
        			}
        			
        		//Si es domingo
        		}else {
        			indFueraDeHorario = NumerosType.INDICADOR_POSITIVO_UNO.getValor();
        		}
        		
        		
    		}
    		
    		sesion.setAttribute("indFueraDeHorario", indFueraDeHorario);
    		
    		// Si no esta dentro del horario
    		if (indFueraDeHorario.equals(NumerosType.INDICADOR_POSITIVO_UNO.getValor())) {
    			
    			DateTimeFormatter pattern = DateTimeFormatter.ofPattern("hh:mm a");
        		mensajeHorarioAtencion = "Horario de atenciï¿½n: Lunes a Viernes de "+horaInicioLaV.format(pattern)+" a "+horaFinalLaV.format(pattern)
        		+" y Sï¿½bados de "+horaInicioSab.format(pattern)+" a "+horaFinalSab.format(pattern);
        		
        		vistaInicialCalculadora();
				mostrarCompra = Boolean.TRUE;
				tipoCambioUsado = new Double(tipoCambioCompraDolar);
				cantidadEnvio = new BigDecimal(CANTIDAD_ENVIO_INICIAL);
				cargarValoresEnvioReciboA();
				
    		}else {
    			
    			// Si ya se tiene una operacion en proceso
    			if(!ValidacionesNumeros.esCeroONuloEntero(codOperClie)) {
    				
    				if(ElementosTablasType.ESTADO_OPERACION_INICIADA.getIdElemento().equals(codEstaOper)) {
    					
    					vistaInicialTransferencia();
    					//cargar datos de la operacion
    					operacionClienteFormulario.setCodOperClie(codOperClie);
    					ServiceOperacionCliente serviceOperacionCliente = new ServiceOperacionClienteImpl();
    					operacionClienteFormulario = serviceOperacionCliente.getOperacionCliente(operacionClienteFormulario);
    					
    		    		ServiceParametroGeneral serviceParametroGeneralValorTiempo = new ServiceParametroGeneralImpl();
    		    		TpParamGenerDto tempporalValorTiempo = new TpParamGenerDto();
    		    		tempporalValorTiempo.setNomParaGene(ParametroGeneralType.IND_TIEMPO_LIMITE_TRANS_MINUTOS.getValor());
    		    		tempporalValorTiempo.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
    		    		List<TpParamGenerDto> listaParametroValorTiempo = serviceParametroGeneralValorTiempo.getParametrosGenerales(tempporalValorTiempo);
    		    		if(listaParametroValorTiempo!=null && !listaParametroValorTiempo.isEmpty()) {
    		    			valorTiempoLimite  =  Integer.parseInt(listaParametroValorTiempo.get(NumerosType.NUMERO_MINIMO_CERO.getValor()).getValParaGene());
    		    		}
    					
    					if(operacionClienteFormulario != null) {
    						tipoCambioUsado = operacionClienteFormulario.getValTipoCambUsad();

    						if(operacionClienteFormulario.getIndCompVent().equals(NumerosType.NUMERO_MINIMO_CERO.getValor())) {
    							mostrarCompra = Boolean.TRUE;
    						}else {
    							mostrarCompra = Boolean.FALSE;
    						}
    						cantidadEnvio = new BigDecimal(operacionClienteFormulario.getMonEnvi().toString());
    						cargarValoresEnvioReciboA();
    						
    			        	cuentaBancoAdminDetalle = new TpCuentBancoDto();
    			        	
//    			        	cuentaBancoAdminDetalle.getTpBanco().setCodBanc(operacionClienteFormulario.getTpBanco().getCodBanc());
    			        	cuentaBancoAdminDetalle.getTpDivis().setCodDivi(operacionClienteFormulario.getTpDivisByCodDiviEnvi().getCodDivi());
    			        	cuentaBancoAdminDetalle.getTpClien().setCodClie(NumerosType.INDICADOR_POSITIVO_UNO.getValor());
    			        	
    			        	
    			        	/* Obtenemos el codigo de banco de la cuenta bancaria desde donde el cliente envia su dinero*/
    			        	ServiceCliente serviceClienteBancoOrigen = new ServiceClienteImpl();
//    			        	operacionClienteFormulario.getTpCuentBancoByCodCuenBancClieOrig().getTpClien().setCodClie(codigoCliente);
    			        	TpCuentBancoDto tpCuentBancoByCodCuenBancClieOrig = serviceClienteBancoOrigen.getCuentaBanco(operacionClienteFormulario.getTpCuentBancoByCodCuenBancClieOrig());
    			        	
    			        	cuentaBancoAdminDetalle.getTpBanco().setCodBanc(tpCuentBancoByCodCuenBancClieOrig.getTpBanco().getCodBanc());
    			        	
    			        	ServiceCliente serviceCliente = new ServiceClienteImpl();
    			        	
    			        	cuentaBancoAdminDetalle = serviceCliente.getCuentaBanco(cuentaBancoAdminDetalle);
    			        	
    			        	// Se le da la cuenta del banco para un transferencia interbancaria, se debe configurar por parametro general
    			        	if( cuentaBancoAdminDetalle == null) {

    			        		cuentaBancoAdminDetalle = new TpCuentBancoDto();
    			        		
    			        		cuentaBancoAdminDetalle.getTpClien().setCodClie(NumerosType.INDICADOR_POSITIVO_UNO.getValor());
    			            	
    			            	if(operacionClienteFormulario.getTpDivisByCodDiviEnvi().getCodDivi().equals(DivisaType.DOLAR.getCodDivi())) {
    			            		
    			            		//Obtenemos la cuenta en dolares que esta configurada para transferencias interbancarias 
    			            		
    			            		String valorParametroDolares = CadenasType.VACIO.getValor();        				;
    			            		ServiceParametroGeneral serviceParametroGeneralDolares = new ServiceParametroGeneralImpl();
    			            		TpParamGenerDto tempporalDolares = new TpParamGenerDto();
    			            		tempporalDolares.setNomParaGene(ParametroGeneralType.IND_APLICA_TRANS_INTER_DOLAR.getValor());
    			            		tempporalDolares.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
    			            		List<TpParamGenerDto> listaParametroDolar = serviceParametroGeneralDolares.getParametrosGenerales(tempporalDolares);
    			            		if(listaParametroDolar!=null && !listaParametroDolar.isEmpty()) {
    			            			valorParametroDolares  = listaParametroDolar.get(NumerosType.NUMERO_MINIMO_CERO.getValor()).getValParaGene();
    			            		}
    			            		
    			            		cuentaBancoAdminDetalle.getTpBanco().setCodCortBanc(valorParametroDolares);
    			            		cuentaBancoAdminDetalle.getTpDivis().setCodDivi(DivisaType.DOLAR.getCodDivi());
    			            		
    			            	} else if(operacionClienteFormulario.getTpDivisByCodDiviEnvi().getCodDivi().equals(DivisaType.SOL.getCodDivi())) {
    			            		
    			            		//Obtenemos la cuenta en soles que esta configurada para transferencias interbancarias 
    			            		
    			            		String valorParametroSoles = CadenasType.VACIO.getValor();        				;
    			            		ServiceParametroGeneral serviceParametroGeneralSoles = new ServiceParametroGeneralImpl();
    			            		TpParamGenerDto tempporalSoles = new TpParamGenerDto();
    			            		tempporalSoles.setNomParaGene(ParametroGeneralType.IND_APLICA_TRANS_INTER_SOLES.getValor());
    			            		tempporalSoles.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
    			            		List<TpParamGenerDto> listaParametroSoles = serviceParametroGeneralSoles.getParametrosGenerales(tempporalSoles);
    			            		if(listaParametroSoles!=null && !listaParametroSoles.isEmpty()) {
    			            			valorParametroSoles  = listaParametroSoles.get(NumerosType.NUMERO_MINIMO_CERO.getValor()).getValParaGene();
    			            		}
    			            		
    			            		cuentaBancoAdminDetalle.getTpBanco().setCodCortBanc(valorParametroSoles);
    			            		cuentaBancoAdminDetalle.getTpDivis().setCodDivi(DivisaType.SOL.getCodDivi());
    			            	}
    			            	
    			            	ServiceCliente serviceClienteInterbancario = new ServiceClienteImpl();
    			        		cuentaBancoAdminDetalle = serviceClienteInterbancario.getCuentaBanco(cuentaBancoAdminDetalle);
    			        	}
    			        	
    					}
    					
    				}else if (ElementosTablasType.ESTADO_OPERACION_VERIFICACION.getIdElemento().equals(codEstaOper)) {
    					
    					try {
    						FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_OPERACIONES.getValor());
    					} catch (IOException e) {
    						e.printStackTrace();
    				        LoggerUtil.getInstance().getLogger().error(e.getMessage());
    				        LoggerUtil.getInstance().getLogger().error(e);
    					}

    				}
    				
    				
    	
    			}else {
    				
    				//Se valida si ya tiene alguna cuenta registrada
    				
    	        	ServiceCliente serviceCliente = new ServiceClienteImpl();
    	        	
    	        	Integer cantidadCuentas = serviceCliente.getExisteCuentaBancariaCliente(codigoCliente);
    	        	
    	        	// Tiene minimo 1 cuenta en USD y PEN registradas
    	        	if (cantidadCuentas > NumerosType.INDICADOR_POSITIVO_UNO.getValor()) {
    	        		indRegistroCuentaBancaria = NumerosType.INDICADOR_POSITIVO_UNO.getValor();
    	        	}else {
    	        		indRegistroCuentaBancaria = NumerosType.NUMERO_MINIMO_CERO.getValor();
					}
    	        	
    				vistaInicialCalculadora();
    				mostrarCompra = Boolean.TRUE;
    				tipoCambioUsado = new Double(tipoCambioCompraDolar);
    				cantidadEnvio = new BigDecimal(CANTIDAD_ENVIO_INICIAL);
    				cargarValoresEnvioReciboA();
    			}
    		}
    		
			
		}
    	
    }
    
    public void getTipoCambioDolar() {
		ServiceTipoCambio serviceTipoCambio = new ServiceTipoCambioImpl();
		TpTipoCambiDto tpTipoCambiDtoTempo = new TpTipoCambiDto();
		tpTipoCambiDtoTempo.getTpDivisByCodDivi().setCodDivi(DivisaType.DOLAR.getCodDivi());
		tpTipoCambiDtoTempo.getTpDivisByCodDiviCamb().setCodDivi(DivisaType.SOL.getCodDivi());
		tpTipoCambiDtoTempo = serviceTipoCambio.getTipoCambio(tpTipoCambiDtoTempo);

		if(!ValidacionesNumeros.esCeroONuloDecimal(tpTipoCambiDtoTempo.getValCambComp()) &&  !ValidacionesNumeros.esCeroONuloDecimal(tpTipoCambiDtoTempo.getValCambVent()) ) {
			tipoCambioCompraDolar = new Double(tpTipoCambiDtoTempo.getValCambComp());
			tipoCambioVentaDolar = new Double(tpTipoCambiDtoTempo.getValCambVent());
			operacionClienteFormulario.getTpTipoCambi().setCodTipoCamb(tpTipoCambiDtoTempo.getCodTipoCamb());
		}
    }
    
    public Boolean devuelveParametroOrigenFondo(BigDecimal cantidadDolar) {
    	
    	Boolean resultado = Boolean.FALSE;
    	
		String valorMontoBaseUSDOrigenFondo = CadenasType.VACIO.getValor();        				;
		ServiceParametroGeneral serviceParametroMontoBaseUSDOrigenFondo = new ServiceParametroGeneralImpl();
		TpParamGenerDto tempporalBaseUSDOrigenFondo = new TpParamGenerDto();
		tempporalBaseUSDOrigenFondo.setNomParaGene(ParametroGeneralType.MONTO_BASE_USD_ORIGEN_FONDO.getValor());
		tempporalBaseUSDOrigenFondo.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
		List<TpParamGenerDto> listaBaseUSDOrigenFondo = serviceParametroMontoBaseUSDOrigenFondo.getParametrosGenerales(tempporalBaseUSDOrigenFondo);
		if(listaBaseUSDOrigenFondo!=null && !listaBaseUSDOrigenFondo.isEmpty()) {
			valorMontoBaseUSDOrigenFondo  = listaBaseUSDOrigenFondo.get(NumerosType.NUMERO_MINIMO_CERO.getValor()).getValParaGene();
		}
		
		BigDecimal montoBaseParametroUSD= new BigDecimal(valorMontoBaseUSDOrigenFondo);
		
		if ( cantidadDolar.compareTo(montoBaseParametroUSD) == NumerosType.INDICADOR_POSITIVO_UNO.getValor() || 
				cantidadDolar.compareTo(montoBaseParametroUSD) == NumerosType.NUMERO_MINIMO_CERO.getValor()) {
			resultado = Boolean.TRUE;
		}
		
    	return resultado;
    }
    public void cargarValoresEnvioReciboA() {
    	if(cantidadEnvio != null) {
	    	if(mostrarCompra) {
	    		cantidadRecibo = cantidadEnvio.multiply(new BigDecimal(tipoCambioCompraDolar));
	    		cantidadRecibo = cantidadRecibo.setScale(2, RoundingMode.HALF_UP);
	    		cadenaDivisaCantidadEnvio = DivisaType.DOLAR.getSimDivi()+" "+cantidadEnvio+" "+CadenasType.PARENTESIS_INI.getValor()+DivisaType.DOLAR.getCodIsoDivi()+CadenasType.PARENTESIS_FIN.getValor();
	    		cadenaDivisaCantidadRecibo = DivisaType.SOL.getSimDivi()+" "+cantidadRecibo+" "+CadenasType.PARENTESIS_INI.getValor()+DivisaType.SOL.getCodIsoDivi()+CadenasType.PARENTESIS_FIN.getValor();
	    		cadenaDivisaCantidadEnvioInformacion = cantidadEnvio+CadenasType.ESPACIO.getValor()+DivisaType.DOLAR.getNomDiviPlur();
	    		cadenaDivisaCantidadReciboInformacion = cantidadRecibo+CadenasType.ESPACIO.getValor()+DivisaType.SOL.getNomDiviPlur();
	    		operacionClienteFormulario.getTpDivisByCodDiviEnvi().setCodDivi(DivisaType.DOLAR.getCodDivi());
	    		operacionClienteFormulario.getTpDivisByCodDiviReci().setCodDivi(DivisaType.SOL.getCodDivi());
	    		
	    		indHabilitarOrigenFondo = devuelveParametroOrigenFondo(cantidadEnvio);
	    		
	    	}else {
	    		cantidadRecibo = cantidadEnvio.divide(new BigDecimal(tipoCambioVentaDolar), 2, RoundingMode.HALF_UP);
	    		cadenaDivisaCantidadEnvio = DivisaType.SOL.getSimDivi()+" "+cantidadEnvio+" "+CadenasType.PARENTESIS_INI.getValor()+DivisaType.SOL.getCodIsoDivi()+CadenasType.PARENTESIS_FIN.getValor();
	    		cadenaDivisaCantidadRecibo = DivisaType.DOLAR.getSimDivi()+" "+cantidadRecibo+" "+CadenasType.PARENTESIS_INI.getValor()+DivisaType.DOLAR.getCodIsoDivi()+CadenasType.PARENTESIS_FIN.getValor();
	    		cadenaDivisaCantidadEnvioInformacion = cantidadEnvio+CadenasType.ESPACIO.getValor()+DivisaType.SOL.getNomDiviPlur();
	    		cadenaDivisaCantidadReciboInformacion = cantidadRecibo+CadenasType.ESPACIO.getValor()+DivisaType.DOLAR.getNomDiviPlur();
	    		operacionClienteFormulario.getTpDivisByCodDiviEnvi().setCodDivi(DivisaType.SOL.getCodDivi());
	    		operacionClienteFormulario.getTpDivisByCodDiviReci().setCodDivi(DivisaType.DOLAR.getCodDivi());
	    		
	    		indHabilitarOrigenFondo = devuelveParametroOrigenFondo(cantidadRecibo);
	    	}
	    	
	    	
    	}
    }
    
    public void cargarValoresEnvioReciboB() {
    	if(cantidadRecibo != null) {
	    	if(mostrarCompra) {
	    		cantidadEnvio = cantidadRecibo.divide(new BigDecimal(tipoCambioCompraDolar), 2, RoundingMode.HALF_UP);
	    		cadenaDivisaCantidadEnvio = DivisaType.DOLAR.getSimDivi()+" "+cantidadEnvio+" "+CadenasType.PARENTESIS_INI.getValor()+DivisaType.DOLAR.getCodIsoDivi()+CadenasType.PARENTESIS_FIN.getValor();
	    		cadenaDivisaCantidadRecibo = DivisaType.SOL.getSimDivi()+" "+cantidadRecibo+" "+CadenasType.PARENTESIS_INI.getValor()+DivisaType.SOL.getCodIsoDivi()+CadenasType.PARENTESIS_FIN.getValor();
	    		cadenaDivisaCantidadEnvioInformacion = cantidadEnvio+CadenasType.ESPACIO.getValor()+DivisaType.DOLAR.getNomDiviPlur();
	    		cadenaDivisaCantidadReciboInformacion = cantidadRecibo+CadenasType.ESPACIO.getValor()+DivisaType.SOL.getNomDiviPlur();
	    		operacionClienteFormulario.getTpDivisByCodDiviEnvi().setCodDivi(DivisaType.DOLAR.getCodDivi());
	    		operacionClienteFormulario.getTpDivisByCodDiviReci().setCodDivi(DivisaType.SOL.getCodDivi());
	    		
	    		indHabilitarOrigenFondo = devuelveParametroOrigenFondo(cantidadEnvio);
	    	}else{
		    	cantidadEnvio = cantidadRecibo.multiply(new BigDecimal(tipoCambioVentaDolar));
		    	cantidadEnvio = cantidadEnvio.setScale(2, RoundingMode.HALF_UP);
		    	cadenaDivisaCantidadEnvio = DivisaType.SOL.getSimDivi()+" "+cantidadEnvio+" "+CadenasType.PARENTESIS_INI.getValor()+DivisaType.SOL.getCodIsoDivi()+CadenasType.PARENTESIS_FIN.getValor();
		    	cadenaDivisaCantidadRecibo = DivisaType.DOLAR.getSimDivi()+" "+cantidadRecibo+" "+CadenasType.PARENTESIS_INI.getValor()+DivisaType.DOLAR.getCodIsoDivi()+CadenasType.PARENTESIS_FIN.getValor();
		    	cadenaDivisaCantidadEnvioInformacion = cantidadEnvio+CadenasType.ESPACIO.getValor()+DivisaType.SOL.getNomDiviPlur();
	    		cadenaDivisaCantidadReciboInformacion = cantidadRecibo+CadenasType.ESPACIO.getValor()+DivisaType.DOLAR.getNomDiviPlur();
		    	operacionClienteFormulario.getTpDivisByCodDiviEnvi().setCodDivi(DivisaType.SOL.getCodDivi());
	    		operacionClienteFormulario.getTpDivisByCodDiviReci().setCodDivi(DivisaType.DOLAR.getCodDivi());
	    		
	    		indHabilitarOrigenFondo = devuelveParametroOrigenFondo(cantidadRecibo);
	    	}
    	}
    }
    
    // Los montons al rotar se calculan en base a la cantidad de envio
    public void rotarVista() {
    	mostrarCompra = !mostrarCompra;
    	if(mostrarCompra) {
    		tipoCambioUsado = new Double(tipoCambioCompraDolar);
    	}else {
    		tipoCambioUsado = new Double(tipoCambioVentaDolar);
    	}
    	cargarValoresEnvioReciboA();
    }
    
	public void getListaBancosEnvioDinero() {
		
		listaCuentasBancariasTemporal = new LinkedList<TpCuentBancoDto>();
		listaComboBancosEnvioDinero = new ArrayList<SelectItem>();
		ServiceBanco serviceBanco = new ServiceBancoImpl();
		
		TpBancoDto tpBancoDto = new TpBancoDto();

		tpBancoDto.setIndVistAdmi( NumerosType.INDICADOR_POSITIVO_UNO.getValor());
		
		for(TpBancoDto temp : serviceBanco.getBancosDisponibles(tpBancoDto)) {
			listaComboBancosEnvioDinero.add(new SelectItem(temp.getCodBanc(), temp.getNomBanc()));
		}

	}
	
	public void getListaCuentasBancariasReci() {
		listaCuentasBancariasTemporal = new LinkedList<TpCuentBancoDto>();
		listaComboCuentasBancariasReci = new ArrayList<SelectItem>();
		ServiceCliente serviceCliente = new ServiceClienteImpl();
		
		TpCuentBancoDto tpCuentBancoDto = new TpCuentBancoDto();
    	
    	tpCuentBancoDto.getTpClien().setCodClie(codigoCliente);
    	tpCuentBancoDto.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
    	
    	listaCuentasBancariasTemporal = serviceCliente.getCuentasBanco(tpCuentBancoDto);
		
		for(TpCuentBancoDto temp : listaCuentasBancariasTemporal) {
			listaComboCuentasBancariasReci.add(new SelectItem(temp.getCodCuenBanc(), temp.getAliCuen() + CadenasType.GUION_ESPACIOS_LATERALES.getValor()+ CadenasType.PARENTESIS_INI.getValor() + temp.getTpDivis().getCodIsoDivi()  + CadenasType.PARENTESIS_FIN.getValor()  + CadenasType.GUION_ESPACIOS_LATERALES.getValor()+ temp.getValCuenBanc()));
		}
		
	}
	
	public void getListaCuentasBancariasEnvi() {
		listaComboCuentasBancariasEnvi = new ArrayList<SelectItem>();
		ServiceCliente serviceCliente = new ServiceClienteImpl();
		
		TpCuentBancoDto tpCuentBancoDto = new TpCuentBancoDto();
    	
    	tpCuentBancoDto.getTpClien().setCodClie(codigoCliente);
    	tpCuentBancoDto.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
    	
    	listaCuentasBancariasTemporal = serviceCliente.getCuentasBanco(tpCuentBancoDto);
		
		for(TpCuentBancoDto temp : listaCuentasBancariasTemporal) {
			listaComboCuentasBancariasEnvi.add(new SelectItem(temp.getCodCuenBanc(), temp.getAliCuen() + CadenasType.GUION_ESPACIOS_LATERALES.getValor()+ CadenasType.PARENTESIS_INI.getValor() + temp.getTpDivis().getCodIsoDivi()  + CadenasType.PARENTESIS_FIN.getValor()  + CadenasType.GUION_ESPACIOS_LATERALES.getValor()+ temp.getValCuenBanc()));
		}
		
	}
	
	public void getListaOrigenFondos() {
		listaComboOrigenFondo = new ArrayList<SelectItem>();
		ServiceOrigenFondo serviceOrigenFondo = new ServiceOrigenFondoImpl();

		listaComboOrigenFondoTemporal = serviceOrigenFondo.getOrigenFondos(null);
		
		for(TpOrigeFondoDto temp : listaComboOrigenFondoTemporal) {
			listaComboOrigenFondo.add(new SelectItem(temp.getCodOrigFond(), temp.getDesOrigFond()));
		}
		
	}
	
	public Integer validarCuentaReciboCliente() {
		Integer valorRespuesta = NumerosType.NUMERO_MINIMO_CERO.getValor();
		
		TpCuentBancoDto tpCuentBancoDto = listaCuentasBancariasTemporal.stream()
				  .filter(x -> operacionClienteFormulario.getTpCuentBancoByCodCuenBancClieReci().getCodCuenBanc().equals(x.getCodCuenBanc()))
				  .findAny()
				  .orElse(null);
		
		if( !tpCuentBancoDto.getTpDivis().getCodDivi().equals(operacionClienteFormulario.getTpDivisByCodDiviReci().getCodDivi())) {
			valorRespuesta = operacionClienteFormulario.getTpDivisByCodDiviReci().getCodDivi();
		}
		
		return valorRespuesta;
	}
	
	public Integer validarCuentaOrigenCliente() {
		Integer valorRespuesta = NumerosType.NUMERO_MINIMO_CERO.getValor();
		
		TpCuentBancoDto tpCuentBancoDto = listaCuentasBancariasTemporal.stream()
				  .filter(x -> operacionClienteFormulario.getTpCuentBancoByCodCuenBancClieOrig().getCodCuenBanc().equals(x.getCodCuenBanc()))
				  .findAny()
				  .orElse(null);
		
		if( !tpCuentBancoDto.getTpDivis().getCodDivi().equals(operacionClienteFormulario.getTpDivisByCodDiviEnvi().getCodDivi())) {
			valorRespuesta = operacionClienteFormulario.getTpDivisByCodDiviEnvi().getCodDivi();
		}
		
		return valorRespuesta;
	}
	
    public void validarSeleccionCuenta() {
    	resultadoProcesoError = CadenasType.VACIO.getValor();
    	String result = null;
    	Integer temp = validarCuentaReciboCliente();
    	Integer tempOrigen = validarCuentaOrigenCliente();
    	if(operacionClienteFormulario.getTpCuentBancoByCodCuenBancClieOrig().getCodCuenBanc() == null) {
    		result = "Debes seleccionar la cuenta de donde enviarás tu dinero.";
    	}else if(operacionClienteFormulario.getTpCuentBancoByCodCuenBancClieReci().getCodCuenBanc() == null) {
    		result = "Debes seleccionar la cuenta a donde te enviaremos tu dinero.";
    	}else if(operacionClienteFormulario.getTpCuentBancoByCodCuenBancClieOrig().getCodCuenBanc() == 
    			operacionClienteFormulario.getTpCuentBancoByCodCuenBancClieReci().getCodCuenBanc()) {
    		result = "Las cuentas seleccionadas no pueden ser iguales.";
    	}else if(!NumerosType.NUMERO_MINIMO_CERO.getValor().equals(tempOrigen)) {
    		result = ( tempOrigen.equals(DivisaType.DOLAR.getCodDivi()) ? "La cuenta de donde enviaras tu dinero debe ser en Dolares." :"La cuenta de donde enviaras tu dinero debe ser en Soles.") ;
    	}else if(!NumerosType.NUMERO_MINIMO_CERO.getValor().equals(temp)) {
    		result = ( temp.equals(DivisaType.DOLAR.getCodDivi()) ? "La cuenta en donde recibiras tu dinero debe ser en Dolares." :"La cuenta en donde recibiras tu dinero debe ser en Soles.") ;
    	}else {
    		
    		
    		
        	/*inicio de banco a donde enviara el cliente*/

    		ServiceParametroGeneral serviceParametroGeneralValorTiempo = new ServiceParametroGeneralImpl();
    		TpParamGenerDto tempporalValorTiempo = new TpParamGenerDto();
    		tempporalValorTiempo.setNomParaGene(ParametroGeneralType.IND_TIEMPO_LIMITE_TRANS_MINUTOS.getValor());
    		tempporalValorTiempo.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
    		List<TpParamGenerDto> listaParametroValorTiempo = serviceParametroGeneralValorTiempo.getParametrosGenerales(tempporalValorTiempo);
    		if(listaParametroValorTiempo!=null && !listaParametroValorTiempo.isEmpty()) {
    			valorTiempoLimite  =  Integer.parseInt(listaParametroValorTiempo.get(NumerosType.NUMERO_MINIMO_CERO.getValor()).getValParaGene());
    		}
    		
    		
//        	if(ValidacionesNumeros.esCeroONuloEntero(codOperClie)) {
//        		String[] respuesta = result.split(CadenasType.GUION.getValor());
//        		codOperClie = Integer.parseInt(respuesta[1]);
//        		HttpSession sesion = ConeccionSesion.getSession();
//				sesion.setAttribute("codOperClie", codOperClie);
//        	}
        	
        	cuentaBancoAdminDetalle = new TpCuentBancoDto();
        	
//        	cuentaBancoAdminDetalle.getTpBanco().setCodBanc(operacionClienteFormulario.getTpBanco().getCodBanc());
        	cuentaBancoAdminDetalle.getTpDivis().setCodDivi(operacionClienteFormulario.getTpDivisByCodDiviEnvi().getCodDivi());
        	cuentaBancoAdminDetalle.getTpClien().setCodClie(NumerosType.INDICADOR_POSITIVO_UNO.getValor());
        	
        	/* Obtenemos el codigo de banco de la cuenta bancaria desde donde el cliente envia su dinero*/
        	ServiceCliente serviceClienteBancoOrigen = new ServiceClienteImpl();
        	
        	TpCuentBancoDto tpCuentBancoByCodCuenBancClieOrig = serviceClienteBancoOrigen.getCuentaBanco(operacionClienteFormulario.getTpCuentBancoByCodCuenBancClieOrig());
        	
        	cuentaBancoAdminDetalle.getTpBanco().setCodBanc(tpCuentBancoByCodCuenBancClieOrig.getTpBanco().getCodBanc());
        			
        	ServiceCliente serviceCliente = new ServiceClienteImpl();
        	
        	cuentaBancoAdminDetalle = serviceCliente.getCuentaBanco(cuentaBancoAdminDetalle);
        	
        	// Se le da la cuenta del banco para un transferencia interbancaria, se debe configurar por parametro general
        	if( cuentaBancoAdminDetalle == null) {

        		cuentaBancoAdminDetalle = new TpCuentBancoDto();
        		
        		cuentaBancoAdminDetalle.getTpClien().setCodClie(NumerosType.INDICADOR_POSITIVO_UNO.getValor());
            	
            	if(operacionClienteFormulario.getTpDivisByCodDiviEnvi().getCodDivi().equals(DivisaType.DOLAR.getCodDivi())) {
            		
            		//Obtenemos la cuenta en dolares que esta configurada para transferencias interbancarias 
            		
            		String valorParametroDolares = CadenasType.VACIO.getValor();        				;
            		ServiceParametroGeneral serviceParametroGeneralDolares = new ServiceParametroGeneralImpl();
            		TpParamGenerDto tempporalDolares = new TpParamGenerDto();
            		tempporalDolares.setNomParaGene(ParametroGeneralType.IND_APLICA_TRANS_INTER_DOLAR.getValor());
            		tempporalDolares.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
            		List<TpParamGenerDto> listaParametroDolar = serviceParametroGeneralDolares.getParametrosGenerales(tempporalDolares);
            		if(listaParametroDolar!=null && !listaParametroDolar.isEmpty()) {
            			valorParametroDolares  = listaParametroDolar.get(NumerosType.NUMERO_MINIMO_CERO.getValor()).getValParaGene();
            		}
            		
            		cuentaBancoAdminDetalle.getTpBanco().setCodCortBanc(valorParametroDolares);
            		cuentaBancoAdminDetalle.getTpDivis().setCodDivi(DivisaType.DOLAR.getCodDivi());
            		
            	} else if(operacionClienteFormulario.getTpDivisByCodDiviEnvi().getCodDivi().equals(DivisaType.SOL.getCodDivi())) {
            		
            		//Obtenemos la cuenta en soles que esta configurada para transferencias interbancarias 
            		
            		String valorParametroSoles = CadenasType.VACIO.getValor();        				;
            		ServiceParametroGeneral serviceParametroGeneralSoles = new ServiceParametroGeneralImpl();
            		TpParamGenerDto tempporalSoles = new TpParamGenerDto();
            		tempporalSoles.setNomParaGene(ParametroGeneralType.IND_APLICA_TRANS_INTER_SOLES.getValor());
            		tempporalSoles.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
            		List<TpParamGenerDto> listaParametroSoles = serviceParametroGeneralSoles.getParametrosGenerales(tempporalSoles);
            		if(listaParametroSoles!=null && !listaParametroSoles.isEmpty()) {
            			valorParametroSoles  = listaParametroSoles.get(NumerosType.NUMERO_MINIMO_CERO.getValor()).getValParaGene();
            		}
            		
            		cuentaBancoAdminDetalle.getTpBanco().setCodCortBanc(valorParametroSoles);
            		cuentaBancoAdminDetalle.getTpDivis().setCodDivi(DivisaType.SOL.getCodDivi());
            	}
            	
            	ServiceCliente serviceClienteInterbancario = new ServiceClienteImpl();
        		cuentaBancoAdminDetalle = serviceClienteInterbancario.getCuentaBanco(cuentaBancoAdminDetalle);
        	}
        	/*fin de banco a donde enviara el cliente*/
        	
        	
    		//Insertamos la operacion en curso con estatus iniciada
        	
        	
        	operacionClienteFormulario.setTpCuentBancoByCodCuenBancCome(cuentaBancoAdminDetalle);
    		operacionClienteFormulario.getTpClien().setCodClie(codigoCliente);
    		operacionClienteFormulario.getTpClien().getTpUsuar().setCodUsua(codigoUsuario);
    		operacionClienteFormulario.setMonEnvi(cantidadEnvio);
    		operacionClienteFormulario.setMonReci(cantidadRecibo);
        	if(mostrarCompra) {
        		operacionClienteFormulario.setIndCompVent(NumerosType.NUMERO_MINIMO_CERO.getValor());
        	}else {
        		operacionClienteFormulario.setIndCompVent(NumerosType.INDICADOR_POSITIVO_UNO.getValor());
        	}
        	
        	operacionClienteFormulario.setValTipoCambUsad(tipoCambioUsado);
        	operacionClienteFormulario.getTpEstadOpera().setCodEstaOper(ElementosTablasType.ESTADO_OPERACION_INICIADA.getIdElemento());
        	operacionClienteFormulario.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
        	operacionClienteFormulario.setUsuApliCrea(String.valueOf(ideUsuaEmai));
        	operacionClienteFormulario.setFecCreaRegi(new Date());
        	operacionClienteFormulario.setFecInicOper(new Date());
        	
        	if (operacionClienteFormulario.getTpOrigeFondo().getCodOrigFond() == null) {
        		TpOrigeFondoDto tpOrigeFondoDto = new TpOrigeFondoDto();
        		tpOrigeFondoDto.setCodOrigFond(NumerosType.NUMERO_MINIMO_CERO.getValor());
            	operacionClienteFormulario.setTpOrigeFondo(tpOrigeFondoDto);
        	}
        	
        	ServiceOperacionCliente serviceOperacionCliente = new ServiceOperacionClienteImpl();
        	
        	result = serviceOperacionCliente.insertUpdate(operacionClienteFormulario);
        	
        	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor()) && ValidacionesNumeros.esCeroONuloEntero(codOperClie)) {
	    		String[] respuesta = result.split(CadenasType.GUION.getValor());
	    		codOperClie = Integer.parseInt(respuesta[1]);
	    		HttpSession sesion = ConeccionSesion.getSession();
				sesion.setAttribute("codOperClie", codOperClie);
				vistaInicialTransferencia();
	    	}

        	
    	}

//        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
//        	
//        	
//        	
//        }else {
//        	resultadoProcesoError = result;
//        }
//        
        
        if(!result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        	resultadoProcesoError = result;
        	PrimeFaces.current().executeScript("operacionSeleccionarCuentaError();");
        }
        
    }
    
    public void cancelarOperacion() {
    	
    	ServiceOperacionCliente serviceOperacionCliente = new ServiceOperacionClienteImpl();
    	
    	operacionClienteFormulario.setCodOperClie(codOperClie);
    	operacionClienteFormulario.getTpEstadOpera().setCodEstaOper(ElementosTablasType.ESTADO_OPERACION_CANCELADA_CLIENTE.getIdElemento());
    	operacionClienteFormulario.getTpClien().getTpUsuar().setCodUsua(codigoUsuario);
    	operacionClienteFormulario.setUsuApliModi(ideUsuaEmai);
    	operacionClienteFormulario.setFecModiRegi(new Date());
    	operacionClienteFormulario.setUsuApliCancOper(ideUsuaEmai);
    	operacionClienteFormulario.setFecCancOper(new Date());
    	operacionClienteFormulario.setValTextComeCanc(MOTIVO_CANCELACION_CLIENTE_DESDE_REALIZAR_TRANSFERENCIA);
    	
    	serviceOperacionCliente.actualizarEstadoOperacionCliente(operacionClienteFormulario);
    	
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.removeAttribute("codOperClie");
		sesion.removeAttribute("codEstaOper");
		codOperClie = NumerosType.NUMERO_MINIMO_CERO.getValor();
		codEstaOper = NumerosType.NUMERO_MINIMO_CERO.getValor();
		
    	operacionClienteFormulario = new TpOperaClienDto();
    	cuentaBancoAdminDetalle = new TpCuentBancoDto();
    	
    	getTipoCambioDolar();
		vistaInicialCalculadora();
		mostrarCompra = Boolean.TRUE;
		tipoCambioUsado = new Double(tipoCambioCompraDolar);
		cantidadEnvio = new BigDecimal(CANTIDAD_ENVIO_INICIAL);
    	
		cargarValoresEnvioReciboA();
    	
    }
    
    public void validarTransferencia() {
    	
    	cuentaClienteUsoDetalle = new TpCuentBancoDto();
    	
    	cuentaClienteUsoDetalle.setCodCuenBanc(operacionClienteFormulario.getTpCuentBancoByCodCuenBancClieReci().getCodCuenBanc());
    	
    	ServiceCliente serviceCliente = new ServiceClienteImpl();
    	
    	cuentaClienteUsoDetalle = serviceCliente.getCuentaBanco(cuentaClienteUsoDetalle);
    	
		mostrarCuentas = Boolean.FALSE;
		mostrartransferencia = Boolean.FALSE;		
		mostrarVerificacion = Boolean.TRUE;
		
		
    }
    
	public String generarCodigoUnico() {

		SecureRandom rnd = new SecureRandom();

		StringBuilder sb = new StringBuilder(LONGITUD_CODIGO);
		for (int i = 0; i < LONGITUD_CODIGO; i++) {
			sb.append(BASE_CODIGOS.charAt(rnd.nextInt(BASE_CODIGOS.length())));
		}
		return sb.toString();

	}
    
//	public void enviarCorreoRegistroOperacion() {
//		
//		LoggerUtil.getInstance().getLogger().info("inicio enviarCorreoRegistroOperacion");
//		
//    	Map<String, String> datamodel = new HashMap<String, String>();
//    	datamodel.put("parametroNombre", cuentaClienteUsoDetalle.getNombreClienteCorreo());
//    	datamodel.put("parametroCodigoOperacion", operacionClienteFormulario.getCodUnicOperClie());
//    	
//    	
//    	
//    	String asunto = "Operaciï¿½n con cï¿½digo "+operacionClienteFormulario.getCodUnicOperClie()+ " registrada, pendiente de verificaciï¿½n.";
//    	NotificacionUtil.enviarCorreo(datamodel, PlantillasType.PLANTILLA_ENVIAR_REGISTRO_OPERACION.getNombre(), asunto, usuario);
//    	LoggerUtil.getInstance().getLogger().info("Fin enviarCorreoRegistroOperacion");
//	}
	
	
    public void confirmarVerificacion() {
//		mostrarCuentas = Boolean.TRUE;
//		mostrartransferencia = Boolean.FALSE;		
//		mostrarVerificacion = Boolean.FALSE;
    	
    	// Actualiza 
    	if (!ValidacionesString.esNuloOVacio(operacionClienteFormulario.getCodTranBanc())) {
    		ServiceOperacionCliente serviceOperacionClienteCodigo = new ServiceOperacionClienteImpl();
        	
//        	String codigoGenerado = generarCodigoUnico();
        	
        	BigInteger codigoGenerado = serviceOperacionClienteCodigo.getCodigoUnicoOperacion();
        	
        	
    		ServiceOperacionCliente serviceOperacionCliente = new ServiceOperacionClienteImpl();
    		
        	operacionClienteFormulario.setCodOperClie(codOperClie);
        	operacionClienteFormulario.getTpEstadOpera().setCodEstaOper(ElementosTablasType.ESTADO_OPERACION_VERIFICACION.getIdElemento());
        	operacionClienteFormulario.getTpClien().getTpUsuar().setCodUsua(codigoUsuario);
        	operacionClienteFormulario.setUsuApliModi(ideUsuaEmai);
        	operacionClienteFormulario.setFecModiRegi(new Date());
    		operacionClienteFormulario.setCodUnicOperClie(String.format("%06d" , codigoGenerado));
    		operacionClienteFormulario.setFecVeriOper(new Date());
    		
        	String result = serviceOperacionCliente.actualizarEstadoOperacionCliente(operacionClienteFormulario);

        	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        		
//        		if (file != null) {
//                    FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
//                    LoggerUtil.getInstance().getLogger().info("Satisfactorio" + file.getFileName() + " esta en el back.");
//                    FacesContext.getCurrentInstance().addMessage(null, message);
//                }
        		
        		PrimeFaces.current().executeScript("operacionVerificacionExitosa();");
//        		enviarCorreoRegistroOperacion();
        		
    			LoggerUtil.getInstance().getLogger().info("Iniciando Hilo Principal");

    			CorreoEnvioHilo hiloEnvioInicioBean1 = new CorreoEnvioHilo(PlantillasType.PLANTILLA_ENVIAR_REGISTRO_OPERACION , "hiloEnvioInicioBean1", usuario , cuentaClienteUsoDetalle.getNombreClienteCorreo(), operacionClienteFormulario.getCodUnicOperClie(), null);
    			 
    			Thread nuevoHiloEnvioCorreo = new Thread(hiloEnvioInicioBean1);
    			 
    			nuevoHiloEnvioCorreo.start();

    			LoggerUtil.getInstance().getLogger().info("Finalizando Hilo Principal");
    			
        	}else {
        		resultadoProcesoError = result;
        		PrimeFaces.current().executeScript("operacionVerificacionError();");
        	}
    	}else {
    		resultadoProcesoError = "El codigo de operaciÃ³n bancaria es obligatorio, por favor verifique";
    		PrimeFaces.current().executeScript("operacionIngresoDatos();");
    	}
    	
	
    }
    
    public void vistaInicialCalculadora(){
		mostrarCuentas = Boolean.TRUE;
		mostrartransferencia = Boolean.FALSE;		
		mostrarVerificacion = Boolean.FALSE;
    }
    
    public void vistaInicialTransferencia() {
		mostrarCuentas = Boolean.FALSE;
		mostrartransferencia = Boolean.TRUE;		
		mostrarVerificacion = Boolean.FALSE;
    }
    
    public void validarMensajeClienteCCI() {
    	ServiceCliente serviceCliente = new ServiceClienteImpl();
    	
    	Integer indExisteBancoEnNegocio = NumerosType.NUMERO_MINIMO_CERO.getValor();
    	
    	indExisteBancoEnNegocio = serviceCliente.getExisteBancoEnNegocio(operacionClienteFormulario.getTpCuentBancoByCodCuenBancClieOrig());
    	
    	if(indExisteBancoEnNegocio.equals(NumerosType.INDICADOR_POSITIVO_UNO.getValor())) {
    		mostrarMensajeClienteCCI = Boolean.FALSE;
    	}else {
    		mostrarMensajeClienteCCI = Boolean.TRUE;
    	}
    }
    
    public void validarMensajeCCI() {
    	ServiceCliente serviceCliente = new ServiceClienteImpl();
    	
    	Integer indExisteBancoEnNegocio = NumerosType.NUMERO_MINIMO_CERO.getValor();
    	
    	indExisteBancoEnNegocio = serviceCliente.getExisteBancoEnNegocio(operacionClienteFormulario.getTpCuentBancoByCodCuenBancClieReci());
    	
    	if(indExisteBancoEnNegocio.equals(NumerosType.INDICADOR_POSITIVO_UNO.getValor())) {
    		mostrarMensajeCCI = Boolean.FALSE;
    	}else {
    		mostrarMensajeCCI = Boolean.TRUE;
    	}
    }
    
    
    public void validarAplicaCupon() {
    	System.out.println("Entro al validarAplicaCupon");
    	
    	TpCuponDto cuponBusqueda = new TpCuponDto();
    	cuponBusqueda.setNomCupo(operacionClienteFormulario.getNomCupoUsad().trim().toUpperCase());
    	cuponBusqueda.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
    	cuponBusqueda.setEmaClieCupo(ideUsuaEmai);
    	cuponBusqueda.setIndFiltCantCupo(RegistroActivoType.ACTIVO.getLlave());
    	
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    	Date today = new Date();
    	try {
    		Date todayWithZeroTime = formatter.parse(formatter.format(today));
			cuponBusqueda.setFecVigeFilt(todayWithZeroTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	ServiceCupon serviceCupon = new ServiceCuponImpl();
    	List<TpCuponDto> listaCupones = serviceCupon.getCupones(cuponBusqueda);
    	
    	if(listaCupones!=null && !listaCupones.isEmpty()) {
    		indAplicoCupon = Boolean.TRUE;
    		
    		
    		TpCuponDto cuponEncontrado = listaCupones.get(NumerosType.NUMERO_MINIMO_CERO.getValor());
    		operacionClienteFormulario.setCodCupoUsad(cuponEncontrado.getCodCupo());
    		operacionClienteFormulario.setMonDescCupoUsad(cuponEncontrado.getMonDescCupo());
    		
    		aplicaDescuentoPorTipoOperacion(cuponEncontrado);
    		recalculaMontosConCupon();
    		
        	PrimeFaces.current().executeScript("confirmaAplicaCuponExitoso();");
        }else if(listaCupones==null || listaCupones.isEmpty()) {
        	indAplicoCupon = Boolean.FALSE;
        	operacionClienteFormulario.setMonDescCupoUsad(null);
        	PrimeFaces.current().executeScript("confirmaAplicaCuponError();");
        }else {
        	indAplicoCupon = Boolean.FALSE;
        	operacionClienteFormulario.setMonDescCupoUsad(null);
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().executeScript("procesoConError();");
        }
    	
    	
    }
    
    public void aplicaDescuentoPorTipoOperacion(TpCuponDto tpCuponDto) {
    	
    	BigDecimal tipoCambioCompraDolarDesc = (new BigDecimal(tipoCambioCompraDolar - tpCuponDto.getMonDescCupo())).setScale(4, RoundingMode.HALF_UP);
    	BigDecimal tipoCambioVentaDolarDesc = (new BigDecimal(tipoCambioVentaDolar - tpCuponDto.getMonDescCupo())).setScale(4, RoundingMode.HALF_UP);
    	
    	if (ElementosTablasType.TIPO_SERVICIO_COMPRA.getIdElemento().equals(tpCuponDto.getCodTipoOperApli())) {
    		tipoCambioCompraDolar = tipoCambioCompraDolarDesc.doubleValue();
    		operacionClienteFormulario.setValCambCompCupo(tipoCambioCompraDolar);
    	}else if (ElementosTablasType.TIPO_SERVICIO_VENTA.getIdElemento().equals(tpCuponDto.getCodTipoOperApli())) {
    		tipoCambioVentaDolar = tipoCambioVentaDolarDesc.doubleValue();
    		operacionClienteFormulario.setValCambVentCupo(tipoCambioVentaDolar);
    	}else {
    		tipoCambioCompraDolar = tipoCambioCompraDolarDesc.doubleValue();
    		tipoCambioVentaDolar = tipoCambioVentaDolarDesc.doubleValue();
    		operacionClienteFormulario.setValCambCompCupo(tipoCambioCompraDolar);
    		operacionClienteFormulario.setValCambVentCupo(tipoCambioVentaDolar);
    	}
    }
    
    public void recalculaMontosConCupon() {
    	
    	if(mostrarCompra) {
    		tipoCambioUsado = new Double(tipoCambioCompraDolar);
    	}else {
    		tipoCambioUsado = new Double(tipoCambioVentaDolar);
    	}
    	cargarValoresEnvioReciboA();
    }
    
    public void procesarCerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_INDEX.getValor());
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }

    public void irARegistrarCuenta() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_CUENTA_ITEM.getValor());
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
	public Double getTipoCambioCompraDolar() {
		return tipoCambioCompraDolar;
	}

	public void setTipoCambioCompraDolar(Double tipoCambioCompraDolar) {
		this.tipoCambioCompraDolar = tipoCambioCompraDolar;
	}

	public Double getTipoCambioVentaDolar() {
		return tipoCambioVentaDolar;
	}

	public void setTipoCambioVentaDolar(Double tipoCambioVentaDolar) {
		this.tipoCambioVentaDolar = tipoCambioVentaDolar;
	}

	public Boolean getMostrarCuentas() {
		return mostrarCuentas;
	}

	public void setMostrarCuentas(Boolean mostrarCuentas) {
		this.mostrarCuentas = mostrarCuentas;
	}

	public Boolean getMostrartransferencia() {
		return mostrartransferencia;
	}

	public void setMostrartransferencia(Boolean mostrartransferencia) {
		this.mostrartransferencia = mostrartransferencia;
	}

	public Boolean getMostrarVerificacion() {
		return mostrarVerificacion;
	}

	public void setMostrarVerificacion(Boolean mostrarVerificacion) {
		this.mostrarVerificacion = mostrarVerificacion;
	}

	public List<SelectItem> getListaComboBancosEnvioDinero() {
		return listaComboBancosEnvioDinero;
	}

	public void setListaComboBancosEnvioDinero(List<SelectItem> listaComboBancosEnvioDinero) {
		this.listaComboBancosEnvioDinero = listaComboBancosEnvioDinero;
	}

	public String getValorNombre() {
		return valorNombre;
	}

	public void setValorNombre(String valorNombre) {
		this.valorNombre = valorNombre;
	}

	public Integer getIndCompleDatos() {
		return indCompleDatos;
	}

	public void setIndCompleDatos(Integer indCompleDatos) {
		this.indCompleDatos = indCompleDatos;
	}

	public List<SelectItem> getListaComboCuentasBancariasReci() {
		return listaComboCuentasBancariasReci;
	}

	public void setListaComboCuentasBancariasReci(List<SelectItem> listaComboCuentasBancariasReci) {
		this.listaComboCuentasBancariasReci = listaComboCuentasBancariasReci;
	}

	public Boolean getMostrarCompra() {
		return mostrarCompra;
	}

	public void setMostrarCompra(Boolean mostrarCompra) {
		this.mostrarCompra = mostrarCompra;
	}

	public BigDecimal getCantidadEnvio() {
		return cantidadEnvio;
	}

	public void setCantidadEnvio(BigDecimal cantidadEnvio) {
		this.cantidadEnvio = cantidadEnvio;
	}

	public BigDecimal getCantidadRecibo() {
		return cantidadRecibo;
	}

	public void setCantidadRecibo(BigDecimal cantidadRecibo) {
		this.cantidadRecibo = cantidadRecibo;
	}

	public Double getTipoCambioUsado() {
		return tipoCambioUsado;
	}

	public void setTipoCambioUsado(Double tipoCambioUsado) {
		this.tipoCambioUsado = tipoCambioUsado;
	}

	public String getCadenaDivisaCantidadEnvio() {
		return cadenaDivisaCantidadEnvio;
	}

	public void setCadenaDivisaCantidadEnvio(String cadenaDivisaCantidadEnvio) {
		this.cadenaDivisaCantidadEnvio = cadenaDivisaCantidadEnvio;
	}

	public String getCadenaDivisaCantidadRecibo() {
		return cadenaDivisaCantidadRecibo;
	}

	public void setCadenaDivisaCantidadRecibo(String cadenaDivisaCantidadRecibo) {
		this.cadenaDivisaCantidadRecibo = cadenaDivisaCantidadRecibo;
	}

	public TpOperaClienDto getOperacionClienteFormulario() {
		return operacionClienteFormulario;
	}

	public void setOperacionClienteFormulario(TpOperaClienDto operacionClienteFormulario) {
		this.operacionClienteFormulario = operacionClienteFormulario;
	}

	public String getResultadoProcesoError() {
		return resultadoProcesoError;
	}

	public void setResultadoProcesoError(String resultadoProcesoError) {
		this.resultadoProcesoError = resultadoProcesoError;
	}

	public String getCadenaDivisaCantidadEnvioInformacion() {
		return cadenaDivisaCantidadEnvioInformacion;
	}

	public void setCadenaDivisaCantidadEnvioInformacion(String cadenaDivisaCantidadEnvioInformacion) {
		this.cadenaDivisaCantidadEnvioInformacion = cadenaDivisaCantidadEnvioInformacion;
	}

	public String getCadenaDivisaCantidadReciboInformacion() {
		return cadenaDivisaCantidadReciboInformacion;
	}

	public void setCadenaDivisaCantidadReciboInformacion(String cadenaDivisaCantidadReciboInformacion) {
		this.cadenaDivisaCantidadReciboInformacion = cadenaDivisaCantidadReciboInformacion;
	}

	public TpCuentBancoDto getCuentaBancoAdminDetalle() {
		return cuentaBancoAdminDetalle;
	}

	public void setCuentaBancoAdminDetalle(TpCuentBancoDto cuentaBancoAdminDetalle) {
		this.cuentaBancoAdminDetalle = cuentaBancoAdminDetalle;
	}

	public TpCuentBancoDto getCuentaClienteUsoDetalle() {
		return cuentaClienteUsoDetalle;
	}

	public void setCuentaClienteUsoDetalle(TpCuentBancoDto cuentaClienteUsoDetalle) {
		this.cuentaClienteUsoDetalle = cuentaClienteUsoDetalle;
	}

	public Integer getValorTiempoLimite() {
		return valorTiempoLimite;
	}

	public void setValorTiempoLimite(Integer valorTiempoLimite) {
		this.valorTiempoLimite = valorTiempoLimite;
	}

	public Integer getIndRegistroCuentaBancaria() {
		return indRegistroCuentaBancaria;
	}

	public void setIndRegistroCuentaBancaria(Integer indRegistroCuentaBancaria) {
		this.indRegistroCuentaBancaria = indRegistroCuentaBancaria;
	}

	public Integer getIndFueraDeHorario() {
		return indFueraDeHorario;
	}

	public void setIndFueraDeHorario(Integer indFueraDeHorario) {
		this.indFueraDeHorario = indFueraDeHorario;
	}

	public String getMensajeHorarioAtencion() {
		return mensajeHorarioAtencion;
	}

	public void setMensajeHorarioAtencion(String mensajeHorarioAtencion) {
		this.mensajeHorarioAtencion = mensajeHorarioAtencion;
	}

	public Boolean getMostrarMensajeCCI() {
		return mostrarMensajeCCI;
	}

	public void setMostrarMensajeCCI(Boolean mostrarMensajeCCI) {
		this.mostrarMensajeCCI = mostrarMensajeCCI;
	}

	public Boolean getMostrarMensajeClienteCCI() {
		return mostrarMensajeClienteCCI;
	}

	public void setMostrarMensajeClienteCCI(Boolean mostrarMensajeClienteCCI) {
		this.mostrarMensajeClienteCCI = mostrarMensajeClienteCCI;
	}

	public List<SelectItem> getListaComboOrigenFondo() {
		return listaComboOrigenFondo;
	}

	public void setListaComboOrigenFondo(List<SelectItem> listaComboOrigenFondo) {
		this.listaComboOrigenFondo = listaComboOrigenFondo;
	}

	public Boolean getIndHabilitarOrigenFondo() {
		return indHabilitarOrigenFondo;
	}

	public void setIndHabilitarOrigenFondo(Boolean indHabilitarOrigenFondo) {
		this.indHabilitarOrigenFondo = indHabilitarOrigenFondo;
	}

	public Boolean getIndDatosEmpresa() {
		return indDatosEmpresa;
	}

	public void setIndDatosEmpresa(Boolean indDatosEmpresa) {
		this.indDatosEmpresa = indDatosEmpresa;
	}

	public List<SelectItem> getListaComboCuentasBancariasEnvi() {
		return listaComboCuentasBancariasEnvi;
	}

	public void setListaComboCuentasBancariasEnvi(List<SelectItem> listaComboCuentasBancariasEnvi) {
		this.listaComboCuentasBancariasEnvi = listaComboCuentasBancariasEnvi;
	}

	public Boolean getIndAplicoCupon() {
		return indAplicoCupon;
	}

	public void setIndAplicoCupon(Boolean indAplicoCupon) {
		this.indAplicoCupon = indAplicoCupon;
	}

	public Boolean getMostrarCampoOperacion() {
		return mostrarCampoOperacion;
	}

	public void setMostrarCampoOperacion(Boolean mostrarCampoOperacion) {
		this.mostrarCampoOperacion = mostrarCampoOperacion;
	}

	public Boolean getMostrarCampoSubeImagenOperacion() {
		return mostrarCampoSubeImagenOperacion;
	}

	public void setMostrarCampoSubeImagenOperacion(Boolean mostrarCampoSubeImagenOperacion) {
		this.mostrarCampoSubeImagenOperacion = mostrarCampoSubeImagenOperacion;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	

}
