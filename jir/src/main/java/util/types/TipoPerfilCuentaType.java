package util.types;

public enum TipoPerfilCuentaType {
	
	TIPO_PERFIL_CUENTA_PERSONA(1, "PERSONA"),
	TIPO_PERFIL_CUENTA_EMPRESA(2, "EMPRESA");
	
	private final Integer idElemento;
	private final String nombreElemento;
	
	private TipoPerfilCuentaType(Integer idElemento, String nombreElemento) {
		this.idElemento = idElemento;
		this.nombreElemento = nombreElemento;
	}

	public Integer getIdElemento() {
		return idElemento;
	}
	
	public String getNombreElemento() {
		return nombreElemento;
	}
		
}
