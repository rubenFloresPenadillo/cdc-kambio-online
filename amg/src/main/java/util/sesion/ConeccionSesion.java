package util.sesion;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ConeccionSesion implements Serializable{

	private static final long serialVersionUID = -7757060756397766647L;

	/*
	 * request.getSession(false): Este método verificará si la sesión ya existió para la solicitud o no. 
	 * Si existió, devolverá la sesión ya existente. Si Session aún no existe para esta solicitud, 
	 * este método devolverá NULL, lo que significa que este método indica que la solicitud no tiene una sesión previamente.
	 * */
	public static HttpSession getSession(){
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	public static HttpServletRequest getRequest(){
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
}
