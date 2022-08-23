package dto;

import java.util.Date;

public class TpTipoCuentDto implements java.io.Serializable {

	private static final long serialVersionUID = -4189172937596254735L;
	
	private Integer codTipoCuen;
	private String desTipoCuen;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpTipoCuentDto() {
	}

	public Integer getCodTipoCuen() {
		return codTipoCuen;
	}

	public void setCodTipoCuen(Integer codTipoCuen) {
		this.codTipoCuen = codTipoCuen;
	}

	public String getDesTipoCuen() {
		return desTipoCuen;
	}

	public void setDesTipoCuen(String desTipoCuen) {
		this.desTipoCuen = desTipoCuen;
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
