package model.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cadenas.util.ValidacionesString;
import dto.TpCuponDto;
import hibernate.entidades.TpCupon;
import loggerUtil.LoggerUtil;
import model.dao.DaoCupon;
import numeros.util.ValidacionesNumeros;
import util.types.CadenasType;
import util.types.NumerosType;

public class DaoCuponImpl implements DaoCupon {

    private Session session;
    private Transaction tx;

    public DaoCuponImpl(Session session) {
        this.session = session;
        tx = this.session.beginTransaction();
    }

//	public void closeSesion() {
//		session.close();
//	}
	
	@Override
	public List<TpCupon> getCuponesDisponibles(TpCupon tpCupon) {
		
        StringBuilder sb = new StringBuilder();
        sb.append("select tb from TpCupon tb");

        Query<TpCupon> query = session.createQuery(sb.toString());
        
        List<TpCupon> list = query.list();

        session.close();
        
        return list;
	}


	@Override
	public List<Object[]> getCupones(TpCuponDto tpCuponDto) {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("select tpcu.codCupo, tpcu.codTipoPersApli, tpcu.codTipoOperApli, tpcu.emaClieCupo, tpcu.nomCupo, ");
        sb.append(" tpcu.fecInicVige, tpcu.fecFinaVige, tpcu.canCupo, tpcu.monDescCupo, tpcu.indReutClie, ");
        sb.append(" tpcu.indEsta, tpcu.usuApliCrea, tpcu.fecCreaRegi, tpcu.usuApliModi, tpcu.fecModiRegi ");
        sb.append(" from TpCupon tpcu");
        sb.append(" where 1=1 ");
        
        
        if(tpCuponDto != null && !ValidacionesNumeros.esCeroONuloEntero(tpCuponDto.getIndEsta())) {
            sb.append(" and tpcu.indEsta = :indEsta ");
        }		
        		
        if(tpCuponDto != null && !ValidacionesNumeros.esCeroONuloEntero(tpCuponDto.getCodCupo())) {
            sb.append(" and tpcu.codCupo = :codCupo ");
        }
        
        if(tpCuponDto != null && !ValidacionesString.esNuloOVacio(tpCuponDto.getNomCupo())) {
            sb.append(" and tpcu.nomCupo = :nomCupo ");
        }
        
        if(tpCuponDto != null &&  tpCuponDto.getFecVigeFilt() != null) {
        	sb.append(" and DATE_TRUNC('D', tpcu.fecFinaVige) >= :fechaActual and DATE_TRUNC('D', tpcu.fecInicVige) <= :fechaActual ");
        }
        
        if(tpCuponDto != null &&  tpCuponDto.getEmaClieCupo() != null) {
        	sb.append(" and ( tpcu.emaClieCupo = :emaClieCupo or (tpcu.emaClieCupo IS NULL or tpcu.emaClieCupo = '') ) ");
        }
        
        if(tpCuponDto != null &&  tpCuponDto.getIndFiltCantCupo() != null) {
        	sb.append(" and tpcu.canCupo > 0 ");
        }

        sb.append(" order by tpcu.indEsta desc, tpcu.nomCupo asc ");
        
        Query query = session.createQuery(sb.toString());
        
        if(tpCuponDto != null && !ValidacionesNumeros.esCeroONuloEntero(tpCuponDto.getIndEsta())) {
        	query.setParameter("indEsta", tpCuponDto.getIndEsta());	        	
        }
        
        if(tpCuponDto != null && !ValidacionesNumeros.esCeroONuloEntero(tpCuponDto.getCodCupo())) {
        	query.setParameter("codCupo", tpCuponDto.getCodCupo());      	        	
        }
        
        if(tpCuponDto != null && !ValidacionesString.esNuloOVacio(tpCuponDto.getNomCupo())) {
        	query.setParameter("nomCupo", tpCuponDto.getNomCupo()); 
        }

        if(tpCuponDto != null &&  tpCuponDto.getFecVigeFilt() != null) {
        	query.setParameter("fechaActual", tpCuponDto.getFecVigeFilt()); 
        }
        
        if(tpCuponDto != null &&  tpCuponDto.getEmaClieCupo() != null) {
        	query.setParameter("emaClieCupo", tpCuponDto.getEmaClieCupo().toUpperCase().trim()); 
        }
        
        List<Object[]> list = query.list();
        
        session.close();

        return list;
	}

	@Override
	public String insertUpdate(TpCupon tpCupon) {
		
		String result = null;
        
	     Integer codCupon = NumerosType.NUMERO_MINIMO_CERO.getValor();

	     try {
	        	
	    	if(	tpCupon.getCodCupo() != null) {
	            session.update(tpCupon);
	            result = CadenasType.INDICADOR_PROCESO_OK.getValor()+CadenasType.GUION.getValor()+"U";
	        } else {
	        	codCupon = (Integer) session.save(tpCupon);
	        	if(!ValidacionesNumeros.esCeroONuloEntero(codCupon)) {
	        		result = CadenasType.INDICADOR_PROCESO_OK.getValor()+CadenasType.GUION.getValor()+"S";
	        	}
	        	
	        }

	        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
	        		tx.commit();
	        }else {
	        		tx.rollback();
	        }
	        
	        session.close();
	            
	    } catch (Exception e ) {
	        result = e.getMessage();
	        tx.rollback();
	        session.close();
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
	    }

	    return result;
	}

	

}
