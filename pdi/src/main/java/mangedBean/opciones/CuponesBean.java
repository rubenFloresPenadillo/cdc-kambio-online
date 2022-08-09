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
import dto.TpCuponDto;
import loggerUtil.LoggerUtil;
import service.ServiceCupon;
import service.impl.ServiceCuponImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.PaginasPrivadasType;

@ManagedBean(name="cuponesBean")
@ViewScoped
public class CuponesBean {

	private String resultadoProcesoError;
//	private List<TpBancoDto> listaBancos;
	private List<TpCuponDto> listaCupones;
//	private TpBancoDto bancoSeleccionado;
//	private Integer codigoCliente;
	private String valorNombre;
	
	public CuponesBean() {
//		bancoSeleccionado = new TpBancoDto();
		listaCupones = new LinkedList<TpCuponDto>();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor CuponesBean");
		
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
    	
    	getCupones();
    }
    
    public void getCupones() {
    	ServiceCupon serviceCupon = new ServiceCuponImpl();
    	listaCupones = serviceCupon.getCupones(null);
    }
    
    public void seleccionarCuponEditar(TpCuponDto tpCuponDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("cuponItemSeleccionado", tpCuponDto);
		sesion.setAttribute("indicadorModoEdicion", Boolean.TRUE);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_CUPON_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void seleccionarCuponDetalle(TpCuponDto tpCuponDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("cuponItemSeleccionado", tpCuponDto);
		sesion.setAttribute("indicadorModoConsulta", Boolean.TRUE);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_CUPON_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void procesarRefrescarCupon() {
    	getCupones();
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

	public List<TpCuponDto> getListaCupones() {
		return listaCupones;
	}

	public void setListaCupones(List<TpCuponDto> listaCupones) {
		this.listaCupones = listaCupones;
	}



}
