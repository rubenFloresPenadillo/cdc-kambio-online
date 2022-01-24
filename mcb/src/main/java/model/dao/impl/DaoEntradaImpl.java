package model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cadenas.util.ValidacionesString;
import hibernate.entidades.TpEntra;
import hibernate.entidades.TpOperaClien;
import loggerUtil.LoggerUtil;
import model.dao.DaoEntrada;
import numeros.util.ValidacionesNumeros;
import util.types.CadenasType;
import util.types.NumerosType;
import util.types.RegistroActivoType;

public class DaoEntradaImpl implements DaoEntrada {

    private Session session;
    private Transaction tx;

    public DaoEntradaImpl(Session session) {
        this.session = session;
        tx = this.session.beginTransaction();
    }

	@Override
	public List<TpEntra> getEntradasDisponibles(TpEntra TpEntra) {
		
        StringBuilder sb = new StringBuilder();
        sb.append("select tb from TpEntra tb");
        sb.append(" where tb.indEsta = :indEsta");
        
//        if(!ValidacionesNumeros.esCeroONuloEntero(TpEntra.getIndVistClie())) {
//        	sb.append(" and tb.indVistClie = :indVistClie");
//        }
//        
//        if(!ValidacionesNumeros.esCeroONuloEntero(TpEntra.getIndVistAdmi())) {
//        	sb.append(" and tb.indVistAdmi = :indVistAdmi");
//        }
        
        Query<TpEntra> query = session.createQuery(sb.toString());
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        
//        if(!ValidacionesNumeros.esCeroONuloEntero(TpEntra.getIndVistClie())) {
//        	 query.setParameter("indVistClie", TpEntra.getIndVistClie() );
//        }
//        
//        if(!ValidacionesNumeros.esCeroONuloEntero(TpEntra.getIndVistAdmi())) {
//        	 query.setParameter("indVistAdmi", TpEntra.getIndVistAdmi());
//        }
        
        List<TpEntra> list = query.list();

        session.close();
        
        return list;
	}

	@Override
	public List<Object[]> getEntradas(TpEntra tpEntra) {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("select te.codEntr, te.titEntr, te.catEntr, te.imaEntrPrev, te.imaEntrCont, te.nomImaPrev, te.nomImaCont, te.conEntr, te.enlEntr,");
        sb.append(" te.indEsta, te.usuApliCrea, te.fecCreaRegi, te.usuApliModi, te.fecModiRegi ");
        sb.append(" from TpEntra te");
        sb.append(" where 1=1 ");
        sb.append(" order by te.titEntr asc");
        
        if(tpEntra != null && !ValidacionesNumeros.esCeroONuloEntero(tpEntra.getCodEntr())) {
            sb.append(" and te.codEntr = :codEntr ");
        }
        
        Query query = session.createQuery(sb.toString());
      
        if(tpEntra != null && !ValidacionesNumeros.esCeroONuloEntero(tpEntra.getCodEntr())) {
        	query.setParameter("codEntr", tpEntra.getCodEntr());      	        	
        }

        List<Object[]> list = query.list();
        
        session.close();

        return list;
	}

	@Override
	public String insertUpdate(TpEntra TpEntra) {
		
		String result = null;
        
	     Integer codEntra = NumerosType.NUMERO_MINIMO_CERO.getValor();

	     try {
	        	
	    	if(	TpEntra.getCodEntr() != null) {
	            session.update(TpEntra);
	            result = CadenasType.INDICADOR_PROCESO_OK.getValor()+CadenasType.GUION.getValor()+"U";
	        } else {
	        	codEntra = (Integer) session.save(TpEntra);
	        	if(!ValidacionesNumeros.esCeroONuloEntero(codEntra)) {
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
	public Object[] getEntrada(TpEntra tpEntra) {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("select te.codEntr, te.titEntr, te.catEntr, te.imaEntrPrev, te.imaEntrCont, te.nomImaPrev, te.nomImaCont, te.conEntr, te.enlEntr,");
        sb.append(" te.indEsta, te.usuApliCrea, te.fecCreaRegi, te.usuApliModi, te.fecModiRegi ");
        sb.append(" from TpEntra te");
        sb.append(" where 1=1 ");
        sb.append(" and te.indEsta = :indEsta");
       

		if(!ValidacionesString.esNuloOVacio(tpEntra.getEnlEntr())) {
			sb.append(" and te.enlEntr = :enlEntr ");
		}

		Query query = session.createQuery(sb.toString());
		
		query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
		 
		if(!ValidacionesString.esNuloOVacio(tpEntra.getEnlEntr())) {
			query.setParameter("enlEntr", tpEntra.getEnlEntr());
		}

		sb.append(" order by te.titEntr asc");
		 
		Object[] resultado = (Object[]) query.uniqueResult();
		
		session.close();
        
		return resultado;
        
	}

	@Override
	public List<Object[]> getUltimasEntradas(Integer numeroDeEntradas) {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("select te.codEntr, te.titEntr, te.catEntr, te.imaEntrPrev, te.imaEntrCont, te.nomImaPrev, te.nomImaCont, te.conEntr, te.enlEntr,");
        sb.append(" te.indEsta, te.usuApliCrea, te.fecCreaRegi, te.usuApliModi, te.fecModiRegi ");
        sb.append(" from TpEntra te");
        sb.append(" where 1=1 ");
        sb.append(" order by fecCreaRegi desc");

        
        Query query = session.createQuery(sb.toString());
      
        query.setMaxResults(numeroDeEntradas);

        List<Object[]> list = query.list();
        
        session.close();

        return list;
	}

}
