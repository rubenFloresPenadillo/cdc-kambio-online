package dto;
// Generated 14 ago. 2022 14:52:21 by Hibernate Tools 5.2.12.Final

import java.util.Date;

public class TpFactuDocumCabecDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codFactDocuCabec;
	private Integer codOperClie;
	private short tipoDeComprobante;
	private String serie;
	private Integer numero;
	private short sunatTransaction;
	private String clienteTipoDeDocumento;
	private String clienteNumeroDeDocumento;
	private String clienteDenominacion;
	private String clienteDireccion;
	private String clienteEmail;
	private String clienteEmail1;
	private String clienteEmail2;
	private Date fechaDeEmision;
	private Date fechaDeVencimiento;
	private short moneda;
	private Double tipoDeCambio;
	private Double porcentajeDeIgv;
	private Double descuentoGlobal;
	private Double totalDescuento;
	private Double totalAnticipo;
	private Double totalGravada;
	private Double totalInafecta;
	private Double totalExonerada;
	private Double totalIgv;
	private Double totalGratuita;
	private Double totalOtrosCargos;
	private Double totalIsc;
	private Double total;
	private Short percepcionTipo;
	private Double percepcionBaseImponible;
	private Double totalPercepcion;
	private Double totalIncluidoPercepcion;
	private Short detraccion;
	private String observaciones;
	private Short documentoQueSeModificaTipo;
	private String documentoQueSeModificaSerie;
	private Integer documentoQueSeModificaNumero;
	private Short tipoDeNotaDeCredito;
	private Short tipoDeNotaDeDebito;
	private Short enviarAutomaticamenteALaSunat;
	private Short enviarAutomaticamenteAlCliente;
	private String codigoUnico;
	private String condicionesDePago;
	private String medioDePago;
	private String placaVehiculo;
	private String ordenCompraServicio;
	private String formatoDePdf;
	private String repuestaEnlace;
	private Short repuestaAceptadaPorSunat;
	private String repuestaSunatDescription;
	private String repuestaSunatNote;
	private String repuestaSunatResponsecode;
	private String repuestaSunatSoapError;
	private Short repuestaAnulado;
	private String repuestaPdfZipBase64;
	private String repuestaXmlZipBase64;
	private String repuestaCdrZipBase64;
	private String repuestaCadenaParaCodigoQr;
	private String repuestaCodigoHash;
	private String repuestaEnlaceDelPdf;
	private String repuestaEnlaceDelXml;
	private String repuestaEnlaceDelCdr;
	private String repuestaErrors;
	private String repuestaCodigoError;
	private String desCortDocuGene;
	private Short codEstaDocu;
	private String menDetaDocu;
	private short indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;
	
	
	public TpFactuDocumCabecDto() {
	}
	
	public Integer getCodFactDocuCabec() {
		return codFactDocuCabec;
	}
	public void setCodFactDocuCabec(Integer codFactDocuCabec) {
		this.codFactDocuCabec = codFactDocuCabec;
	}
	public Integer getCodOperClie() {
		return codOperClie;
	}
	public void setCodOperClie(Integer codOperClie) {
		this.codOperClie = codOperClie;
	}
	public short getTipoDeComprobante() {
		return tipoDeComprobante;
	}
	public void setTipoDeComprobante(short tipoDeComprobante) {
		this.tipoDeComprobante = tipoDeComprobante;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public short getSunatTransaction() {
		return sunatTransaction;
	}
	public void setSunatTransaction(short sunatTransaction) {
		this.sunatTransaction = sunatTransaction;
	}
	public String getClienteTipoDeDocumento() {
		return clienteTipoDeDocumento;
	}
	public void setClienteTipoDeDocumento(String clienteTipoDeDocumento) {
		this.clienteTipoDeDocumento = clienteTipoDeDocumento;
	}
	public String getClienteNumeroDeDocumento() {
		return clienteNumeroDeDocumento;
	}
	public void setClienteNumeroDeDocumento(String clienteNumeroDeDocumento) {
		this.clienteNumeroDeDocumento = clienteNumeroDeDocumento;
	}
	public String getClienteDenominacion() {
		return clienteDenominacion;
	}
	public void setClienteDenominacion(String clienteDenominacion) {
		this.clienteDenominacion = clienteDenominacion;
	}
	public String getClienteDireccion() {
		return clienteDireccion;
	}
	public void setClienteDireccion(String clienteDireccion) {
		this.clienteDireccion = clienteDireccion;
	}
	public String getClienteEmail() {
		return clienteEmail;
	}
	public void setClienteEmail(String clienteEmail) {
		this.clienteEmail = clienteEmail;
	}
	public String getClienteEmail1() {
		return clienteEmail1;
	}
	public void setClienteEmail1(String clienteEmail1) {
		this.clienteEmail1 = clienteEmail1;
	}
	public String getClienteEmail2() {
		return clienteEmail2;
	}
	public void setClienteEmail2(String clienteEmail2) {
		this.clienteEmail2 = clienteEmail2;
	}
	public Date getFechaDeEmision() {
		return fechaDeEmision;
	}
	public void setFechaDeEmision(Date fechaDeEmision) {
		this.fechaDeEmision = fechaDeEmision;
	}
	public Date getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}
	public void setFechaDeVencimiento(Date fechaDeVencimiento) {
		this.fechaDeVencimiento = fechaDeVencimiento;
	}
	public short getMoneda() {
		return moneda;
	}
	public void setMoneda(short moneda) {
		this.moneda = moneda;
	}
	public Double getTipoDeCambio() {
		return tipoDeCambio;
	}
	public void setTipoDeCambio(Double tipoDeCambio) {
		this.tipoDeCambio = tipoDeCambio;
	}
	public Double getPorcentajeDeIgv() {
		return porcentajeDeIgv;
	}
	public void setPorcentajeDeIgv(Double porcentajeDeIgv) {
		this.porcentajeDeIgv = porcentajeDeIgv;
	}
	public Double getDescuentoGlobal() {
		return descuentoGlobal;
	}
	public void setDescuentoGlobal(Double descuentoGlobal) {
		this.descuentoGlobal = descuentoGlobal;
	}
	public Double getTotalDescuento() {
		return totalDescuento;
	}
	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}
	public Double getTotalAnticipo() {
		return totalAnticipo;
	}
	public void setTotalAnticipo(Double totalAnticipo) {
		this.totalAnticipo = totalAnticipo;
	}
	public Double getTotalGravada() {
		return totalGravada;
	}
	public void setTotalGravada(Double totalGravada) {
		this.totalGravada = totalGravada;
	}
	public Double getTotalInafecta() {
		return totalInafecta;
	}
	public void setTotalInafecta(Double totalInafecta) {
		this.totalInafecta = totalInafecta;
	}
	public Double getTotalExonerada() {
		return totalExonerada;
	}
	public void setTotalExonerada(Double totalExonerada) {
		this.totalExonerada = totalExonerada;
	}
	public Double getTotalIgv() {
		return totalIgv;
	}
	public void setTotalIgv(Double totalIgv) {
		this.totalIgv = totalIgv;
	}
	public Double getTotalGratuita() {
		return totalGratuita;
	}
	public void setTotalGratuita(Double totalGratuita) {
		this.totalGratuita = totalGratuita;
	}
	public Double getTotalOtrosCargos() {
		return totalOtrosCargos;
	}
	public void setTotalOtrosCargos(Double totalOtrosCargos) {
		this.totalOtrosCargos = totalOtrosCargos;
	}
	public Double getTotalIsc() {
		return totalIsc;
	}
	public void setTotalIsc(Double totalIsc) {
		this.totalIsc = totalIsc;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Short getPercepcionTipo() {
		return percepcionTipo;
	}
	public void setPercepcionTipo(Short percepcionTipo) {
		this.percepcionTipo = percepcionTipo;
	}
	public Double getPercepcionBaseImponible() {
		return percepcionBaseImponible;
	}
	public void setPercepcionBaseImponible(Double percepcionBaseImponible) {
		this.percepcionBaseImponible = percepcionBaseImponible;
	}
	public Double getTotalPercepcion() {
		return totalPercepcion;
	}
	public void setTotalPercepcion(Double totalPercepcion) {
		this.totalPercepcion = totalPercepcion;
	}
	public Double getTotalIncluidoPercepcion() {
		return totalIncluidoPercepcion;
	}
	public void setTotalIncluidoPercepcion(Double totalIncluidoPercepcion) {
		this.totalIncluidoPercepcion = totalIncluidoPercepcion;
	}
	public Short getDetraccion() {
		return detraccion;
	}
	public void setDetraccion(Short detraccion) {
		this.detraccion = detraccion;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Short getDocumentoQueSeModificaTipo() {
		return documentoQueSeModificaTipo;
	}
	public void setDocumentoQueSeModificaTipo(Short documentoQueSeModificaTipo) {
		this.documentoQueSeModificaTipo = documentoQueSeModificaTipo;
	}
	public String getDocumentoQueSeModificaSerie() {
		return documentoQueSeModificaSerie;
	}
	public void setDocumentoQueSeModificaSerie(String documentoQueSeModificaSerie) {
		this.documentoQueSeModificaSerie = documentoQueSeModificaSerie;
	}
	public Integer getDocumentoQueSeModificaNumero() {
		return documentoQueSeModificaNumero;
	}
	public void setDocumentoQueSeModificaNumero(Integer documentoQueSeModificaNumero) {
		this.documentoQueSeModificaNumero = documentoQueSeModificaNumero;
	}
	public Short getTipoDeNotaDeCredito() {
		return tipoDeNotaDeCredito;
	}
	public void setTipoDeNotaDeCredito(Short tipoDeNotaDeCredito) {
		this.tipoDeNotaDeCredito = tipoDeNotaDeCredito;
	}
	public Short getTipoDeNotaDeDebito() {
		return tipoDeNotaDeDebito;
	}
	public void setTipoDeNotaDeDebito(Short tipoDeNotaDeDebito) {
		this.tipoDeNotaDeDebito = tipoDeNotaDeDebito;
	}
	public Short getEnviarAutomaticamenteALaSunat() {
		return enviarAutomaticamenteALaSunat;
	}
	public void setEnviarAutomaticamenteALaSunat(Short enviarAutomaticamenteALaSunat) {
		this.enviarAutomaticamenteALaSunat = enviarAutomaticamenteALaSunat;
	}
	public Short getEnviarAutomaticamenteAlCliente() {
		return enviarAutomaticamenteAlCliente;
	}
	public void setEnviarAutomaticamenteAlCliente(Short enviarAutomaticamenteAlCliente) {
		this.enviarAutomaticamenteAlCliente = enviarAutomaticamenteAlCliente;
	}
	public String getCodigoUnico() {
		return codigoUnico;
	}
	public void setCodigoUnico(String codigoUnico) {
		this.codigoUnico = codigoUnico;
	}
	public String getCondicionesDePago() {
		return condicionesDePago;
	}
	public void setCondicionesDePago(String condicionesDePago) {
		this.condicionesDePago = condicionesDePago;
	}
	public String getMedioDePago() {
		return medioDePago;
	}
	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
	}
	public String getPlacaVehiculo() {
		return placaVehiculo;
	}
	public void setPlacaVehiculo(String placaVehiculo) {
		this.placaVehiculo = placaVehiculo;
	}
	public String getOrdenCompraServicio() {
		return ordenCompraServicio;
	}
	public void setOrdenCompraServicio(String ordenCompraServicio) {
		this.ordenCompraServicio = ordenCompraServicio;
	}
	public String getFormatoDePdf() {
		return formatoDePdf;
	}
	public void setFormatoDePdf(String formatoDePdf) {
		this.formatoDePdf = formatoDePdf;
	}
	public String getRepuestaEnlace() {
		return repuestaEnlace;
	}
	public void setRepuestaEnlace(String repuestaEnlace) {
		this.repuestaEnlace = repuestaEnlace;
	}
	public Short getRepuestaAceptadaPorSunat() {
		return repuestaAceptadaPorSunat;
	}
	public void setRepuestaAceptadaPorSunat(Short repuestaAceptadaPorSunat) {
		this.repuestaAceptadaPorSunat = repuestaAceptadaPorSunat;
	}
	public String getRepuestaSunatDescription() {
		return repuestaSunatDescription;
	}
	public void setRepuestaSunatDescription(String repuestaSunatDescription) {
		this.repuestaSunatDescription = repuestaSunatDescription;
	}
	public String getRepuestaSunatNote() {
		return repuestaSunatNote;
	}
	public void setRepuestaSunatNote(String repuestaSunatNote) {
		this.repuestaSunatNote = repuestaSunatNote;
	}
	public String getRepuestaSunatResponsecode() {
		return repuestaSunatResponsecode;
	}
	public void setRepuestaSunatResponsecode(String repuestaSunatResponsecode) {
		this.repuestaSunatResponsecode = repuestaSunatResponsecode;
	}
	public String getRepuestaSunatSoapError() {
		return repuestaSunatSoapError;
	}
	public void setRepuestaSunatSoapError(String repuestaSunatSoapError) {
		this.repuestaSunatSoapError = repuestaSunatSoapError;
	}
	public Short getRepuestaAnulado() {
		return repuestaAnulado;
	}
	public void setRepuestaAnulado(Short repuestaAnulado) {
		this.repuestaAnulado = repuestaAnulado;
	}
	public String getRepuestaPdfZipBase64() {
		return repuestaPdfZipBase64;
	}
	public void setRepuestaPdfZipBase64(String repuestaPdfZipBase64) {
		this.repuestaPdfZipBase64 = repuestaPdfZipBase64;
	}
	public String getRepuestaXmlZipBase64() {
		return repuestaXmlZipBase64;
	}
	public void setRepuestaXmlZipBase64(String repuestaXmlZipBase64) {
		this.repuestaXmlZipBase64 = repuestaXmlZipBase64;
	}
	public String getRepuestaCdrZipBase64() {
		return repuestaCdrZipBase64;
	}
	public void setRepuestaCdrZipBase64(String repuestaCdrZipBase64) {
		this.repuestaCdrZipBase64 = repuestaCdrZipBase64;
	}
	public String getRepuestaCadenaParaCodigoQr() {
		return repuestaCadenaParaCodigoQr;
	}
	public void setRepuestaCadenaParaCodigoQr(String repuestaCadenaParaCodigoQr) {
		this.repuestaCadenaParaCodigoQr = repuestaCadenaParaCodigoQr;
	}
	public String getRepuestaCodigoHash() {
		return repuestaCodigoHash;
	}
	public void setRepuestaCodigoHash(String repuestaCodigoHash) {
		this.repuestaCodigoHash = repuestaCodigoHash;
	}
	public String getRepuestaEnlaceDelPdf() {
		return repuestaEnlaceDelPdf;
	}
	public void setRepuestaEnlaceDelPdf(String repuestaEnlaceDelPdf) {
		this.repuestaEnlaceDelPdf = repuestaEnlaceDelPdf;
	}
	public String getRepuestaEnlaceDelXml() {
		return repuestaEnlaceDelXml;
	}
	public void setRepuestaEnlaceDelXml(String repuestaEnlaceDelXml) {
		this.repuestaEnlaceDelXml = repuestaEnlaceDelXml;
	}
	public String getRepuestaEnlaceDelCdr() {
		return repuestaEnlaceDelCdr;
	}
	public void setRepuestaEnlaceDelCdr(String repuestaEnlaceDelCdr) {
		this.repuestaEnlaceDelCdr = repuestaEnlaceDelCdr;
	}
	public String getRepuestaErrors() {
		return repuestaErrors;
	}
	public void setRepuestaErrors(String repuestaErrors) {
		this.repuestaErrors = repuestaErrors;
	}
	public String getRepuestaCodigoError() {
		return repuestaCodigoError;
	}
	public void setRepuestaCodigoError(String repuestaCodigoError) {
		this.repuestaCodigoError = repuestaCodigoError;
	}
	public String getDesCortDocuGene() {
		return desCortDocuGene;
	}
	public void setDesCortDocuGene(String desCortDocuGene) {
		this.desCortDocuGene = desCortDocuGene;
	}
	public Short getCodEstaDocu() {
		return codEstaDocu;
	}
	public void setCodEstaDocu(Short codEstaDocu) {
		this.codEstaDocu = codEstaDocu;
	}
	public String getMenDetaDocu() {
		return menDetaDocu;
	}
	public void setMenDetaDocu(String menDetaDocu) {
		this.menDetaDocu = menDetaDocu;
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
