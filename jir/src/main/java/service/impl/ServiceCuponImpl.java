package service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import dto.TpBancoDto;
import dto.TpCuponDto;
import hibernate.entidades.TpBanco;
import hibernate.entidades.TpCupon;
import hibernate.util.HibernateUtil;
import model.dao.DaoCupon;
import model.dao.impl.DaoCuponImpl;
import service.ServiceCupon;

public class ServiceCuponImpl  implements ServiceCupon{

    private DaoCupon daoCupon;
    private Session session;

    public ServiceCuponImpl() {
        session =  HibernateUtil.getSessionFactory().openSession();
        daoCupon = new DaoCuponImpl(session);
    }

	@Override
	public List<TpCuponDto> getCuponesDisponibles(TpBancoDto tpBancoDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TpCuponDto> getCupones(TpCuponDto tpCuponDto) {
		
		List<TpCuponDto> resultado = new LinkedList<TpCuponDto>() ;

		List<Object[]> listaTemporal = daoCupon.getCupones(tpCuponDto); 
		
		for(Object[] temp : listaTemporal) {
			
			TpCuponDto dto = new TpCuponDto();

			dto.setCodCupo((Integer) temp[0]);
			dto.setCodTipoPersApli((Integer) temp[1]);
			dto.setCodTipoOperApli((Integer) temp[2]);
			dto.setEmaClieCupo((String) temp[3]);
			dto.setNomCupo((String) temp[4]);
			dto.setFecInicVige((Date) temp[5]);
			dto.setFecFinaVige((Date) temp[6]);
			dto.setCanCupo((Integer) temp[7]);
			dto.setMonDescCupo((Double) temp[8]);
			dto.setIndReutClie((Integer) temp[9]);
			dto.setIndEsta((Integer) temp[10]);
			dto.setUsuApliCrea((String) temp[11]);
	        dto.setFecCreaRegi((Date) temp[12]);
	        dto.setUsuApliModi((String) temp[13]);
	        dto.setFecModiRegi((Date) temp[14]);
			
			resultado.add(dto);
		}
		
		return resultado;
	}

	@Override
	public String insertUpdate(TpCuponDto tpCuponDto) {

		TpCupon tpCuponEntidad = new TpCupon();
        
        if (tpCuponDto.getCodCupo()  != null) {
        	tpCuponEntidad.setCodCupo(tpCuponDto.getCodCupo());
        }

        
        tpCuponEntidad.setCodTipoPersApli(tpCuponDto.getCodTipoPersApli());
        tpCuponEntidad.setCodTipoOperApli(tpCuponDto.getCodTipoOperApli());
        tpCuponEntidad.setEmaClieCupo(tpCuponDto.getEmaClieCupo());
        tpCuponEntidad.setNomCupo(tpCuponDto.getNomCupo());
        tpCuponEntidad.setFecInicVige(tpCuponDto.getFecInicVige());
        tpCuponEntidad.setFecFinaVige(tpCuponDto.getFecFinaVige());
        tpCuponEntidad.setCanCupo(tpCuponDto.getCanCupo());
        tpCuponEntidad.setMonDescCupo(tpCuponDto.getMonDescCupo());
        tpCuponEntidad.setIndReutClie(tpCuponDto.getIndReutClie());
        tpCuponEntidad.setIndEsta(tpCuponDto.getIndEsta());
        tpCuponEntidad.setUsuApliCrea(tpCuponDto.getUsuApliCrea());
        tpCuponEntidad.setFecCreaRegi(tpCuponDto.getFecCreaRegi());
        tpCuponEntidad.setUsuApliModi(tpCuponDto.getUsuApliModi());
        tpCuponEntidad.setFecModiRegi(tpCuponDto.getFecModiRegi());
        
        
//        tpBancoEntidad.setCodCortBanc(tpBancoDto.getCodCortBanc());
//        tpBancoEntidad.setNomBanc(tpBancoDto.getNomBanc());
//        tpBancoEntidad.setIndVistClie(tpBancoDto.getIndVistClie());
//        tpBancoEntidad.setIndVistAdmi(tpBancoDto.getIndVistAdmi());
//        tpBancoEntidad.setIndTienCuenNego(tpBancoDto.getIndTienCuenNego());
//        tpBancoEntidad.setIndEsta(tpBancoDto.getIndEsta());
//        tpBancoEntidad.setFecCreaRegi(tpBancoDto.getFecCreaRegi());
//        tpBancoEntidad.setUsuApliCrea(tpBancoDto.getUsuApliCrea());

        String result = daoCupon.insertUpdate(tpCuponEntidad);

        return result;
	}

//	@Override
//	public void closeSesion() {
//		daoBanco.closeSesion();
//	}
	
	
    
}

