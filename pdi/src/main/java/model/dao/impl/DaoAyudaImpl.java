package model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.entidades.TpAyudaPregu;
import hibernate.entidades.TpBanco;
import hibernate.entidades.TpDivis;
import loggerUtil.LoggerUtil;
import model.dao.DaoAyuda;
import numeros.util.ValidacionesNumeros;
import util.types.CadenasType;
import util.types.NumerosType;
import util.types.RegistroActivoType;

public class DaoAyudaImpl implements DaoAyuda {

    private Session session;
    private Transaction tx;

    public DaoAyudaImpl(Session session) {
        this.session = session;
        tx = this.session.beginTransaction();
    }


	@Override
	public List<TpAyudaPregu> getPreguntasDisponibles(TpAyudaPregu tpAyudaPregu) {
		
		StringBuilder sb = new StringBuilder();
        sb.append("select tap from TpAyudaPregu tap");
        sb.append(" where tap.indEsta = :indEsta");
        sb.append(" order by tpTipoPregu.codTipoPreg, valNumePosi ");
        
        
        Query<TpAyudaPregu> query = session.createQuery(sb.toString());
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());

        List<TpAyudaPregu> list = query.list();

//        session.close();
        
        return list;
	}

	@Override
	public String insertUpdate(TpAyudaPregu tpAyudaPregu) {
		
		 String result = null;
	        
	     Integer codAyudPreg = NumerosType.NUMERO_MINIMO_CERO.getValor();

	     try {
	        	
	    	if(	tpAyudaPregu.getCodAyudPreg() != null) {
	            session.update(tpAyudaPregu);
	            result = CadenasType.INDICADOR_PROCESO_OK.getValor()+CadenasType.GUION.getValor()+"U";
	        } else {
	        	codAyudPreg = (Integer) session.save(tpAyudaPregu);
	        	if(!ValidacionesNumeros.esCeroONuloEntero(codAyudPreg)) {
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
