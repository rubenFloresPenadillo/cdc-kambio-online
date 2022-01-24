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
import dto.TpBancoDto;
import javax.servlet.http.HttpSession;
import loggerUtil.LoggerUtil;
import service.ServiceBanco;
import service.impl.ServiceBancoImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.PaginasPrivadasType;
import util.types.RegistroActivoType;

@ManagedBean(name="bancoItemBean")
@ViewScoped
public class BancoItemBean {

	private String resultadoProcesoExito;
	private String resultadoProcesoError;
	private TpBancoDto bancoItem;
	private String valorNombre;
	private String ideUsuaEmai;
	private List<SelectItem> listaComboEstadoBanco;
	private Boolean indicadorModoConsulta;
	private List<SelectItem> listaComboIndVistaClientes;
	private List<SelectItem> listaComboIndVistaAdministracion;
	private List<SelectItem> listaComboIndCuentaNegocio;
	
	public BancoItemBean() {
		
		HttpSession sesion = ConeccionSesion.getSession();
		ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
		
		bancoItem = new TpBancoDto();
		indicadorModoConsulta = Boolean.FALSE;
		resultadoProcesoExito = CadenasType.VACIO.getValor();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor BancoItemBean");
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase OperacionesControlBean.
     */
    @PostConstruct
    public void init() {
    	
    	HttpSession sesion = ConeccionSesion.getSession();
    	TpBancoDto bancoItemSeleccionado = (TpBancoDto) sesion.getAttribute("bancoItemSeleccionado");
		Boolean indicadorModoConsultaTemporal = (Boolean) sesion.getAttribute("indicadorModoConsulta");
		sesion.removeAttribute("bancoItemSeleccionado");
		sesion.removeAttribute("indicadorModoConsulta");
		
		if(bancoItemSeleccionado != null) {
			bancoItem = bancoItemSeleccionado;
		} else {
			bancoItem.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
		}
		
		
		if (indicadorModoConsultaTemporal!=null) {
			indicadorModoConsulta = indicadorModoConsultaTemporal;
		}
		
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
    	
    	getListaEstadosBanco();
    	getListaIndVistaClientes();
    	getListaIndVistaAdministracion();
    	getListaIndCuentaNegocio();
    }
	
	public void getListaEstadosBanco() {
		listaComboEstadoBanco = new ArrayList<SelectItem>();
		listaComboEstadoBanco.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
		listaComboEstadoBanco.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
	}

	public void getListaIndVistaClientes() {
		listaComboIndVistaClientes = new ArrayList<SelectItem>();
		listaComboIndVistaClientes.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
		listaComboIndVistaClientes.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
	}
	
	public void getListaIndVistaAdministracion() {
		listaComboIndVistaAdministracion = new ArrayList<SelectItem>();
		listaComboIndVistaAdministracion.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
		listaComboIndVistaAdministracion.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
	}
	
	public void getListaIndCuentaNegocio() {
		listaComboIndCuentaNegocio = new ArrayList<SelectItem>();
		listaComboIndCuentaNegocio.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
		listaComboIndCuentaNegocio.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
	}
	
	public void procesarGuardarBanco() {
		
    	System.out.println("Entro a validar");
    	String result = null;
        
    	ServiceBanco serviceBanco = new ServiceBancoImpl();
    	if(ValidacionesString.esNuloOVacio(bancoItem.getUsuApliCrea())) {
        	bancoItem.setUsuApliCrea(String.valueOf(ideUsuaEmai));
        	bancoItem.setFecCreaRegi(new Date());
    	}
    	bancoItem.setUsuApliModi(String.valueOf(ideUsuaEmai));
    	bancoItem.setFecModiRegi(new Date());
    	result = serviceBanco.insertUpdate(bancoItem);
    	
        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        	bancoItem = new TpBancoDto();
        	PrimeFaces.current().executeScript("operacionGuardarBancoExitosa();");
        }else {
        	resultadoProcesoError = result;
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().executeScript("procesoConError();");
        }

    }

	public void regresar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_BANCO.getValor());
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

	public TpBancoDto getBancoItem() {
		return bancoItem;
	}

	public void setBancoItem(TpBancoDto bancoItem) {
		this.bancoItem = bancoItem;
	}

	public List<SelectItem> getListaComboEstadoBanco() {
		return listaComboEstadoBanco;
	}

	public void setListaComboEstadoBanco(List<SelectItem> listaComboEstadoBanco) {
		this.listaComboEstadoBanco = listaComboEstadoBanco;
	}

	public Boolean getIndicadorModoConsulta() {
		return indicadorModoConsulta;
	}

	public void setIndicadorModoConsulta(Boolean indicadorModoConsulta) {
		this.indicadorModoConsulta = indicadorModoConsulta;
	}

	public List<SelectItem> getListaComboIndVistaClientes() {
		return listaComboIndVistaClientes;
	}

	public void setListaComboIndVistaClientes(List<SelectItem> listaComboIndVistaClientes) {
		this.listaComboIndVistaClientes = listaComboIndVistaClientes;
	}

	public List<SelectItem> getListaComboIndVistaAdministracion() {
		return listaComboIndVistaAdministracion;
	}

	public void setListaComboIndVistaAdministracion(List<SelectItem> listaComboIndVistaAdministracion) {
		this.listaComboIndVistaAdministracion = listaComboIndVistaAdministracion;
	}

	public List<SelectItem> getListaComboIndCuentaNegocio() {
		return listaComboIndCuentaNegocio;
	}

	public void setListaComboIndCuentaNegocio(List<SelectItem> listaComboIndCuentaNegocio) {
		this.listaComboIndCuentaNegocio = listaComboIndCuentaNegocio;
	}
	
	

}
