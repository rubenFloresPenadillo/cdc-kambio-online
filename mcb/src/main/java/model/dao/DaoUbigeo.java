package model.dao;

import java.util.List;

import hibernate.entidades.TpDepar;
import hibernate.entidades.TpDistr;
import hibernate.entidades.TpPais;
import hibernate.entidades.TpProvi;

public interface DaoUbigeo {

	public void closeSesion();
    public List<Object[]> getPaises (TpPais tpPais);
    public List<Object[]> getDepartamentos ();
    public List<Object[]> getProvincias (Integer codigo);
    public List<Object[]> getDistritos (Integer codigo);
    public TpDepar getDepartamento (Integer codigo);
    public TpProvi getProvincia (Integer codigo);
    public TpDistr getDistrito (Integer codigo);
	
}
