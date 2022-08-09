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
import dto.TpParamGenerDto;
import loggerUtil.LoggerUtil;
import service.ServiceParametroGeneral;
import service.impl.ServiceParametroGeneralImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.PaginasPrivadasType;
import util.types.RegistroActivoType;

@ManagedBean(name="parametroGeneralItemBean")
@ViewScoped
public class ParametroGeneralItemBean {

	private String resultadoProcesoExito;
	private String resultadoProcesoError;
	private TpParamGenerDto parametroGeneralItem;
	private String valorNombre;
	private String ideUsuaEmai;
	private Boolean indicadorModoConsulta;
	private List<SelectItem> listaComboEstadoParametroGeneral;
	
	public ParametroGeneralItemBean() {
		
		HttpSession sesion = ConeccionSesion.getSession();
		ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
		
		parametroGeneralItem = new TpParamGenerDto();
		indicadorModoConsulta = Boolean.FALSE;
		resultadoProcesoExito = CadenasType.VACIO.getValor();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor ParametroGeneralItemBean");
	}
	
    /**
     * Realiza su ejecuciï¿½n despues del Constructor de la clase OperacionesControlBean.
     */
    @PostConstruct
    public void init() {
    	
    	HttpSession sesion = ConeccionSesion.getSession();
		TpParamGenerDto cabeceraOrigen = (TpParamGenerDto) sesion.getAttribute("parametroGeneralItemSeleccionado");
		Boolean indicadorModoConsultaTemporal = (Boolean) sesion.getAttribute("indicadorModoConsulta");
		sesion.removeAttribute("parametroGeneralItemSeleccionado");
		sesion.removeAttribute("indicadorModoConsulta");
		
		if(cabeceraOrigen!=null) {
			parametroGeneralItem = cabeceraOrigen;
		}
		
		if (indicadorModoConsultaTemporal!=null) {
			indicadorModoConsulta = indicadorModoConsultaTemporal;
		} else {
			parametroGeneralItem.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
		}
		
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}

    	getListaEstadoParametroGeneral();
    }

	public void getListaEstadoParametroGeneral() {
		listaComboEstadoParametroGeneral = new ArrayList<SelectItem>();
		listaComboEstadoParametroGeneral.add(new SelectItem(RegistroActivoType.ACTIVO.getLlave(), RegistroActivoType.ACTIVO.getDescripcion()));
		listaComboEstadoParametroGeneral.add(new SelectItem(RegistroActivoType.INACTIVO.getLlave(), RegistroActivoType.INACTIVO.getDescripcion()));
	}
	
	public void procesarGuardarParametroGeneral() {
		
    	System.out.println("Entro a validar");
    	String result = null;
        
    	ServiceParametroGeneral serviceParametroGeneral = new ServiceParametroGeneralImpl();
    	if(ValidacionesString.esNuloOVacio(parametroGeneralItem.getUsuApliCrea())) {
        	parametroGeneralItem.setUsuApliCrea(String.valueOf(ideUsuaEmai));
        	parametroGeneralItem.setFecCreaRegi(new Date());
    	}
    	parametroGeneralItem.setUsuApliModi(String.valueOf(ideUsuaEmai));
    	parametroGeneralItem.setFecModiRegi(new Date());
    	result = serviceParametroGeneral.insertUpdate(parametroGeneralItem);
    	
        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {

//        	resultadoProcesoExito = "Datos guardados con éxito.";
        	parametroGeneralItem = new TpParamGenerDto();
        	PrimeFaces.current().executeScript("operacionGuardarParametroGeneralExitosa();");
        }else {
        	resultadoProcesoError = result;
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().executeScript("procesoConError();");
        }

    }
	
	public void regresar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_PARAMETRO_GENERAL.getValor());
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

	public TpParamGenerDto getparametroGeneralItem() {
		return parametroGeneralItem;
	}

	public void setparametroGeneralItem(TpParamGenerDto parametroGeneralItem) {
		this.parametroGeneralItem = parametroGeneralItem;
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

	public TpParamGenerDto getParametroGeneralItem() {
		return parametroGeneralItem;
	}

	public void setParametroGeneralItem(TpParamGenerDto parametroGeneralItem) {
		this.parametroGeneralItem = parametroGeneralItem;
	}

	public List<SelectItem> getListaComboEstadoParametroGeneral() {
		return listaComboEstadoParametroGeneral;
	}

	public void setListaComboEstadoParametroGeneral(List<SelectItem> listaComboEstadoParametroGeneral) {
		this.listaComboEstadoParametroGeneral = listaComboEstadoParametroGeneral;
	}
   
	
}
