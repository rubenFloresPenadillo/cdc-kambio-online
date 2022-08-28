package mangedBean;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import dto.TpUsuarDto;
import loggerUtil.LoggerUtil;
import managedThread.CorreoEnvioHilo;
import seguridad.EnmascaraUtil;
import service.ServiceUsuario;
import service.impl.ServiceUsuarioImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.EstadosCuentaUsuarioType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;
import util.types.PerfilesType;
import util.types.PlantillasType;

@ManagedBean(name="ingresoBean")
@ViewScoped
public class IngresoBean {

	private TpUsuarDto usuarioFormulario;
	private String resultadoProcesoError;
	private static final Integer ACTIVACION_CUENTA = 1;
	private static final Integer MINIMO_VALOR_CODIGO_ACTIVACION = 1000;
	private static final Integer MAXIMO_VALOR_CODIGO_ACTIVACION = 9999;
	
	public IngresoBean() {
		System.out.println("Entro al constructor ingresoBean");
		usuarioFormulario = new TpUsuarDto();
		resultadoProcesoError = CadenasType.VACIO.getValor();
	}
	
    /**
     * Realiza su ejecuciï¿½n despues del Constructor de la clase AccesoBean.
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
//    	String asunto = "Activación de tu cuenta - JirehPlus.";
//    	NotificacionUtil.enviarCorreo(datamodel, PlantillasType.PLANTILLA_ENVIAR_ENLACE_ACTIVACION_CUENTA.getNombre(), asunto, usuarioFormulario.getIdeUsuaEmai());
//    }
    
	public void validar() throws IOException {

		String result = null;

		TpUsuarDto temp = new TpUsuarDto();

		ServiceUsuario serviceUsuario = new ServiceUsuarioImpl();
		usuarioFormulario.setIdeUsuaEmai(usuarioFormulario.getIdeUsuaEmai() != null ? usuarioFormulario.getIdeUsuaEmai().trim() : usuarioFormulario.getIdeUsuaEmai());
		temp.setIdeUsuaEmai(usuarioFormulario.getIdeUsuaEmai());
		temp.setCodClav(EnmascaraUtil.getMD5(usuarioFormulario.getCodClav()));
		temp = serviceUsuario.getUsuario(temp);

		if (temp == null) {
			resultadoProcesoError = "Su correo o contraseña son incorrectos.";
		} else {
			
			HttpSession sesion = ConeccionSesion.getSession();
			sesion.setAttribute("usuario", usuarioFormulario.getIdeUsuaEmai());
			sesion.setAttribute("codigoUsuario", temp.getCodUsua());
			sesion.setAttribute("codigoUsuarioPadre", temp.getCodUsua());
			sesion.setAttribute("codigoCliente", temp.getCodClie());
			sesion.setAttribute("codigoClientePadre", temp.getCodClie());
			sesion.setAttribute("valorNombre", temp.getValNombRegi());
			sesion.setAttribute("valorNombrePadre", temp.getValNombRegi());
			sesion.setAttribute("ideUsuaEmai", temp.getIdeUsuaEmai());
//			sesion.setAttribute("ideUsuaEmaiPadre", temp.getIdeUsuaEmai());
			sesion.setAttribute("indCompleDatos", temp.getIndCompDato());
			sesion.setAttribute("indCompleDatosPadre", temp.getIndCompDato());
			sesion.setAttribute("tipoPersona", temp.getTpTipoPerso().getCodTipoPers());
			sesion.setAttribute("codOperClie", temp.getCodOperClie());
			sesion.setAttribute("codOperCliePadre", temp.getCodOperClie());
			sesion.setAttribute("codEstaOper", temp.getCodEstaOper());
			sesion.setAttribute("codEstaOperPadre", temp.getCodEstaOper());
			sesion.setAttribute("codPerfUsua", temp.getCodPerfUsua());

			if (PerfilesType.CLIENTE.getIdElemento().equals(temp.getCodPerfUsua())) {

				if (EstadosCuentaUsuarioType.ESTADO_CUENTA_USUARIO_REGISTRADA.getIdElemento().equals(temp.getCodEstaCuenUsua())) {
					
					/*
					// Cuenta aun no activada
					UUID uuid = UUID.randomUUID();
					String encriptado = EnmascaraUtil.encriptar(usuarioFormulario.getIdeUsuaEmai());

					StringBuilder enlaceActivaCuenta = new StringBuilder();
					enlaceActivaCuenta.append(encriptado);
					enlaceActivaCuenta.append(CadenasType.GUION_BAJO.getValor());
					enlaceActivaCuenta.append(uuid.toString());

					ServiceUsuario serviceUsuarioActivacion = new ServiceUsuarioImpl();
					
			        usuarioFormulario.setValTokeCuen(uuid.toString());
			        usuarioFormulario.setFecCreaToke(new Date());
			        usuarioFormulario.setUsuApliModi(usuarioFormulario.getIdeUsuaEmai());
			        usuarioFormulario.setFecModiRegi(new Date());
			        
					result = serviceUsuarioActivacion.updateCuentaActivacionPendiente(usuarioFormulario);

					if (result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
						
//						enviarCorreoActivacionCuenta(enlaceActivaCuenta.toString());
						
						LoggerUtil.getInstance().getLogger().info("Iniciando Hilo Principal");
						
						CorreoEnvioHilo hiloEnvioIngresoBean1 = new CorreoEnvioHilo(PlantillasType.PLANTILLA_ENVIAR_ENLACE_ACTIVACION_CUENTA , "hiloEnvioIngresoBean1", usuarioFormulario.getIdeUsuaEmai(), null, null, enlaceActivaCuenta.toString()  );
						 
						Thread nuevoHiloEnvioCorreo = new Thread(hiloEnvioIngresoBean1);
						 
						nuevoHiloEnvioCorreo.start();

						FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_CUENTA_PENDIENTE_ACTIVACION.getValor());
						
						LoggerUtil.getInstance().getLogger().info("Finalizando Hilo Principal");
					} else {
						resultadoProcesoError = result;
						// PrimeFaces.current().executeScript("procesoConError();");
						LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
					}
					*/
					
					Integer codigoVerificacion = (int)Math.floor(Math.random()*(MAXIMO_VALOR_CODIGO_ACTIVACION-MINIMO_VALOR_CODIGO_ACTIVACION+1)+MINIMO_VALOR_CODIGO_ACTIVACION);
					
					ServiceUsuario serviceUsuarioActivacion = new ServiceUsuarioImpl();
					
					usuarioFormulario.setValNombRegi(temp.getValNombRegi());
			        usuarioFormulario.setValTokeCuen(codigoVerificacion.toString());
			        usuarioFormulario.setFecCreaToke(new Date());
			        usuarioFormulario.setUsuApliModi(usuarioFormulario.getIdeUsuaEmai());
			        usuarioFormulario.setFecModiRegi(new Date());
			        
					
			        result = serviceUsuarioActivacion.updateCuentaActivacionPendiente(usuarioFormulario);

                	LoggerUtil.getInstance().getLogger().info("Iniciando Hilo Principal");
                	
    				CorreoEnvioHilo hiloEnvioRegistroBean1 = new CorreoEnvioHilo(PlantillasType.PLANTILLA_ENVIAR_ENLACE_ACTIVACION_CUENTA_CODIGO, "hiloEnvioRegistroBean1", usuarioFormulario.getIdeUsuaEmai(), usuarioFormulario.getValNombRegi(),  null , codigoVerificacion.toString() );
    				 
    				Thread nuevoHiloEnvioCorreo = new Thread(hiloEnvioRegistroBean1);
    				 
    				nuevoHiloEnvioCorreo.start();
                	
    				LoggerUtil.getInstance().getLogger().info("Finalizando Hilo Principal");
			        
    				if (result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
    		        	redireccionarIngresoCodigo();
    				}else {
    		        	resultadoProcesoError = result; 
    		        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
    		        	PrimeFaces.current().ajax().update("idFormIngreso:idMensajeResultadoProceso");
    				}
	        
					
				} else if (EstadosCuentaUsuarioType.ESTADO_CUENTA_USUARIO_BLOQUEADA.getIdElemento().equals(temp.getCodEstaCuenUsua())) {
					resultadoProcesoError = "Su cuenta se encuentra bloqueada, ponganse en contacto con atención al cliente";
				} else if (EstadosCuentaUsuarioType.ESTADO_CUENTA_USUARIO_ELIMINADA.getIdElemento().equals(temp.getCodEstaCuenUsua())) {
					resultadoProcesoError = "Su cuenta se encuentra eliminada, para mayor información ponganse en contacto con atención al cliente";
				} else {
					//Cuenta activa
					if (temp.getIndCompDato().equals(NumerosType.NUMERO_MINIMO_CERO.getValor())) {
						FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_DATOS.getValor());
					} else {
//						FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_INICIO.getValor());
						FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_ELEGIR_PERFIL.getValor());
					}

				}
			} else if (PerfilesType.ADMINISTRADOR.getIdElemento().equals(temp.getCodPerfUsua())) {
				FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_PANEL_PRINCIPAL.getValor());
			}

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
