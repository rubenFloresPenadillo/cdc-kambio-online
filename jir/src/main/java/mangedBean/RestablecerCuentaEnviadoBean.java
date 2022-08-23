package mangedBean;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import dto.TpBancoDto;
import dto.TpUsuarDto;
import loggerUtil.LoggerUtil;
import seguridad.EnmascaraUtil;
import service.ServiceUsuario;
import service.impl.ServiceUsuarioImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.ElementosTablasType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;

@ManagedBean(name="restablecerCuentaEnviadoBean")
@ViewScoped
public class RestablecerCuentaEnviadoBean {

	private String ideUsuaEmai;
	
	
	public RestablecerCuentaEnviadoBean() {
		System.out.println("Entro al constructor restablecerCuentaEnviadoBean");
		HttpSession sesion = ConeccionSesion.getSession();
		ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
		sesion.removeAttribute("ideUsuaEmai");
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase AccesoBean.
     */
    @PostConstruct
    public void init() {
    	
        
    }

	public String getIdeUsuaEmai() {
		return ideUsuaEmai;
	}

	public void setIdeUsuaEmai(String ideUsuaEmai) {
		this.ideUsuaEmai = ideUsuaEmai;
	}
}
