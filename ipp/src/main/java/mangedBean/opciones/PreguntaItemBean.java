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
import dto.TpAyudaPreguDto;
import dto.TpTipoPreguDto;
import javax.servlet.http.HttpSession;
import loggerUtil.LoggerUtil;
import service.ServiceAyuda;
import service.ServiceTipoPregunta;
import service.impl.ServiceAyudaImpl;
import service.impl.ServiceTipoPreguntaImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;
import util.types.RegistroActivoType;

@ManagedBean(name="preguntaItemBean")
@ViewScoped
public class PreguntaItemBean {

	private String resultadoProcesoExito;
	private String resultadoProcesoError;
//	private TpDivisDto divisaItem;
	private TpAyudaPreguDto preguntaItem;
	private String valorNombre;
	private String ideUsuaEmai;
//	private List<SelectItem> listaComboIndAplicaCuentasBancarias;
//	private List<SelectItem> listaComboEstadoDivisa;
	private List<SelectItem> listaComboTipoPregunta;	
	private List<TpTipoPreguDto> listaComboTipoPreguntaTemporal;
	private Boolean indicadorModoConsulta;
	
	public PreguntaItemBean() {
		
		HttpSession sesion = ConeccionSesion.getSession();
		ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
		
//		divisaItem = new TpDivisDto();
		preguntaItem = new TpAyudaPreguDto();
		indicadorModoConsulta = Boolean.FALSE;
		resultadoProcesoExito = CadenasType.VACIO.getValor();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor PreguntaItemBean");
	}
	
    /**
     * Realiza su ejecuciï¿½n despues del Constructor de la clase OperacionesControlBean.
     */
    @PostConstruct
    public void init() {
    	
    	HttpSession sesion = ConeccionSesion.getSession();
    	TpAyudaPreguDto cabeceraOrigen = (TpAyudaPreguDto) sesion.getAttribute("preguntaItemSeleccionada");
		Boolean indicadorModoConsultaTemporal = (Boolean) sesion.getAttribute("indicadorModoConsulta");
		sesion.removeAttribute("preguntaItemSeleccionada");
		sesion.removeAttribute("indicadorModoConsulta");
		
		if(cabeceraOrigen!=null) {
			preguntaItem = cabeceraOrigen;
		}
		
		if (indicadorModoConsultaTemporal!=null) {
			indicadorModoConsulta = indicadorModoConsultaTemporal;
		} else {
			preguntaItem.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
		}
		
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
    	
    	getListaTipoPregunta();
//    	getListaIndAplicaCuentasBancarias() ;
//    	getListaEstadosDivisa();
    }

//	public void getListaIndAplicaCuentasBancarias() {
//		listaComboIndAplicaCuentasBancarias = new ArrayList<SelectItem>();
//		listaComboIndAplicaCuentasBancarias.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
//		listaComboIndAplicaCuentasBancarias.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
//	}
//	
//	public void getListaEstadosDivisa() {
//		listaComboEstadoDivisa = new ArrayList<SelectItem>();
//		listaComboEstadoDivisa.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
//		listaComboEstadoDivisa.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
//	}
    
	
	public void procesarGuardarPregunta() {
		
    	System.out.println("Entro a validar");
    	String result = null;
        
    	ServiceAyuda serviceAyuda = new ServiceAyudaImpl();
    	if(ValidacionesString.esNuloOVacio(preguntaItem.getUsuApliCrea())) {
        	preguntaItem.setUsuApliCrea(String.valueOf(ideUsuaEmai));
        	preguntaItem.setFecCreaRegi(new Date());
        	preguntaItem.setIndEsta(NumerosType.INDICADOR_POSITIVO_UNO.getValor());
    	}
    	preguntaItem.setUsuApliModi(String.valueOf(ideUsuaEmai));
    	preguntaItem.setFecModiRegi(new Date());
    	result = serviceAyuda.insertUpdate(preguntaItem);
    	
        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {

//        	resultadoProcesoExito = "Datos guardados con éxito.";
        	preguntaItem = new TpAyudaPreguDto();
        	PrimeFaces.current().executeScript("operacionGuardarPreguntaExitosa();");
        }else {
        	resultadoProcesoError = result;
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().executeScript("procesoConError();");
        }

    }
	
	
	public void getListaTipoPregunta() {
		listaComboTipoPregunta = new ArrayList<SelectItem>();
		ServiceTipoPregunta serviceTipoPregunta = new ServiceTipoPreguntaImpl();

		List<TpTipoPreguDto> listaComboTipoPreguntaTemporal = serviceTipoPregunta.getTipoPreguntas(null);
		
		for(TpTipoPreguDto temp : listaComboTipoPreguntaTemporal) {
			listaComboTipoPregunta.add(new SelectItem(temp.getCodTipoPreg(), temp.getDesTipoPreg()));
		}
		
	}
	
	public void regresar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_AYUDA_CONNFIGURACION.getValor());
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

	public TpAyudaPreguDto getPreguntaItem() {
		return preguntaItem;
	}

	public void setPreguntaItem(TpAyudaPreguDto preguntaItem) {
		this.preguntaItem = preguntaItem;
	}

	public List<SelectItem> getListaComboTipoPregunta() {
		return listaComboTipoPregunta;
	}

	public void setListaComboTipoPregunta(List<SelectItem> listaComboTipoPregunta) {
		this.listaComboTipoPregunta = listaComboTipoPregunta;
	}
   
	
}
