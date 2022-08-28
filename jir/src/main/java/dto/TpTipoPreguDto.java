package dto;
// Generated 30 jul. 2021 23:58:21 by Hibernate Tools 5.2.12.Final

import java.util.Date;


public class TpTipoPreguDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5510240449343141095L;
	
	private Integer codTipoPreg;
	private String desTipoPreg;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpTipoPreguDto() {
	}

	public Integer getCodTipoPreg() {
		return codTipoPreg;
	}

	public void setCodTipoPreg(Integer codTipoPreg) {
		this.codTipoPreg = codTipoPreg;
	}

	public String getDesTipoPreg() {
		return desTipoPreg;
	}

	public void setDesTipoPreg(String desTipoPreg) {
		this.desTipoPreg = desTipoPreg;
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
