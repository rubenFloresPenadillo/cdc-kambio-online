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
import dto.TpCuentBancoDto;
import loggerUtil.LoggerUtil;
import service.ServiceCliente;
import service.impl.ServiceClienteImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;

@ManagedBean(name="cuentaBancariaAdminBean")
@ViewScoped
public class CuentaBancariaAdminBean {

	private String resultadoProcesoError;
	private List<TpCuentBancoDto> listaCuentasBancariasAdmin;
	private TpCuentBancoDto cuentaBancariaSeleccionada;
//	private Integer codigoCliente;
	private String valorNombre;
	
	public CuentaBancariaAdminBean() {
		cuentaBancariaSeleccionada = new TpCuentBancoDto();
		listaCuentasBancariasAdmin = new LinkedList<TpCuentBancoDto>();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor CuentaBancariaAdminBean");
		
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
    	
    	getCuentasBancariasAdmin();
    }
    
    public void getCuentasBancariasAdmin() {
    	ServiceCliente serviceCliente = new ServiceClienteImpl();
    	TpCuentBancoDto tpCuentBancoDtoAdmin = new TpCuentBancoDto();
    	tpCuentBancoDtoAdmin.getTpClien().setCodClie(NumerosType.INDICADOR_POSITIVO_UNO.getValor());
    	listaCuentasBancariasAdmin = serviceCliente.getCuentasBanco(tpCuentBancoDtoAdmin);
   	
    }

    public void seleccionarCuentaBancariaAdminEditar(TpCuentBancoDto TpCuentBancoDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("cuentaBancariaAdminItemSeleccionado", TpCuentBancoDto);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_CUENTA_BANCARIA_ADMIN_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void seleccionarCuentaBancariaAdminDetalle(TpCuentBancoDto TpCuentBancoDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("cuentaBancariaAdminItemSeleccionado", TpCuentBancoDto);
		sesion.setAttribute("indicadorModoConsulta", Boolean.TRUE);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_CUENTA_BANCARIA_ADMIN_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void procesarRefrescarBancariaAdmin() {
    	getCuentasBancariasAdmin();
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

	public List<TpCuentBancoDto> getListaCuentasBancariasAdmin() {
		return listaCuentasBancariasAdmin;
	}

	public void setListaCuentasBancariasAdmin(List<TpCuentBancoDto> listaCuentasBancariasAdmin) {
		this.listaCuentasBancariasAdmin = listaCuentasBancariasAdmin;
	}

	public TpCuentBancoDto getCuentaBancariaSeleccionada() {
		return cuentaBancariaSeleccionada;
	}

	public void setCuentaBancariaSeleccionada(TpCuentBancoDto cuentaBancariaSeleccionada) {
		this.cuentaBancariaSeleccionada = cuentaBancariaSeleccionada;
	}

}
