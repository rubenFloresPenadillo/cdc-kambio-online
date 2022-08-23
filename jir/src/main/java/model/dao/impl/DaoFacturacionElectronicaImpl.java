package model.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cadenas.util.ValidacionesString;
import hibernate.entidades.TpBanco;
import hibernate.entidades.TpFactuDocumCabec;
import hibernate.entidades.TpFactuDocumDetal;
import hibernate.entidades.TpFactuSerieCorre;
import hibernate.entidades.TpUsuar;
import loggerUtil.LoggerUtil;
import model.dao.DaoFacturacionElectronica;
import numeros.util.ValidacionesNumeros;
import util.types.CadenasType;
import util.types.EstadosInternosDocumentosFEType;
import util.types.NumerosType;
import util.types.RegistroActivoType;

public class DaoFacturacionElectronicaImpl implements DaoFacturacionElectronica {

    private Session session;
    private Transaction tx;

    public DaoFacturacionElectronicaImpl(Session session) {
        this.session = session;
        tx = this.session.beginTransaction();
    }

	@Override
	public String generarComprobanteElectronico(TpFactuDocumCabec tpFactuDocumCabec,
			List<TpFactuDocumDetal> listTpFactuDocumDetal) {
		
		String result = null;
	
		try {
        	
			// Recuperamos el correlativo segun el tipo de documento
			TpFactuSerieCorre tpFactuSerieCorreResultado = null;
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("from TpFactuSerieCorre tfsc ");
			sb.append("where tfsc.indEsta = 1 ");
			
			if(!ValidacionesString.esNuloOVacio(tpFactuDocumCabec.getDesCortDocuGene())) {
				sb.append("and tfsc.tpFactuTipoDocumElect.desCortTipoDocuElec = :desCortTipoDocuElec ");
			}
		
			Query query = session.createQuery(sb.toString());
			
			if(!ValidacionesString.esNuloOVacio(tpFactuDocumCabec.getDesCortDocuGene())) {
				query.setParameter("desCortTipoDocuElec", tpFactuDocumCabec.getDesCortDocuGene());
			}

			tpFactuSerieCorreResultado = (TpFactuSerieCorre) query.uniqueResult();
			
			
        	// Valida si se tiene un correlativo configurado para dicho documento
        	if( tpFactuSerieCorreResultado != null && tpFactuSerieCorreResultado.getValCorrUsadUlti() != null) {
        		
        		tpFactuDocumCabec.setSerie(tpFactuSerieCorreResultado.getValSeri());
        		Integer nuevoCorrelativo = tpFactuSerieCorreResultado.getValCorrUsadUlti() + NumerosType.INDICADOR_POSITIVO_UNO.getValor();
        		tpFactuDocumCabec.setNumero(nuevoCorrelativo);
        		Integer codFactDocuCabec = (Integer) session.save(tpFactuDocumCabec);
        		tpFactuDocumCabec.setCodFactDocuCabec(codFactDocuCabec);
        		
        		for (TpFactuDocumDetal item : listTpFactuDocumDetal) {
        			item.setTpFactuDocumCabec(tpFactuDocumCabec);
        		    session.save(item);
        		}
        		
        		//Actualizamos el correlativo utilizado
        		tpFactuSerieCorreResultado.setValCorrUsadUlti(nuevoCorrelativo);
        		tpFactuSerieCorreResultado.setFecModiRegi(new Date());
        		tpFactuSerieCorreResultado.setUsuApliModi(tpFactuDocumCabec.getUsuApliCrea());
        		
        		session.update(tpFactuSerieCorreResultado);
        		
        		result = CadenasType.INDICADOR_PROCESO_OK.getValor();
		        		
        	}else {
        		result = "No se tiene configurado una serie para el tipo de documento.";
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
	public List<TpFactuDocumCabec> getDocumentosPorEstado(TpFactuDocumCabec tpFactuDocumCabec) {

		StringBuilder sb = new StringBuilder();
        sb.append("select td from TpFactuDocumCabec td");
        sb.append(" where td.indEsta = :indEsta");
        sb.append(" and td.codEstaDocu = :codEstaDocu");
        
        Query<TpFactuDocumCabec> query = session.createQuery(sb.toString());
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave().shortValue());
        query.setParameter("codEstaDocu", tpFactuDocumCabec.getCodEstaDocu());
        List<TpFactuDocumCabec> list = query.list();

        session.close();
        
        return list;

	}

	@Override
	public List<TpFactuDocumDetal> getDetallePorCodCabecera(Integer codFactDocuCabec) {
		
		StringBuilder sb = new StringBuilder();
        sb.append("select td from TpFactuDocumDetal td");
        sb.append(" where td.indEsta = :indEsta");
        sb.append(" and td.tpFactuDocumCabec.codFactDocuCabec = :codFactDocuCabec");
        
        Query<TpFactuDocumDetal> query = session.createQuery(sb.toString());
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave().shortValue());
        query.setParameter("codFactDocuCabec", codFactDocuCabec);
        List<TpFactuDocumDetal> list = query.list();

        session.close();
        
        return list;
        
	}

	@Override
	public String updateComprobante(TpFactuDocumCabec tpFactuDocumCabec) {
		
		String result =  "Error al actualizar estado del documento";
        
	     try {
	        	
	    	if(	tpFactuDocumCabec.getCodFactDocuCabec() != null) {
	    		session.update(tpFactuDocumCabec);
	            result = CadenasType.INDICADOR_PROCESO_OK.getValor();
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
