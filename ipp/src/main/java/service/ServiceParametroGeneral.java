package service;

import java.util.List;

import dto.TpParamGenerDto;

public interface ServiceParametroGeneral {

	public List<TpParamGenerDto> getParametrosGenerales(TpParamGenerDto tpParamGenerDto);
	public String insertUpdate(TpParamGenerDto tpParamGenerDto);
	
}
