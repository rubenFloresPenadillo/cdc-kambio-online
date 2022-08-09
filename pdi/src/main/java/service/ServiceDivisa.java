package service;

import java.util.List;

import dto.TpDivisDto;
import dto.TpOperaClienDto;

public interface ServiceDivisa {

	public List<TpDivisDto> getDivisas(TpDivisDto tpDivisDto);
	public String insertUpdate(TpDivisDto tpDivisDto);
	
}
