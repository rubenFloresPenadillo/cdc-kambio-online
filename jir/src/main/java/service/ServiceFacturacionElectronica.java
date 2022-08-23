package service;

import java.util.List;

import dto.TpFactuDocumCabecDto;
import dto.TpFactuDocumDetalDto;

public interface ServiceFacturacionElectronica {
//	public void closeSesion();
	public String generarComprobanteElectronico (TpFactuDocumCabecDto tpFactuDocumCabecDto,  List<TpFactuDocumDetalDto> listTpFactuDocumDetalDto);
	public List<TpFactuDocumCabecDto> getDocumentosPorEstado(TpFactuDocumCabecDto tpFactuDocumCabecDto);
	public List<TpFactuDocumDetalDto> getDetallePorCodCabecera(Integer codFactDocuCabec);
	public String updateComprobante(TpFactuDocumCabecDto tpFactuDocumCabecDto);
}
