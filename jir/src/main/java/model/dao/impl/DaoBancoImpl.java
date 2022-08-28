package model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.entidades.TpBanco;
import hibernate.entidades.TpDivis;
import hibernate.entidades.TpTipoCuent;
import loggerUtil.LoggerUtil;
import model.dao.DaoBanco;
import numeros.util.ValidacionesNumeros;
import util.types.CadenasType;
import util.types.NumerosType;
import util.types.RegistroActivoType;

public class DaoBancoImpl implements DaoBanco {

    private Session session;
    private Transaction tx;

    public DaoBancoImpl(Session session) {
        this.session = session;
        tx = this.session.beginTransaction();
    }

//	public void closeSesion() {
//		session.close();
//	}
	
	@Override
	public List<TpBanco> getBancosDisponibles(TpBanco tpBanco) {
		
        StringBuilder sb = new StringBuilder();
        sb.append("select tb from TpBanco tb");
        sb.append(" where tb.indEsta = :indEsta");
        
        if(!ValidacionesNumeros.esCeroONuloEntero(tpBanco.getIndVistClie())) {
        	sb.append(" and tb.indVistClie = :indVistClie");
        }
        
        if(!ValidacionesNumeros.esCeroONuloEntero(tpBanco.getIndVistAdmi())) {
        	sb.append(" and tb.indVistAdmi = :indVistAdmi");
        }
        
        Query<TpBanco> query = session.createQuery(sb.toString());
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        
        if(!ValidacionesNumeros.esCeroONuloEntero(tpBanco.getIndVistClie())) {
        	 query.setParameter("indVistClie", tpBanco.getIndVistClie() );
        }
        
        if(!ValidacionesNumeros.esCeroONuloEntero(tpBanco.getIndVistAdmi())) {
        	 query.setParameter("indVistAdmi", tpBanco.getIndVistAdmi());
        }
        
        List<TpBanco> list = query.list();

        session.close();
        
        return list;
	}

	@Override
	public List<TpDivis> getDivisasCuentasDisponibles(TpDivis TpDivis) {
		
        StringBuilder sb = new StringBuilder();
        sb.append("select td from TpDivis td");
        sb.append(" where td.indEsta = :indEsta");
        sb.append(" and td.indApliCuenBanc = :indApliCuenBanc");
        
        Query<TpDivis> query = session.createQuery(sb.toString());
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        query.setParameter("indApliCuenBanc", TpDivis.getIndApliCuenBanc());
        List<TpDivis> list = query.list();

        session.close();
        
        return list;
	}

	@Override
	public List<TpTipoCuent> getTipoCuentasBacarias(TpTipoCuent tpTipoCuent) {
		StringBuilder sb = new StringBuilder();
        sb.append("select tc from TpTipoCuent tc");
        sb.append(" where tc.indEsta = :indEsta");
        
        Query<TpTipoCuent> query = session.createQuery(sb.toString());
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        List<TpTipoCuent> list = query.list();

        session.close();
        
        return list;
	}

	@Override
	public List<Object[]> getBancos(TpBanco tpBanco) {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("select tpb.codBanc, tpb.codCortBanc, tpb.nomBanc, tpb.indVistClie, tpb.indVistAdmi, tpb.indTienCuenNego,");
        sb.append(" tpb.indEsta, tpb.usuApliCrea, tpb.fecCreaRegi, tpb.usuApliModi, tpb.fecModiRegi ");
        sb.append(" from TpBanco tpb");
        sb.append(" where 1=1 ");
        sb.append(" order by tpb.nomBanc asc");
        
        if(tpBanco != null && !ValidacionesNumeros.esCeroONuloEntero(tpBanco.getCodBanc())) {
            sb.append(" and tpb.codBanc = :codBanco ");
        }
        
        Query query = session.createQuery(sb.toString());
      
        if(tpBanco != null && !ValidacionesNumeros.esCeroONuloEntero(tpBanco.getCodBanc())) {
        	query.setParameter("codBanco", tpBanco.getCodBanc());      	        	
        }

        List<Object[]> list = query.list();
        
        session.close();

        return list;
	}

	@Override
	public String insertUpdate(TpBanco tpBanco) {
		
		String result = null;
        
	     Integer codBanc = NumerosType.NUMERO_MINIMO_CERO.getValor();

	     try {
	        	
	    	if(	tpBanco.getCodBanc() != null) {
	            session.update(tpBanco);
	            result = CadenasType.INDICADOR_PROCESO_OK.getValor()+CadenasType.GUION.getValor()+"U";
	        } else {
	        	codBanc = (Integer) session.save(tpBanco);
	        	if(!ValidacionesNumeros.esCeroONuloEntero(codBanc)) {
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
