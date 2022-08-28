package model.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.entidades.TpSuscr;
import loggerUtil.LoggerUtil;
import model.dao.DaoSuscripcion;
import numeros.util.ValidacionesNumeros;
import util.types.CadenasType;
import util.types.NumerosType;

public class DaoSuscripcionImpl implements DaoSuscripcion {

    private Session session;
    private Transaction tx;

    public DaoSuscripcionImpl(Session session) {
        this.session = session;
        tx = this.session.beginTransaction();
    }

	@Override
	public String insertUpdate(TpSuscr tpSuscr) {
		
		String result = null;
        
	     Integer codSusc = NumerosType.NUMERO_MINIMO_CERO.getValor();

	     try {
	        	
	    	if(	tpSuscr.getCodSusc() != null) {
	            session.update(tpSuscr);
	            result = CadenasType.INDICADOR_PROCESO_OK.getValor()+CadenasType.GUION.getValor()+"U";
	        } else {
	        	codSusc = (Integer) session.save(tpSuscr);
	        	if(!ValidacionesNumeros.esCeroONuloEntero(codSusc)) {
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
