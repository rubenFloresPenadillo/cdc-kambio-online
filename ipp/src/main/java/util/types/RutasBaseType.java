  package util.types;

public enum RutasBaseType {
//	RUTA_BASE_IMAGENES("D:/cdcBases/rhaakBase/imagenes/"),
//	RUTA_BASE_PLANTILLAS("D:/cdcBases/rhaakBase/plantillas/"),
//	RUTA_RECURSO_IMAGENES_TEMPORAL("D:/cdcBases/rhaakBase/imagenes/temp/");
	RUTA_BASE_IMAGENES("/etc/rhaakBase/imagenes/"),
	RUTA_BASE_PLANTILLAS("/etc/rhaakBase/plantillas/"),
	RUTA_RECURSO_IMAGENES_TEMPORAL("resources/imagenes/temp/");

	private String valor;
	
	private RutasBaseType(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
