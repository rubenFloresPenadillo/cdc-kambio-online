package model.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dto.TpClienDto;
import dto.TpCuentBancoDto;
import dto.TpSectoEconoDto;
import hibernate.entidades.TpClien;
import hibernate.entidades.TpCuentBanco;
import hibernate.entidades.TpTipoPerso;
import hibernate.entidades.TpUsuar;
import loggerUtil.LoggerUtil;
import model.dao.DaoCliente;
import numeros.util.ValidacionesNumeros;
import util.types.CadenasType;
import util.types.NumerosType;
import util.types.RegistroActivoType;

public class DaoClienteImpl implements DaoCliente {

    private Session session;
    private Transaction tx;

    public DaoClienteImpl(Session session) {
        this.session = session;
        tx = this.session.beginTransaction();
    }

	@Override
	public void closeSesion() {
		session.close();
	}
	
    @Override
	public String insertUpdate(TpClien tpClien) {
        String result = null;
        
        Integer codClie = NumerosType.NUMERO_MINIMO_CERO.getValor();

        try {
        	
        	if(	tpClien.getCodClie() != null) {
//            	TpClien tpClienEntidad =  (TpClien) session.get(TpClien.class, tpClien.getCodClie());
//            	if(tpClienEntidad != null) {
            		session.update(tpClien);
            		result = CadenasType.INDICADOR_PROCESO_ACTUALIZA_OK.getValor();
//            	}
        	}else {
        		codClie = (Integer) session.save(tpClien);
        	}
        	
        	if(!ValidacionesNumeros.esCeroONuloEntero(codClie)) {
        		//Datos completados
        		
        		StringBuilder sb = new StringBuilder();
                sb.append(" update TpUsuar ");
                sb.append("set valNomb = :valNomb ");
                sb.append(", valNombRegi = :valNombRegi");
                sb.append(", indCompDato = :indCompDato");
                sb.append(", codClie = :codClie");
                sb.append(" where codUsua = :codUsua ");
        		
        		Query query = session.createQuery(sb.toString());
        		String valNomb = String.valueOf(tpClien.getValPrimNombPers().charAt(0)).toUpperCase()+String.valueOf(tpClien.getValPrimApelPers().charAt(0)).toUpperCase();
			    query.setParameter("valNomb", valNomb);
			    query.setParameter("indCompDato", RegistroActivoType.ACTIVO.getLlave());
			    query.setParameter("codClie", codClie);
			    query.setParameter("codUsua", tpClien.getTpUsuar().getCodUsua());
			    query.setParameter("valNombRegi", tpClien.getTpUsuar().getValNombRegi());
			    int registrosAfectados = query.executeUpdate();
			    
			    if(registrosAfectados != NumerosType.INDICADOR_POSITIVO_UNO.getValor().intValue()) {
			    	result = "Error: Se han actualizado "+registrosAfectados+" registros, Verifique.";
			    }else {
			    	result = CadenasType.INDICADOR_PROCESO_OK.getValor()+CadenasType.GUION.getValor()+codClie+CadenasType.GUION.getValor()+tpClien.getTpUsuar().getValNombRegi();
			    }
		        		
        	}

        	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor()) || result.startsWith(CadenasType.INDICADOR_PROCESO_ACTUALIZA_OK.getValor()) ) {
        		tx.commit();
        		session.close();
        	}
            
        } catch (HibernateException e) {
            result = e.getMessage();
            tx.rollback();
            session.close();
        }

        return result;
	}

	@Override
	public TpClien get(Integer codClie) {
		TpClien tpClien =  (TpClien) session.get(TpClien.class, codClie);
		session.close();
        return tpClien;
	}

	@Override
	public TpUsuar getUsuar(Integer codUsua) {
		TpUsuar tpUsuar =  (TpUsuar) session.get(TpUsuar.class, codUsua);
		session.close();
        return tpUsuar;
	}
	
	@Override
	public String insertUpdateCuentaBanco(TpCuentBanco tpCuentBanco) {
		
		String result = null;
        
	     Integer codCuenBanc = NumerosType.NUMERO_MINIMO_CERO.getValor();

	     try {
	        	
	    	if(	tpCuentBanco.getCodCuenBanc() != null) {
	            session.update(tpCuentBanco);
	            result = CadenasType.INDICADOR_PROCESO_OK.getValor()+CadenasType.GUION.getValor()+"U";
	        } else {
	        	codCuenBanc = (Integer) session.save(tpCuentBanco);
	        	if(!ValidacionesNumeros.esCeroONuloEntero(codCuenBanc)) {
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
	public List<Object[]> getCuentasBanco(TpCuentBanco tpCuentBanco) {
		
        StringBuilder sb = new StringBuilder();
        sb.append("select tcb.codCuenBanc, tcb.valCuenBanc, tcb.aliCuen, tcb.indCuenProp, tcb.tpBanco.codBanc, tcb.tpBanco.codCortBanc, tcb.tpBanco.nomBanc, tcb.tpDivis.codDivi, ");
        sb.append(" tcb.tpDivis.codIsoDivi, tcb.tpDivis.simDivi, tcb.tpDivis.nomDiviSing, tcb.tpTipoCuent.codTipoCuen, tcb.tpTipoCuent.desTipoCuen, tcb.valCuenInte, ");
        sb.append(" tcb.indEsta, tcb.usuApliCrea, tcb.fecCreaRegi, tcb.usuApliModi, tcb.fecModiRegi ");
        sb.append(" from TpCuentBanco tcb ");
        sb.append(" where tcb.tpClien.codClie = :codClie ");
        sb.append(" and tcb.tpBanco.indEsta = :indEstaBanco ");
        sb.append(" and tcb.tpDivis.indEsta = :indEstaDivis ");
        
        if(tpCuentBanco != null && !ValidacionesNumeros.esCeroONuloEntero(tpCuentBanco.getIndEsta())) {
        	sb.append(" and tcb.indEsta = :indEsta ");
        }

        sb.append(" order by tcb.tpBanco.nomBanc,  tcb.tpDivis.nomDiviSing, tcb.tpTipoCuent.desTipoCuen");
        
        Query query = session.createQuery(sb.toString());
        
        query.setParameter("codClie", tpCuentBanco.getTpClien().getCodClie());
        query.setParameter("indEstaBanco", RegistroActivoType.ACTIVO.getLlave());
        query.setParameter("indEstaDivis", RegistroActivoType.ACTIVO.getLlave());
        
        if(tpCuentBanco != null && !ValidacionesNumeros.esCeroONuloEntero(tpCuentBanco.getIndEsta())) {
        	query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        }
        List<Object[]> list = query.list();
        
        session.close();

        return list;
	}

	@Override
	public Object[] getCuentaBanco(TpCuentBanco tpCuentBanco) {
		
        StringBuilder sb = new StringBuilder();
        sb.append("select tcba.codCuenBanc, tcba.valCuenBanc, tcba.valCuenInte, tcba.tpBanco.codCortBanc, tcba.tpBanco.nomBanc, tcba.tpDivis.codIsoDivi, tcba.tpDivis.simDivi, ");
        sb.append(" tcba.tpClien.tpTipoDocumPerso.tpTipoPerso.codTipoPers, tcba.tpClien.tpTipoDocumPerso.nomTipoDocuPers, tcba.tpClien.valPrimNombPers, tcba.tpClien.valSeguNombPers, tcba.tpClien.valPrimApelPers, tcba.tpClien.valSeguApelPers, ");
        sb.append(" tcba.tpClien.valRazoSociPers, tcba.tpClien.valDocuPers, tcba.tpTipoCuent.desTipoCuen, tcba.aliCuen ");
        sb.append(" from TpCuentBanco tcba");
        sb.append(" where tcba.indEsta = :indEsta ");
        sb.append(" and tcba.tpBanco.indEsta = :indEsta ");
        sb.append(" and tcba.tpDivis.indEsta = :indEsta ");
        
        if(tpCuentBanco.getCodCuenBanc() != null) {
        	sb.append(" and tcba.codCuenBanc = :codCuenBanc ");
        }
        
        if(tpCuentBanco.getTpBanco().getCodBanc() != null) {
        	sb.append(" and tcba.tpBanco.codBanc = :codBanc ");
        }
        
        if(tpCuentBanco.getTpBanco().getCodCortBanc() != null) {
        	sb.append(" and tcba.tpBanco.codCortBanc = :codCortBanc ");
        }
        
        if(tpCuentBanco.getTpDivis().getCodDivi() != null) {
        	sb.append(" and tcba.tpDivis.codDivi = :codDivi ");
        }
        
        if(tpCuentBanco.getTpClien().getCodClie() != null) {
        	sb.append(" and tcba.tpClien.codClie = :codClie ");
       }
        
        Query query = session.createQuery(sb.toString());
        
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        
        if(tpCuentBanco.getCodCuenBanc() != null) {
        	query.setParameter("codCuenBanc", tpCuentBanco.getCodCuenBanc());
        }
        
        if(tpCuentBanco.getTpBanco().getCodBanc() != null) {
        	query.setParameter("codBanc", tpCuentBanco.getTpBanco().getCodBanc());
        }
        
        if(tpCuentBanco.getTpBanco().getCodCortBanc() != null) {
        	query.setParameter("codCortBanc", tpCuentBanco.getTpBanco().getCodCortBanc());
        }
        
        if(tpCuentBanco.getTpDivis().getCodDivi() != null) {
        	query.setParameter("codDivi", tpCuentBanco.getTpDivis().getCodDivi());
        }
        
        if(tpCuentBanco.getTpClien().getCodClie() != null) {
        	query.setParameter("codClie", tpCuentBanco.getTpClien().getCodClie());
        }
        
        
        Object[] resultado = (Object[]) query.uniqueResult();
        
        session.close();

        return resultado;
	}

	@Override
	public Integer getExisteCuentaBancariaCliente(Integer codClie) {
		
		Integer resultado = NumerosType.NUMERO_MINIMO_CERO.getValor();
		
		StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from TpCuentBanco tcba") ;
        sb.append(" where tcba.indEsta = :indEsta ");
        sb.append(" and tcba.tpClien.codClie = :codClie ");

        Query query = session.createQuery(sb.toString());
        
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        query.setParameter("codClie", codClie);
        
        resultado = (int) (long) query.uniqueResult();
        
        session.close();

        return resultado;
	}
    
	@Override
	public Integer getExisteBancoEnNegocio(TpCuentBancoDto tpCuentBancoDto) {
		
		Integer resultado = NumerosType.NUMERO_MINIMO_CERO.getValor();
		
		StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from TpBanco tpb, TpCuentBanco tcb") ;
        sb.append(" where tpb.indEsta = :indEsta ");
        sb.append(" and tcb.indEsta = :indEsta ");
        sb.append(" and tpb.codBanc = tcb.tpBanco.codBanc ");
        sb.append(" and tpb.indTienCuenNego = :indEsta ");
        sb.append(" and tcb.codCuenBanc = :codCuenBanc ");
        
        Query query = session.createQuery(sb.toString());
        
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        query.setParameter("codCuenBanc", tpCuentBancoDto.getCodCuenBanc());
        
        resultado = (int) (long) query.uniqueResult();
        
        session.close();

        return resultado;
	}

	@Override
	public List<Object[]> getListaSectorEconomico(TpSectoEconoDto tpSectoEconoDto) {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("select tse.codSectEcon, tse.desSectEcon  ");
        sb.append(" from TpSectoEcono tse ");
        sb.append(" where tse.indEsta = :indEsta ");
        sb.append(" order by tse.desSectEcon");
        
        Query query = session.createQuery(sb.toString());
        
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        
        List<Object[]> list = query.list();
        
        session.close();

        return list;
	}

	@Override
	public List<Object[]> getListaActividadEconomica(TpSectoEconoDto tpSectoEconoDto) {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("select tae.codActiEcon, tae.desActiEcon  ");
        sb.append("from TpActivEcono tae ");
        sb.append("where tae.indEsta = :indEsta ");
        sb.append(" and tae.codActiEcon  > :codActiEcon ");
        
        if(tpSectoEconoDto.getCodSectEcon() != null) {
        	sb.append(" and tae.tpSectoEcono.codSectEcon  = :codSectEcon");
        }
        
        sb.append(" order by tae.desActiEcon");
        
        Query query = session.createQuery(sb.toString());
        
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        query.setParameter("codActiEcon", NumerosType.NUMERO_MINIMO_CERO.getValor());
        if(tpSectoEconoDto.getCodSectEcon() != null) {
        	query.setParameter("codSectEcon", tpSectoEconoDto.getCodSectEcon());
        }
        
        List<Object[]> list = query.list();
        
        session.close();

        return list;
        
	}

	@Override
	public String insertUpdateEnterprise(TpUsuar tpUsuar, TpClien tpClien) {
		
	       String result = null;
	       Integer codUsua = NumerosType.NUMERO_MINIMO_CERO.getValor();
	       Integer codClie = NumerosType.NUMERO_MINIMO_CERO.getValor();
	       
	        try {
	        	
	        	TpTipoPerso tpTipoPersoEntidad =  (TpTipoPerso) session.get(TpTipoPerso.class, tpUsuar.getTpTipoPerso().getCodTipoPers());
	        	tpUsuar.setTpTipoPerso(tpTipoPersoEntidad);
	        	
	        	if(	tpUsuar.getCodUsua() != null) {
	            	session.update(tpUsuar);
	        	}else {
	        		codUsua = (Integer) session.save(tpUsuar);
	        		tpUsuar.setCodUsua(codUsua);
	        	}
	        	
	        	tpClien.setTpUsuar(tpUsuar);
	        	
	        	if(	tpClien.getCodClie() != null) {
	            	session.update(tpClien);
	            	result = CadenasType.INDICADOR_PROCESO_ACTUALIZA_OK.getValor();
	        	}else {
	        		codClie = (Integer) session.save(tpClien);
	        		result = CadenasType.INDICADOR_PROCESO_OK.getValor();
	        	}	        	
	            
	        	
	        	
	            tx.commit();
	            session.close();
	            
	        } catch (HibernateException e) {
	            result = e.getMessage();
		        LoggerUtil.getInstance().getLogger().error("Error en DaoClienteImpl.insertUpdateEnterprise");
		        LoggerUtil.getInstance().getLogger().error(e.getMessage());
		        LoggerUtil.getInstance().getLogger().error(e);
	            tx.rollback();
	            session.close();
	        }

	        return result;
	}

	@Override
	public List<Object[]> getListaPerfilesEmpresa(TpClienDto tpClienDto) {

		StringBuilder sb = new StringBuilder();
		sb.append("select tpc.codClie, tpc.valRazoSociPers, tpc.valNombPerf, tpc.tpUsuar.codUsua, tpc.tpUsuar.codOperClie, tpc.tpUsuar.codEstaOper ");
		sb.append(" from TpClien tpc");
		sb.append(" where tpc.tpUsuar.codUsuaPadr = :codUsuaPadr");
		sb.append(" and tpc.indEsta = :indEsta ");

		sb.append(" order by tpc.codClie ");

		Query query = session.createQuery(sb.toString());

		query.setParameter("codUsuaPadr", tpClienDto.getTpUsuar().getCodUsuaPadr());
		query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());

		List<Object[]> list = query.list();

		session.close();

		return list;
	}
    
}
