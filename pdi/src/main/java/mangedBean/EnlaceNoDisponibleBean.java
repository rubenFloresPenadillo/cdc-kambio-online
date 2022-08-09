package mangedBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="enlaceNoDisponibleBean")
@ViewScoped
public class EnlaceNoDisponibleBean {

	public EnlaceNoDisponibleBean() {
		System.out.println("Entro al constructor EnlaceNoDisponibleBean");
	}
	
    /**
     * Realiza su ejecuci�n despues del Constructor de la clase AccesoBean.
     */
    @PostConstruct
    public void init() {
        
    }

    
}
