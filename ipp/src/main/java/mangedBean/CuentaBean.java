package mangedBean;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import cadenas.util.ValidacionesString;
import dto.TpCuentBancoDto;
import javax.servlet.http.HttpSession;
import loggerUtil.LoggerUtil;
import service.ServiceCliente;
import service.impl.ServiceClienteImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;
import util.types.PerfilesType;

@ManagedBean(name="cuentaBean")
@ViewScoped
public class CuentaBean {

	private String resultadoProcesoError;
	private List<TpCuentBancoDto> listaCuentasBancarias;
	private TpCuentBancoDto cuentaBancariaSeleccionada;
	private Integer codigoCliente;
	private Integer indCompleDatos;
	private Integer codPerfUsua;
	private String valorNombre;
	private Boolean indDatosEmpresa;
	private Integer codigoUsuario;
	private Integer codigoUsuarioPadre;	
	
	public CuentaBean() {
		cuentaBancariaSeleccionada = new TpCuentBancoDto();
		listaCuentasBancarias = new LinkedList<TpCuentBancoDto>();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor CuentaBean");
		indDatosEmpresa = Boolean.FALSE;
		
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase OperacionesControlBean.
     */
    @PostConstruct
    public void init() {
    	
    	HttpSession sesion = ConeccionSesion.getSession();
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	codigoCliente = (Integer) sesion.getAttribute("codigoCliente");
    	indCompleDatos = (Integer) sesion.getAttribute("indCompleDatos");
    	codPerfUsua = (Integer) sesion.getAttribute("codPerfUsua");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
    	
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
			getCuentasBancarias();
		}	
    }
    
    public void getCuentasBancarias() {
    	ServiceCliente serviceCliente = new ServiceClienteImpl();
    	TpCuentBancoDto tpCuentBancoDto = new TpCuentBancoDto();
    	tpCuentBancoDto.getTpClien().setCodClie(codigoCliente);
    	listaCuentasBancarias = serviceCliente.getCuentasBanco(tpCuentBancoDto);
   	
    }

    public void seleccionarCuentaBancariaEditar(TpCuentBancoDto TpCuentBancoDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("cuentaBancariaItemSeleccionado", TpCuentBancoDto);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_CUENTA_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void seleccionarCuentaBancariaDetalle(TpCuentBancoDto TpCuentBancoDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("cuentaBancariaItemSeleccionado", TpCuentBancoDto);
		sesion.setAttribute("indicadorModoConsulta", Boolean.TRUE);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_CUENTA_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void procesarRefrescarBancaria() {
    	getCuentasBancarias();
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

	public String getResultadoProcesoError() {
		return resultadoProcesoError;
	}

	public void setResultadoProcesoError(String resultadoProcesoError) {
		this.resultadoProcesoError = resultadoProcesoError;
	}

	public TpCuentBancoDto getCuentaBancariaSeleccionada() {
		return cuentaBancariaSeleccionada;
	}

	public void setCuentaBancariaSeleccionada(TpCuentBancoDto cuentaBancariaSeleccionada) {
		this.cuentaBancariaSeleccionada = cuentaBancariaSeleccionada;
	}

	public List<TpCuentBancoDto> getListaCuentasBancarias() {
		return listaCuentasBancarias;
	}

	public void setListaCuentasBancarias(List<TpCuentBancoDto> listaCuentasBancarias) {
		this.listaCuentasBancarias = listaCuentasBancarias;
	}

	public Boolean getIndDatosEmpresa() {
		return indDatosEmpresa;
	}

	public void setIndDatosEmpresa(Boolean indDatosEmpresa) {
		this.indDatosEmpresa = indDatosEmpresa;
	}

	
	
}
