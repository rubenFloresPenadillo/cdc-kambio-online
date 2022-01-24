package mangedBean;

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

@ManagedBean(name="cuentaItemBean")
@ViewScoped
public class CuentaItemBean {

	private String resultadoProcesoExito;
	private String resultadoProcesoError;
	private TpCuentBancoDto cuentaItem;
	private String valorNombre;
	private Integer codigoCliente;
	private String ideUsuaEmai;
//	private List<SelectItem> listaComboEstadoCuenta;
	private List<SelectItem> listaComboDivisas;
	private List<SelectItem> listaComboBancos;
	private List<SelectItem> listaComboTipoCuentas;
//	private List<SelectItem> listaComboIndiTransfeBancarias;
//	private List<SelectItem> listaComboIndiCuentaPropia;
	private Boolean indDatosEmpresa;
	private Integer codigoUsuario;
	private Integer codigoUsuarioPadre;	
	
	private Boolean indicadorModoConsulta;
	public CuentaItemBean() {
		cuentaItem = new TpCuentBancoDto();
		indicadorModoConsulta = Boolean.FALSE;
		resultadoProcesoExito = CadenasType.VACIO.getValor();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		indDatosEmpresa = Boolean.FALSE;
		System.out.println("Entro al constructor CuentaItemBean");
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase OperacionesControlBean.
     */
    @PostConstruct
    public void init() {
    	
    	HttpSession sesion = ConeccionSesion.getSession();
    	codigoCliente = (Integer) sesion.getAttribute("codigoCliente");
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
    	
    	codigoUsuario = (Integer) sesion.getAttribute("codigoUsuario");
    	codigoUsuarioPadre = (Integer) sesion.getAttribute("codigoUsuarioPadre");
    	
    	if(codigoUsuario.intValue() != codigoUsuarioPadre.intValue()) {
    		indDatosEmpresa = Boolean.TRUE;
    	}
    	
    	TpCuentBancoDto cuentaBancariaItemSeleccionado = (TpCuentBancoDto) sesion.getAttribute("cuentaBancariaItemSeleccionado");
		Boolean indicadorModoConsultaTemporal = (Boolean) sesion.getAttribute("indicadorModoConsulta");
		sesion.removeAttribute("cuentaBancariaItemSeleccionado");
		sesion.removeAttribute("indicadorModoConsulta");
		
		if(cuentaBancariaItemSeleccionado != null) {
			cuentaItem = cuentaBancariaItemSeleccionado;
		} else {
			cuentaItem.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
		}
		
		
		if (indicadorModoConsultaTemporal!=null) {
			indicadorModoConsulta = indicadorModoConsultaTemporal;
		}
		

    	
//    	getListaEstadosCuentasBancariasAdmin();
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

		tpBancoDto.setIndVistClie( NumerosType.INDICADOR_POSITIVO_UNO.getValor());
		
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
	
//	public void getListaEstadosCuentasBancariasAdmin() {
//		listaComboEstadoCuenta = new ArrayList<SelectItem>();
//		listaComboEstadoCuenta.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
//		listaComboEstadoCuenta.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
//	}
	
	public void procesarGuardarCuenta() {
		
    	System.out.println("Entro a validar");
    	String result = null;
        
    	ServiceCliente serviceCliente = new ServiceClienteImpl();
    	if(ValidacionesString.esNuloOVacio(cuentaItem.getUsuApliCrea())) {
        	cuentaItem.setUsuApliCrea(String.valueOf(ideUsuaEmai));
        	cuentaItem.setFecCreaRegi(new Date());
        	cuentaItem.setIndCuenProp(RegistroActivoType.ACTIVO.getLlave());
        	cuentaItem.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
    	}
    	cuentaItem.getTpClien().setCodClie(codigoCliente);
    	cuentaItem.setUsuApliModi(String.valueOf(ideUsuaEmai));
    	cuentaItem.setFecModiRegi(new Date());
    	result = serviceCliente.insertUpdateCuentaBanco(cuentaItem);
    	
        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {

        	cuentaItem = new TpCuentBancoDto();
        	PrimeFaces.current().executeScript("operacionGuardarCuentaExitosa();");
        }else {
        	resultadoProcesoError = result;
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().executeScript("procesoConError();");
        }

    }

	public void regresar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_INICIO.getValor());
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
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

//	public List<SelectItem> getListaComboEstadoCuenta() {
//		return listaComboEstadoCuenta;
//	}
//
//	public void setListaComboEstadoCuenta(List<SelectItem> listaComboEstadoCuenta) {
//		this.listaComboEstadoCuenta = listaComboEstadoCuenta;
//	}

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

	public TpCuentBancoDto getCuentaItem() {
		return cuentaItem;
	}

	public void setCuentaItem(TpCuentBancoDto cuentaItem) {
		this.cuentaItem = cuentaItem;
	}

	public Boolean getIndDatosEmpresa() {
		return indDatosEmpresa;
	}

	public void setIndDatosEmpresa(Boolean indDatosEmpresa) {
		this.indDatosEmpresa = indDatosEmpresa;
	}

	
	
}
