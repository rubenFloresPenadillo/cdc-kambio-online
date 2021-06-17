package mangedBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import cadenas.util.ValidacionesString;
import dto.TpBancoDto;
import dto.TpCuentBancoDto;
import dto.TpDivisDto;
import dto.TpTipoCuentDto;
import loggerUtil.LoggerUtil;
import numeros.util.ValidacionesNumeros;
import service.ServiceBanco;
import service.ServiceCliente;
import service.impl.ServiceBancoImpl;
import service.impl.ServiceClienteImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;
import util.types.RegistroActivoType;

@ManagedBean(name="cuentasBean")
@ViewScoped
public class CuentasBeanBKP {

	private List<TpCuentBancoDto> listaCuentasBancarias;
	private Integer codigoUsuario;
	private Integer codigoCliente;
	private Boolean booleanAgregandoCuenta;
	private String resultadoProcesoExito;
	private String resultadoProcesoError;
	private List<SelectItem> listaComboBancosCuentas;
	private List<SelectItem> listaComboDivisaCuentas;
	private List<SelectItem> listaComboTipoCuentasBancarias;
	private List<SelectItem> listaComboEsCuentaPropia;
	private TpCuentBancoDto cuentaBancoFormulario;
	private String valorNombre;
	private Integer indCompleDatos;
	private String ideUsuaEmai;
	
	public CuentasBeanBKP() {
		System.out.println("Entro al constructor cuentasBean");
		listaCuentasBancarias = new LinkedList<TpCuentBancoDto>();
		booleanAgregandoCuenta = Boolean.FALSE;
		resultadoProcesoExito = CadenasType.VACIO.getValor();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		cuentaBancoFormulario = new TpCuentBancoDto();
	}
	

	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase CuentasBean.
     */
    @PostConstruct
    public void init() {
    	HttpSession sesion = ConeccionSesion.getSession();
    	if(sesion==null) {
    		try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_SESION_TIMEOUT.getValor());
			} catch (IOException e) {
				e.printStackTrace();
		        LoggerUtil.getInstance().getLogger().error(e.getMessage());
		        LoggerUtil.getInstance().getLogger().error(e);
			}
    	}
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	indCompleDatos = (Integer) sesion.getAttribute("indCompleDatos");
    	ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
    	
    	if(indCompleDatos.equals(NumerosType.NUMERO_MINIMO_CERO.getValor())) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_DATOS.getValor());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
	    	codigoUsuario = (Integer) sesion.getAttribute("codigoUsuario");
	    	codigoCliente = (Integer) sesion.getAttribute("codigoCliente");
	    	
	    	setAtributosVista();
		}
    	

    }
    
    public void setAtributosVista()  {
    	
    	cuentaBancoFormulario.getTpClien().setCodClie(codigoCliente);
    	
    	if(!ValidacionesNumeros.esCeroONuloEntero(codigoUsuario)) {
    		getCuentasBancos();
    		getListaBancosCuentas();
    		getListaDivisaCuentas();
    		getListaTipoCuentasBancarias();
    		getListaEsCuentaPropia();
    	}
    	
    }
	
    public void getCuentasBancos() {
    	ServiceCliente serviceCliente = new ServiceClienteImpl();
    	TpCuentBancoDto tpCuentBancoDto = new TpCuentBancoDto();
    	
    	tpCuentBancoDto.getTpClien().setCodClie(codigoCliente);
    	tpCuentBancoDto.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
    	listaCuentasBancarias = serviceCliente.getCuentasBanco(tpCuentBancoDto);
    	
    	serviceCliente.closeSesion();

    }
    
	public void getListaBancosCuentas() {
		listaComboBancosCuentas = new ArrayList<SelectItem>();
		ServiceBanco serviceBanco = new ServiceBancoImpl();
		
		TpBancoDto tpBancoDto = new TpBancoDto();

		tpBancoDto.setIndVistClie( NumerosType.INDICADOR_POSITIVO_UNO.getValor());
		
		for(TpBancoDto temp : serviceBanco.getBancosDisponibles(tpBancoDto)) {
			listaComboBancosCuentas.add(new SelectItem(temp.getCodBanc(), temp.getNomBanc()));
		}

	}
	
	public void getListaDivisaCuentas() {
		listaComboDivisaCuentas = new ArrayList<SelectItem>();
		ServiceBanco serviceBanco = new ServiceBancoImpl();
		
		TpDivisDto tpDivisDto = new TpDivisDto();
		tpDivisDto.setIndApliCuenBanc(RegistroActivoType.ACTIVO.getLlave());
		for(TpDivisDto temp : serviceBanco.getDivisasCuentasDisponibles(tpDivisDto)) {
			listaComboDivisaCuentas.add(new SelectItem(temp.getCodDivi(), temp.getNomDiviPlur()+" ("+temp.getSimDivi()+")"));
		}
		
//		serviceBanco.closeSesion();
		
	}
	
	public void getListaTipoCuentasBancarias() {
		listaComboTipoCuentasBancarias = new ArrayList<SelectItem>();
		ServiceBanco serviceBanco = new ServiceBancoImpl();

		for(TpTipoCuentDto temp : serviceBanco.getTipoCuentasBacarias(null)) { 
			listaComboTipoCuentasBancarias.add(new SelectItem(temp.getCodTipoCuen(), temp.getDesTipoCuen()));
		}
	}
	
	public void getListaEsCuentaPropia() {
		listaComboEsCuentaPropia = new ArrayList<SelectItem>();
		listaComboEsCuentaPropia.add(new SelectItem(NumerosType.INDICADOR_POSITIVO_UNO.getValor(), CadenasType.INDICADOR_SI.getValor()));
		listaComboEsCuentaPropia.add(new SelectItem(NumerosType.INDICADOR_POSITIVO_UNO.getValor(), CadenasType.INDICADOR_NO.getValor()));
	}
	
	public void mostrarDatosCuenta() {
		System.out.println("Ingreso mostrarDatosCuenta");
		booleanAgregandoCuenta = Boolean.TRUE;
	}
	
	public void ocultarDatosCuenta() {
		System.out.println("Ingreso ocultarDatosCuenta");
		booleanAgregandoCuenta = Boolean.FALSE;
	}

    public void validar() {
		
    	System.out.println("Entro a validar");
    	String result = null;
        
    	ServiceCliente serviceCliente = new ServiceClienteImpl();
    	cuentaBancoFormulario.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
    	cuentaBancoFormulario.setUsuApliCrea(String.valueOf(ideUsuaEmai));
    	cuentaBancoFormulario.setFecCreaRegi(new Date());
    	
    	result = serviceCliente.insertUpdateCuentaBanco(cuentaBancoFormulario);
//    	
//        if(result == null) {
//        	getCuentasBancos();
//        	resultadoProcesoExito = "Datos guardados con éxito.";
//        }else {
//        	resultadoProcesoError = result;
//        }
        
        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {

        	PrimeFaces.current().executeScript("operacionGuardarCuentaBancariaExitosa();");
        }else {
        	resultadoProcesoError = result;
        	PrimeFaces.current().executeScript("procesoConError();");
        }

    }
    
	public void irAiniciarOperacion() {
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
    
	public List<TpCuentBancoDto> getListaCuentasBancarias() {
		return listaCuentasBancarias;
	}

	public void setListaCuentasBancarias(List<TpCuentBancoDto> listaCuentasBancarias) {
		this.listaCuentasBancarias = listaCuentasBancarias;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Boolean getBooleanAgregandoCuenta() {
		return booleanAgregandoCuenta;
	}

	public void setBooleanAgregandoCuenta(Boolean booleanAgregandoCuenta) {
		this.booleanAgregandoCuenta = booleanAgregandoCuenta;
	}

	public String getResultadoProcesoExito() {
		return resultadoProcesoExito;
	}

	public void setResultadoProcesoExito(String resultadoProcesoExito) {
		this.resultadoProcesoExito = resultadoProcesoExito;
	}

	public String getResultadoProcesoError() {
		return resultadoProcesoError;
	}

	public void setResultadoProcesoError(String resultadoProcesoError) {
		this.resultadoProcesoError = resultadoProcesoError;
	}

	public List<SelectItem> getListaComboBancosCuentas() {
		return listaComboBancosCuentas;
	}

	public void setListaComboBancosCuentas(List<SelectItem> listaComboBancosCuentas) {
		this.listaComboBancosCuentas = listaComboBancosCuentas;
	}

	public List<SelectItem> getListaComboDivisaCuentas() {
		return listaComboDivisaCuentas;
	}

	public void setListaComboDivisaCuentas(List<SelectItem> listaComboDivisaCuentas) {
		this.listaComboDivisaCuentas = listaComboDivisaCuentas;
	}

	public List<SelectItem> getListaComboTipoCuentasBancarias() {
		return listaComboTipoCuentasBancarias;
	}

	public void setListaComboTipoCuentasBancarias(List<SelectItem> listaComboTipoCuentasBancarias) {
		this.listaComboTipoCuentasBancarias = listaComboTipoCuentasBancarias;
	}

	public List<SelectItem> getListaComboEsCuentaPropia() {
		return listaComboEsCuentaPropia;
	}

	public void setListaComboEsCuentaPropia(List<SelectItem> listaComboEsCuentaPropia) {
		this.listaComboEsCuentaPropia = listaComboEsCuentaPropia;
	}

	public TpCuentBancoDto getCuentaBancoFormulario() {
		return cuentaBancoFormulario;
	}

	public void setCuentaBancoFormulario(TpCuentBancoDto cuentaBancoFormulario) {
		this.cuentaBancoFormulario = cuentaBancoFormulario;
	}

	public String getValorNombre() {
		return valorNombre;
	}
	
	public void setValorNombre(String valorNombre) {
		this.valorNombre = valorNombre;
	}

	public Integer getIndCompleDatos() {
		return indCompleDatos;
	}

	public void setIndCompleDatos(Integer indCompleDatos) {
		this.indCompleDatos = indCompleDatos;
	}

}
