package mangedBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;

import dto.TpUsuarDto;
import loggerUtil.LoggerUtil;
import managedThread.CorreoEnvioHilo;
import util.types.CadenasType;
import util.types.PlantillasType;

@ManagedBean(name="contactoBean")
@ViewScoped
public class ContactoBean {

	private String resultadoProcesoError;
	private String nombreCompleto;
	private String telefonoCelular;
	private String correoElectronico;
	private String asuntoCorreo;
	private String mensaje;
	
	public ContactoBean() {
		System.out.println("Entro al constructor contactoBean");
		resultadoProcesoError = CadenasType.VACIO.getValor();
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase ContactoBean.
     */
    @PostConstruct
    public void init() {
//    	HttpSession sesion = ConeccionSesion.getSession();
//    	System.out.println(sesion.getId());

    }
    
    
    public void procesarEnviarMensaje() {
		
    	System.out.println("Entro a procesarEnviarMensaje");
    	String result = null;
    	
    	
    	LoggerUtil.getInstance().getLogger().info("Iniciando Hilo Principal");


		CorreoEnvioHilo hiloEnvioContactoBean1 = new CorreoEnvioHilo(PlantillasType.PLANTILLA_CORREO_COMERCIO_DESDE_CONTACTO, "hiloEnvioContactoBean1", nombreCompleto
				, telefonoCelular, correoElectronico, asuntoCorreo, mensaje);
		 
		Thread nuevoHiloEnvioCorreo = new Thread(hiloEnvioContactoBean1);
		 
		nuevoHiloEnvioCorreo.start();
    	
		LoggerUtil.getInstance().getLogger().info("Finalizando Hilo Principal");
    	
    	result = "OK";
    	
        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        	nombreCompleto = null;
        	telefonoCelular = null;
        	correoElectronico = null;
        	asuntoCorreo = null;
        	mensaje = null;
        	PrimeFaces.current().executeScript("operacionEnviarMensajeExitoso();");
        	
        }else {
        	resultadoProcesoError = result;
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().executeScript("procesoConError();");
        }

    }

	public String getResultadoProcesoError() {
		return resultadoProcesoError;
	}

	public void setResultadoProcesoError(String resultadoProcesoError) {
		this.resultadoProcesoError = resultadoProcesoError;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getAsuntoCorreo() {
		return asuntoCorreo;
	}

	public void setAsuntoCorreo(String asuntoCorreo) {
		this.asuntoCorreo = asuntoCorreo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
    
    
	
}
