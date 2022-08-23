package service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import dto.TpDivisDto;
import hibernate.entidades.TpDivis;
import hibernate.entidades.TpOperaClien;
import hibernate.util.HibernateUtil;
import model.dao.DaoDivisa;
import model.dao.impl.DaoDivisImpl;
import service.ServiceDivisa;

public class ServiceDivisaImpl  implements ServiceDivisa{

    private DaoDivisa daoDivisa;
    private Session session;

    public ServiceDivisaImpl() {
        session =  HibernateUtil.getSessionFactory().openSession();

        daoDivisa = new DaoDivisImpl(session);
    }

	@Override
	public List<TpDivisDto> getDivisas(TpDivisDto tpDivisDto) {
		
		List<TpDivisDto> resultado = new LinkedList<TpDivisDto>() ;
		TpDivis entidad = null;
		
		if(tpDivisDto!=null) {
			entidad = new TpDivis();
		}
		
		List<Object[]> listaTemporal = daoDivisa.getDivisas(entidad);
		
		for(Object[] temp : listaTemporal) {
			TpDivisDto dto = new TpDivisDto();
			dto.setCodDivi((Integer) temp[0]);
			dto.setCodIsoDivi((String) temp[1]);
			dto.setNomDiviSing((String) temp[2]);
			dto.setNomDiviPlur((String) temp[3]);
			dto.setSimDivi((String) temp[4]);
			dto.setIndApliCuenBanc((Integer) temp[5]);
			dto.setIndEsta((Integer) temp[6]);
			dto.setUsuApliCrea((String) temp[7]);
			dto.setFecCreaRegi((Date) temp[8]);
			dto.setUsuApliModi((String) temp[9]);
			dto.setFecModiRegi((Date) temp[10]);
			
			resultado.add(dto);
		}
		
		return resultado;
	}

	@Override
	public String insertUpdate(TpDivisDto tpDivisDto) {
		
		TpDivis tpDivisEntidad = new TpDivis();
        
        if (tpDivisDto.getCodDivi()  != null) {
        	tpDivisEntidad.setCodDivi(tpDivisDto.getCodDivi());
            tpDivisEntidad.setFecModiRegi(tpDivisDto.getFecModiRegi());
            tpDivisEntidad.setUsuApliModi(tpDivisDto.getUsuApliModi());
        }
        
        tpDivisEntidad.setCodIsoDivi(tpDivisDto.getCodIsoDivi());
        tpDivisEntidad.setNomDiviSing(tpDivisDto.getNomDiviSing());
        tpDivisEntidad.setNomDiviPlur(tpDivisDto.getNomDiviPlur());
        tpDivisEntidad.setSimDivi(tpDivisDto.getSimDivi());
        tpDivisEntidad.setIndApliCuenBanc(tpDivisDto.getIndApliCuenBanc());
        tpDivisEntidad.setIndEsta(tpDivisDto.getIndEsta());
        tpDivisEntidad.setFecCreaRegi(tpDivisDto.getFecCreaRegi());
        tpDivisEntidad.setUsuApliCrea(tpDivisDto.getUsuApliCrea());


        String result = daoDivisa.insertUpdate(tpDivisEntidad);

        return result;
	}



}
