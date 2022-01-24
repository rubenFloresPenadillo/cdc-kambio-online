package dto;
// Generated 29/07/2018 01:49:06 AM by Hibernate Tools 5.2.10.Final

import java.util.Date;

public class TpDeparDto implements java.io.Serializable {

	private static final long serialVersionUID = 1468242001035331045L;
	
	private Integer codDepa;
	private String desDepa;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpDeparDto() {
	}

	public Integer getCodDepa() {
		return codDepa;
	}

	public void setCodDepa(Integer codDepa) {
		this.codDepa = codDepa;
	}

	public String getDesDepa() {
		return desDepa;
	}

	public void setDesDepa(String desDepa) {
		this.desDepa = desDepa;
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
