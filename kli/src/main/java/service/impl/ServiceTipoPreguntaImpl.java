package service.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import dto.TpTipoPreguDto;
import hibernate.entidades.TpTipoPregu;
import hibernate.util.HibernateUtil;
import model.dao.DaoTipoPregunta;
import model.dao.impl.DaoTipoPreguntaImpl;
import service.ServiceTipoPregunta;

public class ServiceTipoPreguntaImpl  implements ServiceTipoPregunta{

    private DaoTipoPregunta daoTipoPregunta;
    private Session session;

    public ServiceTipoPreguntaImpl() {
        session =  HibernateUtil.getSessionFactory().openSession();

        daoTipoPregunta = new DaoTipoPreguntaImpl(session);
    }


	@Override
	public List<TpTipoPreguDto> getTipoPreguntas(TpTipoPreguDto tpTipoPreguDto) {
		List<TpTipoPreguDto> resultado = new LinkedList<TpTipoPreguDto>() ;
		List<TpTipoPregu> listaTemporal = daoTipoPregunta.getTipoPreguntas(null);
		
		for(TpTipoPregu temp : listaTemporal) {
			TpTipoPreguDto dto = new TpTipoPreguDto();
			dto.setCodTipoPreg(temp.getCodTipoPreg());
			dto.setDesTipoPreg(temp.getDesTipoPreg());
			resultado.add(dto);
		}
		
		return resultado;
	}

    
}
