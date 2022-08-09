  package util.types;

public enum RutasBaseType {
	RUTA_BASE_IMAGENES("D:/cdcBases/icambioBase/imagenes/"),
	RUTA_BASE_PLANTILLAS("D:/cdcBases/icambioBase/plantillas/"),
	RUTA_RECURSO_IMAGENES_TEMPORAL("D:/cdcBases/icambioBase/imagenes/temp/");
//	RUTA_BASE_IMAGENES("/etc/icambioBase/imagenes/"),
//	RUTA_BASE_PLANTILLAS("/etc/icambioBase/plantillas/"),
//	RUTA_RECURSO_IMAGENES_TEMPORAL("resources/imagenes/temp/");

	private String valor;
	
	private RutasBaseType(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
