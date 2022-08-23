package dto;
// Generated 01/04/2021 05:17:35 PM by Hibernate Tools 5.2.10.Final

import java.util.Date;


public class TpOrigeFondoDto implements java.io.Serializable {

	static final long serialVersionUID = -4036963449462611063L;
	
	private Integer codOrigFond;
	private String desOrigFond;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpOrigeFondoDto() {
	}

	public Integer getCodOrigFond() {
		return codOrigFond;
	}

	public void setCodOrigFond(Integer codOrigFond) {
		this.codOrigFond = codOrigFond;
	}

	public String getDesOrigFond() {
		return desOrigFond;
	}

	public void setDesOrigFond(String desOrigFond) {
		this.desOrigFond = desOrigFond;
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
