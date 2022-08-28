package mangedBean;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dto.TpAyudaPreguDto;
import service.ServiceAyuda;
import service.impl.ServiceAyudaImpl;
import util.types.CadenasType;
import util.types.ElementosTablasType;

@ManagedBean(name="ayudaBean")
@ViewScoped
public class AyudaBean {

	private String resultadoProcesoError;
	private List<TpAyudaPreguDto> listaPreguntasAyuda;
	private List<TpAyudaPreguDto> listaPreguntasAyudaNosotros;
	private List<TpAyudaPreguDto> listaPreguntasAyudaCambio;
	private List<TpAyudaPreguDto> listaPreguntasAyudaSeguridad;
	private List<TpAyudaPreguDto> listaPreguntasAyudaOtras;
	
	public AyudaBean() {
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
     * Realiza su ejecuci�n despues del Constructor de la clase ContactoBean.
     */
    @PostConstruct
    public void init() {
//    	HttpSession sesion = ConeccionSesion.getSession();
//    	System.out.println(sesion.getId());
    	getListaAyuda();

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
    

	public String getResultadoProcesoError() {
		return resultadoProcesoError;
	}

	public void setResultadoProcesoError(String resultadoProcesoError) {
		this.resultadoProcesoError = resultadoProcesoError;
	}

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
