package service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import cadenas.util.ValidacionesString;
import dto.TpClienDto;
import dto.TpTipoDocumPersoDto;
import dto.TpTipoPersoDto;
import dto.TpUsuarDto;
import hibernate.entidades.TpClien;
import hibernate.entidades.TpTipoDocumPerso;
import hibernate.entidades.TpTipoPerso;
import hibernate.entidades.TpUsuar;
import hibernate.util.HibernateUtil;
import loggerUtil.LoggerUtil;
import model.dao.DaoUsuario;
import model.dao.impl.DaoUsuarioImpl;
import service.ServiceUsuario;
import util.types.CadenasType;
import util.types.ElementosTablasType;
import util.types.EstadosCuentaUsuarioType;
import util.types.PerfilesType;

public class ServiceUsuarioImpl  implements ServiceUsuario{

    private DaoUsuario daoUsuario;
    private Session session;

    public ServiceUsuarioImpl() {
//        session =  HibernateUtil.getSessionFactory().getCurrentSession();
        session =  HibernateUtil.getSessionFactory().openSession();

        daoUsuario = new DaoUsuarioImpl(session);
    }
    
    @Override
	public String insertUpdate(TpUsuarDto tpUsuarDto) {

        
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
        	tpUsuarEntidad = daoUsuario.get(tpUsuarDto.getCodUsua()); 
        }
        LoggerUtil.getInstance().getLogger().info("ServiceUsuarioImpl.insertUpdate");
        String result = daoUsuario.insertUpdate(tpUsuarEntidad);

        return result;
	}
	
	@Override
	public TpUsuarDto getUsuario(TpUsuarDto tpUsuarDto) {
		
        TpUsuar entidad = new TpUsuar();
        TpUsuarDto resultado = null;

        entidad.setIdeUsuaEmai(tpUsuarDto.getIdeUsuaEmai() );
        entidad.setCodClav(tpUsuarDto.getCodClav());
        
        entidad = daoUsuario.getUsuario(entidad);
        
        if(entidad != null) {
        	resultado = new TpUsuarDto();
        	resultado.setCodUsua(entidad.getCodUsua());
        	resultado.setCodClie(entidad.getCodClie());
        	resultado.setIdeUsuaEmai(entidad.getIdeUsuaEmai());
        	resultado.setCodClav(entidad.getCodClav());
        	resultado.setCodEstaCuenUsua(entidad.getCodEstaCuenUsua());
        	resultado.setValNomb(entidad.getValNomb());
        	resultado.setIndCompDato(entidad.getIndCompDato());
        	resultado.setCodPerfUsua(entidad.getCodPerfUsua());
        	resultado.setCodOperClie(entidad.getCodOperClie());
        	resultado.setCodEstaOper(entidad.getCodEstaOper());
        	resultado.getTpTipoPerso().setCodTipoPers(entidad.getTpTipoPerso().getCodTipoPers());
        	resultado.setValNombRegi(entidad.getValNombRegi());
        }

        return resultado;
	}

	@Override
	public void closeSesion() {
		daoUsuario.closeSesion();
	}

	@Override
	public List<TpTipoDocumPersoDto> getTipoDocumentoPersona(TpTipoDocumPersoDto tpTipoDocumPersoDto) {
		List<TpTipoDocumPersoDto> resultado = new LinkedList<TpTipoDocumPersoDto>() ;
		TpTipoDocumPerso tpTipoDocumPerso = new TpTipoDocumPerso();
		tpTipoDocumPerso.getTpTipoPerso().setCodTipoPers(tpTipoDocumPersoDto.getTpTipoPerso().getCodTipoPers());
		List<TpTipoDocumPerso> listaTemporal = daoUsuario.getTipoDocumentoPersona(tpTipoDocumPerso);
		
		for(TpTipoDocumPerso temp : listaTemporal) {
			TpTipoDocumPersoDto dto = new TpTipoDocumPersoDto();
			dto.setCodTipoDocuPers(temp.getCodTipoDocuPers());
			dto.setNomTipoDocuPerso(temp.getNomTipoDocuPers());
			resultado.add(dto);
		}
		return resultado;
	}

	@Override
	public TpUsuarDto getOperacionEnCurso(Integer codigoUsuario) {
		
		TpUsuarDto resultado = null;

		Object[] temporal = daoUsuario.getOperacionEnCurso(codigoUsuario);
		
		if (temporal != null) {
			resultado = new TpUsuarDto();
			resultado.setCodOperClie((Integer) temporal[0]);
			resultado.setCodEstaOper((Integer) temporal[1]);
		}
		
		
		return resultado;
	}

	@Override
	public List<TpClienDto> getUsuarios(TpClienDto tpClienDto) {
		
		List<TpClienDto> resultado = new LinkedList<TpClienDto>() ;
		TpClien entidad = null;
		
		if(tpClienDto!=null) {
			entidad = new TpClien();
			entidad.getTpUsuar().setCodPerfUsua(tpClienDto.getTpUsuar().getCodPerfUsua());
		}
		
		List<Object[]> listaTemporal = daoUsuario.getUsuarios(entidad);
		
		for(Object[] temp : listaTemporal) {
			
			TpClienDto dto = new TpClienDto();
			
			dto.getTpUsuar().setCodUsua((Integer) temp[0]);
			dto.getTpUsuar().getTpTipoPerso().setCodTipoPers((Integer) temp[1]);
			dto.getTpUsuar().getTpTipoPerso().setDesTipoPers((String) temp[2]);
			dto.getTpUsuar().setIdeUsuaEmai((String) temp[3]);
			dto.getTpUsuar().setEmaUsuaAuxi((String) temp[4] == null ? (String) temp[3] : (String) temp[4]);
			dto.getTpUsuar().setCodClav((String) temp[5]);
			dto.getTpUsuar().setValNomb((String) temp[6]);
			dto.getTpUsuar().setIndCompDato((Integer) temp[7]);
			dto.getTpUsuar().setCodClie((Integer) temp[8]);
			dto.getTpUsuar().setCodEstaOper((Integer) temp[9]);
			dto.getTpUsuar().setCodOperClie((Integer) temp[10]);
			dto.getTpUsuar().setCodPerfUsua((Integer) temp[11]);
			dto.getTpUsuar().setDesPerfUsua(PerfilesType.get((Integer) temp[11]));
			
			dto.setValPrimNombPers(temp[12] == null ? CadenasType.VACIO.getValor() : String.valueOf(temp[12]));
			dto.setValSeguNombPers(temp[13] == null ? CadenasType.VACIO.getValor() : String.valueOf(temp[13]));
			dto.setValPrimApelPers(temp[14] == null ? CadenasType.VACIO.getValor() : String.valueOf(temp[14]));
			dto.setValSeguApelPers(temp[15] == null ? CadenasType.VACIO.getValor() : String.valueOf(temp[15]));
			dto.setValRazoSociPers(temp[16] == null ? CadenasType.VACIO.getValor() : String.valueOf(temp[16]));
			
			dto.getTpUsuar().setCodEstaCuenUsua((Integer) temp[17]);
			dto.getTpUsuar().setDesEstaCuenUsua(EstadosCuentaUsuarioType.get((Integer) temp[17])); 
			
			
			dto.getTpUsuar().setIndEsta((Integer) temp[18]);
			dto.getTpUsuar().setUsuApliCrea((String) temp[19]);
			dto.getTpUsuar().setFecCreaRegi((Date) temp[20]);
			dto.getTpUsuar().setUsuApliModi((String) temp[21]);
			dto.getTpUsuar().setFecModiRegi((Date) temp[22]);
			
			
			if(ElementosTablasType.TIPO_PERSONERIA_NATURAL.getIdElemento().equals(dto.getTpUsuar().getTpTipoPerso().getCodTipoPers())) {
				StringBuilder sb = new StringBuilder();
				sb.append(dto.getValPrimNombPers());
				sb.append(CadenasType.ESPACIO.getValor());
				if(dto.getValSeguNombPers() != null) {
					sb.append(dto.getValSeguNombPers());
					sb.append(CadenasType.ESPACIO.getValor());
					sb.append(dto.getValPrimApelPers());
					sb.append(CadenasType.ESPACIO.getValor());
					sb.append(dto.getValSeguApelPers());
				}else {
					sb.append(dto.getValPrimApelPers());
					sb.append(CadenasType.ESPACIO.getValor());
					sb.append(dto.getValSeguApelPers());
				}
				dto.setValNombreCompleto( ValidacionesString.esNuloOVacio(sb.toString()) ? CadenasType.VACIO.getValor() : sb.toString() );
			}else {
				dto.setValNombreCompleto(ValidacionesString.esNuloOVacio(dto.getValRazoSociPers()) ? CadenasType.VACIO.getValor() : dto.getValRazoSociPers());
			}
			
			resultado.add(dto);
		}
		
		return resultado;
	}

	@Override
	public String updateCuenta(TpUsuarDto tpUsuarDto) {
		
		TpUsuar tpUsuarEntidad = new TpUsuar();
		
		tpUsuarEntidad.setIdeUsuaEmai(tpUsuarDto.getIdeUsuaEmai());
		tpUsuarEntidad.setValTokeCuen(tpUsuarDto.getValTokeCuen());
		tpUsuarEntidad.setCodEstaCuenUsua(tpUsuarDto.getCodEstaCuenUsua());
		tpUsuarEntidad.setUsuApliModi(tpUsuarDto.getIdeUsuaEmai());
		tpUsuarEntidad.setFecModiRegi(tpUsuarDto.getFecModiRegi());
		
        String result = daoUsuario.updateCuenta(tpUsuarEntidad);

        return result;
	}

	@Override
	public String updateCuentaActivacionPendiente(TpUsuarDto tpUsuarDto) {
		
		TpUsuar tpUsuarEntidad = new TpUsuar();
		
		tpUsuarEntidad.setIdeUsuaEmai(tpUsuarDto.getIdeUsuaEmai());
		tpUsuarEntidad.setValTokeCuen(tpUsuarDto.getValTokeCuen());
		tpUsuarEntidad.setFecCreaToke(tpUsuarDto.getFecCreaToke());
		tpUsuarEntidad.setUsuApliModi(tpUsuarDto.getIdeUsuaEmai());
		tpUsuarEntidad.setFecModiRegi(tpUsuarDto.getFecModiRegi());
		
        String result = daoUsuario.updateCuentaActivacionPendiente(tpUsuarEntidad);

        return result;
	}

	@Override
	public String updateCuentaRestablece(TpUsuarDto tpUsuarDto) {
		
		TpUsuar tpUsuarEntidad = new TpUsuar();
		
		tpUsuarEntidad.setIdeUsuaEmai(tpUsuarDto.getIdeUsuaEmai());
		tpUsuarEntidad.setValTokeRestCuen(tpUsuarDto.getValTokeRestCuen());
		tpUsuarEntidad.setFecCreaTokeRestCuen(tpUsuarDto.getFecCreaTokeRestCuen());
		tpUsuarEntidad.setUsuApliModi(tpUsuarDto.getIdeUsuaEmai());
		tpUsuarEntidad.setFecModiRegi(tpUsuarDto.getFecModiRegi());
		
        String result = daoUsuario.updateCuentaRestablece(tpUsuarEntidad);

        return result;
	}

	@Override
	public String updateGeneraClave(TpUsuarDto tpUsuarDto) {

		TpUsuar tpUsuarEntidad = new TpUsuar();
		
		tpUsuarEntidad.setIdeUsuaEmai(tpUsuarDto.getIdeUsuaEmai());
		tpUsuarEntidad.setCodClav(tpUsuarDto.getCodClav());
		tpUsuarEntidad.setValTokeRestCuen(tpUsuarDto.getValTokeRestCuen());
		tpUsuarEntidad.setUsuApliModi(tpUsuarDto.getIdeUsuaEmai());
		tpUsuarEntidad.setFecUltiModiClav(tpUsuarDto.getFecUltiModiClav());
		tpUsuarEntidad.setFecModiRegi(tpUsuarDto.getFecModiRegi());
		
        String result = daoUsuario.updateGeneraClave(tpUsuarEntidad);

        return result;
	}

	@Override
	public List<TpTipoPersoDto> getTipoPersona() {
		List<TpTipoPersoDto> resultado = new LinkedList<TpTipoPersoDto>() ;
		List<TpTipoPerso> listaTemporal = daoUsuario.getTipoPersona();
		
		for(TpTipoPerso temp : listaTemporal) {
			TpTipoPersoDto dto = new TpTipoPersoDto();
			dto.setCodTipoPers(temp.getCodTipoPers());
			dto.setDesTipoPers(temp.getDesTipoPers());
			resultado.add(dto);
		}
		return resultado;
	}
}
