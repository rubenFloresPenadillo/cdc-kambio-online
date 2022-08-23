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
import dto.TpParamGenerDto;
import loggerUtil.LoggerUtil;
import service.ServiceParametroGeneral;
import service.impl.ServiceParametroGeneralImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.PaginasPrivadasType;

@ManagedBean(name="parametrosGeneralesBean")
@ViewScoped
public class ParametrosGeneralesBean {

	private String resultadoProcesoError;
	private List<TpParamGenerDto> listaParametrosGenerales;
	private TpParamGenerDto parametroGeneralSeleccionado;
	private Integer codigoCliente;
	private String valorNombre;
	
	public ParametrosGeneralesBean() {
		parametroGeneralSeleccionado = new TpParamGenerDto();
		listaParametrosGenerales = new LinkedList<TpParamGenerDto>();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor parametrosGeneralesBean");
		
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
    	
    	getParametrosGenerales();
    }
    
    public void getParametrosGenerales() {
    	ServiceParametroGeneral serviceParametroGeneral = new ServiceParametroGeneralImpl();
    	listaParametrosGenerales = serviceParametroGeneral.getParametrosGenerales(null);
   	
    }

    public void seleccionarParametroGeneralEditar(TpParamGenerDto tpParamGenerDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("parametroGeneralItemSeleccionado", tpParamGenerDto);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_PARAMETRO_GENERAL_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void seleccionarParametroGeneralDetalle(TpParamGenerDto tpParamGenerDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("parametroGeneralItemSeleccionado", tpParamGenerDto);
		sesion.setAttribute("indicadorModoConsulta", Boolean.TRUE);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_PARAMETRO_GENERAL_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void procesarRefrescarParametroGeneral() {
    	getParametrosGenerales();
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

	public List<TpParamGenerDto> getListaParametrosGenerales() {
		return listaParametrosGenerales;
	}

	public void setListaParametrosGenerales(List<TpParamGenerDto> listaParametrosGenerales) {
		this.listaParametrosGenerales = listaParametrosGenerales;
	}

	public TpParamGenerDto getParametroGeneralSeleccionado() {
		return parametroGeneralSeleccionado;
	}

	public void setParametroGeneralSeleccionado(TpParamGenerDto parametroGeneralSeleccionado) {
		this.parametroGeneralSeleccionado = parametroGeneralSeleccionado;
	}
    
}
