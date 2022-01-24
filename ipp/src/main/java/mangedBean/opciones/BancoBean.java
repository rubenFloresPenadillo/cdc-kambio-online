package mangedBean.opciones;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import cadenas.util.ValidacionesString;
import dto.TpBancoDto;
import javax.servlet.http.HttpSession;
import loggerUtil.LoggerUtil;
import service.ServiceBanco;
import service.impl.ServiceBancoImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.PaginasPrivadasType;

@ManagedBean(name="bancoBean")
@ViewScoped
public class BancoBean {

	private String resultadoProcesoError;
	private List<TpBancoDto> listaBancos;
	private TpBancoDto bancoSeleccionado;
//	private Integer codigoCliente;
	private String valorNombre;
	
	public BancoBean() {
		bancoSeleccionado = new TpBancoDto();
		listaBancos = new LinkedList<TpBancoDto>();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor BancoBean");
		
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
    	
    	getBancos();
    }
    
    public void getBancos() {
    	ServiceBanco serviceBanco = new ServiceBancoImpl();
    	listaBancos = serviceBanco.getBancos(null);
    }
    
    public void seleccionarBancoEditar(TpBancoDto tpBancoDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("bancoItemSeleccionado", tpBancoDto);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_BANCO_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void seleccionarBancoDetalle(TpBancoDto tpBancoDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("bancoItemSeleccionado", tpBancoDto);
		sesion.setAttribute("indicadorModoConsulta", Boolean.TRUE);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_BANCO_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void procesarRefrescarBanco() {
    	getBancos();
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

	public List<TpBancoDto> getListaBancos() {
		return listaBancos;
	}

	public void setListaBancos(List<TpBancoDto> listaBancos) {
		this.listaBancos = listaBancos;
	}

	public TpBancoDto getBancoSeleccionado() {
		return bancoSeleccionado;
	}

	public void setBancoSeleccionado(TpBancoDto bancoSeleccionado) {
		this.bancoSeleccionado = bancoSeleccionado;
	}

}
