package util.types;

public enum TipoDocumentoElectronicoType {
	
	FACTURA(1,"FA","FACTURA"),
	BOLETA(2,"BO","BOLETA"),
	NOTA_DE_CREDITO(3,"NC","NOTA DE CREDITO"),
	NOTE_DE_DEBITO(4,"ND","NOTA DE DEBITO");
	
	private Integer llave;
	private String codigoCorto;
	private String descripcion;

	private TipoDocumentoElectronicoType(Integer llave, String codigoCorto, String descripcion) {
		this.llave = llave;
		this.codigoCorto = codigoCorto;
		this.descripcion = descripcion;
	}

	public Integer getLlave() {
		return llave;
	}

	public String getCodigoCorto() {
		return codigoCorto;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
