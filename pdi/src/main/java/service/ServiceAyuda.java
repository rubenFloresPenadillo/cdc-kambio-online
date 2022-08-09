package service;

import java.util.List;

import dto.TpAyudaPreguDto;

public interface ServiceAyuda {
	public List<TpAyudaPreguDto> getPreguntasDisponibles (TpAyudaPreguDto tpAyudaPreguDto);
	public String insertUpdate(TpAyudaPreguDto tpAyudaPreguDto);
}
