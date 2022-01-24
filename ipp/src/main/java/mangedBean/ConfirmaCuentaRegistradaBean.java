package mangedBean;

import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import cadenas.util.ValidacionesString;
import dto.TpUsuarDto;
import javax.servlet.http.HttpSession;
import loggerUtil.LoggerUtil;
import service.ServiceUsuario;
import service.impl.ServiceUsuarioImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.EstadosCuentaUsuarioType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;

@ManagedBean(name="confirmaCuentaRegistradaBean")
@ViewScoped
public class ConfirmaCuentaRegistradaBean {

	TpUsuarDto usuarioFormulario;
	String resultadoProcesoError;
    private String ideUsuaEmai;
    private String codigoVerificacion1;
    private String codigoVerificacion2;
    private String codigoVerificacion3;
    private String codigoVerificacion4;   
    
	public ConfirmaCuentaRegistradaBean() {
		System.out.println("Entro al constructor confirmaCuentaRegistradaBean");
		usuarioFormulario = new TpUsuarDto();
		resultadoProcesoError = CadenasType.VACIO.getValor();
	}
	
    /**
     * Realiza su ejecuciï¿½n despues del Constructor de la clase AccesoBean.
     */
    @PostConstruct
    public void init() {
    	
    	HttpSession sesion = ConeccionSesion.getSession();
    	ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
    	if(ValidacionesString.esNuloOVacio(ideUsuaEmai)) {
    		ideUsuaEmai = CadenasType.VACIO.getValor();
    	}
    	LoggerUtil.getInstance().getLogger().info("ideUsuaEmai: "+ideUsuaEmai);
    }
    
    private Boolean validarCodigoVerificacion() {
    	
    	Boolean resultado = Boolean.FALSE;
    	
    	if (codigoVerificacion1.length() == NumerosType.INDICADOR_POSITIVO_UNO.getValor() && 
    			codigoVerificacion2.length() == NumerosType.INDICADOR_POSITIVO_UNO.getValor() && 
    			codigoVerificacion3.length() == NumerosType.INDICADOR_POSITIVO_UNO.getValor() && 
    			codigoVerificacion4.length() == NumerosType.INDICADOR_POSITIVO_UNO.getValor() &&
    			!ValidacionesString.esNuloOVacio(codigoVerificacion1) && !ValidacionesString.esNuloOVacio(codigoVerificacion2) &&
    			!ValidacionesString.esNuloOVacio(codigoVerificacion3) && !ValidacionesString.esNuloOVacio(codigoVerificacion4) ) {
    		resultado = Boolean.TRUE;
    	}else {
    		resultado = Boolean.FALSE;
    	}
    		
    	return resultado;
    }
	
	public void enviar() {

        ServiceUsuario serviceUsuario = new ServiceUsuarioImpl();
        ServiceUsuario serviceUsuarioEstadoCuenta = new ServiceUsuarioImpl();
        String result = null;
        
        
        if (ValidacionesString.esNuloOVacio(ideUsuaEmai)) {
        	result = "No se puede verificar el correo, por favor contactar al Soporte TÃ©cnico";
        }else if (!validarCodigoVerificacion()) {
        	result = "El codigo de verificación es incorrecto, por favor valÃ­delo.";
        }else {
        	
        	TpUsuarDto temp = new TpUsuarDto();
        	
    		
    		usuarioFormulario.setIdeUsuaEmai(ideUsuaEmai);
    		temp.setIdeUsuaEmai(usuarioFormulario.getIdeUsuaEmai());
    		temp = serviceUsuarioEstadoCuenta.getUsuario(temp);
    		
    		if (EstadosCuentaUsuarioType.ESTADO_CUENTA_USUARIO_ACTIVADA.getIdElemento().equals(temp.getCodEstaCuenUsua())) {
    			result = "Su cuenta ya esta activada, por favor inicie sesión";
    		}else {
    			StringBuilder sb = new StringBuilder();
            	sb.append(codigoVerificacion1);
            	sb.append(codigoVerificacion2);
            	sb.append(codigoVerificacion3);
            	sb.append(codigoVerificacion4);
            
            	usuarioFormulario.setIdeUsuaEmai(ideUsuaEmai);
                usuarioFormulario.setValTokeCuen(sb.toString());
                usuarioFormulario.setCodEstaCuenUsua(EstadosCuentaUsuarioType.ESTADO_CUENTA_USUARIO_ACTIVADA.getIdElemento());
                usuarioFormulario.setUsuApliModi(ideUsuaEmai);
                usuarioFormulario.setFecModiRegi(new Date());
            	
                result = serviceUsuario.updateCuenta(usuarioFormulario);
    		}
        	
        	
            
            if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {

//            	cuentaBancariaAdminItem = new TpCuentBancoDto();
            	PrimeFaces.current().executeScript("operacionConfirmaCuentaCodigoExitosa();");
            }else {
            	LoggerUtil.getInstance().getLogger().error(result);
            	resultadoProcesoError = "El código ingresado no es valido, por favor verifique o genere uno nuevo.";
            	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
            	PrimeFaces.current().executeScript("procesoConError();");
            }
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

	public String getResultadoProcesoError() {
		return resultadoProcesoError;
	}

	public void setResultadoProcesoError(String resultadoProcesoError) {
		this.resultadoProcesoError = resultadoProcesoError;
	}

	public String getIdeUsuaEmai() {
		return ideUsuaEmai;
	}

	public void setIdeUsuaEmai(String ideUsuaEmai) {
		this.ideUsuaEmai = ideUsuaEmai;
	}

	public String getCodigoVerificacion1() {
		return codigoVerificacion1;
	}

	public void setCodigoVerificacion1(String codigoVerificacion1) {
		this.codigoVerificacion1 = codigoVerificacion1;
	}

	public String getCodigoVerificacion2() {
		return codigoVerificacion2;
	}

	public void setCodigoVerificacion2(String codigoVerificacion2) {
		this.codigoVerificacion2 = codigoVerificacion2;
	}

	public String getCodigoVerificacion3() {
		return codigoVerificacion3;
	}

	public void setCodigoVerificacion3(String codigoVerificacion3) {
		this.codigoVerificacion3 = codigoVerificacion3;
	}

	public String getCodigoVerificacion4() {
		return codigoVerificacion4;
	}

	public void setCodigoVerificacion4(String codigoVerificacion4) {
		this.codigoVerificacion4 = codigoVerificacion4;
	}

	
}
