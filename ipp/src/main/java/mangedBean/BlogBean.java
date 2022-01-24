package mangedBean;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpSession;

import org.primefaces.model.StreamedContent;

import dto.TpEntraDto;
import javax.servlet.http.HttpSession;
import loggerUtil.LoggerUtil;
import service.ServiceEntrada;
import service.impl.ServiceEntradaImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.PaginasPrivadasType;

@ManagedBean(name="blogBean")
@ViewScoped
public class BlogBean {

	private String resultadoProcesoError;
	private List<TpEntraDto> listaEntradas;
	private TpEntraDto entradaSeleccionada;
//	private Integer codigoCliente;
//	private String valorNombre;
	private StreamedContent myImage;
//	private String rutaImagen;
	
	public BlogBean() {
		entradaSeleccionada = new TpEntraDto();
		listaEntradas = new LinkedList<TpEntraDto>();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor blogBean");
		
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase OperacionesControlBean.
     */
    @PostConstruct
    public void init() {

    	try {
			getEntradas();
		} catch (IOException e) {
			LoggerUtil.getInstance().getLogger().error(e.getMessage());
		    LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void getEntradas() throws IOException {
    	ServiceEntrada serviceEntrada = new ServiceEntradaImpl();
    	listaEntradas = serviceEntrada.getEntradas(null);
    	
    	for(TpEntraDto temp: listaEntradas) {
    		
    		
    		String mimeType;
    	    if (temp.getNomImaPrev().endsWith("png")) {
    	        mimeType = "image/png";
    	    } else if (temp.getNomImaPrev().endsWith("jpg") || temp.getNomImaPrev().endsWith("jpeg")) {
    	        mimeType = "image/jpeg";
    	    } else {
    	        mimeType = "application/octet-stream";
    	    }
    	    
    	    temp.setImaEntrPrev("data:"+mimeType+";base64,"+temp.getImaEntrPrev());
    	    
//    		InputStream targetStream = new ByteArrayInputStream(temp.getImaEntr());
//    		 myImage = new DefaultStreamedContent(targetStream, mimeType, temp.getNomImaEntr());
    	}
    }
    
    
    public void seleccionarEntradaEditar(TpEntraDto TpEntraDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("entradaItemSeleccionado", TpEntraDto);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_ENTRADA_GENERAL_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void seleccionarEntradaDetalle(TpEntraDto TpEntraDto){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("entradaItemSeleccionado", TpEntraDto);
		sesion.setAttribute("indicadorModoConsulta", Boolean.TRUE);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_ENTRADA_GENERAL_ITEM.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void procesarRefrescarBlogGeneral() {
    	try {
			getEntradas();
		} catch (IOException e) {
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

	public List<TpEntraDto> getListaEntradas() {
		return listaEntradas;
	}

	public void setListaEntradas(List<TpEntraDto> listaEntradas) {
		this.listaEntradas = listaEntradas;
	}

	public TpEntraDto getEntradaSeleccionada() {
		return entradaSeleccionada;
	}

	public void setEntradaSeleccionada(TpEntraDto entradaSeleccionada) {
		this.entradaSeleccionada = entradaSeleccionada;
	}

	public StreamedContent getMyImage() {
		return myImage;
	}

	public void setMyImage(StreamedContent myImage) {
		this.myImage = myImage;
	}

//	public String getRutaImagen() {
//		return rutaImagen;
//	}
//
//	public void setRutaImagen(String rutaImagen) {
//		this.rutaImagen = rutaImagen;
//	}

	
}
