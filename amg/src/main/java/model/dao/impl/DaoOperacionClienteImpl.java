package model.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cadenas.util.ValidacionesString;
import hibernate.entidades.TpBanco;
import hibernate.entidades.TpClien;
import hibernate.entidades.TpCuentBanco;
import hibernate.entidades.TpDivis;
import hibernate.entidades.TpEstadOpera;
import hibernate.entidades.TpOperaClien;
import hibernate.entidades.TpTipoCambi;
import model.dao.DaoOperacionCliente;
import numeros.util.ValidacionesNumeros;
import util.types.CadenasType;
import util.types.ElementosTablasType;
import util.types.NumerosType;
import util.types.RegistroActivoType;

public class DaoOperacionClienteImpl implements DaoOperacionCliente {

    private Session session;
    private Transaction tx;

    public DaoOperacionClienteImpl(Session session) {
        this.session = session;
        tx = this.session.beginTransaction();
    }

	@Override
	public void closeSesion() {
		session.close();
	}

	@Override
	public String insertUpdate(TpOperaClien tpOperaClien) {
		 String result = null;
	        
	        Integer codOperClie = NumerosType.NUMERO_MINIMO_CERO.getValor();

	        try {
	        	
	        	if(	tpOperaClien.getCodOperClie() != null) {
	        		TpOperaClien tpOperaClienEntidad =  (TpOperaClien) session.get(TpOperaClien.class, tpOperaClien.getCodOperClie());
	            	if(tpOperaClienEntidad != null) {
	            		session.update(tpOperaClienEntidad);
	            	}
	        	}else {
	        		codOperClie = (Integer) session.save(tpOperaClien);
	        	}
	        	
	        	if(!ValidacionesNumeros.esCeroONuloEntero(codOperClie)) {
	        		//Datos completados
	        		
	        		StringBuilder sb = new StringBuilder();
	                sb.append(" update TpUsuar ");
	                sb.append("set codEstaOper = :codEstaOper ");
	                sb.append(", codOperClie = :codOperClie");
	                sb.append(", usuApliModi = :usuApliModi");
	                sb.append(", fecModiRegi = :fecModiRegi");
	                sb.append(" where codUsua = :codUsua ");
	        		
	        		Query query = session.createQuery(sb.toString());
				    query.setParameter("codEstaOper", tpOperaClien.getTpEstadOpera().getCodEstaOper());
				    query.setParameter("codOperClie", codOperClie);
				    query.setParameter("usuApliModi", tpOperaClien.getUsuApliModi());
				    query.setParameter("fecModiRegi", tpOperaClien.getFecModiRegi());
				    query.setParameter("codUsua", tpOperaClien.getTpClien().getTpUsuar().getCodUsua());
				    int registrosAfectados = query.executeUpdate();
				    
				    if(registrosAfectados != NumerosType.INDICADOR_POSITIVO_UNO.getValor().intValue()) {
				    	result = "Error: Se han actualizado "+registrosAfectados+" registros, Verifique.";
				    }else {
				    	result = CadenasType.INDICADOR_PROCESO_OK.getValor()+CadenasType.GUION.getValor()+codOperClie;
				    }
			        		
	        	}

	        	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
	        		tx.commit();
	        	}else {
	        		tx.rollback();
	        	}
	            
	        	session.close();
	        	
	        } catch (HibernateException e) {
	            result = e.getMessage();
	            tx.rollback();
	            session.close();
	        }

	        return result;
	}

	@Override
	public String actualizarEstadoOperacionCliente(TpOperaClien tpOperaClien) {
		
		String result = null;
		
		try {
			
			StringBuilder sbUsuario = new StringBuilder();
			sbUsuario.append(" update TpUsuar ");
			sbUsuario.append("set codEstaOper = :codEstaOper ");
			sbUsuario.append(", codOperClie = :codOperClie");
			sbUsuario.append(", usuApliModi = :usuApliModi");
			sbUsuario.append(", fecModiRegi = :fecModiRegi");
			sbUsuario.append(" where codUsua = :codUsua ");
			
			Query queryUsuario = session.createQuery(sbUsuario.toString());
			
			if(tpOperaClien.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OPERACION_VERIFICACION.getIdElemento())) {
				queryUsuario.setParameter("codEstaOper", tpOperaClien.getTpEstadOpera().getCodEstaOper());
				queryUsuario.setParameter("codOperClie", tpOperaClien.getCodOperClie());
			}else {
				queryUsuario.setParameter("codEstaOper", null);
				queryUsuario.setParameter("codOperClie", null);
			}
			queryUsuario.setParameter("usuApliModi", tpOperaClien.getUsuApliModi());
			queryUsuario.setParameter("fecModiRegi", tpOperaClien.getFecModiRegi());
			queryUsuario.setParameter("codUsua", tpOperaClien.getTpClien().getTpUsuar().getCodUsua());
			
			
			
		    int registrosAfectados = queryUsuario.executeUpdate();
		    
		    if(registrosAfectados != NumerosType.INDICADOR_POSITIVO_UNO.getValor().intValue()) {
		    	result = "Error: Se han actualizado "+registrosAfectados+" registros, Verifique.";
		    }else {
		    	
				StringBuilder sbOperacionCliente = new StringBuilder();
				sbOperacionCliente.append(" update TpOperaClien toc ");
				sbOperacionCliente.append("set toc.tpEstadOpera.codEstaOper = :codEstaOper ");
				sbOperacionCliente.append(", toc.usuApliModi = :usuApliModi");
				sbOperacionCliente.append(", toc.fecModiRegi = :fecModiRegi");
				
				if(!ValidacionesString.esNuloOVacio(tpOperaClien.getCodUnicOperClie())) {
					sbOperacionCliente.append(", toc.codUnicOperClie = :codUnicOperClie");
				}
				
				if(tpOperaClien.getFecVeriOper() != null) {
					sbOperacionCliente.append(", toc.fecVeriOper = :fecVeriOper");
				}
				
				if (tpOperaClien.getUsuApliFinaOper() != null && tpOperaClien.getFecFinaOper() != null) {
					sbOperacionCliente.append(", toc.usuApliFinaOper = :usuApliFinaOper");
					sbOperacionCliente.append(", toc.fecFinaOper = :fecFinaOper");
				}
				
				if(tpOperaClien.getUsuApliCancOper() != null && tpOperaClien.getFecCancOper() != null) {
					sbOperacionCliente.append(", toc.usuApliCancOper = :usuApliCancOper");
					sbOperacionCliente.append(", toc.fecCancOper = :fecCancOper");
					sbOperacionCliente.append(", toc.valTextComeCanc = :valTextComeCanc");
				}
				
				sbOperacionCliente.append(" where toc.codOperClie = :codOperClie ");
				
				Query queryOperacionCliente = session.createQuery(sbOperacionCliente.toString());
				queryOperacionCliente.setParameter("codEstaOper", tpOperaClien.getTpEstadOpera().getCodEstaOper());
				queryOperacionCliente.setParameter("usuApliModi", tpOperaClien.getUsuApliModi());
				queryOperacionCliente.setParameter("fecModiRegi", tpOperaClien.getFecModiRegi());
				
				if(!ValidacionesString.esNuloOVacio(tpOperaClien.getCodUnicOperClie())) {
					queryOperacionCliente.setParameter("codUnicOperClie", tpOperaClien.getCodUnicOperClie());
				}
				
				if(tpOperaClien.getFecVeriOper() != null) {
					queryOperacionCliente.setParameter("fecVeriOper", tpOperaClien.getFecVeriOper());
				}
				
				if (tpOperaClien.getUsuApliFinaOper() != null && tpOperaClien.getFecFinaOper() != null) {
					queryOperacionCliente.setParameter("usuApliFinaOper", tpOperaClien.getUsuApliFinaOper());
					queryOperacionCliente.setParameter("fecFinaOper", tpOperaClien.getFecFinaOper());
				}
				
				if(tpOperaClien.getUsuApliCancOper() != null && tpOperaClien.getFecCancOper() != null) {
					queryOperacionCliente.setParameter("usuApliCancOper", tpOperaClien.getUsuApliCancOper());
					queryOperacionCliente.setParameter("fecCancOper", tpOperaClien.getFecCancOper());
					queryOperacionCliente.setParameter("valTextComeCanc", tpOperaClien.getValTextComeCanc());
				}
				
				queryOperacionCliente.setParameter("codOperClie", tpOperaClien.getCodOperClie());
			    registrosAfectados = queryOperacionCliente.executeUpdate();
			    
			    
			    if(registrosAfectados != NumerosType.INDICADOR_POSITIVO_UNO.getValor().intValue()) {
			    	result = "Error: Se han actualizado "+registrosAfectados+" registros, Verifique.";
			    }else {
			    	result = CadenasType.INDICADOR_PROCESO_OK.getValor();
			    }
		    }
			
	    	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
	    		tx.commit();
	    	}else {
	    		tx.rollback();
	    	}
	    	session.close();
		} catch (HibernateException e) {
            result = e.getMessage();
            tx.rollback();
            session.close();
        }
		return result;
		
	}

	@Override
	public Object[] getOperacionCliente(TpOperaClien tpOperaClien) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select toc.codOperClie, toc.tpEstadOpera.codEstaOper , toc.indCompVent, toc.valTipoCambUsad, toc.monEnvi, toc.monReci,  toc.tpBanco.codBanc,  toc.tpDivisByCodDiviEnvi.codDivi, toc.tpCuentBancoByCodCuenBancClieReci.codCuenBanc, ");
		sb.append(" toc.tpClien.valPrimNombPers ");
		sb.append(" from TpOperaClien toc where 1=1 ");
		if(!ValidacionesNumeros.esCeroONuloEntero(tpOperaClien.getCodOperClie())) {
			sb.append("and toc.codOperClie = :codOperClie ");
		}

		Query query = session.createQuery(sb.toString());
		
		if(!ValidacionesNumeros.esCeroONuloEntero(tpOperaClien.getCodOperClie())) {
			query.setParameter("codOperClie", tpOperaClien.getCodOperClie());
		}

		Object[] resultado = (Object[]) query.uniqueResult();
		
		session.close();
        
		return resultado;
        
	}

	@Override
	public List<Object[]> getOperacionesCliente(TpOperaClien tpOperaClien) {
		
		StringBuilder sb = new StringBuilder();
        sb.append("select toc.codOperClie, toc.codUnicOperClie, toc.tpClien.tpUsuar.codUsua, toc.fecCreaRegi, toc.monEnvi, ");
        sb.append("toc.monReci, toc.valTipoCambUsad, toc.indCompVent, toc.tpEstadOpera.codEstaOper, toc.tpEstadOpera.desEstaOper, ");
        sb.append("toc.tpClien.tpUsuar.ideUsuaEmai, toc.tpClien.valPrimNombPers, toc.tpClien.valSeguNombPers, toc.tpClien.valPrimApelPers, toc.tpClien.valSeguApelPers, ");
        sb.append("toc.tpClien.valRazoSociPers, toc.tpClien.tpTipoDocumPerso.tpTipoPerso.codTipoPers, toc.tpCuentBancoByCodCuenBancClieEnvi.codCuenBanc, toc.tpCuentBancoByCodCuenBancClieReci.codCuenBanc, ");
        sb.append("toc.fecInicOper, toc.fecVeriOper, toc.fecFinaOper, toc.usuApliFinaOper, toc.fecCancOper, toc.usuApliCancOper, toc.valTextComeCanc, toc.tpClien.tpUsuar.codUsuaPadr, toc.tpClien.valNombPerf, toc.tpClien.valDocuEmpr");
        sb.append(" from TpOperaClien toc");
        sb.append(" where 1=1 ");
        sb.append(" and toc.indEsta = :indEsta ");
        
        if(!ValidacionesNumeros.esCeroONuloEntero(tpOperaClien.getTpClien().getCodClie())) {
            sb.append(" and toc.tpClien.codClie = :codClie ");        	
        }
        
        if(!ValidacionesString.esNuloOVacio(tpOperaClien.getCodUnicOperClie())) {
            sb.append(" and toc.codUnicOperClie = :codUnicOperClie ");        	
        }
       
        
        sb.append(" order by toc.codOperClie desc");
        
        Query query = session.createQuery(sb.toString());
        
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        
        if(!ValidacionesNumeros.esCeroONuloEntero(tpOperaClien.getTpClien().getCodClie())) {
            query.setParameter("codClie", tpOperaClien.getTpClien().getCodClie());      	
        }
        
        if(!ValidacionesString.esNuloOVacio(tpOperaClien.getCodUnicOperClie())) {
        	query.setParameter("codUnicOperClie", tpOperaClien.getCodUnicOperClie());      	     	
        }

        List<Object[]> list = query.list();
        
        session.close();

        return list;
        
        
	}
	
}
