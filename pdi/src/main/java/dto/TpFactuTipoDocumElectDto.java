package dto;
// Generated 14 ago. 2022 14:52:21 by Hibernate Tools 5.2.12.Final

import java.util.Date;


public class TpFactuTipoDocumElectDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codTipoDocuElec;
	private String desCortTipoDocuElec;
	private String desTipoDocuElec;
	private Integer codTipoCompExte;
	private short indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpFactuTipoDocumElectDto() {
	}

	public Integer getCodTipoDocuElec() {
		return codTipoDocuElec;
	}

	public void setCodTipoDocuElec(Integer codTipoDocuElec) {
		this.codTipoDocuElec = codTipoDocuElec;
	}

	public String getDesCortTipoDocuElec() {
		return desCortTipoDocuElec;
	}

	public void setDesCortTipoDocuElec(String desCortTipoDocuElec) {
		this.desCortTipoDocuElec = desCortTipoDocuElec;
	}

	public String getDesTipoDocuElec() {
		return desTipoDocuElec;
	}

	public void setDesTipoDocuElec(String desTipoDocuElec) {
		this.desTipoDocuElec = desTipoDocuElec;
	}

	public Integer getCodTipoCompExte() {
		return codTipoCompExte;
	}

	public void setCodTipoCompExte(Integer codTipoCompExte) {
		this.codTipoCompExte = codTipoCompExte;
	}

	public short getIndEsta() {
		return indEsta;
	}

	public void setIndEsta(short indEsta) {
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
