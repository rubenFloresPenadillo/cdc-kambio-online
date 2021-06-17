package util.types;

import java.util.HashMap;
import java.util.Map;

public enum PerfilesType {

	CLIENTE(1, "Cliente"),
	ADMINISTRADOR(2, "Administrador");

	private final Integer idElemento;
	private final String nombreElemento;

	private PerfilesType(Integer idElemento, String nombreElemento) {
		this.idElemento = idElemento;
		this.nombreElemento = nombreElemento;
	}
	
	//Lookup table
	private static final Map<Integer, PerfilesType> lookup = new HashMap<>();
	
	//Populate the lookup table on loading time
    static
    {
        for(PerfilesType elemento : PerfilesType.values())
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
