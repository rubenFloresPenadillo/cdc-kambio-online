package dto;
// Generated 09/07/2018 01:05:29 AM by Hibernate Tools 5.2.10.Final

import java.util.Date;

public class TpCuentBancoDto implements java.io.Serializable {

	private static final long serialVersionUID = 9195929743615787953L;
	
	private Integer codCuenBanc;
	private TpBancoDto tpBanco;
	private TpClienDto tpClien;
	private TpDivisDto tpDivis;
	private TpTipoCuentDto tpTipoCuent;
	private String valCuenBanc;
	private String valCuenInte;
	private Integer indCuenProp;
	private String aliCuen;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;
	private String nombreTitularVista;
	private String nombreClienteCorreo;

	public TpCuentBancoDto() {
		tpBanco = new TpBancoDto();
		tpClien = new TpClienDto();
		tpDivis = new TpDivisDto();
		tpTipoCuent = new TpTipoCuentDto();
	}

	public Integer getCodCuenBanc() {
		return codCuenBanc;
	}

	public void setCodCuenBanc(Integer codCuenBanc) {
		this.codCuenBanc = codCuenBanc;
	}

	public TpBancoDto getTpBanco() {
		return tpBanco;
	}

	public void setTpBanco(TpBancoDto tpBanco) {
		this.tpBanco = tpBanco;
	}

	public TpClienDto getTpClien() {
		return tpClien;
	}

	public void setTpClien(TpClienDto tpClien) {
		this.tpClien = tpClien;
	}

	public TpDivisDto getTpDivis() {
		return tpDivis;
	}

	public void setTpDivis(TpDivisDto tpDivis) {
		this.tpDivis = tpDivis;
	}

	public TpTipoCuentDto getTpTipoCuent() {
		return tpTipoCuent;
	}

	public void setTpTipoCuent(TpTipoCuentDto tpTipoCuent) {
		this.tpTipoCuent = tpTipoCuent;
	}

	public String getValCuenBanc() {
		return valCuenBanc;
	}

	public void setValCuenBanc(String valCuenBanc) {
		this.valCuenBanc = valCuenBanc;
	}

	public String getValCuenInte() {
		return valCuenInte;
	}

	public void setValCuenInte(String valCuenInte) {
		this.valCuenInte = valCuenInte;
	}

	public Integer getIndCuenProp() {
		return indCuenProp;
	}

	public void setIndCuenProp(Integer indCuenProp) {
		this.indCuenProp = indCuenProp;
	}

	public String getAliCuen() {
		return aliCuen;
	}

	public void setAliCuen(String aliCuen) {
		this.aliCuen = aliCuen;
	}

	public Integer getIndEsta() {
		return indEsta;
	}

	public void setIndEsta(Integer indEsta) {
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

	public String getNombreTitularVista() {
		return nombreTitularVista;
	}

	public void setNombreTitularVista(String nombreTitularVista) {
		this.nombreTitularVista = nombreTitularVista;
	}

	public String getNombreClienteCorreo() {
		return nombreClienteCorreo;
	}

	public void setNombreClienteCorreo(String nombreClienteCorreo) {
		this.nombreClienteCorreo = nombreClienteCorreo;
	}

}
