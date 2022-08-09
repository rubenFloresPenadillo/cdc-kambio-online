package hibernate.entidades;
// Generated 12/09/2019 12:32:03 AM by Hibernate Tools 5.2.10.Final

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
 * TpDistr generated by hbm2java
 */
@Entity
@Table(name = "tp_distr", schema = "public")
public class TpDistr implements java.io.Serializable {

	private Integer codDist;
	private TpProvi tpProvi;
	private String desDist;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpDistr() {
	}

	public TpDistr(Integer codDist, TpProvi tpProvi, String desDist, Integer indEsta, String usuApliCrea,
			Date fecCreaRegi) {
		this.codDist = codDist;
		this.tpProvi = tpProvi;
		this.desDist = desDist;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
	}

	public TpDistr(Integer codDist, TpProvi tpProvi, String desDist, Integer indEsta, String usuApliCrea,
			Date fecCreaRegi, String usuApliModi, Date fecModiRegi) {
		this.codDist = codDist;
		this.tpProvi = tpProvi;
		this.desDist = desDist;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
		this.usuApliModi = usuApliModi;
		this.fecModiRegi = fecModiRegi;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cod_dist", unique = true, nullable = false)
	public Integer getCodDist() {
		return this.codDist;
	}

	public void setCodDist(Integer codDist) {
		this.codDist = codDist;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_prov", nullable = false)
	public TpProvi getTpProvi() {
		return this.tpProvi;
	}

	public void setTpProvi(TpProvi tpProvi) {
		this.tpProvi = tpProvi;
	}

	@Column(name = "des_dist", nullable = false, length = 50)
	public String getDesDist() {
		return this.desDist;
	}

	public void setDesDist(String desDist) {
		this.desDist = desDist;
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
