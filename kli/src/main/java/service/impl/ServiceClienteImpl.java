package service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import dto.TpActivEconoDto;
import dto.TpClienDto;
import dto.TpCuentBancoDto;
import dto.TpDeparDto;
import dto.TpDistrDto;
import dto.TpProviDto;
import dto.TpSectoEconoDto;
import dto.TpUsuarDto;
import hibernate.entidades.TpClien;
import hibernate.entidades.TpCuentBanco;
import hibernate.entidades.TpDepar;
import hibernate.entidades.TpDistr;
import hibernate.entidades.TpProvi;
import hibernate.entidades.TpTipoPerso;
import hibernate.entidades.TpUsuar;
import hibernate.util.HibernateUtil;
import model.dao.DaoCliente;
import model.dao.impl.DaoClienteImpl;
import numeros.util.ValidacionesNumeros;
import service.ServiceCliente;
import util.types.CadenasType;
import util.types.ElementosTablasType;
import util.types.NumerosType;

public class ServiceClienteImpl  implements ServiceCliente{

    private DaoCliente daoCliente;
    private Session session;

    public ServiceClienteImpl() {
        session =  HibernateUtil.getSessionFactory().openSession();

        daoCliente = new DaoClienteImpl(session);
    }

	@Override
	public void closeSesion() {
		daoCliente.closeSesion();
	}

	@Override
	public String insertUpdate(TpClienDto tpClienDto) {
		
		TpClien tpClienEntidad = new TpClien();
        
        if (tpClienDto.getCodClie()  != null) {
        	tpClienEntidad.setCodClie(tpClienDto.getCodClie());
        }
        
        
        tpClienEntidad.getTpUsuar().setCodUsua(tpClienDto.getTpUsuar().getCodUsua());
        tpClienEntidad.getTpUsuar().setValNombRegi(tpClienDto.getValPrimNombPers());
        tpClienEntidad.getTpTipoDocumPerso().setCodTipoDocuPers(tpClienDto.getTpTipoDocumPerso().getCodTipoDocuPers());
        tpClienEntidad.setValDocuPers(tpClienDto.getValDocuPers());
        tpClienEntidad.setFecNaci(tpClienDto.getFecNaci());
        tpClienEntidad.setValPrimNombPers(tpClienDto.getValPrimNombPers());
        tpClienEntidad.setValSeguNombPers(tpClienDto.getValSeguNombPers());
        tpClienEntidad.setValPrimApelPers(tpClienDto.getValPrimApelPers());
        tpClienEntidad.setValSeguApelPers(tpClienDto.getValSeguApelPers());
        tpClienEntidad.setValTelePers(tpClienDto.getValTelePers());
        tpClienEntidad.getTpPaisByCodPaisNaci().setCodPais(tpClienDto.getTpPaisByCodPaisNaci().getCodPais());
        tpClienEntidad.getTpPaisByCodPaisResi().setCodPais(tpClienDto.getTpPaisByCodPaisResi().getCodPais());
        
        if(tpClienDto.getTpDepar()!= null && !ValidacionesNumeros.esCeroONuloEntero(tpClienDto.getTpDepar().getCodDepa())) {
        	TpDepar tpDepar = new TpDepar();
        	tpDepar.setCodDepa(tpClienDto.getTpDepar().getCodDepa());
        	tpClienEntidad.setTpDepar(tpDepar);
        }
        
    	if( tpClienDto.getTpProvi()!= null && !ValidacionesNumeros.esCeroONuloEntero(tpClienDto.getTpProvi().getCodProv())) {
    		TpProvi tpProvi = new TpProvi();
    		tpProvi.setCodProv(tpClienDto.getTpProvi().getCodProv());
    		tpClienEntidad.setTpProvi(tpProvi);
    	}
    	
    	if( tpClienDto.getTpDistr()!= null && !ValidacionesNumeros.esCeroONuloEntero(tpClienDto.getTpDistr().getCodDist())) {
    		TpDistr tpDistr = new TpDistr();
    		tpDistr.setCodDist(tpClienDto.getTpDistr().getCodDist());
    		tpClienEntidad.setTpDistr(tpDistr);
    	}

        tpClienEntidad.setValDirePers(tpClienDto.getValDirePers());
        tpClienEntidad.setIndPepo(tpClienDto.getIndPepo());
        tpClienEntidad.setValPepoInst(tpClienDto.getValPepoInst());
        tpClienEntidad.setValPepoRol(tpClienDto.getValPepoRol());
        tpClienEntidad.setIndEsta(tpClienDto.getIndEsta());
        tpClienEntidad.setUsuApliCrea(tpClienDto.getUsuApliCrea());
        tpClienEntidad.setFecCreaRegi(tpClienDto.getFecCreaRegi());
        tpClienEntidad.getTpActivEcono().setCodActiEcon(tpClienDto.getTpActivEcono().getCodActiEcon());
        
        String result = daoCliente.insertUpdate(tpClienEntidad);

        return result;
	}

	@Override
	public TpClienDto get(Integer codClie) {
		
		TpClienDto tpClienDto = null;
		
		TpClien tpClienEntidad =  daoCliente.get(codClie); 
		
		if(tpClienEntidad != null) {
			
			tpClienDto = new TpClienDto();
			tpClienDto.setCodClie(tpClienEntidad.getCodClie());
			tpClienDto.setValPrimNombPers(tpClienEntidad.getValPrimNombPers());
			tpClienDto.setValSeguNombPers(tpClienEntidad.getValSeguNombPers());
			tpClienDto.setValPrimApelPers(tpClienEntidad.getValPrimApelPers());
			tpClienDto.setValSeguApelPers(tpClienEntidad.getValSeguApelPers());
			tpClienDto.getTpTipoDocumPerso().setCodTipoDocuPers(tpClienEntidad.getTpTipoDocumPerso().getCodTipoDocuPers());
			tpClienDto.setValDocuPers(tpClienEntidad.getValDocuPers());
			tpClienDto.setFecNaci(tpClienEntidad.getFecNaci());
			tpClienDto.setValTelePers(tpClienEntidad.getValTelePers());
			tpClienDto.getTpPaisByCodPaisNaci().setCodPais(tpClienEntidad.getTpPaisByCodPaisNaci().getCodPais());
			tpClienDto.getTpPaisByCodPaisResi().setCodPais(tpClienEntidad.getTpPaisByCodPaisResi().getCodPais());
			if(tpClienEntidad.getTpDepar() != null) {
				TpDeparDto temp = new TpDeparDto();
				temp.setCodDepa(tpClienEntidad.getTpDepar().getCodDepa());
				tpClienDto.setTpDepar(temp);
			}
			if(tpClienEntidad.getTpProvi() != null) {
				TpProviDto temp = new TpProviDto();
				temp.setCodProv(tpClienEntidad.getTpProvi().getCodProv());
				tpClienDto.setTpProvi(temp);
			}
			if(tpClienEntidad.getTpDistr() != null) {
				TpDistrDto temp = new TpDistrDto();
				temp.setCodDist(tpClienEntidad.getTpDistr().getCodDist());
				tpClienDto.setTpDistr(temp);
			}
			
			tpClienDto.setValDirePers(tpClienEntidad.getValDirePers());
			tpClienDto.setIndPepo(tpClienEntidad.getIndPepo());
			tpClienDto.setValPepoInst(tpClienEntidad.getValPepoInst());
			tpClienDto.setValPepoRol(tpClienEntidad.getValPepoRol());
		}
		
		
		return tpClienDto;
	}

	@Override
	public String insertUpdateCuentaBanco(TpCuentBancoDto tpCuentBancoDto) {

		TpCuentBanco tpCuentBancoEntidad = new TpCuentBanco();
        
        if (tpCuentBancoDto.getCodCuenBanc()  != null) {
        	tpCuentBancoEntidad.setCodCuenBanc(tpCuentBancoDto.getCodCuenBanc());
        }

        tpCuentBancoEntidad.getTpClien().setCodClie(tpCuentBancoDto.getTpClien().getCodClie());
        tpCuentBancoEntidad.setAliCuen(tpCuentBancoDto.getAliCuen());
        tpCuentBancoEntidad.getTpBanco().setCodBanc(tpCuentBancoDto.getTpBanco().getCodBanc());
        tpCuentBancoEntidad.getTpDivis().setCodDivi(tpCuentBancoDto.getTpDivis().getCodDivi());
        tpCuentBancoEntidad.getTpTipoCuent().setCodTipoCuen(tpCuentBancoDto.getTpTipoCuent().getCodTipoCuen());
        tpCuentBancoEntidad.setValCuenBanc(tpCuentBancoDto.getValCuenBanc());
        tpCuentBancoEntidad.setValCuenInte(tpCuentBancoDto.getValCuenInte());
        tpCuentBancoEntidad.setIndCuenProp(tpCuentBancoDto.getIndCuenProp());
        tpCuentBancoEntidad.setIndEsta(tpCuentBancoDto.getIndEsta());
        tpCuentBancoEntidad.setUsuApliCrea(tpCuentBancoDto.getUsuApliCrea());
        tpCuentBancoEntidad.setFecCreaRegi(tpCuentBancoDto.getFecCreaRegi());
        tpCuentBancoEntidad.setUsuApliModi(tpCuentBancoDto.getUsuApliModi());
        tpCuentBancoEntidad.setFecModiRegi(tpCuentBancoDto.getFecModiRegi());
        String result = daoCliente.insertUpdateCuentaBanco(tpCuentBancoEntidad);

        return result;
	}

	@Override
	public List<TpCuentBancoDto> getCuentasBanco(TpCuentBancoDto tpCuentBancoDto) {
		List<TpCuentBancoDto> resultado = new LinkedList<TpCuentBancoDto>() ;
		TpCuentBanco entidad = new TpCuentBanco();
		TpClien entidadCliente = new TpClien();
		
		entidadCliente.setCodClie(tpCuentBancoDto.getTpClien().getCodClie());
		entidad.setTpClien(entidadCliente);

		List<Object[]> listaTemporal = daoCliente.getCuentasBanco(entidad);
		
		for(Object[] temp : listaTemporal) {
			TpCuentBancoDto dto = new TpCuentBancoDto();
			dto.setCodCuenBanc((Integer) temp[0]);
			dto.setValCuenBanc(String.valueOf(temp[1]));
			dto.setAliCuen(String.valueOf(temp[2]));
			dto.setIndCuenProp((Integer) temp[3]);
			dto.getTpBanco().setCodBanc((Integer) temp[4]);
			dto.getTpBanco().setCodCortBanc(String.valueOf(temp[5]));
			dto.getTpBanco().setNomBanc(String.valueOf(temp[6]));
			dto.getTpDivis().setCodDivi((Integer) temp[7]);
			dto.getTpDivis().setCodIsoDivi(String.valueOf(temp[8]));
			dto.getTpDivis().setSimDivi(String.valueOf(temp[9]));
			dto.getTpDivis().setNomDiviSing(String.valueOf(temp[10]));
			dto.getTpTipoCuent().setCodTipoCuen((Integer) temp[11]);
			dto.getTpTipoCuent().setDesTipoCuen(String.valueOf(temp[12]));
			dto.setValCuenInte((String) temp[13]);
			dto.setIndEsta((Integer) temp[14]);
			dto.setUsuApliCrea((String) temp[15]);
			dto.setFecCreaRegi((Date) temp[16]);
			dto.setUsuApliModi((String) temp[17]);
			dto.setFecModiRegi((Date) temp[18]);
			resultado.add(dto);
		}
		
		return resultado;
	}

	@Override
	public TpCuentBancoDto getCuentaBanco(TpCuentBancoDto tpCuentBancoDto) {
		
		TpCuentBancoDto resultado = null;
		TpCuentBanco entidad = new TpCuentBanco();
		
		if(tpCuentBancoDto.getCodCuenBanc() !=null) {
			entidad.setCodCuenBanc(tpCuentBancoDto.getCodCuenBanc());
		}else {
			entidad.getTpBanco().setCodCortBanc(tpCuentBancoDto.getTpBanco().getCodCortBanc());
			entidad.getTpBanco().setCodBanc(tpCuentBancoDto.getTpBanco().getCodBanc());
			entidad.getTpDivis().setCodDivi(tpCuentBancoDto.getTpDivis().getCodDivi());
			entidad.getTpClien().setCodClie(tpCuentBancoDto.getTpClien().getCodClie());
		}
		
		Object[] temporal = daoCliente.getCuentaBanco(entidad);
		
		if (temporal != null) {
			resultado = new TpCuentBancoDto();
			resultado.setCodCuenBanc((Integer) temporal[0]);
			resultado.setValCuenBanc(String.valueOf(temporal[1]));
			resultado.setValCuenInte(String.valueOf(temporal[2]));
			resultado.getTpBanco().setCodCortBanc(String.valueOf(temporal[3]));
			resultado.getTpBanco().setNomBanc(String.valueOf(temporal[4]));
			resultado.getTpDivis().setCodIsoDivi(String.valueOf(temporal[5]));
			resultado.getTpDivis().setSimDivi(String.valueOf(temporal[6]));
			resultado.getTpClien().getTpTipoDocumPerso().getTpTipoPerso().setCodTipoPers((Integer) temporal[7]);
			resultado.getTpClien().getTpTipoDocumPerso().setNomTipoDocuPerso(String.valueOf(temporal[8]));
			resultado.getTpClien().setValPrimNombPers(String.valueOf(temporal[9]));
			resultado.getTpClien().setValSeguNombPers(String.valueOf(temporal[10]));
			resultado.getTpClien().setValPrimApelPers(String.valueOf(temporal[11]));
			resultado.getTpClien().setValSeguApelPers(String.valueOf(temporal[12]));
			resultado.getTpClien().setValRazoSociPers(String.valueOf(temporal[13]));
			resultado.getTpClien().setValDocuPers(String.valueOf(temporal[14]));
			resultado.getTpTipoCuent().setDesTipoCuen(String.valueOf(temporal[15]));
			resultado.setAliCuen(String.valueOf(temporal[16]));
			
			if(ElementosTablasType.TIPO_PERSONERIA_NATURAL.getIdElemento().equals(resultado.getTpClien().getTpTipoDocumPerso().getTpTipoPerso().getCodTipoPers())) {
				StringBuilder sb = new StringBuilder();
				sb.append(resultado.getTpClien().getValPrimNombPers());
				sb.append(CadenasType.ESPACIO.getValor());
				if(resultado.getTpClien().getValSeguNombPers() != null) {
					sb.append(resultado.getTpClien().getValSeguNombPers());
					sb.append(CadenasType.ESPACIO.getValor());
					sb.append(resultado.getTpClien().getValPrimApelPers());
					sb.append(CadenasType.ESPACIO.getValor());
					sb.append(resultado.getTpClien().getValSeguApelPers());
				}else {
					sb.append(resultado.getTpClien().getValPrimApelPers());
					sb.append(CadenasType.ESPACIO.getValor());
					sb.append(resultado.getTpClien().getValSeguApelPers());
				}
				resultado.setNombreTitularVista(sb.toString());
				resultado.setNombreClienteCorreo(resultado.getTpClien().getValPrimNombPers()+CadenasType.ESPACIO.getValor()+resultado.getTpClien().getValPrimApelPers());
			}else {
				resultado.setNombreTitularVista(resultado.getTpClien().getValRazoSociPers());
				resultado.setNombreClienteCorreo(resultado.getTpClien().getValRazoSociPers());
			}
		}
		
		
		return resultado;
	}

	@Override
	public Integer getExisteCuentaBancariaCliente(Integer codClie) {
		Integer resultado = NumerosType.NUMERO_MINIMO_CERO.getValor();
		resultado = daoCliente.getExisteCuentaBancariaCliente(codClie);
		return resultado;
	}

	@Override
	public Integer getExisteBancoEnNegocio(TpCuentBancoDto tpCuentBancoDto) {
		Integer resultado = NumerosType.NUMERO_MINIMO_CERO.getValor();
		resultado = daoCliente.getExisteBancoEnNegocio(tpCuentBancoDto);
		return resultado;
	}

	@Override
	public List<TpSectoEconoDto> getListaSectorEconomico(TpSectoEconoDto tpSectoEconoDto) {
		
		List<TpSectoEconoDto> resultado = new LinkedList<TpSectoEconoDto>() ;

		List<Object[]> listaTemporal = daoCliente.getListaSectorEconomico(null);
		
		for(Object[] temp : listaTemporal) {
			TpSectoEconoDto dto = new TpSectoEconoDto();
			
			dto.setCodSectEcon((Integer) temp[0]);
			dto.setDesSectEcon(String.valueOf(temp[1]));
			resultado.add(dto);
		}
		
		return resultado;
	}

	@Override
	public List<TpActivEconoDto> getListaActividadEconomica(TpSectoEconoDto tpSectoEconoDto) {
		
		List<TpActivEconoDto> resultado = new LinkedList<TpActivEconoDto>() ;

		List<Object[]> listaTemporal = daoCliente.getListaActividadEconomica(tpSectoEconoDto);
		
		for(Object[] temp : listaTemporal) {
			
			TpActivEconoDto dto = new TpActivEconoDto();
			
			dto.setCodActiEcon((Integer) temp[0]);
			dto.setDesActiEcon(String.valueOf(temp[1]));
			resultado.add(dto);
		}
		
		return resultado;
	}

	@Override
	public String insertUpdateEnterprise(TpUsuarDto tpUsuarDto, TpClienDto tpClienDto) {

		 TpUsuar tpUsuarEntidad = new TpUsuar();
	     
		 TpTipoPerso tpTipoPersoEntidad = new TpTipoPerso();

	     tpUsuarEntidad.setIdeUsuaEmai(tpUsuarDto.getIdeUsuaEmai());
	     tpUsuarEntidad.setCodClav(tpUsuarDto.getCodClav());
	     tpUsuarEntidad.setIndEsta(tpUsuarDto.getIndEsta());
	     tpUsuarEntidad.setCodEstaCuenUsua(tpUsuarDto.getCodEstaCuenUsua());
	     tpUsuarEntidad.setValTokeCuen(tpUsuarDto.getValTokeCuen());
	     tpUsuarEntidad.setFecCreaToke(tpUsuarDto.getFecCreaToke());
	     tpUsuarEntidad.setCodPerfUsua(tpUsuarDto.getCodPerfUsua());
	     tpUsuarEntidad.setUsuApliCrea(tpUsuarDto.getUsuApliCrea());
	     tpUsuarEntidad.setFecCreaRegi(tpUsuarDto.getFecCreaRegi());
	     tpUsuarEntidad.setIndCompDato(tpUsuarDto.getIndCompDato());
	     tpUsuarEntidad.setValNombRegi(tpUsuarDto.getValNombRegi());
	     tpUsuarEntidad.setCodUsuaPadr(tpUsuarDto.getCodUsuaPadr());
	        
	     tpTipoPersoEntidad.setCodTipoPers(tpUsuarDto.getTpTipoPerso().getCodTipoPers());
	     tpUsuarEntidad.setTpTipoPerso(tpTipoPersoEntidad);
	        
	     if (tpUsuarDto.getCodUsua()  != null) { 
	        	tpUsuarEntidad = daoCliente.getUsuar(tpUsuarDto.getCodUsua()); 
	     }

		TpClien tpClienEntidad = new TpClien();
        
        if (tpClienDto.getCodClie()  != null) {
        	tpClienEntidad.setCodClie(tpClienDto.getCodClie());
        }
        
        
        tpClienEntidad.getTpUsuar().setCodUsua(tpClienDto.getTpUsuar().getCodUsua());
        tpClienEntidad.getTpUsuar().setValNombRegi(tpClienDto.getValPrimNombPers());
        tpClienEntidad.getTpTipoDocumPerso().setCodTipoDocuPers(tpClienDto.getTpTipoDocumPerso().getCodTipoDocuPers());
        tpClienEntidad.getTpTipoDocumPerso().getTpTipoPerso().setCodTipoPers(tpClienDto.getTpTipoDocumPerso().getTpTipoPerso().getCodTipoPers());
        tpClienEntidad.setValDocuPers(tpClienDto.getValDocuPers());
        tpClienEntidad.setFecNaci(tpClienDto.getFecNaci());
        tpClienEntidad.setValPrimNombPers(tpClienDto.getValPrimNombPers());
        tpClienEntidad.setValSeguNombPers(tpClienDto.getValSeguNombPers());
        tpClienEntidad.setValPrimApelPers(tpClienDto.getValPrimApelPers());
        tpClienEntidad.setValSeguApelPers(tpClienDto.getValSeguApelPers());
        tpClienEntidad.setValTelePers(tpClienDto.getValTelePers());
        tpClienEntidad.getTpPaisByCodPaisNaci().setCodPais(tpClienDto.getTpPaisByCodPaisNaci().getCodPais());
        tpClienEntidad.getTpPaisByCodPaisResi().setCodPais(tpClienDto.getTpPaisByCodPaisResi().getCodPais());
        
        if(tpClienDto.getTpDepar()!= null && !ValidacionesNumeros.esCeroONuloEntero(tpClienDto.getTpDepar().getCodDepa())) {
        	TpDepar tpDepar = new TpDepar();
        	tpDepar.setCodDepa(tpClienDto.getTpDepar().getCodDepa());
        	tpClienEntidad.setTpDepar(tpDepar);
        }
        
    	if( tpClienDto.getTpProvi()!= null && !ValidacionesNumeros.esCeroONuloEntero(tpClienDto.getTpProvi().getCodProv())) {
    		TpProvi tpProvi = new TpProvi();
    		tpProvi.setCodProv(tpClienDto.getTpProvi().getCodProv());
    		tpClienEntidad.setTpProvi(tpProvi);
    	}
    	
    	if( tpClienDto.getTpDistr()!= null && !ValidacionesNumeros.esCeroONuloEntero(tpClienDto.getTpDistr().getCodDist())) {
    		TpDistr tpDistr = new TpDistr();
    		tpDistr.setCodDist(tpClienDto.getTpDistr().getCodDist());
    		tpClienEntidad.setTpDistr(tpDistr);
    	}

    	tpClienEntidad.setValDocuEmpr(tpClienDto.getValDocuEmpr());
    	tpClienEntidad.setValRazoSociPers(tpClienDto.getValRazoSociPers());
    	tpClienEntidad.setValNombPerf(tpClienDto.getValNombPerf());
    	tpClienEntidad.getTpActivEcono().setCodActiEcon(tpClienDto.getTpActivEcono().getCodActiEcon());
        tpClienEntidad.setValDirePers(tpClienDto.getValDirePers());
        tpClienEntidad.setIndPepo(tpClienDto.getIndPepo());
        tpClienEntidad.setValPepoInst(tpClienDto.getValPepoInst());
        tpClienEntidad.setValPepoRol(tpClienDto.getValPepoRol());
        tpClienEntidad.setIndEsta(tpClienDto.getIndEsta());
        tpClienEntidad.setUsuApliCrea(tpClienDto.getUsuApliCrea());
        tpClienEntidad.setFecCreaRegi(tpClienDto.getFecCreaRegi());

        String result = daoCliente.insertUpdateEnterprise(tpUsuarEntidad, tpClienEntidad);

        return result;
        
	}

	@Override
	public List<TpClienDto> getListaPerfilesEmpresa(TpClienDto tpClienDto) {
		
		List<TpClienDto> resultado = new LinkedList<TpClienDto>() ;

		List<Object[]> listaTemporal = daoCliente.getListaPerfilesEmpresa(tpClienDto);
		
		for(Object[] temp : listaTemporal) {
			TpClienDto dto = new TpClienDto();
			
			dto.setCodClie((Integer) temp[0]);
			dto.setValRazoSociPers(String.valueOf(temp[1]));
			dto.setValNombPerf(String.valueOf(temp[2]));
			dto.getTpUsuar().setCodUsua((Integer) temp[3]);
			dto.getTpUsuar().setCodOperClie((Integer) temp[4]);
			dto.getTpUsuar().setCodEstaOper((Integer) temp[5]);
			resultado.add(dto);
		}
		
		return resultado;
	}

}
