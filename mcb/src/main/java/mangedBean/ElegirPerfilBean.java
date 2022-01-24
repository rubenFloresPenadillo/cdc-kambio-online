package mangedBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import cadenas.util.ValidacionesString;
import dto.TpClienDto;
import dto.TpCuentBancoDto;
import loggerUtil.LoggerUtil;
import service.ServiceCliente;
import service.impl.ServiceClienteImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;
import util.types.RegistroActivoType;

@ManagedBean(name="elegirPerfilBean")
@ViewScoped
public class ElegirPerfilBean {



	private Integer codigoCliente;
	private Integer codigoClientePadre;
	private Integer indCompleDatosPadre;
	private String valorNombrePadre;
	private String resultadoProcesoExito;
	private String resultadoProcesoError;
	private String valorNombre;
	private Integer codigoUsuario;
	private Integer codigoUsuarioPadre;
	private Integer codOperCliePadre;
	private Integer codEstaOperPadre;
	private Integer indCompleDatos;
	private String ideUsuaEmai;
	private Integer codPerfUsua;
	private String usuario;
	private Integer codOperClie;
	private Integer codEstaOper;
	private List<TpClienDto> listaPerfilesEmpresas;


	
	public ElegirPerfilBean() {
		System.out.println("Entro al constructor datosBean");
		resultadoProcesoExito = CadenasType.VACIO.getValor();
		resultadoProcesoError = CadenasType.VACIO.getValor();
	}
	
    /**
     * Realiza su ejecución despues del Constructor de la clase DatosBean.
     */
    @PostConstruct
    public void init() {
    	HttpSession sesion = ConeccionSesion.getSession();
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	valorNombrePadre = (String) sesion.getAttribute("valorNombrePadre");
    	indCompleDatos = (Integer) sesion.getAttribute("indCompleDatos");
    	ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}

    	codigoUsuario = (Integer) sesion.getAttribute("codigoUsuario");
    	codigoUsuarioPadre = (Integer) sesion.getAttribute("codigoUsuarioPadre");
    	
    	getListaPerfilesEmpresa();
    	
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
    

	public void redireccionarAlInicioOperaciones() {
		
		HttpSession sesion = ConeccionSesion.getSession();
		
		codigoUsuarioPadre = (Integer) sesion.getAttribute("codigoUsuarioPadre");
		codigoClientePadre = (Integer) sesion.getAttribute("codigoClientePadre");
		indCompleDatosPadre = (Integer) sesion.getAttribute("indCompleDatosPadre");
		valorNombrePadre = (String) sesion.getAttribute("valorNombrePadre");
		codOperCliePadre = (Integer) sesion.getAttribute("codOperCliePadre");
		codEstaOperPadre = (Integer) sesion.getAttribute("codEstaOperPadre");
    	
    	sesion.setAttribute("codigoUsuario", codigoUsuarioPadre);
    	sesion.setAttribute("codigoCliente", codigoClientePadre);
    	sesion.setAttribute("indCompleDatos", indCompleDatosPadre);
    	sesion.setAttribute("valorNombre", valorNombrePadre);
    	sesion.setAttribute("codOperClie", codOperCliePadre);
    	sesion.setAttribute("codEstaOper", codEstaOperPadre);
    	
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_INICIO.getValor());
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
	}
	
	public void redireccionarAlOperacionEmpresa(TpClienDto tpClienDto) {
		
    	HttpSession sesion = ConeccionSesion.getSession();
//    	valorNombre = (String) sesion.getAttribute("valorNombre");
//    	indCompleDatos = (Integer) sesion.getAttribute("indCompleDatos");
//    	codigoUsuario = (Integer) sesion.getAttribute("codigoUsuario");
//    	codigoCliente = (Integer) sesion.getAttribute("codigoCliente");
//    	codOperClie = (Integer) sesion.getAttribute("codOperClie");
//    	codPerfUsua = (Integer) sesion.getAttribute("codPerfUsua");
//    	usuario = (String) sesion.getAttribute("usuario");
//    	ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
    	codigoUsuario = tpClienDto.getTpUsuar().getCodUsua();
    	codigoCliente = tpClienDto.getCodClie();
    	indCompleDatos = NumerosType.INDICADOR_POSITIVO_UNO.getValor();
    	valorNombre = tpClienDto.getValNombPerf();
    	codOperClie = tpClienDto.getTpUsuar().getCodOperClie();
    	codEstaOper = tpClienDto.getTpUsuar().getCodEstaOper(); 
    	
    	sesion.setAttribute("codigoUsuario", codigoUsuario);
//    	sesion.setAttribute("usuario", usuario);
    	sesion.setAttribute("codigoCliente", codigoCliente);
    	sesion.setAttribute("indCompleDatos", indCompleDatos);
    	sesion.setAttribute("valorNombre", valorNombre);
    	sesion.setAttribute("codOperClie", codOperClie);
    	sesion.setAttribute("codEstaOper", codEstaOper);
    	
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_INICIO.getValor());
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
	}
	
	
	public void getListaPerfilesEmpresa() {

		ServiceCliente serviceCliente = new ServiceClienteImpl();
		TpClienDto tpClienDto = new TpClienDto();
		tpClienDto.getTpUsuar().setCodUsuaPadr(codigoUsuarioPadre);
    	
    	listaPerfilesEmpresas = serviceCliente.getListaPerfilesEmpresa(tpClienDto);
		
//		for(TpCuentBancoDto temp : listaCuentasBancariasTemporal) {
//			listaComboCuentasBancarias.add(new SelectItem(temp.getCodCuenBanc(), temp.getAliCuen() + CadenasType.GUION_ESPACIOS_LATERALES.getValor()+ CadenasType.PARENTESIS_INI.getValor() + temp.getTpDivis().getCodIsoDivi()  + CadenasType.PARENTESIS_FIN.getValor()  + CadenasType.GUION_ESPACIOS_LATERALES.getValor()+ temp.getValCuenBanc()));
//		}
		
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

	

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getIdeUsuaEmai() {
		return ideUsuaEmai;
	}

	public void setIdeUsuaEmai(String ideUsuaEmai) {
		this.ideUsuaEmai = ideUsuaEmai;
	}

	public List<TpClienDto> getListaPerfilesEmpresas() {
		return listaPerfilesEmpresas;
	}

	public void setListaPerfilesEmpresas(List<TpClienDto> listaPerfilesEmpresas) {
		this.listaPerfilesEmpresas = listaPerfilesEmpresas;
	}

	public String getValorNombrePadre() {
		return valorNombrePadre;
	}

	public void setValorNombrePadre(String valorNombrePadre) {
		this.valorNombrePadre = valorNombrePadre;
	}
	
	
}
