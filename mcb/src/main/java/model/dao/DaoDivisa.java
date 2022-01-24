package model.dao;

import java.util.List;

import hibernate.entidades.TpDivis;
import hibernate.entidades.TpOperaClien;

public interface DaoDivisa {

	public List<Object[]> getDivisas (TpDivis tpDivis);
	public String insertUpdate(TpDivis tpDivis);

}
