package model.dao;

import java.util.List;

import hibernate.entidades.TpBanco;
import hibernate.entidades.TpDivis;
import hibernate.entidades.TpTipoCuent;

public interface DaoBanco {

//	public void closeSesion();
    public List<TpBanco> getBancosDisponibles (TpBanco tpBanco);
    public List<TpDivis> getDivisasCuentasDisponibles (TpDivis TpDivis);
    public List<TpTipoCuent> getTipoCuentasBacarias (TpTipoCuent tpTipoCuent);
    public List<Object[]> getBancos (TpBanco tpBanco);
    public String insertUpdate(TpBanco tpBanco);
}
