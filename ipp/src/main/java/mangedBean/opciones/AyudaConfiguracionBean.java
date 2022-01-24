package mangedBean.opciones;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import cadenas.util.ValidacionesString;
import dto.TpAyudaPreguDto;
import javax.servlet.http.HttpSession;
import loggerUtil.LoggerUtil;
import service.ServiceAyuda;
import service.impl.ServiceAyudaImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.ElementosTablasType;
import util.types.PaginasPrivadasType;

@ManagedBean(name="ayudaConfiguracionBean")
@ViewScoped
public class AyudaConfiguracionBean {

	private String resultadoProcesoError;
//	private List<TpDivisDto> listaDivisas;
//	private TpDivisDto divisaSeleccionada;
	private Integer codigoCliente;
	private String valorNombre;
	private List<TpAyudaPreguDto> listaPreguntasAyuda;
	private List<TpAyudaPreguDto> listaPreguntasAyudaNosotros;
	private List<TpAyudaPreguDto> listaPreguntasAyudaCambio;
	private List<TpAyudaPreguDto> listaPreguntasAyudaSeguridad;
	private List<TpAyudaPreguDto> listaPreguntasAyudaOtras;
	
	public AyudaConfiguracionBean() {
//		divisaSeleccionada = new TpDivisDto();
		listaPreguntasAyuda = new LinkedList<TpAyudaPreguDto>();
		listaPreguntasAyudaNosotros = new LinkedList<TpAyudaPreguDto>();
		listaPreguntasAyudaCambio = new LinkedList<TpAyudaPreguDto>();
		listaPreguntasAyudaSeguridad = new LinkedList<TpAyudaPreguDto>();
		listaPreguntasAyudaOtras = new LinkedList<TpAyudaPreguDto>();
//		listaDivisas = new LinkedList<TpDivisDto>();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor DivisasBean");
		
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
    	
//    	getDivisas();
    	getListaAyuda();
    }
    
//    public void getDivisas() {
//    	ServiceDivisa serviceDivisa = new ServiceDivisaImpl();
//    	listaDivisas = serviceDivisa.getDivisas(null);
//   	
//    }

//    public void seleccionarDivisaEditar(TpDivisDto tpDivisDto){
//		HttpSession sesion = ConeccionSesion.getSession();
//		sesion.setAttribute("preguntaItemSeleccionada", tpDivisDto);
//		try {
//			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_DIVISA_ITEM.getValor());
//		} catch (IOException e) {
//	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
//	        LoggerUtil.getInstance().getLogger().error(e);
//		}
//    }
    
    
    public void seleccionarPreguntaEditar(TpAyudaPreguDto tpAyudaPreguDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("preguntaItemSeleccionada", tpAyudaPreguDto);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_AYUDA_CONNFIGURACION_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void seleccionarPreguntaDetalle(TpAyudaPreguDto tpAyudaPreguDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("preguntaItemSeleccionada", tpAyudaPreguDto);
		sesion.setAttribute("indicadorModoConsulta", Boolean.TRUE);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_AYUDA_CONNFIGURACION_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void procesarRefrescarAyuda() {
    	getListaAyuda();
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
    
    
    public void getListaAyuda() {
    	
    	ServiceAyuda serviceAyuda =  new ServiceAyudaImpl();
    	listaPreguntasAyuda = serviceAyuda.getPreguntasDisponibles(null);
    	
		
    	listaPreguntasAyudaNosotros = listaPreguntasAyuda.stream()          
                .filter(x -> x.getTpTipoPregu().getCodTipoPreg().equals(ElementosTablasType.TIPO_PREGUNTA_NOSOTROS.getIdElemento()) )  
                .collect(Collectors.toList());
    	
    	listaPreguntasAyudaCambio = listaPreguntasAyuda.stream()          
                .filter(x -> x.getTpTipoPregu().getCodTipoPreg().equals(ElementosTablasType.TIPO_PREGUNTA_CAMBIO.getIdElemento()) )  
                .collect(Collectors.toList());
    	
    	listaPreguntasAyudaSeguridad = listaPreguntasAyuda.stream()          
                .filter(x -> x.getTpTipoPregu().getCodTipoPreg().equals(ElementosTablasType.TIPO_PREGUNTA_SEGURIDAD.getIdElemento()) )  
                .collect(Collectors.toList());
    	
    	listaPreguntasAyudaOtras = listaPreguntasAyuda.stream()          
                .filter(x -> x.getTpTipoPregu().getCodTipoPreg().equals(ElementosTablasType.TIPO_PREGUNTA_OTRAS.getIdElemento()) )  
                .collect(Collectors.toList());
    	
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

//	public List<TpDivisDto> getListaDivisas() {
//		return listaDivisas;
//	}
//
//	public void setListaDivisas(List<TpDivisDto> listaDivisas) {
//		this.listaDivisas = listaDivisas;
//	}
//
//	public TpDivisDto getDivisaSeleccionada() {
//		return divisaSeleccionada;
//	}
//
//	public void setDivisaSeleccionada(TpDivisDto divisaSeleccionada) {
//		this.divisaSeleccionada = divisaSeleccionada;
//	}

	public List<TpAyudaPreguDto> getListaPreguntasAyuda() {
		return listaPreguntasAyuda;
	}

	public void setListaPreguntasAyuda(List<TpAyudaPreguDto> listaPreguntasAyuda) {
		this.listaPreguntasAyuda = listaPreguntasAyuda;
	}

	public List<TpAyudaPreguDto> getListaPreguntasAyudaNosotros() {
		return listaPreguntasAyudaNosotros;
	}

	public void setListaPreguntasAyudaNosotros(List<TpAyudaPreguDto> listaPreguntasAyudaNosotros) {
		this.listaPreguntasAyudaNosotros = listaPreguntasAyudaNosotros;
	}

	public List<TpAyudaPreguDto> getListaPreguntasAyudaCambio() {
		return listaPreguntasAyudaCambio;
	}

	public void setListaPreguntasAyudaCambio(List<TpAyudaPreguDto> listaPreguntasAyudaCambio) {
		this.listaPreguntasAyudaCambio = listaPreguntasAyudaCambio;
	}

	public List<TpAyudaPreguDto> getListaPreguntasAyudaSeguridad() {
		return listaPreguntasAyudaSeguridad;
	}

	public void setListaPreguntasAyudaSeguridad(List<TpAyudaPreguDto> listaPreguntasAyudaSeguridad) {
		this.listaPreguntasAyudaSeguridad = listaPreguntasAyudaSeguridad;
	}

	public List<TpAyudaPreguDto> getListaPreguntasAyudaOtras() {
		return listaPreguntasAyudaOtras;
	}

	public void setListaPreguntasAyudaOtras(List<TpAyudaPreguDto> listaPreguntasAyudaOtras) {
		this.listaPreguntasAyudaOtras = listaPreguntasAyudaOtras;
	}

	
    
}
