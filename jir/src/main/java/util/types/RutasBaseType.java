  package util.types;

public enum RutasBaseType {
	RUTA_BASE_IMAGENES("D:/cdcBases/jirehPlusBase/imagenes/"),
	RUTA_BASE_PLANTILLAS("D:/cdcBases/jirehPlusBase/plantillas/"),
	RUTA_RECURSO_IMAGENES_TEMPORAL("D:/cdcBases/jirehPlusBase/imagenes/temp/");
//	RUTA_BASE_IMAGENES("/etc/jirehPlusBase/imagenes/"),
//	RUTA_BASE_PLANTILLAS("/etc/jirehPlusBase/plantillas/"),
//	RUTA_RECURSO_IMAGENES_TEMPORAL("resources/imagenes/temp/");

	private String valor;
	
	private RutasBaseType(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
