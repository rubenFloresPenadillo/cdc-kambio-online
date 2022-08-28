package service;

import java.util.List;

import dto.TpActivEconoDto;
import dto.TpClienDto;
import dto.TpCuentBancoDto;
import dto.TpSectoEconoDto;
import dto.TpUsuarDto;

public interface ServiceCliente {

	public void closeSesion();
	public String insertUpdate(TpClienDto tpClienDto);
	public TpClienDto get(Integer codClie);
	public String insertUpdateCuentaBanco(TpCuentBancoDto tpCuentBancoDto);
	public List<TpCuentBancoDto> getCuentasBanco(TpCuentBancoDto tpCuentBancoDto);
	public TpCuentBancoDto getCuentaBanco(TpCuentBancoDto tpCuentBancoDto);
	public Integer getExisteCuentaBancariaCliente(Integer codClie);
	public Integer getExisteBancoEnNegocio(TpCuentBancoDto tpCuentBancoDto);
	public List<TpSectoEconoDto> getListaSectorEconomico(TpSectoEconoDto tpSectoEconoDto);
	public List<TpActivEconoDto> getListaActividadEconomica(TpSectoEconoDto tpSectoEconoDto);
	public String insertUpdateEnterprise(TpUsuarDto tpUsuarDto, TpClienDto tpClienDto);
	public List<TpClienDto> getListaPerfilesEmpresa(TpClienDto tpClienDto);
	
	
}
