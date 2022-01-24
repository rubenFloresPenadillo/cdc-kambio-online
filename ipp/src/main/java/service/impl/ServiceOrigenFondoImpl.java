package service.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import dto.TpOrigeFondoDto;
import hibernate.entidades.TpOrigeFondo;
import hibernate.util.HibernateUtil;
import model.dao.DaoOrigenFondo;
import model.dao.impl.DaoOrigenFondoImpl;
import service.ServiceOrigenFondo;

public class ServiceOrigenFondoImpl  implements ServiceOrigenFondo{

    private DaoOrigenFondo daoOrigenFondo;
    private Session session;

    public ServiceOrigenFondoImpl() {
        session =  HibernateUtil.getSessionFactory().openSession();

        daoOrigenFondo = new DaoOrigenFondoImpl(session);
    }

	@Override
	public List<TpOrigeFondoDto> getOrigenFondos(TpOrigeFondoDto tpOrigenFondos) {
		List<TpOrigeFondoDto> resultado = new LinkedList<TpOrigeFondoDto>() ;
		List<TpOrigeFondo> listaTemporal = daoOrigenFondo.getOrigenFondos(null);
		
		for(TpOrigeFondo temp : listaTemporal) {
			TpOrigeFondoDto dto = new TpOrigeFondoDto();
			dto.setCodOrigFond(temp.getCodOrigFond());
			dto.setDesOrigFond(temp.getDesOrigFond());
			resultado.add(dto);
		}
		
		return resultado;
	}

    
}
