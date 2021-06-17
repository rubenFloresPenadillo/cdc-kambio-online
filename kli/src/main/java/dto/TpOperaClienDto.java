package dto;

import java.math.BigDecimal;
import java.util.Date;

import hibernate.entidades.TpOrigeFondo;

public class TpOperaClienDto implements java.io.Serializable {

	private static final long serialVersionUID = 6339639927481055428L;
	
	private Integer codOperClie;
	private TpBancoDto tpBanco;
	private TpClienDto tpClien;
	private TpCuentBancoDto tpCuentBancoByCodCuenBancClieReci;
	private TpCuentBancoDto tpCuentBancoByCodCuenBancClieEnvi;
	private TpDivisDto tpDivisByCodDiviEnvi;
	private TpDivisDto tpDivisByCodDiviReci;
	private TpEstadOperaDto tpEstadOpera;
	private TpTipoCambiDto tpTipoCambi;
	private TpOrigeFondoDto tpOrigeFondo;
	private String codUnicOperClie;
	private BigDecimal monEnvi;
	private BigDecimal monReci;
	private Integer indCompVent;
	private Double valTipoCambUsad;
	private String codTranBanc;
	private Date fecInicOper;
	private Date fecVeriOper;
	private Date fecFinaOper;
	private String usuApliFinaOper;
	private Date fecCancOper;
	private String usuApliCancOper;
	private String valTextComeCanc;
	private Integer indEsta;
	private String usuApliCrea;
	private Date fecCreaRegi;
	private String usuApliModi;
	private Date fecModiRegi;
	
	public TpOperaClienDto() {
		tpBanco = new TpBancoDto();
		tpClien = new TpClienDto();
		tpCuentBancoByCodCuenBancClieReci = new TpCuentBancoDto();
		tpCuentBancoByCodCuenBancClieEnvi = new TpCuentBancoDto();
		tpDivisByCodDiviReci = new TpDivisDto();
		tpDivisByCodDiviEnvi = new TpDivisDto();
		tpEstadOpera = new TpEstadOperaDto();
		tpTipoCambi = new TpTipoCambiDto();
		tpOrigeFondo = new TpOrigeFondoDto();
	}
	
	public Integer getCodOperClie() {
		return codOperClie;
	}
	public void setCodOperClie(Integer codOperClie) {
		this.codOperClie = codOperClie;
	}
	public TpBancoDto getTpBanco() {
		return tpBanco;
	}
	public void setTpBanco(TpBancoDto tpBanco) {
		this.tpBanco = tpBanco;
	}

	
	
	public TpClienDto getTpClien() {
		return tpClien;
	}

	public void setTpClien(TpClienDto tpClien) {
		this.tpClien = tpClien;
	}

	public TpCuentBancoDto getTpCuentBancoByCodCuenBancClieReci() {
		return tpCuentBancoByCodCuenBancClieReci;
	}
	public void setTpCuentBancoByCodCuenBancClieReci(TpCuentBancoDto tpCuentBancoByCodCuenBancClieReci) {
		this.tpCuentBancoByCodCuenBancClieReci = tpCuentBancoByCodCuenBancClieReci;
	}
	public TpCuentBancoDto getTpCuentBancoByCodCuenBancClieEnvi() {
		return tpCuentBancoByCodCuenBancClieEnvi;
	}
	public void setTpCuentBancoByCodCuenBancClieEnvi(TpCuentBancoDto tpCuentBancoByCodCuenBancClieEnvi) {
		this.tpCuentBancoByCodCuenBancClieEnvi = tpCuentBancoByCodCuenBancClieEnvi;
	}
	public TpDivisDto getTpDivisByCodDiviEnvi() {
		return tpDivisByCodDiviEnvi;
	}
	public void setTpDivisByCodDiviEnvi(TpDivisDto tpDivisByCodDiviEnvi) {
		this.tpDivisByCodDiviEnvi = tpDivisByCodDiviEnvi;
	}
	public TpDivisDto getTpDivisByCodDiviReci() {
		return tpDivisByCodDiviReci;
	}
	public void setTpDivisByCodDiviReci(TpDivisDto tpDivisByCodDiviReci) {
		this.tpDivisByCodDiviReci = tpDivisByCodDiviReci;
	}
	public TpEstadOperaDto getTpEstadOpera() {
		return tpEstadOpera;
	}
	public void setTpEstadOpera(TpEstadOperaDto tpEstadOpera) {
		this.tpEstadOpera = tpEstadOpera;
	}
	public TpTipoCambiDto getTpTipoCambi() {
		return tpTipoCambi;
	}
	public void setTpTipoCambi(TpTipoCambiDto tpTipoCambi) {
		this.tpTipoCambi = tpTipoCambi;
	}
	public String getCodUnicOperClie() {
		return codUnicOperClie;
	}
	public void setCodUnicOperClie(String codUnicOperClie) {
		this.codUnicOperClie = codUnicOperClie;
	}
	public BigDecimal getMonEnvi() {
		return monEnvi;
	}
	public void setMonEnvi(BigDecimal monEnvi) {
		this.monEnvi = monEnvi;
	}
	public BigDecimal getMonReci() {
		return monReci;
	}
	public void setMonReci(BigDecimal monReci) {
		this.monReci = monReci;
	}
	public Integer getIndCompVent() {
		return indCompVent;
	}
	public void setIndCompVent(Integer indCompVent) {
		this.indCompVent = indCompVent;
	}
	public Double getValTipoCambUsad() {
		return valTipoCambUsad;
	}
	public void setValTipoCambUsad(Double valTipoCambUsad) {
		this.valTipoCambUsad = valTipoCambUsad;
	}
	public String getCodTranBanc() {
		return codTranBanc;
	}
	public void setCodTranBanc(String codTranBanc) {
		this.codTranBanc = codTranBanc;
	}
	public Date getFecInicOper() {
		return fecInicOper;
	}
	public void setFecInicOper(Date fecInicOper) {
		this.fecInicOper = fecInicOper;
	}
	public Date getFecVeriOper() {
		return fecVeriOper;
	}
	public void setFecVeriOper(Date fecVeriOper) {
		this.fecVeriOper = fecVeriOper;
	}
	public Date getFecFinaOper() {
		return fecFinaOper;
	}
	public void setFecFinaOper(Date fecFinaOper) {
		this.fecFinaOper = fecFinaOper;
	}
	public String getUsuApliFinaOper() {
		return usuApliFinaOper;
	}
	public void setUsuApliFinaOper(String usuApliFinaOper) {
		this.usuApliFinaOper = usuApliFinaOper;
	}
	public Date getFecCancOper() {
		return fecCancOper;
	}
	public void setFecCancOper(Date fecCancOper) {
		this.fecCancOper = fecCancOper;
	}
	public String getUsuApliCancOper() {
		return usuApliCancOper;
	}
	public void setUsuApliCancOper(String usuApliCancOper) {
		this.usuApliCancOper = usuApliCancOper;
	}
	public String getValTextComeCanc() {
		return valTextComeCanc;
	}
	public void setValTextComeCanc(String valTextComeCanc) {
		this.valTextComeCanc = valTextComeCanc;
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

	public TpOrigeFondoDto getTpOrigeFondo() {
		return tpOrigeFondo;
	}

	public void setTpOrigeFondo(TpOrigeFondoDto tpOrigeFondo) {
		this.tpOrigeFondo = tpOrigeFondo;
	}
	
	
}
