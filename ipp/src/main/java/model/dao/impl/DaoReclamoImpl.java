package model.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.entidades.TpReclaQueja;
import loggerUtil.LoggerUtil;
import model.dao.DaoReclamo;
import numeros.util.ValidacionesNumeros;
import util.types.CadenasType;
import util.types.NumerosType;

public class DaoReclamoImpl implements DaoReclamo {

    private Session session;
    private Transaction tx;

    public DaoReclamoImpl(Session session) {
        this.session = session;
        tx = this.session.beginTransaction();
    }


	@Override
	public String insertUpdate(TpReclaQueja tpReclaQueja) {
		
		String result = null;
        
	     Integer codReclQuej = NumerosType.NUMERO_MINIMO_CERO.getValor();

	     try {
	        	
	    	if(	tpReclaQueja.getCodReclQuej() != null) {
	            session.update(tpReclaQueja);
	            result = CadenasType.INDICADOR_PROCESO_OK.getValor()+CadenasType.GUION.getValor()+"U";
	        } else {
	        	codReclQuej = (Integer) session.save(tpReclaQueja);
	        	if(!ValidacionesNumeros.esCeroONuloEntero(codReclQuej)) {
	        		result = CadenasType.INDICADOR_PROCESO_OK.getValor()+CadenasType.GUION.getValor()+"S"+CadenasType.GUION.getValor()+codReclQuej;
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
