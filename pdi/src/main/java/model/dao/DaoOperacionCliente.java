package model.dao;

import java.math.BigInteger;
import java.util.List;

import dto.TpOperaClienDto;
import hibernate.entidades.TpOperaClien;

public interface DaoOperacionCliente {
	
	public void closeSesion();
	public Object[] getOperacionCliente (TpOperaClien tpOperaClien);
    public String insertUpdate(TpOperaClien tpOperaClien);
    public String actualizarEstadoOperacionCliente(TpOperaClien tpOperaClien);
    public List<Object[]> getOperacionesCliente (TpOperaClienDto tpOperaClienDto);
    public BigInteger getCodigoUnicoOperacion();
    public String cancelarOperacionPorLimiteDeTiempo(Integer valorTiempoLimite);
}
