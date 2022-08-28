package dto;

import java.util.Date;

public class TpUsuarDto implements java.io.Serializable {

	private static final long serialVersionUID = -5321353534569609827L;
	
	private Integer codUsua;
	private TpTipoPersoDto tpTipoPerso;
	private String ideUsuaEmai;
	private String emaUsuaAuxi;
	private String codClav;
	private String codClavConf;
	private String valNomb;
	private Integer indCompDato;
	private Integer codClie;
	private Integer codEstaOper;
	private Integer codOperClie;
	private Integer codPerfUsua;
	private String desPerfUsua;
	private Date fecUltiModiClav;
	private Date fecUltiAcce;
	private Integer codEstaCuenUsua;
	private String desEstaCuenUsua;
	private String valTokeCuen;
	private Date fecCreaToke;
	private String valTokeRestCuen;
	private Date fecCreaTokeRestCuen;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;
	private String valNombRegi;
	private Integer codUsuaPadr;

	public TpUsuarDto() {
		tpTipoPerso = new TpTipoPersoDto();
	}

	public Integer getCodUsua() {
		return codUsua;
	}

	public void setCodUsua(Integer codUsua) {
		this.codUsua = codUsua;
	}

	public TpTipoPersoDto getTpTipoPerso() {
		return tpTipoPerso;
	}

	public void setTpTipoPerso(TpTipoPersoDto tpTipoPerso) {
		this.tpTipoPerso = tpTipoPerso;
	}

	public String getIdeUsuaEmai() {
		return ideUsuaEmai;
	}

	public void setIdeUsuaEmai(String ideUsuaEmai) {
		this.ideUsuaEmai = ideUsuaEmai;
	}

	public String getEmaUsuaAuxi() {
		return emaUsuaAuxi;
	}

	public void setEmaUsuaAuxi(String emaUsuaAuxi) {
		this.emaUsuaAuxi = emaUsuaAuxi;
	}

	public String getCodClav() {
		return codClav;
	}

	public void setCodClav(String codClav) {
		this.codClav = codClav;
	}

	public String getCodClavConf() {
		return codClavConf;
	}

	public void setCodClavConf(String codClavConf) {
		this.codClavConf = codClavConf;
	}

	public String getValNomb() {
		return valNomb;
	}

	public void setValNomb(String valNomb) {
		this.valNomb = valNomb;
	}

	public Integer getIndCompDato() {
		return indCompDato;
	}

	public void setIndCompDato(Integer indCompDato) {
		this.indCompDato = indCompDato;
	}

	public Integer getCodClie() {
		return codClie;
	}

	public void setCodClie(Integer codClie) {
		this.codClie = codClie;
	}

	public Integer getCodEstaOper() {
		return codEstaOper;
	}

	public void setCodEstaOper(Integer codEstaOper) {
		this.codEstaOper = codEstaOper;
	}

	public Integer getCodOperClie() {
		return codOperClie;
	}

	public void setCodOperClie(Integer codOperClie) {
		this.codOperClie = codOperClie;
	}

	public Integer getCodPerfUsua() {
		return codPerfUsua;
	}

	public void setCodPerfUsua(Integer codPerfUsua) {
		this.codPerfUsua = codPerfUsua;
	}
	
	public String getDesPerfUsua() {
		return desPerfUsua;
	}

	public void setDesPerfUsua(String desPerfUsua) {
		this.desPerfUsua = desPerfUsua;
	}

	public Date getFecUltiModiClav() {
		return fecUltiModiClav;
	}

	public void setFecUltiModiClav(Date fecUltiModiClav) {
		this.fecUltiModiClav = fecUltiModiClav;
	}

	public Date getFecUltiAcce() {
		return fecUltiAcce;
	}

	public void setFecUltiAcce(Date fecUltiAcce) {
		this.fecUltiAcce = fecUltiAcce;
	}

	public Integer getCodEstaCuenUsua() {
		return codEstaCuenUsua;
	}

	public void setCodEstaCuenUsua(Integer codEstaCuenUsua) {
		this.codEstaCuenUsua = codEstaCuenUsua;
	}

	public String getDesEstaCuenUsua() {
		return desEstaCuenUsua;
	}

	public void setDesEstaCuenUsua(String desEstaCuenUsua) {
		this.desEstaCuenUsua = desEstaCuenUsua;
	}

	public String getValTokeCuen() {
		return valTokeCuen;
	}

	public void setValTokeCuen(String valTokeCuen) {
		this.valTokeCuen = valTokeCuen;
	}

	public Date getFecCreaToke() {
		return fecCreaToke;
	}

	public void setFecCreaToke(Date fecCreaToke) {
		this.fecCreaToke = fecCreaToke;
	}

	public String getValTokeRestCuen() {
		return valTokeRestCuen;
	}

	public void setValTokeRestCuen(String valTokeRestCuen) {
		this.valTokeRestCuen = valTokeRestCuen;
	}

	public Date getFecCreaTokeRestCuen() {
		return fecCreaTokeRestCuen;
	}

	public void setFecCreaTokeRestCuen(Date fecCreaTokeRestCuen) {
		this.fecCreaTokeRestCuen = fecCreaTokeRestCuen;
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

	public String getValNombRegi() {
		return valNombRegi;
	}

	public void setValNombRegi(String valNombRegi) {
		this.valNombRegi = valNombRegi;
	}

	public Integer getCodUsuaPadr() {
		return codUsuaPadr;
	}

	public void setCodUsuaPadr(Integer codUsuaPadr) {
		this.codUsuaPadr = codUsuaPadr;
	}
	
}
