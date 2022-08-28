package dto;
// Generated 09/07/2018 01:05:29 AM by Hibernate Tools 5.2.10.Final

import java.util.Date;

public class TpTipoDocumPersoDto implements java.io.Serializable {

	private static final long serialVersionUID = 3408274346482506030L;
	
	private Integer codTipoDocuPers;
	private TpTipoPersoDto tpTipoPerso;
	private String nomTipoDocuPerso;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpTipoDocumPersoDto() {
		tpTipoPerso = new TpTipoPersoDto();
	}

	public Integer getCodTipoDocuPers() {
		return codTipoDocuPers;
	}

	public void setCodTipoDocuPers(Integer codTipoDocuPers) {
		this.codTipoDocuPers = codTipoDocuPers;
	}

	public TpTipoPersoDto getTpTipoPerso() {
		return tpTipoPerso;
	}

	public void setTpTipoPerso(TpTipoPersoDto tpTipoPerso) {
		this.tpTipoPerso = tpTipoPerso;
	}

	public String getNomTipoDocuPerso() {
		return nomTipoDocuPerso;
	}

	public void setNomTipoDocuPerso(String nomTipoDocuPerso) {
		this.nomTipoDocuPerso = nomTipoDocuPerso;
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
