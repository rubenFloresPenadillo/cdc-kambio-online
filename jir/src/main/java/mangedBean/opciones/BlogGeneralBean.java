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
import dto.TpEntraDto;
import loggerUtil.LoggerUtil;
import service.ServiceEntrada;
import service.impl.ServiceEntradaImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.PaginasPrivadasType;

@ManagedBean(name="blogGeneralBean")
@ViewScoped
public class BlogGeneralBean {

	private String resultadoProcesoError;
	private List<TpEntraDto> listaEntradas;
	private TpEntraDto entradaSeleccionada;
//	private Integer codigoCliente;
	private String valorNombre;
	
	public BlogGeneralBean() {
		entradaSeleccionada = new TpEntraDto();
		listaEntradas = new LinkedList<TpEntraDto>();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor BlogGeneralBean");
		
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
    	
    	try {
			getEntradas();
		} catch (IOException e) {
			LoggerUtil.getInstance().getLogger().error(e.getMessage());
		    LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void getEntradas() throws IOException {
    	ServiceEntrada serviceEntrada = new ServiceEntradaImpl();
    	listaEntradas = serviceEntrada.getEntradas(null);
    }
    
    public void seleccionarEntradaEditar(TpEntraDto TpEntraDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("entradaItemSeleccionado", TpEntraDto);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_ENTRADA_GENERAL_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void seleccionarEntradaDetalle(TpEntraDto TpEntraDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("entradaItemSeleccionado", TpEntraDto);
		sesion.setAttribute("indicadorModoConsulta", Boolean.TRUE);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_ENTRADA_GENERAL_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void procesarRefrescarBlogGeneral() {
    	try {
			getEntradas();
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

	public List<TpEntraDto> getListaEntradas() {
		return listaEntradas;
	}

	public void setListaEntradas(List<TpEntraDto> listaEntradas) {
		this.listaEntradas = listaEntradas;
	}

	public TpEntraDto getEntradaSeleccionada() {
		return entradaSeleccionada;
	}

	public void setEntradaSeleccionada(TpEntraDto entradaSeleccionada) {
		this.entradaSeleccionada = entradaSeleccionada;
	}

	
}
