package util.types;

import java.util.HashMap;
import java.util.Map;

public enum EstadosCuentaUsuarioType {

	ESTADO_CUENTA_USUARIO_REGISTRADA(1, "Registrada"),
	ESTADO_CUENTA_USUARIO_ACTIVADA(2, "Activada"),
	ESTADO_CUENTA_USUARIO_BLOQUEADA(3, "Bloqueada"),
	ESTADO_CUENTA_USUARIO_ELIMINADA(4, "Eliminada");
	
	private final Integer idElemento;
	private final String nombreElemento;

	private EstadosCuentaUsuarioType(Integer idElemento, String nombreElemento) {
		this.idElemento = idElemento;
		this.nombreElemento = nombreElemento;
	}
	
	//Lookup table
	private static final Map<Integer, EstadosCuentaUsuarioType> lookup = new HashMap<>();
	
	//Populate the lookup table on loading time
    static
    {
        for(EstadosCuentaUsuarioType elemento : EstadosCuentaUsuarioType.values())
        {
            lookup.put(elemento.getIdElemento(), elemento);
        }
    }
	
    
    //This method can be used for reverse lookup purpose
    public static String get(Integer idElementoParametro){
		return lookup.get(idElementoParametro).getNombreElemento();
    }
    
    
	public Integer getIdElemento() {
		return idElemento;
	}
	public String getNombreElemento() {
		return nombreElemento;
	}
	
	
	
}
