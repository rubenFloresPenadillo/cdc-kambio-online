package service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import dto.AudiTpTipoCambiDto;
import dto.TpTipoCambiDto;
import hibernate.entidades.TpTipoCambi;
import hibernate.util.HibernateUtil;
import loggerUtil.LoggerUtil;
import model.dao.DaoTipoCambio;
import model.dao.impl.DaoTipoCambioImpl;
import service.ServiceTipoCambio;

public class ServiceTipoCambioImpl  implements ServiceTipoCambio{

    private DaoTipoCambio daoTipoCambio;
    private Session session;

    public ServiceTipoCambioImpl() {
        session =  HibernateUtil.getSessionFactory().openSession();
        LoggerUtil.getInstance().getLogger().info("Despues del HibernateUtil.getSessionFactory().openSession");
        daoTipoCambio = new DaoTipoCambioImpl(session);
        LoggerUtil.getInstance().getLogger().info("Despues del DaoTipoCambioImpl(session)");
    }

	@Override
	public void closeSesion() {
		daoTipoCambio.closeSesion();
	}

	@Override
	public TpTipoCambiDto getTipoCambio(TpTipoCambiDto tpTipoCambiDto) {
		TpTipoCambi entidad = new TpTipoCambi();
		TpTipoCambiDto resultado = null;
		
		entidad.getTpDivisByCodDivi().setCodDivi(tpTipoCambiDto.getTpDivisByCodDivi().getCodDivi());
		entidad.getTpDivisByCodDiviCamb().setCodDivi(tpTipoCambiDto.getTpDivisByCodDiviCamb().getCodDivi());
		
//		LoggerUtil.getInstance().getLogger().info("Log 1 "+tpTipoCambiDto.getTpDivisByCodDivi().getCodDivi());
//		LoggerUtil.getInstance().getLogger().info("Log 2 "+tpTipoCambiDto.getTpDivisByCodDiviCamb().getCodDivi());
		
		entidad = daoTipoCambio.getTipoCambio(entidad);

		LoggerUtil.getInstance().getLogger().info("Log entidad "+entidad);
		
		if (entidad != null) {
			resultado = new TpTipoCambiDto();
			resultado.setCodTipoCamb(entidad.getCodTipoCamb());
			resultado.setValCambComp(entidad.getValCambComp());
			resultado.setValCambVent(entidad.getValCambVent());
		}

		return resultado;
	}

	@Override
	public List<TpTipoCambiDto> getTipoCambios(TpTipoCambiDto tpTipoCambiDto) {
		
		List<TpTipoCambiDto> resultado = new LinkedList<TpTipoCambiDto>() ;
		TpTipoCambi entidad = null;
		
		if(tpTipoCambiDto!=null) {
			entidad = new TpTipoCambi();
		}
		
		List<Object[]> listaTemporal = daoTipoCambio.getTipoCambios(entidad);
		
		for(Object[] temp : listaTemporal) {
			
			TpTipoCambiDto dto = new TpTipoCambiDto();

			dto.setCodTipoCamb((Integer) temp[0]);
			dto.getTpDivisByCodDivi().setCodDivi((Integer) temp[1]);
			dto.getTpDivisByCodDiviCamb().setCodDivi((Integer) temp[2]);
			dto.getTpDivisByCodDivi().setNomDiviSing((String) temp[3]);
			dto.getTpDivisByCodDiviCamb().setNomDiviSing((String) temp[4]);
			dto.setValCambComp((Double) temp[5]);
			dto.setValCambVent((Double) temp[6]);
			dto.setIndEsta((Integer) temp[7]);
			dto.setUsuApliCrea((String) temp[8]);
			dto.setFecCreaRegi((Date) temp[9]);
			dto.setUsuApliModi((String) temp[10]);
			dto.setFecModiRegi((Date) temp[11]);
			
			resultado.add(dto);
		}
		
		return resultado;
		
	}
    
	@Override
	public String insertUpdate(TpTipoCambiDto tpTipoCambiDto) {
		
		TpTipoCambi tpTipoCambiEntidad = new TpTipoCambi();
        
        if (tpTipoCambiDto.getCodTipoCamb() != null) {
        	tpTipoCambiEntidad.setCodTipoCamb(tpTipoCambiDto.getCodTipoCamb());
        	tpTipoCambiEntidad.setFecModiRegi(tpTipoCambiDto.getFecModiRegi());
        	tpTipoCambiEntidad.setUsuApliModi(tpTipoCambiDto.getUsuApliModi());
        }

        tpTipoCambiEntidad.getTpDivisByCodDivi().setCodDivi(tpTipoCambiDto.getTpDivisByCodDivi().getCodDivi());
        tpTipoCambiEntidad.getTpDivisByCodDiviCamb().setCodDivi(tpTipoCambiDto.getTpDivisByCodDiviCamb().getCodDivi());
        tpTipoCambiEntidad.setValCambComp(tpTipoCambiDto.getValCambComp());
        tpTipoCambiEntidad.setValCambVent(tpTipoCambiDto.getValCambVent());
        tpTipoCambiEntidad.setIndEsta(tpTipoCambiDto.getIndEsta());
        tpTipoCambiEntidad.setFecCreaRegi(tpTipoCambiDto.getFecCreaRegi());
        tpTipoCambiEntidad.setUsuApliCrea(tpTipoCambiDto.getUsuApliCrea());


        String result = daoTipoCambio.insertUpdate(tpTipoCambiEntidad);

        return result;
	}

	@Override
	public List<AudiTpTipoCambiDto> getTipoCambiosVariacion(Integer cantidadResultados) {
		
		List<AudiTpTipoCambiDto> resultado = new LinkedList<AudiTpTipoCambiDto>() ;
		
		List<Object[]> listaTemporal = daoTipoCambio.getTipoCambiosVariacion(cantidadResultados);
		
		for(Object[] temp : listaTemporal) {
			
			AudiTpTipoCambiDto dto = new AudiTpTipoCambiDto();

			dto.setFecModiRegi((Date) temp[0]);
			dto.setValCambComp((Double) temp[1]);
			dto.setValCambVent((Double) temp[2]);

			resultado.add(dto);
		}
		
		return resultado;
	}

}
