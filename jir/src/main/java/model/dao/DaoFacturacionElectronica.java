package model.dao;

import java.util.List;

import hibernate.entidades.TpFactuDocumCabec;
import hibernate.entidades.TpFactuDocumDetal;

public interface DaoFacturacionElectronica {

    public String generarComprobanteElectronico (TpFactuDocumCabec tpFactuDocumCabec,  List<TpFactuDocumDetal> listTpFactuDocumDetal);
    public List<TpFactuDocumCabec> getDocumentosPorEstado(TpFactuDocumCabec tpFactuDocumCabec);
    public List<TpFactuDocumDetal> getDetallePorCodCabecera(Integer codFactDocuCabec);
    public String updateComprobante(TpFactuDocumCabec tpFactuDocumCabec);

}
