package mangedBean;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dto.TpUsuarDto;
import loggerUtil.LoggerUtil;
import seguridad.EnmascaraUtil;
import service.ServiceUsuario;
import service.impl.ServiceUsuarioImpl;
import util.types.CadenasType;
import util.types.EstadosCuentaUsuarioType;
import util.types.PaginasPrivadasType;

@ManagedBean(name="activaCuentaBean")
@ViewScoped
public class ActivaCuentaBean {

	private TpUsuarDto usuarioFormulario;
//	private String tipoPersonaSeleccion;
//	private String tipoPersoneriaN;
//	private String tipoPersoneria;
//	private String resultadoProcesoExito;
	private String resultadoProcesoError;
	
	private final static String TOKEN_ENLACE = "token";
	
	
	public ActivaCuentaBean() {
		System.out.println("Entro al constructor activaCuentaBean");
		usuarioFormulario = new TpUsuarDto();
		resultadoProcesoError = CadenasType.VACIO.getValor();
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase AccesoBean.
     */
    @PostConstruct
    public void init() {
    	
    	FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String token = paramMap.get("token");
        LoggerUtil.getInstance().getLogger().info("token: "+token);
        
        
        
        String[] respuesta = token.split(CadenasType.GUION_BAJO.getValor());
		String valorUsuarioEmailEncriptado = String.valueOf(respuesta[0]);
		String uuid = String.valueOf(respuesta[1]);
        
		String valorUsuarioEmail = EnmascaraUtil.desencriptar(valorUsuarioEmailEncriptado);
		
        ServiceUsuario serviceUsuario = new ServiceUsuarioImpl();
        String result = null;
        
        usuarioFormulario.setIdeUsuaEmai(valorUsuarioEmail);
        usuarioFormulario.setValTokeCuen(uuid);
        usuarioFormulario.setCodEstaCuenUsua(EstadosCuentaUsuarioType.ESTADO_CUENTA_USUARIO_ACTIVADA.getIdElemento());
        usuarioFormulario.setUsuApliModi(valorUsuarioEmail);
        usuarioFormulario.setFecModiRegi(new Date());
    	
        result = serviceUsuario.updateCuenta(usuarioFormulario);
        
        if(!result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
//        	resultadoProcesoError = result;
//        	PrimeFaces.current().executeScript("procesoConError();");
        	try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_ENLACE_NO_DISPONIBLE.getValor());
			} catch (IOException e) {
				e.printStackTrace();
				LoggerUtil.getInstance().getLogger().error(e.getMessage());
		        LoggerUtil.getInstance().getLogger().error(e);
			}
        }
        
    }

	public String getResultadoProcesoError() {
		return resultadoProcesoError;
	}

	public void setResultadoProcesoError(String resultadoProcesoError) {
		this.resultadoProcesoError = resultadoProcesoError;
	}

	
    
}
