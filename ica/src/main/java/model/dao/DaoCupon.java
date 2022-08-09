package model.dao;

import java.util.List;

import dto.TpCuponDto;
import hibernate.entidades.TpCupon;

public interface DaoCupon {

//	public void closeSesion();
    public List<TpCupon> getCuponesDisponibles (TpCupon tpCupon);
//    public List<TpDivis> getDivisasCuentasDisponibles (TpDivis TpDivis);
//    public List<TpTipoCuent> getTipoCuentasBacarias (TpTipoCuent tpTipoCuent);
    public List<Object[]> getCupones (TpCuponDto tpCuponDto);
    public String insertUpdate(TpCupon tpCupon);
}
