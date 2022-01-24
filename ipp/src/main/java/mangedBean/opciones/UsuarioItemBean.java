package mangedBean.opciones;

import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import cadenas.util.ValidacionesString;
import dto.TpClienDto;
import javax.servlet.http.HttpSession;
import loggerUtil.LoggerUtil;
import service.ServiceTipoCambio;
import service.impl.ServiceTipoCambioImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.PaginasPrivadasType;
import util.types.RegistroActivoType;

@ManagedBean(name="usuarioItemBean")
@ViewScoped
public class UsuarioItemBean {

	private String resultadoProcesoExito;
	private String resultadoProcesoError;
	private TpClienDto usuarioClienteItem;
	private String valorNombre;
	private String ideUsuaEmai;
//	private List<SelectItem> listaComboEstadoTipoCambio;
	private Boolean indicadorModoConsulta;
//	private List<SelectItem> listaComboDivisas;
//	private List<SelectItem> listaComboDivisasCambio;
	
	public UsuarioItemBean() {
		
		HttpSession sesion = ConeccionSesion.getSession();
		ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
		
		usuarioClienteItem = new TpClienDto();
		indicadorModoConsulta = Boolean.FALSE;
		resultadoProcesoExito = CadenasType.VACIO.getValor();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor usuarioClienteItemBean");
	}
	
    /**
     * Realiza su ejecuciï¿½n despues del Constructor de la clase OperacionesControlBean.
     */
    @PostConstruct
    public void init() {
    	
    	HttpSession sesion = ConeccionSesion.getSession();
    	TpClienDto usuarioClienteItemSeleccionado = (TpClienDto) sesion.getAttribute("usuarioClienteItemSeleccionado");
		Boolean indicadorModoConsultaTemporal = (Boolean) sesion.getAttribute("indicadorModoConsulta");
		sesion.removeAttribute("usuarioClienteItemSeleccionado");
		sesion.removeAttribute("indicadorModoConsulta");
		
		if(usuarioClienteItemSeleccionado != null) {
			usuarioClienteItem = usuarioClienteItemSeleccionado;
		} else {
			usuarioClienteItem.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
		}
		
		
		if (indicadorModoConsultaTemporal!=null) {
			indicadorModoConsulta = indicadorModoConsultaTemporal;
		}
		
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
    	
//    	getListaEstadosCambio();
//    	getListasDivisas();
//    	getListasDivisasCambios();
    	
    }
	
//	public void getListaEstadosCambio() {
//		listaComboEstadoTipoCambio = new ArrayList<SelectItem>();
//		listaComboEstadoTipoCambio.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
//		listaComboEstadoTipoCambio.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
//	}

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
	
//	public void getListasDivisasCambios() {
//		
//		listaComboDivisasCambio = new ArrayList<SelectItem>();
//		
//		ServiceDivisa serviceDivisa = new ServiceDivisaImpl();
//
//		for(TpDivisDto temp : serviceDivisa.getDivisas(null)) {
//			listaComboDivisasCambio.add(new SelectItem(temp.getCodDivi(), temp.getNomDiviSing()));
//		}
//
//	}
	
	public void procesarGuardarUsuario() {
		
    	System.out.println("Entro a validar");
    	String result = null;
        
    	ServiceTipoCambio serviceTipoCambio = new ServiceTipoCambioImpl();
    	if(ValidacionesString.esNuloOVacio(usuarioClienteItem.getUsuApliCrea())) {
        	usuarioClienteItem.setUsuApliCrea(String.valueOf(ideUsuaEmai));
        	usuarioClienteItem.setFecCreaRegi(new Date());
    	}
    	usuarioClienteItem.setUsuApliModi(String.valueOf(ideUsuaEmai));
    	usuarioClienteItem.setFecModiRegi(new Date());
//    	result = serviceTipoCambio.insertUpdate(usuarioClienteItem);
    	
        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {

//        	resultadoProcesoExito = "Datos guardados con éxito.";
        	usuarioClienteItem = new TpClienDto();
        	PrimeFaces.current().executeScript("operacionGuardarUsuarioExitosa();");
        }else {
        	resultadoProcesoError = result;
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().executeScript("procesoConError();");
        }

    }

	public void regresar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_CAMBIO.getValor());
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
    
	public String getResultadoProcesoError() {	
		return resultadoProcesoError;
	}

	public void setResultadoProcesoError(String resultadoProcesoError) {
		this.resultadoProcesoError = resultadoProcesoError;
	}

	public String getValorNombre() {
		return valorNombre;
	}

	public void setValorNombre(String valorNombre) {
		this.valorNombre = valorNombre;
	}

	public String getResultadoProcesoExito() {
		return resultadoProcesoExito;
	}

	public void setResultadoProcesoExito(String resultadoProcesoExito) {
		this.resultadoProcesoExito = resultadoProcesoExito;
	}

	public String getIdeUsuaEmai() {
		return ideUsuaEmai;
	}

	public void setIdeUsuaEmai(String ideUsuaEmai) {
		this.ideUsuaEmai = ideUsuaEmai;
	}

	public Boolean getIndicadorModoConsulta() {
		return indicadorModoConsulta;
	}

	public void setIndicadorModoConsulta(Boolean indicadorModoConsulta) {
		this.indicadorModoConsulta = indicadorModoConsulta;
	}


	
}
