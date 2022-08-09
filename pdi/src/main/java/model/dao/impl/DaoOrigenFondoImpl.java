package model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import hibernate.entidades.TpOrigeFondo;
import model.dao.DaoOrigenFondo;
import util.types.NumerosType;
import util.types.RegistroActivoType;

public class DaoOrigenFondoImpl implements DaoOrigenFondo {

    private Session session;
//    private Transaction tx;

    public DaoOrigenFondoImpl(Session session) {
        this.session = session;
//        tx = this.session.beginTransaction();
    }

	@Override
	public List<TpOrigeFondo> getOrigenFondos (TpOrigeFondo tpOrigenFondos) {
		
        StringBuilder sb = new StringBuilder();
        sb.append("select tof from TpOrigeFondo tof");
        sb.append(" where tof.indEsta = :indEsta ");
        sb.append(" and tof.codOrigFond > :codOrigFond ");
        sb.append(" order by tof.desOrigFond asc");
        
        Query<TpOrigeFondo> query = session.createQuery(sb.toString());
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        query.setParameter("codOrigFond", NumerosType.NUMERO_MINIMO_CERO.getValor());
        List<TpOrigeFondo> list = query.list();
        
        session.close();

        return list;
	}

}
