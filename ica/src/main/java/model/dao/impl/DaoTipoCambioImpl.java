package model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.entidades.TpTipoCambi;
import loggerUtil.LoggerUtil;
import model.dao.DaoTipoCambio;
import numeros.util.ValidacionesNumeros;
import util.types.CadenasType;
import util.types.NumerosType;

public class DaoTipoCambioImpl implements DaoTipoCambio {

    private Session session;
    private Transaction tx;

    public DaoTipoCambioImpl(Session session) {
        this.session = session;
        tx = this.session.beginTransaction();
    }

	@Override
	public void closeSesion() {
		session.close();
	}

	@Override
	public TpTipoCambi getTipoCambio(TpTipoCambi tpTipoCambi) {
		TpTipoCambi tpTipoCambiResultado = null;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("from TpTipoCambi ttc where 1=1 ");
		
		if(!ValidacionesNumeros.esCeroONuloEntero(tpTipoCambi.getTpDivisByCodDivi().getCodDivi())) {
			sb.append(" and ttc.tpDivisByCodDivi.codDivi = :codDivi");
		}
		
		if(!ValidacionesNumeros.esCeroONuloEntero(tpTipoCambi.getTpDivisByCodDiviCamb().getCodDivi())) {
			sb.append(" and ttc.tpDivisByCodDiviCamb.codDivi = :codDiviCamb ");
		}
		
		Query query = session.createQuery(sb.toString());
		
		if(!ValidacionesNumeros.esCeroONuloEntero(tpTipoCambi.getTpDivisByCodDivi().getCodDivi())) {
			query.setParameter("codDivi", tpTipoCambi.getTpDivisByCodDivi().getCodDivi());
		}
		
		if(!ValidacionesNumeros.esCeroONuloEntero(tpTipoCambi.getTpDivisByCodDiviCamb().getCodDivi())) {
			query.setParameter("codDiviCamb", tpTipoCambi.getTpDivisByCodDiviCamb().getCodDivi());
		}
		
		tpTipoCambiResultado = (TpTipoCambi) query.uniqueResult();
		
		LoggerUtil.getInstance().getLogger().info("tpTipoCambiResultado "+tpTipoCambiResultado);
		
		session.close();
		
        return tpTipoCambiResultado;
	}

	@Override
	public List<Object[]> getTipoCambios(TpTipoCambi tpTipoCambi) {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("select ttc.codTipoCamb, ttc.tpDivisByCodDivi.codDivi, ttc.tpDivisByCodDiviCamb.codDivi, ttc.tpDivisByCodDivi.nomDiviSing, ttc.tpDivisByCodDiviCamb.nomDiviSing, ttc.valCambComp, ttc.valCambVent, ");
        sb.append(" ttc.indEsta, ttc.usuApliCrea, ttc.fecCreaRegi, ttc.usuApliModi, ttc.fecModiRegi ");
        sb.append(" from TpTipoCambi ttc");
        sb.append(" where 1=1 ");
        sb.append(" order by ttc.tpDivisByCodDivi.nomDiviSing  asc");
        
        if(tpTipoCambi != null && !ValidacionesNumeros.esCeroONuloEntero(tpTipoCambi.getCodTipoCamb())) {
            sb.append(" and ttc.codTipoCamb = :codTipoCamb ");
        }
        
        Query query = session.createQuery(sb.toString());
        
        if(tpTipoCambi != null && !ValidacionesNumeros.esCeroONuloEntero(tpTipoCambi.getCodTipoCamb())) {
        	query.setParameter("codTipoCamb", tpTipoCambi.getCodTipoCamb());      	        	
        }

        List<Object[]> list = query.list();
        
        session.close();

        return list;
	}

	@Override
	public String insertUpdate(TpTipoCambi tpTipoCambi) {
		
		String result = null;
        
	     Integer codDivi = NumerosType.NUMERO_MINIMO_CERO.getValor();

	     try {
	        	
	    	if(	tpTipoCambi.getCodTipoCamb() != null) {
	            session.update(tpTipoCambi);
	            result = CadenasType.INDICADOR_PROCESO_OK.getValor()+CadenasType.GUION.getValor()+"U";
	        } else {
	        	codDivi = (Integer) session.save(tpTipoCambi);
	        	if(!ValidacionesNumeros.esCeroONuloEntero(codDivi)) {
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

	@Override
	public List<Object[]> getTipoCambiosVariacion(Integer cantidadResultados) {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("select ttc.fecModiRegi, avg(ttc.valCambComp), avg(ttc.valCambVent)  ");
        sb.append(" from AudiTpTipoCambi ttc");
        sb.append(" where ttc.fecModiRegi is not null ");
        sb.append(" group by ttc.fecModiRegi");
        sb.append(" order by ttc.fecModiRegi desc");
              
        Query query = session.createQuery(sb.toString());
        query.setMaxResults(cantidadResultados);
        List<Object[]> list = query.list();
        
        session.close();

        return list;
	}


}
