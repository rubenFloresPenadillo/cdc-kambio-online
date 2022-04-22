package util.types;

public enum CadenasType {
	
	VACIO(""),
	NULO("null"),
	ESPACIO(" "),
	COMA(","),
	COMA_ESPACIO(", "),
	GUION("-"),
	GUION_BAJO("_"),
	GUION_ESPACIOS_LATERALES(" - "),
	PARENTESIS_INI("("),
	PARENTESIS_FIN(")"),
	PORCENTAJE("%"),
	INTERROGACION_APERTURA("¿"),
	INTERROGACION_CIERRE("?"),
	AMPERSAND("&"),
	IGUAL("="),
	SALTO_LINEA("\n"),
	INDICADOR_SI("SI"),
	INDICADOR_NO("NO"),
	INDICADOR_PROCESO_OK("OK"),
	INDICADOR_PROCESO_ACTUALIZA_OK("UOK"),
	SLASH("/"),
	BACK_SLASH("\\"),
	CADENA_USUARIO("U"),
	CADENA_USUARIO_SISTEMA("SYSTEM_APP"),
	CORREO_DE_ADMINISTRACION("rfloresp@icreando.com"),
	DOMINIO_WEB("www.rhaak.com"),
//	DOMINIO_WEB("http://localhost:8080/ipp"),
	NOMBRE_COMERCIO("Rhaak");
	private String valor;

	private CadenasType(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
		
}
