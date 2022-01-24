package mangedBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.servlet.http.HttpSession;
import util.sesion.ConeccionSesion;

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
