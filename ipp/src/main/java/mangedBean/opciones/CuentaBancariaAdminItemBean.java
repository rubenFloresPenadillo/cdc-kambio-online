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
import dto.TpCuentBancoDto;
import dto.TpDivisDto;
import dto.TpTipoCuentDto;
import javax.servlet.http.HttpSession;
import loggerUtil.LoggerUtil;
import service.ServiceBanco;
import service.ServiceCliente;
import service.ServiceDivisa;
import service.impl.ServiceBancoImpl;
import service.impl.ServiceClienteImpl;
import service.impl.ServiceDivisaImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;
import util.types.RegistroActivoType;

@ManagedBean(name="cuentaBancariaAdminItemBean")
@ViewScoped
public class CuentaBancariaAdminItemBean {

	private String resultadoProcesoExito;
	private String resultadoProcesoError;
	private TpCuentBancoDto cuentaBancariaAdminItem;
	private String valorNombre;
	private String ideUsuaEmai;
	private List<SelectItem> listaComboEstadoCuentaBancariaAdmin;
	private List<SelectItem> listaComboDivisas;
	private List<SelectItem> listaComboBancos;
	private List<SelectItem> listaComboTipoCuentas;
//	private List<SelectItem> listaComboIndiTransfeBancarias;
//	private List<SelectItem> listaComboIndiCuentaPropia;
	
	private Boolean indicadorModoConsulta;
	public CuentaBancariaAdminItemBean() {
		
		HttpSession sesion = ConeccionSesion.getSession();
		ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
		
		cuentaBancariaAdminItem = new TpCuentBancoDto();
		indicadorModoConsulta = Boolean.FALSE;
		resultadoProcesoExito = CadenasType.VACIO.getValor();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor CambioItemBean");
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase OperacionesControlBean.
     */
    @PostConstruct
    public void init() {
    	
    	HttpSession sesion = ConeccionSesion.getSession();
    	TpCuentBancoDto cuentaBancariaAdminItemSeleccionado = (TpCuentBancoDto) sesion.getAttribute("cuentaBancariaAdminItemSeleccionado");
		Boolean indicadorModoConsultaTemporal = (Boolean) sesion.getAttribute("indicadorModoConsulta");
		sesion.removeAttribute("cuentaBancariaAdminItemSeleccionado");
		sesion.removeAttribute("indicadorModoConsulta");
		
		if(cuentaBancariaAdminItemSeleccionado != null) {
			cuentaBancariaAdminItem = cuentaBancariaAdminItemSeleccionado;
		} else {
			cuentaBancariaAdminItem.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
		}
		
		
		if (indicadorModoConsultaTemporal!=null) {
			indicadorModoConsulta = indicadorModoConsultaTemporal;
		}
		
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
    	
    	getListaEstadosCuentasBancariasAdmin();
    	getListasDivisas();
    	getListaBancos();
    	getListaTipoCuentas();
//    	getListaIndiTransfeBancarias();
//    	getListaIndiCuentaPropia();
    	
    }
	
//	public void getListaIndiTransfeBancarias() {
//		listaComboIndiTransfeBancarias = new ArrayList<SelectItem>();
//		listaComboIndiTransfeBancarias.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
//		listaComboIndiTransfeBancarias.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
//	}
	
//	public void getListaIndiCuentaPropia() {
////		listaComboIndiCuentaPropia = new ArrayList<SelectItem>();
////		listaComboIndiCuentaPropia.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcionAlternativa()));
////		listaComboIndiCuentaPropia.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcionAlternativa()));
//	}
//	
	

	public void getListasDivisas() {
		
		listaComboDivisas = new ArrayList<SelectItem>();
		
		ServiceDivisa serviceDivisa = new ServiceDivisaImpl();

		for(TpDivisDto temp : serviceDivisa.getDivisas(null)) {
			listaComboDivisas.add(new SelectItem(temp.getCodDivi(), temp.getNomDiviSing()));
		}

	}
	
	public void getListaBancos() {
		
		listaComboBancos = new ArrayList<SelectItem>();
		
		ServiceBanco serviceBanco = new ServiceBancoImpl();
		
		TpBancoDto tpBancoDto = new TpBancoDto();

		tpBancoDto.setIndVistAdmi( NumerosType.INDICADOR_POSITIVO_UNO.getValor());
		
		for(TpBancoDto temp : serviceBanco.getBancosDisponibles(tpBancoDto)) {
			listaComboBancos.add(new SelectItem(temp.getCodBanc(), temp.getNomBanc()));
		}

	}
	
	
	public void getListaTipoCuentas() {
		
		listaComboTipoCuentas = new ArrayList<SelectItem>();
		
		ServiceBanco serviceBanco = new ServiceBancoImpl();

		for(TpTipoCuentDto temp : serviceBanco.getTipoCuentasBacarias(null)) { 
			listaComboTipoCuentas.add(new SelectItem(temp.getCodTipoCuen(), temp.getDesTipoCuen()));
		}

	}
	
	public void getListaEstadosCuentasBancariasAdmin() {
		listaComboEstadoCuentaBancariaAdmin = new ArrayList<SelectItem>();
		listaComboEstadoCuentaBancariaAdmin.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
		listaComboEstadoCuentaBancariaAdmin.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
	}
	
	public void procesarGuardarCuentaBancariaAdmin() {
		
    	System.out.println("Entro a validar");
    	String result = null;
        
    	ServiceCliente serviceCliente = new ServiceClienteImpl();
    	if(ValidacionesString.esNuloOVacio(cuentaBancariaAdminItem.getUsuApliCrea())) {
        	cuentaBancariaAdminItem.setUsuApliCrea(String.valueOf(ideUsuaEmai));
        	cuentaBancariaAdminItem.setFecCreaRegi(new Date());
        	cuentaBancariaAdminItem.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
        	cuentaBancariaAdminItem.setIndCuenProp(RegistroActivoType.ACTIVO.getLlave());
    	}
    	cuentaBancariaAdminItem.getTpClien().setCodClie(NumerosType.INDICADOR_POSITIVO_UNO.getValor());
    	cuentaBancariaAdminItem.setUsuApliModi(String.valueOf(ideUsuaEmai));
    	cuentaBancariaAdminItem.setFecModiRegi(new Date());
    	result = serviceCliente.insertUpdateCuentaBanco(cuentaBancariaAdminItem);
    	
        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {

        	cuentaBancariaAdminItem = new TpCuentBancoDto();
        	PrimeFaces.current().executeScript("operacionGuardarCuentaBancariaAdminExitosa();");
        }else {
        	resultadoProcesoError = result;
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().executeScript("procesoConError();");
        }

    }

	public void regresar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_CUENTA_BANCARIA_ADMIN.getValor());
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

	public Boolean getIndicadorModoConsulta() {
		return indicadorModoConsulta;
	}

	public void setIndicadorModoConsulta(Boolean indicadorModoConsulta) {
		this.indicadorModoConsulta = indicadorModoConsulta;
	}

	public TpCuentBancoDto getCuentaBancariaAdminItem() {
		return cuentaBancariaAdminItem;
	}

	public void setCuentaBancariaAdminItem(TpCuentBancoDto cuentaBancariaAdminItem) {
		this.cuentaBancariaAdminItem = cuentaBancariaAdminItem;
	}

	public List<SelectItem> getListaComboEstadoCuentaBancariaAdmin() {
		return listaComboEstadoCuentaBancariaAdmin;
	}

	public void setListaComboEstadoCuentaBancariaAdmin(List<SelectItem> listaComboEstadoCuentaBancariaAdmin) {
		this.listaComboEstadoCuentaBancariaAdmin = listaComboEstadoCuentaBancariaAdmin;
	}

	public List<SelectItem> getListaComboDivisas() {
		return listaComboDivisas;
	}

	public void setListaComboDivisas(List<SelectItem> listaComboDivisas) {
		this.listaComboDivisas = listaComboDivisas;
	}

	public List<SelectItem> getListaComboBancos() {
		return listaComboBancos;
	}

	public void setListaComboBancos(List<SelectItem> listaComboBancos) {
		this.listaComboBancos = listaComboBancos;
	}

	public List<SelectItem> getListaComboTipoCuentas() {
		return listaComboTipoCuentas;
	}

	public void setListaComboTipoCuentas(List<SelectItem> listaComboTipoCuentas) {
		this.listaComboTipoCuentas = listaComboTipoCuentas;
	}
//
//	public List<SelectItem> getListaComboIndiTransfeBancarias() {
//		return listaComboIndiTransfeBancarias;
//	}
//
//	public void setListaComboIndiTransfeBancarias(List<SelectItem> listaComboIndiTransfeBancarias) {
//		this.listaComboIndiTransfeBancarias = listaComboIndiTransfeBancarias;
//	}
//
//	public List<SelectItem> getListaComboIndiCuentaPropia() {
//		return listaComboIndiCuentaPropia;
//	}
//
//	public void setListaComboIndiCuentaPropia(List<SelectItem> listaComboIndiCuentaPropia) {
//		this.listaComboIndiCuentaPropia = listaComboIndiCuentaPropia;
//	}

	
}
