package service.impl;

import org.hibernate.Session;

import dto.TpSuscrDto;
import hibernate.entidades.TpSuscr;
import hibernate.util.HibernateUtil;
import model.dao.DaoSuscripcion;
import model.dao.impl.DaoSuscripcionImpl;
import service.ServiceSuscripcion;

public class ServiceSuscripcionImpl  implements ServiceSuscripcion{

    private DaoSuscripcion daoSuscripcion;
    private Session session;

    public ServiceSuscripcionImpl() {
        session =  HibernateUtil.getSessionFactory().openSession();
        daoSuscripcion = new DaoSuscripcionImpl(session);
    }

	@Override
	public String insertUpdate(TpSuscrDto tpSuscrDto) {

		TpSuscr tpSuscrEntidad = new TpSuscr();
        
        if (tpSuscrDto.getCodSusc()  != null) {
        	tpSuscrEntidad.setCodSusc(tpSuscrDto.getCodSusc());
        	tpSuscrEntidad.setFecModiRegi(tpSuscrDto.getFecModiRegi());
        	tpSuscrEntidad.setUsuApliModi(tpSuscrDto.getUsuApliModi());
        }

        tpSuscrEntidad.setEmaSusc(tpSuscrDto.getEmaSusc());
        tpSuscrEntidad.setIndEsta(tpSuscrDto.getIndEsta());
        tpSuscrEntidad.setFecCreaRegi(tpSuscrDto.getFecCreaRegi());
        tpSuscrEntidad.setUsuApliCrea(tpSuscrDto.getUsuApliCrea());

        String result = daoSuscripcion.insertUpdate(tpSuscrEntidad);

        return result;
        
	}
    
}
