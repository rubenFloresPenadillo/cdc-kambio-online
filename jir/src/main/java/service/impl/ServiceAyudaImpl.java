package service.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import dto.TpAyudaPreguDto;
import hibernate.entidades.TpAyudaPregu;
import hibernate.util.HibernateUtil;
import model.dao.DaoAyuda;
import model.dao.impl.DaoAyudaImpl;
import service.ServiceAyuda;

public class ServiceAyudaImpl  implements ServiceAyuda{

    private DaoAyuda daoAyuda;
    private Session session;

    public ServiceAyudaImpl() {
        session =  HibernateUtil.getSessionFactory().openSession();
        daoAyuda = new DaoAyudaImpl(session);
    }

	@Override
	public List<TpAyudaPreguDto> getPreguntasDisponibles(TpAyudaPreguDto tpAyudaPreguDto) {
		List<TpAyudaPreguDto> resultado = new LinkedList<TpAyudaPreguDto>() ;
		
		List<TpAyudaPregu> listaTemporal = daoAyuda.getPreguntasDisponibles(null);
		
		for(TpAyudaPregu temp : listaTemporal) {
			TpAyudaPreguDto dto = new TpAyudaPreguDto();
			dto.setCodAyudPreg(temp.getCodAyudPreg());
			dto.getTpTipoPregu().setCodTipoPreg(temp.getTpTipoPregu().getCodTipoPreg());
			dto.getTpTipoPregu().setDesTipoPreg(temp.getTpTipoPregu().getDesTipoPreg());
			dto.setValPreg(temp.getValPreg());
			dto.setValResp(temp.getValResp());
			dto.setValNumePosi(temp.getValNumePosi());
			dto.setIndEsta(temp.getIndEsta());
			dto.setUsuApliCrea(temp.getUsuApliCrea());
			dto.setFecCreaRegi(temp.getFecCreaRegi());
			dto.setUsuApliModi(temp.getUsuApliModi());
			dto.setFecModiRegi(temp.getFecModiRegi());
	        
			resultado.add(dto);
		}
		
		return resultado;
	}

	
	@Override
	public String insertUpdate(TpAyudaPreguDto tpAyudaPreguDto) {
		
		TpAyudaPregu tpAyudaPreguEntidad = new TpAyudaPregu();
        
        if (tpAyudaPreguDto.getCodAyudPreg()  != null) {
        	tpAyudaPreguEntidad.setCodAyudPreg(tpAyudaPreguDto.getCodAyudPreg());
        	tpAyudaPreguEntidad.setFecModiRegi(tpAyudaPreguDto.getFecModiRegi());
        	tpAyudaPreguEntidad.setUsuApliModi(tpAyudaPreguDto.getUsuApliModi());
        }
        
        tpAyudaPreguEntidad.getTpTipoPregu().setCodTipoPreg(tpAyudaPreguDto.getTpTipoPregu().getCodTipoPreg());
        tpAyudaPreguEntidad.getTpTipoPregu().setDesTipoPreg(tpAyudaPreguDto.getTpTipoPregu().getDesTipoPreg());
        tpAyudaPreguEntidad.setValPreg(tpAyudaPreguDto.getValPreg());
        tpAyudaPreguEntidad.setValResp(tpAyudaPreguDto.getValResp());
		tpAyudaPreguEntidad.setValNumePosi(tpAyudaPreguDto.getValNumePosi());
        tpAyudaPreguEntidad.setIndEsta(tpAyudaPreguDto.getIndEsta());
        tpAyudaPreguEntidad.setFecCreaRegi(tpAyudaPreguDto.getFecCreaRegi());
        tpAyudaPreguEntidad.setUsuApliCrea(tpAyudaPreguDto.getUsuApliCrea());

        String result = daoAyuda.insertUpdate(tpAyudaPreguEntidad);

        return result;
	}

}

