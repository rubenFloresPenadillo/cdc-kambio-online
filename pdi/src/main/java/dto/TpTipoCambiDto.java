package dto;
// Generated 06/08/2018 01:51:00 AM by Hibernate Tools 5.2.10.Final

import java.util.Date;

public class TpTipoCambiDto implements java.io.Serializable {

	private static final long serialVersionUID = -8230391513413805025L;
	
	private Integer codTipoCamb;
	private TpDivisDto tpDivisByCodDiviCamb;
	private TpDivisDto tpDivisByCodDivi;
	private Double valCambComp;
	private Double valCambVent;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpTipoCambiDto() {
		tpDivisByCodDivi = new TpDivisDto();
		tpDivisByCodDiviCamb = new TpDivisDto();
	}

	public Integer getCodTipoCamb() {
		return codTipoCamb;
	}

	public void setCodTipoCamb(Integer codTipoCamb) {
		this.codTipoCamb = codTipoCamb;
	}

	public TpDivisDto getTpDivisByCodDiviCamb() {
		return tpDivisByCodDiviCamb;
	}

	public void setTpDivisByCodDiviCamb(TpDivisDto tpDivisByCodDiviCamb) {
		this.tpDivisByCodDiviCamb = tpDivisByCodDiviCamb;
	}

	public TpDivisDto getTpDivisByCodDivi() {
		return tpDivisByCodDivi;
	}

	public void setTpDivisByCodDivi(TpDivisDto tpDivisByCodDivi) {
		this.tpDivisByCodDivi = tpDivisByCodDivi;
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
