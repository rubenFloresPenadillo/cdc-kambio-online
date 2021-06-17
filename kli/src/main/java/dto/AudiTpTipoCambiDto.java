package dto;
// Generated 06/11/2018 12:25:49 AM by Hibernate Tools 5.2.10.Final

import java.util.Date;


public class AudiTpTipoCambiDto implements java.io.Serializable {


	private static final long serialVersionUID = 3775214735028521379L;
	
	private Integer codAudiTipoCamb;
	private Integer codTipoCamb;
	private Integer codDivi;
	private Integer codDiviCamb;
	private Double valCambComp;
	private Double valCambVent;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;
	
	public AudiTpTipoCambiDto() {
	}

	public Integer getCodAudiTipoCamb() {
		return codAudiTipoCamb;
	}

	public void setCodAudiTipoCamb(Integer codAudiTipoCamb) {
		this.codAudiTipoCamb = codAudiTipoCamb;
	}

	public Integer getCodTipoCamb() {
		return codTipoCamb;
	}

	public void setCodTipoCamb(Integer codTipoCamb) {
		this.codTipoCamb = codTipoCamb;
	}

	public Integer getCodDivi() {
		return codDivi;
	}

	public void setCodDivi(Integer codDivi) {
		this.codDivi = codDivi;
	}

	public Integer getCodDiviCamb() {
		return codDiviCamb;
	}

	public void setCodDiviCamb(Integer codDiviCamb) {
		this.codDiviCamb = codDiviCamb;
	}

	public Double getValCambComp() {
		return valCambComp;
	}

	public void setValCambComp(Double valCambComp) {
		this.valCambComp = valCambComp;
	}

	public Double getValCambVent() {
		return valCambVent;
	}

	public void setValCambVent(Double valCambVent) {
		this.valCambVent = valCambVent;
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

	
}
