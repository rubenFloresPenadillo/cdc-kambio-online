package model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.entidades.TpDepar;
import hibernate.entidades.TpDistr;
import hibernate.entidades.TpPais;
import hibernate.entidades.TpProvi;
import model.dao.DaoUbigeo;
import util.types.RegistroActivoType;

public class DaoUbigeoImpl implements DaoUbigeo {

    private Session session;
    private Transaction tx;

    public DaoUbigeoImpl(Session session) {
        this.session = session;
        tx = this.session.beginTransaction();
    }

	public void closeSesion() {
		session.close();
	}
	
	@Override
	public List<Object[]> getPaises(TpPais tpPais) {
		
        StringBuilder sb = new StringBuilder();
        sb.append("select tp.codPais, tp.nomPais, tp.desGent from TpPais tp");
        sb.append(" where tp.indEsta = :indEsta");
        
        Query query = session.createQuery(sb.toString());
        
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        List<Object[]> list = query.list();
        
        session.close();

        return list;
	}

	@Override
	public List<Object[]> getDepartamentos() {
        StringBuilder sb = new StringBuilder();
        sb.append("select td.codDepa, td.desDepa from TpDepar td");
        sb.append(" where td.indEsta = :indEsta");
        
        Query query = session.createQuery(sb.toString());
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        List<Object[]> list = query.list();

        session.close();
        
        return list;
	}

	@Override
	public List<Object[]> getProvincias(Integer codigo) {
        StringBuilder sb = new StringBuilder();
        sb.append("select tp.codProv, tp.desProv from TpProvi tp");
        sb.append(" where tp.indEsta = :indEsta ");
        sb.append(" and tp.tpDepar.codDepa = :codDepa ");  
        
        Query query = session.createQuery(sb.toString());
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        query.setParameter("codDepa", codigo);
        List<Object[]> list = query.list();

        session.close();
        
        return list;
	}

	@Override
	public List<Object[]> getDistritos(Integer codigo) {
        StringBuilder sb = new StringBuilder();
        sb.append("select td.codDist, td.desDist from TpDistr td");
        sb.append(" where td.indEsta = :indEsta ");
        sb.append(" and td.tpProvi.codProv = :codProv ");  
        
        Query query = session.createQuery(sb.toString());
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        query.setParameter("codProv", codigo);
        List<Object[]> list = query.list();

        session.close();
        
        return list;
	}

	@Override
	public TpDepar getDepartamento(Integer codigo) {
		TpDepar tpDepar =  (TpDepar) session.get(TpDepar.class, codigo);
		session.close();
        return tpDepar;
	}

	@Override
	public TpProvi getProvincia(Integer codigo) {
		TpProvi tpProvi =  (TpProvi) session.get(TpProvi.class, codigo);
        return tpProvi;
	}

	@Override
	public TpDistr getDistrito(Integer codigo) {
		TpDistr tpDistr =  (TpDistr) session.get(TpDistr.class, codigo);
		session.close();
        return tpDistr;
	}


    
	

}
