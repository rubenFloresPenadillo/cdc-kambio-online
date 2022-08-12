package model.dao.impl;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.util.StringUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cadenas.util.ValidacionesString;
import dto.TpOperaClienDto;
import hibernate.entidades.TpCupon;
import hibernate.entidades.TpOperaClien;
import hibernate.entidades.TpOperaCupon;
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
		
		String result = CadenasType.VACIO.getValor();
		
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
				
				if(!ValidacionesString.esNuloOVacio(tpOperaClien.getCodTranBanc())) {
					sbOperacionCliente.append(", toc.codTranBanc = :codTranBanc");
				}
				
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
				
				if(!ValidacionesString.esNuloOVacio(tpOperaClien.getCodTranBanc())) {
					queryOperacionCliente.setParameter("codTranBanc", tpOperaClien.getCodTranBanc());
				}
				
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
			    	// Operacion en estado finalizada, solo aqui aplica el cupon
			    	if(tpOperaClien.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OPERACION_FINALIZADA.getIdElemento()) && 
			    			tpOperaClien.getCodCupoUsad() != null ) {
			    		
			    		StringBuilder sbCupon = new StringBuilder();
			    		sbCupon.append(" update TpCupon tcu ");
			    		sbCupon.append("set tcu.canCupo = tcu.canCupo - 1,  ");
			    		sbCupon.append(" tcu.fecModiRegi = CURRENT_TIMESTAMP, ");
			    		sbCupon.append(" tcu.usuApliModi = :usuApliModi ");
			    		sbCupon.append(" where tcu.canCupo > 0 ");
			    		sbCupon.append(" and tcu.codCupo = :codCupo ");
						
			    		Query queryCupon = session.createQuery(sbCupon.toString());
			    		queryCupon.setParameter("usuApliModi", tpOperaClien.getUsuApliModi());
			    		queryCupon.setParameter("codCupo", tpOperaClien.getCodCupoUsad());
			    		
			    		registrosAfectados = queryCupon.executeUpdate();
			    		
			    		if(registrosAfectados != NumerosType.INDICADOR_POSITIVO_UNO.getValor().intValue()) {
					    	result = "Error: Se han actualizado "+registrosAfectados+" registros de la tabla Cupon, Verifique.";
					    }else {
					    	// Si hay cupon en la TpCupon y se actualiza
					    	TpCupon cuponUsado = new TpCupon();
				    		cuponUsado.setCodCupo(tpOperaClien.getCodCupoUsad());
				    		
				    		TpOperaCupon orepacionCupon = new TpOperaCupon();
				    		orepacionCupon.setTpCupon(cuponUsado);
				    		orepacionCupon.setTpOperaClien(tpOperaClien);
				    		orepacionCupon.setNomCupoUsad(tpOperaClien.getNomCupoUsad());
				    		orepacionCupon.setMonDescCupoUsad(tpOperaClien.getMonDescCupoUsad());
				    		orepacionCupon.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
				    		orepacionCupon.setFecCreaRegi(new Date());
				    		orepacionCupon.setUsuApliCrea(tpOperaClien.getUsuApliModi());
				    		
				    		Integer codOperCupo = (Integer) session.save(orepacionCupon);
				    		
				    		if(!ValidacionesNumeros.esCeroONuloEntero(codOperCupo)) {
				    			// Todo bien con el cupon, se inserto de forma correcta con su relacion de operaciones
				    			result = CadenasType.INDICADOR_PROCESO_OK.getValor();
				    		}
					    	
					    }
							
			    	}else {
			    		// La operacion no tiene cupon y se procesa bien
			    		result = CadenasType.INDICADOR_PROCESO_OK.getValor();
			    	}
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
		
		sb.append("select toc.codOperClie, toc.tpEstadOpera.codEstaOper , toc.indCompVent, toc.valTipoCambUsad, toc.monEnvi, toc.monReci,  toc.tpDivisByCodDiviEnvi.codDivi, toc.tpCuentBancoByCodCuenBancClieReci.codCuenBanc, ");
		sb.append(" toc.tpClien.valPrimNombPers, toc.codCupoUsad, toc.nomCupoUsad, toc.monDescCupoUsad, toc.valCambCompCupo, toc.valCambVentCupo, toc.codTranBanc ");
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
	public List<Object[]> getOperacionesCliente(TpOperaClienDto tpOperaClienDto) {
		
		StringBuilder sb = new StringBuilder();
        sb.append("select toc.codOperClie, toc.codUnicOperClie, toc.tpClien.tpUsuar.codUsua, toc.fecCreaRegi, toc.monEnvi, ");
        sb.append("toc.monReci, toc.valTipoCambUsad, toc.indCompVent, toc.tpEstadOpera.codEstaOper, toc.tpEstadOpera.desEstaOper, ");
        sb.append("toc.tpClien.tpUsuar.ideUsuaEmai, toc.tpClien.valPrimNombPers, toc.tpClien.valSeguNombPers, toc.tpClien.valPrimApelPers, toc.tpClien.valSeguApelPers, ");
        sb.append("toc.tpClien.valRazoSociPers, toc.tpClien.tpTipoDocumPerso.tpTipoPerso.codTipoPers, toc.tpCuentBancoByCodCuenBancClieOrig.codCuenBanc, toc.tpCuentBancoByCodCuenBancCome.codCuenBanc, toc.tpCuentBancoByCodCuenBancClieReci.codCuenBanc, ");
        sb.append("toc.fecInicOper, toc.fecVeriOper, toc.fecFinaOper, toc.usuApliFinaOper, toc.fecCancOper, toc.usuApliCancOper, toc.valTextComeCanc, toc.tpClien.tpUsuar.codUsuaPadr, toc.tpClien.valNombPerf, toc.tpClien.valDocuEmpr, ");
        sb.append("toc.codCupoUsad, toc.nomCupoUsad, toc.monDescCupoUsad, toc.valCambCompCupo, toc.valCambVentCupo, toc.codTranBanc, toc.tpClien.valTelePers ");
        sb.append(" from TpOperaClien toc");  
        sb.append(" where 1=1 ");
        sb.append(" and toc.indEsta = :indEsta ");
        
        if(!ValidacionesNumeros.esCeroONuloEntero(tpOperaClienDto.getTpClien().getCodClie())) {
            sb.append(" and toc.tpClien.codClie = :codClie ");        	
        }
        
        if(!ValidacionesString.esNuloOVacio(tpOperaClienDto.getCodUnicOperClie())) {
            sb.append(" and toc.codUnicOperClie = :codUnicOperClie ");        	
        }
       
        if(tpOperaClienDto.getFecFiltroDesde() != null && tpOperaClienDto.getFecFiltroHasta() != null) {
            sb.append(" and DATE_TRUNC('D', toc.fecCreaRegi) >= :fechaDesde and DATE_TRUNC('D', toc.fecCreaRegi) <= :fechaHasta ");      
//            sb.append(" and toc.fecCreaRegi BETWEEN :fechaDesde and :fechaHasta");  
        }
        
        sb.append(" order by toc.codOperClie desc");
        
        Query query = session.createQuery(sb.toString());
        
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        
        if(!ValidacionesNumeros.esCeroONuloEntero(tpOperaClienDto.getTpClien().getCodClie())) {
            query.setParameter("codClie", tpOperaClienDto.getTpClien().getCodClie());      	
        }
        
        if(!ValidacionesString.esNuloOVacio(tpOperaClienDto.getCodUnicOperClie())) {
        	query.setParameter("codUnicOperClie", tpOperaClienDto.getCodUnicOperClie());      	     	
        }

        if(tpOperaClienDto.getFecFiltroDesde() != null && tpOperaClienDto.getFecFiltroHasta() != null) {
        	
//        	DateFormat df;    
//        	df= new SimpleDateFormat("y: m: d");
//        	String dDesde= df.format(tpOperaClienDto.getFecFiltroDesde());    
//        	String dHasta= df.format(tpOperaClienDto.getFecFiltroHasta()); 
        	
        	query.setParameter("fechaDesde", tpOperaClienDto.getFecFiltroDesde());
        	query.setParameter("fechaHasta", tpOperaClienDto.getFecFiltroHasta());
        }
        
        List<Object[]> list = query.list();
        
        session.close();

        return list;
        
        
	}

	@Override
	public BigInteger getCodigoUnicoOperacion() {
		
		BigInteger resultado = BigInteger.valueOf(NumerosType.NUMERO_MINIMO_CERO.getValor());
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT NEXTVAL('cod_unico_oper_seq') as codigo");
		
		Query query = session.createNativeQuery(sb.toString());
		
		resultado = (BigInteger) query.uniqueResult();
		
		/*Long psqlAutoincrement = (Long) session.createQuery(sb.toString()).addS
				
				
		                                                      .addScalar("id", Hibernate.LONG)
		                                                      .setParameter("psqlTableName", psqlTableName)
		                                                      .uniqueResult();
		*/
		
		session.close();
		
		return resultado;
	}

	@Override
	public String cancelarOperacionPorLimiteDeTiempo(Integer valorTiempoLimite) {
		
		String result = null;

        try {
        		
        		StringBuilder sbTpOperaClien = new StringBuilder();
        		sbTpOperaClien.append(" UPDATE TpOperaClien "); 
        		sbTpOperaClien.append(" SET tpEstadOpera.codEstaOper = :codEstaOperNuevo, ");
        		sbTpOperaClien.append(" fecModiRegi = now(), "); 
        		sbTpOperaClien.append(" usuApliModi = 'QUARTZCANCELAOPERACION', ");
        		sbTpOperaClien.append(" fecCancOper = now(), ");
        		sbTpOperaClien.append(" usuApliCancOper = 'QUARTZCANCELAOPERACION', ");
        		sbTpOperaClien.append(" valTextComeCanc = 'Se cancela de forma automatica por exceder el tiempo limite de :valorTiempoLimite min' ");
        		sbTpOperaClien.append(" WHERE date_part('day' ,now() - fecInicOper )*24*60 + date_part('hours', now() - fecInicOper)*60  + date_part('minutes', now() - fecInicOper)  >= :valorTiempoLimite ");
        		sbTpOperaClien.append(" AND tpEstadOpera.codEstaOper = :codEstaOperAntes ");
        		
        		Query queryTpOperaClien = session.createQuery(sbTpOperaClien.toString());
        		
        		queryTpOperaClien.setParameter("codEstaOperNuevo", ElementosTablasType.ESTADO_OPERACION_CANCELADA_AUTOMATICA.getIdElemento());
        		queryTpOperaClien.setParameter("valorTiempoLimite", valorTiempoLimite);
        		queryTpOperaClien.setParameter("codEstaOperAntes", ElementosTablasType.ESTADO_OPERACION_INICIADA.getIdElemento());
        		
        		
			    int registrosAfectados = queryTpOperaClien.executeUpdate();
			    
			    result = CadenasType.INDICADOR_PROCESO_OK.getValor()+" Se han actualizado "+registrosAfectados+" registros en la tabla Tp_Opera_Clien";
			    
			    if (registrosAfectados > NumerosType.NUMERO_MINIMO_CERO.getValor()) {
			    	
	        		StringBuilder sbTpUsuar = new StringBuilder();
	        		sbTpUsuar.append(" update TpUsuar ");
	        		sbTpUsuar.append("set codEstaOper = null ");
	        		sbTpUsuar.append(", codOperClie = null");
	        		sbTpUsuar.append(" where codEstaOper = :codEstaOperAntes ");
	        		sbTpUsuar.append(" and codOperClie is not null ");
			    	
			    	Query queryTpUsuar = session.createQuery(sbTpUsuar.toString());
	        		
				    queryTpUsuar.setParameter("codEstaOperAntes", ElementosTablasType.ESTADO_OPERACION_INICIADA.getIdElemento());
				    
				    int registrosAfectadosTablaUsuario = queryTpUsuar.executeUpdate();
				    
				    result = result + " ,Se han actualizado "+registrosAfectadosTablaUsuario+" registros en la tabla Tp_Usuar ";
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
	
}
