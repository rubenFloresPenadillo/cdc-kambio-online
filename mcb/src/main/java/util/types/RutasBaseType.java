  package util.types;

public enum RutasBaseType {
	RUTA_BASE_IMAGENES("D:/cdcBases/multicambioBase/imagenes/"),
	RUTA_BASE_PLANTILLAS("D:/cdcBases/multicambioBase/plantillas/"),
	RUTA_RECURSO_IMAGENES_TEMPORAL("D:/cdcBases/multicambioBase/imagenes/temp/");	
//	RUTA_BASE_IMAGENES("/etc/multicambioBase/imagenes/"),
//	RUTA_BASE_PLANTILLAS("/etc/multicambioBase/plantillas/"),
//	RUTA_RECURSO_IMAGENES_TEMPORAL("resources/imagenes/temp/");

	private String valor;
	
	private RutasBaseType(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
