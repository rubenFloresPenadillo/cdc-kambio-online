package service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import cadenas.util.ValidacionesString;
import dto.TpOperaClienDto;
import hibernate.entidades.TpOperaClien;
import hibernate.util.HibernateUtil;
import model.dao.DaoOperacionCliente;
import model.dao.impl.DaoOperacionClienteImpl;
import numeros.util.ValidacionesNumeros;
import service.ServiceOperacionCliente;
import util.types.CadenasType;
import util.types.ElementosTablasType;
import util.types.NumerosType;

public class ServiceOperacionClienteImpl  implements ServiceOperacionCliente{

    private DaoOperacionCliente daoOperacionCliente;
    private Session session;

    public ServiceOperacionClienteImpl() {
        session =  HibernateUtil.getSessionFactory().openSession();

        daoOperacionCliente = new DaoOperacionClienteImpl(session);
    }

	@Override
	public void closeSesion() {
		daoOperacionCliente.closeSesion();
	}

	@Override
	public String insertUpdate(TpOperaClienDto tpOperaClienDto) {
		TpOperaClien tpOperaClienEntidad = new TpOperaClien();
        
        if (tpOperaClienDto.getCodOperClie()  != null) {
        	tpOperaClienEntidad.setCodOperClie(tpOperaClienDto.getCodOperClie());
        }
        
        tpOperaClienEntidad.setCodUnicOperClie(tpOperaClienDto.getCodUnicOperClie());
        tpOperaClienEntidad.getTpClien().setCodClie(tpOperaClienDto.getTpClien().getCodClie());
        tpOperaClienEntidad.getTpClien().getTpUsuar().setCodUsua(tpOperaClienDto.getTpClien().getTpUsuar().getCodUsua());
        tpOperaClienEntidad.getTpCuentBancoByCodCuenBancClieOrig().getTpBanco().setCodBanc(tpOperaClienDto.getTpCuentBancoByCodCuenBancClieOrig().getTpBanco().getCodBanc());
        tpOperaClienEntidad.getTpCuentBancoByCodCuenBancClieOrig().setCodCuenBanc(tpOperaClienDto.getTpCuentBancoByCodCuenBancClieOrig().getCodCuenBanc());
        tpOperaClienEntidad.getTpCuentBancoByCodCuenBancClieReci().setCodCuenBanc(tpOperaClienDto.getTpCuentBancoByCodCuenBancClieReci().getCodCuenBanc());
        tpOperaClienEntidad.getTpCuentBancoByCodCuenBancCome().setCodCuenBanc(tpOperaClienDto.getTpCuentBancoByCodCuenBancCome().getCodCuenBanc());
        tpOperaClienEntidad.getTpDivisByCodDiviEnvi().setCodDivi(tpOperaClienDto.getTpDivisByCodDiviEnvi().getCodDivi());
        tpOperaClienEntidad.getTpDivisByCodDiviReci().setCodDivi(tpOperaClienDto.getTpDivisByCodDiviReci().getCodDivi());
        tpOperaClienEntidad.setMonEnvi(tpOperaClienDto.getMonEnvi());
        tpOperaClienEntidad.setMonReci(tpOperaClienDto.getMonReci());
        tpOperaClienEntidad.getTpTipoCambi().setCodTipoCamb(tpOperaClienDto.getTpTipoCambi().getCodTipoCamb());
        tpOperaClienEntidad.setIndCompVent(tpOperaClienDto.getIndCompVent());
        tpOperaClienEntidad.setValTipoCambUsad(tpOperaClienDto.getValTipoCambUsad());
        tpOperaClienEntidad.setCodTranBanc(tpOperaClienDto.getCodTranBanc());
        tpOperaClienEntidad.getTpEstadOpera().setCodEstaOper(tpOperaClienDto.getTpEstadOpera().getCodEstaOper());
        tpOperaClienEntidad.getTpOrigeFondo().setCodOrigFond(tpOperaClienDto.getTpOrigeFondo().getCodOrigFond());
        tpOperaClienEntidad.setIndEsta(tpOperaClienDto.getIndEsta());
        tpOperaClienEntidad.setUsuApliCrea(tpOperaClienDto.getUsuApliCrea());
        tpOperaClienEntidad.setFecCreaRegi(tpOperaClienDto.getFecCreaRegi());
        tpOperaClienEntidad.setFecInicOper(tpOperaClienDto.getFecInicOper());
        tpOperaClienEntidad.setCodCupoUsad(tpOperaClienDto.getCodCupoUsad());
        tpOperaClienEntidad.setMonDescCupoUsad(tpOperaClienDto.getMonDescCupoUsad());
        tpOperaClienEntidad.setNomCupoUsad(tpOperaClienDto.getNomCupoUsad());
        tpOperaClienEntidad.setValCambCompCupo(tpOperaClienDto.getValCambCompCupo());
        tpOperaClienEntidad.setValCambVentCupo(tpOperaClienDto.getValCambVentCupo());
        
        String result = daoOperacionCliente.insertUpdate(tpOperaClienEntidad);

        return result;
	}

	//Actualmente solo sirve para cancelar o finalizar una operacion
	@Override
	public String actualizarEstadoOperacionCliente(TpOperaClienDto tpOperaClienDto) {
		
		TpOperaClien tpOperaClienEntidad = new TpOperaClien();
		
		tpOperaClienEntidad.setCodOperClie(tpOperaClienDto.getCodOperClie());
		tpOperaClienEntidad.getTpEstadOpera().setCodEstaOper(tpOperaClienDto.getTpEstadOpera().getCodEstaOper());
		tpOperaClienEntidad.getTpClien().getTpUsuar().setCodUsua(tpOperaClienDto.getTpClien().getTpUsuar().getCodUsua());
		tpOperaClienEntidad.setUsuApliModi(tpOperaClienDto.getUsuApliModi());
		tpOperaClienEntidad.setFecModiRegi(tpOperaClienDto.getFecModiRegi());
		tpOperaClienEntidad.setCodUnicOperClie(tpOperaClienDto.getCodUnicOperClie());
		tpOperaClienEntidad.setCodCupoUsad(tpOperaClienDto.getCodCupoUsad());
		tpOperaClienEntidad.setNomCupoUsad(tpOperaClienDto.getNomCupoUsad());
		tpOperaClienEntidad.setMonDescCupoUsad(tpOperaClienDto.getMonDescCupoUsad());
		tpOperaClienEntidad.setCodTranBanc(tpOperaClienDto.getCodTranBanc());
		tpOperaClienEntidad.setNumOperBancCome(tpOperaClienDto.getNumOperBancCome());
		
		if (tpOperaClienDto.getFecVeriOper() != null) {
			tpOperaClienEntidad.setFecVeriOper(tpOperaClienDto.getFecVeriOper());
		}
		
		if (tpOperaClienDto.getUsuApliFinaOper() != null && tpOperaClienDto.getFecFinaOper() != null) {
			tpOperaClienEntidad.setUsuApliFinaOper(tpOperaClienDto.getUsuApliFinaOper());
			tpOperaClienEntidad.setFecFinaOper(tpOperaClienDto.getFecFinaOper());
		}
		
		if(tpOperaClienDto.getUsuApliCancOper() != null && tpOperaClienDto.getFecCancOper() != null) {
			tpOperaClienEntidad.setUsuApliCancOper(tpOperaClienDto.getUsuApliCancOper());
			tpOperaClienEntidad.setFecCancOper(tpOperaClienDto.getFecCancOper());
			tpOperaClienEntidad.setValTextComeCanc(tpOperaClienDto.getValTextComeCanc());
		}
		
		String result = daoOperacionCliente.actualizarEstadoOperacionCliente(tpOperaClienEntidad);
        return result;
	}

	@Override
	public TpOperaClienDto getOperacionCliente(TpOperaClienDto tpOperaClienDto) {
		
		TpOperaClien entidad = new TpOperaClien();
		TpOperaClienDto resultado = null;

		if(!ValidacionesNumeros.esCeroONuloEntero(tpOperaClienDto.getCodOperClie())) {
			entidad.setCodOperClie(tpOperaClienDto.getCodOperClie());
		}
		
		Object[] temporal = daoOperacionCliente.getOperacionCliente(entidad);

		if (temporal != null) {
			resultado = new TpOperaClienDto();
			resultado.setCodOperClie((Integer) temporal[0]);
			resultado.getTpEstadOpera().setCodEstaOper((Integer) temporal[1]);
			resultado.setIndCompVent((Integer) temporal[2]);
			resultado.setValTipoCambUsad((Double) temporal[3]);
			resultado.setMonEnvi((BigDecimal) temporal[4]);
			resultado.setMonReci((BigDecimal) temporal[5]);
//			resultado.getTpBanco().setCodBanc((Integer) temporal[6]);
			resultado.getTpDivisByCodDiviEnvi().setCodDivi((Integer) temporal[6]);
			resultado.getTpCuentBancoByCodCuenBancClieReci().setCodCuenBanc((Integer) temporal[7]);
			resultado.getTpClien().setValPrimNombPers((String) temporal[8]);
			resultado.setCodCupoUsad((Integer) temporal[9]); 
			resultado.setNomCupoUsad((String) temporal[10]);  
			resultado.setMonDescCupoUsad((Double) temporal[11]);
			resultado.setValCambCompCupo((Double) temporal[12]);
			resultado.setValCambVentCupo((Double) temporal[13]);
			resultado.setCodTranBanc((String) temporal[14]);
			resultado.getTpCuentBancoByCodCuenBancClieOrig().getTpBanco().setCodBanc((Integer) temporal[15]);
			resultado.getTpCuentBancoByCodCuenBancClieOrig().setCodCuenBanc((Integer) temporal[16]);
		}

		return resultado;
	}

	@Override
	public List<TpOperaClienDto> getOperacionesCliente(TpOperaClienDto tpOperaClienDto) {

		List<TpOperaClienDto> resultado = new LinkedList<TpOperaClienDto>() ;
		TpOperaClienDto parametroOperacion = new TpOperaClienDto();
		
		parametroOperacion.getTpClien().setCodClie(tpOperaClienDto.getTpClien().getCodClie());
		parametroOperacion.setCodUnicOperClie(tpOperaClienDto.getCodUnicOperClie());
		parametroOperacion.setFecFiltroDesde(tpOperaClienDto.getFecFiltroDesde());
		parametroOperacion.setFecFiltroHasta(tpOperaClienDto.getFecFiltroHasta());

		List<Object[]> listaTemporal = daoOperacionCliente.getOperacionesCliente(parametroOperacion);
		
		for(Object[] temp : listaTemporal) {
			TpOperaClienDto dto = new TpOperaClienDto();
			dto.setCodOperClie((Integer) temp[0]);
			dto.setCodUnicOperClie((String) temp[1]);
			dto.getTpClien().getTpUsuar().setCodUsua((Integer) temp[2]);
			dto.setFecCreaRegi((Date) temp[3]);
			dto.setMonEnvi((BigDecimal) temp[4]);
			dto.setMonReci((BigDecimal) temp[5]);
			dto.setValTipoCambUsad((Double) temp[6]);
			dto.setIndCompVent((Integer) temp[7]);
			dto.getTpEstadOpera().setCodEstaOper((Integer) temp[8]);
			dto.getTpEstadOpera().setDesEstaOper((String) temp[9]);
			dto.getTpClien().getTpUsuar().setIdeUsuaEmai((String) temp[10]);
			dto.getTpClien().setValPrimNombPers((String) temp[11]);
			dto.getTpClien().setValSeguNombPers((String) temp[12]);
			dto.getTpClien().setValPrimApelPers((String) temp[13]);
			dto.getTpClien().setValSeguApelPers((String) temp[14]);
			dto.getTpClien().setValRazoSociPers((String) temp[15]);
			dto.getTpClien().getTpTipoDocumPerso().getTpTipoPerso().setCodTipoPers((Integer) temp[16]);
			dto.getTpCuentBancoByCodCuenBancClieOrig().setCodCuenBanc((Integer) temp[17]);
			dto.getTpCuentBancoByCodCuenBancCome().setCodCuenBanc((Integer) temp[18]);
			dto.getTpCuentBancoByCodCuenBancClieReci().setCodCuenBanc((Integer) temp[19]);	
			dto.setFecInicOper((Date) temp[20]);
			dto.setFecVeriOper((Date) temp[21]);
			dto.setFecFinaOper((Date) temp[22]);
			dto.setUsuApliFinaOper((String) temp[23]);
			dto.setFecCancOper((Date) temp[24]);
			dto.setUsuApliCancOper((String) temp[25]);
			dto.setValTextComeCanc((String)temp[26]);
			dto.getTpClien().getTpUsuar().setCodUsuaPadr((Integer) temp[27]);
			dto.getTpClien().setValNombPerf( (String) temp[28]);
			dto.getTpClien().setValDocuEmpr((String) temp[29]);
			dto.setCodCupoUsad((Integer) temp[30]);
			dto.setNomCupoUsad((String) temp[31]);  
			dto.setMonDescCupoUsad((Double) temp[32]);
			dto.setValCambCompCupo((Double) temp[33]);
			dto.setValCambVentCupo((Double) temp[34]);
			dto.setCodTranBanc((String) temp[35]);
			dto.getTpClien().setValTelePers((String) temp[36]);
			dto.setNumOperBancCome((String) temp[37]);
			dto.getTpClien().setValDocuPers((String) temp[38]);
			dto.getTpClien().getTpTipoDocumPerso().setCodTipoDocuPers((Integer) temp[39]);
			dto.getTpClien().setValDirePers((String) temp[40]);
			
			
			//toc.tpClien.valDocuPers, toc.tpClien.tpTipoDocumPerso.codTipoDocuPers, toc.tpClien.valDirePers
			
			
//			if(ValidacionesString.esNuloOVacio(dto.getTpClien().getValNombPerf())) {
//				dto.getTpClien().setValNombPerf(CadenasType.VACIO.getValor());
//			}
//			
//			if(ValidacionesString.esNuloOVacio(dto.getTpClien().getValRazoSociPers())) {
//				dto.getTpClien().setValRazoSociPers(CadenasType.VACIO.getValor());
//			}
			
			StringBuilder sb = new StringBuilder();
			sb.append(dto.getTpClien().getValPrimNombPers());
			sb.append(CadenasType.ESPACIO.getValor());
			if(dto.getTpClien().getValSeguNombPers() != null) {
				sb.append(dto.getTpClien().getValSeguNombPers());
				sb.append(CadenasType.ESPACIO.getValor());
				sb.append(dto.getTpClien().getValPrimApelPers());
				sb.append(CadenasType.ESPACIO.getValor());
				sb.append(dto.getTpClien().getValSeguApelPers());
			}else {
				sb.append(dto.getTpClien().getValPrimApelPers());
				sb.append(CadenasType.ESPACIO.getValor());
				sb.append(dto.getTpClien().getValSeguApelPers());
			}
			dto.getTpClien().setValNombreCompleto(sb.toString());
			
//			
//			if(ElementosTablasType.TIPO_PERSONERIA_NATURAL.getIdElemento().equals(dto.getTpClien().getTpTipoDocumPerso().getTpTipoPerso().getCodTipoPers())) {
//				StringBuilder sb = new StringBuilder();
//				sb.append(dto.getTpClien().getValPrimNombPers());
//				sb.append(CadenasType.ESPACIO.getValor());
//				if(dto.getTpClien().getValSeguNombPers() != null) {
//					sb.append(dto.getTpClien().getValSeguNombPers());
//					sb.append(CadenasType.ESPACIO.getValor());
//					sb.append(dto.getTpClien().getValPrimApelPers());
//					sb.append(CadenasType.ESPACIO.getValor());
//					sb.append(dto.getTpClien().getValSeguApelPers());
//				}else {
//					sb.append(dto.getTpClien().getValPrimApelPers());
//					sb.append(CadenasType.ESPACIO.getValor());
//					sb.append(dto.getTpClien().getValSeguApelPers());
//				}
//				dto.getTpClien().setValNombreCompleto(sb.toString());
//			}else {
//				dto.getTpClien().setValNombreCompleto(dto.getTpClien().getValRazoSociPers());
//			}
			
			resultado.add(dto);
		}
		
		return resultado;
		
	}

	@Override
	public BigInteger getCodigoUnicoOperacion() {


		BigInteger resultado = BigInteger.valueOf(NumerosType.NUMERO_MINIMO_CERO.getValor());
		
		resultado = daoOperacionCliente.getCodigoUnicoOperacion();
		
		return resultado;
	}

	@Override
	public String cancelarOperacionPorLimiteDeTiempo(Integer valorTiempoLimite) {
		String result = daoOperacionCliente.cancelarOperacionPorLimiteDeTiempo(valorTiempoLimite);
        return result;
	}



}
