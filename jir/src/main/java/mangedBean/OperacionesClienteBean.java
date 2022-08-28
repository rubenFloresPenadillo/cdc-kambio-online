package mangedBean;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import cadenas.util.ValidacionesString;
import dto.TpOperaClienDto;
import loggerUtil.LoggerUtil;
import numeros.util.ValidacionesNumeros;
import service.ServiceOperacionCliente;
import service.impl.ServiceOperacionClienteImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.ElementosTablasType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;
import util.types.PerfilesType;

@ManagedBean(name="operacionesClienteBean")
@ViewScoped
public class OperacionesClienteBean {

	private String resultadoProcesoError;
	private List<TpOperaClienDto> listaOperacionesHistoricas;
	private TpOperaClienDto operacionEnProceso;
	private Integer codigoCliente;
	private Integer indCompleDatos;
	private Integer codPerfUsua;
	private String valorNombre;
	private Integer indFueraDeHorario;
	private Boolean indDatosEmpresa;
	private Integer codigoUsuario;
	private Integer codigoUsuarioPadre;	
	
	private static final String MOTIVO_CANCELACION_CLIENTE_BAND_OPERACION = "Desistio desde su bandeja de operaciones";
	
	public OperacionesClienteBean() {
		listaOperacionesHistoricas = new LinkedList<TpOperaClienDto>();
		operacionEnProceso = new TpOperaClienDto();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor OperacionesClienteBean");
		indDatosEmpresa = Boolean.FALSE;
		
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase OperacionesCliente.
     */
    @PostConstruct
    public void init() {
    	
    	HttpSession sesion = ConeccionSesion.getSession();
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	indFueraDeHorario = (Integer) sesion.getAttribute("indFueraDeHorario");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
    	codigoCliente = (Integer) sesion.getAttribute("codigoCliente");
    	codPerfUsua = (Integer) sesion.getAttribute("codPerfUsua");
    	indCompleDatos = (Integer) sesion.getAttribute("indCompleDatos");
    	codigoUsuario = (Integer) sesion.getAttribute("codigoUsuario");
    	codigoUsuarioPadre = (Integer) sesion.getAttribute("codigoUsuarioPadre");
    	
    	if(codigoUsuario.intValue() != codigoUsuarioPadre.intValue()) {
    		indDatosEmpresa = Boolean.TRUE;
    	}
    	
    	if(indCompleDatos.equals(NumerosType.NUMERO_MINIMO_CERO.getValor())) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_DATOS.getValor());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (PerfilesType.CLIENTE.getIdElemento().equals(codPerfUsua)) {
			getOperacionesCliente();
		}
    }
    
    public void getOperacionesCliente() {
    	ServiceOperacionCliente serviceOperacionCliente = new ServiceOperacionClienteImpl();
    	TpOperaClienDto tpOperaClienDto = new TpOperaClienDto();
    	tpOperaClienDto.getTpClien().setCodClie(codigoCliente);
    	List<TpOperaClienDto> listaOperacionesCliente = serviceOperacionCliente.getOperacionesCliente(tpOperaClienDto);
    	
    	listaOperacionesHistoricas = listaOperacionesCliente.stream()          
                .filter(x -> x.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OPERACION_CANCELADA_AUTOMATICA.getIdElemento()) ||
                		x.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OPERACION_CANCELADA_OPERACION.getIdElemento()) ||
                		x.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OPERACION_CANCELADA_CLIENTE.getIdElemento()) ||
                		x.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OPERACION_FINALIZADA.getIdElemento()))  
                .collect(Collectors.toList());
    	
    	operacionEnProceso = listaOperacionesCliente.stream()                        
                 .filter(x -> x.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OPERACION_INICIADA.getIdElemento()) ||
                		 x.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OPERACION_VERIFICACION.getIdElemento())	)
                 .findAny()
                 .orElse(null); 
    	
    }

    public void mostrarDialogConfirmacionCancelarOperacionCliente() {

    	System.out.println("Se debe cancelar la operacion: "+operacionEnProceso.getCodOperClie());
    	PrimeFaces.current().executeScript("mostrarPopupConfirmaCancelarCliente();");
    	
    	   
    }
    
    public void procesarCancelarOperacionCliente() {
    	
    	resultadoProcesoError = CadenasType.VACIO.getValor();
    	
    	if(operacionEnProceso !=null) {
    		
        	ServiceOperacionCliente serviceOperacionCliente = new ServiceOperacionClienteImpl();

        	operacionEnProceso.getTpEstadOpera().setCodEstaOper(ElementosTablasType.ESTADO_OPERACION_CANCELADA_CLIENTE.getIdElemento());
        	operacionEnProceso.setUsuApliModi(operacionEnProceso.getTpClien().getTpUsuar().getIdeUsuaEmai());
        	operacionEnProceso.setFecModiRegi(new Date());
        	operacionEnProceso.setUsuApliCancOper(operacionEnProceso.getTpClien().getTpUsuar().getIdeUsuaEmai());
        	operacionEnProceso.setFecCancOper(new Date());
        	operacionEnProceso.setValTextComeCanc(MOTIVO_CANCELACION_CLIENTE_BAND_OPERACION);

        	String result = serviceOperacionCliente.actualizarEstadoOperacionCliente(operacionEnProceso);

        	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        		HttpSession sesion = ConeccionSesion.getSession();
        		sesion.removeAttribute("codOperClie");
        		sesion.removeAttribute("codEstaOper");
        		getOperacionesCliente();
        		PrimeFaces.current().executeScript("operacionCanceladaExitosa();");
        	}else {
        		resultadoProcesoError = result;
        		LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        		PrimeFaces.current().executeScript("procesoConError();");
        	}
    		
    	}
    }
    
    
    public void seleccionarOperacionConsultar(){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("operacionItemSeleccionado", operacionEnProceso);
		sesion.setAttribute("indicadorModoConsulta", Boolean.TRUE);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_OPERACIONES_CLIENTE_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
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
    
	public String getValorNombre() {
		return valorNombre;
	}

	public void setValorNombre(String valorNombre) {
		this.valorNombre = valorNombre;
	}

	public List<TpOperaClienDto> getListaOperacionesHistoricas() {
		return listaOperacionesHistoricas;
	}

	public void setListaOperacionesHistoricas(List<TpOperaClienDto> listaOperacionesHistoricas) {
		this.listaOperacionesHistoricas = listaOperacionesHistoricas;
	}

	public TpOperaClienDto getOperacionEnProceso() {
		return operacionEnProceso;
	}

	public void setOperacionEnProceso(TpOperaClienDto operacionEnProceso) {
		this.operacionEnProceso = operacionEnProceso;
	}
    
	public String getResultadoProcesoError() {
		return resultadoProcesoError;
	}

	public void setResultadoProcesoError(String resultadoProcesoError) {
		this.resultadoProcesoError = resultadoProcesoError;
	}

	public Integer getIndFueraDeHorario() {
		return indFueraDeHorario;
	}

	public void setIndFueraDeHorario(Integer indFueraDeHorario) {
		this.indFueraDeHorario = indFueraDeHorario;
	}

	public Boolean getIndDatosEmpresa() {
		return indDatosEmpresa;
	}

	public void setIndDatosEmpresa(Boolean indDatosEmpresa) {
		this.indDatosEmpresa = indDatosEmpresa;
	}
	
	
}
