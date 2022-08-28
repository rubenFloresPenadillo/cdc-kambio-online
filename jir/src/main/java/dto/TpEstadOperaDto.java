package dto;


import java.util.Date;

public class TpEstadOperaDto implements java.io.Serializable {

	private static final long serialVersionUID = 4710218247203064829L;
	
	private Integer codEstaOper;
	private String desEstaOper;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;
	
	public Integer getCodEstaOper() {
		return codEstaOper;
	}
	public void setCodEstaOper(Integer codEstaOper) {
		this.codEstaOper = codEstaOper;
	}
	public String getDesEstaOper() {
		return desEstaOper;
	}
	public void setDesEstaOper(String desEstaOper) {
		this.desEstaOper = desEstaOper;
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
