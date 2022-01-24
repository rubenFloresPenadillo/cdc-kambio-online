package dto;
// Generated 03/07/2018 11:02:47 PM by Hibernate Tools 5.2.10.Final

import java.util.Date;

public class TpTipoPersoDto implements java.io.Serializable {

	private static final long serialVersionUID = -937319068474767133L;
	
	private Integer codTipoPers;
	private String desTipoPers;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public Integer getCodTipoPers() {
		return codTipoPers;
	}
	
	public void setCodTipoPers(Integer codTipoPers) {
		this.codTipoPers = codTipoPers;
	}
	
	public String getDesTipoPers() {
		return desTipoPers;
	}
	
	public void setDesTipoPers(String desTipoPers) {
		this.desTipoPers = desTipoPers;
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
