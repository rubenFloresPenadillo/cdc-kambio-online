package model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import hibernate.entidades.TpTipoPregu;
import model.dao.DaoTipoPregunta;
import util.types.RegistroActivoType;

public class DaoTipoPreguntaImpl implements DaoTipoPregunta {

    private Session session;
//    private Transaction tx;

    public DaoTipoPreguntaImpl(Session session) {
        this.session = session;
//        tx = this.session.beginTransaction();
    }

	@Override
	public List<TpTipoPregu> getTipoPreguntas (TpTipoPregu tpTipoPregu) {
		
        StringBuilder sb = new StringBuilder();
        sb.append("select ttp from TpTipoPregu ttp");
        sb.append(" where ttp.indEsta = :indEsta ");
        sb.append(" order by ttp.codTipoPreg asc");
        
        Query<TpTipoPregu> query = session.createQuery(sb.toString());
        query.setParameter("indEsta", RegistroActivoType.ACTIVO.getLlave());
        List<TpTipoPregu> list = query.list();
        
        session.close();

        return list;
	}

}
