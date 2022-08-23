package util.types;

public enum TipoOperacionCambioType {
	
	COMPRA_DOLARES(1,"CD","COMPRA DE DOLARES"),
	VENTA_DOLARES(2,"VD","VENTA DE DOLARES");
	
	private Integer llave;
	private String codigo;
	private String descripcion;

	private TipoOperacionCambioType(Integer llave, String codigo, String descripcion) {
		this.llave = llave;
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public Integer getLlave() {
		return llave;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
