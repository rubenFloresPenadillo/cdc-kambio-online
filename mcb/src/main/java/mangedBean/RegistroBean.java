package mangedBean;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import cadenas.util.ValidacionesString;
import dto.TpEntraDto;
import dto.TpUsuarDto;
import loggerUtil.LoggerUtil;
import managedThread.CorreoEnvioHilo;
import seguridad.EnmascaraUtil;
import service.ServiceUsuario;
import service.impl.ServiceUsuarioImpl;
import util.notificaciones.NotificacionUtil;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.EstadosCuentaUsuarioType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;
import util.types.PerfilesType;
import util.types.PlantillasType;
import util.types.RegistroActivoType;

@ManagedBean(name="registroBean")
@ViewScoped
public class RegistroBean {

	private TpUsuarDto usuarioFormulario;
	private String tipoPersonaSeleccion;
//	private String tipoPersoneriaN;
	private String tipoPersoneria;
	private String resultadoProcesoExito;
	private String resultadoProcesoError;
//	private static final Integer ACTIVACION_CUENTA = 1;
	private static final Integer MINIMO_VALOR_CODIGO_ACTIVACION = 1000;
	private static final Integer MAXIMO_VALOR_CODIGO_ACTIVACION = 9999;
	
	public RegistroBean() {
		System.out.println("Entro al constructor registroBean");
		usuarioFormulario = new TpUsuarDto();
		tipoPersoneria = NumerosType.INDICADOR_POSITIVO_UNO.getValor().toString();
		resultadoProcesoExito = CadenasType.VACIO.getValor();
		resultadoProcesoError = CadenasType.VACIO.getValor();
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase AccesoBean.
     */
    @PostConstruct
    public void init() {
    	

    }
    
//    public void enviarCorreoActivacionCuenta(String token) {
//    	Map<String, String> datamodel = new HashMap<String, String>();
//    	
//    	datamodel.put("token", token );
//    	LoggerUtil.getInstance().getLogger().info("token "+token);
//    	String asunto = "Activación de tu cuenta - Casa de Cambio Multicambio.";
//    	NotificacionUtil.enviarCorreo(datamodel, PlantillasType.PLANTILLA_ENVIAR_ENLACE_ACTIVACION_CUENTA.getNombre(), asunto, usuarioFormulario.getIdeUsuaEmai());
//    }
    
    public void validar() {


    	
    	LoggerUtil.getInstance().getLogger().info("Entro a validar");
    	String result = null;
        

        
        if (result == null) {
        	
        	if(!ValidacionesString.validarEmail(usuarioFormulario.getIdeUsuaEmai().trim())) {
        		result = "El correo ingresado es incorrecto, valide por favor.";
        	}else if(ValidacionesString.contains(usuarioFormulario.getIdeUsuaEmai().trim(), CadenasType.ESPACIO.getValor())) {
        		result = "El correo ingresado es incorrecto, valide que no tenga espacios en blanco.";
        	}else {
        		
        		TpUsuarDto temp = new TpUsuarDto();
            	
            	ServiceUsuario serviceUsuario = new ServiceUsuarioImpl();
            	usuarioFormulario.setIdeUsuaEmai(usuarioFormulario.getIdeUsuaEmai().trim());
            	temp.setIdeUsuaEmai(usuarioFormulario.getIdeUsuaEmai());
            	temp = serviceUsuario.getUsuario(temp);
            	if(temp !=null) {
            		result = "El correo ingresado ya existe.";
            	}else {
            		
//            		UUID uuid = UUID.randomUUID();
//            		String encriptado = EnmascaraUtil.encriptar(usuarioFormulario.getIdeUsuaEmai());
            		
            		Integer codigoVerificacion = (int)Math.floor(Math.random()*(MAXIMO_VALOR_CODIGO_ACTIVACION-MINIMO_VALOR_CODIGO_ACTIVACION+1)+MINIMO_VALOR_CODIGO_ACTIVACION);
            		
            		System.out.println("tipoPersoneria "+tipoPersoneria);
            		usuarioFormulario.setCodClav(EnmascaraUtil.getMD5(usuarioFormulario.getCodClav()));
            		usuarioFormulario.setCodEstaCuenUsua(EstadosCuentaUsuarioType.ESTADO_CUENTA_USUARIO_REGISTRADA.getIdElemento());
            		usuarioFormulario.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
            		usuarioFormulario.setValTokeCuen(codigoVerificacion.toString());
            		//usuarioFormulario.setValTokeCuen(uuid.toString());
            		usuarioFormulario.setFecCreaToke(new Date());
            		usuarioFormulario.setCodPerfUsua(PerfilesType.CLIENTE.getIdElemento());
            		usuarioFormulario.setUsuApliCrea(usuarioFormulario.getIdeUsuaEmai());
            		usuarioFormulario.setFecCreaRegi(new Date());
            		usuarioFormulario.getTpTipoPerso().setCodTipoPers(Integer.parseInt(tipoPersoneria));
            		usuarioFormulario.setIndCompDato(RegistroActivoType.INACTIVO.getLlave());
            		
            		ServiceUsuario serviceUsuarioRegistro = new ServiceUsuarioImpl();
            		LoggerUtil.getInstance().getLogger().info("despues del ServiceUsuarioImpl B");
                    result = serviceUsuarioRegistro.insertUpdate(usuarioFormulario);
                    
//                	StringBuilder enlaceActivaCuenta = new StringBuilder();
//                	enlaceActivaCuenta.append(encriptado);
//                	enlaceActivaCuenta.append(CadenasType.GUION_BAJO.getValor());
//                	enlaceActivaCuenta.append(uuid.toString());
                	
                	LoggerUtil.getInstance().getLogger().info("Iniciando Hilo Principal");
                	
    				CorreoEnvioHilo hiloEnvioRegistroBean1 = new CorreoEnvioHilo(PlantillasType.PLANTILLA_ENVIAR_ENLACE_ACTIVACION_CUENTA_CODIGO, "hiloEnvioRegistroBean1", usuarioFormulario.getIdeUsuaEmai(), usuarioFormulario.getValNombRegi(),  null , codigoVerificacion.toString() );
    				 
    				Thread nuevoHiloEnvioCorreo = new Thread(hiloEnvioRegistroBean1);
    				 
    				nuevoHiloEnvioCorreo.start();
                	
    				LoggerUtil.getInstance().getLogger().info("Finalizando Hilo Principal");

            		
            	}
        	}
        	
        	
	
        }

        
        if(result == null) {
//        	PrimeFaces.current().executeScript("operacionGuardarRegistroUsuario();");
        	redireccionarIngresoCodigo();
        }else {
        	resultadoProcesoError = result; 
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().ajax().update("idFormRegistro:idMensajeResultadoProceso");
//        	PrimeFaces.current().executeScript("procesoConError();");
        }

    }

    
    public void redireccionarIngresoCodigo(){
		HttpSession sesion = ConeccionSesion.getSession();
		sesion.setAttribute("ideUsuaEmai", usuarioFormulario.getIdeUsuaEmai());
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_CUENTA_CONFIRMA_CUENTA_CODIGO.getValor());
		} catch (IOException e) {
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
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

	public String getTipoPersonaSeleccion() {
		return tipoPersonaSeleccion;
	}

	public void setTipoPersonaSeleccion(String tipoPersonaSeleccion) {
		this.tipoPersonaSeleccion = tipoPersonaSeleccion;
	}

//	public String getTipoPersoneriaN() {
//		return tipoPersoneriaN;
//	}
//
//	public void setTipoPersoneriaN(String tipoPersoneriaN) {
//		this.tipoPersoneriaN = tipoPersoneriaN;
//	}

	public String getTipoPersoneria() {
		return tipoPersoneria;
	}

	public void setTipoPersoneria(String tipoPersoneria) {
		this.tipoPersoneria = tipoPersoneria;
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
