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
 * TpTipoDocumPerso generated by hbm2java
 */
@Entity
@Table(name = "tp_tipo_docum_perso", schema = "public")
public class TpTipoDocumPerso implements java.io.Serializable {

	private Integer codTipoDocuPers;
	private TpTipoPerso tpTipoPerso;
	private String nomTipoDocuPers;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpTipoDocumPerso() {
		tpTipoPerso = new TpTipoPerso();
	}

	public TpTipoDocumPerso(Integer codTipoDocuPers, TpTipoPerso tpTipoPerso, Integer indEsta, String usuApliCrea,
			Date fecCreaRegi) {
		this.codTipoDocuPers = codTipoDocuPers;
		this.tpTipoPerso = tpTipoPerso;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
	}

	public TpTipoDocumPerso(Integer codTipoDocuPers, TpTipoPerso tpTipoPerso, String nomTipoDocuPers, Integer indEsta,
			String usuApliCrea, Date fecCreaRegi, String usuApliModi, Date fecModiRegi) {
		this.codTipoDocuPers = codTipoDocuPers;
		this.tpTipoPerso = tpTipoPerso;
		this.nomTipoDocuPers = nomTipoDocuPers;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
		this.usuApliModi = usuApliModi;
		this.fecModiRegi = fecModiRegi;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cod_tipo_docu_pers", unique = true, nullable = false)
	public Integer getCodTipoDocuPers() {
		return this.codTipoDocuPers;
	}

	public void setCodTipoDocuPers(Integer codTipoDocuPers) {
		this.codTipoDocuPers = codTipoDocuPers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_tipo_pers", nullable = false)
	public TpTipoPerso getTpTipoPerso() {
		return this.tpTipoPerso;
	}

	public void setTpTipoPerso(TpTipoPerso tpTipoPerso) {
		this.tpTipoPerso = tpTipoPerso;
	}

	@Column(name = "nom_tipo_docu_pers", length = 50)
	public String getNomTipoDocuPers() {
		return this.nomTipoDocuPers;
	}

	public void setNomTipoDocuPers(String nomTipoDocuPers) {
		this.nomTipoDocuPers = nomTipoDocuPers;
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
