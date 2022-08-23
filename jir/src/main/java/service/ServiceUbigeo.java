package service;

import java.util.List;

import dto.TpDeparDto;
import dto.TpDistrDto;
import dto.TpPaisDto;
import dto.TpProviDto;

public interface ServiceUbigeo {
	public void closeSesion();
	public List<TpPaisDto> getPaises (TpPaisDto tpPaisDto);
    public List<TpDeparDto> getDepartamentos ();
    public List<TpProviDto> getProvincias (Integer codigo);
    public List<TpDistrDto> getDistritos (Integer codigo);
    public TpDeparDto getDepartamento (Integer codigo);
    public TpProviDto getProvincia (Integer codigo);
    public TpDistrDto getDistrito (Integer codigo);
}
