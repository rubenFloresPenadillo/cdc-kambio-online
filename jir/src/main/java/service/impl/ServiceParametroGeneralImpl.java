package service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import dto.TpParamGenerDto;
import hibernate.entidades.TpParamGener;
import hibernate.util.HibernateUtil;
import model.dao.DaoParametroGeneral;
import model.dao.impl.DaoParametroGeneralImpl;
import service.ServiceParametroGeneral;

public class ServiceParametroGeneralImpl  implements ServiceParametroGeneral{

    private DaoParametroGeneral daoParametroGeneral;
    private Session session;

    public ServiceParametroGeneralImpl() {
        session =  HibernateUtil.getSessionFactory().openSession();

        daoParametroGeneral = new DaoParametroGeneralImpl(session);
    }

	@Override
	public List<TpParamGenerDto> getParametrosGenerales(TpParamGenerDto tpParamGenerDto) {
		
		List<TpParamGenerDto> resultado = new LinkedList<TpParamGenerDto>() ;
		TpParamGener entidad = null;
		
		if(tpParamGenerDto!=null) {
			entidad = new TpParamGener();
			entidad.setNomParaGene(tpParamGenerDto.getNomParaGene());
			entidad.setIndEsta(tpParamGenerDto.getIndEsta());
		}
		
		List<Object[]> listaTemporal = daoParametroGeneral.getParametrosGenerales(entidad);
		
		for(Object[] temp : listaTemporal) {
			
			TpParamGenerDto dto = new TpParamGenerDto();

			dto.setCodParaGene((Integer) temp[0]);
			dto.setNomParaGene((String) temp[1]);
			dto.setValParaGene((String) temp[2]);
			dto.setDesValoParaGene((String) temp[3]);
			dto.setIndEsta((Integer) temp[4]);
			dto.setUsuApliCrea((String) temp[5]);
			dto.setFecCreaRegi((Date) temp[6]);
			dto.setUsuApliModi((String) temp[7]);
			dto.setFecModiRegi((Date) temp[8]);
			
			resultado.add(dto);
		}
		
		return resultado;
	}

	@Override
	public String insertUpdate(TpParamGenerDto tpParamGenerDto) {

		TpParamGener tpParamGenerEntidad = new TpParamGener();
        
        if (tpParamGenerDto.getCodParaGene()  != null) {
        	tpParamGenerEntidad.setCodParaGene(tpParamGenerDto.getCodParaGene());
        	tpParamGenerEntidad.setFecModiRegi(tpParamGenerDto.getFecModiRegi());
        	tpParamGenerEntidad.setUsuApliModi(tpParamGenerDto.getUsuApliModi());
        }
        
        tpParamGenerEntidad.setNomParaGene(tpParamGenerDto.getNomParaGene());
        tpParamGenerEntidad.setValParaGene(tpParamGenerDto.getValParaGene());
        tpParamGenerEntidad.setDesValoParaGene(tpParamGenerDto.getDesValoParaGene());
        tpParamGenerEntidad.setIndEsta(tpParamGenerDto.getIndEsta());
        tpParamGenerEntidad.setFecCreaRegi(tpParamGenerDto.getFecCreaRegi());
        tpParamGenerEntidad.setUsuApliCrea(tpParamGenerDto.getUsuApliCrea());


        String result = daoParametroGeneral.insertUpdate(tpParamGenerEntidad);

        return result;
	}

	


}
