package model.dao;

import java.util.List;

import hibernate.entidades.TpEntra;

public interface DaoEntrada {
    public List<TpEntra> getEntradasDisponibles (TpEntra tpEntra);
    public List<Object[]> getEntradas (TpEntra tpEntra);
    public List<Object[]> getUltimasEntradas (Integer numeroDeEntradas);
    public String insertUpdate(TpEntra tpEntra);
    public Object[] getEntrada(TpEntra TpEntra);
}
