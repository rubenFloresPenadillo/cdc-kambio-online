package mangedBean;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dto.TpUsuarDto;
import loggerUtil.LoggerUtil;
import managedThread.CorreoEnvioHilo;
import seguridad.EnmascaraUtil;
import service.ServiceUsuario;
import service.impl.ServiceUsuarioImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.EstadosCuentaUsuarioType;
import util.types.PaginasPrivadasType;
import util.types.PerfilesType;
import util.types.PlantillasType;

@ManagedBean(name="correoRestablecerCuentaBean")
@ViewScoped
public class CorreoRestablecerCuentaBean {

	TpUsuarDto usuarioFormulario;
	String resultadoProcesoError;
	private static final Integer RESTABLECER_CUENTA = 2;
	
	public CorreoRestablecerCuentaBean() {
		System.out.println("Entro al constructor correoRestablecerCuentaBean");
		usuarioFormulario = new TpUsuarDto();
		resultadoProcesoError = CadenasType.VACIO.getValor();
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase AccesoBean.
     */
    @PostConstruct
    public void init() {
//    	HttpSession sesion = ConeccionSesion.getSession();
//    	System.out.println(sesion.getId());

    }
    
//    public void enviarCorreoActivacionCuenta(String token) {
//    	Map<String, String> datamodel = new HashMap<String, String>();
//    	datamodel.put("token", token );
//    	LoggerUtil.getInstance().getLogger().info("token "+token);
//    	String asunto = "Activación de tu cuenta - Casa de Cambio Hmafg.";
//    	NotificacionUtil.enviarCorreo(datamodel, PlantillasType.PLANTILLA_ENVIAR_ENLACE_ACTIVACION_CUENTA.getNombre(), asunto, usuarioFormulario.getIdeUsuaEmai());
//    }
    
	public void validar() throws IOException {

		String result = null;

		TpUsuarDto temp = new TpUsuarDto();

		ServiceUsuario serviceUsuario = new ServiceUsuarioImpl();
		temp.setIdeUsuaEmai(usuarioFormulario.getIdeUsuaEmai());
		temp = serviceUsuario.getUsuario(temp);

		if (temp == null) {
			resultadoProcesoError = "El correo no esta registrado.";
		} else {

			if (PerfilesType.CLIENTE.getIdElemento().equals(temp.getCodPerfUsua())) {

				if (EstadosCuentaUsuarioType.ESTADO_CUENTA_USUARIO_REGISTRADA.getIdElemento().equals(temp.getCodEstaCuenUsua()) || 
						EstadosCuentaUsuarioType.ESTADO_CUENTA_USUARIO_ACTIVADA.getIdElemento().equals(temp.getCodEstaCuenUsua())) {
					
					//Solo si la cuenta esta activa o registrada se procede al enlace para crear una nueva clave
					
					HttpSession sesion = ConeccionSesion.getSession();
					sesion.setAttribute("ideUsuaEmai", temp.getIdeUsuaEmai());
					
					UUID uuid = UUID.randomUUID();
					String encriptado = EnmascaraUtil.encriptar(usuarioFormulario.getIdeUsuaEmai());

					StringBuilder enlaceRestableceCuenta = new StringBuilder();
					enlaceRestableceCuenta.append(encriptado);
					enlaceRestableceCuenta.append(CadenasType.GUION_BAJO.getValor());
					enlaceRestableceCuenta.append(uuid.toString());

					ServiceUsuario serviceUsuarioRestableceCuenta = new ServiceUsuarioImpl();
					
			        usuarioFormulario.setValTokeRestCuen(uuid.toString());
			        usuarioFormulario.setFecCreaTokeRestCuen(new Date());
			        usuarioFormulario.setUsuApliModi(usuarioFormulario.getIdeUsuaEmai());
			        usuarioFormulario.setFecModiRegi(new Date());
			        
					result = serviceUsuarioRestableceCuenta.updateCuentaRestablece(usuarioFormulario);

					if (result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
						
//						enviarCorreoActivacionCuenta(enlaceActivaCuenta.toString());
						
						LoggerUtil.getInstance().getLogger().info("Iniciando Hilo Principal");
						
						CorreoEnvioHilo hiloCorreoRestablecerCuentaBean1 = new CorreoEnvioHilo(PlantillasType.PLANTILLA_ENVIAR_ENLACE_RESTABLECER_CUENTA, "hiloCorreoRestablecerCuentaBean1",  usuarioFormulario.getIdeUsuaEmai(), null, null, enlaceRestableceCuenta.toString());
						
						Thread nuevoHiloEnvioCorreo = new Thread(hiloCorreoRestablecerCuentaBean1);
						 
						nuevoHiloEnvioCorreo.start();

						FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_CUENTA_RESTABLECER_CONTRASENIA.getValor());
						
						LoggerUtil.getInstance().getLogger().info("Finalizando Hilo Principal");
					} else {
						resultadoProcesoError = result;
						// PrimeFaces.current().executeScript("procesoConError();");
						LoggerUtil.getInstance().getLogger().error(result);
					}

				} else if (EstadosCuentaUsuarioType.ESTADO_CUENTA_USUARIO_BLOQUEADA.getIdElemento().equals(temp.getCodEstaCuenUsua())) {
					resultadoProcesoError = "Su cuenta se encuentra bloqueada, ponganse en contacto con atención al cliente";
				} else if (EstadosCuentaUsuarioType.ESTADO_CUENTA_USUARIO_ELIMINADA.getIdElemento().equals(temp.getCodEstaCuenUsua())) {
					resultadoProcesoError = "Su cuenta se encuentra eliminada, para mayor información ponganse en contacto con atención al cliente";
				} 
			} else if (PerfilesType.ADMINISTRADOR.getIdElemento().equals(temp.getCodPerfUsua())) {
//				FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_PANEL_PRINCIPAL.getValor());
			}

		}

	}

	public TpUsuarDto getUsuarioFormulario() {
		return usuarioFormulario;
	}

	public void setUsuarioFormulario(TpUsuarDto usuarioFormulario) {
		this.usuarioFormulario = usuarioFormulario;
	}

	public String getResultadoProcesoError() {
		return resultadoProcesoError;
	}

	public void setResultadoProcesoError(String resultadoProcesoError) {
		this.resultadoProcesoError = resultadoProcesoError;
	}

    
	
	
}
