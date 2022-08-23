package util.types;

import java.util.HashMap;
import java.util.Map;

public enum EstadosInternosDocumentosFEType {

	PENDIENTE_ENVIO(1, "Pendiente de envio"),
	ENVIADO(2, "Enviado"),
	GENERADO(3, "Generado");

	private final Integer idElemento;
	private final String nombreElemento;

	private EstadosInternosDocumentosFEType(Integer idElemento, String nombreElemento) {
		this.idElemento = idElemento;
		this.nombreElemento = nombreElemento;
	}
	
	//Lookup table
	private static final Map<Integer, EstadosInternosDocumentosFEType> lookup = new HashMap<>();
	
	//Populate the lookup table on loading time
    static
    {
        for(EstadosInternosDocumentosFEType elemento : EstadosInternosDocumentosFEType.values())
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
