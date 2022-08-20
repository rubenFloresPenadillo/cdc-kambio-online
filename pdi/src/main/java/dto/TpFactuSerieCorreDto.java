package dto;
// Generated 14 ago. 2022 14:52:21 by Hibernate Tools 5.2.12.Final

import java.util.Date;


public class TpFactuSerieCorreDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codSeriCorr;
	private TpFactuTipoDocumElectDto tpFactuTipoDocumElect;
	private String valSeri;
	private Integer valCorrInic;
	private Integer valCorrUsadUlti;
	private short indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpFactuSerieCorreDto() {
		tpFactuTipoDocumElect = new TpFactuTipoDocumElectDto();
	}

	public Integer getCodSeriCorr() {
		return codSeriCorr;
	}

	public void setCodSeriCorr(Integer codSeriCorr) {
		this.codSeriCorr = codSeriCorr;
	}

	public TpFactuTipoDocumElectDto getTpFactuTipoDocumElect() {
		return tpFactuTipoDocumElect;
	}

	public void setTpFactuTipoDocumElect(TpFactuTipoDocumElectDto tpFactuTipoDocumElect) {
		this.tpFactuTipoDocumElect = tpFactuTipoDocumElect;
	}

	public String getValSeri() {
		return valSeri;
	}

	public void setValSeri(String valSeri) {
		this.valSeri = valSeri;
	}

	public Integer getValCorrInic() {
		return valCorrInic;
	}

	public void setValCorrInic(Integer valCorrInic) {
		this.valCorrInic = valCorrInic;
	}

	public Integer getValCorrUsadUlti() {
		return valCorrUsadUlti;
	}

	public void setValCorrUsadUlti(Integer valCorrUsadUlti) {
		this.valCorrUsadUlti = valCorrUsadUlti;
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
