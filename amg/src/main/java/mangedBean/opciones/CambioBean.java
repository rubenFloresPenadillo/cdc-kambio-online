package mangedBean.opciones;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import cadenas.util.ValidacionesString;
import dto.TpTipoCambiDto;
import loggerUtil.LoggerUtil;
import service.ServiceTipoCambio;
import service.impl.ServiceTipoCambioImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.PaginasPrivadasType;

@ManagedBean(name="cambioBean")
@ViewScoped
public class CambioBean {

	private String resultadoProcesoError;
	private List<TpTipoCambiDto> listaCambios;
	private TpTipoCambiDto cambioSeleccionado;
//	private Integer codigoCliente;
	private String valorNombre;
	
	public CambioBean() {
		cambioSeleccionado = new TpTipoCambiDto();
		listaCambios = new LinkedList<TpTipoCambiDto>();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor CambioBean");
		
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
    	
    	getCambios();
    }
    
    public void getCambios() {
    	ServiceTipoCambio serviceTipoCambio = new ServiceTipoCambioImpl();
    	listaCambios = serviceTipoCambio.getTipoCambios(null);
   	
    }

    public void seleccionarCambioEditar(TpTipoCambiDto TpTipoCambiDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("cambioItemSeleccionado", TpTipoCambiDto);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_CAMBIO_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void seleccionarCambioDetalle(TpTipoCambiDto TpTipoCambiDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("cambioItemSeleccionado", TpTipoCambiDto);
		sesion.setAttribute("indicadorModoConsulta", Boolean.TRUE);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_CAMBIO_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void procesarRefrescarCambio() {
    	getCambios();
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

	public List<TpTipoCambiDto> getListaCambios() {
		return listaCambios;
	}

	public void setListaCambios(List<TpTipoCambiDto> listaCambios) {
		this.listaCambios = listaCambios;
	}

	public TpTipoCambiDto getCambioSeleccionado() {
		return cambioSeleccionado;
	}

	public void setCambioSeleccionado(TpTipoCambiDto cambioSeleccionado) {
		this.cambioSeleccionado = cambioSeleccionado;
	}

	
    
}
