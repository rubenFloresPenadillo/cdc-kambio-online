package service;

import java.util.List;

import dto.TpBancoDto;
import dto.TpCuponDto;

public interface ServiceCupon {
//	public void closeSesion();
	public List<TpCuponDto> getCuponesDisponibles (TpBancoDto tpBancoDto);
	public List<TpCuponDto> getCupones (TpCuponDto tpCuponDto);
	public String insertUpdate(TpCuponDto tpCuponDto);
}
