  package util.types;

public enum RutasBaseType {
	RUTA_BASE_IMAGENES("D:/cdcBases/hmafgBase/imagenes/"),
	RUTA_BASE_PLANTILLAS("D:/cdcBases/hmafgBase/plantillas/"),
	RUTA_RECURSO_IMAGENES_TEMPORAL("D:/cdcBases/hmafgBase/imagenes/temp/");	
//	RUTA_BASE_IMAGENES("/etc/hmafgBase/imagenes/"),
//	RUTA_BASE_PLANTILLAS("/etc/hmafgBase/plantillas/"),
//	RUTA_RECURSO_IMAGENES_TEMPORAL("resources/imagenes/temp/");

	private String valor;
	
	private RutasBaseType(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
