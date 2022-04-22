package dto;
// Generated 09/07/2018 01:05:29 AM by Hibernate Tools 5.2.10.Final

import java.util.Date;


public class TpClienDto implements java.io.Serializable {

	private static final long serialVersionUID = -493296108199798719L;
	
	private Integer codClie;
	private TpDeparDto tpDepar;
	private TpDistrDto tpDistr;
	private TpPaisDto tpPaisByCodPaisNaci;
	private TpPaisDto tpPaisByCodPaisResi;
	private TpProviDto tpProvi;
	private TpTipoDocumPersoDto tpTipoDocumPerso;
	private TpActivEconoDto tpActivEcono;
	private TpUsuarDto tpUsuar;
	private String valDocuPers;
	private String valPrimNombPers;
	private String valSeguNombPers;
	private String valPrimApelPers;
	private String valSeguApelPers;
	private String valRazoSociPers;
	private String valNombreCompleto;
	private String valDocuEmpr;
	private Date fecNaci;
	private String valTelePers;
	private String valDirePers;
	private String valCiud;
	private Integer indPepo;
	private String valPepoInst;
	private String valPepoRol;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;
	private String valNombPerf;
	private Integer codCliePadr;
	
	public TpClienDto() {
//		tpDepar = new TpDeparDto();
//		tpDistr = new TpDistrDto();
		tpPaisByCodPaisNaci = new TpPaisDto();
		tpPaisByCodPaisResi = new TpPaisDto();
//		tpProvi = new TpProviDto();
		tpTipoDocumPerso = new TpTipoDocumPersoDto();
		tpUsuar = new TpUsuarDto();
		tpActivEcono = new TpActivEconoDto();
	}

	public Integer getCodClie() {
		return codClie;
	}

	public void setCodClie(Integer codClie) {
		this.codClie = codClie;
	}

	public TpDeparDto getTpDepar() {
		return tpDepar;
	}

	public void setTpDepar(TpDeparDto tpDepar) {
		this.tpDepar = tpDepar;
	}

	public TpDistrDto getTpDistr() {
		return tpDistr;
	}

	public void setTpDistr(TpDistrDto tpDistr) {
		this.tpDistr = tpDistr;
	}

	public TpPaisDto getTpPaisByCodPaisNaci() {
		return tpPaisByCodPaisNaci;
	}

	public void setTpPaisByCodPaisNaci(TpPaisDto tpPaisByCodPaisNaci) {
		this.tpPaisByCodPaisNaci = tpPaisByCodPaisNaci;
	}

	public TpPaisDto getTpPaisByCodPaisResi() {
		return tpPaisByCodPaisResi;
	}

	public void setTpPaisByCodPaisResi(TpPaisDto tpPaisByCodPaisResi) {
		this.tpPaisByCodPaisResi = tpPaisByCodPaisResi;
	}

	public TpProviDto getTpProvi() {
		return tpProvi;
	}

	public void setTpProvi(TpProviDto tpProvi) {
		this.tpProvi = tpProvi;
	}

	public TpTipoDocumPersoDto getTpTipoDocumPerso() {
		return tpTipoDocumPerso;
	}

	public void setTpTipoDocumPerso(TpTipoDocumPersoDto tpTipoDocumPerso) {
		this.tpTipoDocumPerso = tpTipoDocumPerso;
	}

	public TpUsuarDto getTpUsuar() {
		return tpUsuar;
	}

	public void setTpUsuar(TpUsuarDto tpUsuar) {
		this.tpUsuar = tpUsuar;
	}

	public String getValDocuPers() {
		return valDocuPers;
	}

	public void setValDocuPers(String valDocuPers) {
		this.valDocuPers = valDocuPers;
	}

	public String getValPrimNombPers() {
		return valPrimNombPers;
	}

	public void setValPrimNombPers(String valPrimNombPers) {
		this.valPrimNombPers = valPrimNombPers;
	}

	public String getValSeguNombPers() {
		return valSeguNombPers;
	}

	public void setValSeguNombPers(String valSeguNombPers) {
		this.valSeguNombPers = valSeguNombPers;
	}

	public String getValPrimApelPers() {
		return valPrimApelPers;
	}

	public void setValPrimApelPers(String valPrimApelPers) {
		this.valPrimApelPers = valPrimApelPers;
	}

	public String getValSeguApelPers() {
		return valSeguApelPers;
	}

	public void setValSeguApelPers(String valSeguApelPers) {
		this.valSeguApelPers = valSeguApelPers;
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

	public Date getFecNaci() {
		return fecNaci;
	}

	public void setFecNaci(Date fecNaci) {
		this.fecNaci = fecNaci;
	}

	public String getValTelePers() {
		return valTelePers;
	}

	public void setValTelePers(String valTelePers) {
		this.valTelePers = valTelePers;
	}

	public String getValDirePers() {
		return valDirePers;
	}

	public void setValDirePers(String valDirePers) {
		this.valDirePers = valDirePers;
	}

	public String getValCiud() {
		return valCiud;
	}

	public void setValCiud(String valCiud) {
		this.valCiud = valCiud;
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

	public String getValNombreCompleto() {
		return valNombreCompleto;
	}

	public void setValNombreCompleto(String valNombreCompleto) {
		this.valNombreCompleto = valNombreCompleto;
	}

	public Integer getIndPepo() {
		return indPepo;
	}

	public void setIndPepo(Integer indPepo) {
		this.indPepo = indPepo;
	}

	public String getValPepoInst() {
		return valPepoInst;
	}

	public void setValPepoInst(String valPepoInst) {
		this.valPepoInst = valPepoInst;
	}

	public String getValPepoRol() {
		return valPepoRol;
	}

	public void setValPepoRol(String valPepoRol) {
		this.valPepoRol = valPepoRol;
	}

	public String getValNombPerf() {
		return valNombPerf;
	}

	public void setValNombPerf(String valNombPerf) {
		this.valNombPerf = valNombPerf;
	}

	public TpActivEconoDto getTpActivEcono() {
		return tpActivEcono;
	}

	public void setTpActivEcono(TpActivEconoDto tpActivEcono) {
		this.tpActivEcono = tpActivEcono;
	}

	public Integer getCodCliePadr() {
		return codCliePadr;
	}

	public void setCodCliePadr(Integer codCliePadr) {
		this.codCliePadr = codCliePadr;
	}

	
}
