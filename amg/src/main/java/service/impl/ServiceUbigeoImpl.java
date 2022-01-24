package service.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import dto.TpClienDto;
import dto.TpDeparDto;
import dto.TpDistrDto;
import dto.TpPaisDto;
import dto.TpProviDto;
import hibernate.entidades.TpClien;
import hibernate.entidades.TpDepar;
import hibernate.entidades.TpDistr;
import hibernate.entidades.TpPais;
import hibernate.entidades.TpProvi;
import hibernate.util.HibernateUtil;
import model.dao.DaoUbigeo;
import model.dao.impl.DaoUbigeoImpl;
import service.ServiceUbigeo;

public class ServiceUbigeoImpl  implements ServiceUbigeo{

    private DaoUbigeo daoUbigeo;
    private Session session;

    public ServiceUbigeoImpl() {
        session =  HibernateUtil.getSessionFactory().openSession();
        daoUbigeo = new DaoUbigeoImpl(session);
    }

	@Override
	public void closeSesion() {
		daoUbigeo.closeSesion();
	}
	
	@Override
	public List<TpPaisDto> getPaises(TpPaisDto tpPaisDto) {
		List<TpPaisDto> resultado = new LinkedList<TpPaisDto>() ;

		List<Object[]> listaTemporal = daoUbigeo.getPaises(null);
		
		for(Object[] temp : listaTemporal) {
			TpPaisDto dto = new TpPaisDto();
			dto.setCodPais((Integer) temp[0]);
			dto.setNomPais(String.valueOf(temp[1]));
			dto.setDesGent(String.valueOf(temp[2]));
			resultado.add(dto);
		}
		
		return resultado;
	}

	@Override
	public List<TpDeparDto> getDepartamentos() {
		List<TpDeparDto> resultado = new LinkedList<TpDeparDto>() ;

		List<Object[]> listaTemporal = daoUbigeo.getDepartamentos();
		
		for(Object[] temp : listaTemporal) {
			TpDeparDto dto = new TpDeparDto();
			dto.setCodDepa((Integer) temp[0]);
			dto.setDesDepa(String.valueOf(temp[1]));
			resultado.add(dto);
		}
		
		return resultado;
	}

	@Override
	public List<TpProviDto> getProvincias(Integer codigo) {
		List<TpProviDto> resultado = new LinkedList<TpProviDto>() ;

		List<Object[]> listaTemporal = daoUbigeo.getProvincias(codigo);
		
		for(Object[] temp : listaTemporal) {
			TpProviDto dto = new TpProviDto();
			dto.setCodProv((Integer) temp[0]);
			dto.setDesProv(String.valueOf(temp[1]));
			resultado.add(dto);
		}
		
		return resultado;
	}

	@Override
	public List<TpDistrDto> getDistritos(Integer codigo) {
		List<TpDistrDto> resultado = new LinkedList<TpDistrDto>() ;

		List<Object[]> listaTemporal = daoUbigeo.getDistritos(codigo);
		
		for(Object[] temp : listaTemporal) {
			TpDistrDto dto = new TpDistrDto();
			dto.setCodDist((Integer) temp[0]);
			dto.setDesDist(String.valueOf(temp[1]));
			resultado.add(dto);
		}
		
		return resultado;
	}

	@Override
	public TpDeparDto getDepartamento(Integer codigo) {
		
		TpDepar tpDeparEntidad =  daoUbigeo.getDepartamento(codigo);
		TpDeparDto tpDeparDto = null;
		
		if(tpDeparEntidad != null) {
			tpDeparDto = new TpDeparDto();
			tpDeparDto.setCodDepa(tpDeparEntidad.getCodDepa());
			tpDeparDto.setDesDepa(tpDeparEntidad.getDesDepa());
		}
				
		return tpDeparDto;
	}

	@Override
	public TpProviDto getProvincia(Integer codigo) {

		TpProvi tpProviEntidad =  daoUbigeo.getProvincia(codigo);
		TpProviDto tpProviDto = null;
		
		if(tpProviEntidad != null) {
			tpProviDto = new TpProviDto();
			tpProviDto.setCodProv(tpProviEntidad.getCodProv());
			tpProviDto.setDesProv(tpProviEntidad.getDesProv());
		}
		
		return tpProviDto;
	}

	@Override
	public TpDistrDto getDistrito(Integer codigo) {

		TpDistr tpDistrEntidad =  daoUbigeo.getDistrito(codigo);
		TpDistrDto tpDistrDto = null;
		
		if(tpDistrEntidad != null) {
			tpDistrDto = new TpDistrDto();
			tpDistrDto.setCodDist(tpDistrEntidad.getCodDist());
			tpDistrDto.setDesDist(tpDistrEntidad.getDesDist());
		}
		
		return tpDistrDto;
	}
    
}
