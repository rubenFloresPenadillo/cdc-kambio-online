package model.dao;

import java.util.List;

import hibernate.entidades.TpParamGener;

public interface DaoParametroGeneral {

	public List<Object[]> getParametrosGenerales (TpParamGener tpParamGener);
	public String insertUpdate(TpParamGener tpParamGener);

}
