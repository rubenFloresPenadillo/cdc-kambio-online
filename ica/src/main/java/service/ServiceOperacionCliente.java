package service;

import java.math.BigInteger;
import java.util.List;

import dto.TpOperaClienDto;

public interface ServiceOperacionCliente {

	public void closeSesion();
	public TpOperaClienDto getOperacionCliente (TpOperaClienDto tpOperaClienDto);
	public String insertUpdate(TpOperaClienDto tpOperaClienDto);
	public String actualizarEstadoOperacionCliente(TpOperaClienDto tpOperaClienDto);
	public List<TpOperaClienDto> getOperacionesCliente(TpOperaClienDto tpOperaClienDto);
	public BigInteger getCodigoUnicoOperacion();
	
}
