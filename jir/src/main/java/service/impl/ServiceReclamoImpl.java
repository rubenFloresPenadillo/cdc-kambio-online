package service.impl;

import org.hibernate.Session;

import dto.TpReclaQuejaDto;
import hibernate.entidades.TpReclaQueja;
import hibernate.util.HibernateUtil;
import model.dao.DaoReclamo;
import model.dao.impl.DaoReclamoImpl;
import service.ServiceReclamo;

public class ServiceReclamoImpl  implements ServiceReclamo {

    private DaoReclamo daoReclamo;
    private Session session;

    public ServiceReclamoImpl() {
        session =  HibernateUtil.getSessionFactory().openSession();
        daoReclamo = new DaoReclamoImpl(session);
    }

	@Override
	public String insertUpdate(TpReclaQuejaDto tpReclaQuejaDto) {

		TpReclaQueja tpReclaQuejaEntidad = new TpReclaQueja();
        
        if (tpReclaQuejaDto.getCodReclQuej()  != null) {
        	tpReclaQuejaEntidad.setCodReclQuej(tpReclaQuejaDto.getCodReclQuej());
        	tpReclaQuejaEntidad.setFecModiRegi(tpReclaQuejaDto.getFecModiRegi());
        	tpReclaQuejaEntidad.setUsuApliModi(tpReclaQuejaDto.getUsuApliModi());
        }

        
//        tpReclaQuejaEntidad.setCodCortBanc(tpBancoDto.getCodCortBanc());
//        tpReclaQuejaEntidad.setNomBanc(tpBancoDto.getNomBanc());
//        tpReclaQuejaEntidad.setIndVistClie(tpBancoDto.getIndVistClie());
//        tpReclaQuejaEntidad.setIndVistAdmi(tpBancoDto.getIndVistAdmi());
        
        tpReclaQuejaEntidad.getTpTipoPerso().setCodTipoPers(tpReclaQuejaDto.getTpTipoPerso().getCodTipoPers());
        tpReclaQuejaEntidad.setValNombPers(tpReclaQuejaDto.getValNombPers());
        tpReclaQuejaEntidad.setValApelPers(tpReclaQuejaDto.getValApelPers());
        tpReclaQuejaEntidad.setValRazoSociPers(tpReclaQuejaDto.getValRazoSociPers());
        tpReclaQuejaEntidad.setValDocuEmpr(tpReclaQuejaDto.getValDocuEmpr());
        tpReclaQuejaEntidad.getTpTipoDocumPerso().setCodTipoDocuPers(tpReclaQuejaDto.getTpTipoDocumPerso().getCodTipoDocuPers());
        tpReclaQuejaEntidad.setValDocuPers(tpReclaQuejaDto.getValDocuPers());
        tpReclaQuejaEntidad.setValEmaiCont(tpReclaQuejaDto.getValEmaiCont());
        tpReclaQuejaEntidad.setValCeluCont(tpReclaQuejaDto.getValCeluCont());
        tpReclaQuejaEntidad.setValTipoServCont(tpReclaQuejaDto.getValTipoServCont());
        tpReclaQuejaEntidad.setValMontCamb(tpReclaQuejaDto.getValMontCamb());
        tpReclaQuejaEntidad.setCodUnicOperClie(tpReclaQuejaDto.getCodUnicOperClie());
        tpReclaQuejaEntidad.setValTipoReclQuej(tpReclaQuejaDto.getValTipoReclQuej());
        tpReclaQuejaEntidad.setValDescReclQuej(tpReclaQuejaDto.getValDescReclQuej());
        tpReclaQuejaEntidad.setIndEsta(tpReclaQuejaDto.getIndEsta());
        tpReclaQuejaEntidad.setFecCreaRegi(tpReclaQuejaDto.getFecCreaRegi());
        tpReclaQuejaEntidad.setUsuApliCrea(tpReclaQuejaDto.getUsuApliCrea());

        String result = daoReclamo.insertUpdate(tpReclaQuejaEntidad);

        return result;
        
	}
    
}
