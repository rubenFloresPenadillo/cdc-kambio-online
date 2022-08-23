package dto;
// Generated 09/07/2018 01:05:29 AM by Hibernate Tools 5.2.10.Final

import java.util.Date;

public class TpBancoDto implements java.io.Serializable {

	private static final long serialVersionUID = -6370158006240331895L;
	
	private Integer codBanc;
	private String codCortBanc;
	private String nomBanc;
	private Integer indVistClie;
	private Integer indVistAdmi;
	private Integer indEsta;
	private Integer indTienCuenNego;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpBancoDto() {
	}

	public Integer getCodBanc() {
		return codBanc;
	}

	public void setCodBanc(Integer codBanc) {
		this.codBanc = codBanc;
	}

	public String getCodCortBanc() {
		return codCortBanc;
	}

	public void setCodCortBanc(String codCortBanc) {
		this.codCortBanc = codCortBanc;
	}

	public String getNomBanc() {
		return nomBanc;
	}

	public void setNomBanc(String nomBanc) {
		this.nomBanc = nomBanc;
	}

	public Integer getIndVistClie() {
		return indVistClie;
	}

	public void setIndVistClie(Integer indVistClie) {
		this.indVistClie = indVistClie;
	}

	public Integer getIndVistAdmi() {
		return indVistAdmi;
	}

	public void setIndVistAdmi(Integer indVistAdmi) {
		this.indVistAdmi = indVistAdmi;
	}

	public Integer getIndEsta() {
		return indEsta;
	}

	public void setIndEsta(Integer indEsta) {
		this.indEsta = indEsta;
	}

	public Integer getIndTienCuenNego() {
		return indTienCuenNego;
	}

	public void setIndTienCuenNego(Integer indTienCuenNego) {
		this.indTienCuenNego = indTienCuenNego;
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
