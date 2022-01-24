package service;

import java.util.List;

import dto.TpAyudaPreguDto;
import dto.TpDivisDto;

public interface ServiceAyuda {
	public List<TpAyudaPreguDto> getPreguntasDisponibles (TpAyudaPreguDto tpAyudaPreguDto);
	public String insertUpdate(TpAyudaPreguDto tpAyudaPreguDto);
}
