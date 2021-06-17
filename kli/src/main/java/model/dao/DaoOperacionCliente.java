package model.dao;

import java.util.List;

import hibernate.entidades.TpOperaClien;

public interface DaoOperacionCliente {
	
	public void closeSesion();
	public Object[] getOperacionCliente (TpOperaClien tpOperaClien);
    public String insertUpdate(TpOperaClien tpOperaClien);
    public String actualizarEstadoOperacionCliente(TpOperaClien tpOperaClien);
    public List<Object[]> getOperacionesCliente (TpOperaClien tpOperaClien);
}
