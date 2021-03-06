package hibernate.entidades;
// Generated 12/09/2019 12:32:03 AM by Hibernate Tools 5.2.10.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TpEntra generated by hbm2java
 */
@Entity
@Table(name = "tp_entra", schema = "public")
public class TpEntra implements java.io.Serializable {

	private Integer codEntr;
	private String titEntr;
	private String imaEntrCont;
	private String imaEntrPrev;
	private String nomImaCont;
	private String nomImaPrev;
	private String conEntr;
	private String catEntr;
	private String enlEntr;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpEntra() {
	}

	public TpEntra(Integer codEntr, String titEntr, String imaEntrCont, String imaEntrPrev, String nomImaCont,
			String nomImaPrev, String conEntr, String catEntr, String enlEntr, Integer indEsta, String usuApliCrea,
			Date fecCreaRegi) {
		this.codEntr = codEntr;
		this.titEntr = titEntr;
		this.imaEntrCont = imaEntrCont;
		this.imaEntrPrev = imaEntrPrev;
		this.nomImaCont = nomImaCont;
		this.nomImaPrev = nomImaPrev;
		this.conEntr = conEntr;
		this.catEntr = catEntr;
		this.enlEntr = enlEntr;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
	}

	public TpEntra(Integer codEntr, String titEntr, String imaEntrCont, String imaEntrPrev, String nomImaCont,
			String nomImaPrev, String conEntr, String catEntr, String enlEntr, Integer indEsta, String usuApliCrea,
			Date fecCreaRegi, String usuApliModi, Date fecModiRegi) {
		this.codEntr = codEntr;
		this.titEntr = titEntr;
		this.imaEntrCont = imaEntrCont;
		this.imaEntrPrev = imaEntrPrev;
		this.nomImaCont = nomImaCont;
		this.nomImaPrev = nomImaPrev;
		this.conEntr = conEntr;
		this.catEntr = catEntr;
		this.enlEntr = enlEntr;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
		this.usuApliModi = usuApliModi;
		this.fecModiRegi = fecModiRegi;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cod_entr", unique = true, nullable = false)
	public Integer getCodEntr() {
		return this.codEntr;
	}

	public void setCodEntr(Integer codEntr) {
		this.codEntr = codEntr;
	}

	@Column(name = "tit_entr", nullable = false)
	public String getTitEntr() {
		return this.titEntr;
	}

	public void setTitEntr(String titEntr) {
		this.titEntr = titEntr;
	}

	@Column(name = "ima_entr_cont", nullable = false)
	public String getImaEntrCont() {
		return this.imaEntrCont;
	}

	public void setImaEntrCont(String imaEntrCont) {
		this.imaEntrCont = imaEntrCont;
	}

	@Column(name = "ima_entr_prev", nullable = false)
	public String getImaEntrPrev() {
		return this.imaEntrPrev;
	}

	public void setImaEntrPrev(String imaEntrPrev) {
		this.imaEntrPrev = imaEntrPrev;
	}

	@Column(name = "nom_ima_cont", nullable = false, length = 100)
	public String getNomImaCont() {
		return this.nomImaCont;
	}

	public void setNomImaCont(String nomImaCont) {
		this.nomImaCont = nomImaCont;
	}

	@Column(name = "nom_ima_prev", nullable = false, length = 100)
	public String getNomImaPrev() {
		return this.nomImaPrev;
	}

	public void setNomImaPrev(String nomImaPrev) {
		this.nomImaPrev = nomImaPrev;
	}

	@Column(name = "con_entr", nullable = false)
	public String getConEntr() {
		return this.conEntr;
	}

	public void setConEntr(String conEntr) {
		this.conEntr = conEntr;
	}

	@Column(name = "cat_entr", nullable = false, length = 50)
	public String getCatEntr() {
		return this.catEntr;
	}

	public void setCatEntr(String catEntr) {
		this.catEntr = catEntr;
	}

	@Column(name = "enl_entr", nullable = false, length = 200)
	public String getEnlEntr() {
		return this.enlEntr;
	}

	public void setEnlEntr(String enlEntr) {
		this.enlEntr = enlEntr;
	}

	@Column(name = "ind_esta", nullable = false)
	public Integer getIndEsta() {
		return this.indEsta;
	}

	public void setIndEsta(Integer indEsta) {
		this.indEsta = indEsta;
	}

	@Column(name = "usu_apli_crea", nullable = false, length = 100)
	public String getUsuApliCrea() {
		return this.usuApliCrea;
	}

	public void setUsuApliCrea(String usuApliCrea) {
		this.usuApliCrea = usuApliCrea;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fec_crea_regi", nullable = false, length = 29)
	public Date getFecCreaRegi() {
		return this.fecCreaRegi;
	}

	public void setFecCreaRegi(Date fecCreaRegi) {
		this.fecCreaRegi = fecCreaRegi;
	}

	@Column(name = "usu_apli_modi", length = 100)
	public String getUsuApliModi() {
		return this.usuApliModi;
	}

	public void setUsuApliModi(String usuApliModi) {
		this.usuApliModi = usuApliModi;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fec_modi_regi", length = 29)
	public Date getFecModiRegi() {
		return this.fecModiRegi;
	}

	public void setFecModiRegi(Date fecModiRegi) {
		this.fecModiRegi = fecModiRegi;
	}

}
