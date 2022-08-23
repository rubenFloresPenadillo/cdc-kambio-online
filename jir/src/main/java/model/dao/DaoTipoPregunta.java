package model.dao;

import java.util.List;

import hibernate.entidades.TpTipoPregu;

public interface DaoTipoPregunta {

    public List<TpTipoPregu> getTipoPreguntas (TpTipoPregu tpTipoPregu);
}
