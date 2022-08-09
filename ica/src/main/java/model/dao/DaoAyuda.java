package model.dao;

import java.util.List;

import hibernate.entidades.TpAyudaPregu;

public interface DaoAyuda {

//	public void closeSesion();
    public List<TpAyudaPregu> getPreguntasDisponibles (TpAyudaPregu tpAyudaPregu);
    public String insertUpdate(TpAyudaPregu tpAyudaPregu);
}
