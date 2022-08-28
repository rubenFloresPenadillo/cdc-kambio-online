package mangedBean.opciones;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.IOUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
//import org.primefaces.model.UploadedFile;
import org.primefaces.model.file.UploadedFile;

import cadenas.util.ValidacionesString;
import dto.TpEntraDto;
import file.util.FileUtil;
import loggerUtil.LoggerUtil;
import service.ServiceEntrada;
import service.impl.ServiceEntradaImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.PaginasPrivadasType;
import util.types.RegistroActivoType;
import util.types.RutasBaseType;

@ManagedBean(name="blogGeneralItemBean")
@ViewScoped
public class BlogGeneralItemBean {

	private String resultadoProcesoExito;
	private String resultadoProcesoError;
	private TpEntraDto entradaItem;
	private String valorNombre;
	private String ideUsuaEmai;
	private Boolean indicadorModoConsulta;

	
	private UploadedFile archivoImagen;
	
	public BlogGeneralItemBean() {
		
		HttpSession sesion = ConeccionSesion.getSession();
		ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
		
		entradaItem = new TpEntraDto();
		indicadorModoConsulta = Boolean.FALSE;
		resultadoProcesoExito = CadenasType.VACIO.getValor();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor BlogGeneralItemBean");
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase OperacionesControlBean.
     */
    @PostConstruct
    public void init() {
    	
    	HttpSession sesion = ConeccionSesion.getSession();
    	TpEntraDto entradaItemSeleccionado = (TpEntraDto) sesion.getAttribute("entradaItemSeleccionado");
		Boolean indicadorModoConsultaTemporal = (Boolean) sesion.getAttribute("indicadorModoConsulta");
		sesion.removeAttribute("entradaItemSeleccionado");
		sesion.removeAttribute("indicadorModoConsulta");
		
		if(entradaItemSeleccionado != null) {
			entradaItem = entradaItemSeleccionado;
		} else {
			entradaItem.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
		}
		
		
		if (indicadorModoConsultaTemporal!=null) {
			indicadorModoConsulta = indicadorModoConsultaTemporal;
		}
		
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
    	
    }

	public void procesarGuardarEntrada() {
		
    	System.out.println("Entro a procesarGuardarEntrada");
    	String result = null;
        
    	ServiceEntrada serviceEntrada = new ServiceEntradaImpl();
    	if(ValidacionesString.esNuloOVacio(entradaItem.getUsuApliCrea())) {
        	entradaItem.setUsuApliCrea(String.valueOf(ideUsuaEmai));
        	entradaItem.setFecCreaRegi(new Date());
        	entradaItem.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
    	}
    	entradaItem.setUsuApliModi(String.valueOf(ideUsuaEmai));
    	entradaItem.setFecModiRegi(new Date());
    	result = serviceEntrada.insertUpdate(entradaItem);
    	
        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        	entradaItem = new TpEntraDto();
        	PrimeFaces.current().executeScript("operacionGuardarEntradaExitosa();");
        }else {
        	resultadoProcesoError = result;
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().executeScript("procesoConError();");
        }

    }

	public void cargaEnlaceEntra() {
		entradaItem.setEnlEntr(ValidacionesString.eliminarAccentos(entradaItem.getTitEntr()));
		entradaItem.setEnlEntr(entradaItem.getEnlEntr().replaceAll(" ", "-"));
		entradaItem.setEnlEntr(entradaItem.getEnlEntr().replaceAll("[^a-zA-Z0-9-]", "").toLowerCase().trim());
	}
	
	public void regresar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_ENTRADA_GENERAL.getValor());
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
    
//    public void upload() {
//        if(archivoImagen != null) {
//            FacesMessage message = new FacesMessage("Succesful AAAAAA", archivoImagen.getFileName() + " is uploaded.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }
//    }
     
    
    public byte[] toByteArray(InputStream in) throws IOException {

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		byte[] buffer = new byte[1024];
		int len;

		// read bytes from the input stream and store them in buffer
		while ((len = in.read(buffer)) != -1) {
			// write bytes from the buffer into output stream
			os.write(buffer, 0, len);
		}

		return os.toByteArray();
	}
    
    public void handleFileUploadPrevia(FileUploadEvent event) {

    	try {
        	
        	String ubicacionImagen = null;
        	InputStream initialStream = event.getFile().getInputStream();
        	

        	
        	ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        	String path = servletContext.getRealPath(CadenasType.VACIO.getValor())+RutasBaseType.RUTA_RECURSO_IMAGENES_TEMPORAL.getValor()+event.getFile().getFileName();
        	ubicacionImagen = CadenasType.SLASH.getValor()+RutasBaseType.RUTA_RECURSO_IMAGENES_TEMPORAL.getValor()+event.getFile().getFileName();
        	entradaItem.setRutaEntrPrev(ubicacionImagen);
        	entradaItem.setNomImaPrev(event.getFile().getFileName());
        	
        	LoggerUtil.getInstance().getLogger().info("Path handleFileUploadPrevia: "+path);
        	LoggerUtil.getInstance().getLogger().info("ubicacionImagen handleFileUploadPrevia: "+ubicacionImagen);
        	
        	File targetFile = new File(path);
        	java.nio.file.Files.copy( initialStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//        	byte[] bytes = toByteArray(initialStream);
//        	entradaItem.setImaEntr(bytes);
        	entradaItem.setImaEntrPrev(FileUtil.fileToBase64(targetFile));
        	IOUtils.closeQuietly(initialStream);

		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
		
    }

    public void handleFileUploadCentral(FileUploadEvent event) {

    	try {
        	
        	String ubicacionImagen = null;
        	InputStream initialStream = event.getFile().getInputStream();
        	

        	
        	ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        	String path = servletContext.getRealPath(CadenasType.VACIO.getValor())+RutasBaseType.RUTA_RECURSO_IMAGENES_TEMPORAL.getValor()+event.getFile().getFileName();
        	ubicacionImagen = CadenasType.SLASH.getValor()+RutasBaseType.RUTA_RECURSO_IMAGENES_TEMPORAL.getValor()+event.getFile().getFileName();
        	entradaItem.setRutaEntr(ubicacionImagen);
        	entradaItem.setNomImaCont(event.getFile().getFileName());
        	
        	LoggerUtil.getInstance().getLogger().info("Path handleFileUploadCentral: "+path);
        	LoggerUtil.getInstance().getLogger().info("ubicacionImagen handleFileUploadCentral: "+ubicacionImagen);
        	
        	File targetFile = new File(path);
        	java.nio.file.Files.copy( initialStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//        	byte[] bytes = toByteArray(initialStream);
//        	entradaItem.setImaEntr(bytes);
        	entradaItem.setImaEntrCont(FileUtil.fileToBase64(targetFile));
        	IOUtils.closeQuietly(initialStream);

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

	public UploadedFile getArchivoImagen() {
		return archivoImagen;
	}

	public void setArchivoImagen(UploadedFile archivoImagen) {
		this.archivoImagen = archivoImagen;
	}

	public TpEntraDto getEntradaItem() {
		return entradaItem;
	}

	public void setEntradaItem(TpEntraDto entradaItem) {
		this.entradaItem = entradaItem;
	}

	
}
