package service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import cadenas.util.ValidacionesString;
import dto.TpEntraDto;
import dto.TpOperaClienDto;
import fecha.util.FechaUtil;
import file.util.FileUtil;
import hibernate.entidades.TpEntra;
import hibernate.entidades.TpOperaClien;
import hibernate.util.HibernateUtil;
import model.dao.DaoEntrada;
import model.dao.impl.DaoEntradaImpl;
import numeros.util.ValidacionesNumeros;
import service.ServiceEntrada;

public class ServiceEntradaImpl  implements ServiceEntrada{

    private DaoEntrada daoEntra;
    private Session session;

    public ServiceEntradaImpl() {
        session =  HibernateUtil.getSessionFactory().openSession();
        daoEntra = new DaoEntradaImpl(session);
    }

//	@Override
//	public void closeSesion() {
//		daoBanco.closeSesion();
//	}
	
	@Override
	public List<TpEntraDto> getEntradasDisponibles(TpEntraDto TpEntraDto) {
		List<TpEntraDto> resultado = new LinkedList<TpEntraDto>() ;
		
		TpEntra entidad = new TpEntra();
//		entidad.setIndVistAdmi(TpEntraDto.getIndVistAdmi());
//		entidad.setIndVistClie(TpEntraDto.getIndVistClie());
		
		List<TpEntra> listaTemporal = daoEntra.getEntradasDisponibles(entidad);
		
		for(TpEntra temp : listaTemporal) {
			TpEntraDto dto = new TpEntraDto();
//			dto.setCodBanc(temp.getCodBanc());
//			dto.setCodCortBanc(temp.getCodCortBanc());
//			dto.setNomBanc(temp.getNomBanc());
			resultado.add(dto);
		}
		return resultado;
	}
	

	
	@Override
	public List<TpEntraDto> getEntradas(TpEntraDto tpEntraDto) throws IOException {
		
		List<TpEntraDto> resultado = new LinkedList<TpEntraDto>() ;
		TpEntra entidad = null;
		
		if(tpEntraDto!=null) {
			entidad = new TpEntra();
		}
		
		List<Object[]> listaTemporal = daoEntra.getEntradas(entidad); 
		
		for(Object[] temp : listaTemporal) {
			
			TpEntraDto dto = new TpEntraDto();

			dto.setCodEntr((Integer) temp[0]);
			dto.setTitEntr((String) temp[1]);
			dto.setCatEntr((String) temp[2]);
			dto.setImaEntrPrev((String) temp[3]);			
			dto.setImaEntrCont((String) temp[4]);
			dto.setNomImaPrev((String) temp[5]);
			dto.setNomImaCont((String) temp[6]);
			dto.setConEntr((String) temp[7]);
			dto.setEnlEntr((String) temp[8]);
			dto.setIndEsta((Integer) temp[9]);
			dto.setUsuApliCrea((String) temp[10]);
			dto.setFecCreaRegi((Date) temp[11]);
			dto.setUsuApliModi((String) temp[12]);
			dto.setFecModiRegi((Date) temp[13]);
			dto.setFecCreaFormPrev(FechaUtil.formatoFecha(dto.getFecCreaRegi()));
			
			resultado.add(dto);
		}
		
		return resultado;
	}

	@Override
	public String insertUpdate(TpEntraDto tpEntraDto) {

		TpEntra tpEntraEntidad = new TpEntra();
        
        if (tpEntraDto.getCodEntr()  != null) {
        	tpEntraEntidad.setCodEntr(tpEntraDto.getCodEntr());
        	tpEntraEntidad.setFecModiRegi(tpEntraDto.getFecModiRegi());
        	tpEntraEntidad.setUsuApliModi(tpEntraDto.getUsuApliModi());
        }

        tpEntraEntidad.setTitEntr(tpEntraDto.getTitEntr());
        tpEntraEntidad.setCatEntr(tpEntraDto.getCatEntr());
        tpEntraEntidad.setConEntr(tpEntraDto.getConEntr());
        tpEntraEntidad.setImaEntrPrev(tpEntraDto.getImaEntrPrev());
        tpEntraEntidad.setImaEntrCont(tpEntraDto.getImaEntrCont());
        tpEntraEntidad.setNomImaCont(tpEntraDto.getNomImaCont());
        tpEntraEntidad.setNomImaPrev(tpEntraDto.getNomImaPrev());
        tpEntraEntidad.setEnlEntr(tpEntraDto.getEnlEntr());
        tpEntraEntidad.setIndEsta(tpEntraDto.getIndEsta());
        tpEntraEntidad.setFecCreaRegi(tpEntraDto.getFecCreaRegi());
        tpEntraEntidad.setUsuApliCrea(tpEntraDto.getUsuApliCrea());

        String result = daoEntra.insertUpdate(tpEntraEntidad);

        return result;
        
	}

	@Override
	public TpEntraDto getEntrada(TpEntraDto tpEntraDto) {
		
		TpEntra entidad = new TpEntra();
		TpEntraDto resultado = null;

		if(!ValidacionesString.esNuloOVacio(tpEntraDto.getEnlEntr())) {
			entidad.setEnlEntr(tpEntraDto.getEnlEntr());
		}
		
		Object[] temporal = daoEntra.getEntrada(entidad);

		if (temporal != null) {
			
			resultado = new TpEntraDto();
			
			resultado.setCodEntr((Integer) temporal[0]);
			resultado.setTitEntr((String) temporal[1]);
			resultado.setCatEntr((String) temporal[2]);
			resultado.setImaEntrPrev((String) temporal[3]);			
			resultado.setImaEntrCont((String) temporal[4]);
			resultado.setNomImaPrev((String) temporal[5]);
			resultado.setNomImaCont((String) temporal[6]);
			resultado.setConEntr((String) temporal[7]);
			resultado.setEnlEntr((String) temporal[8]);
			resultado.setIndEsta((Integer) temporal[9]);
			resultado.setUsuApliCrea((String) temporal[10]);
			resultado.setFecCreaRegi((Date) temporal[11]);
			resultado.setUsuApliModi((String) temporal[12]);
			resultado.setFecModiRegi((Date) temporal[13]);
			resultado.setFecCreaFormPrev(FechaUtil.formatoFecha(resultado.getFecCreaRegi()));

		}

		return resultado;
	}

	@Override
	public List<TpEntraDto> getUltimasEntradas(Integer numeroDeEntradas) throws IOException {
		
		List<TpEntraDto> resultado = new LinkedList<TpEntraDto>() ;
		
		List<Object[]> listaTemporal = daoEntra.getUltimasEntradas(numeroDeEntradas); 
		
		for(Object[] temp : listaTemporal) {
			
			TpEntraDto dto = new TpEntraDto();

			dto.setCodEntr((Integer) temp[0]);
			dto.setTitEntr((String) temp[1]);
			dto.setCatEntr((String) temp[2]);
			dto.setImaEntrPrev((String) temp[3]);			
			dto.setImaEntrCont((String) temp[4]);
			dto.setNomImaPrev((String) temp[5]);
			dto.setNomImaCont((String) temp[6]);
			dto.setConEntr((String) temp[7]);
			dto.setEnlEntr((String) temp[8]);
			dto.setIndEsta((Integer) temp[9]);
			dto.setUsuApliCrea((String) temp[10]);
			dto.setFecCreaRegi((Date) temp[11]);
			dto.setUsuApliModi((String) temp[12]);
			dto.setFecModiRegi((Date) temp[13]);
			dto.setFecCreaFormPrev(FechaUtil.formatoFecha(dto.getFecCreaRegi()));
			
			resultado.add(dto);
		}
		
		return resultado;
	}
    
}
