package mangedBean.opciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.PrimeFaces;

import cadenas.util.ValidacionesString;
import dto.TpDivisDto;
import javax.servlet.http.HttpSession;
import loggerUtil.LoggerUtil;
import service.ServiceDivisa;
import service.impl.ServiceDivisaImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.PaginasPrivadasType;
import util.types.RegistroActivoType;

@ManagedBean(name="divisaItemBean")
@ViewScoped
public class DivisasItemBean {

	private String resultadoProcesoExito;
	private String resultadoProcesoError;
	private TpDivisDto divisaItem;
	private String valorNombre;
	private String ideUsuaEmai;
	private List<SelectItem> listaComboIndAplicaCuentasBancarias;
	private List<SelectItem> listaComboEstadoDivisa;
	private Boolean indicadorModoConsulta;
	
	public DivisasItemBean() {
		
		HttpSession sesion = ConeccionSesion.getSession();
		ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
		
		divisaItem = new TpDivisDto();
		indicadorModoConsulta = Boolean.FALSE;
		resultadoProcesoExito = CadenasType.VACIO.getValor();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor DivisasItemBean");
	}
	
    /**
     * Realiza su ejecuciï¿½n despues del Constructor de la clase OperacionesControlBean.
     */
    @PostConstruct
    public void init() {
    	
    	HttpSession sesion = ConeccionSesion.getSession();
		TpDivisDto cabeceraOrigen = (TpDivisDto) sesion.getAttribute("divisaItemSeleccionada");
		Boolean indicadorModoConsultaTemporal = (Boolean) sesion.getAttribute("indicadorModoConsulta");
		sesion.removeAttribute("divisaItemSeleccionada");
		sesion.removeAttribute("indicadorModoConsulta");
		
		if(cabeceraOrigen!=null) {
			divisaItem = cabeceraOrigen;
		}
		
		if (indicadorModoConsultaTemporal!=null) {
			indicadorModoConsulta = indicadorModoConsultaTemporal;
		} else {
			divisaItem.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
		}
		
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
    	
    	
    	getListaIndAplicaCuentasBancarias() ;
    	getListaEstadosDivisa();
    }

	public void getListaIndAplicaCuentasBancarias() {
		listaComboIndAplicaCuentasBancarias = new ArrayList<SelectItem>();
		listaComboIndAplicaCuentasBancarias.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
		listaComboIndAplicaCuentasBancarias.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
	}
	
	public void getListaEstadosDivisa() {
		listaComboEstadoDivisa = new ArrayList<SelectItem>();
		listaComboEstadoDivisa.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
		listaComboEstadoDivisa.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
	}
    
	
	public void procesarGuardarDivisa() {
		
    	System.out.println("Entro a validar");
    	String result = null;
        
    	ServiceDivisa serviceDivisa = new ServiceDivisaImpl();
    	if(ValidacionesString.esNuloOVacio(divisaItem.getUsuApliCrea())) {
        	divisaItem.setUsuApliCrea(String.valueOf(ideUsuaEmai));
        	divisaItem.setFecCreaRegi(new Date());
    	}
    	divisaItem.setUsuApliModi(String.valueOf(ideUsuaEmai));
    	divisaItem.setFecModiRegi(new Date());
    	result = serviceDivisa.insertUpdate(divisaItem);
    	
        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {

//        	resultadoProcesoExito = "Datos guardados con éxito.";
        	divisaItem = new TpDivisDto();
        	PrimeFaces.current().executeScript("operacionGuardarDivisaExitosa();");
        }else {
        	resultadoProcesoError = result;
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().executeScript("procesoConError();");
        }

    }
	
	public void regresar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_DIVISAS.getValor());
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

	public TpDivisDto getDivisaItem() {
		return divisaItem;
	}

	public void setDivisaItem(TpDivisDto divisaItem) {
		this.divisaItem = divisaItem;
	}

	public String getValorNombre() {
		return valorNombre;
	}

	public void setValorNombre(String valorNombre) {
		this.valorNombre = valorNombre;
	}

	public List<SelectItem> getListaComboIndAplicaCuentasBancarias() {
		return listaComboIndAplicaCuentasBancarias;
	}

	public void setListaComboIndAplicaCuentasBancarias(List<SelectItem> listaComboIndAplicaCuentasBancarias) {
		this.listaComboIndAplicaCuentasBancarias = listaComboIndAplicaCuentasBancarias;
	}

	public List<SelectItem> getListaComboEstadoDivisa() {
		return listaComboEstadoDivisa;
	}

	public void setListaComboEstadoDivisa(List<SelectItem> listaComboEstadoDivisa) {
		this.listaComboEstadoDivisa = listaComboEstadoDivisa;
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
