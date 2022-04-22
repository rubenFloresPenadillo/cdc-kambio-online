package hibernate.entidades;
// Generated 30 jul. 2021 23:58:21 by Hibernate Tools 5.2.12.Final

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
 * TpAyudaPregu generated by hbm2java
 */
@Entity
@Table(name = "tp_ayuda_pregu", schema = "public")
public class TpAyudaPregu implements java.io.Serializable {

	private Integer codAyudPreg;
	private TpTipoPregu tpTipoPregu;
	private String valPreg;
	private String valResp;
	private Integer valNumePosi;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpAyudaPregu() {
		tpTipoPregu = new TpTipoPregu();
	}

	public TpAyudaPregu(Integer codAyudPreg, TpTipoPregu tpTipoPregu, String valPreg, String valResp, Integer valNumePosi, Integer indEsta,
			String usuApliCrea, Date fecCreaRegi) {
		this.codAyudPreg = codAyudPreg;
		this.tpTipoPregu = tpTipoPregu;
		this.valPreg = valPreg;
		this.valResp = valResp;
		this.valNumePosi = valNumePosi;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
	}

	public TpAyudaPregu(Integer codAyudPreg, TpTipoPregu tpTipoPregu, String valPreg, String valResp,  Integer valNumePosi, Integer indEsta,
			String usuApliCrea, Date fecCreaRegi, String usuApliModi, Date fecModiRegi) {
		this.codAyudPreg = codAyudPreg;
		this.tpTipoPregu = tpTipoPregu;
		this.valPreg = valPreg;
		this.valResp = valResp;
		this.valNumePosi = valNumePosi;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
		this.usuApliModi = usuApliModi;
		this.fecModiRegi = fecModiRegi;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cod_ayud_preg", unique = true, nullable = false)
	public Integer getCodAyudPreg() {
		return this.codAyudPreg;
	}

	public void setCodAyudPreg(Integer codAyudPreg) {
		this.codAyudPreg = codAyudPreg;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_tipo_preg", nullable = false)
	public TpTipoPregu getTpTipoPregu() {
		return this.tpTipoPregu;
	}

	public void setTpTipoPregu(TpTipoPregu tpTipoPregu) {
		this.tpTipoPregu = tpTipoPregu;
	}

	@Column(name = "val_preg", nullable = false, length = 100)
	public String getValPreg() {
		return this.valPreg;
	}

	public void setValPreg(String valPreg) {
		this.valPreg = valPreg;
	}

	@Column(name = "val_resp", nullable = false, length = 4000)
	public String getValResp() {
		return this.valResp;
	}

	public void setValResp(String valResp) {
		this.valResp = valResp;
	}

	@Column(name = "val_nume_posi", nullable = false)
	public Integer getValNumePosi() {
		return valNumePosi;
	}

	public void setValNumePosi(Integer valNumePosi) {
		this.valNumePosi = valNumePosi;
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
