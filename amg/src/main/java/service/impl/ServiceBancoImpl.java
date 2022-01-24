package service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import dto.TpBancoDto;
import dto.TpDivisDto;
import dto.TpTipoCuentDto;
import hibernate.entidades.TpBanco;
import hibernate.entidades.TpDivis;
import hibernate.entidades.TpTipoCuent;
import hibernate.util.HibernateUtil;
import model.dao.DaoBanco;
import model.dao.impl.DaoBancoImpl;
import service.ServiceBanco;

public class ServiceBancoImpl  implements ServiceBanco{

    private DaoBanco daoBanco;
    private Session session;

    public ServiceBancoImpl() {
        session =  HibernateUtil.getSessionFactory().openSession();
        daoBanco = new DaoBancoImpl(session);
    }

//	@Override
//	public void closeSesion() {
//		daoBanco.closeSesion();
//	}
	
	@Override
	public List<TpBancoDto> getBancosDisponibles(TpBancoDto tpBancoDto) {
		List<TpBancoDto> resultado = new LinkedList<TpBancoDto>() ;
		
		TpBanco entidad = new TpBanco();
		entidad.setIndVistAdmi(tpBancoDto.getIndVistAdmi());
		entidad.setIndVistClie(tpBancoDto.getIndVistClie());
		
		List<TpBanco> listaTemporal = daoBanco.getBancosDisponibles(entidad);
		
		for(TpBanco temp : listaTemporal) {
			TpBancoDto dto = new TpBancoDto();
			dto.setCodBanc(temp.getCodBanc());
			dto.setCodCortBanc(temp.getCodCortBanc());
			dto.setNomBanc(temp.getNomBanc());
			resultado.add(dto);
		}
		return resultado;
	}

	@Override
	public List<TpDivisDto> getDivisasCuentasDisponibles(TpDivisDto tpDivisDto) {
		List<TpDivisDto> resultado = new LinkedList<TpDivisDto>() ;

		TpDivis entidadFiltro = new TpDivis();
		entidadFiltro.setIndApliCuenBanc(tpDivisDto.getIndApliCuenBanc());
		List<TpDivis> listaTemporal = daoBanco.getDivisasCuentasDisponibles(entidadFiltro);
		
		for(TpDivis temp : listaTemporal) {
			TpDivisDto dto = new TpDivisDto();
			dto.setCodDivi(temp.getCodDivi());
			dto.setCodIsoDivi(temp.getCodIsoDivi());
			dto.setNomDiviSing(temp.getNomDiviSing());
			dto.setNomDiviPlur(temp.getNomDiviPlur());
			dto.setSimDivi(temp.getSimDivi());
			resultado.add(dto);
		}
		return resultado;
	}

	@Override
	public List<TpTipoCuentDto> getTipoCuentasBacarias(TpTipoCuentDto tpTipoCuentDto) {
		
		List<TpTipoCuentDto> resultado = new LinkedList<TpTipoCuentDto>() ;

		List<TpTipoCuent> listaTemporal = daoBanco.getTipoCuentasBacarias(null);
		
		for(TpTipoCuent temp : listaTemporal) {
			TpTipoCuentDto dto = new TpTipoCuentDto();
			dto.setCodTipoCuen(temp.getCodTipoCuen());
			dto.setDesTipoCuen(temp.getDesTipoCuen());
			resultado.add(dto);
		}
		return resultado;
	}

	@Override
	public List<TpBancoDto> getBancos(TpBancoDto tpBancoDto) {
		
		List<TpBancoDto> resultado = new LinkedList<TpBancoDto>() ;
		TpBanco entidad = null;
		
		if(tpBancoDto!=null) {
			entidad = new TpBanco();
		}
		
		List<Object[]> listaTemporal = daoBanco.getBancos(entidad); 
		
		for(Object[] temp : listaTemporal) {
			
			TpBancoDto dto = new TpBancoDto();

			dto.setCodBanc((Integer) temp[0]);
			dto.setCodCortBanc((String) temp[1]);
			dto.setNomBanc((String) temp[2]);
			dto.setIndVistClie((Integer) temp[3]);
			dto.setIndVistAdmi((Integer) temp[4]);
			dto.setIndTienCuenNego((Integer) temp[5]);
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
	public String insertUpdate(TpBancoDto tpBancoDto) {

		TpBanco tpBancoEntidad = new TpBanco();
        
        if (tpBancoDto.getCodBanc()  != null) {
        	tpBancoEntidad.setCodBanc(tpBancoDto.getCodBanc());
        	tpBancoEntidad.setFecModiRegi(tpBancoDto.getFecModiRegi());
        	tpBancoEntidad.setUsuApliModi(tpBancoDto.getUsuApliModi());
        }

        
        tpBancoEntidad.setCodCortBanc(tpBancoDto.getCodCortBanc());
        tpBancoEntidad.setNomBanc(tpBancoDto.getNomBanc());
        tpBancoEntidad.setIndVistClie(tpBancoDto.getIndVistClie());
        tpBancoEntidad.setIndVistAdmi(tpBancoDto.getIndVistAdmi());
        tpBancoEntidad.setIndTienCuenNego(tpBancoDto.getIndTienCuenNego());
        tpBancoEntidad.setIndEsta(tpBancoDto.getIndEsta());
        tpBancoEntidad.setFecCreaRegi(tpBancoDto.getFecCreaRegi());
        tpBancoEntidad.setUsuApliCrea(tpBancoDto.getUsuApliCrea());

        String result = daoBanco.insertUpdate(tpBancoEntidad);

        return result;
        
	}
    
}

