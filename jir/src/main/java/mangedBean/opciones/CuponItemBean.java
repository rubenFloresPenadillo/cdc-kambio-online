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
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import cadenas.util.ValidacionesString;
import dto.TpCuponDto;
import loggerUtil.LoggerUtil;
import service.ServiceBanco;
import service.ServiceCupon;
import service.impl.ServiceBancoImpl;
import service.impl.ServiceCuponImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.ElementosTablasType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;
import util.types.RegistroActivoType;
import util.types.TipoPerfilCuentaType;

@ManagedBean(name="cuponItemBean")
@ViewScoped
public class CuponItemBean {

	private String resultadoProcesoExito;
	private String resultadoProcesoError;
	private TpCuponDto cuponItem;
	private String valorNombre;
	private String ideUsuaEmai;
	
	private Boolean indicadorModoConsulta;
	private Boolean indicadorModoEdicion;
	private List<SelectItem> listaComboTipoPerfilCuenta;
	private List<SelectItem> listaComboTipoOperacion;
//	private List<SelectItem> listaComboIndVistaAdministracion;
//	private List<SelectItem> listaComboIndCuentaNegocio;
	
	private Integer indSeleccionReutiCliente;
	private Integer indCuponActivo;
	
	public CuponItemBean() {
		
		HttpSession sesion = ConeccionSesion.getSession();
		ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
		
		cuponItem = new TpCuponDto();
		indicadorModoConsulta = Boolean.FALSE;
		resultadoProcesoExito = CadenasType.VACIO.getValor();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		indSeleccionReutiCliente = NumerosType.NUMERO_MINIMO_CERO.getValor(); 
		indCuponActivo = NumerosType.INDICADOR_POSITIVO_UNO.getValor(); 
		System.out.println("Entro al constructor CuponItemBean");
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase OperacionesControlBean.
     */
    @PostConstruct
    public void init() {
    	
    	HttpSession sesion = ConeccionSesion.getSession();
    	TpCuponDto cuponItemSeleccionado = (TpCuponDto) sesion.getAttribute("cuponItemSeleccionado");
		Boolean indicadorModoConsultaTemporal = (Boolean) sesion.getAttribute("indicadorModoConsulta");
		Boolean indicadorModoEdicionTemporal = (Boolean) sesion.getAttribute("indicadorModoEdicion");
		sesion.removeAttribute("cuponItemSeleccionado");
		sesion.removeAttribute("indicadorModoConsulta");
		sesion.removeAttribute("indicadorModoEdicion");
		
		if(cuponItemSeleccionado != null) {
			cuponItem = cuponItemSeleccionado;
		} else {
			cuponItem.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
			cuponItem.setIndReutClie(RegistroActivoType.INACTIVO.getLlave());
			cuponItem.setFecInicVige(new Date());
		}
		
		
		if (indicadorModoConsultaTemporal!=null) {
			indicadorModoConsulta = indicadorModoConsultaTemporal;
		}
		
		if (indicadorModoEdicionTemporal!=null) {
			indicadorModoEdicion = indicadorModoEdicionTemporal;
		}
		
		
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
    	
    	getListaTipoOperacion();
    	getListaTipoPerfilCuenta();
//    	getListaIndVistaClientes();
//    	getListaIndVistaAdministracion();
//    	getListaIndCuentaNegocio();
    }
	
	public void getListaTipoOperacion() {
		listaComboTipoOperacion = new ArrayList<SelectItem>();
		listaComboTipoOperacion.add(new SelectItem(ElementosTablasType.TIPO_SERVICIO_COMPRA.getIdElemento(), ElementosTablasType.TIPO_SERVICIO_COMPRA.getNombreElemento()));
		listaComboTipoOperacion.add(new SelectItem(ElementosTablasType.TIPO_SERVICIO_VENTA.getIdElemento(), ElementosTablasType.TIPO_SERVICIO_VENTA.getNombreElemento()));
	}
	
	
	public void getListaTipoPerfilCuenta() {
		listaComboTipoPerfilCuenta = new ArrayList<SelectItem>();
		listaComboTipoPerfilCuenta.add(new SelectItem(TipoPerfilCuentaType.TIPO_PERFIL_CUENTA_PERSONA.getIdElemento(), TipoPerfilCuentaType.TIPO_PERFIL_CUENTA_PERSONA.getNombreElemento()));
		listaComboTipoPerfilCuenta.add(new SelectItem(TipoPerfilCuentaType.TIPO_PERFIL_CUENTA_EMPRESA.getIdElemento(), TipoPerfilCuentaType.TIPO_PERFIL_CUENTA_EMPRESA.getNombreElemento()));
	}

//	public void getListaIndVistaClientes() {
//		listaComboIndVistaClientes = new ArrayList<SelectItem>();
//		listaComboIndVistaClientes.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
//		listaComboIndVistaClientes.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
//	}
	
//	public void getListaIndVistaAdministracion() {
//		listaComboIndVistaAdministracion = new ArrayList<SelectItem>();
//		listaComboIndVistaAdministracion.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
//		listaComboIndVistaAdministracion.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
//	}
	
//	public void getListaIndCuentaNegocio() {
//		listaComboIndCuentaNegocio = new ArrayList<SelectItem>();
//		listaComboIndCuentaNegocio.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
//		listaComboIndCuentaNegocio.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
//	}
	
	public void procesarGuardarCupon() {
		
    	System.out.println("Entro a validar");
    	String result = null;
        
    	ServiceCupon serviceCupon = new ServiceCuponImpl();
    	if(ValidacionesString.esNuloOVacio(cuponItem.getUsuApliCrea())) {
    		cuponItem.setUsuApliCrea(String.valueOf(ideUsuaEmai));
    		cuponItem.setFecCreaRegi(new Date());
    	}
    	cuponItem.setUsuApliModi(String.valueOf(ideUsuaEmai));
    	cuponItem.setFecModiRegi(new Date());
    	
    	if ( !ValidacionesString.esNuloOVacio(cuponItem.getEmaClieCupo())) {
    		cuponItem.setEmaClieCupo(cuponItem.getEmaClieCupo().trim().toUpperCase());
    	}
    	
    	if ( !ValidacionesString.esNuloOVacio(cuponItem.getNomCupo())) {
    		cuponItem.setNomCupo(cuponItem.getNomCupo().trim().toUpperCase());
    	}
    	
    	result = serviceCupon.insertUpdate(cuponItem);
    	
        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        	cuponItem = new TpCuponDto();
        	PrimeFaces.current().executeScript("operacionGuardarCuponExitosa();");
        }else {
        	resultadoProcesoError = result;
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().executeScript("procesoConError();");
        }

    }

	public void regresar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_CUPON.getValor());
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
    
	public void habilitarIndReutClie() {
		if(NumerosType.INDICADOR_POSITIVO_UNO.getValor().equals(indSeleccionReutiCliente)) {
			cuponItem.setIndReutClie(NumerosType.INDICADOR_POSITIVO_UNO.getValor());
		}else {
			cuponItem.setIndReutClie(NumerosType.NUMERO_MINIMO_CERO.getValor());
		}
	}
	
	public void habilitarIndicadorActivo() {
		if(NumerosType.INDICADOR_POSITIVO_UNO.getValor().equals(indCuponActivo)) {
			cuponItem.setIndEsta(NumerosType.INDICADOR_POSITIVO_UNO.getValor());
		}else {
			cuponItem.setIndEsta(NumerosType.NUMERO_MINIMO_CERO.getValor());
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

	public TpCuponDto getCuponItem() {
		return cuponItem;
	}

	public void setCuponItem(TpCuponDto cuponItem) {
		this.cuponItem = cuponItem;
	}

	public List<SelectItem> getListaComboTipoOperacion() {
		return listaComboTipoOperacion;
	}

	public void setListaComboTipoOperacion(List<SelectItem> listaComboTipoOperacion) {
		this.listaComboTipoOperacion = listaComboTipoOperacion;
	}

	public List<SelectItem> getListaComboTipoPerfilCuenta() {
		return listaComboTipoPerfilCuenta;
	}

	public void setListaComboTipoPerfilCuenta(List<SelectItem> listaComboTipoPerfilCuenta) {
		this.listaComboTipoPerfilCuenta = listaComboTipoPerfilCuenta;
	}
	
	public Boolean getIndicadorModoConsulta() {
		return indicadorModoConsulta;
	}

	public void setIndicadorModoConsulta(Boolean indicadorModoConsulta) {
		this.indicadorModoConsulta = indicadorModoConsulta;
	}

	public Integer getIndSeleccionReutiCliente() {
		return indSeleccionReutiCliente;
	}

	public void setIndSeleccionReutiCliente(Integer indSeleccionReutiCliente) {
		this.indSeleccionReutiCliente = indSeleccionReutiCliente;
	}

	public Integer getIndCuponActivo() {
		return indCuponActivo;
	}

	public void setIndCuponActivo(Integer indCuponActivo) {
		this.indCuponActivo = indCuponActivo;
	}

	public Boolean getIndicadorModoEdicion() {
		return indicadorModoEdicion;
	}

	public void setIndicadorModoEdicion(Boolean indicadorModoEdicion) {
		this.indicadorModoEdicion = indicadorModoEdicion;
	}
	
}
