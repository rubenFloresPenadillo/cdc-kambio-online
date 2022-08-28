package model.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cadenas.util.ValidacionesString;
import hibernate.entidades.TpClien;
import hibernate.entidades.TpTipoDocumPerso;
import hibernate.entidades.TpTipoPerso;
import hibernate.entidades.TpUsuar;
import loggerUtil.LoggerUtil;
import model.dao.DaoUsuario;
import numeros.util.ValidacionesNumeros;
import util.types.CadenasType;
import util.types.EstadosCuentaUsuarioType;
import util.types.NumerosType;
import util.types.RegistroActivoType;

public class DaoUsuarioImpl implements DaoUsuario {

    private Session session;
    private Transaction tx;

    public DaoUsuarioImpl(Session session) {
        this.session = session;
        tx = this.session.beginTransaction();
    }

    @Override
	public String insertUpdate(TpUsuar tpUsuar) {
        String result = null;

        try {
        	
        	TpTipoPerso tpTipoPersoEntidad =  (TpTipoPerso) session.get(TpTipoPerso.class, tpUsuar.getTpTipoPerso().getCodTipoPers());
        	
        	tpUsuar.setTpTipoPerso(tpTipoPersoEntidad);
        	
            session.saveOrUpdate(tpUsuar);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            result = e.getMessage();
	        LoggerUtil.getInstance().getLogger().error("Error en DaoUsuarioImpl.insertUpdate");
	        LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
            tx.rollback();
            session.close();
        }

        return result;
	}
	
	@Override
	public TpUsuar get(Integer codUsua) {
		TpUsuar tpUsuar =  (TpUsuar) session.get(TpUsuar.class, codUsua);
        return tpUsuar;
	}

	@Override
	public TpUsuar getUsuario (TpUsuar tpUsuar) {
		
		LoggerUtil.getInstance().getLogger().info("Usuario: "+tpUsuar.getIdeUsuaEmai());
		LoggerUtil.getInstance().getLogger().info("Clave: "+tpUsuar.getCodClav());
		
		TpUsuar tpUsuarResultado = null;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("from TpUsuar where 1=1 ");
		
		if(!ValidacionesString.esNuloOVacio(tpUsuar.getIdeUsuaEmai())) {
			sb.append("and upper(ideUsuaEmai) = :ideUsuaEmai ");
		}
		
		if(!ValidacionesString.esNuloOVacio(tpUsuar.getCodClav())) {
			sb.append("and codClav = :codClav ");
		}
		
		Query query = session.createQuery(sb.toString());
		
		if(!ValidacionesString.esNuloOVacio(tpUsuar.getIdeUsuaEmai())) {
			query.setParameter("ideUsuaEmai", tpUsuar.getIdeUsuaEmai().toUpperCase());
		}
		
		if(!ValidacionesString.esNuloOVacio(tpUsuar.getCodClav())) {
			query.setParameter("codClav", tpUsuar.getCodClav());
		}
		
		tpUsuarResultado = (TpUsuar) query.uniqueResult();
		
		session.close();
		
        return tpUsuarResultado;
		
	}
	
	@Override
	public void closeSesion() {
		session.close();
	}

	@Override
	public List<TpTipoPerso> getTipoPersona() {
		
        StringBuilder sb = new StringBuilder();
        sb.append("select ttp from TpTipoPerso ttp");
        sb.append(" where ttp.indEsta = :indEsta ");
        
        Query<TpTipoPerso> query = session.createQuery(sb.toString());
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        List<TpTipoPerso> list = query.list();
        
        session.close();

        return list;
	}

	@Override
	public Object[] getOperacionEnCurso(Integer codigoUsuario) {
		
		StringBuilder sb = new StringBuilder();
        sb.append("select tpu.codOperClie, tpu.codEstaOper");
        sb.append(" from TpUsuar tpu");
        sb.append(" where tpu.indEsta = :indEsta ");
        sb.append(" and tpu.codUsua = :codUsua ");
        
        Query query = session.createQuery(sb.toString());
        
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        query.setParameter("codUsua", codigoUsuario);
    
        Object[] resultado = (Object[]) query.uniqueResult();

        session.close();
        
        return resultado;
	}

	@Override
	public List<Object[]> getUsuarios(TpClien tpClien) {
		
		StringBuilder sb = new StringBuilder();
        sb.append("select tpu.codUsua, tpu.tpTipoPerso.codTipoPers, tpu.tpTipoPerso.desTipoPers,  ");
		sb.append(" tpu.ideUsuaEmai, tpu.emaUsuaAuxi, tpu.codClav, tpu.valNomb, tpu.indCompDato, tpu.codClie, tpu.codEstaOper, tpu.codOperClie, tpu.codPerfUsua, ");
		sb.append(" tc.valPrimNombPers, tc.valSeguNombPers, tc.valPrimApelPers, tc.valSeguApelPers, tc.valRazoSociPers, tpu.codEstaCuenUsua, ");
        sb.append(" tpu.indEsta, tpu.usuApliCrea, tpu.fecCreaRegi, tpu.usuApliModi, tpu.fecModiRegi ");
        sb.append(" from TpUsuar tpu left join TpClien tc on tc.codClie = tpu.codClie");
        sb.append(" where 1=1 ");
        sb.append(" order by tpu.codPerfUsua desc, tpu.ideUsuaEmai");
        
		if(tpClien!=null && ValidacionesNumeros.esCeroONuloEntero(tpClien.getTpUsuar().getCodPerfUsua())) {
			sb.append(" and tpu.codPerfUsua = :codPerfUsua ");
		}
        
        Query query = session.createQuery(sb.toString());
        
		if(tpClien!=null && ValidacionesNumeros.esCeroONuloEntero(tpClien.getTpUsuar().getCodPerfUsua())) {
	        query.setParameter("codPerfUsua", tpClien.getTpUsuar().getCodPerfUsua());
		}
		


        List<Object[]> list = query.list();
        
        session.close();

        return list;
	}

	@Override
	public String updateCuenta(TpUsuar tpUsuar) {
		
		String result = null;

        try {
        		
        		StringBuilder sb = new StringBuilder();
                sb.append(" update TpUsuar ");
                sb.append("set codEstaCuenUsua = :codEstaCuenUsua,");
                sb.append(" valTokeCuen = null, ");
                sb.append(" fecCreaToke = null, ");
                sb.append(" usuApliModi = :usuApliModi, ");
                sb.append(" fecModiRegi = :fecModiRegi ");
                sb.append(" where valTokeCuen = :valTokeCuen ");
                sb.append(" and ideUsuaEmai = :ideUsuaEmai ");
//                sb.append(" and codUsua = :codUsua ");
                
        		Query query = session.createQuery(sb.toString());
        		query.setParameter("usuApliModi", tpUsuar.getUsuApliModi());
        		query.setParameter("fecModiRegi", tpUsuar.getFecModiRegi());
			    query.setParameter("codEstaCuenUsua", tpUsuar.getCodEstaCuenUsua());
			    query.setParameter("valTokeCuen", tpUsuar.getValTokeCuen());
			    query.setParameter("ideUsuaEmai", tpUsuar.getIdeUsuaEmai());
			    int registrosAfectados = query.executeUpdate();
			    
			    if(registrosAfectados != NumerosType.INDICADOR_POSITIVO_UNO.getValor().intValue()) {
			    	result = "Error: Se han actualizado "+registrosAfectados+" registros, Verifique.";
			    }else {
			    	result = CadenasType.INDICADOR_PROCESO_OK.getValor();
			    }
			    

        	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        		tx.commit();
        	}
            
        } catch (HibernateException e) {
            result = e.getMessage();
            tx.rollback();
        }

        return result;
	}

	@Override
	public String updateCuentaActivacionPendiente(TpUsuar tpUsuar) {
		
		String result = null;

        try {
        		
        		StringBuilder sb = new StringBuilder();
                sb.append(" update TpUsuar ");
                sb.append("set valTokeCuen = :valTokeCuen, ");
                sb.append(" fecCreaToke = :fecCreaToke, ");
                sb.append(" usuApliModi = :usuApliModi, ");
                sb.append(" fecModiRegi = :fecModiRegi ");
                sb.append(" where ideUsuaEmai = :ideUsuaEmai ");
//                sb.append(" and codUsua = :codUsua ");
                
        		Query query = session.createQuery(sb.toString());
        		
        		query.setParameter("valTokeCuen", tpUsuar.getValTokeCuen());
        		query.setParameter("fecCreaToke", tpUsuar.getFecCreaToke());
        		query.setParameter("usuApliModi", tpUsuar.getUsuApliModi());
        		query.setParameter("fecModiRegi", tpUsuar.getFecModiRegi());
			    query.setParameter("ideUsuaEmai", tpUsuar.getIdeUsuaEmai());
			    int registrosAfectados = query.executeUpdate();
			    
			    if(registrosAfectados != NumerosType.INDICADOR_POSITIVO_UNO.getValor().intValue()) {
			    	result = "Error: Se han actualizado "+registrosAfectados+" registros, Verifique.";
			    }else {
			    	result = CadenasType.INDICADOR_PROCESO_OK.getValor();
			    }
			    

        	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        		tx.commit();
        	}
            
        } catch (HibernateException e) {
            result = e.getMessage();
            tx.rollback();
        }

        return result;
	}

	@Override
	public String updateCuentaRestablece(TpUsuar tpUsuar) {
		
		String result = null;

        try {
        		
        		StringBuilder sb = new StringBuilder();
                sb.append(" update TpUsuar ");
                sb.append("set valTokeRestCuen = :valTokeRestCuen, ");
                sb.append(" fecCreaTokeRestCuen = :fecCreaTokeRestCuen, ");
                sb.append(" usuApliModi = :usuApliModi, ");
                sb.append(" fecModiRegi = :fecModiRegi ");
                sb.append(" where ideUsuaEmai = :ideUsuaEmai ");
                
        		Query query = session.createQuery(sb.toString());
        		
        		query.setParameter("valTokeRestCuen", tpUsuar.getValTokeRestCuen());
        		query.setParameter("fecCreaTokeRestCuen", tpUsuar.getFecCreaTokeRestCuen());
        		query.setParameter("usuApliModi", tpUsuar.getUsuApliModi());
        		query.setParameter("fecModiRegi", tpUsuar.getFecModiRegi());
			    query.setParameter("ideUsuaEmai", tpUsuar.getIdeUsuaEmai());
			    int registrosAfectados = query.executeUpdate();
			    
			    if(registrosAfectados != NumerosType.INDICADOR_POSITIVO_UNO.getValor().intValue()) {
			    	result = "Error: Se han actualizado "+registrosAfectados+" registros, Verifique.";
			    }else {
			    	result = CadenasType.INDICADOR_PROCESO_OK.getValor();
			    }
			    

        	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        		tx.commit();
        	}
            
        } catch (HibernateException e) {
            result = e.getMessage();
            tx.rollback();
        }

        return result;
	}

	@Override
	public String updateGeneraClave(TpUsuar tpUsuar) {
		
		String result = null;

        try {
        		
        		StringBuilder sb = new StringBuilder();
        		
                sb.append(" update TpUsuar ");
                sb.append("set valTokeRestCuen = null, ");
                sb.append(" fecCreaTokeRestCuen = null, ");
                sb.append(" codClav = :codClav, ");
                sb.append(" usuApliModi = :usuApliModi, ");
                sb.append(" fecModiRegi = :fecModiRegi, ");
                sb.append(" fecUltiModiClav = :fecUltiModiClav ");
                sb.append(" where ideUsuaEmai = :ideUsuaEmai ");
                sb.append(" and valTokeRestCuen = :valTokeRestCuen ");
                
        		Query query = session.createQuery(sb.toString());
        		
        		query.setParameter("valTokeRestCuen", tpUsuar.getValTokeRestCuen());
        		query.setParameter("codClav", tpUsuar.getCodClav());
        		query.setParameter("usuApliModi", tpUsuar.getUsuApliModi());
        		query.setParameter("fecModiRegi", tpUsuar.getFecModiRegi());
        		query.setParameter("fecUltiModiClav", tpUsuar.getFecUltiModiClav());
			    query.setParameter("ideUsuaEmai", tpUsuar.getIdeUsuaEmai());
			    int registrosAfectados = query.executeUpdate();
			    
			    if(registrosAfectados != NumerosType.INDICADOR_POSITIVO_UNO.getValor().intValue()) {
			    	result = "Error: Se han actualizado "+registrosAfectados+" registros, Verifique.";
			    }else {
			    	result = CadenasType.INDICADOR_PROCESO_OK.getValor();
			    }
			    

        	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        		tx.commit();
        	}
            
        } catch (HibernateException e) {
            result = e.getMessage();
            tx.rollback();
        }

        return result;
	}

	@Override
	public List<TpTipoDocumPerso> getTipoDocumentoPersona(TpTipoDocumPerso tpTipoDocumPerso) {
		
        StringBuilder sb = new StringBuilder();
        sb.append("select ttdp from TpTipoDocumPerso ttdp");
        sb.append(" where ttdp.indEsta = :indEsta ");
        sb.append(" and ttdp.tpTipoPerso.codTipoPers = :codTipoPers ");
        
        Query<TpTipoDocumPerso> query = session.createQuery(sb.toString());
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        query.setParameter("codTipoPers", tpTipoDocumPerso.getTpTipoPerso().getCodTipoPers());
        List<TpTipoDocumPerso> list = query.list();
        
        session.close();

        return list;
	}

}
