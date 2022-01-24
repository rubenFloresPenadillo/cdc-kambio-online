package managedThread;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import cadenas.util.ValidacionesString;
import dto.TpReclaQuejaDto;
import loggerUtil.LoggerUtil;
import util.notificaciones.NotificacionUtil;
import util.types.PlantillasType;

public class CorreoEnvioHilo implements Runnable {
	
	private String identificadorHilo;
	private String token;
	private String emailDestino;
	private String nombreCliente;
	private String codigoUnicoOperacion;
	private PlantillasType plantillasType;
	private String datoNombreCompleto;
	private String datoTelefonoCelular;
	private String datoCorreoElectronico;
	private String datoAsuntoCorreo;
	private String datoMensaje;
	private TpReclaQuejaDto tpReclaQuejaDto;

//    public CorreoEnvioHilo (PlantillasType plantillasType, String identificadorHilo, String token, String email, ) {
//    	this.plantillasType = plantillasType;
//    	this.identificadorHilo = identificadorHilo;
//		this.token = token;
//		this.emailDestino = email;
//	}
    
    public CorreoEnvioHilo (PlantillasType plantillasType, String identificadorHilo, String email, String nombreCliente , String codigoUnicoOperacion, String token) {
    	this.plantillasType = plantillasType;
    	this.identificadorHilo = identificadorHilo;
		this.emailDestino = email;
		this.nombreCliente = nombreCliente;
		this.codigoUnicoOperacion = codigoUnicoOperacion;
		this.token = token;
	}
    
    public CorreoEnvioHilo (PlantillasType plantillasType, String identificadorHilo, String datoNombreCompleto, 
    		String datoTelefonoCelular , String datoCorreoElectronico, String datoAsuntoCorreo, String datoMensaje) {
    	this.plantillasType = plantillasType;
    	this.identificadorHilo = identificadorHilo;
		this.datoNombreCompleto = datoNombreCompleto;
		this.datoTelefonoCelular = datoTelefonoCelular;
		this.datoCorreoElectronico = datoCorreoElectronico;
		this.datoAsuntoCorreo = datoAsuntoCorreo;
		this.datoMensaje = datoMensaje;
	}

    public CorreoEnvioHilo (PlantillasType plantillasType, String identificadorHilo, TpReclaQuejaDto tpReclaQuejaDto, String emailDestino) {
    	this.plantillasType = plantillasType;
    	this.identificadorHilo = identificadorHilo;
    	this.emailDestino = emailDestino;
    	
    	this.tpReclaQuejaDto = new TpReclaQuejaDto();
    	
    	this.tpReclaQuejaDto.setCodReclQuej(tpReclaQuejaDto.getCodReclQuej());
    			
    	if (this.tpReclaQuejaDto.getCodReclQuej()  != null) {
    		this.tpReclaQuejaDto.setFecModiRegi(tpReclaQuejaDto.getFecModiRegi());
    		this.tpReclaQuejaDto.setUsuApliModi(tpReclaQuejaDto.getUsuApliModi());
        }
    	this.tpReclaQuejaDto.getTpTipoPerso().setCodTipoPers(tpReclaQuejaDto.getTpTipoPerso().getCodTipoPers());
    	this.tpReclaQuejaDto.getTpTipoPerso().setDesTipoPers(tpReclaQuejaDto.getTpTipoPerso().getDesTipoPers());
    	this.tpReclaQuejaDto.setValNombPers(tpReclaQuejaDto.getValNombPers());
    	this.tpReclaQuejaDto.setValApelPers(tpReclaQuejaDto.getValApelPers());
    	this.tpReclaQuejaDto.setValRazoSociPers(ValidacionesString.esNuloOVacio(tpReclaQuejaDto.getValRazoSociPers()) ? "-": tpReclaQuejaDto.getValRazoSociPers() );
    	this.tpReclaQuejaDto.setValDocuEmpr(ValidacionesString.esNuloOVacio(tpReclaQuejaDto.getValDocuEmpr()) ? "-": tpReclaQuejaDto.getValDocuEmpr() );
    	this.tpReclaQuejaDto.getTpTipoDocumPerso().setCodTipoDocuPers(tpReclaQuejaDto.getTpTipoDocumPerso().getCodTipoDocuPers());
    	this.tpReclaQuejaDto.getTpTipoDocumPerso().setNomTipoDocuPerso(tpReclaQuejaDto.getTpTipoDocumPerso().getNomTipoDocuPerso());
    	this.tpReclaQuejaDto.setValDocuPers(tpReclaQuejaDto.getValDocuPers());
    	this.tpReclaQuejaDto.setValEmaiCont(tpReclaQuejaDto.getValEmaiCont());
    	this.tpReclaQuejaDto.setValCeluCont(tpReclaQuejaDto.getValCeluCont());
    	this.tpReclaQuejaDto.setValTipoServContDesc(tpReclaQuejaDto.getValTipoServContDesc());
    	this.tpReclaQuejaDto.setValMontCamb(tpReclaQuejaDto.getValMontCamb());
    	this.tpReclaQuejaDto.setCodUnicOperClie(tpReclaQuejaDto.getCodUnicOperClie());
    	this.tpReclaQuejaDto.setValTipoReclQuej(tpReclaQuejaDto.getValTipoReclQuej());
    	this.tpReclaQuejaDto.setValTipoDescReclQuej(tpReclaQuejaDto.getValTipoDescReclQuej());
    	this.tpReclaQuejaDto.setValDescReclQuej(tpReclaQuejaDto.getValDescReclQuej());
    	this.tpReclaQuejaDto.setIndEsta(tpReclaQuejaDto.getIndEsta());
    	this.tpReclaQuejaDto.setFecCreaRegi(tpReclaQuejaDto.getFecCreaRegi());
    	this.tpReclaQuejaDto.setUsuApliCrea(tpReclaQuejaDto.getUsuApliCrea());
    	
	}
    
	public void enviarCorreoActivacionCuenta() {
    	Map<String, String> datamodel = new HashMap<String, String>();
    	datamodel.put("token", token );
    	LoggerUtil.getInstance().getLogger().info("token "+token);
    	String asunto = "Activación de tu cuenta - Kambio Online.";
    	NotificacionUtil.enviarCorreo(datamodel, PlantillasType.PLANTILLA_ENVIAR_ENLACE_ACTIVACION_CUENTA.getNombre(), asunto, emailDestino);
    }
	
	public void enviarCorreoActivacionCuentaCodigo() {
    	Map<String, String> datamodel = new HashMap<String, String>();
    	datamodel.put("codigoVerifiacion", token );
    	datamodel.put("nombreCliente", nombreCliente);
    	LoggerUtil.getInstance().getLogger().info("codigoVerifiacion "+token);
    	String asunto = "Activación de tu cuenta - Código de Verificación | Kambio Online.";
    	NotificacionUtil.enviarCorreo(datamodel, PlantillasType.PLANTILLA_ENVIAR_ENLACE_ACTIVACION_CUENTA_CODIGO.getNombre(), asunto, emailDestino);
    }
	
	public void enviarCorreoRestablecerCuenta() {
    	Map<String, String> datamodel = new HashMap<String, String>();
    	datamodel.put("token", token );
    	datamodel.put("usuaEmail", emailDestino );
    	LoggerUtil.getInstance().getLogger().info("token "+token);
    	String asunto = "Solicitud de restablecimiento de contraseña - Kambio Online.";
    	NotificacionUtil.enviarCorreo(datamodel, PlantillasType.PLANTILLA_ENVIAR_ENLACE_RESTABLECER_CUENTA.getNombre(), asunto, emailDestino);
    }
    
	public void enviarCorreoRegistroOperacion() {
		
    	Map<String, String> datamodel = new HashMap<String, String>();
    	datamodel.put("parametroNombre", nombreCliente);
    	datamodel.put("parametroCodigoOperacion", codigoUnicoOperacion);
    	String asunto = "Operación con código "+codigoUnicoOperacion+ " registrada, pendiente de verificación.";
    	NotificacionUtil.enviarCorreo(datamodel, PlantillasType.PLANTILLA_ENVIAR_REGISTRO_OPERACION.getNombre(), asunto, emailDestino);
	}
	
    public void enviarCorreoOperacionFinalizada() {
    	Map<String, String> datamodel = new HashMap<String, String>();
    	datamodel.put("parametroNombre", nombreCliente);
    	datamodel.put("parametroCodigoOperacion", codigoUnicoOperacion);
    	String asunto = "Operación con código "+codigoUnicoOperacion+ " finalizada con éxito.";
    	NotificacionUtil.enviarCorreo(datamodel, PlantillasType.PLANTILLA_ENVIAR_FINALIZO_OPERACION.getNombre(), asunto, emailDestino);
    }
	
    public void enviarCorreoOperacionCancelada() {
    	Map<String, String> datamodel = new HashMap<String, String>();
    	datamodel.put("parametroNombre", nombreCliente);
    	String asunto = null;
    	if(ValidacionesString.esNuloOVacio(codigoUnicoOperacion)) {
        	datamodel.put("parametroCodigoOperacion", "No procesado");
        	asunto = "Operación cancelada.";	
    	}else {
        	datamodel.put("parametroCodigoOperacion", codigoUnicoOperacion);
        	asunto = "Operación con código "+codigoUnicoOperacion+ " ha sido cancelada.";
    	}
    	NotificacionUtil.enviarCorreo(datamodel, PlantillasType.PLANTILLA_ENVIAR_CANCELA_OPERACION.getNombre(), asunto, emailDestino);

    }
    
	public void enviarCorreoAComercioDesdeContacto() {
//    	String asunto = "Activación de tu cuenta - Kambio Online.";
		StringBuilder sb = new StringBuilder();
		
		sb.append("Correo: "+datoCorreoElectronico);
		sb.append("\nTeléfono: "+datoTelefonoCelular);
		sb.append("\nNombre: "+datoNombreCompleto);
		sb.append("\nMensaje: "+datoMensaje);
		
    	NotificacionUtil.enviarCorreoAComercio(PlantillasType.PLANTILLA_CORREO_COMERCIO_DESDE_CONTACTO.getNombre(), datoAsuntoCorreo, sb.toString());
    }
	
	public void enviarCorreoRegistroReclamoCliente() {
		
    	Map<String, String> datamodel = new HashMap<String, String>();
    	datamodel.put("paramNumeroReclamo", String.format("%06d" , tpReclaQuejaDto.getCodReclQuej()));
    	datamodel.put("paramTipoPersona", tpReclaQuejaDto.getTpTipoPerso().getDesTipoPers());
    	datamodel.put("paramNombres", tpReclaQuejaDto.getValNombPers());
    	datamodel.put("paramApellidos", tpReclaQuejaDto.getValApelPers());
    	datamodel.put("paramRazonSocial", tpReclaQuejaDto.getValRazoSociPers());
    	datamodel.put("paramRuc", tpReclaQuejaDto.getValDocuEmpr());
    	datamodel.put("paramTipoDocumento", tpReclaQuejaDto.getTpTipoDocumPerso().getNomTipoDocuPerso());
    	datamodel.put("paramNumeroDocumento", tpReclaQuejaDto.getValDocuPers());
    	datamodel.put("paramEmail", tpReclaQuejaDto.getValEmaiCont());
    	datamodel.put("paramTelefono", tpReclaQuejaDto.getValCeluCont());
    	datamodel.put("paramTipoServicio", tpReclaQuejaDto.getValTipoServContDesc());
    	datamodel.put("paramMontoCambiado", tpReclaQuejaDto.getValMontCamb().toString());
    	datamodel.put("paramCodigoOperacion", tpReclaQuejaDto.getCodUnicOperClie());
    	datamodel.put("paramTipoRegistro", tpReclaQuejaDto.getValTipoDescReclQuej());
    	datamodel.put("paramDescripcion", tpReclaQuejaDto.getValDescReclQuej());
    	
    	Format f = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	String fechaDeRegistro = f.format(tpReclaQuejaDto.getFecCreaRegi());
    	
    	datamodel.put("paramFechaCreacion", fechaDeRegistro);
    	
    	String asunto = "Registro de Queja o Reclamo - Kambio Online.";
    	NotificacionUtil.enviarCorreo(datamodel, PlantillasType.PLANTILLA_ENVIAR_REGISTRO_RECLAMO_CLIENTE.getNombre(), asunto, emailDestino);
    	
	}
	
	public void enviarCorreoRegistroReclamoAdmin() {
		
    	Map<String, String> datamodel = new HashMap<String, String>();
    	datamodel.put("paramNumeroReclamo", String.format("%06d" , tpReclaQuejaDto.getCodReclQuej()));
    	datamodel.put("paramTipoPersona", tpReclaQuejaDto.getTpTipoPerso().getDesTipoPers());
    	datamodel.put("paramNombres", tpReclaQuejaDto.getValNombPers());
    	datamodel.put("paramApellidos", tpReclaQuejaDto.getValApelPers());
    	datamodel.put("paramRazonSocial", tpReclaQuejaDto.getValRazoSociPers());
    	datamodel.put("paramRuc", tpReclaQuejaDto.getValDocuEmpr());
    	datamodel.put("paramTipoDocumento", tpReclaQuejaDto.getTpTipoDocumPerso().getNomTipoDocuPerso());
    	datamodel.put("paramNumeroDocumento", tpReclaQuejaDto.getValDocuPers());
    	datamodel.put("paramEmail", tpReclaQuejaDto.getValEmaiCont());
    	datamodel.put("paramTelefono", tpReclaQuejaDto.getValCeluCont());
    	datamodel.put("paramTipoServicio", tpReclaQuejaDto.getValTipoServContDesc());
    	datamodel.put("paramMontoCambiado", tpReclaQuejaDto.getValMontCamb().toString());
    	datamodel.put("paramCodigoOperacion", tpReclaQuejaDto.getCodUnicOperClie());
    	datamodel.put("paramTipoRegistro", tpReclaQuejaDto.getValTipoDescReclQuej());
    	datamodel.put("paramDescripcion", tpReclaQuejaDto.getValDescReclQuej());
    	
    	Format f = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	String fechaDeRegistro = f.format(tpReclaQuejaDto.getFecCreaRegi());
    	
    	datamodel.put("paramFechaCreacion", fechaDeRegistro);
    	
    	String asunto = "Se registró una Queja o Reclamo- Kambio Online.";
    	NotificacionUtil.enviarCorreo(datamodel, PlantillasType.PLANTILLA_ENVIAR_REGISTRO_RECLAMO_ADMIN.getNombre(), asunto, emailDestino);
    	
	}


	@Override
	public void run() {
		LoggerUtil.getInstance().getLogger().info("Iniciando Run:" +identificadorHilo );
		
		switch (plantillasType) {
//	        case PLANTILLA_ENVIAR_ENLACE_ACTIVACION_CUENTA :  enviarCorreoActivacionCuenta(); break;
	        case PLANTILLA_ENVIAR_ENLACE_ACTIVACION_CUENTA_CODIGO :  enviarCorreoActivacionCuentaCodigo(); break;
	        case PLANTILLA_ENVIAR_ENLACE_RESTABLECER_CUENTA:  enviarCorreoRestablecerCuenta(); break;
	        case PLANTILLA_ENVIAR_REGISTRO_OPERACION:  enviarCorreoRegistroOperacion(); break;
	        case PLANTILLA_ENVIAR_FINALIZO_OPERACION:  enviarCorreoOperacionFinalizada(); break;
	        case PLANTILLA_ENVIAR_CANCELA_OPERACION:  enviarCorreoOperacionCancelada(); break;
	        case PLANTILLA_CORREO_COMERCIO_DESDE_CONTACTO:  enviarCorreoAComercioDesdeContacto(); break;
	        case PLANTILLA_ENVIAR_REGISTRO_RECLAMO_CLIENTE:  enviarCorreoRegistroReclamoCliente(); break;
	        case PLANTILLA_ENVIAR_REGISTRO_RECLAMO_ADMIN:  enviarCorreoRegistroReclamoAdmin(); break;
	        default: LoggerUtil.getInstance().getLogger().info("Opcion no encontrada"); break;
		}
		
		LoggerUtil.getInstance().getLogger().info("Finalizando Run:" +identificadorHilo );
	}

	public String getIdentificadorHilo() {
		return identificadorHilo;
	}

	public void setIdentificadorHilo(String identificadorHilo) {
		this.identificadorHilo = identificadorHilo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmailDestino() {
		return emailDestino;
	}

	public void setEmailDestino(String emailDestino) {
		this.emailDestino = emailDestino;
	}

	public TpReclaQuejaDto getTpReclaQuejaDto() {
		return tpReclaQuejaDto;
	}

	public void setTpReclaQuejaDto(TpReclaQuejaDto tpReclaQuejaDto) {
		this.tpReclaQuejaDto = tpReclaQuejaDto;
	}

	public PlantillasType getPlantillasType() {
		return plantillasType;
	}

	public void setPlantillasType(PlantillasType plantillasType) {
		this.plantillasType = plantillasType;
	}

	
	
}
