package model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cadenas.util.ValidacionesString;
import hibernate.entidades.TpParamGener;
import loggerUtil.LoggerUtil;
import model.dao.DaoParametroGeneral;
import numeros.util.ValidacionesNumeros;
import util.types.CadenasType;
import util.types.NumerosType;

public class DaoParametroGeneralImpl implements DaoParametroGeneral {

    private Session session;
    private Transaction tx;

    public DaoParametroGeneralImpl(Session session) {
        this.session = session;
        tx = this.session.beginTransaction();
    }

	@Override
	public List<Object[]> getParametrosGenerales(TpParamGener tpParamGener) {
		
		StringBuilder sb = new StringBuilder();
        sb.append("select tpg.codParaGene, tpg.nomParaGene, tpg.valParaGene, tpg.desValoParaGene,  ");
        sb.append(" tpg.indEsta, tpg.usuApliCrea, tpg.fecCreaRegi, tpg.usuApliModi, tpg.fecModiRegi ");
        sb.append(" from TpParamGener tpg");
        sb.append(" where 1=1 ");

        if(tpParamGener != null && !ValidacionesNumeros.esCeroONuloEntero(tpParamGener.getCodParaGene())) {
            sb.append(" and tpg.codParaGene = :codParaGene ");        	
        }
        
        if(tpParamGener != null && !ValidacionesString.esNuloOVacio(tpParamGener.getNomParaGene())) {
            sb.append(" and tpg.nomParaGene = :nomParaGene ");        	
        }
        
        if(tpParamGener != null && !ValidacionesNumeros.esCeroONuloEntero(tpParamGener.getIndEsta())) {
            sb.append(" and tpg.indEsta = :indEsta ");        	
        }
        
        sb.append(" order by tpg.nomParaGene asc");
        
        Query query = session.createQuery(sb.toString());
        
        if(tpParamGener != null && !ValidacionesNumeros.esCeroONuloEntero(tpParamGener.getCodParaGene())) {
        	query.setParameter("codParaGene", tpParamGener.getCodParaGene());      	        	
        }
        
        if(tpParamGener != null && !ValidacionesString.esNuloOVacio(tpParamGener.getNomParaGene())) {
        	query.setParameter("nomParaGene", tpParamGener.getNomParaGene());      	        	
        }
        
        if(tpParamGener != null && !ValidacionesNumeros.esCeroONuloEntero(tpParamGener.getIndEsta())) {
        	query.setParameter("indEsta", tpParamGener.getIndEsta()); 	
        }

        List<Object[]> list = query.list();
        
        session.close();

        return list;
	}

	@Override
	public String insertUpdate(TpParamGener tpParamGener) {

		 String result = null;
	        
	     Integer codParaGene = NumerosType.NUMERO_MINIMO_CERO.getValor();

	     try {
	        	
	    	if(	tpParamGener.getCodParaGene() != null) {
	            session.update(tpParamGener);
	            result = CadenasType.INDICADOR_PROCESO_OK.getValor()+CadenasType.GUION.getValor()+"U";
	        } else {
	        	codParaGene = (Integer) session.save(tpParamGener);
	        	if(!ValidacionesNumeros.esCeroONuloEntero(codParaGene)) {
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
