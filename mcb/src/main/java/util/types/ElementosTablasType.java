package util.types;

public enum ElementosTablasType {

	TIPO_PERSONERIA_NATURAL(1, "NATURAL"),
	TIPO_PERSONERIA_JURIDICA(2, "JURIDICA"),
	ESTADO_OPERACION_INICIADA(1, "INICIADA"),
	ESTADO_OPERACION_VERIFICACION(2, "VERIFICACION"),
	ESTADO_OPERACION_FINALIZADA(3, "FINALIZADA"),
	ESTADO_OPERACION_CANCELADA_AUTOMATICA(4, "CANCEL. AUTOM."),
	ESTADO_OPERACION_CANCELADA_OPERACION(5, "CANCEL. OPERA."),
	ESTADO_OPERACION_CANCELADA_CLIENTE(6, "CANCEL. CLIEN."),
//	ESTADO_CUENTA_USUARIO_REGISTRADA(1, "REGISTRADA"),
//	ESTADO_CUENTA_USUARIO_ACTIVADA(2, "ACTIVADA"),
//	ESTADO_CUENTA_USUARIO_BLOQUEADA(3, "BLOQUEADA"),
//	ESTADO_CUENTA_USUARIO_ELIMINADA(4, "ELIMINADA"),
	TIPO_DOCUMENTO_PERSONA_DNI(1,"DNI"),
	TIPO_DOCUMENTO_PERSONA_CE(2,"CE"),
	TIPO_DOCUMENTO_PERSONA_PASAPORTE(3,"PASAPORTE"),
	TIPO_DOCUMENTO_PERSONA_RUC(4,"RUC"),

	TIPO_SERVICIO_COMPRA(1,"COMPRA"),
	TIPO_SERVICIO_VENTA(2,"VENTA"),
	
	TIPO_REGISTRO_RECLAMO(1,"RECLAMO"),
	TIPO_REGISTRO_QUEJA(2,"QUEJA");
	
	private final Integer idElemento;
	private final String nombreElemento;
	
	private ElementosTablasType(Integer idElemento, String nombreElemento) {
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
