package model.dao;

import java.util.List;

import hibernate.entidades.TpClien;
import hibernate.entidades.TpTipoDocumPerso;
import hibernate.entidades.TpTipoPerso;
import hibernate.entidades.TpUsuar;

public interface DaoUsuario {
	
    public String insertUpdate(TpUsuar tpUsuar);
    public TpUsuar get(Integer codUsua);
    public TpUsuar getUsuario (TpUsuar tpUsuar);
    public Object[] getOperacionEnCurso(Integer codigoUsuario);
    public void closeSesion();
    public List<TpTipoDocumPerso> getTipoDocumentoPersona(TpTipoDocumPerso tpTipoDocumPerso);
    public List<Object[]> getUsuarios(TpClien tpClien);
    public String updateCuenta(TpUsuar tpUsuar);
    public String updateCuentaActivacionPendiente(TpUsuar tpUsuar);
    public String updateCuentaRestablece(TpUsuar tpUsuar);
    public String updateGeneraClave(TpUsuar tpUsuar);
    public List<TpTipoPerso> getTipoPersona();
    
}
