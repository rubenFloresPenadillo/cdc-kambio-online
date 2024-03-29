package hibernate.entidades;
// Generated 24 jul. 2022 13:20:26 by Hibernate Tools 5.2.12.Final

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

/**
 * TpOperaCupon generated by hbm2java
 */
@Entity
@Table(name = "tp_opera_cupon", schema = "public")
public class TpOperaCupon implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codOperCupo;
	private TpCupon tpCupon;
	private TpOperaClien tpOperaClien;
	private String nomCupoUsad;
	private Double monDescCupoUsad;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpOperaCupon() {
		tpCupon = new TpCupon();
		tpOperaClien = new TpOperaClien();
	}

	public TpOperaCupon(Integer codOperCupo, TpCupon tpCupon, TpOperaClien tpOperaClien, String nomCupoUsad,
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

	public TpOperaCupon(Integer codOperCupo, TpCupon tpCupon, TpOperaClien tpOperaClien, String nomCupoUsad,
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

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cod_oper_cupo", unique = true, nullable = false)
	public Integer getCodOperCupo() {
		return this.codOperCupo;
	}

	public void setCodOperCupo(Integer codOperCupo) {
		this.codOperCupo = codOperCupo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_cupo", nullable = false)
	public TpCupon getTpCupon() {
		return this.tpCupon;
	}

	public void setTpCupon(TpCupon tpCupon) {
		this.tpCupon = tpCupon;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_oper_clie", nullable = false)
	public TpOperaClien getTpOperaClien() {
		return this.tpOperaClien;
	}

	public void setTpOperaClien(TpOperaClien tpOperaClien) {
		this.tpOperaClien = tpOperaClien;
	}

	@Column(name = "nom_cupo_usad", nullable = false, length = 50)
	public String getNomCupoUsad() {
		return this.nomCupoUsad;
	}

	public void setNomCupoUsad(String nomCupoUsad) {
		this.nomCupoUsad = nomCupoUsad;
	}

	@Column(name = "mon_desc_cupo_usad", nullable = false, precision = 17, scale = 17)
	public Double getMonDescCupoUsad() {
		return this.monDescCupoUsad;
	}

	public void setMonDescCupoUsad(Double monDescCupoUsad) {
		this.monDescCupoUsad = monDescCupoUsad;
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
