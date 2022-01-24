package mangedBean;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import dto.TpBancoDto;
import dto.TpUsuarDto;
import loggerUtil.LoggerUtil;
import managedThread.CorreoEnvioHilo;
import seguridad.EnmascaraUtil;
import service.ServiceUsuario;
import service.impl.ServiceUsuarioImpl;
import util.types.CadenasType;
import util.types.ElementosTablasType;
import util.types.PaginasPrivadasType;
import util.types.PerfilesType;
import util.types.PlantillasType;
import util.types.RegistroActivoType;

@ManagedBean(name="generaNuevaClaveBean")
@ViewScoped
public class GeneraNuevaClaveBean {

	private TpUsuarDto usuarioFormulario;

	private String resultadoProcesoExito;
	private String resultadoProcesoError;
	
	
	public GeneraNuevaClaveBean() {
		System.out.println("Entro al constructor GeneraNuevaClaveBean");
		usuarioFormulario = new TpUsuarDto();
		resultadoProcesoExito = CadenasType.VACIO.getValor();
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
		
        usuarioFormulario.setIdeUsuaEmai(valorUsuarioEmail);
        usuarioFormulario.setValTokeRestCuen(uuid);
		
    }
    
    public void validar() {


    	
		LoggerUtil.getInstance().getLogger().info("Entro a validar");
		String result = null;

		TpUsuarDto temp = new TpUsuarDto();

		ServiceUsuario serviceUsuario = new ServiceUsuarioImpl();
		temp.setIdeUsuaEmai(usuarioFormulario.getIdeUsuaEmai());
		temp = serviceUsuario.getUsuario(temp);
		if (temp == null) {
			result = "El correo no esta registrado, verifique por favor.";
		} else {

			usuarioFormulario.setCodClav(EnmascaraUtil.getMD5(usuarioFormulario.getCodClav()));

			usuarioFormulario.setUsuApliModi(usuarioFormulario.getIdeUsuaEmai());
			usuarioFormulario.setFecModiRegi(new Date());
			usuarioFormulario.setFecUltiModiClav(new Date());

			ServiceUsuario serviceUsuarioGeneraClave = new ServiceUsuarioImpl();

	        
			result = serviceUsuarioGeneraClave.updateGeneraClave(usuarioFormulario);


		}
	
        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        	PrimeFaces.current().executeScript("operacionGenerarNuevaClave();");
        }else {
        	resultadoProcesoError = result; 
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().ajax().update("idFormRegistro:idMensajeResultadoProceso");
        	PrimeFaces.current().executeScript("procesoConError();");
        }


    }

	public void redireccionarLogin() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_INGRESO.getValor());
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
	}
	
	public TpUsuarDto getUsuarioFormulario() {
		return usuarioFormulario;
	}

	public void setUsuarioFormulario(TpUsuarDto usuarioFormulario) {
		this.usuarioFormulario = usuarioFormulario;
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

	
    
}
