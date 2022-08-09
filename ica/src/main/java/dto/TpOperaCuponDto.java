package dto;
// Generated 24 jul. 2022 13:20:26 by Hibernate Tools 5.2.12.Final

import java.util.Date;


public class TpOperaCuponDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codOperCupo;
	private TpCuponDto tpCupon;
	private TpOperaClienDto tpOperaClien;
	private String nomCupoUsad;
	private Double monDescCupoUsad;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpOperaCuponDto() {
		tpCupon = new TpCuponDto();
		tpOperaClien = new TpOperaClienDto();
	}

	public TpOperaCuponDto(Integer codOperCupo, TpCuponDto tpCupon, TpOperaClienDto tpOperaClien, String nomCupoUsad,
			Double monDescCupoUsad, Integer indEsta, String usuApliCrea, Date fecCreaRegi) {
		this.codOperCupo = codOperCupo;
		this.tpCupon = tpCupon;
		this.tpOperaClien = tpOperaClien;
		this.nomCupoUsad = nomCupoUsad;
		this.monDescCupoUsad = monDescCupoUsad;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
	}

	public TpOperaCuponDto(Integer codOperCupo, TpCuponDto tpCupon, TpOperaClienDto tpOperaClien, String nomCupoUsad,
			Double monDescCupoUsad, Integer indEsta, String usuApliCrea, Date fecCreaRegi, String usuApliModi,
			Date fecModiRegi) {
		this.codOperCupo = codOperCupo;
		this.tpCupon = tpCupon;
		this.tpOperaClien = tpOperaClien;
		this.nomCupoUsad = nomCupoUsad;
		this.monDescCupoUsad = monDescCupoUsad;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
		this.usuApliModi = usuApliModi;
		this.fecModiRegi = fecModiRegi;
	}

	public Integer getCodOperCupo() {
		return codOperCupo;
	}

	public void setCodOperCupo(Integer codOperCupo) {
		this.codOperCupo = codOperCupo;
	}

	public TpCuponDto getTpCupon() {
		return tpCupon;
	}

	public void setTpCupon(TpCuponDto tpCupon) {
		this.tpCupon = tpCupon;
	}

	public TpOperaClienDto getTpOperaClien() {
		return tpOperaClien;
	}

	public void setTpOperaClien(TpOperaClienDto tpOperaClien) {
		this.tpOperaClien = tpOperaClien;
	}

	public String getNomCupoUsad() {
		return nomCupoUsad;
	}

	public void setNomCupoUsad(String nomCupoUsad) {
		this.nomCupoUsad = nomCupoUsad;
	}

	public Double getMonDescCupoUsad() {
		return monDescCupoUsad;
	}

	public void setMonDescCupoUsad(Double monDescCupoUsad) {
		this.monDescCupoUsad = monDescCupoUsad;
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
