package hibernate.entidades;
// Generated 08/05/2021 03:53:47 AM by Hibernate Tools 5.2.10.Final

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
 * TpSectoEcono generated by hbm2java
 */
@Entity
@Table(name = "tp_secto_econo", schema = "public")
public class TpSectoEcono implements java.io.Serializable {

	private Integer codSectEcon;
	private String desSectEcon;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpSectoEcono() {
	}

	public TpSectoEcono(Integer codSectEcon, String desSectEcon, Integer indEsta, String usuApliCrea,
			Date fecCreaRegi) {
		this.codSectEcon = codSectEcon;
		this.desSectEcon = desSectEcon;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
	}

	public TpSectoEcono(Integer codSectEcon, String desSectEcon, Integer indEsta, String usuApliCrea, Date fecCreaRegi,
			String usuApliModi, Date fecModiRegi) {
		this.codSectEcon = codSectEcon;
		this.desSectEcon = desSectEcon;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
		this.usuApliModi = usuApliModi;
		this.fecModiRegi = fecModiRegi;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cod_sect_econ", unique = true, nullable = false)
	public Integer getCodSectEcon() {
		return this.codSectEcon;
	}

	public void setCodSectEcon(Integer codSectEcon) {
		this.codSectEcon = codSectEcon;
	}

	@Column(name = "des_sect_econ", nullable = false, length = 100)
	public String getDesSectEcon() {
		return this.desSectEcon;
	}

	public void setDesSectEcon(String desSectEcon) {
		this.desSectEcon = desSectEcon;
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