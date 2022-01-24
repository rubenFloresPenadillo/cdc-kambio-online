package util.types;

public enum PaginasPrivadasType {
	
	PAGINA_INDEX("/index.xhtml"),
	PAGINA_SESION_TIMEOUT("/paginaSesionTimeout.xhtml"),
	//Cliente
	PAGINA_INICIO("/paginas/privado/intranet/inicio.xhtml"),
	PAGINA_ELEGIR_PERFIL("/paginas/privado/intranet/elegirPerfil.xhtml"),
	PAGINA_INGRESO("/paginas/privado/ingreso.xhtml"),
	PAGINA_DATOS("/paginas/privado/intranet/datos.xhtml"),
	PAGINA_OPERACIONES("/paginas/privado/intranet/operaciones.xhtml"),
	PAGINA_OPERACIONES_CLIENTE_ITEM("/paginas/privado/intranet/operacionesItem.xhtml"),
	PAGINA_CUENTA("/paginas/privado/intranet/cuenta.xhtml"),
	PAGINA_CUENTA_ITEM("/paginas/privado/intranet/cuentaItem.xhtml"),
	PAGINA_ENLACE_NO_DISPONIBLE("/paginas/privado/enlaceNoDisponible.xhtml"),
	PAGINA_CUENTA_PENDIENTE_ACTIVACION("/paginas/privado/cuentaPendienteActivacion.xhtml"),
	PAGINA_CUENTA_RESTABLECER_CONTRASENIA("/paginas/privado/restablerCuentaEnviado.xhtml"),
	PAGINA_CUENTA_CONFIRMA_CUENTA_CODIGO("/paginas/privado/confirmaCuentaRegistrada.xhtml"),
	// Personal
	PAGINA_INDEX_PERSONAL("/mda/index.xhtml"),
	PAGINA_PANEL_PRINCIPAL("/mda/panelPrincipal.xhtml"),
	PAGINA_DIVISAS("/mda/opciones/divisas.xhtml"),
	PAGINA_DIVISA_ITEM("/mda/opciones/divisasItem.xhtml"),
	PAGINA_CAMBIO("/mda/opciones/cambio.xhtml"),
	PAGINA_CAMBIO_ITEM("/mda/opciones/cambioItem.xhtml"),
	PAGINA_BANCO("/mda/opciones/bancos.xhtml"),
	PAGINA_BANCO_ITEM("/mda/opciones/bancoItem.xhtml"),
	PAGINA_CUENTA_BANCARIA_ADMIN("/mda/opciones/cuentasBancariasAdmin.xhtml"),
	PAGINA_CUENTA_BANCARIA_ADMIN_ITEM("/mda/opciones/cuentasBancariasAdminItem.xhtml"),
	PAGINA_USUARIO("/mda/opciones/usuarios.xhtml"),
	PAGINA_USUARIO_ITEM("/mda/opciones/usuarioItem.xhtml"),
	PAGINA_PARAMETRO_GENERAL("/mda/opciones/parametrosGenerales.xhtml"),
	PAGINA_PARAMETRO_GENERAL_ITEM("/mda/opciones/parametroGeneralItem.xhtml"),
	PAGINA_OPERACIONES_CONTROL("/mda/opciones/operacionesControl.xhtml"),
	PAGINA_OPERACIONES_CONTROL_ITEM("/mda/opciones/operacionesControlItem.xhtml"),
	PAGINA_ENTRADA_GENERAL("/mda/opciones/blogGeneral.xhtml"),
	PAGINA_ENTRADA_GENERAL_ITEM("/mda/opciones/blogGeneralItem.xhtml");
	

	private String valor;

	private PaginasPrivadasType(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
		
}
