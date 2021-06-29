  package util.types;

public enum RutasBaseType {
//	RUTA_BASE_IMAGENES("D:/cdcBases/kambioOnlineBase/imagenes/"),
//	RUTA_BASE_PLANTILLAS("D:/cdcBases/kambioOnlineBase/plantillas/"),
//	RUTA_RECURSO_IMAGENES_TEMPORAL("D:/cdcBases/kambioOnlineBase/imagenes/temp/");
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
