package dto;
// Generated 12/08/2019 07:06:15 PM by Hibernate Tools 5.2.10.Final

import java.util.Date;


public class TpSuscrDto implements java.io.Serializable {

	private static final long serialVersionUID = -8233154287795346327L;
	
	private Integer codSusc;
	private String emaSusc;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpSuscrDto() {
	}

	public Integer getCodSusc() {
		return codSusc;
	}

	public void setCodSusc(Integer codSusc) {
		this.codSusc = codSusc;
	}

	public String getEmaSusc() {
		return emaSusc;
	}

	public void setEmaSusc(String emaSusc) {
		this.emaSusc = emaSusc;
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
