package service;

import java.util.List;

import dto.TpClienDto;
import dto.TpTipoDocumPersoDto;
import dto.TpTipoPersoDto;
import dto.TpUsuarDto;

public interface ServiceUsuario {

	// coment
	public void closeSesion();
	public String insertUpdate(TpUsuarDto tpUsuarDto);
	public TpUsuarDto getUsuario(TpUsuarDto tpUsuarDto);
	public TpUsuarDto getOperacionEnCurso(Integer codigoUsuario);
	public List<TpTipoDocumPersoDto> getTipoDocumentoPersona(TpTipoDocumPersoDto tpTipoDocumPersoDto);
	public List<TpClienDto> getUsuarios(TpClienDto tpClienDto);
	public String updateCuenta(TpUsuarDto tpUsuarDto);
	public String updateCuentaActivacionPendiente(TpUsuarDto tpUsuarDto);
	public String updateCuentaRestablece(TpUsuarDto tpUsuarDto);
	public String updateGeneraClave(TpUsuarDto tpUsuarDto);
	public List<TpTipoPersoDto> getTipoPersona();
	
	
}
