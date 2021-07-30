package service;

import java.util.List;

import dto.TpBancoDto;
import dto.TpDivisDto;
import dto.TpTipoCuentDto;

public interface ServiceBanco {
//	public void closeSesion();
	public List<TpBancoDto> getBancosDisponibles (TpBancoDto tpBancoDto);
	public List<TpDivisDto> getDivisasCuentasDisponibles (TpDivisDto tpDivisDto);
	public List<TpTipoCuentDto> getTipoCuentasBacarias (TpTipoCuentDto tpTipoCuentDto);
	public List<TpBancoDto> getBancos (TpBancoDto tpBancoDto);
	public String insertUpdate(TpBancoDto tpBancoDto);
}
