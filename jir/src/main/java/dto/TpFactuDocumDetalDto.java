package dto;
// Generated 14 ago. 2022 14:52:21 by Hibernate Tools 5.2.12.Final

import java.util.Date;


public class TpFactuDocumDetalDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codFactDocuDetal;
	private TpFactuDocumCabecDto tpFactuDocumCabec;
	private String unidadDeMedida;
	private String codigo;
	private String descripcion;
	private Double cantidad;
	private Double valorUnitario;
	private Double precioUnitario;
	private Double descuento;
	private Double subtotal;
	private short tipoDeIgv;
	private Double igv;
	private Double total;
	private short anticipoRegularizacion;
	private String anticipoDocumentoSerie;
	private Integer anticipoDocumentoNumero;
	private String codigoProductoSunat;
	private short indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpFactuDocumDetalDto() {
		tpFactuDocumCabec = new TpFactuDocumCabecDto();
	}

	public Integer getCodFactDocuDetal() {
		return codFactDocuDetal;
	}

	public void setCodFactDocuDetal(Integer codFactDocuDetal) {
		this.codFactDocuDetal = codFactDocuDetal;
	}

	public TpFactuDocumCabecDto getTpFactuDocumCabec() {
		return tpFactuDocumCabec;
	}

	public void setTpFactuDocumCabec(TpFactuDocumCabecDto tpFactuDocumCabec) {
		this.tpFactuDocumCabec = tpFactuDocumCabec;
	}

	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}

	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public short getTipoDeIgv() {
		return tipoDeIgv;
	}

	public void setTipoDeIgv(short tipoDeIgv) {
		this.tipoDeIgv = tipoDeIgv;
	}

	public Double getIgv() {
		return igv;
	}

	public void setIgv(Double igv) {
		this.igv = igv;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public short getAnticipoRegularizacion() {
		return anticipoRegularizacion;
	}

	public void setAnticipoRegularizacion(short anticipoRegularizacion) {
		this.anticipoRegularizacion = anticipoRegularizacion;
	}

	public String getAnticipoDocumentoSerie() {
		return anticipoDocumentoSerie;
	}

	public void setAnticipoDocumentoSerie(String anticipoDocumentoSerie) {
		this.anticipoDocumentoSerie = anticipoDocumentoSerie;
	}

	public Integer getAnticipoDocumentoNumero() {
		return anticipoDocumentoNumero;
	}

	public void setAnticipoDocumentoNumero(Integer anticipoDocumentoNumero) {
		this.anticipoDocumentoNumero = anticipoDocumentoNumero;
	}

	public String getCodigoProductoSunat() {
		return codigoProductoSunat;
	}

	public void setCodigoProductoSunat(String codigoProductoSunat) {
		this.codigoProductoSunat = codigoProductoSunat;
	}

	public short getIndEsta() {
		return indEsta;
	}

	public void setIndEsta(short indEsta) {
		this.indEsta = indEsta;
	}

	public String getUsuApliCrea() {
		return usuApliCrea;
	}

	public void setUsuApliCrea(String usuApliCrea) {
		this.usuApliCrea = usuApliCrea;
	}

	public Date getFecCreaRegi() {
		return fecCreaRegi;
	}

	public void setFecCreaRegi(Date fecCreaRegi) {
		this.fecCreaRegi = fecCreaRegi;
	}

	public String getUsuApliModi() {
		return usuApliModi;
	}

	public void setUsuApliModi(String usuApliModi) {
		this.usuApliModi = usuApliModi;
	}

	public Date getFecModiRegi() {
		return fecModiRegi;
	}

	public void setFecModiRegi(Date fecModiRegi) {
		this.fecModiRegi = fecModiRegi;
	}

}
