package dto;
// Generated 29/07/2018 01:49:06 AM by Hibernate Tools 5.2.10.Final

import java.util.Date;

public class TpProviDto implements java.io.Serializable {

	private static final long serialVersionUID = -4596556485806796771L;
	
	private Integer codProv;
	private TpDeparDto tpDepar;
	private String desProv;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpProviDto() {
		tpDepar = new TpDeparDto();
	}

	public Integer getCodProv() {
		return codProv;
	}

	public void setCodProv(Integer codProv) {
		this.codProv = codProv;
	}

	public TpDeparDto getTpDepar() {
		return tpDepar;
	}

	public void setTpDepar(TpDeparDto tpDepar) {
		this.tpDepar = tpDepar;
	}

	public String getDesProv() {
		return desProv;
	}

	public void setDesProv(String desProv) {
		this.desProv = desProv;
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
