package mangedBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import cadenas.util.ValidacionesString;
import dto.TpCuentBancoDto;
import dto.TpOperaClienDto;
import loggerUtil.LoggerUtil;
import managedThread.CorreoEnvioHilo;
import numeros.util.ValidacionesNumeros;
import service.ServiceCliente;
import service.ServiceOperacionCliente;
import service.impl.ServiceClienteImpl;
import service.impl.ServiceOperacionClienteImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.ElementosTablasType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;
import util.types.PlantillasType;

@ManagedBean(name="operacionesClienteItemBean")
@ViewScoped
public class OperacionesClienteItemBean {

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
	private Boolean indDatosEmpresa;
	private Integer codigoUsuario;
	private Integer codigoUsuarioPadre;	
	
	private List<SelectItem> listaComboNuevoEstado;
	
	private Boolean indicadorModoConsulta;
	public OperacionesClienteItemBean() {
		
		HttpSession sesion = ConeccionSesion.getSession();
		ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
		
		operacionControlItem = new TpOperaClienDto();
		indicadorModoConsulta = Boolean.FALSE;
		resultadoProcesoExito = CadenasType.VACIO.getValor();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		indDatosEmpresa = Boolean.FALSE;
		System.out.println("Entro al constructor OperacionesControlItemBean");
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase OperacionesControlBean.
     */
    @PostConstruct
    public void init() {
    	
    	HttpSession sesion = ConeccionSesion.getSession();
    	TpOperaClienDto operacionItemSeleccionado = (TpOperaClienDto) sesion.getAttribute("operacionItemSeleccionado");
		Boolean indicadorModoConsultaTemporal = (Boolean) sesion.getAttribute("indicadorModoConsulta");
//		sesion.removeAttribute("operacionItemSeleccionado");
//		sesion.removeAttribute("indicadorModoConsulta");
		
    	codigoUsuario = (Integer) sesion.getAttribute("codigoUsuario");
    	codigoUsuarioPadre = (Integer) sesion.getAttribute("codigoUsuarioPadre");
    	
    	if(codigoUsuario.intValue() != codigoUsuarioPadre.intValue()) {
    		indDatosEmpresa = Boolean.TRUE;
    	}
    	
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
    	indMostrarVistaFinalizar = Boolean.TRUE;
    	PrimeFaces.current().executeScript("mostrarPopupConfirmaFinalizar();");
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
    		System.out.println("Ejecutar"+operacionControlItem.getCodOperClie());
    		
    		ServiceOperacionCliente serviceOperacionCliente = new ServiceOperacionClienteImpl();

    		operacionControlItem.getTpEstadOpera().setCodEstaOper(ElementosTablasType.ESTADO_OPERACION_FINALIZADA.getIdElemento());
    		operacionControlItem.getTpEstadOpera().setDesEstaOper(ElementosTablasType.ESTADO_OPERACION_FINALIZADA.getNombreElemento());
        	operacionControlItem.setUsuApliModi(ideUsuaEmai);
        	operacionControlItem.setFecModiRegi(new Date());
    		operacionControlItem.setUsuApliFinaOper(ideUsuaEmai);
        	operacionControlItem.setFecFinaOper(new Date());
    		
        	String result = serviceOperacionCliente.actualizarEstadoOperacionCliente(operacionControlItem);

        	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        		
//        		getOperacionesCliente();
        		
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

	public Boolean getIndDatosEmpresa() {
		return indDatosEmpresa;
	}

	public void setIndDatosEmpresa(Boolean indDatosEmpresa) {
		this.indDatosEmpresa = indDatosEmpresa;
	}
    
	
}
