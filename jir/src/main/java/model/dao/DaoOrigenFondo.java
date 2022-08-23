package model.dao;

import java.util.List;

import hibernate.entidades.TpOrigeFondo;

public interface DaoOrigenFondo {

    public List<TpOrigeFondo> getOrigenFondos (TpOrigeFondo tpOrigenFondos);
}
