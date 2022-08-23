package hibernate.entidades;
// Generated 14 ago. 2022 14:52:21 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TpFactuDocumDetal generated by hbm2java
 */
@Entity
@Table(name = "tp_factu_docum_detal", schema = "public")
public class TpFactuDocumDetal implements java.io.Serializable {

	private Integer codFactDocuDetal;
	private TpFactuDocumCabec tpFactuDocumCabec;
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

	public TpFactuDocumDetal() {
	}

	public TpFactuDocumDetal(Integer codFactDocuDetal, TpFactuDocumCabec tpFactuDocumCabec, String unidadDeMedida,
			String descripcion, Double cantidad, Double valorUnitario, Double precioUnitario, Double subtotal,
			short tipoDeIgv, Double igv, Double total, short anticipoRegularizacion, short indEsta, String usuApliCrea,
			Date fecCreaRegi) {
		this.codFactDocuDetal = codFactDocuDetal;
		this.tpFactuDocumCabec = tpFactuDocumCabec;
		this.unidadDeMedida = unidadDeMedida;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.valorUnitario = valorUnitario;
		this.precioUnitario = precioUnitario;
		this.subtotal = subtotal;
		this.tipoDeIgv = tipoDeIgv;
		this.igv = igv;
		this.total = total;
		this.anticipoRegularizacion = anticipoRegularizacion;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
	}

	public TpFactuDocumDetal(Integer codFactDocuDetal, TpFactuDocumCabec tpFactuDocumCabec, String unidadDeMedida,
			String codigo, String descripcion, Double cantidad, Double valorUnitario, Double precioUnitario,
			Double descuento, Double subtotal, short tipoDeIgv, Double igv, Double total, short anticipoRegularizacion,
			String anticipoDocumentoSerie, Integer anticipoDocumentoNumero, String codigoProductoSunat, short indEsta,
			String usuApliCrea, Date fecCreaRegi, String usuApliModi, Date fecModiRegi) {
		this.codFactDocuDetal = codFactDocuDetal;
		this.tpFactuDocumCabec = tpFactuDocumCabec;
		this.unidadDeMedida = unidadDeMedida;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.valorUnitario = valorUnitario;
		this.precioUnitario = precioUnitario;
		this.descuento = descuento;
		this.subtotal = subtotal;
		this.tipoDeIgv = tipoDeIgv;
		this.igv = igv;
		this.total = total;
		this.anticipoRegularizacion = anticipoRegularizacion;
		this.anticipoDocumentoSerie = anticipoDocumentoSerie;
		this.anticipoDocumentoNumero = anticipoDocumentoNumero;
		this.codigoProductoSunat = codigoProductoSunat;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
		this.usuApliModi = usuApliModi;
		this.fecModiRegi = fecModiRegi;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cod_fact_docu_detal", unique = true, nullable = false)
	public Integer getCodFactDocuDetal() {
		return this.codFactDocuDetal;
	}

	public void setCodFactDocuDetal(Integer codFactDocuDetal) {
		this.codFactDocuDetal = codFactDocuDetal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_fact_docu_cabec", nullable = false)
	public TpFactuDocumCabec getTpFactuDocumCabec() {
		return this.tpFactuDocumCabec;
	}

	public void setTpFactuDocumCabec(TpFactuDocumCabec tpFactuDocumCabec) {
		this.tpFactuDocumCabec = tpFactuDocumCabec;
	}

	@Column(name = "unidad_de_medida", nullable = false, length = 5)
	public String getUnidadDeMedida() {
		return this.unidadDeMedida;
	}

	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}

	@Column(name = "codigo", length = 50)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "descripcion", nullable = false, length = 250)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "cantidad", nullable = false, precision = 17, scale = 17)
	public Double getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	@Column(name = "valor_unitario", nullable = false, precision = 17, scale = 17)
	public Double getValorUnitario() {
		return this.valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@Column(name = "precio_unitario", nullable = false, precision = 17, scale = 17)
	public Double getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	@Column(name = "descuento", precision = 17, scale = 17)
	public Double getDescuento() {
		return this.descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	@Column(name = "subtotal", nullable = false, precision = 17, scale = 17)
	public Double getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	@Column(name = "tipo_de_igv", nullable = false)
	public short getTipoDeIgv() {
		return this.tipoDeIgv;
	}

	public void setTipoDeIgv(short tipoDeIgv) {
		this.tipoDeIgv = tipoDeIgv;
	}

	@Column(name = "igv", nullable = false, precision = 17, scale = 17)
	public Double getIgv() {
		return this.igv;
	}

	public void setIgv(Double igv) {
		this.igv = igv;
	}

	@Column(name = "total", nullable = false, precision = 17, scale = 17)
	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Column(name = "anticipo_regularizacion", nullable = false)
	public short getAnticipoRegularizacion() {
		return this.anticipoRegularizacion;
	}

	public void setAnticipoRegularizacion(short anticipoRegularizacion) {
		this.anticipoRegularizacion = anticipoRegularizacion;
	}

	@Column(name = "anticipo_documento_serie", length = 4)
	public String getAnticipoDocumentoSerie() {
		return this.anticipoDocumentoSerie;
	}

	public void setAnticipoDocumentoSerie(String anticipoDocumentoSerie) {
		this.anticipoDocumentoSerie = anticipoDocumentoSerie;
	}

	@Column(name = "anticipo_documento_numero")
	public Integer getAnticipoDocumentoNumero() {
		return this.anticipoDocumentoNumero;
	}

	public void setAnticipoDocumentoNumero(Integer anticipoDocumentoNumero) {
		this.anticipoDocumentoNumero = anticipoDocumentoNumero;
	}

	@Column(name = "codigo_producto_sunat", length = 8)
	public String getCodigoProductoSunat() {
		return this.codigoProductoSunat;
	}

	public void setCodigoProductoSunat(String codigoProductoSunat) {
		this.codigoProductoSunat = codigoProductoSunat;
	}

	@Column(name = "ind_esta", nullable = false)
	public short getIndEsta() {
		return this.indEsta;
	}

	public void setIndEsta(short indEsta) {
		this.indEsta = indEsta;
	}

	@Column(name = "usu_apli_crea", nullable = false, length = 100)
	public String getUsuApliCrea() {
		return this.usuApliCrea;
	}

	public void setUsuApliCrea(String usuApliCrea) {
		this.usuApliCrea = usuApliCrea;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fec_crea_regi", nullable = false, length = 29)
	public Date getFecCreaRegi() {
		return this.fecCreaRegi;
	}

	public void setFecCreaRegi(Date fecCreaRegi) {
		this.fecCreaRegi = fecCreaRegi;
	}

	@Column(name = "usu_apli_modi", length = 100)
	public String getUsuApliModi() {
		return this.usuApliModi;
	}

	public void setUsuApliModi(String usuApliModi) {
		this.usuApliModi = usuApliModi;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fec_modi_regi", length = 29)
	public Date getFecModiRegi() {
		return this.fecModiRegi;
	}

	public void setFecModiRegi(Date fecModiRegi) {
		this.fecModiRegi = fecModiRegi;
	}

}