package dto;
// Generated 30 jul. 2021 23:58:21 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class TpAyudaPreguDto implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3788127440623804483L;
	
	private Integer codAyudPreg;
	private TpTipoPreguDto tpTipoPregu;
	private String valPreg;
	private String valResp;
	private Integer valNumePosi;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpAyudaPreguDto() {
		tpTipoPregu = new TpTipoPreguDto();
	}

	public Integer getCodAyudPreg() {
		return codAyudPreg;
	}

	public void setCodAyudPreg(Integer codAyudPreg) {
		this.codAyudPreg = codAyudPreg;
	}

	public TpTipoPreguDto getTpTipoPregu() {
		return tpTipoPregu;
	}

	public void setTpTipoPregu(TpTipoPreguDto tpTipoPregu) {
		this.tpTipoPregu = tpTipoPregu;
	}

	public String getValPreg() {
		return valPreg;
	}

	public void setValPreg(String valPreg) {
		this.valPreg = valPreg;
	}
	
	public Integer getValNumePosi() {
		return valNumePosi;
	}

	public void setValNumePosi(Integer valNumePosi) {
		this.valNumePosi = valNumePosi;
	}

	public String getValResp() {
		return valResp;
	}

	public void setValResp(String valResp) {
		this.valResp = valResp;
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
