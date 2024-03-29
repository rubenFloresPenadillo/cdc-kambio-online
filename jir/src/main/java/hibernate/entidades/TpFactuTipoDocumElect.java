package hibernate.entidades;
// Generated 14 ago. 2022 14:52:21 by Hibernate Tools 5.2.12.Final

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
 * TpFactuTipoDocumElect generated by hbm2java
 */
@Entity
@Table(name = "tp_factu_tipo_docum_elect", schema = "public")
public class TpFactuTipoDocumElect implements java.io.Serializable {

	private Integer codTipoDocuElec;
	private String desCortTipoDocuElec;
	private String desTipoDocuElec;
	private Integer codTipoCompExte;
	private short indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpFactuTipoDocumElect() {
	}

	public TpFactuTipoDocumElect(Integer codTipoDocuElec, String desCortTipoDocuElec, String desTipoDocuElec,
			Integer codTipoCompExte, short indEsta, String usuApliCrea, Date fecCreaRegi) {
		this.codTipoDocuElec = codTipoDocuElec;
		this.desCortTipoDocuElec = desCortTipoDocuElec;
		this.desTipoDocuElec = desTipoDocuElec;
		this.codTipoCompExte = codTipoCompExte;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
	}

	public TpFactuTipoDocumElect(Integer codTipoDocuElec, String desCortTipoDocuElec, String desTipoDocuElec,
			Integer codTipoCompExte, short indEsta, String usuApliCrea, Date fecCreaRegi, String usuApliModi,
			Date fecModiRegi) {
		this.codTipoDocuElec = codTipoDocuElec;
		this.desCortTipoDocuElec = desCortTipoDocuElec;
		this.desTipoDocuElec = desTipoDocuElec;
		this.codTipoCompExte = codTipoCompExte;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
		this.usuApliModi = usuApliModi;
		this.fecModiRegi = fecModiRegi;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cod_tipo_docu_elec", unique = true, nullable = false)
	public Integer getCodTipoDocuElec() {
		return this.codTipoDocuElec;
	}

	public void setCodTipoDocuElec(Integer codTipoDocuElec) {
		this.codTipoDocuElec = codTipoDocuElec;
	}

	@Column(name = "des_cort_tipo_docu_elec", nullable = false, length = 10)
	public String getDesCortTipoDocuElec() {
		return this.desCortTipoDocuElec;
	}

	public void setDesCortTipoDocuElec(String desCortTipoDocuElec) {
		this.desCortTipoDocuElec = desCortTipoDocuElec;
	}

	@Column(name = "des_tipo_docu_elec", nullable = false, length = 100)
	public String getDesTipoDocuElec() {
		return this.desTipoDocuElec;
	}

	public void setDesTipoDocuElec(String desTipoDocuElec) {
		this.desTipoDocuElec = desTipoDocuElec;
	}

	@Column(name = "cod_tipo_comp_exte", nullable = false)
	public Integer getCodTipoCompExte() {
		return this.codTipoCompExte;
	}

	public void setCodTipoCompExte(Integer codTipoCompExte) {
		this.codTipoCompExte = codTipoCompExte;
	}

	@Column(name = "ind_esta", nullable = false)
	public short getIndEsta() {
		return this.indEsta;
	}

	public void setIndEsta(short indEsta) {
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
