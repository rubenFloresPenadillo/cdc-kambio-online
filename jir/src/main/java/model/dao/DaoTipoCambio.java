package model.dao;

import java.util.List;

import hibernate.entidades.TpTipoCambi;

public interface DaoTipoCambio {

	public void closeSesion();
	public TpTipoCambi getTipoCambio (TpTipoCambi tpTipoCambi);
	public List<Object[]> getTipoCambios (TpTipoCambi tpTipoCambi);
	public List<Object[]> getTipoCambiosVariacion (Integer cantidadResultados);
	public String insertUpdate(TpTipoCambi tpTipoCambi);
}
