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
 * TpCuentBanco generated by hbm2java
 */
@Entity
@Table(name = "tp_cuent_banco", schema = "public")
public class TpCuentBanco implements java.io.Serializable {

	private Integer codCuenBanc;
	private TpBanco tpBanco;
	private TpClien tpClien;
	private TpDivis tpDivis;
	private TpTipoCuent tpTipoCuent;
	private String valCuenBanc;
	private String valCuenInte;
	private Integer indCuenProp;
	private String aliCuen;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpCuentBanco() {
		tpBanco = new TpBanco();
		tpClien = new TpClien();
		tpDivis = new TpDivis();
		tpTipoCuent = new TpTipoCuent();
	}

	public TpCuentBanco(Integer codCuenBanc, TpBanco tpBanco, TpClien tpClien, TpDivis tpDivis, TpTipoCuent tpTipoCuent,
			String valCuenBanc, Integer indCuenProp, Integer indEsta, String usuApliCrea, Date fecCreaRegi) {
		this.codCuenBanc = codCuenBanc;
		this.tpBanco = tpBanco;
		this.tpClien = tpClien;
		this.tpDivis = tpDivis;
		this.tpTipoCuent = tpTipoCuent;
		this.valCuenBanc = valCuenBanc;
		this.indCuenProp = indCuenProp;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
	}

	public TpCuentBanco(Integer codCuenBanc, TpBanco tpBanco, TpClien tpClien, TpDivis tpDivis, TpTipoCuent tpTipoCuent,
			String valCuenBanc, String valCuenInte, Integer indCuenProp, String aliCuen, Integer indEsta,
			String usuApliCrea, Date fecCreaRegi, String usuApliModi, Date fecModiRegi) {
		this.codCuenBanc = codCuenBanc;
		this.tpBanco = tpBanco;
		this.tpClien = tpClien;
		this.tpDivis = tpDivis;
		this.tpTipoCuent = tpTipoCuent;
		this.valCuenBanc = valCuenBanc;
		this.valCuenInte = valCuenInte;
		this.indCuenProp = indCuenProp;
		this.aliCuen = aliCuen;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
		this.usuApliModi = usuApliModi;
		this.fecModiRegi = fecModiRegi;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cod_cuen_banc", unique = true, nullable = false)
	public Integer getCodCuenBanc() {
		return this.codCuenBanc;
	}

	public void setCodCuenBanc(Integer codCuenBanc) {
		this.codCuenBanc = codCuenBanc;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_banc", nullable = false)
	public TpBanco getTpBanco() {
		return this.tpBanco;
	}

	public void setTpBanco(TpBanco tpBanco) {
		this.tpBanco = tpBanco;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_clie", nullable = false)
	public TpClien getTpClien() {
		return this.tpClien;
	}

	public void setTpClien(TpClien tpClien) {
		this.tpClien = tpClien;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_divi", nullable = false)
	public TpDivis getTpDivis() {
		return this.tpDivis;
	}

	public void setTpDivis(TpDivis tpDivis) {
		this.tpDivis = tpDivis;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_tipo_cuen", nullable = false)
	public TpTipoCuent getTpTipoCuent() {
		return this.tpTipoCuent;
	}

	public void setTpTipoCuent(TpTipoCuent tpTipoCuent) {
		this.tpTipoCuent = tpTipoCuent;
	}

	@Column(name = "val_cuen_banc", nullable = false, length = 50)
	public String getValCuenBanc() {
		return this.valCuenBanc;
	}

	public void setValCuenBanc(String valCuenBanc) {
		this.valCuenBanc = valCuenBanc;
	}

	@Column(name = "val_cuen_inte", length = 50)
	public String getValCuenInte() {
		return this.valCuenInte;
	}

	public void setValCuenInte(String valCuenInte) {
		this.valCuenInte = valCuenInte;
	}

	@Column(name = "ind_cuen_prop", nullable = false)
	public Integer getIndCuenProp() {
		return this.indCuenProp;
	}

	public void setIndCuenProp(Integer indCuenProp) {
		this.indCuenProp = indCuenProp;
	}

	@Column(name = "ali_cuen", length = 50)
	public String getAliCuen() {
		return this.aliCuen;
	}

	public void setAliCuen(String aliCuen) {
		this.aliCuen = aliCuen;
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
