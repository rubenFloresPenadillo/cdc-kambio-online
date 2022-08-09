package dto;
// Generated 8 jun. 2022 23:29:22 by Hibernate Tools 5.2.12.Final

import java.util.Date;


public class TpCuponDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codCupo;
	private Integer codTipoPersApli;
	private Integer codTipoOperApli;
	private String emaClieCupo;
	private String nomCupo;
	private Date fecInicVige;
	private Date fecFinaVige;
	private Integer canCupo;
	private Double monDescCupo;
	private Integer indReutClie;
	private Date fecVigeFilt;
	private Integer indFiltCantCupo;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpCuponDto() {
	}

	public Integer getCodCupo() {
		return codCupo;
	}

	public void setCodCupo(Integer codCupo) {
		this.codCupo = codCupo;
	}

	public Integer getCodTipoPersApli() {
		return codTipoPersApli;
	}

	public void setCodTipoPersApli(Integer codTipoPersApli) {
		this.codTipoPersApli = codTipoPersApli;
	}

	public Integer getCodTipoOperApli() {
		return codTipoOperApli;
	}

	public void setCodTipoOperApli(Integer codTipoOperApli) {
		this.codTipoOperApli = codTipoOperApli;
	}

	public String getEmaClieCupo() {
		return emaClieCupo;
	}

	public void setEmaClieCupo(String emaClieCupo) {
		this.emaClieCupo = emaClieCupo;
	}

	public String getNomCupo() {
		return nomCupo;
	}

	public void setNomCupo(String nomCupo) {
		this.nomCupo = nomCupo;
	}

	public Date getFecInicVige() {
		return fecInicVige;
	}

	public void setFecInicVige(Date fecInicVige) {
		this.fecInicVige = fecInicVige;
	}

	public Date getFecFinaVige() {
		return fecFinaVige;
	}

	public void setFecFinaVige(Date fecFinaVige) {
		this.fecFinaVige = fecFinaVige;
	}

	public Integer getCanCupo() {
		return canCupo;
	}

	public void setCanCupo(Integer canCupo) {
		this.canCupo = canCupo;
	}

	public Double getMonDescCupo() {
		return monDescCupo;
	}

	public void setMonDescCupo(Double monDescCupo) {
		this.monDescCupo = monDescCupo;
	}

	public Integer getIndReutClie() {
		return indReutClie;
	}

	public void setIndReutClie(Integer indReutClie) {
		this.indReutClie = indReutClie;
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

	public Date getFecVigeFilt() {
		return fecVigeFilt;
	}

	public void setFecVigeFilt(Date fecVigeFilt) {
		this.fecVigeFilt = fecVigeFilt;
	}

	public Integer getIndFiltCantCupo() {
		return indFiltCantCupo;
	}

	public void setIndFiltCantCupo(Integer indFiltCantCupo) {
		this.indFiltCantCupo = indFiltCantCupo;
	}

}
