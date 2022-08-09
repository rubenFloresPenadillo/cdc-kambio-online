package util.types;

public enum PlantillasType {

	PLANTILLA_ENVIAR_REGISTRO_OPERACION("enviarRegistroOperacion.ftl"),
	PLANTILLA_ENVIAR_FINALIZO_OPERACION("enviarFinalizoOperacion.ftl"),
	PLANTILLA_ENVIAR_CANCELA_OPERACION("enviarCancelaOperacion.ftl"),
	PLANTILLA_ENVIAR_ENLACE_ACTIVACION_CUENTA("enviarEnlaceActivacionCuenta.ftl"),
	PLANTILLA_ENVIAR_ENLACE_ACTIVACION_CUENTA_CODIGO("enviarEnlaceActivacionCuentaCodigo.ftl"),
	PLANTILLA_ENVIAR_ENLACE_RESTABLECER_CUENTA("enviarEnlaceRestablecerCuenta.ftl"),
	PLANTILLA_ENVIAR_REGISTRO_RECLAMO_CLIENTE("enviarReclamoQuejaCliente.ftl"),
	PLANTILLA_ENVIAR_REGISTRO_RECLAMO_ADMIN("enviarReclamoQuejaAdministracion.ftl"),
	PLANTILLA_CORREO_COMERCIO_DESDE_CONTACTO("enviarCorreoAComercioDesdeContacto");
	
	private String nombre;
	
	private PlantillasType(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

}
