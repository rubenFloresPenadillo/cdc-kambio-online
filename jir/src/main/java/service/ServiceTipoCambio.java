package service;

import java.util.List;

import dto.AudiTpTipoCambiDto;
import dto.TpTipoCambiDto;

public interface ServiceTipoCambio {

	public void closeSesion();
	public TpTipoCambiDto getTipoCambio (TpTipoCambiDto tpTipoCambiDto);
	public List<TpTipoCambiDto> getTipoCambios (TpTipoCambiDto tpTipoCambiDto);
	public List<AudiTpTipoCambiDto> getTipoCambiosVariacion (Integer cantidadResultados);
	public String insertUpdate(TpTipoCambiDto tpTipoCambiDto);
	
}
