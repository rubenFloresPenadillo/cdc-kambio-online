package service;

import java.io.IOException;
import java.util.List;

import dto.TpEntraDto;
import dto.TpOperaClienDto;

public interface ServiceEntrada {
//	public void closeSesion();
	public List<TpEntraDto> getEntradasDisponibles (TpEntraDto tpEntraDto);
	public List<TpEntraDto> getEntradas (TpEntraDto tpEntraDto) throws IOException;
	public String insertUpdate(TpEntraDto tpEntraDto);
	public TpEntraDto getEntrada(TpEntraDto tpEntraDto);
	public List<TpEntraDto> getUltimasEntradas (Integer numeroDeEntradas) throws IOException;
}
