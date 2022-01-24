package hibernate.entidades;
// Generated 08/05/2021 04:15:22 AM by Hibernate Tools 5.2.10.Final

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
 * TpActivEcono generated by hbm2java
 */
@Entity
@Table(name = "tp_activ_econo", schema = "public")
public class TpActivEcono implements java.io.Serializable {

	private static final long serialVersionUID = -3321591347195245711L;
	private Integer codActiEcon;
	private TpSectoEcono tpSectoEcono;
	private String desActiEcon;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpActivEcono() {
	}

	public TpActivEcono(Integer codActiEcon, TpSectoEcono tpSectoEcono, String desActiEcon, Integer indEsta,
			String usuApliCrea, Date fecCreaRegi) {
		this.codActiEcon = codActiEcon;
		this.tpSectoEcono = tpSectoEcono;
		this.desActiEcon = desActiEcon;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
	}

	public TpActivEcono(Integer codActiEcon, TpSectoEcono tpSectoEcono, String desActiEcon, Integer indEsta,
			String usuApliCrea, Date fecCreaRegi, String usuApliModi, Date fecModiRegi) {
		this.codActiEcon = codActiEcon;
		this.tpSectoEcono = tpSectoEcono;
		this.desActiEcon = desActiEcon;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
		this.usuApliModi = usuApliModi;
		this.fecModiRegi = fecModiRegi;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cod_acti_econ", unique = true, nullable = false)
	public Integer getCodActiEcon() {
		return this.codActiEcon;
	}

	public void setCodActiEcon(Integer codActiEcon) {
		this.codActiEcon = codActiEcon;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_sect_econ", nullable = false)
	public TpSectoEcono getTpSectoEcono() {
		return this.tpSectoEcono;
	}

	public void setTpSectoEcono(TpSectoEcono tpSectoEcono) {
		this.tpSectoEcono = tpSectoEcono;
	}

	@Column(name = "des_acti_econ", nullable = false, length = 100)
	public String getDesActiEcon() {
		return this.desActiEcon;
	}

	public void setDesActiEcon(String desActiEcon) {
		this.desActiEcon = desActiEcon;
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
