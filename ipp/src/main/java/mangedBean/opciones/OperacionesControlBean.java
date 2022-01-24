package mangedBean.opciones;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import cadenas.util.ValidacionesString;
import dto.TpOperaClienDto;
import javax.servlet.http.HttpSession;
import loggerUtil.LoggerUtil;
import service.ServiceOperacionCliente;
import service.impl.ServiceOperacionClienteImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.PaginasPrivadasType;

@ManagedBean(name="operacionesControlBean")
@ViewScoped
public class OperacionesControlBean {

	private String resultadoProcesoError;
	private List<TpOperaClienDto> listaOperacionesControl;
//	private TpOperaClienDto operacionSeleccionada;
//	private Integer codigoCliente;
	private String valorNombre;
	private String codigoUnicoOperacionBuscar;	
	public OperacionesControlBean() {
//		operacionSeleccionada = new TpOperaClienDto();
		listaOperacionesControl = new LinkedList<TpOperaClienDto>();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor OperacionesControlBean");
		
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase OperacionesControlBean.
     */
    @PostConstruct
    public void init() {
    	
    	HttpSession sesion = ConeccionSesion.getSession();
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
//    	codigoCliente = (Integer) sesion.getAttribute("codigoCliente");
    	
    	getOperacionesCliente();
    }
    
    public void getOperacionesCliente() {
    	ServiceOperacionCliente serviceOperacionCliente = new ServiceOperacionClienteImpl();
    	TpOperaClienDto tpOperaClienDto = new TpOperaClienDto();
    	codigoUnicoOperacionBuscar = codigoUnicoOperacionBuscar != null ? codigoUnicoOperacionBuscar.trim() : codigoUnicoOperacionBuscar;
    	tpOperaClienDto.setCodUnicOperClie(codigoUnicoOperacionBuscar);
//    	tpOperaClienDto.getTpClien().setCodClie(codigoCliente);
    	
    	listaOperacionesControl = serviceOperacionCliente.getOperacionesCliente(tpOperaClienDto);
    	
//    	listaOperacionesControl = listaOperacionesCliente.stream()          
//                .filter(x -> x.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OOPERACION_CANCELADA.getIdElemento()) ||
//                		x.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OOPERACION_FINALIZADA.getIdElemento()))  
//                .collect(Collectors.toList());
//    	
    }

//    public void mostrarDialogConfirmacionFinalizarOperacion(TpOperaClienDto tpOperaClienDto) {
//    	
//    	operacionSeleccionada = tpOperaClienDto;
//    	System.out.println("Se debe cambiar el estado de: "+tpOperaClienDto.getCodOperClie());
//    	PrimeFaces.current().executeScript("mostrarPopupConfirmaFinalizar();");
//    	
//    	   
//    }
//    
//    public void mostrarDialogConfirmacionCancelarOperacion(TpOperaClienDto tpOperaClienDtoCancelar) {
//    	
//    	operacionSeleccionada = tpOperaClienDtoCancelar;
//    	System.out.println("Se debe cancelar la operacion: "+tpOperaClienDtoCancelar.getCodOperClie());
//    	PrimeFaces.current().executeScript("mostrarPopupConfirmaCancelar();");
//    	
//    	   
//    }
//    
//    public void procesarFinalizarOperacion() {
//    	
//    	resultadoProcesoError = CadenasType.VACIO.getValor();
//    	
//    	if(operacionSeleccionada!=null) {
//    		System.out.println("Ejecutar"+operacionSeleccionada.getCodOperClie());
//    		
//    		ServiceOperacionCliente serviceOperacionCliente = new ServiceOperacionClienteImpl();
//
//    		operacionSeleccionada.getTpEstadOpera().setCodEstaOper(ElementosTablasType.ESTADO_OOPERACION_FINALIZADA.getIdElemento());
//        	operacionSeleccionada.setUsuApliModi(operacionSeleccionada.getTpClien().getTpUsuar().getIdeUsuaEmai());
//        	operacionSeleccionada.setFecModiRegi(new Date());
//    		
//        	String result = serviceOperacionCliente.actualizarEstadoOperacionCliente(operacionSeleccionada);
//
//        	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
//        		
//        		getOperacionesCliente();
//        		PrimeFaces.current().executeScript("operacionFinalizadaExitosa();");
//        		
//        		LoggerUtil.getInstance().getLogger().info("Iniciando Hilo Principal");
//
//    			CorreoEnvioHilo hiloEnvioOperacionesControlBean1 = new CorreoEnvioHilo(PlantillasType.PLANTILLA_ENVIAR_FINALIZO_OPERACION , "hiloEnvioOperacionesControlBean1", operacionSeleccionada.getTpClien().getTpUsuar().getIdeUsuaEmai() , operacionSeleccionada.getTpClien().getValPrimNombPers(), operacionSeleccionada.getCodUnicOperClie());
//    			 
//    			Thread nuevoHiloEnvioCorreo = new Thread(hiloEnvioOperacionesControlBean1);
//    			 
//    			nuevoHiloEnvioCorreo.start();
//
//    			LoggerUtil.getInstance().getLogger().info("Finalizando Hilo Principal");
//    			
//        	}else {
//        		resultadoProcesoError = result;
//        		LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
//        		PrimeFaces.current().executeScript("procesoConError();");
//        	}
//    		
//    	}
//    }
//    
//    public void procesarCancelarOperacion() {
//    	
//    	resultadoProcesoError = CadenasType.VACIO.getValor();
//    	
//    	if(operacionSeleccionada !=null) {
//    		
//        	ServiceOperacionCliente serviceOperacionCliente = new ServiceOperacionClienteImpl();
//
//        	operacionSeleccionada.getTpEstadOpera().setCodEstaOper(ElementosTablasType.ESTADO_OOPERACION_CANCELADA_OPERACION.getIdElemento());
//        	operacionSeleccionada.setUsuApliModi(operacionSeleccionada.getTpClien().getTpUsuar().getIdeUsuaEmai());
//        	operacionSeleccionada.setFecModiRegi(new Date());
//
//        	String result = serviceOperacionCliente.actualizarEstadoOperacionCliente(operacionSeleccionada);
//
//        	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
//
//        		getOperacionesCliente();
//        		PrimeFaces.current().executeScript("operacionCanceladaExitosa();");
//        		
//        		LoggerUtil.getInstance().getLogger().info("Iniciando Hilo Principal");
//
//    			CorreoEnvioHilo hiloEnvioOperacionesControlBean2 = new CorreoEnvioHilo(PlantillasType.PLANTILLA_ENVIAR_CANCELA_OPERACION , "hiloEnvioOperacionesControlBean2", operacionSeleccionada.getTpClien().getTpUsuar().getIdeUsuaEmai() , operacionSeleccionada.getTpClien().getValPrimNombPers(), operacionSeleccionada.getCodUnicOperClie());
//    			 
//    			Thread nuevoHiloEnvioCorreo = new Thread(hiloEnvioOperacionesControlBean2);
//    			 
//    			nuevoHiloEnvioCorreo.start();
//
//    			LoggerUtil.getInstance().getLogger().info("Finalizando Hilo Principal");
//    			
//        	}else {
//        		resultadoProcesoError = result;
//        		LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
//        		PrimeFaces.current().executeScript("procesoConError();");
//        	}
//    		
//    	}
//
//    }

    public void seleccionarOperacionAtender(TpOperaClienDto tpOperaClienDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("operacionItemSeleccionado", tpOperaClienDto);
		sesion.removeAttribute("indicadorModoConsulta");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_OPERACIONES_CONTROL_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void seleccionarOperacionDetalle(TpOperaClienDto tpOperaClienDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("operacionItemSeleccionado", tpOperaClienDto);
		sesion.setAttribute("indicadorModoConsulta", Boolean.TRUE);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_OPERACIONES_CONTROL_ITEM.getValor());
		} catch (IOException e) {
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
    
    public void procesarRefrescarOperaciones() {
    	codigoUnicoOperacionBuscar = null;
    	getOperacionesCliente();
    }
    
    public void procesarBuscarCodigo() {
    	getOperacionesCliente();
    }
    
	public String getValorNombre() {
		return valorNombre;
	}

	public void setValorNombre(String valorNombre) {
		this.valorNombre = valorNombre;
	}

	public List<TpOperaClienDto> getListaOperacionesControl() {
		return listaOperacionesControl;
	}

	public void setListaOperacionesControl(List<TpOperaClienDto> listaOperacionesControl) {
		this.listaOperacionesControl = listaOperacionesControl;
	}

	public String getResultadoProcesoError() {
		return resultadoProcesoError;
	}

	public void setResultadoProcesoError(String resultadoProcesoError) {
		this.resultadoProcesoError = resultadoProcesoError;
	}

	public String getCodigoUnicoOperacionBuscar() {
		return codigoUnicoOperacionBuscar;
	}

	public void setCodigoUnicoOperacionBuscar(String codigoUnicoOperacionBuscar) {
		this.codigoUnicoOperacionBuscar = codigoUnicoOperacionBuscar;
	}

	
    
}
