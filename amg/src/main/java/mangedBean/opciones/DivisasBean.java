package mangedBean.opciones;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import cadenas.util.ValidacionesString;
import dto.TpDivisDto;
import loggerUtil.LoggerUtil;
import dto.TpDivisDto;
import service.ServiceDivisa;
import service.ServiceOperacionCliente;
import service.impl.ServiceDivisaImpl;
import service.impl.ServiceOperacionClienteImpl;
import util.notificaciones.NotificacionUtil;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.ElementosTablasType;
import util.types.PaginasPrivadasType;
import util.types.PlantillasType;

@ManagedBean(name="divisasBean")
@ViewScoped
public class DivisasBean {

	private String resultadoProcesoError;
	private List<TpDivisDto> listaDivisas;
	private TpDivisDto divisaSeleccionada;
	private Integer codigoCliente;
	private String valorNombre;
	
	public DivisasBean() {
		divisaSeleccionada = new TpDivisDto();
		listaDivisas = new LinkedList<TpDivisDto>();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor DivisasBean");
		
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
    	
    	getDivisas();
    }
    
    public void getDivisas() {
    	ServiceDivisa serviceDivisa = new ServiceDivisaImpl();
    	listaDivisas = serviceDivisa.getDivisas(null);
   	
    }

    public void seleccionarDivisaEditar(TpDivisDto tpDivisDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("divisaItemSeleccionada", tpDivisDto);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_DIVISA_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void seleccionarDivisaDetalle(TpDivisDto tpDivisDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("divisaItemSeleccionada", tpDivisDto);
		sesion.setAttribute("indicadorModoConsulta", Boolean.TRUE);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_DIVISA_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void procesarRefrescarDivisa() {
    	getDivisas();
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
    
	public String getValorNombre() {
		return valorNombre;
	}

	public void setValorNombre(String valorNombre) {
		this.valorNombre = valorNombre;
	}

	public String getResultadoProcesoError() {
		return resultadoProcesoError;
	}

	public void setResultadoProcesoError(String resultadoProcesoError) {
		this.resultadoProcesoError = resultadoProcesoError;
	}

	public List<TpDivisDto> getListaDivisas() {
		return listaDivisas;
	}

	public void setListaDivisas(List<TpDivisDto> listaDivisas) {
		this.listaDivisas = listaDivisas;
	}

	public TpDivisDto getDivisaSeleccionada() {
		return divisaSeleccionada;
	}

	public void setDivisaSeleccionada(TpDivisDto divisaSeleccionada) {
		this.divisaSeleccionada = divisaSeleccionada;
	}

	
    
}
