package model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.entidades.TpDivis;
import loggerUtil.LoggerUtil;
import model.dao.DaoDivisa;
import numeros.util.ValidacionesNumeros;
import util.types.CadenasType;
import util.types.NumerosType;
import util.types.RegistroActivoType;

public class DaoDivisImpl implements DaoDivisa {

    private Session session;
    private Transaction tx;

    public DaoDivisImpl(Session session) {
        this.session = session;
        tx = this.session.beginTransaction();
    }

	@Override
	public List<Object[]> getDivisas(TpDivis tpDivis) {
		
		StringBuilder sb = new StringBuilder();
        sb.append("select tpd.codDivi, tpd.codIsoDivi, tpd.nomDiviSing, tpd.nomDiviPlur, tpd.simDivi, tpd.indApliCuenBanc, tpd.indEsta, tpd.usuApliCrea, tpd.fecCreaRegi, tpd.usuApliModi, tpd.fecModiRegi  ");
        sb.append(" from TpDivis tpd");
        sb.append(" where 1=1 ");
        sb.append(" order by tpd.nomDiviSing asc");
        
        if(tpDivis != null && !ValidacionesNumeros.esCeroONuloEntero(tpDivis.getCodDivi())) {
            sb.append(" and tpd.codDivi = :codDivi ");        	
        }
        
        Query query = session.createQuery(sb.toString());
        
        if(tpDivis != null && !ValidacionesNumeros.esCeroONuloEntero(tpDivis.getCodDivi())) {
        	query.setParameter("codDivi", tpDivis.getCodDivi());      	        	
        }

        List<Object[]> list = query.list();
        
        session.close();

        return list;
	}

	@Override
	public String insertUpdate(TpDivis tpDivis) {
		
		 String result = null;
	        
	     Integer codDivi = NumerosType.NUMERO_MINIMO_CERO.getValor();

	     try {
	        	
	    	if(	tpDivis.getCodDivi() != null) {
	            session.update(tpDivis);
	            result = CadenasType.INDICADOR_PROCESO_OK.getValor()+CadenasType.GUION.getValor()+"U";
	        } else {
	        	codDivi = (Integer) session.save(tpDivis);
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

    

}
