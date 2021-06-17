  package util.types;

public enum RutasBaseType {
//	RUTA_BASE_IMAGENES("C:/kambioOnlineBase/imagenes/"),
//	RUTA_BASE_PLANTILLAS("C:/kambioOnlineBase/plantillas/"),
//	RUTA_RECURSO_IMAGENES_TEMPORAL("resources/imagenes/temp/");
	RUTA_BASE_IMAGENES("/etc/kambioOnlineBase/imagenes/"),
	RUTA_BASE_PLANTILLAS("/etc/kambioOnlineBase/plantillas/"),
	RUTA_RECURSO_IMAGENES_TEMPORAL("resources/imagenes/temp/");

	private String valor;
	
	private RutasBaseType(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
