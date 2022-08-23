package dto;
// Generated 31/01/2021 03:09:10 PM by Hibernate Tools 5.2.10.Final

import java.math.BigDecimal;
import java.util.Date;


public class TpReclaQuejaDto implements java.io.Serializable {

	private static final long serialVersionUID = 1949492320661934450L;
	
	private Integer codReclQuej;
	private TpTipoDocumPersoDto tpTipoDocumPerso;
	private TpTipoPersoDto tpTipoPerso;
	private String valNombPers;
	private String valApelPers;
	private String valRazoSociPers;
	private String valDocuEmpr;
	private String valDocuPers;
	private String valEmaiCont;
	private String valCeluCont;
	private String valTipoServCont;
	private String valTipoServContDesc;
	private BigDecimal valMontCamb;
	private String codUnicOperClie;
	private String valTipoReclQuej;
	private String valTipoDescReclQuej;
	private String valDescReclQuej;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;

	public TpReclaQuejaDto() {
		tpTipoDocumPerso = new TpTipoDocumPersoDto();
		tpTipoPerso = new TpTipoPersoDto();
	}

	public TpReclaQuejaDto(Integer codReclQuej, TpTipoDocumPersoDto tpTipoDocumPerso, TpTipoPersoDto tpTipoPerso,
			String valNombPers, String valDocuPers, String valEmaiCont, String valCeluCont, String valTipoServCont,
			String valTipoReclQuej, String valDescReclQuej, Integer indEsta, String usuApliCrea, Date fecCreaRegi) {
		this.codReclQuej = codReclQuej;
		this.tpTipoDocumPerso = tpTipoDocumPerso;
		this.tpTipoPerso = tpTipoPerso;
		this.valNombPers = valNombPers;
		this.valDocuPers = valDocuPers;
		this.valEmaiCont = valEmaiCont;
		this.valCeluCont = valCeluCont;
		this.valTipoServCont = valTipoServCont;
		this.valTipoReclQuej = valTipoReclQuej;
		this.valDescReclQuej = valDescReclQuej;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
	}

	public TpReclaQuejaDto(Integer codReclQuej, TpTipoDocumPersoDto tpTipoDocumPerso, TpTipoPersoDto tpTipoPerso,
			String valNombPers, String valApelPers, String valRazoSociPers, String valDocuEmpr, String valDocuPers,
			String valEmaiCont, String valCeluCont, String valTipoServCont, BigDecimal valMontCamb,
			String codUnicOperClie, String valTipoReclQuej, String valDescReclQuej, Integer indEsta, String usuApliCrea,
			Date fecCreaRegi, String usuApliModi, Date fecModiRegi) {
		this.codReclQuej = codReclQuej;
		this.tpTipoDocumPerso = tpTipoDocumPerso;
		this.tpTipoPerso = tpTipoPerso;
		this.valNombPers = valNombPers;
		this.valApelPers = valApelPers;
		this.valRazoSociPers = valRazoSociPers;
		this.valDocuEmpr = valDocuEmpr;
		this.valDocuPers = valDocuPers;
		this.valEmaiCont = valEmaiCont;
		this.valCeluCont = valCeluCont;
		this.valTipoServCont = valTipoServCont;
		this.valMontCamb = valMontCamb;
		this.codUnicOperClie = codUnicOperClie;
		this.valTipoReclQuej = valTipoReclQuej;
		this.valDescReclQuej = valDescReclQuej;
		this.indEsta = indEsta;
		this.usuApliCrea = usuApliCrea;
		this.fecCreaRegi = fecCreaRegi;
		this.usuApliModi = usuApliModi;
		this.fecModiRegi = fecModiRegi;
	}

	public Integer getCodReclQuej() {
		return codReclQuej;
	}

	public void setCodReclQuej(Integer codReclQuej) {
		this.codReclQuej = codReclQuej;
	}

	public TpTipoDocumPersoDto getTpTipoDocumPerso() {
		return tpTipoDocumPerso;
	}

	public void setTpTipoDocumPerso(TpTipoDocumPersoDto tpTipoDocumPerso) {
		this.tpTipoDocumPerso = tpTipoDocumPerso;
	}

	public TpTipoPersoDto getTpTipoPerso() {
		return tpTipoPerso;
	}

	public void setTpTipoPerso(TpTipoPersoDto tpTipoPerso) {
		this.tpTipoPerso = tpTipoPerso;
	}

	public String getValNombPers() {
		return valNombPers;
	}

	public void setValNombPers(String valNombPers) {
		this.valNombPers = valNombPers;
	}

	public String getValApelPers() {
		return valApelPers;
	}

	public void setValApelPers(String valApelPers) {
		this.valApelPers = valApelPers;
	}

	public String getValRazoSociPers() {
		return valRazoSociPers;
	}

	public void setValRazoSociPers(String valRazoSociPers) {
		this.valRazoSociPers = valRazoSociPers;
	}

	public String getValDocuEmpr() {
		return valDocuEmpr;
	}

	public void setValDocuEmpr(String valDocuEmpr) {
		this.valDocuEmpr = valDocuEmpr;
	}

	public String getValDocuPers() {
		return valDocuPers;
	}

	public void setValDocuPers(String valDocuPers) {
		this.valDocuPers = valDocuPers;
	}

	public String getValEmaiCont() {
		return valEmaiCont;
	}

	public void setValEmaiCont(String valEmaiCont) {
		this.valEmaiCont = valEmaiCont;
	}

	public String getValCeluCont() {
		return valCeluCont;
	}

	public void setValCeluCont(String valCeluCont) {
		this.valCeluCont = valCeluCont;
	}

	public String getValTipoServCont() {
		return valTipoServCont;
	}

	public void setValTipoServCont(String valTipoServCont) {
		this.valTipoServCont = valTipoServCont;
	}

	public BigDecimal getValMontCamb() {
		return valMontCamb;
	}

	public void setValMontCamb(BigDecimal valMontCamb) {
		this.valMontCamb = valMontCamb;
	}

	public String getCodUnicOperClie() {
		return codUnicOperClie;
	}

	public void setCodUnicOperClie(String codUnicOperClie) {
		this.codUnicOperClie = codUnicOperClie;
	}

	public String getValTipoReclQuej() {
		return valTipoReclQuej;
	}

	public void setValTipoReclQuej(String valTipoReclQuej) {
		this.valTipoReclQuej = valTipoReclQuej;
	}

	public String getValTipoDescReclQuej() {
		return valTipoDescReclQuej;
	}

	public void setValTipoDescReclQuej(String valTipoDescReclQuej) {
		this.valTipoDescReclQuej = valTipoDescReclQuej;
	}

	public String getValDescReclQuej() {
		return valDescReclQuej;
	}

	public void setValDescReclQuej(String valDescReclQuej) {
		this.valDescReclQuej = valDescReclQuej;
	}

	public Integer getIndEsta() {
		return indEsta;
	}

	public void setIndEsta(Integer indEsta) {
		this.indEsta = indEsta;
	}

	public String getUsuApliCrea() {
		return usuApliCrea;
	}

	public void setUsuApliCrea(String usuApliCrea) {
		this.usuApliCrea = usuApliCrea;
	}

	public Date getFecCreaRegi() {
		return fecCreaRegi;
	}

	public void setFecCreaRegi(Date fecCreaRegi) {
		this.fecCreaRegi = fecCreaRegi;
	}

	public String getUsuApliModi() {
		return usuApliModi;
	}

	public void setUsuApliModi(String usuApliModi) {
		this.usuApliModi = usuApliModi;
	}

	public Date getFecModiRegi() {
		return fecModiRegi;
	}

	public void setFecModiRegi(Date fecModiRegi) {
		this.fecModiRegi = fecModiRegi;
	}

	public String getValTipoServContDesc() {
		return valTipoServContDesc;
	}

	public void setValTipoServContDesc(String valTipoServContDesc) {
		this.valTipoServContDesc = valTipoServContDesc;
	}

	
	

}
