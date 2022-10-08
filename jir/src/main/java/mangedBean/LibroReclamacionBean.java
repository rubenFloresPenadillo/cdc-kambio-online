package mangedBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.PrimeFaces;

import cadenas.util.ValidacionesString;
import dto.TpReclaQuejaDto;
import dto.TpTipoDocumPersoDto;
import dto.TpTipoPersoDto;
import loggerUtil.LoggerUtil;
import managedThread.CorreoEnvioHilo;
import service.ServiceReclamo;
import service.ServiceUsuario;
import service.impl.ServiceReclamoImpl;
import service.impl.ServiceUsuarioImpl;
import util.types.CadenasType;
import util.types.ElementosTablasType;
import util.types.PlantillasType;
import util.types.RegistroActivoType;

@ManagedBean(name="libroReclamacionBean")
@ViewScoped
public class LibroReclamacionBean {

	private List<SelectItem> listaComboTipoDocumentosPerso;
	private List<SelectItem> listaComboTipoPerso;
	private TpReclaQuejaDto reclamoFormulario;
	private String resultadoProcesoError;
	private String nombreCompleto;
	private String telefonoCelular;
	private String correoElectronico;
	private String asuntoCorreo;
	private String mensaje;
	
	public LibroReclamacionBean() {
		System.out.println("Entro al constructor LibroReclamacionBean");
		resultadoProcesoError = CadenasType.VACIO.getValor();
		reclamoFormulario = new TpReclaQuejaDto();
		getListaTipoDocumentos();
		getListaTipoPersona();
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase ContactoBean.
     */
    @PostConstruct
    public void init() {
//    	HttpSession sesion = ConeccionSesion.getSession();
//    	System.out.println(sesion.getId());

    }
    
    
    public void procesarGuardarDatos() {
    	
        LoggerUtil.getInstance().getLogger().info("Iniciando procesarGuardarDatos");
    	String result = null;
        
    	ServiceReclamo serviceReclamo = new ServiceReclamoImpl();
    	
    	if(ValidacionesString.esNuloOVacio(reclamoFormulario.getUsuApliCrea())) {
    		reclamoFormulario.setUsuApliCrea(CadenasType.CADENA_USUARIO_SISTEMA.getValor());
    		reclamoFormulario.setFecCreaRegi(new Date());
    		reclamoFormulario.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
    	}
    	reclamoFormulario.setUsuApliModi(CadenasType.CADENA_USUARIO_SISTEMA.getValor());
    	reclamoFormulario.setFecModiRegi(new Date());
    	if(ElementosTablasType.TIPO_PERSONERIA_NATURAL.getIdElemento().equals(reclamoFormulario.getTpTipoPerso().getCodTipoPers())) {
    		reclamoFormulario.getTpTipoPerso().setDesTipoPers(ElementosTablasType.TIPO_PERSONERIA_NATURAL.getNombreElemento());
    	}else if(ElementosTablasType.TIPO_PERSONERIA_JURIDICA.getIdElemento().equals(reclamoFormulario.getTpTipoPerso().getCodTipoPers())) {
    		reclamoFormulario.getTpTipoPerso().setDesTipoPers(ElementosTablasType.TIPO_PERSONERIA_JURIDICA.getNombreElemento());
    	}
    	
    	if(ElementosTablasType.TIPO_DOCUMENTO_PERSONA_DNI.getIdElemento().equals(reclamoFormulario.getTpTipoDocumPerso().getCodTipoDocuPers())) {
    		reclamoFormulario.getTpTipoDocumPerso().setNomTipoDocuPerso(ElementosTablasType.TIPO_DOCUMENTO_PERSONA_DNI.getNombreElemento());
    	}else if(ElementosTablasType.TIPO_DOCUMENTO_PERSONA_CE.getIdElemento().equals(reclamoFormulario.getTpTipoDocumPerso().getCodTipoDocuPers())) {
    		reclamoFormulario.getTpTipoDocumPerso().setNomTipoDocuPerso(ElementosTablasType.TIPO_DOCUMENTO_PERSONA_CE.getNombreElemento());
    	}else if(ElementosTablasType.TIPO_DOCUMENTO_PERSONA_PASAPORTE.getIdElemento().equals(reclamoFormulario.getTpTipoDocumPerso().getCodTipoDocuPers())) {
    		reclamoFormulario.getTpTipoDocumPerso().setNomTipoDocuPerso(ElementosTablasType.TIPO_DOCUMENTO_PERSONA_PASAPORTE.getNombreElemento());
    	}else if(ElementosTablasType.TIPO_DOCUMENTO_PERSONA_RUC.getIdElemento().equals(reclamoFormulario.getTpTipoDocumPerso().getCodTipoDocuPers())) {
    		reclamoFormulario.getTpTipoDocumPerso().setNomTipoDocuPerso(ElementosTablasType.TIPO_DOCUMENTO_PERSONA_RUC.getNombreElemento());
    	}
    	
    	if(ElementosTablasType.TIPO_SERVICIO_COMPRA.getIdElemento().equals(Integer.parseInt(reclamoFormulario.getValTipoServCont()))) {
    		reclamoFormulario.setValTipoServContDesc(ElementosTablasType.TIPO_SERVICIO_COMPRA.getNombreElemento());
    	}else if(ElementosTablasType.TIPO_SERVICIO_VENTA.getIdElemento().equals(Integer.parseInt(reclamoFormulario.getValTipoServCont()))) {
    		reclamoFormulario.setValTipoServContDesc(ElementosTablasType.TIPO_SERVICIO_VENTA.getNombreElemento());
    	}
    	
    	if(ElementosTablasType.TIPO_REGISTRO_RECLAMO.getIdElemento().equals(Integer.parseInt(reclamoFormulario.getValTipoReclQuej()))) {
    		reclamoFormulario.setValTipoDescReclQuej(ElementosTablasType.TIPO_REGISTRO_RECLAMO.getNombreElemento());
    	}else if(ElementosTablasType.TIPO_REGISTRO_QUEJA.getIdElemento().equals(Integer.parseInt(reclamoFormulario.getValTipoReclQuej()))) {
    		reclamoFormulario.setValTipoDescReclQuej(ElementosTablasType.TIPO_REGISTRO_QUEJA.getNombreElemento());
    	}
    	
    	result = serviceReclamo.insertUpdate(reclamoFormulario);
    	
        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        	
        	String[] listaRespuesta = result.split("-", 3); 
            reclamoFormulario.setCodReclQuej(Integer.parseInt(listaRespuesta[2]));
        	
        	PrimeFaces.current().executeScript("operacionGuardarReclamoExitoso();");
        	
        	LoggerUtil.getInstance().getLogger().info("Iniciando Hilos Principal Envio Reclamo");
        	
			CorreoEnvioHilo hiloEnvioReclamoBean2 = new CorreoEnvioHilo(PlantillasType.PLANTILLA_ENVIAR_REGISTRO_RECLAMO_ADMIN , "hiloEnvioReclamoBean2", reclamoFormulario, CadenasType.CORREO_DE_ADMIN_LIBRO_RECLAMACIONES.getValor());
			CorreoEnvioHilo hiloEnvioReclamoBean1 = new CorreoEnvioHilo(PlantillasType.PLANTILLA_ENVIAR_REGISTRO_RECLAMO_CLIENTE , "hiloEnvioReclamoBean1", reclamoFormulario, reclamoFormulario.getValEmaiCont());
			
			Thread nuevoHiloEnvioCorreo2 = new Thread(hiloEnvioReclamoBean2);
			Thread nuevoHiloEnvioCorreo1 = new Thread(hiloEnvioReclamoBean1);
			 
			nuevoHiloEnvioCorreo2.start();
			nuevoHiloEnvioCorreo1.start();
			
			LoggerUtil.getInstance().getLogger().info("Finalizando Hilos Principal Envio Reclamo");
			
			reclamoFormulario = new TpReclaQuejaDto();
			
			
        }else {
        	resultadoProcesoError = result;
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().executeScript("procesoConError();");
        }
        
        LoggerUtil.getInstance().getLogger().info("Finalizando procesarGuardarDatos");

    }

	public void getListaTipoDocumentos() {
		listaComboTipoDocumentosPerso = new ArrayList<SelectItem>();
		ServiceUsuario serviceUsuario = new ServiceUsuarioImpl();
		TpTipoDocumPersoDto tpTipoDocumPersoDto = new TpTipoDocumPersoDto();
		tpTipoDocumPersoDto.getTpTipoPerso().setCodTipoPers(ElementosTablasType.TIPO_PERSONERIA_NATURAL.getIdElemento());
		
		
		for(TpTipoDocumPersoDto temp : serviceUsuario.getTipoDocumentoPersona(tpTipoDocumPersoDto)) {
			listaComboTipoDocumentosPerso.add(new SelectItem(temp.getCodTipoDocuPers(), temp.getNomTipoDocuPerso()));
		}
	
	}
	
	public void getListaTipoPersona() {
		listaComboTipoPerso = new ArrayList<SelectItem>();
		ServiceUsuario serviceUsuario = new ServiceUsuarioImpl();
		
		for(TpTipoPersoDto temp : serviceUsuario.getTipoPersona()) {
			listaComboTipoPerso.add(new SelectItem(temp.getCodTipoPers(), temp.getDesTipoPers()));
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

	public List<SelectItem> getListaComboTipoDocumentosPerso() {
		return listaComboTipoDocumentosPerso;
	}

	public void setListaComboTipoDocumentosPerso(List<SelectItem> listaComboTipoDocumentosPerso) {
		this.listaComboTipoDocumentosPerso = listaComboTipoDocumentosPerso;
	}

	public List<SelectItem> getListaComboTipoPerso() {
		return listaComboTipoPerso;
	}

	public void setListaComboTipoPerso(List<SelectItem> listaComboTipoPerso) {
		this.listaComboTipoPerso = listaComboTipoPerso;
	}

	public TpReclaQuejaDto getReclamoFormulario() {
		return reclamoFormulario;
	}

	public void setReclamoFormulario(TpReclaQuejaDto reclamoFormulario) {
		this.reclamoFormulario = reclamoFormulario;
	}
    
    
	
}
