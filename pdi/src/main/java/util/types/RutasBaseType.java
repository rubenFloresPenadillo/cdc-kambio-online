  package util.types;

public enum RutasBaseType {
	RUTA_BASE_IMAGENES("D:/cdcBases/precisoBase/imagenes/"),
	RUTA_BASE_PLANTILLAS("D:/cdcBases/precisoBase/plantillas/"),
	RUTA_RECURSO_IMAGENES_TEMPORAL("D:/cdcBases/precisoBase/imagenes/temp/");
//	RUTA_BASE_IMAGENES("/etc/precisoBase/imagenes/"),
//	RUTA_BASE_PLANTILLAS("/etc/precisoBase/plantillas/"),
//	RUTA_RECURSO_IMAGENES_TEMPORAL("resources/imagenes/temp/");

	private String valor;
	
	private RutasBaseType(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
