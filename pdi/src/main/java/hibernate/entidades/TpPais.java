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
 * TpPais generated by hbm2java
 */
@Entity
@Table(name = "tp_pais", schema = "public")
public class TpPais implements java.io.Serializable {

	private Integer codPais;
	private String nomPais;
	private String desGent;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpPais() {
	}

	public TpPais(Integer codPais, String nomPais, Integer indEsta, String usuApliCrea, Date fecCreaRegi) {
		this.codPais = codPais;
		this.nomPais = nomPais;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
	}

	public TpPais(Integer codPais, String nomPais, String desGent, Integer indEsta, String usuApliCrea,
			Date fecCreaRegi, String usuApliModi, Date fecModiRegi) {
		this.codPais = codPais;
		this.nomPais = nomPais;
		this.desGent = desGent;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
		this.usuApliModi = usuApliModi;
		this.fecModiRegi = fecModiRegi;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cod_pais", unique = true, nullable = false)
	public Integer getCodPais() {
		return this.codPais;
	}

	public void setCodPais(Integer codPais) {
		this.codPais = codPais;
	}

	@Column(name = "nom_pais", nullable = false, length = 50)
	public String getNomPais() {
		return this.nomPais;
	}

	public void setNomPais(String nomPais) {
		this.nomPais = nomPais;
	}

	@Column(name = "des_gent", length = 100)
	public String getDesGent() {
		return this.desGent;
	}

	public void setDesGent(String desGent) {
		this.desGent = desGent;
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
