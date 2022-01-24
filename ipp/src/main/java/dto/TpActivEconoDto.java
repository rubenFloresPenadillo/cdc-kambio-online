package dto;
// Generated 08/05/2021 03:53:47 AM by Hibernate Tools 5.2.10.Final

import java.util.Date;


public class TpActivEconoDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1995780498421316867L;
	
	private Integer codActiEcon;
	private TpSectoEconoDto tpSectoEcono;
	private String desActiEcon;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;
	

	public TpActivEconoDto() {
		tpSectoEcono = new TpSectoEconoDto();
	}
	
	public Integer getCodActiEcon() {
		return codActiEcon;
	}
	public void setCodActiEcon(Integer codActiEcon) {
		this.codActiEcon = codActiEcon;
	}
	public TpSectoEconoDto getTpSectoEcono() {
		return tpSectoEcono;
	}
	public void setTpSectoEcono(TpSectoEconoDto tpSectoEcono) {
		this.tpSectoEcono = tpSectoEcono;
	}
	public String getDesActiEcon() {
		return desActiEcon;
	}
	public void setDesActiEcon(String desActiEcon) {
		this.desActiEcon = desActiEcon;
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

