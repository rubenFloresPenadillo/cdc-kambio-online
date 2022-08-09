package mangedBean;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.StreamedContent;

import cadenas.util.ValidacionesString;
import dto.TpEntraDto;
import loggerUtil.LoggerUtil;
import service.ServiceEntrada;
import service.impl.ServiceEntradaImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.PaginasPrivadasType;

@ManagedBean(name="blogItemBean")
@ViewScoped
public class BlogItemBean {

	private String resultadoProcesoError;
//	private List<TpEntraDto> listaEntradas;
	private TpEntraDto entradaConsulta;
//	private Integer codigoCliente;
	private String valorNombre;
//	private StreamedContent myImage;
//	private String rutaImagen;
	
	public BlogItemBean() {
		entradaConsulta = new TpEntraDto();
//		listaEntradas = new LinkedList<TpEntraDto>();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor BlogItemBean");
		
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase OperacionesControlBean.
     */
    @PostConstruct
    public void init() {
    	
    	FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String param = paramMap.get("param");
        LoggerUtil.getInstance().getLogger().info("param: "+param);

        HttpSession sesion = ConeccionSesion.getSession();
        
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
    	
    	
    	try {
			getEntrada(param);
		} catch (IOException e) {
			LoggerUtil.getInstance().getLogger().error(e.getMessage());
		    LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void getEntrada(String param) throws IOException {
    	ServiceEntrada serviceEntrada = new ServiceEntradaImpl();
    	TpEntraDto temporal = new TpEntraDto();
    	temporal.setEnlEntr(param);
    	entradaConsulta = serviceEntrada.getEntrada(temporal);
    	
    	if(entradaConsulta != null) {
    		String mimeType;
    	    if (entradaConsulta.getNomImaCont().endsWith("png")) {
    	        mimeType = "image/png";
    	    } else if (entradaConsulta.getNomImaCont().endsWith("jpg") || entradaConsulta.getNomImaCont().endsWith("jpeg")) {
    	        mimeType = "image/jpeg";
    	    } else {
    	        mimeType = "application/octet-stream";
    	    }
    	    
    	    entradaConsulta.setImaEntrCont("data:"+mimeType+";base64,"+entradaConsulta.getImaEntrCont());
    	}
    	
    	System.out.println(entradaConsulta.getNomImaCont());
    }
    
//    public void seleccionarEntradaEditar(TpEntraDto TpEntraDto){
//		HttpSession sesion = ConeccionSesion.getSession();
//		sesion.setAttribute("entradaItemSeleccionado", TpEntraDto);
//		try {
//			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_ENTRADA_GENERAL_ITEM.getValor());
//		} catch (IOException e) {
//	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
//	        LoggerUtil.getInstance().getLogger().error(e);
//		}
//    }
    
//    public void seleccionarEntradaDetalle(TpEntraDto TpEntraDto){
//		HttpSession sesion = ConeccionSesion.getSession();
//		sesion.setAttribute("entradaItemSeleccionado", TpEntraDto);
//		sesion.setAttribute("indicadorModoConsulta", Boolean.TRUE);
//		try {
//			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_ENTRADA_GENERAL_ITEM.getValor());
//		} catch (IOException e) {
//	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
//	        LoggerUtil.getInstance().getLogger().error(e);
//		}
//    }
    
//    public void procesarRefrescarBlogGeneral() {
//    	try {
//			getEntrada();
//		} catch (IOException e) {
//			LoggerUtil.getInstance().getLogger().error(e.getMessage());
//		    LoggerUtil.getInstance().getLogger().error(e);
//		}
//    }
    
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

	public TpEntraDto getEntradaConsulta() {
		return entradaConsulta;
	}

	public void setEntradaConsulta(TpEntraDto entradaConsulta) {
		this.entradaConsulta = entradaConsulta;
	}

	public String getValorNombre() {
		return valorNombre;
	}

	public void setValorNombre(String valorNombre) {
		this.valorNombre = valorNombre;
	}

	

//	public StreamedContent getMyImage() {
//		return myImage;
//	}
//
//	public void setMyImage(StreamedContent myImage) {
//		this.myImage = myImage;
//	}

//	public String getRutaImagen() {
//		return rutaImagen;
//	}
//
//	public void setRutaImagen(String rutaImagen) {
//		this.rutaImagen = rutaImagen;
//	}

	
}
