package util.types;

public enum RegistroActivoType {
	
	ACTIVO(1,"A","Activo","Sí"),
	INACTIVO(0,"I","Inactivo","No");
	
	private Integer llave;
	private String valor;
	private String descripcion;
	private String descripcionAlternativa;

	private RegistroActivoType(Integer llave, String valor, String descripcion, String descripcionAlternativa) {
		this.llave = llave;
		this.valor = valor;
		this.descripcion = descripcion;
		this.descripcionAlternativa = descripcionAlternativa;
	}

	public Integer getLlave() {
		return llave;
	}

	public String getValor() {
		return valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getDescripcionAlternativa() {
		return descripcionAlternativa;
	}

}
