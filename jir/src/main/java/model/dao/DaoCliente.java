package model.dao;

import java.util.List;

import dto.TpClienDto;
import dto.TpCuentBancoDto;
import dto.TpSectoEconoDto;
import hibernate.entidades.TpClien;
import hibernate.entidades.TpCuentBanco;
import hibernate.entidades.TpUsuar;

public interface DaoCliente {
	
	public void closeSesion();
    public String insertUpdate(TpClien tpClien);
    public TpClien get(Integer codClie);
    public TpUsuar getUsuar(Integer codUsua);
    public String insertUpdateCuentaBanco(TpCuentBanco tpCuentBanco);
    public List<Object[]> getCuentasBanco(TpCuentBanco tpCuentBanco);
    public Object[] getCuentaBanco(TpCuentBanco tpCuentBanco);
    public Integer getExisteCuentaBancariaCliente(Integer codClie);
    public Integer getExisteBancoEnNegocio(TpCuentBancoDto tpCuentBancoDto);
	public List<Object[]> getListaSectorEconomico(TpSectoEconoDto tpSectoEconoDto);
	public List<Object[]> getListaActividadEconomica(TpSectoEconoDto tpSectoEconoDto);
	public String insertUpdateEnterprise(TpUsuar tpUsuar, TpClien tpClien);
	public List<Object[]> getListaPerfilesEmpresa(TpClienDto tpClienDto);
    
}
