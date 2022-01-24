package dto;
// Generated 29/07/2018 01:49:06 AM by Hibernate Tools 5.2.10.Final

import java.util.Date;

public class TpDistrDto implements java.io.Serializable {

	private static final long serialVersionUID = 6461076145843872808L;
	
	private Integer codDist;
	private TpProviDto tpProvi;
	private String desDist;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpDistrDto() {
		tpProvi = new TpProviDto();
	}

	public Integer getCodDist() {
		return codDist;
	}

	public void setCodDist(Integer codDist) {
		this.codDist = codDist;
	}

	public TpProviDto getTpProvi() {
		return tpProvi;
	}

	public void setTpProvi(TpProviDto tpProvi) {
		this.tpProvi = tpProvi;
	}

	public String getDesDist() {
		return desDist;
	}

	public void setDesDist(String desDist) {
		this.desDist = desDist;
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
